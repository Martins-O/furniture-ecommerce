package furniture.ecormmerce.furnitureapi.utils;

import furniture.ecormmerce.furnitureapi.data.dto.request.EmailNotificationRequest;
import furniture.ecormmerce.furnitureapi.data.dto.request.Recipient;
import furniture.ecormmerce.furnitureapi.exception.FurnitureException;
import furniture.ecormmerce.furnitureapi.javaMail.MailRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

public class ApplicationUtilities {
	
	
	public static final String EMAIL_REGEX_STRING="^[A-Za-z0-9+_.-]+@(.+)$";
	private static final String USER_VERIFICATION_BASE_URL="https://furniture-api-1jbq.onrender.com/api/v1/users/verify/";
//  http://localhost:8080/api/v1/users/verify/
	public static  final String WELCOME_MAIL_TEMPLATE_LOCATION="src/main/resources/templates/welcome.html";
	public static final int NUMBER_OF_ITEMS_PER_PAGE = 10;
	public static String getMailTemplate(){
		try (BufferedReader reader = new BufferedReader(new FileReader (
				WELCOME_MAIL_TEMPLATE_LOCATION))){
			return reader.lines().collect(Collectors.joining());
		}catch (IOException exception){
			throw new FurnitureException (exception.getMessage(), HttpStatus.NO_CONTENT);
		}
	}
	
	
	public static String generateVerificationTokenLogic() {
//		SecretKey key = Jwts.SIG.HS256.key().build();
		return Jwts.builder()
				.setIssuer("Furniture INC")
				.signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512))
				.setIssuedAt(new Date ())
				.compact();
	}
	
	public static String generateVerificationToken(Long id) {
		return USER_VERIFICATION_BASE_URL + id + "/" + generateVerificationTokenLogic ();
	}
	
	//	public static String generateVerificationToken(Long id) {
//		String token = generateVerificationTokenLogic ();
//
////		return "localhost:8080/api/v1/users/verify/{id}/{token}+ ";
////				?userId=" + id +"&token="+ generateVerificationTokenLogic ();
//	}
	public static EmailNotificationRequest buildNotificationRequest(
			String email,
			String fullName,
			Long id) {
		EmailNotificationRequest request =
				new EmailNotificationRequest();
		request.getTo().add(new Recipient (fullName, email));
		String template = getMailTemplate();
		String content = String.format (template, fullName, generateVerificationToken (id));
		request.setHtmlContent (content);
		return request;
	}
	
	public static MailRequest buildSendMessage(String email, String lastName, Long id) {
		MailRequest request = new MailRequest();
		request.setTo(email);
		String template = getMailTemplate();
		String link = generateVerificationToken(id);
		String content = String.format(template, lastName, link);
		request.setMessage(content);
		return request;
	}
	
	public static boolean isValidToken(String token){
		return Jwts.parserBuilder ()
				.build ()
				.isSigned(token);
	}
}
