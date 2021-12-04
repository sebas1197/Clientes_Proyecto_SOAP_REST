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
import ec.edu.espe.arqsoftware.soap.activotarjeta.dao.TarjetaCreditoRepository;
import ec.edu.espe.arqsoftware.soap.activotarjeta.dao.TransaccionRepository;
import ec.edu.espe.arqsoftware.soap.activotarjeta.exception.CreateException;
import ec.edu.espe.arqsoftware.soap.activotarjeta.exception.FoundException;
import ec.edu.espe.arqsoftware.soap.activotarjeta.model.TarjetaCliente;
import ec.edu.espe.arqsoftware.soap.activotarjeta.model.TarjetaCredito;
import ec.edu.espe.arqsoftware.soap.activotarjeta.model.Transaccion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TarjetaClienteService {
    private final TarjetaClienteRepository tarjetaClienteRepo;
    private final TarjetaCreditoRepository tarjetaCreditoRepo;
    private final TransaccionRepository transaccionRepo;

    public TarjetaClienteService(TarjetaClienteRepository tarjetaClienteRepo, TarjetaCreditoRepository tarjetaCreditoRepo, TransaccionRepository transaccionRepo) {
        this.tarjetaClienteRepo = tarjetaClienteRepo;
        this.tarjetaCreditoRepo = tarjetaCreditoRepo;
        this.transaccionRepo = transaccionRepo;
    }

    public List<TarjetaCliente> obtenerTarjetaCliente() {
        return this.tarjetaClienteRepo.findAll();
    }

}
