package furniture.ecormmerce.furnitureapi.data.repository;

import furniture.ecormmerce.furnitureapi.data.model.AppUser;
import furniture.ecormmerce.furnitureapi.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByEmail(String email);
	
	AppUser findAppUserByFirstName(String firstName);
	
	void deleteUserById(Long id);
	
	void deleteByFirstName(String firstName);
	
	void deleteByEmail(String email);
}
