package mx.com.tvch.migracion.repository.tuxpan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.tuxpan.ClienteTuxpanEntity;

@Repository
public interface ClienteTuxpanRepository extends CrudRepository<ClienteTuxpanEntity, Long> {

}
