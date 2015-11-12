
package messenger;
import java.net.*;
import java.io.*;
public class Conector extends Thread{
    private Socket s;
    private ServerSocket ss;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto = 8180;
    
    public Conector(String ip)
    {
        try{
            this.s = new Socket(ip,this.puerto);
            
            this.entradaSocket = new InputStreamReader(s.getInputStream());
            this.entrada = new BufferedReader(entradaSocket);
            
            this.salida= new DataOutputStream(s.getOutputStream());
            this.salida.writeUTF("Conectado- \n");
            
        }catch (Exception e){};
    }
    
    public void run()
    {
        String texto;
        while(true)
        {try{
            texto = entrada.readLine();
            VCliente.jTextArea1.setText(VCliente.jTextArea1.getText()+"\n"+ texto);
        }catch(IOException e){};
        }
    }
   
    public void enviarMSG(String msg)
    {
        System.out.println("enviado");
        try{
            this.salida = new DataOutputStream(s.getOutputStream());
            this.salida.writeUTF(msg+"\n");
        }catch (IOException e){
            System.out.println("Problema al enviar");
        };
    }
    public String leerMSG()
    {
        try{
            return entrada.readLine();
        }catch(IOException e){};
        return null;
    }
    
}
