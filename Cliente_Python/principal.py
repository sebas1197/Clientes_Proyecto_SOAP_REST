import cliente as soap_cliente
from prettytable import PrettyTable

def listar():
    try:
        wsdl = "http://localhost:8090/ws/activotarjeta.wsdl"
        cliente = soap_cliente.Cliente(wsdl)
    except ConnectionRefusedError:
        print("Error: {}",ConnectionRefusedError)
        
    try:
        respuesta = cliente.obtenerTodas()
        tabla = PrettyTable(['Cod_tarjeta_Cliente','Cod_Tarjeta_Credito','Cod_cliente','Estad','Estado','N_Tarjeta','Cupo_Disponible','Cupo_total','Fecha_expedicion','Fecha_expiracion'])

        for i in respuesta:
            tabla.add_row(i.values())

        print(tabla)
    except Exception as e:
        print("Error: {}",e)


def crear():
    try:
        wsdl = "http://localhost:8090/ws/transaccion.wsdl"
        cliente = soap_cliente.Cliente(wsdl)
    except ConnectionRefusedError:
        print("Error: {}",ConnectionRefusedError)
        
    try:
        codTarjetaCliente = "0JQ1JUO4GQ"
        descripcion = "Compra"
        tipo = "CON"
        meses = "1" 
        monto = "156.33"
        interes = "5.00"

        respuesta = cliente.crearTransaccion(codTarjetaCliente, descripcion, tipo, meses, monto, interes)
    except Exception as e:
        print("Error: {}",e)


opcion = input("1) Obtener todas - Activo Tarjeta \n2) Crear transaccion \n:")

if opcion == "1":
    listar()
elif opcion == "2":
    crear()
else: 
    print("Opcion incorrecta")