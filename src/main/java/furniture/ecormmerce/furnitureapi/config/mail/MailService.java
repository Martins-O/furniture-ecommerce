package furniture.ecormmerce.furnitureapi.config.mail;


import furniture.ecormmerce.furnitureapi.data.dto.request.EmailNotificationRequest;

public interface MailService {
	
	String sendHtmlMail(EmailNotificationRequest emailNotificationRequest);
}
