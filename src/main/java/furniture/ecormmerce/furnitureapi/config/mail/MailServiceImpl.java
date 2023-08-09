package furniture.ecommerce.furnitureecommerce.config.mail;

import furniture.ecommerce.furnitureecommerce.data.dto.request.EmailNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
	private final MailConfiguration mailConfig;
	

	@Override
	public String sendHtmlMail(EmailNotificationRequest emailNotificationRequest) {
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("api-key", mailConfig.getApikey());
		HttpEntity<EmailNotificationRequest> requestHttpEntity = new HttpEntity<> (emailNotificationRequest, headers);
		
		ResponseEntity<String> response =
				template.postForEntity(mailConfig.getMailUrl(), requestHttpEntity, String.class);
        return response.getBody();
	}
}
