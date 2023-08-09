package furniture.ecommerce.furnitureecommerce.data.repository;

import furniture.ecommerce.furnitureecommerce.data.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByEmail(String email);
}
