package mx.com.tvch.migracion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import mx.com.tvch.migracion.service.MigradorService;

@Slf4j
@Component
public class RunAfterStartup {
	
	
	@Autowired
	@Qualifier("sucursalesExistentes")
	private List<Long> sucursalesExistentes;
	
	@Autowired
	private MigradorService service;
	
	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
	    
		System.out.println("Inicializando registro de clientes........");
		
		try {
			service.migrarSucursal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error al migrar sucursal: "+e.getMessage());
			e.printStackTrace();
		}
		
	    System.out.println("Finalizando registro de clientes........");
	    
	}

}
