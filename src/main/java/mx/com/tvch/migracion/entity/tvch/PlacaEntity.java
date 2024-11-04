package mx.com.tvch.migracion.entity.tvch;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "placas")
public class PlacaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_placa")
	private Long id;
	
	@Column(name = "folio")
	private String folio;
	
	@Column(name = "color")
	private String color;
	
	@ManyToOne
	@JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
	private EstatusPlacaEntity estatus;

}
