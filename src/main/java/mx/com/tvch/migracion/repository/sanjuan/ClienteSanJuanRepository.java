package mx.com.tvch.migracion.repository.sanjuan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.sanjuan.ClienteSanJuanEntity;

@Repository
public interface ClienteSanJuanRepository extends CrudRepository<ClienteSanJuanEntity, Long> {

}
