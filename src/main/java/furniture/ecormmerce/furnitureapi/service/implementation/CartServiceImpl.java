package furniture.ecormmerce.furnitureapi.service.implementation;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import furniture.ecormmerce.furnitureapi.data.dto.request.CartItemsDto;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.AppUser;
import furniture.ecormmerce.furnitureapi.data.model.Cart;
import furniture.ecormmerce.furnitureapi.data.model.CartItem;
import furniture.ecormmerce.furnitureapi.data.model.Product;
import furniture.ecormmerce.furnitureapi.exception.FurnitureException;
import furniture.ecormmerce.furnitureapi.service.interfaces.AppUserService;
import furniture.ecormmerce.furnitureapi.service.interfaces.CartService;
import furniture.ecormmerce.furnitureapi.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
	private final ProductService service;
	private final AppUserService userService;
	
	@Value("${paystack.api.key}")
	private String paystack;
	


	public ApiResponse createOrder(CartItemsDto items) {
		Cart order = new Cart ();
		CartItem cartItem = new CartItem();
		List<CartItem> cartItems = new ArrayList<>();
		BigDecimal totalPrice = BigDecimal.ZERO;

		var foundUser = findUserByEmail (items.getUserOrder());

		for(CartItemsDto.Items item : items.getItems ()) {
			var foundProduct = findProductById (item.getProductId());
			
			BigDecimal itemTotalPrice = foundProduct.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
			totalPrice = totalPrice.add(itemTotalPrice);

			cartItem.setProducts (foundProduct);
			cartItem.setQuantity (item.getQuantity ());
			cartItem.setPrice (foundProduct.getPrice());
			cartItem.setUserOrder (foundUser);
			cartItems.add(cartItem);
		}
		order.setCartItems(cartItems);
		String payment = initiatePayment (totalPrice, foundUser.getEmail ());
		return ApiResponse.builder()
				.data (payment)
				.isSuccessful (true)
				.message ("DONE")
				.httpStatus (HttpStatus.OK)
				.statusCode (200)
				.build();
	}
	
	private String initiatePayment(BigDecimal totalPrice, String email) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = getHttpPost (totalPrice, email);
		
		try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String responseString = EntityUtils.toString(entity);
				JsonObject jsonResponse = JsonParser.parseString(responseString).getAsJsonObject();
				if (jsonResponse.has("data")) {
					JsonObject data = jsonResponse.getAsJsonObject("data");
					if (data.has("reference")) {
						return data.get("reference").getAsString();
					}
				}
			}
		} catch (IOException e) {
			throw new FurnitureException ("Failed", HttpStatus.BAD_REQUEST);
			
		}
		
		return null;
	}
	
	@NotNull
	private HttpPost getHttpPost(BigDecimal totalPrice, String email) {
		HttpPost httpPost = new HttpPost("https://api.paystack.co/transaction/initialize");
		
		
		httpPost.addHeader("Authorization", "Bearer "+ paystack);
		httpPost.addHeader("Content-Type", "application/json");
		
	
		JsonObject requestBody = new JsonObject();
		requestBody.addProperty("amount", totalPrice.multiply(BigDecimal.valueOf(100)).intValue());
		requestBody.addProperty("email", email);
		
		httpPost.setEntity(new StringEntity (requestBody.toString(), ContentType.APPLICATION_JSON));
		return httpPost;
	}
	
	
	private Product findProductById(long productId) {
		return service.getProductById(productId);
	}
	
	private AppUser findUserById(long userId) {
		return userService.getUserById(userId);
	}
	private Product findUserByName(String name) {
		return service.getProductByName (name);
	}
	private AppUser findUserByEmail(String email) {
		return userService.getUserByEmail (email);
	}
	
	
}
