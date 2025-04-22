package mx.com.tvch.migracion.entity.tvch;

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
@Table(name = "domicilios")
public class DomicilioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_domicilio ")
	private Long id;
	
	@Column(name = "colonia")
	private String colonia;
	
	@Column(name = "calle")
	private String calle;
	
	@Column(name = "numero_calle")
	private String numeroCalle;
	
	@Column(name = "referencia")
	private String referencia;
	
	@Column(name = "estatus")
	private Integer estatus;

}
