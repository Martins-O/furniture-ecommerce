package furniture.ecormmerce.furnitureapi.controller;

import furniture.ecormmerce.furnitureapi.data.dto.request.ReviewRequest;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.Reviewers;
import furniture.ecormmerce.furnitureapi.service.interfaces.ReviewersService;
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
