package pl.tacocloud.tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tacocloud.tacos.Taco;

@Repository
public interface TacoRepository extends JpaRepository<Taco,Long> {
}
