package furniture.ecormmerce.furnitureapi.service.interfaces;

import furniture.ecormmerce.furnitureapi.data.dto.request.ReviewRequest;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.Reviewers;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewersService {
	
	public ApiResponse createReviews(ReviewRequest request);
	
	List<Reviewers> getReviewers();
	
	Page<Reviewers> getAllReviewers(int pageNumber);
}
