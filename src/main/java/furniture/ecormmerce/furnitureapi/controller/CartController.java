package furniture.ecormmerce.furnitureapi.controller;

import furniture.ecormmerce.furnitureapi.data.dto.request.CartItemsDto;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.service.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart/")
public class CartController {
	private final CartService service;
	
	@PostMapping
	public ResponseEntity<ApiResponse> order(@ModelAttribute CartItemsDto request){
		var response = service.createOrder (request);
		return new ResponseEntity<> (response, HttpStatus.OK);
	}
}
