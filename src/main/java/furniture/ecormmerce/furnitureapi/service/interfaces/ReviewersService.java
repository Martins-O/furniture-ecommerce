package furniture.ecommerce.furnitureecommerce.service.interfaces;

import furniture.ecommerce.furnitureecommerce.data.dto.request.ReviewRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.model.Reviewers;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewersService {
	
	public ApiResponse createReviews(ReviewRequest request);
	
	List<Reviewers> getReviewers();
	
	Page<Reviewers> getAllReviewers(int pageNumber);
}
