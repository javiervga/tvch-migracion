package mx.com.tvch.migracion.repository.sahagun;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.sahagun.ClienteSahagunEntity;

@Repository
public interface ClienteSahagunRepository extends CrudRepository<ClienteSahagunEntity, Long> {

}
