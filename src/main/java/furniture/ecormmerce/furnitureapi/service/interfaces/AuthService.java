package furniture.ecommerce.furnitureecommerce.service.interfaces;

import furniture.ecommerce.furnitureecommerce.data.dto.request.LoginResponse;
import furniture.ecommerce.furnitureecommerce.data.dto.request.RegisterRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.dto.response.TokenResponse;

public interface AuthService {
	
	ApiResponse signUp(RegisterRequest request);
	TokenResponse signIn(LoginResponse response);
}
