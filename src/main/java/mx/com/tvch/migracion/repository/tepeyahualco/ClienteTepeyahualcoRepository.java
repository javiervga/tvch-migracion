package mx.com.tvch.migracion.repository.tepeyahualco;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.tepeyahualco.ClienteTepeyahualcoEntity;

@Repository
public interface ClienteTepeyahualcoRepository extends CrudRepository<ClienteTepeyahualcoEntity, Long> {

}
