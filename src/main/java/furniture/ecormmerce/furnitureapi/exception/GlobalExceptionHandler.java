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
				.statusCode(HttpServletResponse.SC_NOT_FOUND)
				.isSuccessful (false)
				.build();
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ApiResponse handleUserAlreadyExistException(HttpServletRequest request,
	                                        HttpServletResponse response){
		return ApiResponse.builder()
				.message (EMAIL_ALREADY_EXIST)
				.statusCode(HttpServletResponse.SC_FORBIDDEN)
				.isSuccessful (false)
				.build();
	}
	
	
}
