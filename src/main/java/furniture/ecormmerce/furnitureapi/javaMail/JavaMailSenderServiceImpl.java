package furniture.ecormmerce.furnitureapi.javaMail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JavaMailSenderServiceImpl implements JavaMailSenderService{
	private final JavaMailSender mailSender;
	@Override
	public String sendMail(MailRequest request) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage ();
		MimeMessageHelper helper = new MimeMessageHelper (message);
		helper.setTo (request.getTo ());
		helper.setSubject (request.getSubject ());
		helper.setFrom (request.getFrom ());
		helper.setText (request.getMessage (), true);
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setTo (request.getTo ());
//		mailMessage.setSubject (request.getSubject ());
//		mailMessage.setText (request.getMessage ());
		
		mailSender.send (message);
		return "Send Message";
	}
}
