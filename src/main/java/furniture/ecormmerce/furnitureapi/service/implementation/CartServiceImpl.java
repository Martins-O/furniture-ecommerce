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
	
	
	public ApiResponse createOrder(List<CartItemsDto> items) {
		Cart order = new Cart ();
		repository.save(order);
		
		BigDecimal totalPrice = BigDecimal.valueOf(0.0);
		
		for (CartItemsDto item : items) {
			Product product = service.getProductByName (item.getProducts().getName ());
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
