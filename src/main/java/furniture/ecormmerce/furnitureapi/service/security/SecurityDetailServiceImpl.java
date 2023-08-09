package furniture.ecormmerce.furnitureapi.service.security;

import furniture.ecormmerce.furnitureapi.data.model.SecurityDetail;
import furniture.ecormmerce.furnitureapi.data.repository.SecurityDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityDetailServiceImpl implements SecurityDetailService{
    private final SecurityDetailRepository securityDetailRepository;
    public SecurityDetailServiceImpl(SecurityDetailRepository securityDetailRepository){
        this.securityDetailRepository = securityDetailRepository;
    }
    @Override
    public SecurityDetail findSecurityDetailByToken(String token) {
        return securityDetailRepository
                .findSecurityDetailsByToken(token)
                .orElse(null);
    }

    @Override
    public List<SecurityDetail> findSecurityDetailByUserId(Long userId) {
        return securityDetailRepository
                .findSecurityDetailByUser_Id(userId);
    }

    @Override
    public SecurityDetail save(SecurityDetail securityDetail) {
        return securityDetailRepository.save(securityDetail);
    }

    @Override
    public void deleteAllSecurityDetail() {
        securityDetailRepository.deleteAll();
    }
}
