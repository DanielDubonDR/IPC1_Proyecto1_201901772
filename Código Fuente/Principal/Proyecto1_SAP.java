
package Principal;


import GUI.Login;
import Principal.Controlador.Cliente;
import Principal.Controlador.Producto;
import Principal.Controlador.Usuario;
import Principal.Controlador.Venta;
import Principal.Controlador.VentaAgrupada;
import java.io.IOException;

/**
 *
 * @author Daniel Dubón
 */
public class Proyecto1_SAP {
    
    public static Usuario [] users=new Usuario[10];
    public static Cliente [] clientes=new Cliente[100];
    public static Producto[] productos=new Producto[100];
    public static Venta[] ventas=new Venta[500];
    public static VentaAgrupada[] ventasAgrupadas=new VentaAgrupada[100];
    public static String nameuser="Daniel Dubón";
    //contadorusuarios/clientes/productos/contadorVentas/contadorVentasAgrupadas
    public static int contador=0, contadorCl=0, contadorP=0, contadorV=0, contVA=0; 
    
    
    public static void main(String[] args) throws IOException
    {
        Login abrir=new Login();
        abrir.setVisible(true);
    }
}
