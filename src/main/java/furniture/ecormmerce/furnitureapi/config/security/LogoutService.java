package furniture.ecormmerce.furnitureapi.config.security;

import furniture.ecormmerce.furnitureapi.data.model.SecurityDetail;
import furniture.ecormmerce.furnitureapi.service.security.SecurityDetailService;
import furniture.ecormmerce.furnitureapi.utils.EncryptionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
	
	private final SecurityDetailService detailService;
	private final EncryptionUtils encryptionUtils;
	@Override
	public void logout(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) {
		String authHeader = request.getHeader("Authorization");
		String jwt;
		
		if (authHeader == null || !authHeader.startsWith("Bearer "))return;
		
		jwt = authHeader.substring(7);
		
		SecurityDetail foundSecurityDetail = detailService.findSecurityDetailByToken (encryptionUtils.encrypt (jwt));
		if (foundSecurityDetail != null) {
			foundSecurityDetail.setExpired (true);
			foundSecurityDetail.setRevoked (true);
			
			detailService.save (foundSecurityDetail);
		}
	}
}
