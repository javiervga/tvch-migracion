package mx.com.tvch.migracion.repository.tvch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.tvch.PlacaEntity;

@Repository
public interface PlacaRepository extends CrudRepository<PlacaEntity, Long>{

}
