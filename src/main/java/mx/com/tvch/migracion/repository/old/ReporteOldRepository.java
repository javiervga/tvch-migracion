package mx.com.tvch.migracion.repository.old;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.old.ReporteOldEntity;

@Repository
public interface ReporteOldRepository extends CrudRepository<ReporteOldEntity, Long>{
	
	@Query("from ReporteOldEntity r where r.contrato = :contrato")
	public Iterable<ReporteOldEntity> findByContrato(@Param("contrato") String contrato);

}
