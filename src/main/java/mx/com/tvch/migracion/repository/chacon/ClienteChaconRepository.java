package mx.com.tvch.migracion.repository.chacon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.chacon.ClienteChaconEntity;

@Repository
public interface ClienteChaconRepository extends CrudRepository<ClienteChaconEntity, Long> {

}
