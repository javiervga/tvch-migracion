package mx.com.tvch.migracion.repository.lazaro;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.lazaro.ReporteLazaroEntity;

@Repository
public interface ReporteLazaroRepository extends CrudRepository<ReporteLazaroEntity, Long>{

}
