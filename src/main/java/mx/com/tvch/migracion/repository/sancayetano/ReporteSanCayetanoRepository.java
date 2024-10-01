package mx.com.tvch.migracion.repository.sancayetano;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.sancayetano.ReporteSanCayetanoEntity;

@Repository
public interface ReporteSanCayetanoRepository extends CrudRepository<ReporteSanCayetanoEntity, Long>{

}
