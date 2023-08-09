package furniture.ecommerce.furnitureecommerce.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends FurnitureException {
	public UserNotFoundException(String message, HttpStatus statusCode) {
		super (message, statusCode);
	}
}
