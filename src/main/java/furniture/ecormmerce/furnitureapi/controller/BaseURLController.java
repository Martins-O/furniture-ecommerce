package furniture.ecormmerce.furnitureapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BaseURLController {
	
	@GetMapping()
	public ResponseEntity<String> welcome(){
		String welcome = "Welcome, We are live now!!!, FURNITURE API";
		return new ResponseEntity<>(welcome, HttpStatus.OK);
	}
}
