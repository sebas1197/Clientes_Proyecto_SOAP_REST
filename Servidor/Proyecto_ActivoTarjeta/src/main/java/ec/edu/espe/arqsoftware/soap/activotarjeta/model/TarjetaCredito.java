
package ec.edu.espe.arqsoftware.soap.activotarjeta.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tarjeta_credito")
public class TarjetaCredito implements Serializable {

    @Id
    @Column(name = "cod_tarjeta_credito", nullable = false, length = 4)
    private String codigo;
    
    @Column(name = "nombre", nullable = false, length = 64)
    private String nombre;
    
    @Column(name = "tipo", nullable = false, length = 3)
    private String tipo;
    
    @Column(name = "cupo_inicial", nullable = false, precision = 18, scale = 2)
    private BigDecimal cupoInicial;
    
    @Column(name = "fecha_corte", nullable = false)
    private Integer fechaCorte;
    
    @Column(name = "tasa", nullable = false, precision = 4, scale = 2)
    private BigDecimal tasa;
    
    @Column(name = "emisor", nullable = false, length = 3)
    private String emisor;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarjetaCredito")
    private List<TarjetaCliente> tarjetaClientes;

}
