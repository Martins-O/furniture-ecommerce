package furniture.ecormmerce.furnitureapi.service.interfaces;


import furniture.ecormmerce.furnitureapi.data.dto.request.LoginResponse;
import furniture.ecormmerce.furnitureapi.data.dto.request.RegisterRequest;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.dto.response.TokenResponse;
import jakarta.mail.MessagingException;

public interface AuthService {
	
	ApiResponse signUp(RegisterRequest request) throws MessagingException;
	ApiResponse signIn(LoginResponse response);
}
