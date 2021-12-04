/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.cliente.tarjetaactivo.service;

import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.wsdl.ObtenerTodasRequest;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.wsdl.ObtenerTodasResponse;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Service
public class TarjetaClienteService extends WebServiceGatewaySupport{
    private String endpoint = "http://localhost:8090/ws/activotarjeta.wsdl";
    
    public ObtenerTodasResponse ObtenerTodas(ObtenerTodasRequest request ){
        ObtenerTodasResponse  response= (ObtenerTodasResponse ) getWebServiceTemplate()
                .marshalSendAndReceive(endpoint, request);
        return response;
    }
}
