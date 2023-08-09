package furniture.ecommerce.furnitureecommerce.controller;

import furniture.ecommerce.furnitureecommerce.data.dto.request.ProductRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.request.UpdateProductRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.model.Product;
import furniture.ecommerce.furnitureecommerce.service.interfaces.ProductService;
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
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Long productId, @RequestBody UpdateProductRequest request){
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
