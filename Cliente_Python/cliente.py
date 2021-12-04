from zeep import Client, helpers

class Cliente:
    def __init__(self, wsdl):
        self.soap_cliente = Client(wsdl)    

    def obtenerTodas(self):
        respuesta = self.soap_cliente.service.obtenerTodas()
        return helpers.serialize_object(respuesta)

    def crearTransaccion(self, codTarjetaCliente, descripcion, tipo, meses, monto, interes):
        transaccion = {
            'codTarjetaCliente': codTarjetaCliente,
            'descripcion': descripcion,
            'tipo': tipo,
            'meses': meses,
            'monto': monto,
            'interes': interes
        }
        respuesta = self.soap_cliente.service.crearTransaccion(transaccion)
        return helpers.serialize_object(respuesta)