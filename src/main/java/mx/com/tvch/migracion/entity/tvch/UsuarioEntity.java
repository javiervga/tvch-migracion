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
@Table(name = "usuarios")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(name = "usuario")
	private String usuario;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "ap_paterno")
	private String apellidoPaterno;
	
	@Column(name = "ap_materno")
	private String apellidoMaterno;
	
	@Column(name = "curp")
	private String curp;
	
	@Column(name = "rfc")
	private String rfc;
	
	@Column(name = "idmex")
	private String idmex;
	
	@Column(name = "lugar_nacimiento")
	private String lugarNacimiento;
	
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "fecha_ingreso")
	private Date fechaIngreso;
	
	@ManyToOne
	@JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
	private EstatusUsuarioEntity estatus;
	
	@ManyToOne
	@JoinColumn(name = "id_puesto", referencedColumnName = "id_puesto")
	private PuestoEntity puesto;
	
	@ManyToOne
	@JoinColumn(name = "id_area", referencedColumnName = "id_area")
	private AreaEntity area;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "pwd_reset")
	private Integer resetPassword;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@Column(name = "limitado")
	private Integer esLimitado;
	
	
}
