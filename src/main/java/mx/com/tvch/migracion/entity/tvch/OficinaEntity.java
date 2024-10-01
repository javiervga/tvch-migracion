package mx.com.tvch.migracion.entity.tvch;

import java.util.Date;
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
@Table(name = "oficinas")
public class OficinaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_oficina")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "fecha_registro")
	private Date fecha_registro;
	
	@Column(name = "estatus")
	private Integer estatus;

}
