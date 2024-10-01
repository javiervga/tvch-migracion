package mx.com.tvch.migracion.repository.veintenoviembre;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.veintenoviembre.ClienteVeinteNoviembreEntity;

@Repository
public interface ClienteVeinteNoviembreRepository extends CrudRepository<ClienteVeinteNoviembreEntity, Long> {

}
