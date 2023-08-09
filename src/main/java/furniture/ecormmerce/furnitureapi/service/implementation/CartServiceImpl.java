package furniture.ecommerce.furnitureecommerce.service.implementation;

import furniture.ecommerce.furnitureecommerce.data.dto.request.CartItemsDto;
import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.model.AppUser;
import furniture.ecommerce.furnitureecommerce.data.model.Cart;
import furniture.ecommerce.furnitureecommerce.data.model.CartItem;
import furniture.ecommerce.furnitureecommerce.data.model.Product;
import furniture.ecommerce.furnitureecommerce.data.repository.CartItemRepository;
import furniture.ecommerce.furnitureecommerce.data.repository.CartRepository;
import furniture.ecommerce.furnitureecommerce.exception.FurnitureException;
import furniture.ecommerce.furnitureecommerce.exception.UserNotFoundException;
import furniture.ecommerce.furnitureecommerce.service.interfaces.AppUserService;
import furniture.ecommerce.furnitureecommerce.service.interfaces.CartService;
import furniture.ecommerce.furnitureecommerce.service.interfaces.ProductService;
import furniture.ecommerce.furnitureecommerce.utils.Responses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static furniture.ecommerce.furnitureecommerce.common.Messages.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
	private final CartRepository repository;
	private final ProductService service;
	private final AppUserService userService;
	private final CartItemRepository itemRepository;
	
	
	public ApiResponse createOrder(List<CartItemsDto> items) {
		Cart order = new Cart ();
		repository.save(order);
		
		BigDecimal totalPrice = BigDecimal.valueOf(0.0);
		
		for (CartItemsDto item : items) {
			Product product = service.getProductByName (item.getProducts().toString ());
			if (product == null) {
				throw new FurnitureException ("Product not found", HttpStatus.NOT_FOUND);
			}
			if (product.getQuantity () < item.getQuantity ()){
				throw new FurnitureException ("Insufficient quantity", HttpStatus.BAD_REQUEST);
			}
			AppUser user = userService.getUserByEmail (item.getUserOrder ());
			if (user == null){
				throw new UserNotFoundException (USER_NOT_FOUND, HttpStatus.NOT_FOUND);
			}
			
			CartItem cartItem = CartItem.builder ()
					.userOrder (user)
					.products (List.of (product))
					.quantity (item.getQuantity ())
					.cart (order)
					.build ();
			itemRepository.save (cartItem);
			
			product.setQuantity (product.getQuantity () - item.getQuantity ());
			service.saveProduct (product);
			
			totalPrice = totalPrice.add (product.getPrice()).multiply (BigDecimal.valueOf (item.getQuantity ()));
		}
		return Responses.okResponse (totalPrice);
	}
	
	@Override
	public void deleteCart(Cart cart) {
		repository.delete (cart);
	}
}
