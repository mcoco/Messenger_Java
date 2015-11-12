
package messenger;
import java.net.*;
import java.io.*;
import messenger.VServidor;
public class Conector extends Thread {
    private Socket s;
    private ServerSocket ss;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto = 8180;
    
    public Conector(String nombre)
    {
        super(nombre);
    }
    public void enviarMSG(String msg)
    {
        try{
            this.salida.writeUTF(msg+"\n");
        }catch (IOException e){};
    }
    
    public void run()
    {
     String text="test";
     try{
         this.ss = new ServerSocket(puerto);
         this.s = ss.accept();
         
         this.entradaSocket = new InputStreamReader(s.getInputStream());
         this.entrada = new BufferedReader(entradaSocket);
         
         this.salida = new DataOutputStream(s.getOutputStream());
         while(true)
         {
             text = this.entrada.readLine();
             System.out.println(text);
             VServidor.jTextArea1.setText(VServidor.jTextArea1.getText()+"\n"+text);
             
         }
         }catch (IOException e){
         System.out.println("Algun Tipo de error");
         };
    }
    public String leerMSG()
    {
        try{
            return this.entrada.readLine();
        }catch(IOException e){};
        return null;
    }
    public void desconectar()
    {
        try{
            s.close();
        }catch(IOException e){};
        try{
            ss.close();
        }catch(IOException e){};
    }
}
