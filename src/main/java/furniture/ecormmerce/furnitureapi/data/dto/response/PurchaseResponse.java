package furniture.ecommerce.furnitureecommerce.data.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseResponse {
	private Object data;
	private HttpStatus httpStatus;
	private int statusCode;
	private boolean isSuccessful;
	private String message;
}
