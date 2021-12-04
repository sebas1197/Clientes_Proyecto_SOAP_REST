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
package ec.edu.espe.arqsoftware.soap.activotarjeta.endpoint;

import ec.edu.espe.arqsoftware.soap.activotarjeta.exception.FoundException;
import ec.edu.espe.arqsoftware.soap.activotarjeta.model.TarjetaCliente;
import ec.edu.espe.arqsoftware.soap.activotarjeta.service.TarjetaClienteService;
import static ec.edu.espe.arqsoftware.soap.activotarjeta.transform.TarjetaActivoRSTransform.buildTransformRS;
import ec.edu.espe.arqsoftware.soap.activotarjeta.ws.ObtenerTodasRequest;
import ec.edu.espe.arqsoftware.soap.activotarjeta.ws.ObtenerTodasResponse;
import ec.edu.espe.arqsoftware.soap.activotarjeta.ws.TarjetaActivoRS;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
public class TarjetaClienteEndpoint {

    private static final String NAMESPACE_URI = "http://espe.edu.ec/arqsoftware/soap/activotarjeta/ws";
    private final TarjetaClienteService service;

    @Autowired
    public TarjetaClienteEndpoint(TarjetaClienteService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerTodasRequest")
    @ResponsePayload
    public ObtenerTodasResponse obtenerTodas(@RequestPayload ObtenerTodasRequest request) throws RuntimeException {
         log.info("Obteniendo todas las tarjetas de credito esperando por resultados");
        List<TarjetaCliente> tarjetas = this.service.obtenerTarjetaCliente();
        try {
            List<TarjetaActivoRS> tarjetaClienteRS = new ArrayList<>();
            for (TarjetaCliente c : tarjetas) {
                tarjetaClienteRS.add(buildTransformRS(c));
            }
            ObtenerTodasResponse response= new ObtenerTodasResponse();
            response.getTarjetaActivoRS().addAll(tarjetaClienteRS);
            return response;
        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            log.error("Ocurrio un error. {} - retorna badrequest - causado por: {}", e.getMessage(), exceptionMessage);
            throw new FoundException("Error: " + exceptionMessage);
        }
    }
}
