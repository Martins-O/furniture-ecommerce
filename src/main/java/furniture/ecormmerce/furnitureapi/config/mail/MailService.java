package furniture.ecommerce.furnitureecommerce.config.mail;


import furniture.ecommerce.furnitureecommerce.data.dto.request.EmailNotificationRequest;

public interface MailService {
	
	String sendHtmlMail(EmailNotificationRequest emailNotificationRequest);
}
