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
@Table(name = "terminales")
public class TerminalEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_terminal")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo_terminal")
	private TipoTerminalEntity tipo;
	
	@Column(name = "serie")
	private String serie;
	
	@Column(name = "ip")
	private String ip;
	
	@Column(name = "vlan")
	private String vlan;
	
	@Column(name = "nap")
	private String nap;
	
	@ManyToOne
	@JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus_terminal")
	private EstatusTerminalEntity estatus;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	private UsuarioEntity usuario;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;

}
