package furniture.ecommerce.furnitureecommerce.service.interfaces;

import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.model.AppUser;

public interface AppUserService  {
	AppUser saveUsers(AppUser user);
	AppUser getUserByEmail(String email);
	
	ApiResponse verifyAccount(Long userId, String token);
}
