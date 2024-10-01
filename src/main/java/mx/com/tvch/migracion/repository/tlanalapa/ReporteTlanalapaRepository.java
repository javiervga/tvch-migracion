package mx.com.tvch.migracion.repository.tlanalapa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.tlanalapa.ReporteTlanalapaEntity;

@Repository
public interface ReporteTlanalapaRepository extends CrudRepository<ReporteTlanalapaEntity, Long>{

}
