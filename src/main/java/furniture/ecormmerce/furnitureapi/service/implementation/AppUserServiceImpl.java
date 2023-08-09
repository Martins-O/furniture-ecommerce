package furniture.ecormmerce.furnitureapi.service.implementation;

import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.AppUser;
import furniture.ecormmerce.furnitureapi.data.repository.AppUserRepository;
import furniture.ecormmerce.furnitureapi.exception.FurnitureException;
import furniture.ecormmerce.furnitureapi.exception.UserNotFoundException;
import furniture.ecormmerce.furnitureapi.service.interfaces.AppUserService;
import furniture.ecormmerce.furnitureapi.utils.ApplicationUtilities;
import furniture.ecormmerce.furnitureapi.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static furniture.ecormmerce.furnitureapi.common.Messages.ACC_VERIFY_FAILURE;
import static furniture.ecormmerce.furnitureapi.common.Messages.USER_NOT_FOUND;


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
