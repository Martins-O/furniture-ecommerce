package furniture.ecormmerce.furnitureapi.controller.auth;

import furniture.ecormmerce.furnitureapi.data.dto.request.LoginResponse;
import furniture.ecormmerce.furnitureapi.data.dto.request.RegisterRequest;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.dto.response.TokenResponse;
import furniture.ecormmerce.furnitureapi.service.interfaces.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth/")
@Slf4j
//@CrossOrigin(origins = "*")
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
