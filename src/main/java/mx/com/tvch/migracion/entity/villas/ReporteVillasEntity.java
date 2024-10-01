package mx.com.tvch.migracion.entity.villas;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "reporte")
public class ReporteVillasEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "folio")
	private Integer folio;
	
	@Column(name = "contrato")
	private String contrato;
	
	@Column(name = "sn")
	private String sn;
	
	@Column(name = "ip")
	private String ip;
	
	@Column(name = "vlan")
	private String vlan;
	
	@Column(name = "nap")
	private String nap;  

}
