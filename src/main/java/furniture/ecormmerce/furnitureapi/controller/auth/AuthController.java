package furniture.ecommerce.furnitureecommerce.controller.auth;

import furniture.ecommerce.furnitureecommerce.data.dto.request.LoginResponse;
import furniture.ecommerce.furnitureecommerce.data.dto.request.RegisterRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.dto.response.TokenResponse;
import furniture.ecommerce.furnitureecommerce.service.interfaces.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth/")
@CrossOrigin(origins = "*")
public class AuthController {
	
	private final AuthService service;
	
	@PostMapping()
	public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request){
		return new ResponseEntity<>(service.signUp (request),
				HttpStatus.CREATED);
	}
	
	@PostMapping("login")
	public ResponseEntity<TokenResponse> login(@RequestBody LoginResponse request){
		return new ResponseEntity<>(service.signIn (request),
				HttpStatus.OK);
	}
}
