package mx.com.tvch.migracion.repository.irolo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.irolo.ReporteIroloEntity;

@Repository
public interface ReporteIroloRepository extends CrudRepository<ReporteIroloEntity, Long>{

}
