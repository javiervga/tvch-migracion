package mx.com.tvch.migracion.repository.santajulia;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.santajulia.ClienteSantaJuliaEntity;

@Repository
public interface ClienteSantaJuliaRepository extends CrudRepository<ClienteSantaJuliaEntity, Long> {

}
