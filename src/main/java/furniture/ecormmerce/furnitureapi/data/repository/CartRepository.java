package furniture.ecormmerce.furnitureapi.data.repository;


import furniture.ecormmerce.furnitureapi.data.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
//	List<Cart> findCartByProducts(List<Product> products);
}
