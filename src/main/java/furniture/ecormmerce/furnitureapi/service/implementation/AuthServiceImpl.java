package furniture.ecommerce.furnitureecommerce.service.implementation;

import furniture.ecommerce.furnitureecommerce.config.mail.MailService;
import furniture.ecommerce.furnitureecommerce.config.security.jwt.JwtGenerator;
import furniture.ecommerce.furnitureecommerce.data.dto.request.EmailNotificationRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.request.LoginResponse;
import furniture.ecommerce.furnitureecommerce.data.dto.request.RegisterRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.dto.response.TokenResponse;
import furniture.ecommerce.furnitureecommerce.data.model.AppUser;
import furniture.ecommerce.furnitureecommerce.exception.LoginFailureException;
import furniture.ecommerce.furnitureecommerce.exception.UserAlreadyExistsException;
import furniture.ecommerce.furnitureecommerce.service.interfaces.AppUserService;
import furniture.ecommerce.furnitureecommerce.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static furniture.ecommerce.furnitureecommerce.common.Messages.EMAIL_ALREADY_EXIST;
import static furniture.ecommerce.furnitureecommerce.common.Messages.LOGIN_FAIL;
import static furniture.ecommerce.furnitureecommerce.utils.ApplicationUtilities.buildNotificationRequest;
import static furniture.ecommerce.furnitureecommerce.utils.Responses.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final AppUserService service;
	private final MailService mailService;
	private final PasswordEncoder encoder;
	private final AuthenticationManager manager;
	private final JwtGenerator generator;
	@Override
	public ApiResponse signUp(RegisterRequest request) {
		AppUser findUser = service.getUserByEmail (request.getEmail ());
		if (findUser != null) {
			throw new UserAlreadyExistsException (EMAIL_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
		}
		AppUser createUser = AppUser.builder ()
				.lastName (request.getLastName ())
				.firstName (request.getFirstName ())
				.email (request.getEmail ())
				.password (encoder.encode (request.getPassword ()))
				.confirmPassword (encoder.encode (request.getConfirmpassword ()))
				.phoneNumber (request.getPhoneNumber ())
				.createdAt (LocalDateTime.now ())
				.build ();
		var savedUser = service.saveUsers (createUser);
		EmailNotificationRequest emailNotificationRequest =
				buildNotificationRequest(savedUser.getEmail (),
						savedUser.getLastName (),
						savedUser.getId ());
		var response = mailService.sendHtmlMail (emailNotificationRequest);
		if (response == null){
			return getFailureMessage();
		}
		return createdResponse (savedUser);
	}
	
	@Override
	public TokenResponse signIn(LoginResponse response) {
		try {
			Authentication authentication = manager.authenticate (
					new UsernamePasswordAuthenticationToken (
							response.getEmail (),
							response.getPassword()
					)
			);
			String token = generator.generateToken (authentication, Long.valueOf (60000));
			String refreshToken = generator.generateToken (authentication, Long.valueOf (604800000));
			return getLoginResponse (token, refreshToken);
		}catch (Exception e){
			throw new LoginFailureException (LOGIN_FAIL, HttpStatus.BAD_REQUEST);
		}
	}
}
