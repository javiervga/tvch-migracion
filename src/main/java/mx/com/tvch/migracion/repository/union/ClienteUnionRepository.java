package mx.com.tvch.migracion.repository.union;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.union.ClienteUnionEntity;

@Repository
public interface ClienteUnionRepository extends CrudRepository<ClienteUnionEntity, Long> {

}
