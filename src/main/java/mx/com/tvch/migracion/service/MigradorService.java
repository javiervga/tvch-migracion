package mx.com.tvch.migracion.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.com.tvch.migracion.Constantes;
import mx.com.tvch.migracion.entity.old.ClienteOldEntity;
import mx.com.tvch.migracion.entity.old.ReporteOldEntity;
import mx.com.tvch.migracion.entity.tvch.ContratoEntity;
import mx.com.tvch.migracion.entity.tvch.ContratosxSuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.DomicilioEntity;
import mx.com.tvch.migracion.entity.tvch.DomiciliosxContratoEntity;
import mx.com.tvch.migracion.entity.tvch.EstatusContratoEntity;
import mx.com.tvch.migracion.entity.tvch.EstatusSuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.ServicioEntity;
import mx.com.tvch.migracion.entity.tvch.ServiciosxContratoEntity;
import mx.com.tvch.migracion.entity.tvch.SucursalEntity;
import mx.com.tvch.migracion.entity.tvch.SuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.TipoServicioEntity;
import mx.com.tvch.migracion.entity.tvch.UsuarioEntity;
import mx.com.tvch.migracion.repository.old.ClienteOldRepository;
import mx.com.tvch.migracion.repository.old.ReporteOldRepository;
import mx.com.tvch.migracion.repository.tvch.ContratoRepository;
import mx.com.tvch.migracion.repository.tvch.ContratosxSuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.DomicilioRepository;
import mx.com.tvch.migracion.repository.tvch.DomiciliosxContratoRepository;
import mx.com.tvch.migracion.repository.tvch.EstatusContratoRepository;
import mx.com.tvch.migracion.repository.tvch.EstatusSuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.ServicioRepository;
import mx.com.tvch.migracion.repository.tvch.ServiciosxContratoRepository;
import mx.com.tvch.migracion.repository.tvch.SucursalRepository;
import mx.com.tvch.migracion.repository.tvch.SuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.TipoServicioRepository;
import mx.com.tvch.migracion.repository.tvch.UsuarioRepository;

@Slf4j
@Service
public class MigradorService implements MIgracionSucursalService{
	
	private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 

	@Autowired
	private ClienteOldRepository clienteRepository;
	
	@Autowired
	private ReporteOldRepository reporteRepository;
	
	@Autowired
	private SuscriptorRepository suscriptorRepository;
	
	@Autowired
	private SucursalRepository sucursalRepository;
	
	@Autowired
	private EstatusSuscriptorRepository estatusSuscriptorRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EstatusContratoRepository estatusContratoRepository;
	
	@Autowired
	private TipoServicioRepository tipoServicioRepository;
	
	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private ServicioRepository servicioRepository;
	
	@Autowired
	private ContratosxSuscriptorRepository contratosxSuscriptorRepository;
	
	@Autowired
	private ServiciosxContratoRepository serviciosxContratoRepository;
					
	@Autowired
	private DomicilioRepository domicilioRepository;

	@Autowired
	private DomiciliosxContratoRepository domiciliosxContratoRepository;
	
	@Value("${tvch.user.id.instalador}")
	private Long usuarioIdTvch;
	
	@Value("${tvch.id.sucursal}")
	private Long sucursalId;
	
	@Value("${tvch.sucursal.placa}")
	private String colorPlaca;
	
	int contadorClientes = 0;
	int contadorNuevosSuscriptores = 0;
	int contadorNuevosContratos = 0;
	int contadorActualizados = 0;
	int contadorPorConciliarManualmente = 0;
	
	int clientesProcesadosOld = 0;
	int contratosProcesadosOld = 0;
	
	List<ClienteOldEntity> clientesConciliacionOld = new ArrayList<>();
	List<ContratosxSuscriptorEntity> contratosSuscriptorTvchConciliacion = new ArrayList<>();
	
	@Override
	public long count() throws Exception {
		// TODO Auto-generated method stub
		return clienteRepository.count();
	}

	@Override
	public void migrarSucursal() throws Exception {
		// TODO Auto-generated method stub
		
		//Paso 1 -> Obtener el registro de la sucursal de la nueva BD
		SucursalEntity sucursalEntity = sucursalRepository.findById(sucursalId).orElseThrow(()->new Exception("Sucursal No encontrada en BD TVCH"));
		
		//Paso 2 -> recuperar todos los suscriptores de la nueva base y validar para asegurarnos que no dupliquemos
		List<SuscriptorEntity> suscriptoresExistentes = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(suscriptorRepository.findBySucursal(sucursalEntity).iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		
		if(!suscriptoresExistentes.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			sb.append("Existen ").append(suscriptoresExistentes.size()).append(" suscriptores registrados en TVCH pertenecientes a la sucursal: ");
			sb.append(sucursalEntity.getNombre()).append(". Se procedera a registrar los nuevos suscriptores y actualizar los existentes");
			log.info(sb.toString());
		}else {
			StringBuilder sb = new StringBuilder();
			sb.append("No existen suscriptores registrados en TVCH pertenecientes a la sucursal: ");
			sb.append(sucursalEntity.getNombre()).append(". Se procedera a registrar todos los suscriptores.");
			log.info(sb.toString());
		}
		
		//if(suscriptoresExistentes.stream().anyMatch(s -> s.getSucursal().equals(sucursalId))) {
			//throw new Exception("Ya existen clientes registrados con la sucursal solicitada");
		//}
		
		//Paso 3 -> obtener de la BD TVCH el entity del usuario con que se van a registrar todo
		UsuarioEntity usuarioEntity = usuarioRepository.findById(usuarioIdTvch).orElseThrow(()->new Exception("Usuario Id no encontrado en TVCH"));
				
		//Paso 4 -> recuperar todos los clientes de la base anterior
		List<ClienteOldEntity> clientes = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(clienteRepository.findAll().iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		
		if(!clientes.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			sb.append("Se encontraron ").append(clientes.size()).append(" clientes en Bd para ser transferidos a TVCH.");
			log.info(sb.toString());
		}
		
		//Paso 5 -> procesar cada cliente y realizar los registros necesarios en BD TVCH
		
		clientes.forEach(c -> {
			try {
				migrarCliente(c,sucursalEntity,usuarioEntity/*, contadorClientes,contadorNuevosSuscriptores, contadorNuevosContratos, contadorActualizados*/);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Error al procesar cliente: "+c.toString());
				StringWriter sw = new StringWriter();
	            PrintWriter pw = new PrintWriter(sw);
	            e.printStackTrace(pw);
	            String exceptionString = sw.toString();
	            log.error(exceptionString);
			}
		});
		log.info("Clientes existentes OLD............."+clientes.size());
		log.info("Clientes Procesados OLD............."+clientesProcesadosOld);
		log.info("Contratos Procesados OLD............"+contratosProcesadosOld);
		
		//log.info("Se procesaron "+contadorClientes+" registros de clientes");
		log.info("Total de clientes procesados........"+contadorClientes);
		log.info("Nuevos suscriptores TVCH............"+contadorNuevosSuscriptores);
		log.info("Nuevos contratos TVCH..............."+contadorNuevosContratos);
		log.info("Contratos actualizados TVCH........."+contadorActualizados);
		log.info("Contratos por conciliar TVCH........"+contadorPorConciliarManualmente);
		
		log.info("--------------------------------------------------------------------");
		log.warn("Clientes por conciliar: ");
		clientesConciliacionOld.forEach(c -> log.warn(c.toString()));
		log.info("--------------------------------------------------------------------");
		log.warn("Contratos TVCH por conciliar: ");
		contratosSuscriptorTvchConciliacion.forEach(c -> log.warn(
				c.getSuscriptor().getNombre()
				.concat(" ").concat(c.getSuscriptor().getApellidoPaterno())
				.concat(" ").concat(c.getSuscriptor().getApellidoMaterno()) 
				.concat("-")
				.concat(" ID: ").concat(String.valueOf(c.getContrato().getId()))
				.concat(" Folio: ").concat(String.valueOf(c.getContrato().getFolioContrato()))));
		
	}
	
	private void migrarCliente(
			ClienteOldEntity clienteOldEntity, 
			SucursalEntity sucursalEntity, 
			UsuarioEntity usuarioEntity) 
					throws Exception{
		
		//Paso 1 -> validar por el nombre y apellidos si ya existe un cliente con el mismo contrato para que no se repita
		List<SuscriptorEntity> suscriptoresSucursal = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(suscriptorRepository.findBySucursal(sucursalEntity).iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		String nombreClienteOld = "'"+clienteOldEntity.getNom_cliente().trim().replace(" ", "").concat(clienteOldEntity.getApe_cliente().trim().replace(" ", "")+"'");		
		//Optional<SuscriptorEntity> suscriptorExistente = suscriptoresSucursal.stream()
			//	.filter(s -> clienteOldEntity.getNom_cliente().trim().replace(" ", "").concat(clienteOldEntity.getApe_cliente().trim().replace(" ", "")).equals(
				//s.getNombre().trim().replace(" ", "").concat(s.getApellidoPaterno().replace(" ", "").trim())./*concat(" ").*/concat(s.getApellidoMaterno().replace(" ", "").trim())))
				//.findFirst();
		
		/*Optional<SuscriptorEntity> suscriptorExistente = suscriptoresSucursal.stream()
				.filter(s -> clienteOldEntity.getNom_cliente().contains(s.getNombre()) && 
						clienteOldEntity.getApe_cliente().contains(s.getApellidoPaterno()) &&
						clienteOldEntity.getApe_cliente().contains(s.getApellidoMaterno()))
				.findFirst();*/
			
		List<SuscriptorEntity> suscriptoresConMismoNombre = suscriptoresSucursal
				.stream()
				.filter(s -> clienteOldEntity.getNom_cliente().contains(s.getNombre()) && 
						clienteOldEntity.getApe_cliente().contains(s.getApellidoPaterno()) &&
						clienteOldEntity.getApe_cliente().contains(s.getApellidoMaterno()))
				.collect(Collectors.toList());
		
		if(suscriptoresConMismoNombre.size() == 1) {
			//flujo para suscriptores que ya estan registrados en TVCH
			
			/*StringBuilder sb = new StringBuilder();
			sb.append("Actualizando informacion de suscriptor ").append(suscriptorExistente.get().getId()).append(" -> ");
			sb.append(suscriptorExistente.get().getNombre()).append(" ").append(suscriptorExistente.get().getApellidoPaterno());
			sb.append(" ").append(suscriptorExistente.get().getApellidoMaterno());
			log.info(sb.toString());*/
			
			StringBuilder sb = new StringBuilder();
			sb.append("Actualizando informacion de suscriptor ").append(suscriptoresConMismoNombre.getFirst().getId()).append(" -> ");
			sb.append(suscriptoresConMismoNombre.getFirst().getNombre()).append(" ").append(suscriptoresConMismoNombre.getFirst().getApellidoPaterno());
			sb.append(" ").append(suscriptoresConMismoNombre.getFirst().getApellidoMaterno());
			log.info(sb.toString());
			
			//con el suscriptor encontrado, manejar escenarios
			// 1 - el suscriptor no tiene contratos regsitrados en tvch -> en este caso se registrs el nuevo contrato
			// 2 - el suscriptor tiene un solo contrato -> en este caso se actualiza
			// 3 - si el suscriptor tiene mas de un contrato loguearlo y actualizar manualmente para evitar errores
			List<ContratosxSuscriptorEntity> entitiesExistentes = 
					StreamSupport.stream(Spliterators.spliteratorUnknownSize(contratosxSuscriptorRepository.findBySuscriptor(suscriptoresConMismoNombre.getFirst()).iterator(), Spliterator.ORDERED), false)
					.collect(Collectors.toList());
			
			
			if(entitiesExistentes.isEmpty()) {
				//escenario 1

				//en este caso solo se registra el nuevo contrato
				ContratoEntity nuevoContrato = crearContrato(clienteOldEntity, usuarioEntity);
				///////contratoRepository.save(nuevoContrato);
				//crear registro de contratos por suscriptor
				ContratosxSuscriptorEntity contratosxSuscriptorEntity = crearContratoxSuscriptor(nuevoContrato, suscriptoresConMismoNombre.getFirst());
				/////////contratosxSuscriptorRepository.save(contratosxSuscriptorEntity);
				//si se requiere crear servicio y servicio x contrato
				validaryCrearServicio(sucursalEntity, clienteOldEntity, nuevoContrato);			
				//si se requiere crear terminal y terminal x contrato
				//validaryCrearTerminal(usuarioEntity, nuevoContrato);			
				//crear domicilio
				validaryCrearDomicilio(clienteOldEntity, nuevoContrato);
				
				contadorNuevosContratos = contadorNuevosContratos +1;
				contratosProcesadosOld = contratosProcesadosOld + 1;
				
			}else {
				
				log.warn("El cliente con contrato: "+clienteOldEntity.getNum_contrato()+" ya ha sido registrado anteriormente en TVCH como suscriptor: "
						+suscriptoresConMismoNombre.getFirst().getId()+", se procede a validar y actualizar el estatus de contrato.");
				
				if(entitiesExistentes.size() == 1) {
					//escenario 2
					
					ContratosxSuscriptorEntity suscriptorxContratoExistente = entitiesExistentes.getFirst();
					
					log.info("Actualizando contrato con Id: "+suscriptorxContratoExistente.getContrato().getId()+" Folio: "+
							suscriptorxContratoExistente.getContrato().getFolioContrato());
					
					Long estatusId = null;
					String estatusCliente = clienteOldEntity.getEst_cliente();
					switch(estatusCliente) {
					 	case "CORTESIA":
					 		estatusId = 4L;
					 		break;
					 	case "CANCELADO RETIRADO":
					 		estatusId = 8L;
					 		break;
					 	case "CORTE":
					 		estatusId = 5L;
					 		break;
					 	case "CANCELADO PENDIENTE DE RETIRO":
					 		estatusId = 7L;
					 		break;
					 	case "ACTIVO":
					 		estatusId = 3L;
					 		break;
					 	case "RECONEXION":
					 		estatusId = 6L;
					 		break;
					 	case "PENDIENTE DE INSTALAR":
					 		estatusId = 2L;
					 		break;
					}
					
					EstatusContratoEntity estatus = estatusContratoRepository
							.findById(estatusId)
							.get();
					ContratoEntity contratoPorActualizar = suscriptorxContratoExistente.getContrato();
					
					//validar si el estatus del contrato cambio
					StringBuilder logCambio = new StringBuilder();
					if(contratoPorActualizar.getEstatus().getIdEstatus().longValue() != estatusId.longValue()) {
						if(contratoPorActualizar.getFolioContrato()!=null) {
							logCambio.append("El estatus del contrato ").append(contratoPorActualizar.getId()).append(":");
							logCambio.append(contratoPorActualizar.getFolioContrato()).append(" cambia de ");
							logCambio.append(contratoPorActualizar.getEstatus().getDescripcion()).append(" a ");
							logCambio.append(estatus.getDescripcion());
						}else {
							logCambio.append("El estatus del contrato ").append(contratoPorActualizar.getId()).append(" cambia de ");
							logCambio.append(contratoPorActualizar.getEstatus().getDescripcion()).append(" a ").append(estatus.getDescripcion());
						}
					}else {
						if(contratoPorActualizar.getFolioContrato()!=null) {
							logCambio.append("El estatus del contrato ").append(contratoPorActualizar.getId()).append(":");
							logCambio.append(contratoPorActualizar.getFolioContrato()).append(" se mantiene con estatus ");
							logCambio.append(contratoPorActualizar.getEstatus().getDescripcion());
						}else {
							logCambio.append("El estatus del contrato ").append(contratoPorActualizar.getId()).append(" se mantiene con estatus ");
							logCambio.append(contratoPorActualizar.getEstatus().getDescripcion());
						}
					}
					log.info(logCambio.toString());
					
					contratoPorActualizar.setEstatus(estatus);
					//contratoRepository.save(contratoExistente);
					log.info("Contrato "+contratoPorActualizar.getId()+":"+contratoPorActualizar.getFolioContrato()+" actualizado correctamente.");
					
					contadorActualizados = contadorActualizados + 1;
					contratosProcesadosOld = contratosProcesadosOld + 1;
					
				}else {
					
					//To DO -> que se actualicen los que tengan id anterior y solo dejar pendientes los q no
					
					//escenario 3
					log.warn("--------------------------------------------------------------------------------------------------------------------");
					log.warn("No ha sido posible actualizar contrato ya que el suscriptor tiene "+entitiesExistentes.size()+" contratos.");
					for(ContratosxSuscriptorEntity c : entitiesExistentes) {
						log.warn("Contrato ->"+c.toString());
						//log.warn("contrato -> "+c.getContrato().getId()+" con estatus "+c.getContrato().getEstatus().getDescripcion());
						contratosSuscriptorTvchConciliacion.add(c);
						contadorPorConciliarManualmente = contadorPorConciliarManualmente + 1;
					}
					clientesConciliacionOld.add(clienteOldEntity);
					
				}
			}
			
		}else if(suscriptoresConMismoNombre.size() > 1) {
		
			//En este escenario, se encontraron en tvch mas de un suscriptor con el mismo nombre, todos se van a conciliacion
			log.warn("--------------------------------------------------------------------------------------------------------------------");
			log.warn("No se procesa cliente "+clienteOldEntity.getNom_cliente()+" "+clienteOldEntity.getApe_cliente()
					+" ya que se encontraron mas de un suscriptor con el mismo nombre");
			for(SuscriptorEntity s : suscriptoresConMismoNombre) {
				log.warn("Suscriptor con mismo nombre ->"+s.toString());				
			}
			clientesConciliacionOld.add(clienteOldEntity);
		
		}else {
			
			//flujo para clientes nuevos que se van a registrar en TVCH
			
			StringBuilder sb = new StringBuilder();
			sb.append("Registrando nuevo suscriptor ").append(clienteOldEntity.getNom_cliente()).append(" ");
			sb.append(clienteOldEntity.getApe_cliente()).append(" con numero de contrato ").append(clienteOldEntity.getNum_contrato());
			log.info(sb.toString());
			
			//crear registro de contrato
			ContratoEntity nuevoContrato = crearContrato(clienteOldEntity, usuarioEntity);
			///////contratoRepository.save(nuevoContrato);
			//crear registro de suscriptor
			SuscriptorEntity nuevoSuscriptor = crearSuscriptor(clienteOldEntity, sucursalEntity, usuarioEntity, nuevoContrato);
			//////suscriptorRepository.save(nuevoSuscriptor);
			//crear registro de contratos por suscriptor
			ContratosxSuscriptorEntity contratosxSuscriptorEntity = crearContratoxSuscriptor(nuevoContrato, nuevoSuscriptor);
			//////contratosxSuscriptorRepository.save(contratosxSuscriptorEntity);
			//si se requiere crear servicio y servicio x contrato
			validaryCrearServicio(sucursalEntity, clienteOldEntity, nuevoContrato);			
			//si se requiere crear terminal y terminal x contrato
			//validaryCrearTerminal(usuarioEntity, nuevoContrato);			
			//crear domicilio
			validaryCrearDomicilio(clienteOldEntity, nuevoContrato);
			
			contadorNuevosSuscriptores = contadorNuevosSuscriptores + 1;
			contadorNuevosContratos = contadorNuevosContratos + 1;
			contratosProcesadosOld = contratosProcesadosOld + 1;
		}
		
		clientesProcesadosOld = clientesProcesadosOld + 1;
		contadorClientes = contadorClientes + 1;
	}
	
	private void validaryCrearDomicilio(ClienteOldEntity clienteEntity, ContratoEntity contratoEntity) {
		
		DomicilioEntity domicilioEntity = new DomicilioEntity();
		domicilioEntity.setCalle(clienteEntity.getCal_cliente());
		domicilioEntity.setColonia(clienteEntity.getCol_cliente());
		domicilioEntity.setEstatus(1); //activo
		domicilioEntity.setNumeroCalle(clienteEntity.getNum_cliente());
		domicilioEntity.setReferencia(clienteEntity.getObs_cliente());
		/////domicilioRepository.save(domicilioEntity);
		
		DomiciliosxContratoEntity domiciliosxContratoEntity = new DomiciliosxContratoEntity();
		domiciliosxContratoEntity.setContrato(contratoEntity);
		domiciliosxContratoEntity.setDomicilio(domicilioEntity);
		//////domiciliosxContratoRepository.save(domiciliosxContratoEntity);
		
	}
	
	/**
	 * 
	 * @param sucursalEntity
	 * @param clienteEntity
	 * @param contratoEntity
	 */
	private void validaryCrearServicio(SucursalEntity sucursalEntity, ClienteOldEntity clienteEntity, ContratoEntity contratoEntity) {
		
		// obtener todos los servicios en tvch
		List<ServicioEntity> servicios = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(servicioRepository.findAll().iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		if( servicios.stream().anyMatch(s -> s.getZona().getId() == sucursalEntity.getZona().getId() && s.getNombre().equals(clienteEntity.getSer_cliente())) ) {
			//si el servicio ya existe solo se inserta en servicios x contrato
			ServicioEntity servicioEntity = servicios.stream().filter(s -> s.getZona().getId() == sucursalEntity.getZona().getId() && s.getNombre().equals(clienteEntity.getSer_cliente())).findFirst().get();
			ServiciosxContratoEntity serviciosxContratoEntity = new ServiciosxContratoEntity();
			serviciosxContratoEntity.setContrato(contratoEntity);
			serviciosxContratoEntity.setEstatus(1);
			serviciosxContratoEntity.setServicio(servicioEntity);
			/////serviciosxContratoRepository.save(serviciosxContratoEntity);
		}else {
			//si el servicio no existe se crea primero y despues se inserta en servicios x contrato
			
			
			//buscar si existe un costo para el servicio que trae el cliente
			Double costo = 0.0;
			
			TipoServicioEntity tipoServicioEntity = null;
			double costoInstalacion = 0;
			if(clienteEntity.getSer_cliente().contains("INTERNET")) {
				tipoServicioEntity = tipoServicioRepository.findById(Constantes.TIPO_SERVICIO_TV_INTERNET).get();
				costoInstalacion = 150;
			}
			else {
				tipoServicioEntity = tipoServicioRepository.findById(Constantes.TIPO_SERVICIO_TV).get();
				costoInstalacion = 100;
			}
			
			ServicioEntity entity = new ServicioEntity();
			entity.setCosto(costo);
			entity.setCostoInstalacion(costoInstalacion);
			entity.setDescripcion(clienteEntity.getSer_cliente());
			entity.setEstatus(1);//activo
			entity.setNombre(clienteEntity.getSer_cliente());
			entity.setZona(sucursalEntity.getZona());
			entity.setTipoServicio(tipoServicioEntity);
			//////servicioRepository.save(entity);
			
			ServiciosxContratoEntity serviciosxContratoEntity = new ServiciosxContratoEntity();
			serviciosxContratoEntity.setContrato(contratoEntity);
			serviciosxContratoEntity.setEstatus(1);
			serviciosxContratoEntity.setServicio(entity);
			/////serviciosxContratoRepository.save(serviciosxContratoEntity);
		}
	}
	
	/**
	 * 
	 * @param contratoEntity
	 * @param suscriptorEntity
	 * @return
	 */
	private ContratosxSuscriptorEntity crearContratoxSuscriptor(ContratoEntity contratoEntity, SuscriptorEntity suscriptorEntity) {
		ContratosxSuscriptorEntity entity = new ContratosxSuscriptorEntity();
		entity.setContrato(contratoEntity);
		entity.setSuscriptor(suscriptorEntity);
		return entity;
	}
	
	
	private ContratoEntity crearContrato(
			ClienteOldEntity clienteEntity,
			UsuarioEntity usuarioEntity) throws Exception{
		
		//Primero buscar si el estatus existe en TVCH
		EstatusContratoEntity estatusContratoEntity = null;
		Optional<EstatusContratoEntity> optional = estatusContratoRepository.findByDescripcion(clienteEntity.getEst_cliente());
		if(optional.isPresent()) {
			estatusContratoEntity = optional.get();
		}else {
			estatusContratoEntity = new EstatusContratoEntity();
			estatusContratoEntity.setDescripcion(clienteEntity.getEst_cliente());
			estatusContratoRepository.save(estatusContratoEntity);
		}
		
		//recuperar el ultimo registro de la tabla reporte con el numero de contrato anterior
		Integer tvsContratadas = null;
		List<ReporteOldEntity> reportesCliente = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(reporteRepository.findByContrato(String.valueOf(clienteEntity.getNum_contrato())).iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		ReporteOldEntity reporteExistente = null;
		if(!reportesCliente.isEmpty()) {
			reporteExistente = reportesCliente.getLast();
			try {
				tvsContratadas = Integer.parseInt(reporteExistente.getTvs());
			}catch(Exception e) {
				//log.warn("Error al obtener tvs contratadas de reporte: "+reporteExistente.toString());
			}
		}
		
		ContratoEntity entity = new ContratoEntity();
		entity.setFechaProximoPago(formatoFecha.parse(obtenerFechaPago(clienteEntity)));
		entity.setEstatus(estatusContratoEntity);
		entity.setFechaRegistro(formatoFecha.parse(obtenerFechIngreso(clienteEntity)));
		entity.setFolioContrato(clienteEntity.getNum_contrato());
		entity.setTvsContratadas(tvsContratadas);
		entity.setUsuario(usuarioEntity);
		entity.setFolioPlaca(clienteEntity.getNum_contrato());
		entity.setColorPlaca("");
		return entity;
	}
	
	/**
	 * 
	 * @param clienteEntity
	 * @param sucursalEntity
	 * @param estatusSuscriptorEntity
	 * @param usuarioEntity
	 * @return
	 * @throws Exception
	 */
	private SuscriptorEntity crearSuscriptor(
			ClienteOldEntity clienteEntity,
			SucursalEntity sucursalEntity, 
			UsuarioEntity usuarioEntity,
			ContratoEntity contratoEntity) throws Exception{
		
		String apellidoPaterno = "";
		String apellidoMaterno = "";
		if(clienteEntity.getApe_cliente() != null && !clienteEntity.getApe_cliente().isBlank()) {
			String[] apellidos = clienteEntity.getApe_cliente().split(" ");
			if(apellidos.length > 0) {
				if(apellidos.length > 1) {
					apellidoPaterno = apellidos[0];
					apellidoMaterno = apellidos[1];
				}
			}else {
				apellidoPaterno = clienteEntity.getApe_cliente();
			}
		}
		
		EstatusSuscriptorEntity estatusSuscriptorEntity = null;
		if(contratoEntity.getEstatus().getDescripcion().contains("CANCELADO")) {
			estatusSuscriptorEntity = estatusSuscriptorRepository.findById(Constantes.ESTATUS_SUSCRIPTOR_INACTIVO).get();
		}else {
			estatusSuscriptorEntity = estatusSuscriptorRepository.findById(Constantes.ESTATUS_SUSCRIPTOR_ACTIVO).get();
		}
		
		SuscriptorEntity entity = new SuscriptorEntity();
		entity.setApellidoMaterno(apellidoMaterno);
		entity.setApellidoPaterno(apellidoPaterno);
		entity.setEstatus(estatusSuscriptorEntity);
		entity.setFechaRegistro(contratoEntity.getFechaRegistro());
		entity.setNombre(clienteEntity.getNom_cliente());
		entity.setSucursal(sucursalEntity);
		entity.setTelefono(clienteEntity.getTel_cliente());
		entity.setUsuario(usuarioEntity);
		return entity;
	}
	
	/**
	 * 
	 * @param clienteEntity
	 * @return
	 */
	private String obtenerFechIngreso(ClienteOldEntity clienteEntity) {
		StringBuilder sbFechaIngreso = new StringBuilder();
		if(clienteEntity.getFid_cliente() != null && clienteEntity.getFid_cliente() > 0 &&
				clienteEntity.getFim_cliente() != null && !clienteEntity.getFim_cliente().isBlank() && Long.parseLong(clienteEntity.getFim_cliente().trim()) > 0 &&
						clienteEntity.getFia_cliente() != null && clienteEntity.getFia_cliente() > 0) {
			if(clienteEntity.getFid_cliente().longValue() < 10) {
				sbFechaIngreso.append("0").append(clienteEntity.getFid_cliente());
			}else {
				sbFechaIngreso.append(clienteEntity.getFid_cliente());
			}
			sbFechaIngreso.append("/");
			if(Long.parseLong(clienteEntity.getFim_cliente().trim()) < 10) {
				sbFechaIngreso.append("0").append(clienteEntity.getFim_cliente().trim());
			}else {
				sbFechaIngreso.append(clienteEntity.getFim_cliente().trim());
			}
			sbFechaIngreso.append("/");
			sbFechaIngreso.append(clienteEntity.getFia_cliente());
			
		}
		return sbFechaIngreso.toString();
	}
	
	/**
	 * 
	 * @param clienteEntity
	 * @return
	 */
	private String obtenerFechaPago(ClienteOldEntity clienteEntity) {
		StringBuilder sbFechaIngreso = new StringBuilder();
		if(clienteEntity.getFcd_cliente() != null && clienteEntity.getFcd_cliente() > 0 &&
				clienteEntity.getFcm_cliente() != null && !clienteEntity.getFcm_cliente().isBlank() && Long.parseLong(clienteEntity.getFcm_cliente().trim()) > 0 &&
						clienteEntity.getFca_cliente() != null && clienteEntity.getFca_cliente() > 0) {
			if(clienteEntity.getFcd_cliente().longValue() < 10) {
				sbFechaIngreso.append("0").append(clienteEntity.getFcd_cliente() );
			}else {
				sbFechaIngreso.append(clienteEntity.getFcd_cliente() );
			}
			sbFechaIngreso.append("/");
			if(Long.parseLong(clienteEntity.getFcm_cliente().trim()) < 10) {
				sbFechaIngreso.append("0").append(clienteEntity.getFcm_cliente().trim());
			}else {
				sbFechaIngreso.append(clienteEntity.getFcm_cliente().trim());
			}
			sbFechaIngreso.append("/");
			sbFechaIngreso.append(clienteEntity.getFca_cliente() );		
			}
		return sbFechaIngreso.toString();
	}

}
