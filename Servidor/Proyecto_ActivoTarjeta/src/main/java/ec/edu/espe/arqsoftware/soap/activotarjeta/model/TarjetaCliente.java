package ec.edu.espe.arqsoftware.soap.activotarjeta.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tarjeta_cliente")

public class TarjetaCliente implements Serializable {

    @Id
    @Column(name = "cod_tarjeta_cliente", nullable = false, length = 10)
    private String codigo;
    
    @Column(name = "cod_cliente", nullable = false, length = 24)
    private String codigoCliente;
    
    @Column(name = "cod_tarjeta_credito", nullable = false, length = 4)
    private String codigoTarjetaCredito;
    
    @Column(name = "tarjeta_nro", nullable = false, length = 16)
    private String numeroTarjeta;
    
    @Column(name = "nombre_titular", nullable = false, length = 64)
    private String nombreTitular;
    
    @Column(name = "estado", nullable = false, length = 3)
    private String estado;
    
    @Column(name = "cupo_disponible", nullable = false, precision = 18, scale = 2)
    private BigDecimal cupoDisponible;
    
    @Column(name = "pin", nullable = false, length = 4)
    private String pin;
    
    @Column(name = "cod_verificador", nullable = false, length = 7)
    private String codigoVerificador;
    
    @Column(name = "fecha_expedicion", nullable = false)
    private LocalDateTime fechaExpedicion;
    
    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDate fechaExpiracion;
    
    @Column(name = "cupo_total", precision = 18, scale = 2)
    private BigDecimal cupoTotal;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarjetaCliente")
    private List<Transaccion> transacciones;
    
    @JoinColumn(name = "cod_tarjeta_credito", referencedColumnName = "cod_tarjeta_credito", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TarjetaCredito tarjetaCredito;

}
