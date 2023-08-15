package furniture.ecormmerce.furnitureapi.service.interfaces;


import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.AppUser;
import furniture.ecormmerce.furnitureapi.data.model.Product;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface AppUserService  {
	AppUser saveUsers(AppUser user);
	AppUser getUserByEmail(String email);
	
	ApiResponse verifyAccount(Long userId, String token);
	
	AppUser getUserById(long userId);
	List<AppUser> getAllUsers();
	
	AppUser getUserByFirstName(String firstName);
	
	Page<AppUser> getAllUserByPage(int pageNumber);
	
	void deleteUserById(Long id);
	
	void deleteAllUser();
	
	void deleteByFirstName(String firstName);
	void deleteByEmail(String email);
}
