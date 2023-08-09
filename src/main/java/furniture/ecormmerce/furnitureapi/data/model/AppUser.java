package furniture.ecommerce.furnitureecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import furniture.ecommerce.furnitureecommerce.data.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "app_users")
public class AppUser {
	@JsonIgnore
	@Id
	@SequenceGenerator(
			name = "id",
			sequenceName = "user_id"
	)
	@GeneratedValue(
			strategy = GenerationType.IDENTITY,
			generator = "user_id"
	)
	private Long id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String password;
	private String confirmPassword;
	private String phoneNumber;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Address addressDetails;
	@Enumerated(EnumType.STRING)
	private List<Role> roles;
	private boolean enabled;
	@CreatedDate
	private LocalDateTime createdAt;
	private String discountPercent;
	private boolean voucherEnabled;
}
