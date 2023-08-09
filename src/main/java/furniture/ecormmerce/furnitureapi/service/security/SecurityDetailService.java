package furniture.ecormmerce.furnitureapi.service.security;



import furniture.ecormmerce.furnitureapi.data.model.SecurityDetail;

import java.util.List;

public interface SecurityDetailService {
    SecurityDetail findSecurityDetailByToken(String token);
    List<SecurityDetail> findSecurityDetailByUserId(Long userId);

    SecurityDetail save(SecurityDetail securityDetail);
    void deleteAllSecurityDetail();
}
