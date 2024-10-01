package mx.com.tvch.migracion.repository.tvch;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.tvch.PuestoEntity;

@Repository
public interface PuestoRepository extends CrudRepository<PuestoEntity, Long> {
	
	public Optional<PuestoEntity> findByNombre(String name);

}
