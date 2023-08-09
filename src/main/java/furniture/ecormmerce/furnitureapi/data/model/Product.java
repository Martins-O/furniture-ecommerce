package furniture.ecommerce.furnitureecommerce.data.model;

import furniture.ecommerce.furnitureecommerce.data.enums.Color;
import furniture.ecommerce.furnitureecommerce.data.enums.Rating;
import furniture.ecommerce.furnitureecommerce.data.enums.Type;
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
@Table(name = "products")
public class Product {
	@Id
	@SequenceGenerator(
			name = "id",
			sequenceName = "product_id"
	)
	@GeneratedValue(
			strategy = GenerationType.IDENTITY,
			generator = "product_id"
	)
	private Long id;
	private BigDecimal price;
	private String description;
	private String name;
	@Enumerated(EnumType.STRING)
	private Type sizeType;
	@Enumerated(EnumType.STRING)
	private List<Color> colorType;
	private String pictures;
	private Integer quantity;
	@Enumerated(EnumType.STRING)
	private Rating productRating;
}
