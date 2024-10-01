package mx.com.tvch.migracion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SucursalesConfig {
	
	@Bean("sucursalesZonaPuebla")
	List<Long> sucursalesZonaPuebla(){
		List<Long> sucursales = new ArrayList<>();
		Collections.addAll(sucursales, 
				Constantes.AHUAZOTEPEC);
		return sucursales;
	}
	
	@Bean("sucursalesZonaEpazoyucan")
	List<Long> sucursalesZonaEpazoyucan(){
		List<Long> sucursales = new ArrayList<>();
		Collections.addAll(sucursales, 
				Constantes.EPAZOYUCAN,
				Constantes.SAN_JUAN);
		return sucursales;
	}
	
	@Bean("sucursalesZonaTuxpam")
	List<Long> sucursalesZonaTuxpam(){
		List<Long> sucursales = new ArrayList<>();
		Collections.addAll(sucursales, 
				Constantes.TUXPAM);
		return sucursales;
	}
	
	@Bean
	List<Long> sucursalesZonaMineralDelMonte(){
		List<Long> sucursales = new ArrayList<>();
		Collections.addAll(sucursales, 
				Constantes.REAL_DEL_MONTE,
				Constantes.OMITLAN,
				Constantes.ATOTONILCO);
		return sucursales;
	}
	
	@Bean("sucursalesZonaAltiplano")
	List<Long> sucursalesZonaAltiplano(){
		List<Long> sucursales = new ArrayList<>();
		Collections.addAll(sucursales, 
				Constantes.TEPEYAHUALCO,
				Constantes.TLANALAPA,
				Constantes.CD_SAHAGUN,
				Constantes.APAN,
				Constantes.TEPEAPULCO,
				Constantes.CHIMALPA,
				Constantes.LAZARO_CARDENAS,
				Constantes.SANTA_CLARA,
				Constantes.IROLO,
				//Constantes.ACOPINALCO,
				Constantes.EMILIANO_ZAPATA,
				Constantes.ALMOLOYA,
				Constantes.UNION);
		return sucursales;
	}
	
	@Bean("sucursalesZonaPachuca")
	List<Long> sucursalesZonaPachuca(){
		List<Long> sucursales = new ArrayList<>();
		Collections.addAll(sucursales, 
				Constantes.VILLAS,
				Constantes.ONCE_DE_JULIO,
				Constantes.VEINTE_DE_NOVIEMBRE,
				Constantes.NOPANCALCO,
				Constantes.SANTA_JULIA,
				Constantes.PIRACANTOS,
				Constantes.SAN_CAYETANO);
		return sucursales;
	}
	
	@Bean("sucursalesZonaMineral")
	List<Long> sucursalesZonaMineral(){
		List<Long> sucursales = new ArrayList<>();
		Collections.addAll(sucursales, 
				Constantes.PROVIDENCIA,
				Constantes.PACHUQUILLA,
				Constantes.CHACON,
				Constantes.TUZOS,
				Constantes.AVILA_CAMACHO,
				Constantes.XOCHIHUACAN,
				Constantes.CAMPESTRE);
		return sucursales;
	}
	
	@Bean("sucursalesExistentes")
	List<Long> sucursalesExistentes(){
		List<Long> sucursales = new ArrayList<>();
		Collections.addAll(sucursales, 
				Constantes.PROVIDENCIA,
				Constantes.PACHUQUILLA,
				Constantes.CHACON,
				Constantes.TUZOS,
				Constantes.AVILA_CAMACHO,
				Constantes.XOCHIHUACAN,
				Constantes.CAMPESTRE,
				Constantes.VILLAS,
				Constantes.ONCE_DE_JULIO,
				Constantes.VEINTE_DE_NOVIEMBRE,
				Constantes.NOPANCALCO,
				Constantes.SANTA_JULIA,
				Constantes.PIRACANTOS,
				Constantes.SAN_CAYETANO,
				Constantes.TEPEYAHUALCO,
				Constantes.TLANALAPA,
				Constantes.CD_SAHAGUN,
				Constantes.APAN,
				Constantes.TEPEAPULCO,
				Constantes.CHIMALPA,
				Constantes.LAZARO_CARDENAS,
				Constantes.SANTA_CLARA,
				Constantes.IROLO,
				Constantes.ACOPINALCO,
				Constantes.EMILIANO_ZAPATA,
				Constantes.ALMOLOYA,
				Constantes.UNION,
				Constantes.REAL_DEL_MONTE,
				Constantes.OMITLAN,
				Constantes.ATOTONILCO,
				Constantes.TUXPAM,
				Constantes.EPAZOYUCAN,
				Constantes.SAN_JUAN,
				Constantes.AHUAZOTEPEC);
		return sucursales;
	}

}
