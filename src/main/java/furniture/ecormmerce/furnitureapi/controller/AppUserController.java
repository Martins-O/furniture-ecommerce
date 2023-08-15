package furniture.ecormmerce.furnitureapi.controller;

import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.AppUser;
import furniture.ecormmerce.furnitureapi.data.model.Product;
import furniture.ecormmerce.furnitureapi.service.interfaces.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users/")
public class AppUserController {
	
	private final AppUserService service;
	
	@PostMapping("verify/{userId}/{token}")
	public ResponseEntity<ApiResponse> verify(@PathVariable Long userId, @PathVariable String token){
		return new ResponseEntity<>(service.verifyAccount (userId, token),
				HttpStatus.OK);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<AppUser> getUser(@PathVariable("email") String email){
		return new ResponseEntity<>(service.getUserByEmail (email),
				HttpStatus.OK);
	}
	
	@GetMapping("{userId}")
	public ResponseEntity<AppUser> getUserById(@PathVariable("userId") Long userId){
		return new ResponseEntity<>(service.getUserById (userId),
				HttpStatus.OK);
	}
	@GetMapping()
	public ResponseEntity<List<AppUser>> getUser(){
		return new ResponseEntity<>(service.getAllUsers (),
				HttpStatus.OK);
	}
	
	@GetMapping("name/{firstName}")
	public ResponseEntity<AppUser> getUserByFirstName(@PathVariable("firstName") String firstName) {
		return new ResponseEntity<>(service.getUserByFirstName (firstName),
				HttpStatus.OK);
	}
	
	@GetMapping("page/{pageNumber}")
	public ResponseEntity<Page<AppUser>> getAppUserByPage(@PathVariable("pageNumber") int pageNumber){
		return new ResponseEntity<>(service.getAllUserByPage (pageNumber), HttpStatus.OK);
	}
	
	@DeleteMapping("id/{id}")
	public void deleteById(@PathVariable Long id){
		service.deleteUserById (id);
	}
	
	@DeleteMapping("name/{firstName}")
	public void deleteByName(@PathVariable String firstName){
		service.deleteByFirstName (firstName);
	}
	@DeleteMapping("email/{email}")
	public void deleteByEmail(@PathVariable String email){
		service.deleteByEmail (email);
	}
	
	@DeleteMapping()
	public void delete(){
		service.deleteAllUser ();
	}
}
