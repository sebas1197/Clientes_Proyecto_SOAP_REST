/*
 * Copyright (c) 2021 mafer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mafer - initial API and implementation and/or initial documentation
 */
package ec.edu.espe.arqsoftware.soap.activotarjeta.service;

import ec.edu.espe.arqsoftware.soap.activotarjeta.dao.TarjetaClienteRepository;
import ec.edu.espe.arqsoftware.soap.activotarjeta.dao.TransaccionRepository;
import ec.edu.espe.arqsoftware.soap.activotarjeta.exception.CreateException;
import ec.edu.espe.arqsoftware.soap.activotarjeta.exception.FoundException;
import ec.edu.espe.arqsoftware.soap.activotarjeta.model.TarjetaCliente;
import ec.edu.espe.arqsoftware.soap.activotarjeta.model.Transaccion;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TransaccionService {
    private final TarjetaClienteRepository tarjetaClienteRepo;
    private final TransaccionRepository transaccionRepo;

    public TransaccionService(TarjetaClienteRepository tarjetaClienteRepo, TransaccionRepository transaccionRepo) {
        this.tarjetaClienteRepo = tarjetaClienteRepo;
        this.transaccionRepo = transaccionRepo;
    }
    
    @Transactional
    public void crearTransaccion(Transaccion transaccion) {
        Optional<TarjetaCliente> tarjetaClienteOpt = this.tarjetaClienteRepo.findById(transaccion.getCodigoTarjetaCliente());
        if (tarjetaClienteOpt.isEmpty()) {
            throw new CreateException("No existe la tarjeta con codigo: " + transaccion.getCodigoTarjetaCliente());
        }

        if ("CON".equals(transaccion.getTipo()) || "DIF".equals(transaccion.getTipo())) {
            if (tarjetaClienteOpt.get().getCupoDisponible().compareTo(transaccion.getMonto()) == -1) {
                throw new CreateException("No tiene suficiente dinero para realizar esta operación");
            }
            tarjetaClienteOpt.get().setCupoTotal(tarjetaClienteOpt.get().getCupoTotal().subtract(transaccion.getMonto()));

        } else if ("PAG".equals(transaccion.getTipo())) {
            transaccion.setInteres(BigDecimal.ZERO);
            tarjetaClienteOpt.get().setCupoTotal(tarjetaClienteOpt.get().getCupoTotal().add(transaccion.getMonto()));
        } else {
            throw new CreateException("No existe el tipo de pago seleccionado");
        }

        this.transaccionRepo.save(transaccion);
        this.tarjetaClienteRepo.save(tarjetaClienteOpt.get());
    }
    
    public List<Transaccion> obtenerMovimientosActuales(String codTarjetaCliente) {
        Calendar fechaFin = Calendar.getInstance();
        Calendar fechaInicio = Calendar.getInstance();
        Optional<TarjetaCliente> tarjetaClienteOpt = this.tarjetaClienteRepo.findById(codTarjetaCliente);
        if (tarjetaClienteOpt.isPresent()) {
            fechaFin.set(Calendar.DAY_OF_MONTH, tarjetaClienteOpt.get().getTarjetaCredito().getFechaCorte());
            fechaInicio.add(Calendar.MONTH, -1);
            fechaFin.add(Calendar.DAY_OF_WEEK, -1);
            fechaInicio.set(Calendar.DAY_OF_MONTH, tarjetaClienteOpt.get().getTarjetaCredito().getFechaCorte());
        }
        LocalDateTime ldInicio = fechaInicio.toInstant().atZone(ZoneId.of("America/Chicago")).toLocalDate().atStartOfDay();
        LocalDateTime ldFin = fechaFin.toInstant().atZone(ZoneId.of("America/Chicago")).toLocalDate().atTime(23, 59, 59);
        log.info("Va a buscar transacciones desde: {} hasta: {}, de la cuenta {}", ldInicio, ldFin, codTarjetaCliente);
        return this.transaccionRepo.findByCodigoTarjetaClienteAndFechaBetween(
                codTarjetaCliente, ldInicio, ldFin);
    }
    
}
