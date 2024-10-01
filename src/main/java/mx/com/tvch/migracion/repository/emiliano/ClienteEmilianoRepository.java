package mx.com.tvch.migracion.repository.emiliano;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.emiliano.ClienteEmilianoEntity;

@Repository
public interface ClienteEmilianoRepository extends CrudRepository<ClienteEmilianoEntity, Long> {

}
