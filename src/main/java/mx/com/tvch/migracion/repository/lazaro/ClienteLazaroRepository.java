package mx.com.tvch.migracion.repository.lazaro;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tvch.migracion.entity.lazaro.ClienteLazaroEntity;

@Repository
public interface ClienteLazaroRepository extends CrudRepository<ClienteLazaroEntity, Long> {

}
