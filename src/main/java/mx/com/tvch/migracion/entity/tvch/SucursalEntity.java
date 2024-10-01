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
@Table(name = "sucursales")
public class SucursalEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sucursal")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@ManyToOne
	@JoinColumn(name = "id_zona", referencedColumnName = "id_zona")
	private ZonaEntity zona;
	
	@Column(name = "estatus")
	private Integer estatus;

}
