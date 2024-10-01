package mx.com.tvch.migracion.repository.pachuquilla;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.pachuquilla.ClientePachuquillaEntity;

@Repository
public interface ClientePachuquillaRepository extends CrudRepository<ClientePachuquillaEntity, Long> {

}
