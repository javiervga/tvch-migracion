package mx.com.tvch.migracion.repository.old;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import mx.com.tvch.migracion.entity.old.CostosOldEntity;

public interface CostosOldRepository extends CrudRepository<CostosOldEntity, Long>{
	
	Optional<CostosOldEntity> findByServicio(String servicio);

}
