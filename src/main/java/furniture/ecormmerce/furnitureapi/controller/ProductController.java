package furniture.ecormmerce.furnitureapi.controller;

import furniture.ecormmerce.furnitureapi.data.dto.request.ProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.request.UpdateProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.Product;
import furniture.ecormmerce.furnitureapi.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products/")
public class ProductController {
	
	private final ProductService service;
	
	@PostMapping()
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequest request){
		var response = service.createProduct (request);
		return new ResponseEntity<> (response, HttpStatus.CREATED);
	}
	
	@PutMapping("{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Long productId,
	                                                 @RequestBody UpdateProductRequest request){
		var response = service.updateProduct (productId, request);
		return new ResponseEntity<> (response,
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getProducts(){
		return new ResponseEntity<>(service.getAllProducts (), HttpStatus.OK);
	}
	
	@GetMapping("{name}")
	public ResponseEntity<Product> getProductByName(@PathVariable String name){
		return new ResponseEntity<>(service.getProductByName (name),
				HttpStatus.OK);
	}
	
	@GetMapping("{price}")
	public ResponseEntity<Product> getProductByPrice(@PathVariable BigDecimal price){
		return new ResponseEntity<>(service.getProductByPrice (price),
				HttpStatus.OK);
	}
	
	
}
