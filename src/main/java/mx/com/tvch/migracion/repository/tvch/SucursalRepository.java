package mx.com.tvch.migracion.repository.tvch;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.tvch.migracion.entity.tvch.SucursalEntity;
import mx.com.tvch.migracion.entity.tvch.ZonaEntity;

@Repository
public interface SucursalRepository extends CrudRepository<SucursalEntity, Long>{
	
	public Optional<SucursalEntity> findByNombre(String name);

	@Query("from SucursalEntity s where s.estatus = :estatus")
	public Iterable<SucursalEntity> findByEstatus(@Param("estatus") Integer estatus);
	
	@Query("from SucursalEntity s where s.zona = :zona")
	public Iterable<SucursalEntity> findByZona(@Param("zona") ZonaEntity zona);
	
	@Query("from SucursalEntity s where s.estatus = :estatus and s.zona = :zona")
	public Iterable<SucursalEntity> findByEstatusAndZona(@Param("estatus") Integer estatus, @Param("zona") ZonaEntity zona);
	
}
