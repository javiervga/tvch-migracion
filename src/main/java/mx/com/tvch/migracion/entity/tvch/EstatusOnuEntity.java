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
@Table(name = "estatus_onu")
public class EstatusOnuEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estatus_onu")
	private Long estatusId;
	
	@Column(name = "descripcion")
	private String descripcion;
	
}
