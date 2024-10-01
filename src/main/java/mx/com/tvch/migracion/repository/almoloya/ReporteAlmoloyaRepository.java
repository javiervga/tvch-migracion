package mx.com.tvch.migracion.repository.almoloya;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.almoloya.ReporteAlmoloyaEntity;

@Repository
public interface ReporteAlmoloyaRepository extends CrudRepository<ReporteAlmoloyaEntity, Long>{

}
