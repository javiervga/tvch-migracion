package mx.com.tvch.migracion.repository.ahuazotepec;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.ahuazotepec.ClienteAhuazotepecEntity;

@Repository
public interface ClienteAhuazotepecRepository extends CrudRepository<ClienteAhuazotepecEntity, Long> {
	
}
