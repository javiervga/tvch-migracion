package mx.com.tvch.migracion.repository.providencia;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.com.tvch.migracion.entity.providencia.ClienteProvidenciaEntity;

@Repository
public interface ClienteProvidenciaRepository extends CrudRepository<ClienteProvidenciaEntity, Long> {

}
