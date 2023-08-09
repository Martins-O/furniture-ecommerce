package furniture.ecommerce.furnitureecommerce.utils;

import furniture.ecommerce.furnitureecommerce.config.security.jwt.JwtGenerator;
import furniture.ecommerce.furnitureecommerce.data.dto.request.EmailNotificationRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.request.Recipient;
import furniture.ecommerce.furnitureecommerce.exception.FurnitureException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class ApplicationUtilities {
	
	public static final String EMAIL_REGEX_STRING="^[A-Za-z0-9+_.-]+@(.+)$";
	private static final String USER_VERIFICATION_BASE_URL="localhost:9090/api/v1/users/verify";
	public static  final String WELCOME_MAIL_TEMPLATE_LOCATION="/home/martins-o/IdeaProjects/furnitureEcommerce/src/main/resources/templates/welcome.txt";
	public static final int NUMBER_OF_ITEMS_PER_PAGE = 10;
	public static String getMailTemplate(){
		try (BufferedReader reader = new BufferedReader(new FileReader (
				WELCOME_MAIL_TEMPLATE_LOCATION))){
			return reader.lines().collect(Collectors.joining());
		}catch (IOException exception){
			throw new FurnitureException (exception.getMessage(), HttpStatus.NO_CONTENT);
		}
	}
	
	public static String generateVerificationToken(Long id) {
		return USER_VERIFICATION_BASE_URL+"?userId="+id+"&token="+ JwtGenerator.generateVerificationToken ();
	}
	public static EmailNotificationRequest buildNotificationRequest(
			String email,
			String fullName,
			Long id) {
		EmailNotificationRequest request =
				new EmailNotificationRequest();
		request.getTo().add(new Recipient (fullName, email));
		String template = getMailTemplate();
		String content = String.format (template, fullName, generateVerificationToken(id));
		request.setHtmlContent (content);
		return request;
	}
	
	public static boolean isValidToken(String token){
		return Jwts.parser()
				.isSigned(token);
	}
}
