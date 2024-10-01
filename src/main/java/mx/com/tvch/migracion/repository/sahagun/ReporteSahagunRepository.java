package mx.com.tvch.migracion.repository.sahagun;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.sahagun.ReporteSahagunEntity;

@Repository
public interface ReporteSahagunRepository extends CrudRepository<ReporteSahagunEntity, Long>{

}
