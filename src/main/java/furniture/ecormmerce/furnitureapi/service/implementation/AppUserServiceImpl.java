package furniture.ecommerce.furnitureecommerce.service.implementation;

import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.model.AppUser;
import furniture.ecommerce.furnitureecommerce.data.repository.AppUserRepository;
import furniture.ecommerce.furnitureecommerce.exception.FurnitureException;
import furniture.ecommerce.furnitureecommerce.exception.UserNotFoundException;
import furniture.ecommerce.furnitureecommerce.service.interfaces.AppUserService;
import furniture.ecommerce.furnitureecommerce.utils.ApplicationUtilities;
import furniture.ecommerce.furnitureecommerce.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static furniture.ecommerce.furnitureecommerce.common.Messages.ACC_VERIFY_FAILURE;
import static furniture.ecommerce.furnitureecommerce.common.Messages.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
	private final AppUserRepository repository;
	@Override
	public AppUser saveUsers(AppUser user) {
		return repository.save (user);
	}
	
	@Override
	public AppUser getUserByEmail(String email) {
		return repository.findByEmail (email);
	}
	
	@Override
	public ApiResponse verifyAccount(Long userId, String token){
		if (ApplicationUtilities.isValidToken(token)) return getVerifiedResponse (userId);
		throw new FurnitureException (
				ACC_VERIFY_FAILURE+userId,
				HttpStatus.BAD_REQUEST
		);
	}
	
	private void enableAppUserAccount(AppUser user) {
		user.setEnabled (true);
		saveUsers (user);
	}
	
	private ApiResponse getVerifiedResponse(Long userId) {
		Optional<AppUser> foundUser = repository.findById (userId);
		if (foundUser.isEmpty ()){
			throw new UserNotFoundException (USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		foundUser.ifPresent (this::enableAppUserAccount);
		return Responses.okResponse (foundUser);
	}
}
