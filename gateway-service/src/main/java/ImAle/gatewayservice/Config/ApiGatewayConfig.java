package ImAle.gatewayservice.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

        @Bean
        public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
            return builder.routes()
                    .route("person-service", r -> r.path("/person/**")
                            .uri("lb://person-service"))
                    .route("car-service", r -> r.path("/car/**")
                            .uri("lb://car-service"))
                    .route("motorbike-service", r -> r.path("/motorbike/**")
                            .uri("lb://motorbike-service"))
                    .build();
        }

}
