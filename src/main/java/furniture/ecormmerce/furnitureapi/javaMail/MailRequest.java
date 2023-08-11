package furniture.ecormmerce.furnitureapi.javaMail;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest {
	
	private final String from = "noreply@furniture.com";
	private String to;
	private  String message;
	private final String subject = "Furniture App";
}
