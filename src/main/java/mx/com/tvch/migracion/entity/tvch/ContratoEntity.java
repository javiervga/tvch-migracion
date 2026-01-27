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
@Table(name = "contratos")
public class ContratoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contrato")
	private Long id;
	
	@Column(name = "id_contrato_sucursal")
	private Long idSucursal;
	
	@Column(name = "folio_contrato")
	private Long folioContrato;
	
	@ManyToOne
	@JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
	private EstatusContratoEntity estatus;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	private UsuarioEntity usuario;
	
	@Column(name = "tvs_contratadas")
	private Integer tvsContratadas;
	
	@Column(name = "fecha_proximo_pago")
	private Date fechaProximoPago;
	
	@Column(name = "dia_primer_pago")
	private Integer diaPrimerPago;
	
	@Column(name = "mes_primer_pago")
	private Integer mesPrimerPago;
	
	@Column(name = "anio_primer_pago")
	private Integer anioPrimerPago;
	
	@Column(name = "folio_placa")
	private Long folioPlaca;
	
	@Column(name = "color_placa")
	private String colorPlaca;
	
	@ManyToOne
	@JoinColumn(name = "id_onu", referencedColumnName = "id_onu")
	private OnuEntity onu;
	
}
