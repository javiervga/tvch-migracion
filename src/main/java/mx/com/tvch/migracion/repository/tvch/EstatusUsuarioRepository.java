package mx.com.tvch.migracion.repository.tvch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.com.tvch.migracion.entity.tvch.EstatusUsuarioEntity;

@Repository
public interface EstatusUsuarioRepository extends CrudRepository<EstatusUsuarioEntity, Long>{

}
