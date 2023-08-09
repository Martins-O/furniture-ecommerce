package furniture.ecommerce.furnitureecommerce.exception;

import org.springframework.http.HttpStatus;

public class LoginFailureException extends FurnitureException {
	public LoginFailureException(String message, HttpStatus statusCode) {
		super (message, statusCode);
	}
}
