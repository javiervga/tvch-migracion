package mx.com.tvch.migracion.repository.tuzos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.tuzos.ClienteTuzosEntity;

@Repository
public interface ClienteTuzosRepository extends CrudRepository<ClienteTuzosEntity, Long> {

}
