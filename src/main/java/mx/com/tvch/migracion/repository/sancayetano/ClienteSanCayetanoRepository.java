package mx.com.tvch.migracion.repository.sancayetano;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.sancayetano.ClienteSanCayetanoEntity;

@Repository
public interface ClienteSanCayetanoRepository extends CrudRepository<ClienteSanCayetanoEntity, Long> {

}
