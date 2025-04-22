package mx.com.tvch.migracion.repository.tvch;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import mx.com.tvch.migracion.entity.tvch.EstatusContratoEntity;

public interface EstatusContratoRepository extends CrudRepository<EstatusContratoEntity, Long>{
	
	Optional<EstatusContratoEntity> findByDescripcion(String descripcion);

}
