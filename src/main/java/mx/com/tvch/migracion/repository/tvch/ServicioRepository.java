package mx.com.tvch.migracion.repository.tvch;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.com.tvch.migracion.entity.tvch.ServicioEntity;

@Repository
public interface ServicioRepository extends CrudRepository<ServicioEntity, Long>{
	
	public Optional<ServicioEntity> findByNombre(String nombre);

}
