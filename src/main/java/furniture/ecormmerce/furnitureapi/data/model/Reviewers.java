package furniture.ecommerce.furnitureecommerce.data.model;


import furniture.ecommerce.furnitureecommerce.data.enums.Rating;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "reviewers")
public class Reviewers {
	@Id
	@SequenceGenerator(
			name = "id",
			sequenceName = "review_id"
	)
	@GeneratedValue(
			strategy = GenerationType.IDENTITY,
			generator = "review_id"
	)
	private Long id;
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private AppUser reviewerDetails;
	private String name;
	private String email;
	private String reviews;
	@Enumerated(EnumType.STRING)
	private Rating starRating;
}
