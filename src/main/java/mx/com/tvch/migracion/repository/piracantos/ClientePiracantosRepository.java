package mx.com.tvch.migracion.repository.piracantos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.piracantos.ClientePiracantosEntity;

@Repository
public interface ClientePiracantosRepository extends CrudRepository<ClientePiracantosEntity, Long> {

}
