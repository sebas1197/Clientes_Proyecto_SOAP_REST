/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.soap.activotarjeta;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "activotarjeta")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema tarjetaSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("TarjetaPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://espe.edu.ec/arqsoftware/soap/activotarjeta/ws");
        wsdl11Definition.setSchema(tarjetaSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema tarjetaSchema() {
        return new SimpleXsdSchema(new ClassPathResource("activotarjeta.xsd"));
    }
    
    @Bean(name = "transaccion")
    public DefaultWsdl11Definition defaultWsdl11Definitiont(XsdSchema transaccionSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("TransaccionPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://espe.edu.ec/arqsoftware/soap/activotarjeta/ws");
        wsdl11Definition.setSchema(transaccionSchema);
        return wsdl11Definition;
    }
    @Bean
    public XsdSchema transaccionSchema() {
        return new SimpleXsdSchema(new ClassPathResource("transaccion.xsd"));
    }
}
