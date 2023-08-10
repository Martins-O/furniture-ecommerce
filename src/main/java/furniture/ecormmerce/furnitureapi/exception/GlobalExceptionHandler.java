package furniture.ecormmerce.furnitureapi.exception;

import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static furniture.ecormmerce.furnitureapi.common.Messages.EMAIL_ALREADY_EXIST;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ApiResponse handleUserNotFoundException(HttpServletRequest request,
	                                        HttpServletResponse response){
		return ApiResponse.builder()
				.message ("User not found")
				.statusCode(response.SC_NOT_FOUND)
				.isSuccessful (false)
				.build();
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ApiResponse handleUserAlreadyExistException(HttpServletRequest request,
	                                        HttpServletResponse response){
		return ApiResponse.builder()
				.data (request.getMethod ())
				.message (EMAIL_ALREADY_EXIST)
				.statusCode(response.SC_FORBIDDEN)
				.isSuccessful (false)
				.build();
	}
	
	@ExceptionHandler(ImageUploadException.class)
	public ApiResponse handleImageUploadException(HttpServletRequest request,
	                                        HttpServletResponse response){
		return ApiResponse.builder()
				.data (request.getMethod ())
				.message ("Image upload Failed")
				.statusCode(response.SC_FORBIDDEN)
				.isSuccessful (false)
				.build();
	}
	@ExceptionHandler(LoginFailureException.class)
	public ApiResponse handleLoginFailureException(HttpServletRequest request,
	                                        HttpServletResponse response){
		return ApiResponse.builder()
				.data (request.getMethod ())
				.message ("Invalid login details")
				.statusCode(response.SC_BAD_GATEWAY)
				.isSuccessful (false)
				.build();
	}
	
	
}
