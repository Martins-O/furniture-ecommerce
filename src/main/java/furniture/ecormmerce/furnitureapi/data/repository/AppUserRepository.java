package furniture.ecormmerce.furnitureapi.data.repository;

import furniture.ecormmerce.furnitureapi.data.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByEmail(String email);
}
