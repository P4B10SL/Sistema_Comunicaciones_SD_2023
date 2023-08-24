import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SistemaComunicaciones {
    String ipDestino;
    public void SistComunicaciones()
    {}
    public void init(String destino)
    {
        ipDestino=destino;
    }
    public String Send(String mensaje){
        Mensaje nuevoMensaje= new Mensaje();
        String confirmacion;
        byte pkg_byte[] = null; //buffer para la asignacion de los datos
        try { // necesario para capturar los posibles errores
            DatagramPacket paqueteUDP; //declaracion del paquete UDP
            String host = ipDestino; //Declaración de la dirección IP destino
            String port_string = "8001";    //Se indica que se conecte al puerto 8001
            Integer port= Integer.parseInt(port_string);
            InetAddress dir_remota = InetAddress.getByName(host);
            nuevoMensaje.mensajeArrayB= nuevoMensaje.ToArrayBytes(mensaje);
            byte[] buffer = nuevoMensaje.mensajeArrayB; // retorna los bytes del string
            paqueteUDP = new DatagramPacket(buffer, buffer.length, dir_remota, port);
            //crea el socket y envía el paquete
            DatagramSocket ds = new DatagramSocket();
            ds.send(paqueteUDP);
            ds.close();
            confirmacion="Enviado";
        }
        catch(Exception e) {
            System.out.println(e);
            confirmacion="ERROR";
        }
        return confirmacion;
    }
    public String Receive() {
        byte pkg_byte[] = null; //buffer para la asignacion de los datos
        Mensaje nuevoMensaje= new Mensaje();
        String respuesta;
        int MAX_LONG = 800;
        try {
            DatagramSocket so_reciv = new DatagramSocket(8001); //Crea el DatagramSocket en el localhost y lo asigna en el puerto 80
            byte buffer[] = new byte[MAX_LONG]; //buffer solo para el DatagramPacket
            //buffer solo para el DatagramPacket
            DatagramPacket data_reciv = new DatagramPacket(buffer, MAX_LONG);
            so_reciv.receive(data_reciv); //lee los datos
            pkg_byte = new byte[data_reciv.getLength()]; //array de bytes para los datos
            pkg_byte = data_reciv.getData(); // asigna los bytes de datos
            int limite=data_reciv.getLength();
            nuevoMensaje.mensajeString=nuevoMensaje.ToMessage(pkg_byte,limite);
            respuesta=nuevoMensaje.mensajeString;
            so_reciv.close();

        } catch (Exception e) {
            System.out.println(e);
            respuesta="ERROR";
        }
        return respuesta;
    }
}
