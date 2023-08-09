package furniture.ecormmerce.furnitureapi.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static furniture.ecormmerce.furnitureapi.utils.ApplicationUtilities.EMAIL_REGEX_STRING;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterRequest {
	@NotNull(message = "first name address is required")
	private String firstName;
	@NotNull(message = "Last name address is required")
	private String lastName;
	@NotNull(message = "Email address is required")
	@Email(regexp = EMAIL_REGEX_STRING, message = "please enter a valid email address")
	private String email;
	@Size(min = 6, max = 20)
	@NotNull(message = "Password address is required")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=])[A-Za-z\\d@#$%^&+=]{8,}$", message = "Enter a valid password")
	private String password;
	@NotNull(message = "Confirm Password address is required")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=])[A-Za-z\\d@#$%^&+=]{8,}$", message = "Enter a valid password")
	private String Confirmpassword;
	@NotNull(message = "Phone Number address is required")
	private String phoneNumber;
}
