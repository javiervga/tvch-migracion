package mx.com.tvch.migracion.repository.tvch;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import mx.com.tvch.migracion.entity.tvch.ContratosxSuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.SuscriptorEntity;

public interface ContratosxSuscriptorRepository extends CrudRepository<ContratosxSuscriptorEntity, Long>{
	
	@Query("from ContratosxSuscriptorEntity cs where cs.suscriptor = :suscriptor")
	public Iterable<ContratosxSuscriptorEntity> findBySuscriptor(@Param("suscriptor") SuscriptorEntity suscriptor);

}
