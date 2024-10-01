package mx.com.tvch.migracion.repository.chimalpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.chimalpa.ReporteChimalpaEntity;

@Repository
public interface ReporteChimalpaRepository extends CrudRepository<ReporteChimalpaEntity, Long>{

}
