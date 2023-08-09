package furniture.ecommerce.furnitureecommerce.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "carts_item")
public class CartItem {
	
	@Id
	@SequenceGenerator(
			name = "id",
			sequenceName = "carts_item_id"
	)
	@GeneratedValue(
			strategy = GenerationType.IDENTITY,
			generator = "carts_item_id"
	)
	private Long Id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinTable(
			name = "orders"
	)
	private AppUser userOrder;
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinTable(
			name = "product"
	)
	private List<Product> products;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	private int quantity;
	private BigDecimal price;
}
