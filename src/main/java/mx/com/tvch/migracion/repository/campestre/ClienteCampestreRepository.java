package mx.com.tvch.migracion.repository.campestre;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.campestre.ClienteCampestreEntity;

@Repository
public interface ClienteCampestreRepository extends CrudRepository<ClienteCampestreEntity, Long> {

}
