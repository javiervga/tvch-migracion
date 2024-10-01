package mx.com.tvch.migracion.repository.tvch;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mx.com.tvch.migracion.entity.tvch.OficinaEntity;

public interface OficinaRepository extends CrudRepository<OficinaEntity, Long>{
	
	@Query("from OficinaEntity o where o.estatus = :estatus")
	public Iterable<OficinaEntity> findByEstatus(@Param("estatus") Integer estatus);
	
	public Optional<OficinaEntity> findByNombre(String nombre);

}
