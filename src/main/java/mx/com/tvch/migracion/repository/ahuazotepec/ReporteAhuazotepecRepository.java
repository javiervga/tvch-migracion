package mx.com.tvch.migracion.repository.ahuazotepec;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.ahuazotepec.ReporteAhuazotepecEntity;

@Repository
public interface ReporteAhuazotepecRepository extends CrudRepository<ReporteAhuazotepecEntity, Long>{

}
