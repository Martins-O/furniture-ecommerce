package furniture.ecommerce.furnitureecommerce.data.dto.request;

import furniture.ecommerce.furnitureecommerce.data.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CartItemsDto {
	
	private String userOrder;
	private Product products;
	private int quantity;
	private BigDecimal price;
	
}
