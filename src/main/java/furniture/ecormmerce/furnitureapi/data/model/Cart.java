package furniture.ecormmerce.furnitureapi.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Cart {
	@OneToMany(cascade = CascadeType.ALL)
	private List<CartItem> cartItems;

}
