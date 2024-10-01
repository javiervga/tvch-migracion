package mx.com.tvch.migracion.repository.tepeapulco;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.tepeapulco.ClienteTepeapulcoEntity;

@Repository
public interface ClienteTepeapulcoRepository extends CrudRepository<ClienteTepeapulcoEntity, Long> {

}
