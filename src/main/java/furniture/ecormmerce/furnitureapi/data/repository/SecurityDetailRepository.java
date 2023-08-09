package furniture.ecommerce.furnitureecommerce.data.repository;


import furniture.ecommerce.furnitureecommerce.data.model.SecurityDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SecurityDetailRepository extends JpaRepository<SecurityDetail, Long> {
    List<SecurityDetail> findSecurityDetailByUser_Id(Long userId);
    Optional<SecurityDetail> findSecurityDetailsByToken(String token);
}
