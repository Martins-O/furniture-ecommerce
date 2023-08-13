package furniture.ecormmerce.furnitureapi.controller;

import furniture.ecormmerce.furnitureapi.data.dto.request.ProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.request.UpdateProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.Product;
import furniture.ecormmerce.furnitureapi.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products/")
public class ProductController {
	
	private final ProductService service;
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse> createProduct(@ModelAttribute ProductRequest request){
		var response = service.createProduct (request);
		return new ResponseEntity<> (response, HttpStatus.CREATED);
	}
	
	@PutMapping( consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse> updateProduct(@ModelAttribute UpdateProductRequest request){
		var response = service.updateProduct (request);
		return new ResponseEntity<> (response,
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getProducts(){
		return new ResponseEntity<>(service.getAllProducts (), HttpStatus.OK);
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<Product> getProductByName(@PathVariable String name){
		return new ResponseEntity<>(service.getProductByName (name),
				HttpStatus.OK);
	}
	
	@GetMapping("price/{price}")
	public ResponseEntity<Product> getProductByPrice(@PathVariable BigDecimal price){
		return new ResponseEntity<>(service.getProductByPrice (price),
				HttpStatus.OK);
	}
	
	@GetMapping("{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
		return new ResponseEntity<>(service.getProductById (productId),
				HttpStatus.OK);
	}
	
	
}
