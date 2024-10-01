package mx.com.tvch.migracion.repository.oncejulio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.oncejulio.ClienteOnceJulioEntity;

@Repository
public interface ClienteOnceJulioRepository extends CrudRepository<ClienteOnceJulioEntity, Long> {

}
