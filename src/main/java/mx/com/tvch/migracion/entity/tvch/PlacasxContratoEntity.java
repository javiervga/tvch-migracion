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
@Table(name = "placas_x_contrato")
public class PlacasxContratoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_placaxcontrato ")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_contrato", referencedColumnName = "id_contrato")
	private ContratoEntity contrato;
	
	@ManyToOne
	@JoinColumn(name = "id_placa", referencedColumnName = "id_placa")
	private PlacaEntity placa;

}
