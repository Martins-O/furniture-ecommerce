package furniture.ecommerce.furnitureecommerce.service.implementation;

import furniture.ecommerce.furnitureecommerce.data.dto.request.ReviewRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.model.Reviewers;
import furniture.ecommerce.furnitureecommerce.data.repository.ReviewersRepository;
import furniture.ecommerce.furnitureecommerce.service.interfaces.ReviewersService;
import furniture.ecommerce.furnitureecommerce.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static furniture.ecommerce.furnitureecommerce.utils.ApplicationUtilities.NUMBER_OF_ITEMS_PER_PAGE;

@Service
@RequiredArgsConstructor
public class ReviewerServiceImpl implements ReviewersService {
	
	private final ReviewersRepository repository;
	@Override
	public ApiResponse createReviews(ReviewRequest request) {
		Reviewers createReview = Reviewers.builder()
				.reviews (request.getReviews ())
				.name (request.getName ())
				.starRating (request.getStarRating ())
				.build();
		var response = repository.save (createReview);
		return Responses.createdResponse (response);
	}
	
	@Override
	public List<Reviewers> getReviewers(){
		return repository.findAll ();
	}
	
	@Override
	public Page<Reviewers> getAllReviewers(int pageNumber){
		if (pageNumber < 1) {
			pageNumber = 0;
		}
		else pageNumber = pageNumber-1;
		Pageable pageable = PageRequest.of (
				pageNumber,
				NUMBER_OF_ITEMS_PER_PAGE
		);
		return repository.findAll (pageable);
	}
}
