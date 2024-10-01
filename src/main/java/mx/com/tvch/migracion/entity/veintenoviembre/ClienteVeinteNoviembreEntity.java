package mx.com.tvch.migracion.entity.veintenoviembre;

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
@Table(name = "clientes")
public class ClienteVeinteNoviembreEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cliente")
	private Long cod_cliente; 
	
	@Column(name = "num_contrato")
	private Long num_contrato;
	
	@Column(name = "nom_cliente")
	private String nom_cliente;
	
	@Column(name = "ape_cliente")
	private String ape_cliente;
	
	@Column(name = "col_cliente")
	private String col_cliente;
	
	@Column(name = "cal_cliente")
	private String cal_cliente;
	
	@Column(name = "num_cliente")
	private String num_cliente;
	
	@Column(name = "tel_cliente")
	private String tel_cliente;
	
	@Column(name = "ser_cliente")
	private String ser_cliente;
	
	@Column(name = "obs_cliente")
	private String obs_cliente;
	
	@Column(name = "fid_cliente")
	private Long fid_cliente;
	
	@Column(name = "fim_cliente")
	private String fim_cliente;
	
	@Column(name = "fia_cliente")
	private Long fia_cliente; 
	
	@Column(name = "fcd_cliente")
	private Long fcd_cliente;
	
	@Column(name = "fcm_cliente")
	private String fcm_cliente;
	
	@Column(name = "fca_cliente")
	private Long fca_cliente;
	
	@Column(name = "est_cliente")
	private String est_cliente;
	
	@Column(name = "com_cliente")
	private String com_cliente;
	
	@Column(name = "cajero")
	private String cajero;
	
	@Column(name = "fecha")
	private String fecha;
	
	@Column(name = "hora")
	private String hora;
	
	@Column(name = "nap")
	private String nap;

}
