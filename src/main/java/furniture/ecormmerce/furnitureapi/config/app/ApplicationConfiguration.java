package furniture.ecommerce.furnitureecommerce.config.app;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import furniture.ecommerce.furnitureecommerce.config.mail.MailConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {
	
	
	@Value("${cloudinary.api.secret}")
	private String apiSecret;
	@Value("${cloudinary.api.key}")
	private String apiKey;
	@Value("${cloudinary.cloud.name}")
	private String cloudName;
	
	@Value("${mail.api.key}")
	private String mailApiKey;
	@Value("${sendinblue.mail.url}")
	private String mailUrl;
 
	
	
	
	
	@Bean
	public MailConfiguration mailConfig(){
		return new MailConfiguration (mailApiKey, mailUrl);
	}
	
	@Bean
	public Cloudinary cloudinary(){
		return new Cloudinary(
				ObjectUtils.asMap(
						"cloud_name",cloudName,
						"api_key",apiKey,
						"api_secret",apiSecret
				)
		);
	}
	

	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder ();
	}
}
