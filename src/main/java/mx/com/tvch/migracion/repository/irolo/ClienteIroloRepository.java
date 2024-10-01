package mx.com.tvch.migracion.repository.irolo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.irolo.ClienteIroloEntity;

@Repository
public interface ClienteIroloRepository extends CrudRepository<ClienteIroloEntity, Long> {

}
