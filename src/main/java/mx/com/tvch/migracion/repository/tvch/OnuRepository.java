package mx.com.tvch.migracion.repository.tvch;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.tvch.migracion.entity.tvch.EstatusOnuEntity;
import mx.com.tvch.migracion.entity.tvch.OnuEntity;
import mx.com.tvch.migracion.entity.tvch.SucursalEntity;


@Repository
public interface OnuRepository extends CrudRepository<OnuEntity, Long> {
	
	@Query("from OnuEntity o where o.idSucursal = :onuIdSucursal")
	Optional<OnuEntity> findByOnuIdSucursal(@Param("onuIdSucursal") Long onuIdSucursal);
	
	public Optional<OnuEntity> findBySerie(String serie);
	
	@Query("from OnuEntity o where o.estatus = :estatus")
	public Iterable<OnuEntity> listByEstatus(@Param("estatus") EstatusOnuEntity estatus);
	
	@Query("from OnuEntity o where o.sucursal = :sucursal")
	public Iterable<OnuEntity> listBySucursal(@Param("sucursal") SucursalEntity sucursal);
	
	@Query("from OnuEntity o where o.sucursal = :sucursal and o.estatus = :estatus")
	public Iterable<OnuEntity> listBySucursalAndEstatus(@Param("sucursal") SucursalEntity sucursal, @Param("estatus") EstatusOnuEntity estatus);
	

}
