package mx.com.tvch.migracion.repository.tepeyahualco;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.tepeyahualco.ReporteTepeyahualcoEntity;

@Repository
public interface ReporteTepeyahualcoRepository extends CrudRepository<ReporteTepeyahualcoEntity, Long>{

}
