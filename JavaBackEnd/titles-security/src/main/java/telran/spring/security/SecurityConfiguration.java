package telran.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {
	@Bean
	MapReactiveUserDetailsService getMapDetails() {
		
		UserDetails user1 = new User("poster", "{noop}poster", AuthorityUtils.createAuthorityList("ROLE_POSTER"));
		UserDetails user2 = new User("getter", "{noop}getter", AuthorityUtils.createAuthorityList("ROLE_GETTER"));
		
		UserDetails users[] = {user1,user2};
		return new MapReactiveUserDetailsService(users);
	}

	@Bean
	SecurityWebFilterChain securityFiltersChain(ServerHttpSecurity httpSecurity) {
		
		SecurityWebFilterChain securityFiltersChain = httpSecurity.csrf()
				.disable().httpBasic().and().authorizeExchange()
				.pathMatchers(HttpMethod.GET).hasRole("GETTER")
				.pathMatchers(HttpMethod.POST).hasRole("POSTER").and().build();
		
		return securityFiltersChain;
	}
}
