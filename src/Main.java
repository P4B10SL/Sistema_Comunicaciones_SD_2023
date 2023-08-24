public class Main {
    public static void main(String[] args) {
       SistemaComunicaciones Coms= new SistemaComunicaciones();
       Coms.init("192.168.4.65");

       String respuesta= Coms.Send("Mensaje1");
       System.out.println(respuesta);

       //String respuesta2= Coms.Receive();
       //System.out.println(respuesta2);
    }
}