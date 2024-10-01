package mx.com.tvch.migracion.repository.tvch;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.tvch.migracion.entity.tvch.AreaEntity;

@Repository
public interface AreaRepository extends CrudRepository<AreaEntity, Long>{
	
	public Optional<AreaEntity> findByNombre(String nombre);

	@Query("from AreaEntity a where a.estatus = :estatus")
	public Iterable<AreaEntity> findByEstatus(@Param("estatus") Integer estatus);
	
}
