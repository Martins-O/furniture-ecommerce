package furniture.ecommerce.furnitureecommerce.exception;

import org.springframework.http.HttpStatus;

public class ImageUploadException extends FurnitureException{
	public ImageUploadException(String message, HttpStatus statusCode) {
		super (message, statusCode);
	}
}
