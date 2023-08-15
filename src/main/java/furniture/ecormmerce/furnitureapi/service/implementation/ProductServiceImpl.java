package furniture.ecormmerce.furnitureapi.service.implementation;

import furniture.ecormmerce.furnitureapi.cloud.CloudService;
import furniture.ecormmerce.furnitureapi.data.dto.request.ProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.request.UpdateProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.Product;
import furniture.ecormmerce.furnitureapi.data.repository.ProductRepository;
import furniture.ecormmerce.furnitureapi.exception.FurnitureException;
import furniture.ecormmerce.furnitureapi.service.interfaces.ProductService;
import furniture.ecormmerce.furnitureapi.utils.Responses;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static furniture.ecormmerce.furnitureapi.utils.ApplicationUtilities.NUMBER_OF_ITEMS_PER_PAGE;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository repository;
	private final CloudService service;
	@Override
	public ApiResponse createProduct(ProductRequest request) {
		var imageUrl = service.upload (request.getPictures ());
		Product addProduct = Product
				.builder()
				.price (request.getPrice())
				.description(request.getDescription())
				.material (request.getMaterial ())
				.category (request.getCategory())
				.colorType (List.of (request.getColorType()))
				.sizeType (request.getSizeType())
				.name (request.getName())
				.quantity (request.getQuantity())
				.pictures (imageUrl)
				.productRating (request.getRateProduct ())
				.build();
		var saved = repository.save(addProduct);
		return Responses.createdResponse (saved);
	}
	
	@Override
	public ApiResponse updateProduct(UpdateProductRequest request){
		Optional<Product> checkProduct = repository.findById (request.getProductId ());
		if (checkProduct.isEmpty ()) {
			throw new FurnitureException ("Product not found", HttpStatus.BAD_REQUEST);
		}
		Product updateProduct = checkProduct.get();
		updateProduct.setColorType (List.of (request.getColorType()));
		updateProduct.setName (request.getName());
		updateProduct.setDescription (request.getDescription());
		updateProduct.setPrice (request.getPrice().abs ());
		updateProduct.setSizeType (request.getSizeType());
		updateProduct.setQuantity (request.getQuantity());
		var savedProduct = repository.save(updateProduct);
		return Responses.okResponse (savedProduct);
	}
	
	@Override
	public List<Product> getAllProducts() {
		return repository.findAll ();
	}
	
	@Override
	public Product getProductByName (String name) {
		var response = repository.findProductByName (name);
		if (response == null) {
			throw new FurnitureException ("product not found", HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@Override
	public Product getProductByPrice(BigDecimal price) {
		return repository.findProductByPrice(price);
	}
	@Override
	public Page<Product> getAllProductByPage(int pageNumber){
		if (pageNumber < 1) {pageNumber = 0;}
		else pageNumber = pageNumber-1;
		Pageable pageable = PageRequest.of (pageNumber, NUMBER_OF_ITEMS_PER_PAGE);
		return repository.findAll (pageable);
	}
	
	@Override
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
	
	@Override
	public Product getProductById(Long id) {
		return repository.findProductById (id);
	}
	@Override
	@Transactional
	public void deleteProductById(Long id) {
		repository.deleteProductById (id);
	}
	@Override
	@Transactional
	public void deleteAllProducts() {repository.deleteAll ();}
	@Override
	@Transactional
	public void deleteByName(String name) {repository.deleteByName (name);}
}
