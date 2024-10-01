package mx.com.tvch.migracion.repository.omitlan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.omitlan.ClienteOmitlanEntity;

@Repository
public interface ClienteOmitlanRepository extends CrudRepository<ClienteOmitlanEntity, Long> {

}
