package furniture.ecommerce.furnitureecommerce.data.dto.request;

import furniture.ecommerce.furnitureecommerce.data.model.AppUser;
import furniture.ecommerce.furnitureecommerce.data.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartRequest {
	private List<AppUser> userOrder;
	private List<Product> products;
	private int quantity;
}
