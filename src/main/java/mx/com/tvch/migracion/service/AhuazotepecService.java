package mx.com.tvch.migracion.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.com.tvch.migracion.Constantes;
import mx.com.tvch.migracion.entity.ahuazotepec.ClienteAhuazotepecEntity;
import mx.com.tvch.migracion.entity.tvch.ContratoEntity;
import mx.com.tvch.migracion.entity.tvch.ContratosxSuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.EstatusContratoEntity;
import mx.com.tvch.migracion.entity.tvch.EstatusSuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.ServicioEntity;
import mx.com.tvch.migracion.entity.tvch.ServiciosxContratoEntity;
import mx.com.tvch.migracion.entity.tvch.SucursalEntity;
import mx.com.tvch.migracion.entity.tvch.SuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.UsuarioEntity;
import mx.com.tvch.migracion.repository.ahuazotepec.ClienteAhuazotepecRepository;
import mx.com.tvch.migracion.repository.ahuazotepec.ReporteAhuazotepecRepository;
import mx.com.tvch.migracion.repository.tvch.ContratoRepository;
import mx.com.tvch.migracion.repository.tvch.ContratosxSuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.EstatusContratoRepository;
import mx.com.tvch.migracion.repository.tvch.EstatusSuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.ServicioRepository;
import mx.com.tvch.migracion.repository.tvch.ServiciosxContratoRepository;
import mx.com.tvch.migracion.repository.tvch.SucursalRepository;
import mx.com.tvch.migracion.repository.tvch.SuscriptorRepository;
import mx.com.tvch.migracion.repository.tvch.UsuarioRepository;

@Slf4j
@Service
public class AhuazotepecService implements MIgracionSucursalService{
	
	private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 

	@Autowired
	private ClienteAhuazotepecRepository clienteRepository;
	
	@Autowired
	private ReporteAhuazotepecRepository reporteRepository;
	
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
		
		//Paso 3 -> obtener de la BD TVCH el entity del estatus de suscriptor
		//EstatusSuscriptorEntity estatusSuscriptorEntity = estatusSuscriptorRepository.findById(Constantes.ESTATUS_SUSCRIPTOR_ACTIVO).orElseThrow(()->new Exception("No se encontro el estatus de suscriptor en TVCH"));

		//Paso 4 -> obtener de la BD TVCH el entity del usuario con que se van a registrar todo
		UsuarioEntity usuarioEntity = usuarioRepository.findById(2L).orElseThrow(()->new Exception("Usuario Id 2 no encontrado en TVCH"));
				
		//Paso 5 -> recuperar todos los clientes de la base anterior
		List<ClienteAhuazotepecEntity> clientes = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(clienteRepository.findAll().iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		
		//Paso 6 -> procesar cada cliente y registrarlo en la nuev BD como suscriptor
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
			ClienteAhuazotepecEntity entity, 
			SucursalEntity sucursalEntity, 
			//EstatusSuscriptorEntity estatusSuscriptorEntity,
			UsuarioEntity usuarioEntity) 
					throws Exception{
		
		//Paso 1 -> validar si ya existe un cliente con el mismo contrato para que no se repita
		List<SuscriptorEntity> suscriptores = 
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(suscriptorRepository.findBySucursal(sucursalEntity).iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.toList());
		Optional<SuscriptorEntity> suscriptorExistente = suscriptores.stream()
				.filter(s -> entity.getNom_cliente().trim().concat(entity.getApe_cliente().trim()).equals(
				s.getNombre().trim().concat(s.getApellidoPaterno().trim()).concat(s.getApellidoMaterno().trim())))
				.findFirst();
		
		if(suscriptorExistente.isPresent()) {
			//flujo para clientes que ya estan registrados en TVCH
			
			
		}else {
			//flujo para clientes que se van a registrar en TVCH
			
			//crear registro de contrato
			ContratoEntity nuevoContrato = crearContrato(entity, usuarioEntity);
			contratoRepository.save(nuevoContrato);
			//crear registro de suscriptor
			SuscriptorEntity nuevoSuscriptor = crearSuscriptor(entity, sucursalEntity, usuarioEntity, nuevoContrato);
			suscriptorRepository.save(nuevoSuscriptor);
			//crear registro de contratos por suscriptor
			ContratosxSuscriptorEntity contratosxSuscriptorEntity = crearContratoxSuscriptor(nuevoContrato, nuevoSuscriptor);
			contratosxSuscriptorRepository.save(contratosxSuscriptorEntity);
			//crear servicio y servicio x contrato
			validaryCrearServicio(sucursalEntity, entity, nuevoContrato);			
			//crear terminal
			
			//crear terminal x contrato
			
			//crear domicilio
			
			//crear domiciio x contrato
			
		}
		
		
	}
	
	private void validaryCrearServicio(SucursalEntity sucursalEntity, ClienteAhuazotepecEntity clienteEntity, ContratoEntity contratoEntity) {
		
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
			ServicioEntity entity = new ServicioEntity();
			entity.setCosto(null);
			entity.setDescripcion(null);
			entity.setEstatus(null);
			entity.setNombre(null);
			entity.setZona(null);
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
			ClienteAhuazotepecEntity clienteEntity,
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
			ClienteAhuazotepecEntity clienteEntity,
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
	private String obtenerFechIngreso(ClienteAhuazotepecEntity clienteEntity) {
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
