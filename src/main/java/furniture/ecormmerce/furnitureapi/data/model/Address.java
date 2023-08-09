package furniture.ecommerce.furnitureecommerce.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "address")
public class Address {
	@Id
	@SequenceGenerator(
			name = "id",
			sequenceName = "address_id"
	)
	@GeneratedValue(
			strategy = GenerationType.IDENTITY,
			generator = "address_id"
	)
	private Long id;
	private String country;
	private String city;
	private String state;
	private String zipCode;
	private String houseDetails;
}
