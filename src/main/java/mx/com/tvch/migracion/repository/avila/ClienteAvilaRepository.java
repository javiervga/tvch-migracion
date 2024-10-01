package mx.com.tvch.migracion.repository.avila;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.avila.ClienteAvilaEntity;

@Repository
public interface ClienteAvilaRepository extends CrudRepository<ClienteAvilaEntity, Long> {

}
