package furniture.ecommerce.furnitureecommerce.config.security.service;

import furniture.ecommerce.furnitureecommerce.config.security.user.SecureUser;
import furniture.ecommerce.furnitureecommerce.data.enums.Role;
import furniture.ecommerce.furnitureecommerce.data.model.AppUser;
import furniture.ecommerce.furnitureecommerce.data.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repository.findByEmail (username);
        if (user == null) {
            throw new UsernameNotFoundException("Not found");
        }
        return SecureUser.builder()
                .user(user)
                .roles (List.of (Role.CUSTOMER, Role.ADMIN))
                .build();
    }
}
