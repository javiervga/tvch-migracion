package mx.com.tvch.migracion.repository.old;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.com.tvch.migracion.entity.old.ClienteOldEntity;

@Repository
public interface ClienteOldRepository extends CrudRepository<ClienteOldEntity, Long> {
	
}
