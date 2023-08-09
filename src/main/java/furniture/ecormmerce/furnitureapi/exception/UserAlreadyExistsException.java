package furniture.ecommerce.furnitureecommerce.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends FurnitureException {
	public UserAlreadyExistsException(String message, HttpStatus statusCode) {
		super (message, statusCode);
	}
}
