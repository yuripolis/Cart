package oneTomany.demo.repository;

import oneTomany.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cart extends JpaRepository<Cart, Long > {
}
