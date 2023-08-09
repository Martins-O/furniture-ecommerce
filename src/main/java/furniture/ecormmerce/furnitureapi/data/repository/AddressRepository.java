package furniture.ecormmerce.furnitureapi.data.repository;


import furniture.ecormmerce.furnitureapi.data.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
