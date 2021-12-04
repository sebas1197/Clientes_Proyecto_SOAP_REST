
package ec.edu.espe.arqsoftware.soap.activotarjeta.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "transaccion")
public class Transaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_transaccion", nullable = false)
    private Integer codigo;
    
    @Column(name = "cod_tarjeta_cliente", nullable = false, length = 10)
    private String codigoTarjetaCliente;
    
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;
    
    @Column(name = "tipo", length = 3)
    private String tipo;
    
    @Column(name = "meses", length = 3)
    private Integer meses;
    
    @Column(name = "monto", nullable = false, precision = 18, scale = 2)
    private BigDecimal monto;
    
    @Column(name = "interes", precision = 3, scale = 2)
    private BigDecimal interes;
    
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    
    @JoinColumn(name = "cod_tarjeta_cliente", referencedColumnName = "cod_tarjeta_cliente", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TarjetaCliente tarjetaCliente;
    
}
