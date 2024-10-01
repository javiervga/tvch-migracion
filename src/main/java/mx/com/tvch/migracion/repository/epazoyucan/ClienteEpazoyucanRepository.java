package mx.com.tvch.migracion.repository.epazoyucan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.epazoyucan.ClienteEpazoyucanEntity;

@Repository
public interface ClienteEpazoyucanRepository extends CrudRepository<ClienteEpazoyucanEntity, Long> {

}
