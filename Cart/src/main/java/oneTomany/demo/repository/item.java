package oneTomany.demo.repository;

import oneTomany.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface item extends JpaRepository<Item, Long> {
}
