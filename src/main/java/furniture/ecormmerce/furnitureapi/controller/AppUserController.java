package furniture.ecommerce.furnitureecommerce.controller;

import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.model.AppUser;
import furniture.ecommerce.furnitureecommerce.service.interfaces.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users/")
public class AppUserController {
	
	private final AppUserService service;
	
	@PostMapping("verify/{userId}")
	public ResponseEntity<ApiResponse> verify(@PathVariable Long userId, @RequestParam String token){
		return new ResponseEntity<>(service.verifyAccount (userId, token),
				HttpStatus.OK);
	}
	
	@GetMapping("{email}")
	public ResponseEntity<AppUser> getUser(String email){
		return new ResponseEntity<>(service.getUserByEmail (email),
				HttpStatus.OK);
	}
}
