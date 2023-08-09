package furniture.ecormmerce.furnitureapi.service.interfaces;


import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.AppUser;

public interface AppUserService  {
	AppUser saveUsers(AppUser user);
	AppUser getUserByEmail(String email);
	
	ApiResponse verifyAccount(Long userId, String token);
}
