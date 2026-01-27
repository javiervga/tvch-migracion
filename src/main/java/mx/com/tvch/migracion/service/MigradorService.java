package mx.com.tvch.migracion.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
//import mx.com.tvch.migracion.entity.old.ReporteOldEntity;
import mx.com.tvch.migracion.entity.tvch.ContratoEntity;
import mx.com.tvch.migracion.entity.tvch.ContratosxSuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.DomicilioEntity;
import mx.com.tvch.migracion.entity.tvch.DomiciliosxContratoEntity;
import mx.com.tvch.migracion.entity.tvch.EstatusContratoEntity;
import mx.com.tvch.migracion.entity.tvch.EstatusOnuEntity;
import mx.com.tvch.migracion.entity.tvch.EstatusSuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.OnuEntity;
import mx.com.tvch.migracion.entity.tvch.ServicioEntity;
import mx.com.tvch.migracion.entity.tvch.ServiciosxContratoEntity;
import mx.com.tvch.migracion.entity.tvch.SucursalEntity;
import mx.com.tvch.migracion.entity.tvch.SuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.TipoServicioEntity;
import mx.com.tvch.migracion.entity.tvch.UsuarioEntity;
import mx.com.tvch.migracion.repository.old.ClienteOldRepository;
//import mx.com.tvch.migracion.repository.old.ReporteOldRepository;
import mx.com.tvch.migracion.repository.tvch.ContratoRepository;
import mx.com.tvch.migracion.repository.tvch.ContratosxSuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.DomicilioRepository;
import mx.com.tvch.migracion.repository.tvch.DomiciliosxContratoRepository;
import mx.com.tvch.migracion.repository.tvch.EstatusContratoRepository;
import mx.com.tvch.migracion.repository.tvch.EstatusSuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.OnuRepository;
import mx.com.tvch.migracion.repository.tvch.ServicioRepository;
import mx.com.tvch.migracion.repository.tvch.ServiciosxContratoRepository;
import mx.com.tvch.migracion.repository.tvch.SucursalRepository;
import mx.com.tvch.migracion.repository.tvch.SuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.TipoServicioRepository;
import mx.com.tvch.migracion.repository.tvch.UsuarioRepository;
import mx.com.tvch.migracion.util.Utilerias;

@Slf4j  
@Service
public class MigradorService implements MIgracionSucursalService{
	
	private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 

	@Autowired
	private ClienteOldRepository clienteRepository;
	
	//@Autowired
	//private ReporteOldRepository reporteRepository;
	
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
	
	@Autowired
	private OnuRepository onuRepository;
	
	@Autowired
	private Utilerias util;
	
	@Value("${tvch.user.id.instalador}")
	private Long usuarioIdTvch;
	
	@Value("${tvch.id.sucursal}")
	private Long sucursalId;
	
	@Value("${tvch.sucursal.placa}")
	private String colorPlaca;
	
	int contadorClientes = 0;
	int contadorNuevosSuscriptores = 0;
	int contadorNuevosContratos = 0;
	
	private EstatusOnuEntity estatusOnuEntity = new EstatusOnuEntity();
	
	
		
	@Override
	public long count() throws Exception {
		// TODO Auto-generated method stub
		return clienteRepository.count();
	}

	/**
	 * 
	 */
	@Override
	public void migrarSucursal() throws Exception {
		// TODO Auto-generated method stub
		
		estatusOnuEntity.setEstatusId(Constantes.ESTATUS_ONU_ASIGNADA);
		estatusOnuEntity.setDescripcion("ASIGNADA");
		
		//Paso 1 -> Obtener el registro de la sucursal de la nueva BD
		SucursalEntity sucursalEntity = sucursalRepository.findById(sucursalId).orElseThrow(()->new Exception("Sucursal No encontrada en BD TVCH"));
		
		//Paso 2 -> consultar si existen registros de suscriptores de la sucursal 
		List<SuscriptorEntity> suscriptoresExistentes = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(suscriptorRepository.findBySucursal(sucursalEntity).iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		
		if(!suscriptoresExistentes.isEmpty()) {
			throw new Exception("Ya existen suscriptores pertenecientes a la sucursal "+sucursalEntity.getNombre()+" registrados anteriormente.");
		}else {
			StringBuilder sb = new StringBuilder();
			sb.append("No existen suscriptores registrados en TVCH pertenecientes a la sucursal: ");
			sb.append(sucursalEntity.getNombre()).append(". Se procedera a registrar todos los suscriptores.");
			log.info(sb.toString());
		}
		
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
				migrarCliente(c,sucursalEntity,usuarioEntity);
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
		
		log.info("Total de clientes procesados........"+contadorClientes);
		log.info("Nuevos suscriptores TVCH............"+contadorNuevosSuscriptores);
		log.info("Nuevos contratos TVCH..............."+contadorNuevosContratos);
		
		
	}
	
	private void migrarCliente(
			ClienteOldEntity clienteOldEntity, 
			SucursalEntity sucursalEntity, 
			UsuarioEntity usuarioEntity) 
					throws Exception{
		
		//Paso 1 -> validar por el nombre y apellidos si ya existe un cliente con el mismo contrato para que no se repita
		List<SuscriptorEntity> suscriptoresSucursal = 
				StreamSupport.stream(Spliterators
						.spliteratorUnknownSize(suscriptorRepository
								.findBySucursal(sucursalEntity).iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
				
		Optional<SuscriptorEntity> suscriptorExistente = suscriptoresSucursal
				.stream()
				.filter(s -> clienteOldEntity.getNom_cliente().trim()
							.replace(" ", "")
							.concat(clienteOldEntity.getApe_cliente().trim()
							.replace(" ", ""))
							.equals(
									s.getNombre().trim()
									.replace(" ", "")
									.concat(s.getApellidoPaterno().trim().replace(" ", ""))
									.concat(s.getApellidoMaterno().trim().replace(" ", ""))))
				.findFirst();
		
		/*if(clienteOldEntity.getNom_cliente().contains("VIANKA") ||
				clienteOldEntity.getNom_cliente().contains("ARACELY") ||
				clienteOldEntity.getNom_cliente().contains("MARCO ANTONIO") ||
				clienteOldEntity.getNom_cliente().contains("MARÍA FRANCISCA") ||
				clienteOldEntity.getNom_cliente().contains("ROSARIO")) {
			log.info("pausar");
		}*/
		
		if(clienteOldEntity.getNom_cliente().contains("JOSHUA")) {
			log.info("pausar");
		}
		
		if(suscriptorExistente.isPresent()) {
			//flujo para suscriptores que ya estan registrados en TVCH
			
			StringBuilder sb = new StringBuilder();
			sb.append("Actualizando informacion de suscriptor ").append(suscriptorExistente.get().getId()).append(" -> ");
			sb.append(suscriptorExistente.get().getNombre()).append(" ").append(suscriptorExistente.get().getApellidoPaterno());
			sb.append(" ").append(suscriptorExistente.get().getApellidoMaterno());
			log.info(sb.toString());
			
			//con el suscriptor encontrado, generar el contrato con el suscriptor encontrado

			//en este caso solo se registra el nuevo contrato
			ContratoEntity nuevoContrato = crearContrato(clienteOldEntity, usuarioEntity, sucursalEntity);
			contratoRepository.save(nuevoContrato);
			
			//crear registro de contratos por suscriptor
			ContratosxSuscriptorEntity contratosxSuscriptorEntity = crearContratoxSuscriptor(nuevoContrato, suscriptorExistente.get());
			contratosxSuscriptorRepository.save(contratosxSuscriptorEntity);
			
			//si se requiere crear servicio y servicio x contrato
			validaryCrearServicio(sucursalEntity, clienteOldEntity, nuevoContrato);			
		
			//crear domicilio
			validaryCrearDomicilio(clienteOldEntity, nuevoContrato, sucursalEntity);
			
			//crear onu
			validaryCrearOnu(clienteOldEntity, nuevoContrato, sucursalEntity, usuarioEntity);
				
			contadorNuevosContratos = contadorNuevosContratos +1;				
			
			
		}else {
			
			//flujo para clientes nuevos que se van a registrar en TVCH
			
			StringBuilder sb = new StringBuilder();
			sb.append("Registrando nuevo suscriptor ").append(clienteOldEntity.getNom_cliente()).append(" ");
			sb.append(clienteOldEntity.getApe_cliente()).append(" con numero de contrato ").append(clienteOldEntity.getNum_contrato());
			log.info(sb.toString());
			
			//crear registro de contrato
			ContratoEntity nuevoContrato = crearContrato(clienteOldEntity, usuarioEntity, sucursalEntity);
			contratoRepository.save(nuevoContrato);
			
			//crear registro de suscriptor
			SuscriptorEntity nuevoSuscriptor = crearSuscriptor(clienteOldEntity, sucursalEntity, usuarioEntity, nuevoContrato);
			suscriptorRepository.save(nuevoSuscriptor);
			
			//crear registro de contratos por suscriptor
			ContratosxSuscriptorEntity contratosxSuscriptorEntity = crearContratoxSuscriptor(nuevoContrato, nuevoSuscriptor);
			contratosxSuscriptorRepository.save(contratosxSuscriptorEntity);
			
			//si se requiere crear servicio y servicio x contrato
			validaryCrearServicio(sucursalEntity, clienteOldEntity, nuevoContrato);			

			//crear domicilio
			validaryCrearDomicilio(clienteOldEntity, nuevoContrato, sucursalEntity);
			
			//crear onu
			validaryCrearOnu(clienteOldEntity, nuevoContrato, sucursalEntity, usuarioEntity);
			
			contadorNuevosSuscriptores = contadorNuevosSuscriptores + 1;
			contadorNuevosContratos = contadorNuevosContratos + 1;
		}
		
		contadorClientes = contadorClientes + 1;
	}
	
	private void validaryCrearOnu(ClienteOldEntity clienteEntity, ContratoEntity contratoEntity, SucursalEntity sucursalEntity, UsuarioEntity usuarioEntity) {
		
		if(clienteEntity.getOnu() != null && !clienteEntity.getOnu().isEmpty() && clienteEntity.getOnu().length() <= 50) {
			
			OnuEntity onuEntity = new OnuEntity();
			onuEntity.setEstatus(estatusOnuEntity);
			//onuEntity.setFechaRegistro(null);
			onuEntity.setIdSucursal(util.generarIdSucursal(sucursalId));
			onuEntity.setSerie(clienteEntity.getOnu());
			onuEntity.setSucursal(sucursalEntity);
			onuEntity.setUsuario(usuarioEntity);
			onuRepository.save(onuEntity);
			
			contratoEntity.setOnu(onuEntity);
			contratoRepository.save(contratoEntity);
			
		}
		
	}
	
	private void validaryCrearDomicilio(ClienteOldEntity clienteEntity, ContratoEntity contratoEntity, SucursalEntity sucursalEntity) {
		
		DomicilioEntity domicilioEntity = new DomicilioEntity();
		domicilioEntity.setIdSucursal(util.generarIdSucursal(sucursalId));
		domicilioEntity.setCalle(clienteEntity.getCal_cliente());
		domicilioEntity.setColonia(clienteEntity.getCol_cliente());
		domicilioEntity.setEstatus(1); //activo
		domicilioEntity.setNumeroCalle(clienteEntity.getNum_cliente());
		domicilioEntity.setReferencia(clienteEntity.getObs_cliente());
		domicilioRepository.save(domicilioEntity);
		
		DomiciliosxContratoEntity domiciliosxContratoEntity = new DomiciliosxContratoEntity();
		domiciliosxContratoEntity.setContrato(contratoEntity);
		domiciliosxContratoEntity.setDomicilio(domicilioEntity);
		domiciliosxContratoRepository.save(domiciliosxContratoEntity);
		
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
			serviciosxContratoRepository.save(serviciosxContratoEntity);
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
			servicioRepository.save(entity);
			
			ServiciosxContratoEntity serviciosxContratoEntity = new ServiciosxContratoEntity();
			serviciosxContratoEntity.setContrato(contratoEntity);
			serviciosxContratoEntity.setEstatus(1);
			serviciosxContratoEntity.setServicio(entity);
			serviciosxContratoRepository.save(serviciosxContratoEntity);
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
			UsuarioEntity usuarioEntity,
			SucursalEntity sucursalEntity) throws Exception{
		
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
		/*List<ReporteOldEntity> reportesCliente = 
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
		}*/
		
		//recuperar el numero de tvs del campo de los comentarios
		tvsContratadas = util.obtenerNumeroTvs(clienteEntity.getCom_cliente());
		
		ContratoEntity entity = new ContratoEntity();
		
		Date fechaProximoPago = formatoFecha.parse(obtenerFechaPago(clienteEntity));
		entity.setIdSucursal(util.generarIdSucursal(sucursalId));
		entity.setFechaProximoPago(fechaProximoPago);
		entity.setEstatus(estatusContratoEntity);
		Date fechaRegistro = formatoFecha.parse(obtenerFechIngreso(clienteEntity));
		entity.setFechaRegistro(fechaRegistro);
		entity.setFolioContrato(clienteEntity.getNum_contrato());
		entity.setTvsContratadas(tvsContratadas);
		entity.setUsuario(usuarioEntity);
		entity.setFolioPlaca(clienteEntity.getNum_contrato());
		entity.setColorPlaca("");
		entity.setDiaPrimerPago(sucursalEntity.getDiaCorte());
		
		Calendar fechaPagoCal = Calendar.getInstance();
		fechaPagoCal.setTime(fechaRegistro);
		fechaPagoCal.add(Calendar.MONTH, 1); //sumar un mes
		entity.setMesPrimerPago(fechaPagoCal.get(Calendar.MONTH)+1);
		entity.setAnioPrimerPago(fechaPagoCal.get(Calendar.YEAR));
		
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
			List<String> apellidosSinEspacios = Arrays.asList(apellidos).stream().filter(e -> !e.isBlank()).collect(Collectors.toList());
			if(apellidosSinEspacios.size() > 0) {
				//if(apellidosSinEspacios.size() > 1) {
					apellidoPaterno = apellidosSinEspacios.get(0);
					boolean esPaterno = true;
					for(String ap : apellidosSinEspacios) {
						if(!esPaterno) {
							if(apellidoMaterno.isBlank())
								apellidoMaterno = ap;
							else
								apellidoMaterno = apellidoMaterno.concat(" ").concat(ap);
						}
						esPaterno = false;
					}	
				//}
			}//else {
				//apellidoPaterno = clienteEntity.getApe_cliente();
			//}
		}
		
		EstatusSuscriptorEntity estatusSuscriptorEntity = null;
		if(contratoEntity.getEstatus().getDescripcion().contains("CANCELADO")) {
			estatusSuscriptorEntity = estatusSuscriptorRepository.findById(Constantes.ESTATUS_SUSCRIPTOR_INACTIVO).get();
		}else {
			estatusSuscriptorEntity = estatusSuscriptorRepository.findById(Constantes.ESTATUS_SUSCRIPTOR_ACTIVO).get();
		}
		
		SuscriptorEntity entity = new SuscriptorEntity();
		entity.setIdSucursal(util.generarIdSucursal(sucursalId));
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
