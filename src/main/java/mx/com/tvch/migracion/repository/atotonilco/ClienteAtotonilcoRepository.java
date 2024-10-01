package mx.com.tvch.migracion.repository.atotonilco;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.atotonilco.ClienteAtotonilcoEntity;

@Repository
public interface ClienteAtotonilcoRepository extends CrudRepository<ClienteAtotonilcoEntity, Long> {

}
