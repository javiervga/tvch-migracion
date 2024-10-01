package mx.com.tvch.migracion.repository.apan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.apan.ReporteApanEntity;

@Repository
public interface ReporteApanRepository extends CrudRepository<ReporteApanEntity, Long>{

}
