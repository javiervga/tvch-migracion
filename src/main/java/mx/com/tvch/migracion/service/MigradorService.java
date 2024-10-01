package mx.com.tvch.migracion.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.tvch.migracion.Constantes;
import mx.com.tvch.migracion.entity.providencia.ClienteProvidenciaEntity;
import mx.com.tvch.migracion.entity.tvch.EstatusClienteEntity;
import mx.com.tvch.migracion.entity.tvch.ServicioEntity;
import mx.com.tvch.migracion.entity.tvch.SucursalEntity;
import mx.com.tvch.migracion.entity.tvch.SuscriptorEntity;
import mx.com.tvch.migracion.entity.tvch.UsuarioEntity;
import mx.com.tvch.migracion.entity.tvch.ZonaEntity;
import mx.com.tvch.migracion.repository.ahuazotepec.ClienteAhuazotepecRepository;
import mx.com.tvch.migracion.repository.almoloya.ClienteAlmoloyaRepository;
import mx.com.tvch.migracion.repository.apan.ClienteApanRepository;
import mx.com.tvch.migracion.repository.atotonilco.ClienteAtotonilcoRepository;
import mx.com.tvch.migracion.repository.avila.ClienteAvilaRepository;
import mx.com.tvch.migracion.repository.campestre.ClienteCampestreRepository;
import mx.com.tvch.migracion.repository.chacon.ClienteChaconRepository;
import mx.com.tvch.migracion.repository.chimalpa.ClienteChimalpaRepository;
import mx.com.tvch.migracion.repository.emiliano.ClienteEmilianoRepository;
import mx.com.tvch.migracion.repository.epazoyucan.ClienteEpazoyucanRepository;
import mx.com.tvch.migracion.repository.irolo.ClienteIroloRepository;
import mx.com.tvch.migracion.repository.lazaro.ClienteLazaroRepository;
import mx.com.tvch.migracion.repository.nopancalco.ClienteNopancalcoRepository;
import mx.com.tvch.migracion.repository.omitlan.ClienteOmitlanRepository;
import mx.com.tvch.migracion.repository.oncejulio.ClienteOnceJulioRepository;
import mx.com.tvch.migracion.repository.pachuquilla.ClientePachuquillaRepository;
import mx.com.tvch.migracion.repository.piracantos.ClientePiracantosRepository;
import mx.com.tvch.migracion.repository.providencia.ClienteProvidenciaRepository;
import mx.com.tvch.migracion.repository.real.ClienteRealRepository;
import mx.com.tvch.migracion.repository.sahagun.ClienteSahagunRepository;
import mx.com.tvch.migracion.repository.sancayetano.ClienteSanCayetanoRepository;
import mx.com.tvch.migracion.repository.sanjuan.ClienteSanJuanRepository;
import mx.com.tvch.migracion.repository.santaclara.ClienteSantaClaraRepository;
import mx.com.tvch.migracion.repository.santajulia.ClienteSantaJuliaRepository;
import mx.com.tvch.migracion.repository.tepeapulco.ClienteTepeapulcoRepository;
import mx.com.tvch.migracion.repository.tepeyahualco.ClienteTepeyahualcoRepository;
import mx.com.tvch.migracion.repository.tlanalapa.ClienteTlanalapaRepository;
import mx.com.tvch.migracion.repository.tuxpan.ClienteTuxpanRepository;
import mx.com.tvch.migracion.repository.tuzos.ClienteTuzosRepository;
import mx.com.tvch.migracion.repository.tvch.ClienteRepository;
import mx.com.tvch.migracion.repository.tvch.EstatusClienteRepository;
import mx.com.tvch.migracion.repository.tvch.ServicioRepository;
import mx.com.tvch.migracion.repository.tvch.SucursalRepository;
import mx.com.tvch.migracion.repository.tvch.UsuarioRepository;
import mx.com.tvch.migracion.repository.tvch.ZonaRepository;
import mx.com.tvch.migracion.repository.union.ClienteUnionRepository;
import mx.com.tvch.migracion.repository.veintenoviembre.ClienteVeinteNoviembreRepository;
import mx.com.tvch.migracion.repository.villas.ClienteVillasRepository;
import mx.com.tvch.migracion.repository.xochihuacan.ClienteXochihuacanRepository;
import mx.com.tvch.migracion.util.Utilerias;

@Service
public class MigradorService {
	
	private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
	
	@Autowired
	private Utilerias util;
	
	//@Autowired
	//private ClienteProvidenciaRepository providenciaRepository;
	
	//@Autowired
	//private ClientePachuquillaRepository pachuquillaRepository;
	
	//@Autowired
	//private ClienteChaconRepository chaconRepository;
	
	//@Autowired
	//private ClienteTuzosRepository tuzosRepository;
	
	//@Autowired
	//private ClienteAvilaRepository avilaRepository;
	
	//@Autowired
	//private ClienteXochihuacanRepository xochihuacanRepository;
	
	//@Autowired
	//private ClienteCampestreRepository campestreRepository;
	
	//@Autowired
	//private ClienteVillasRepository villasRepository;
	
	//@Autowired
	//private ClienteOnceJulioRepository onceJulioRepository;
	
	//@Autowired
	//private ClienteVeinteNoviembreRepository veinteNoviembreRepository;
	
	//@Autowired
	//private ClienteNopancalcoRepository nopancalcoRepository;
	
	//@Autowired
	//private ClienteSantaJuliaRepository santaJuliaRepository;
	
	//@Autowired
	//private ClientePiracantosRepository piracantosRepository;
	
	//@Autowired
	//private ClienteSanCayetanoRepository sanCayetanoRepository;
	
	//@Autowired
	//private ClienteTepeyahualcoRepository tepeyahualcoRepository;
	
	//@Autowired
	//private ClienteTlanalapaRepository tlanalapaRepository;
	
	//@Autowired
	//private ClienteSahagunRepository sahagunRepository;
	
	//@Autowired
	//private ClienteApanRepository apanRepository;
	
	@Autowired
	private ClienteTepeapulcoRepository tepeapulcoRepository;
	
	@Autowired
	private ClienteChimalpaRepository chimalpaRepository;
	
	@Autowired
	private ClienteLazaroRepository lazaroRepository;
	
	@Autowired
	private ClienteSantaClaraRepository santaClaraRepository;
	
	@Autowired
	private ClienteIroloRepository iroloRepository;
	
	@Autowired
	private ClienteEmilianoRepository emilianoRepository;
	
	@Autowired
	private ClienteAlmoloyaRepository almoloyaRepository;
	
	@Autowired
	private ClienteUnionRepository unionRepository;
	
	@Autowired
	private ClienteRealRepository realRepository;
	
	@Autowired
	private ClienteOmitlanRepository omitlanRepository;
	
	@Autowired
	private ClienteAtotonilcoRepository atotonilcoRepository;
	
	@Autowired
	private ClienteTuxpanRepository tuxpanRepository;
	
	@Autowired
	private ClienteEpazoyucanRepository epazoyucanRepository;
	
	@Autowired
	private ClienteSanJuanRepository sanJuanRepository;
	
	@Autowired
	private ClienteAhuazotepecRepository ahuazotepecRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private ServicioRepository servicioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SucursalRepository sucursalRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EstatusClienteRepository estatusClienteRepository;
	
	
	
	/**
	 * 
	 * @param sucursalId
	 */
	public void migrarSucursal(long sucursalId) throws Exception{
		
		//int zonaId = util.obtenerZonaId(sucursalId);
		ZonaEntity zonaEntity = zonaRepository.findById(
				util.obtenerZonaId(sucursalId)).orElseThrow(() -> new Exception("Zona no encontrada en TVCH BD"));
		SucursalEntity sucursalEntity = sucursalRepository.findById(
				sucursalId).orElseThrow(() -> new Exception("Sucursal no encontrada en TVCH BD"));
		UsuarioEntity usuarioEntity = usuarioRepository.findById(2L).
				orElseThrow(() -> new Exception("Usuario no encontrado en TVCH BD"));;
		
		
		/*if(sucursalId == Constantes.PROVIDENCIA) {
			System.out.println("Clientes Providencia: "+providenciaRepository.count());
		}else if(sucursalId == Constantes.PACHUQUILLA) {
			System.out.println("Clientes Pachuqilla: "+pachuquillaRepository.count());
		}else if(sucursalId == Constantes.CHACON) {
			System.out.println("Clientes Chacon: "+chaconRepository.count());
		}else if(sucursalId == Constantes.TUZOS) {
			System.out.println("Clientes Tuzos: "+tuzosRepository.count());
		}else if(sucursalId == Constantes.AVILA_CAMACHO) {
			System.out.println("Clientes Avila: "+avilaRepository.count());
		}else if(sucursalId == Constantes.XOCHIHUACAN) {
			System.out.println("Clientes Xochihhuacan: "+xochihuacanRepository.count());
		}else if(sucursalId == Constantes.CAMPESTRE) {
			System.out.println("Clientes Campestre: "+campestreRepository.count());
		}else if(sucursalId == Constantes.VILLAS) {
			System.out.println("Clientes Villas: "+villasRepository.count());
		}else if(sucursalId == Constantes.ONCE_DE_JULIO) {
			System.out.println("Clientes Once de Julio: "+onceJulioRepository.count());
		}else if(sucursalId == Constantes.VEINTE_DE_NOVIEMBRE) {
			System.out.println("Clientes 20 Noviembre: "+veinteNoviembreRepository.count());
		}else if(sucursalId == Constantes.NOPANCALCO) {
			System.out.println("Clientes Nopancalco: "+nopancalcoRepository.count());
		}else if(sucursalId == Constantes.SANTA_JULIA) {
			System.out.println("Clientes Santa Julia: "+santaJuliaRepository.count());
		}else if(sucursalId == Constantes.PIRACANTOS) {
			System.out.println("Clientes Piracntos: "+piracantosRepository.count());
		}else if(sucursalId == Constantes.SAN_CAYETANO) {
			System.out.println("Clientes San Cayetano: "+sanCayetanoRepository.count());
		}else if(sucursalId == Constantes.TEPEYAHUALCO) {
			System.out.println("Clientes Tepeyahualco: "+tepeyahualcoRepository.count());
		}else if(sucursalId == Constantes.TLANALAPA) {
			System.out.println("Clientes Tlanalapa: "+tlanalapaRepository.count());
		}else if(sucursalId == Constantes.CD_SAHAGUN) {
			System.out.println("Clientes Shagun: "+sahagunRepository.count());
		}else if(sucursalId == Constantes.APAN) {
			System.out.println("Clientes Apan: "+apanRepository.count());
		}else*/ if(sucursalId == Constantes.TEPEAPULCO) {
			System.out.println("Clientes Tepeapulco: "+tepeapulcoRepository.count());
		}else if(sucursalId == Constantes.CHIMALPA) {
			System.out.println("Clientes Chimalpa: "+chimalpaRepository.count());
		}else if(sucursalId == Constantes.LAZARO_CARDENAS) {
			System.out.println("Clientes Lazaro Cardenas: "+lazaroRepository.count());
		}else if(sucursalId == Constantes.SANTA_CLARA) {
			System.out.println("Clientes Santa Clara: "+santaClaraRepository.count());
		}else if(sucursalId == Constantes.IROLO) {
			System.out.println("Clientes Irolo: "+iroloRepository.count());
		}else if(sucursalId == Constantes.EMILIANO_ZAPATA) {
			System.out.println("Clientes Emiliano Zapata: "+emilianoRepository.count());
		}else if(sucursalId == Constantes.ALMOLOYA) {
			System.out.println("Clientes Almoloya: "+almoloyaRepository.count());
		}else if(sucursalId == Constantes.UNION) {
			System.out.println("Clientes Union: "+unionRepository.count());
		}else if(sucursalId == Constantes.REAL_DEL_MONTE) {
			System.out.println("Clientes Real del MOnte: "+realRepository.count());
		}else if(sucursalId == Constantes.OMITLAN) {
			System.out.println("Clientes Omitlan: "+omitlanRepository.count());
		}else if(sucursalId == Constantes.ATOTONILCO) {
			System.out.println("Clientes Atotonilco: "+atotonilcoRepository.count());
		}else if(sucursalId == Constantes.TUXPAM) {
			System.out.println("Clientes Tuxpan: "+tuxpanRepository.count());
		}else if(sucursalId == Constantes.EPAZOYUCAN) {
			System.out.println("Clientes Epazoyucan: "+epazoyucanRepository.count());
		}else if(sucursalId == Constantes.SAN_JUAN) {
			System.out.println("Clientes San JUan: "+sanJuanRepository.count());
		}else if(sucursalId == Constantes.AHUAZOTEPEC) {
			System.out.println("Clientes Ahuazotepec: "+ahuazotepecRepository.count());
		}				
				
	}
	
	
	/**
	 * 
	 * @param clienteEntity
	 * @param zonaEntity
	 * @param usuarioEntity
	 * @param sucursalEntity
	 */
	private void analizarCliente(ClienteProvidenciaEntity clienteEntity, ZonaEntity zonaEntity, UsuarioEntity usuarioEntity, SucursalEntity sucursalEntity) {
		
		try {
			
			if(clienteEntity != null) {
				
				String nombreServicio = clienteEntity.getSer_cliente().trim();
				
				//validar los servicios existentes, si no existe registrarlo, sino solo recuperarlo
				ServicioEntity servicioEntity = null;
				if(!servicioRepository.findByNombre(nombreServicio).isPresent()) {
					servicioEntity = new ServicioEntity();
					servicioEntity.setCosto(0.0);
					servicioEntity.setDescripcion(nombreServicio);
					servicioEntity.setEstatus(1);
					servicioEntity.setNombre(nombreServicio);
					servicioEntity.setZona(zonaEntity);
					servicioEntity.setId(servicioRepository.save(servicioEntity).getId());
				}else {
					servicioEntity = servicioRepository.findByNombre(nombreServicio).get();
				}
				
				
				//obtener y guardar el nuevo cliente
				SuscriptorEntity nuevoCliente = generarNuevoCliente(clienteEntity, zonaEntity, usuarioEntity, servicioEntity, sucursalEntity);
				clienteRepository.save(nuevoCliente);
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("No se pudo guardar cliente: "+clienteEntity.toString());
		}
		
	}
	
	/**
	 * 
	 * @param clienEntity
	 * @param zonaEntity
	 * @param usuarioEntity
	 * @param servicioEntity
	 * @return
	 */
	private SuscriptorEntity generarNuevoCliente(
			ClienteProvidenciaEntity clienEntity, 
			ZonaEntity zonaEntity, 
			UsuarioEntity usuarioEntity, 
			ServicioEntity servicioEntity,
			SucursalEntity sucursalEntity) throws Exception{
		
		SuscriptorEntity entity = new SuscriptorEntity();
		
		EstatusClienteEntity estatusClienteEntity = null;
		if(estatusClienteRepository.findByDescripcion(clienEntity.getEst_cliente().trim()).isPresent()) {
			estatusClienteEntity = estatusClienteRepository.findByDescripcion(clienEntity.getEst_cliente()).get();
		}else {
			estatusClienteEntity = new EstatusClienteEntity();
			estatusClienteEntity.setDescripcion(clienEntity.getEst_cliente().trim());
			estatusClienteEntity.setIdEstatus(estatusClienteRepository.save(estatusClienteEntity).getIdEstatus());
		}
		
		String apellidoPaterno = "";
		String apellidoMaterno = "";
		if(clienEntity.getApe_cliente() != null && !clienEntity.getApe_cliente().isBlank()) {
			String[] apellidos = clienEntity.getApe_cliente().split(" ");
			if(apellidos.length > 0) {
				if(apellidos.length > 1) {
					apellidoPaterno = apellidos[0];
					apellidoMaterno = apellidos[1];
				}
			}else {
				apellidoPaterno = clienEntity.getApe_cliente();
			}
		}
		
		StringBuilder sbFechaIngreso = new StringBuilder();
		if(clienEntity.getFid_cliente() != null && clienEntity.getFid_cliente() > 0 &&
				clienEntity.getFim_cliente() != null && !clienEntity.getFim_cliente().isBlank() && Long.parseLong(clienEntity.getFim_cliente().trim()) > 0 &&
				clienEntity.getFia_cliente() != null && clienEntity.getFia_cliente() > 0) {
			if(clienEntity.getFid_cliente().longValue() < 10) {
				sbFechaIngreso.append("0").append(clienEntity.getFid_cliente());
			}else {
				sbFechaIngreso.append(clienEntity.getFid_cliente());
			}
			sbFechaIngreso.append("/");
			if(Long.parseLong(clienEntity.getFim_cliente().trim()) < 10) {
				sbFechaIngreso.append("0").append(clienEntity.getFim_cliente().trim());
			}else {
				sbFechaIngreso.append(clienEntity.getFim_cliente().trim());
			}
			sbFechaIngreso.append("/");
			sbFechaIngreso.append(clienEntity.getFia_cliente());
			
		}
		
		entity.setApellidoMaterno(apellidoMaterno);
		entity.setApellidoPaterno(apellidoPaterno);
		//entity.setCalle(clienteOldEntity.getCal_cliente());
		//entity.setColonia(clienteOldEntity.getCol_cliente());
		//entity.setContrato(String.valueOf(clienteOldEntity.getNum_contrato()));
		//entity.setFechaCorte(new Date());
		//entity.setFechaPago(new Date());
		entity.setFechaRegistro(formatoFecha.parse(sbFechaIngreso.toString()));
		entity.setNombre(clienEntity.getNom_cliente());
		//entity.setNumero_calle(clienteOldEntity.getNum_cliente());
		//entity.setReferenciaDomicilio(clienteOldEntity.getObs_cliente());
		//entity.setServicio(servicioEntity);
		entity.setSucursal(sucursalEntity);
		entity.setTelefono(clienEntity.getTel_cliente());
		entity.setUsuario(usuarioEntity);
		//entity.setEstatus(estatusClienteEntity);
		
		return entity;
		
	}
	

}
