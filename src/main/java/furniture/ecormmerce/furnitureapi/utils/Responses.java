package furniture.ecommerce.furnitureecommerce.utils;

import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.dto.response.TokenResponse;
import org.springframework.http.HttpStatus;

import static furniture.ecommerce.furnitureecommerce.common.Messages.REG_FAIL;


public class Responses {
	
	public static ApiResponse createdResponse(Object data){
		return ApiResponse.builder()
				.statusCode(HttpStatus.CREATED.value())
				.isSuccessful(true)
				.httpStatus(HttpStatus.CREATED)
				.data(data)
				.build();
	}
	
	
	public static ApiResponse okResponse(Object data){
		return ApiResponse.builder()
				.statusCode(HttpStatus.OK.value())
				.isSuccessful(true)
				.httpStatus(HttpStatus.OK)
				.data(data)
				.build();
	}
	public static ApiResponse getFailureMessage() {
		return ApiResponse.builder()
				.message(REG_FAIL)
				.statusCode (HttpStatus.NO_CONTENT.value ())
				.isSuccessful (false)
				.build();
	}
	public static TokenResponse getLoginResponse(String token, String refreshToken) {
		return TokenResponse.builder()
				.token("Bearer "+ token)
				.refreshToken("Bearer "+ refreshToken)
				.build();
	}
}
