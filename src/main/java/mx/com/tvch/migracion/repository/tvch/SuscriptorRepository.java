package mx.com.tvch.migracion.repository.tvch;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import mx.com.tvch.migracion.entity.tvch.SucursalEntity;
import mx.com.tvch.migracion.entity.tvch.SuscriptorEntity;

public interface SuscriptorRepository extends CrudRepository<SuscriptorEntity, Long>{
	
	@Query("from SuscriptorEntity s where s.sucursal = :sucursal")
	public Iterable<SuscriptorEntity> findBySucursal(SucursalEntity sucursalEntity);

}
