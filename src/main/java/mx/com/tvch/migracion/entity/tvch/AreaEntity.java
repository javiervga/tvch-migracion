package mx.com.tvch.migracion.entity.tvch;

import java.util.Date;
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
@Table(name = "areas")
public class AreaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_area")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@Column(name = "estatus")
	private Integer estatus;
	
	@ManyToOne
	@JoinColumn(name = "id_oficina", referencedColumnName = "id_oficina")
	private OficinaEntity oficina;

}
