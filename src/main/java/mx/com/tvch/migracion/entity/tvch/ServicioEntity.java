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
@Table(name = "servicios")
public class ServicioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servicio")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "costo")
	private Double costo;
	
	@Column(name = "costo_instalacion")
	private Double costoInstalacion;
	
	@Column(name = "estatus")
	private Integer estatus;
	
	@ManyToOne
	@JoinColumn(name = "id_zona", referencedColumnName = "id_zona")
	private ZonaEntity zona;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_servicio", referencedColumnName = "id_tipo_servicio")
	private TipoServicioEntity tipoServicio;

}
