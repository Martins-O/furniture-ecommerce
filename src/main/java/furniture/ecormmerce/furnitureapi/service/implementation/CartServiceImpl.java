package furniture.ecormmerce.furnitureapi.service.implementation;

import furniture.ecormmerce.furnitureapi.data.dto.request.CartItemsDto;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.AppUser;
import furniture.ecormmerce.furnitureapi.data.model.Cart;
import furniture.ecormmerce.furnitureapi.data.model.CartItem;
import furniture.ecormmerce.furnitureapi.data.model.Product;
import furniture.ecormmerce.furnitureapi.data.repository.CartItemRepository;
import furniture.ecormmerce.furnitureapi.data.repository.CartRepository;
import furniture.ecormmerce.furnitureapi.exception.FurnitureException;
import furniture.ecormmerce.furnitureapi.exception.UserNotFoundException;
import furniture.ecormmerce.furnitureapi.service.interfaces.AppUserService;
import furniture.ecormmerce.furnitureapi.service.interfaces.CartService;
import furniture.ecormmerce.furnitureapi.service.interfaces.ProductService;
import furniture.ecormmerce.furnitureapi.utils.Responses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static furniture.ecormmerce.furnitureapi.common.Messages.USER_NOT_FOUND;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
	private final CartRepository repository;
	private final ProductService service;
	private final AppUserService userService;
	private final CartItemRepository itemRepository;
	
	
	public ApiResponse createOrder(CartItemsDto items) {
		Cart order = new Cart ();
		CartItem cartItem = new CartItem();
		List<CartItem> cartItems = new ArrayList<>();
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		var foundUser = findUserById(items.getUserOrder());
		
		for(CartItemsDto.Items item : items.getItems ()) {
			var foundProduct = findProductById(item.getProductId());
			totalPrice = totalPrice.add(foundProduct.getPrice());
			
			cartItem.setProducts (foundProduct);
			cartItem.setQuantity (item.getQuantity ());
			cartItem.setPrice (foundProduct.getPrice());
			cartItem.setUserOrder (foundUser);
			cartItems.add(cartItem);
		}
		order.setCartItems(cartItems);
		repository.save(order);
		return null;
	}
	
	private Product findProductById(long productId) {
		return service.getProductById(productId);
	}
	
	private AppUser findUserById(long userId) {
		return userService.getUserById(userId);
	}
	
	@Override
	public void deleteCart(Cart cart) {
		repository.delete (cart);
	}
}
