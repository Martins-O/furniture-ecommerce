package furniture.ecormmerce.furnitureapi.data.dto.request;

import furniture.ecormmerce.furnitureapi.data.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CartItemsDto {
	private String userOrder;
	private List<Items> items;
	@Setter
	@Getter
	public static class Items{
		private long productId;
		private int quantity;
	}
}