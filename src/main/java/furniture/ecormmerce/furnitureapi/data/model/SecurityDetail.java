package furniture.ecommerce.furnitureecommerce.data.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SecurityDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne
    private AppUser user;
    private boolean isRevoked;
    private boolean isExpired;
}
