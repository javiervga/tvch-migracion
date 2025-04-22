package mx.com.tvch.migracion.repository.tvch;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import mx.com.tvch.migracion.entity.tvch.SucursalEntity;
import mx.com.tvch.migracion.entity.tvch.SuscriptorEntity;

public interface SuscriptorRepository extends CrudRepository<SuscriptorEntity, Long>{
	
	@Query("from SuscriptorEntity s where s.sucursal = :sucursal")
	public Iterable<SuscriptorEntity> findBySucursal(@Param("sucursal") SucursalEntity sucursal);

}
