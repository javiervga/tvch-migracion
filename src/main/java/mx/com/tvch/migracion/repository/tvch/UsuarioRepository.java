package mx.com.tvch.migracion.repository.tvch;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.tvch.migracion.entity.tvch.EstatusUsuarioEntity;
import mx.com.tvch.migracion.entity.tvch.PuestoEntity;
import mx.com.tvch.migracion.entity.tvch.UsuarioEntity;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
	
	public Optional<UsuarioEntity> findByUsuario(String usuario);
	
	@Query("from UsuarioEntity u where u.usuario = :usuario and u.estatus = :estatus")
	public Optional<UsuarioEntity> findByUsernameAndActiveStatus(@Param("usuario") String usuario, @Param("estatus") EstatusUsuarioEntity estatus);

	@Query("from UsuarioEntity u where u.estatus = :estatus")
	public Iterable<UsuarioEntity> findByEstatus(@Param("estatus") EstatusUsuarioEntity estatus);
	
	@Query("from UsuarioEntity u where u.puesto = :puesto")
	public Iterable<UsuarioEntity> findByPuesto(@Param("puesto") PuestoEntity puesto);
	
	@Query("from UsuarioEntity u where u.estatus = :estatus and u.puesto = :puesto")
	public Iterable<UsuarioEntity> findByEstatusyPuesto(@Param("estatus") EstatusUsuarioEntity estatus, @Param("puesto") PuestoEntity puesto);

}
