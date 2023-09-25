package furniture.ecormmerce.furnitureapi.service.implementation;

import furniture.ecormmerce.furnitureapi.config.mail.MailService;
import furniture.ecormmerce.furnitureapi.config.security.jwt.JwtGenerator;
import furniture.ecormmerce.furnitureapi.config.security.user.SecureUser;
import furniture.ecormmerce.furnitureapi.data.dto.request.LoginResponse;
import furniture.ecormmerce.furnitureapi.data.dto.request.RegisterRequest;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.enums.Role;
import furniture.ecormmerce.furnitureapi.data.model.AppUser;
import furniture.ecormmerce.furnitureapi.data.model.SecurityDetail;
import furniture.ecormmerce.furnitureapi.exception.LoginFailureException;
import furniture.ecormmerce.furnitureapi.exception.UserAlreadyExistsException;
import furniture.ecormmerce.furnitureapi.javaMail.JavaMailSenderService;
import furniture.ecormmerce.furnitureapi.javaMail.MailRequest;
import furniture.ecormmerce.furnitureapi.service.interfaces.AppUserService;
import furniture.ecormmerce.furnitureapi.service.interfaces.AuthService;
import furniture.ecormmerce.furnitureapi.service.security.SecurityDetailService;
import furniture.ecormmerce.furnitureapi.utils.EncryptionUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static furniture.ecormmerce.furnitureapi.common.Messages.EMAIL_ALREADY_EXIST;
import static furniture.ecormmerce.furnitureapi.common.Messages.LOGIN_FAIL;
import static furniture.ecormmerce.furnitureapi.utils.ApplicationUtilities.buildSendMessage;
import static furniture.ecormmerce.furnitureapi.utils.Responses.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final AppUserService service;
	private final MailService mailService;
	private final PasswordEncoder encoder;
	private final AuthenticationManager manager;
	private final JwtGenerator generator;
	private final JavaMailSenderService mailSenderService;
	private final SecurityDetailService securityDetailService;
	private final EncryptionUtils utils;
	@Override
	public ApiResponse signUp(RegisterRequest request) throws MessagingException {
		AppUser findUser = service.getUserByEmail (request.getEmail ());
		if (findUser != null) {
			throw new UserAlreadyExistsException (EMAIL_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
		}
		log.info ("request enter here");
		AppUser createUser = AppUser.builder ()
				.lastName (request.getLastName ())
				.firstName (request.getFirstName ())
				.email (request.getEmail ())
				.password (encoder.encode (request.getPassword ()))
				.confirmPassword (encoder.encode (request.getConfirmPassword ()))
				.phoneNumber (request.getPhoneNumber ())
				.createdAt (LocalDateTime.now ())
				.roles (List.of (Role.CUSTOMER))
				.build ();
		log.info ("request enter here also");
		var savedUser = service.saveUsers (createUser);
//		EmailNotificationRequest emailNotificationRequest =
//				buildNotificationRequest(savedUser.getEmail (),
//						savedUser.getLastName (),
//						savedUser.getId ());
		MailRequest mailRequest = buildSendMessage(savedUser.getEmail (),
				savedUser.getLastName (),
				savedUser.getId ());
//		var response = mailService.sendHtmlMail (emailNotificationRequest);
		var response = mailSenderService.sendMail (mailRequest);
		log.info ("request lastly");
		if (response == null){
			return getFailureMessage();
		}
		return createdResponse (savedUser);
	}
	
	
	
	@Override
	public ApiResponse signIn(LoginResponse response) {
		authenticateUser (response);
		AppUser foundUser = service.getUserByEmail (response.getEmail ());
		if (foundUser != null){
			String jwt = generator.generateToken (new SecureUser (foundUser));
			revokeAllUserToken (foundUser.getId ());
			saveToken (jwt, foundUser);
			return okResponse (jwt);
		}
		throw new LoginFailureException (LOGIN_FAIL, HttpStatus.BAD_REQUEST);
	}
	
	private void saveToken(String jwt, AppUser user){
		SecurityDetail securityDetail = SecurityDetail.builder ()
				.token (utils.encrypt (jwt))
				.isExpired (false)
				.isExpired (false)
				.user (user)
				.build ();
		securityDetailService.save (securityDetail);
	}
	
	private void revokeAllUserToken(Long userId){
		var allUsersToken = securityDetailService.findSecurityDetailByUserId (userId);
		if (allUsersToken == null)return;
		allUsersToken.forEach (securityDetail -> {
			securityDetail.setRevoked (true);
			securityDetail.setExpired (true);
			securityDetailService.save (securityDetail);
		});
	}
	private void authenticateUser(LoginResponse loginRequest){
		manager.authenticate (new UsernamePasswordAuthenticationToken (
				loginRequest.getEmail (),
				loginRequest.getPassword ()
		));
	}
}
