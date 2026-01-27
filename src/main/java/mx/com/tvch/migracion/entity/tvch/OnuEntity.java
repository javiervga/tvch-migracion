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
@Table(name = "onus")
public class OnuEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_onu")
	private Long id;
	
	@Column(name = "id_onu_sucursal")
	private Long idSucursal;
	
	@Column(name = "serie")
	private String serie;
	
	@ManyToOne
	@JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus_onu")
	private EstatusOnuEntity estatus;
	
	@ManyToOne
	@JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
	private SucursalEntity sucursal;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	private UsuarioEntity usuario;

}
