package furniture.ecormmerce.furnitureapi.data.dto.request;

import furniture.ecormmerce.furnitureapi.data.enums.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
	private String name;
	private String email;
	private String reviews;
	private Rating starRating;
}
