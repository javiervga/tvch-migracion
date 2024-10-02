package mx.com.tvch.migracion.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.com.tvch.migracion.Constantes;
import mx.com.tvch.migracion.entity.old.ClienteOldEntity;
import mx.com.tvch.migracion.entity.old.CostosOldEntity;
import mx.com.tvch.migracion.entity.old.ReporteOldEntity;
import mx.com.tvch.migracion.entity.tvch.ContratoEntity;
import mx.com.tvch.migracion.entity.tvch.ContratosxSuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.DomicilioEntity;
import mx.com.tvch.migracion.entity.tvch.DomiciliosxContratoEntity;
import mx.com.tvch.migracion.entity.tvch.EstatusContratoEntity;
import mx.com.tvch.migracion.entity.tvch.EstatusSuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.EstatusTerminalEntity;
import mx.com.tvch.migracion.entity.tvch.ServicioEntity;
import mx.com.tvch.migracion.entity.tvch.ServiciosxContratoEntity;
import mx.com.tvch.migracion.entity.tvch.SucursalEntity;
import mx.com.tvch.migracion.entity.tvch.SuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.TerminalEntity;
import mx.com.tvch.migracion.entity.tvch.TerminalesxContratoEntity;
import mx.com.tvch.migracion.entity.tvch.TipoTerminalEntity;
import mx.com.tvch.migracion.entity.tvch.UsuarioEntity;
import mx.com.tvch.migracion.repository.old.ClienteOldRepository;
import mx.com.tvch.migracion.repository.old.CostosOldRepository;
import mx.com.tvch.migracion.repository.old.ReporteOldRepository;
import mx.com.tvch.migracion.repository.tvch.ContratoRepository;
import mx.com.tvch.migracion.repository.tvch.ContratosxSuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.DomicilioRepository;
import mx.com.tvch.migracion.repository.tvch.DomiciliosxContratoRepository;
import mx.com.tvch.migracion.repository.tvch.EstatusContratoRepository;
import mx.com.tvch.migracion.repository.tvch.EstatusSuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.EstatusTerminalRepository;
import mx.com.tvch.migracion.repository.tvch.ServicioRepository;
import mx.com.tvch.migracion.repository.tvch.ServiciosxContratoRepository;
import mx.com.tvch.migracion.repository.tvch.SucursalRepository;
import mx.com.tvch.migracion.repository.tvch.SuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.TerminalRepository;
import mx.com.tvch.migracion.repository.tvch.TerminalesxContratoRepository;
import mx.com.tvch.migracion.repository.tvch.TipoTerminalRepository;
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
	private ContratoRepository contratoRepository;
	
	@Autowired
	private ServicioRepository servicioRepository;
	
	@Autowired
	private ContratosxSuscriptorRepository contratosxSuscriptorRepository;
	
	@Autowired
	private ServiciosxContratoRepository serviciosxContratoRepository;
	
	@Autowired
	private CostosOldRepository costosRepository;
	
	@Autowired
	private EstatusTerminalRepository estatusTerminalRepository;
	
	@Autowired
	private TipoTerminalRepository tipoTerminalRepository;
	
	@Autowired
	private TerminalRepository terminalRepository;
	
	@Autowired
	private TerminalesxContratoRepository terminalesxContratoRepository;
	
	@Autowired
	private DomicilioRepository domicilioRepository;

	@Autowired
	private DomiciliosxContratoRepository domiciliosxContratoRepository;
	
	@Override
	public long count() throws Exception {
		// TODO Auto-generated method stub
		return clienteRepository.count();
	}

	@Override
	public void migrarSucursal() throws Exception {
		// TODO Auto-generated method stub
		
		//Paso 1 -> Obtener el registro de la sucursal de la nueva BD
		SucursalEntity sucursalEntity = sucursalRepository.findById(Constantes.AHUAZOTEPEC).orElseThrow(()->new Exception("Sucursal No encontrada en BD TVCH"));
		
		//Paso 2 -> recuperar todos los suscriptores de la nueva base y validar para asegurarnos que no dupliquemos
		List<SuscriptorEntity> suscriptoresExistentes = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(suscriptorRepository.findAll().iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		
		if(suscriptoresExistentes.stream().anyMatch(s -> s.getSucursal().equals(Constantes.AHUAZOTEPEC))) {
			throw new Exception("Ya existen clientes registrados con la sucursal solicitada");
		}
		
		//Paso 3 -> obtener de la BD TVCH el entity del usuario con que se van a registrar todo
		UsuarioEntity usuarioEntity = usuarioRepository.findById(2L).orElseThrow(()->new Exception("Usuario Id 2 no encontrado en TVCH"));
				
		//Paso 4 -> recuperar todos los clientes de la base anterior
		List<ClienteOldEntity> clientes = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(clienteRepository.findAll().iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		
		//Paso 5 -> procesar cada cliente y realizar los registros necesarios en BD TVCH
		clientes.forEach(c -> {
			try {
				migrarCliente(c,sucursalEntity,usuarioEntity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Error al procesar cliente: "+c.toString());
			}
		});
		
	}
	
	private void migrarCliente(
			ClienteOldEntity clienteOldEntity, 
			SucursalEntity sucursalEntity, 
			UsuarioEntity usuarioEntity) 
					throws Exception{
		
		if(clienteOldEntity.getNum_contrato() == 187 || clienteOldEntity.getNum_contrato() == 188) {
			System.out.println("aca");
		}
		
		//Paso 1 -> validar si ya existe un cliente con el mismo contrato para que no se repita
		List<SuscriptorEntity> suscriptores = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(suscriptorRepository.findBySucursal(sucursalEntity).iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		Optional<SuscriptorEntity> suscriptorExistente = suscriptores.stream()
				.filter(s -> clienteOldEntity.getNom_cliente().trim().concat(clienteOldEntity.getApe_cliente().trim()).equals(
				s.getNombre().trim().concat(s.getApellidoPaterno().trim()).concat(" ").concat(s.getApellidoMaterno().trim())))
				.findFirst();
		
		if(suscriptorExistente.isPresent()) {
			//flujo para clientes que ya estan registrados en TVCH
			
			//validar si el contrato es diferente al q ya tiene registrado 
			List<ContratosxSuscriptorEntity> contratosxSuscriptorEntities = 
					StreamSupport.stream(Spliterators.spliteratorUnknownSize(contratosxSuscriptorRepository.findBySuscriptor(suscriptorExistente.get()).iterator(), Spliterator.ORDERED), false)
					.collect(Collectors.toList());
			if(contratosxSuscriptorEntities.stream().anyMatch(
					cs -> cs.getContrato().getIdContratoAnterior().longValue() == clienteOldEntity.getNum_contrato().longValue())) {
				//ya existe un suscriptor con el mismo numero de contrato anterior
				//en este caso no se hace nada porque se entiende que esta duplicado el registro
				log.info("El cliente con contrato: "+clienteOldEntity.getNum_contrato()+" ya ha sido registrado anteriormente como suscriptor: "+suscriptorExistente.get().getId());			
			}else {
				//el numero de contrato anterior es diferente, quiere decir que el cliente tenia mas de un contrato en la antigua base
				//en este caso solo se registra el nuevo contrato
				ContratoEntity nuevoContrato = crearContrato(clienteOldEntity, usuarioEntity);
				contratoRepository.save(nuevoContrato);
				//crear registro de contratos por suscriptor
				ContratosxSuscriptorEntity contratosxSuscriptorEntity = crearContratoxSuscriptor(nuevoContrato, suscriptorExistente.get());
				contratosxSuscriptorRepository.save(contratosxSuscriptorEntity);
				//si se requiere crear servicio y servicio x contrato
				validaryCrearServicio(sucursalEntity, clienteOldEntity, nuevoContrato);			
				//si se requiere crear terminal y terminal x contrato
				validaryCrearTerminal(usuarioEntity, nuevoContrato);			
				//crear domicilio
				validaryCrearDomicilio(clienteOldEntity, nuevoContrato);
			}
			
			
		}else {
			//flujo para clientes que se van a registrar en TVCH
			
			//crear registro de contrato
			ContratoEntity nuevoContrato = crearContrato(clienteOldEntity, usuarioEntity);
			contratoRepository.save(nuevoContrato);
			//crear registro de suscriptor
			SuscriptorEntity nuevoSuscriptor = crearSuscriptor(clienteOldEntity, sucursalEntity, usuarioEntity, nuevoContrato);
			suscriptorRepository.save(nuevoSuscriptor);
			//crear registro de contratos por suscriptor
			ContratosxSuscriptorEntity contratosxSuscriptorEntity = crearContratoxSuscriptor(nuevoContrato, nuevoSuscriptor);
			contratosxSuscriptorRepository.save(contratosxSuscriptorEntity);
			//si se requiere crear servicio y servicio x contrato
			validaryCrearServicio(sucursalEntity, clienteOldEntity, nuevoContrato);			
			//si se requiere crear terminal y terminal x contrato
			validaryCrearTerminal(usuarioEntity, nuevoContrato);			
			//crear domicilio
			validaryCrearDomicilio(clienteOldEntity, nuevoContrato);
			
		}
		
		
	}
	
	private void validaryCrearDomicilio(ClienteOldEntity clienteEntity, ContratoEntity contratoEntity) {
		
		DomicilioEntity domicilioEntity = new DomicilioEntity();
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
	 * @param usuarioEntity
	 * @param contratoEntity
	 */
	private void validaryCrearTerminal(UsuarioEntity usuarioEntity, ContratoEntity contratoEntity) {
		
		EstatusTerminalEntity estatusTerminalEntity = estatusTerminalRepository.findById(Constantes.ESTATUS_TERMINAL_ACTIVO).get();
		TipoTerminalEntity tipoTerminalEntity = tipoTerminalRepository.findById(Constantes.TIPO_TERMINAL_ONT).get();
		
		TerminalEntity terminal = new TerminalEntity();
		
		//recuperar el ultimo registro de la tabla reporte con el numero de contrato anterior
		List<ReporteOldEntity> reportesCliente = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(reporteRepository.findByContrato(String.valueOf(contratoEntity.getIdContratoAnterior())).iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		ReporteOldEntity reporteExistente = null;
		if(!reportesCliente.isEmpty()) {
			reporteExistente = reportesCliente.getLast();
		}
		
		if(reporteExistente != null) {
			terminal.setIp(reporteExistente.getIp());
			terminal.setNap(reporteExistente.getNap());
			terminal.setSerie(reporteExistente.getSn());
			terminal.setVlan(reporteExistente.getVlan());
			
			terminal.setEstatus(estatusTerminalEntity);
			terminal.setFechaRegistro(new Date());
			terminal.setTipo(tipoTerminalEntity);
			terminal.setUsuario(usuarioEntity);
			terminalRepository.save(terminal);
			
			TerminalesxContratoEntity terminalesxContratoEntity = new TerminalesxContratoEntity();
			terminalesxContratoEntity.setContrato(contratoEntity);
			terminalesxContratoEntity.setTerminal(terminal);
			terminalesxContratoEntity.setEstatus(1); //activo
			terminalesxContratoRepository.save(terminalesxContratoEntity);
		}
		
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
			Optional<CostosOldEntity> costoOld = costosRepository.findByServicio(clienteEntity.getSer_cliente());
			if(costoOld.isPresent() && !costoOld.get().getCosto().isBlank()) {
				try {
					costo = Double.parseDouble(costoOld.get().getCosto());
				}catch(Exception e) {
					
				}
			}
			
			ServicioEntity entity = new ServicioEntity();
			entity.setCosto(costo);
			entity.setDescripcion(clienteEntity.getSer_cliente());
			entity.setEstatus(1);//activo
			entity.setNombre(clienteEntity.getSer_cliente());
			entity.setZona(sucursalEntity.getZona());
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
		
		ContratoEntity entity = new ContratoEntity();
		entity.setDiaCorte(null);
		entity.setDiaPago(null);
		entity.setEstatus(estatusContratoEntity);
		entity.setFechaRegistro(formatoFecha.parse(obtenerFechIngreso(clienteEntity)));
		entity.setIdContratoAnterior(clienteEntity.getNum_contrato());
		entity.setTvsContratadas(null);
		entity.setUsuario(usuarioEntity);
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

}
