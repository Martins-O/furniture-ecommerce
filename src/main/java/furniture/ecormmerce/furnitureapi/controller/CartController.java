package furniture.ecormmerce.furnitureapi.controller;

import furniture.ecormmerce.furnitureapi.data.dto.request.CartItemsDto;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.service.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart/")
public class CartController {
	private final CartService service;
	
	@PostMapping
	public ResponseEntity<ApiResponse> order(@RequestBody CartItemsDto request){
		var response = service.createOrder (request);
		return new ResponseEntity<> (response, HttpStatus.OK);
	}
}
