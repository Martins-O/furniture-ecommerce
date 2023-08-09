package furniture.ecommerce.furnitureecommerce.service.security;


import furniture.ecommerce.furnitureecommerce.data.model.SecurityDetail;

import java.util.List;

public interface SecurityDetailService {
    SecurityDetail findSecurityDetailByToken(String token);
    List<SecurityDetail> findSecurityDetailByUserId(Long userId);

    SecurityDetail save(SecurityDetail securityDetail);
    void deleteAllSecurityDetail();
}
