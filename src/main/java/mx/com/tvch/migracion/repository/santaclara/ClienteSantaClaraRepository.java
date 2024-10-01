package mx.com.tvch.migracion.repository.santaclara;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.santaclara.ClienteSantaClaraEntity;

@Repository
public interface ClienteSantaClaraRepository extends CrudRepository<ClienteSantaClaraEntity, Long> {

}
