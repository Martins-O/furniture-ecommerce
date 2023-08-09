package furniture.ecommerce.furnitureecommerce.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "carts")
public class Cart {
	@Id
	@SequenceGenerator(
			name = "id",
			sequenceName = "carts_id"
	)
	@GeneratedValue(
			strategy = GenerationType.IDENTITY,
			generator = "carts_id"
	)
	private Long Id;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartItem> cartItems;

}
