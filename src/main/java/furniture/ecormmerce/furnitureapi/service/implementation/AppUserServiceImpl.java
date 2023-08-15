package furniture.ecormmerce.furnitureapi.service.implementation;

import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.AppUser;
import furniture.ecormmerce.furnitureapi.data.model.Product;
import furniture.ecormmerce.furnitureapi.data.repository.AppUserRepository;
import furniture.ecormmerce.furnitureapi.exception.FurnitureException;
import furniture.ecormmerce.furnitureapi.exception.UserNotFoundException;
import furniture.ecormmerce.furnitureapi.service.interfaces.AppUserService;
import furniture.ecormmerce.furnitureapi.utils.ApplicationUtilities;
import furniture.ecormmerce.furnitureapi.utils.Responses;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static furniture.ecormmerce.furnitureapi.common.Messages.ACC_VERIFY_FAILURE;
import static furniture.ecormmerce.furnitureapi.common.Messages.USER_NOT_FOUND;
import static furniture.ecormmerce.furnitureapi.utils.ApplicationUtilities.NUMBER_OF_ITEMS_PER_PAGE;


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
	
	@Override
	public AppUser getUserById(long userId) {
		return repository.findById(userId)
				.orElseThrow (() -> new IllegalStateException("User not found"));
	}
	
	@Override
	public List<AppUser> getAllUsers() {
		return repository.findAll ();
	}
	
	@Override
	public AppUser getUserByFirstName(String firstName) {
		return repository.findAppUserByFirstName (firstName);
	}
	
	@Override
	public Page<AppUser> getAllUserByPage(int pageNumber) {
		if (pageNumber < 1) {pageNumber = 0;}
		else pageNumber = pageNumber-1;
		Pageable pageable = PageRequest.of (pageNumber, NUMBER_OF_ITEMS_PER_PAGE);
		return repository.findAll (pageable);
	}
	
	@Override
	@Transactional
	public void deleteUserById(Long id) {
		repository.deleteUserById(id);
	}
	
	@Override
	@Transactional
	public void deleteAllUser() {
		repository.deleteAll();
	}
	
	@Override
	@Transactional
	public void deleteByFirstName(String firstName) {
		repository.deleteByFirstName(firstName);
	}
	
	@Override
	@Transactional
	public void deleteByEmail(String email) {
		repository.deleteByEmail(email);
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
