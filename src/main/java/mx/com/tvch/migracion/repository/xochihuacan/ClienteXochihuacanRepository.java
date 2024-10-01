package mx.com.tvch.migracion.repository.xochihuacan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.xochihuacan.ClienteXochihuacanEntity;

@Repository
public interface ClienteXochihuacanRepository extends CrudRepository<ClienteXochihuacanEntity, Long> {

}
