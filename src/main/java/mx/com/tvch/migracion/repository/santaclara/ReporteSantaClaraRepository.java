package mx.com.tvch.migracion.repository.santaclara;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.santaclara.ReporteSantaClaraEntity;

@Repository
public interface ReporteSantaClaraRepository extends CrudRepository<ReporteSantaClaraEntity, Long>{

}
