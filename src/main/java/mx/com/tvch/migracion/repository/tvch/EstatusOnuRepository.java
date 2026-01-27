package mx.com.tvch.migracion.repository.tvch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.tvch.EstatusOnuEntity;

@Repository
public interface EstatusOnuRepository extends CrudRepository<EstatusOnuEntity, Long> {
	

}
