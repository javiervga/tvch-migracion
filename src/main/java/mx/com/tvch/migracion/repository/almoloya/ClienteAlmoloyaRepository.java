package mx.com.tvch.migracion.repository.almoloya;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.almoloya.ClienteAlmoloyaEntity;

@Repository
public interface ClienteAlmoloyaRepository extends CrudRepository<ClienteAlmoloyaEntity, Long> {

}
