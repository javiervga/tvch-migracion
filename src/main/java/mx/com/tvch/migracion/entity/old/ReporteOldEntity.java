/*package mx.com.tvch.migracion.entity.old;

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
public class ReporteOldEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "folio")
	private Integer folio;
	
	@Column(name = "contrato")
	private String contrato;
	
	@Column(name = "tvs")
	private String tvs;
	
}*/
