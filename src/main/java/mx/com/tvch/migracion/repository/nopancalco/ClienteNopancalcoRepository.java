package mx.com.tvch.migracion.repository.nopancalco;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.nopancalco.ClienteNopancalcoEntity;

@Repository
public interface ClienteNopancalcoRepository extends CrudRepository<ClienteNopancalcoEntity, Long> {

}
