package mx.com.tvch.migracion.repository.tvch;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import mx.com.tvch.migracion.entity.tvch.EstatusClienteEntity;

public interface EstatusClienteRepository extends CrudRepository<EstatusClienteEntity, Long>{
	
	Optional<EstatusClienteEntity> findByDescripcion(String descripcion);

}
