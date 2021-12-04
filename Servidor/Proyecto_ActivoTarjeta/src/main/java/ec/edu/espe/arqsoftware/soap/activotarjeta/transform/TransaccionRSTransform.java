/*
 * Copyright (c) 2021 mafer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */
package ec.edu.espe.arqsoftware.soap.activotarjeta.transform;

import ec.edu.espe.arqsoftware.soap.activotarjeta.model.Transaccion;
import ec.edu.espe.arqsoftware.soap.activotarjeta.ws.TransaccionRS;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class TransaccionRSTransform {

    public static TransaccionRS buildTransaccionTransformRS(Transaccion transaccion) throws DatatypeConfigurationException {
        XMLGregorianCalendar fechaInicio;
        if (transaccion.getFecha() != null) {
            fechaInicio = DatatypeFactory.newInstance().newXMLGregorianCalendar(transaccion.getFecha().toString());
        } else {
            fechaInicio = null;
        }
        return TransaccionRS.builder()
                .codigo(transaccion.getCodigo())
                .codigoTarjetaCliente(transaccion.getCodigoTarjetaCliente())
                .descripcion(transaccion.getDescripcion())
                .tipo(transaccion.getTipo())
                .mes(transaccion.getMeses())
                .monto(transaccion.getMonto())
                .interes(transaccion.getInteres())
                .fecha(fechaInicio)
                .build();
    }
}
