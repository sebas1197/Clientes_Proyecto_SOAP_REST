/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.cliente.tarjetaactivo.controller;

import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.dto.Serializador;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.exception.FoundException;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.service.TransaccionService;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.wsdl.TransaccionRQ;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.wsdl.TransaccionRS;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaccion")
@Slf4j
public class TransaccionController {

    private final TransaccionService service;

    public TransaccionController(TransaccionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity crearTransaccion(@RequestBody TransaccionRQ request) {
        try {
            log.info("Se va a crear una transaccion con la siguiente informacion: {}", request);
            this.service.crearTransaccion(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Ocurrio un error al crear la transaccion. {} - retorna badrequest {}", e.getMessage(), e.getStackTrace());
            return Serializador.Error(e, "Error al crear una transacción, inténtelo de nuevo");
        }
    }

    @GetMapping(value = "/movimientosActuales/{codTarjetaCliente}")
    public ResponseEntity listarUltimasTransacciones(@PathVariable("codTarjetaCliente") String codTarjetaCliente) {

        try {
            List<TransaccionRS> transaccionesRS = this.service.listarUltimasTransacciones(codTarjetaCliente).getTransaccionRS();
            if (transaccionesRS.size() <= 0) {
                throw new FoundException("No se ha encontrado ningún movimiento");
            } else {
                log.info("Transacciones obtenidas {} con la tarjeta de credito: {}",
                        transaccionesRS.size(), codTarjetaCliente);
                return ResponseEntity.ok(transaccionesRS);
            }
        } catch (Exception e) {
            return Serializador.Error(e, "Error al obtener los últimos movimientos");
        }
    }

}
