package furniture.ecormmerce.furnitureapi.data.dto.request;

import furniture.ecormmerce.furnitureapi.data.enums.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	@NotNull(message = "price field is required")
	private BigDecimal price;
	@NotNull(message = "Please provide more information about the product")
	private String description;
	@NotNull(message = "Please name the product")
	private String name;
	private Type sizeType;
	private Color colorType;
	private MultipartFile pictures;
	private int quantity;
	private Rating rateProduct;
	private Category category;
	private Material material;
}
