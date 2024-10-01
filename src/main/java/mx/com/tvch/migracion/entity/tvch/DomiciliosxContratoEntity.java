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
@Table(name = "domicilios_x_contrato")
public class DomiciliosxContratoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contratoxsuscriptor")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_contrato", referencedColumnName = "id_contrato")
	private ContratoEntity contrato;
	
	@ManyToOne
	@JoinColumn(name = "id_domicilio", referencedColumnName = "id_domicilio")
	private DomicilioEntity domicilio;
}
