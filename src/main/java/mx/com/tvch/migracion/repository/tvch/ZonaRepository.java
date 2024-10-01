package mx.com.tvch.migracion.repository.tvch;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.tvch.migracion.entity.tvch.ZonaEntity;

@Repository
public interface ZonaRepository extends CrudRepository<ZonaEntity, Long>{
	
	@Query("from ZonaEntity z where z.estatus = :estatus")
	public Iterable<ZonaEntity> findByEstatus(@Param("estatus") Integer estatus);
	
	@Query(nativeQuery = true, value="SELECT z.id_zona, z.nombre, z.fecha_registro, z.estatus"
			+ "      FROM sucursales_x_usuario su"
			+ "     INNER"
			+ "      JOIN sucursales s"
			+ "        ON su.id_sucursal = s.id_sucursal"
			+ "     INNER"
			+ "      JOIN zonas z"
			+ "        ON s.id_zona = z.id_zona"
			+ "     where su.id_usuario = ?1")
	public List<ZonaEntity> findBySucursalesPorUsuario(Long idUser);
	
	public Optional<ZonaEntity> findByNombre(String nombre);

}
