package mx.com.tvch.migracion.repository.villas;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.villas.ReporteVillasEntity;

@Repository
public interface ReporteVillasRepository extends CrudRepository<ReporteVillasEntity, Long>{

}
