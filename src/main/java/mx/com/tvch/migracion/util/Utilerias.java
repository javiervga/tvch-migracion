package mx.com.tvch.migracion.util;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import mx.com.tvch.migracion.Constantes;

@Component
public class Utilerias {
	
	@Autowired
	@Qualifier("sucursalesZonaPuebla")
	private List<Long> sucursalesZonaPuebla;
	
	@Autowired
	@Qualifier("sucursalesZonaEpazoyucan")
	private List<Long> sucursalesZonaEpazoyucan;
	
	@Autowired
	@Qualifier("sucursalesZonaTuxpam")
	private List<Long> sucursalesZonaTuxpam;
	
	@Autowired
	@Qualifier("sucursalesZonaMineralDelMonte")
	private List<Long> sucursalesZonaMineralDelMonte;
	
	@Autowired
	@Qualifier("sucursalesZonaAltiplano")
	private List<Long> sucursalesZonaAltiplano;
	
	@Autowired
	@Qualifier("sucursalesZonaPachuca")
	private List<Long> sucursalesZonaPachuca;
	
	@Autowired
	@Qualifier("sucursalesZonaMineral")
	private List<Long> sucursalesZonaMineral;
	
	public Long obtenerZonaId(Long sucursalId) throws Exception{
		
		if(sucursalesZonaAltiplano.contains(sucursalId))
			return Constantes.ZONA_ALTIPLANO;
		else if(sucursalesZonaEpazoyucan.contains(sucursalId))
			return Constantes.ZONA_EPAZOYUCAN;
		else if(sucursalesZonaTuxpam.contains(sucursalId))
			return Constantes.ZONA_TUXPAM;
		else if(sucursalesZonaMineralDelMonte.contains(sucursalId))
			return Constantes.ZONA_MINERAL_MONTE;
		else if(sucursalesZonaAltiplano.contains(sucursalId))
			return Constantes.ZONA_ALTIPLANO;
		else if(sucursalesZonaPachuca.contains(sucursalId))
			return Constantes.ZONA_PACHUCA;
		else if(sucursalesZonaMineral.contains(sucursalId))
			return Constantes.ZONA_MINERAL_REFORMA;
		else if(sucursalesZonaPuebla.contains(sucursalId))
			return Constantes.ZONA_PUEBLA;
		else
			throw new Exception("Zona no encontrada para sucursal: "+sucursalId);
	
	}
	
	public Integer obtenerNumeroTvs(String cadenaComentarios) {
		
		StringBuilder sb = new StringBuilder();
		Integer tvs = null;
		
		for (int i = 0; i < cadenaComentarios.length(); i++) {
            char caracterActual = cadenaComentarios.charAt(i);
            if (Character.isDigit(caracterActual)) {
            	sb.append(caracterActual);
            }
        }
		
		try {
			tvs = Integer.parseInt(sb.toString());
		}catch(NumberFormatException nfe) {
			
		}
		
		return tvs;
		
	}

}
