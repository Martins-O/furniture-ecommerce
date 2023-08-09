package furniture.ecommerce.furnitureecommerce.exception;

import org.springframework.http.HttpStatus;

public class FurnitureException extends RuntimeException {
	public FurnitureException(String message, HttpStatus statusCode) {
		super (message);
	}
}
