package mx.com.tvch.migracion.repository.chimalpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.chimalpa.ClienteChimalpaEntity;

@Repository
public interface ClienteChimalpaRepository extends CrudRepository<ClienteChimalpaEntity, Long> {

}
