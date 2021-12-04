/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.cliente.tarjetaactivo.service;

import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.wsdl.CrearTransaccionRequest;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.wsdl.CrearTransaccionResponse;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.wsdl.ListarUltimasTransaccionesRequest;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.wsdl.ListarUltimasTransaccionesResponse;
import ec.edu.espe.arqsoftware.cliente.tarjetaactivo.wsdl.TransaccionRQ;
import javax.xml.datatype.DatatypeConfigurationException;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Service
public class TransaccionService extends WebServiceGatewaySupport{
    private String endpoint = "http://localhost:8090/ws/transaccion.wsdl";
    
    public void crearTransaccion(TransaccionRQ transaccion) throws DatatypeConfigurationException{
        CrearTransaccionRequest request = new CrearTransaccionRequest();
        request.setTransaccionRQ(transaccion);
        CrearTransaccionResponse response
                = (CrearTransaccionResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint, request);
    }
    
    public ListarUltimasTransaccionesResponse listarUltimasTransacciones(String codigoTarjeta){
        ListarUltimasTransaccionesRequest request = new ListarUltimasTransaccionesRequest();
        request.setCodTarjetaCliente(codigoTarjeta);
        ListarUltimasTransaccionesResponse response= (ListarUltimasTransaccionesResponse) getWebServiceTemplate()
                .marshalSendAndReceive(endpoint, request);
        
        return response;
    }
}
