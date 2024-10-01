package mx.com.tvch.migracion.repository.real;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.real.ClienteRealEntity;

@Repository
public interface ClienteRealRepository extends CrudRepository<ClienteRealEntity, Long> {

}
