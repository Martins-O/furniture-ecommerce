package furniture.ecormmerce.furnitureapi.data.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenResponse {
	private String token;
	private String refreshToken;
}
