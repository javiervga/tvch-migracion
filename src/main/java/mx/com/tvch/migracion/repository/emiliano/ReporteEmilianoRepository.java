package mx.com.tvch.migracion.repository.emiliano;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.emiliano.ReporteEmilianoEntity;

@Repository
public interface ReporteEmilianoRepository extends CrudRepository<ReporteEmilianoEntity, Long>{

}
