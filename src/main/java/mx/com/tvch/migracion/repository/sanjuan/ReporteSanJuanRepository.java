package mx.com.tvch.migracion.repository.sanjuan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.sanjuan.ReporteSanJuanEntity;

@Repository
public interface ReporteSanJuanRepository extends CrudRepository<ReporteSanJuanEntity, Long>{

}
