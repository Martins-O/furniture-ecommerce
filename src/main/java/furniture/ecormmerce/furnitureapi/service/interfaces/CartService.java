package furniture.ecormmerce.furnitureapi.service.interfaces;


import furniture.ecormmerce.furnitureapi.data.dto.request.CartItemsDto;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.Cart;

public interface CartService {
	
	ApiResponse createOrder(CartItemsDto items);
	
	
}
