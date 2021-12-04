/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.cliente.tarjetaactivo.controller;

import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.dto.Serializador;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.exception.FoundException;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.service.TarjetaClienteService;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.wsdl.ObtenerTodasRequest;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.wsdl.TarjetaActivoRS;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tarjetaCliente")
@Slf4j
public class TarjetaClienteController {
    
    private final TarjetaClienteService service;

    public TarjetaClienteController(TarjetaClienteService service) {
        this.service = service;
    }
  
    @GetMapping(value = "/todas")
    public ResponseEntity obtenerTodas() {
        log.info("Obteniendo todas las tarjetas de credito esperando por resultados");
         
        List<TarjetaActivoRS> tarjetas = this.service.ObtenerTodas(new ObtenerTodasRequest()).getTarjetaActivoRS();
        try {
            if(tarjetas.size()<=0){
                throw new FoundException("No se ha encontrado ningúna tarjeta.");
            }

            return ResponseEntity.ok(tarjetas);
        } catch (Exception e) {
            return Serializador.Error(e, "Error, no se encontró ningun producto");
        }
    }
}
