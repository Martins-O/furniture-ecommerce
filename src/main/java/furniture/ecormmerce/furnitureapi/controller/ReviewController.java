package furniture.ecommerce.furnitureecommerce.controller;

import furniture.ecommerce.furnitureecommerce.data.dto.request.ReviewRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.model.Reviewers;
import furniture.ecommerce.furnitureecommerce.service.interfaces.ReviewersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reviews")
public class ReviewController {
	
	private final ReviewersService service;
	
	@PostMapping()
	private ResponseEntity<ApiResponse> createReviews(@RequestBody ReviewRequest request){
		return new ResponseEntity<> (service.createReviews (request),
				HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Page<Reviewers>> getAllReviewsByPage(int page){
		return new ResponseEntity<> (service.getAllReviewers (page),
				HttpStatus.OK);
	}
}
