package mx.com.tvch.migracion.repository.oncejulio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.oncejulio.ReporteOnceJulioEntity;

@Repository
public interface ReporteOnceJulioRepository extends CrudRepository<ReporteOnceJulioEntity, Long>{

}
