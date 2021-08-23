
package EstructuraDatos;

import GUI.AdminClientes.DashboardClientes;
import Principal.Controlador.Cliente;
import Principal.Controlador.Producto;
import Principal.Controlador.Venta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import static Principal.Proyecto1_SAP.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Dubón
 */
public class ManejadorArchivos {
    private File path;
    private FileReader entrada;
    private boolean abierto;
    
    public void abrir()
    {
        JFileChooser choose=new JFileChooser();
        choose.setCurrentDirectory(new java.io.File("."));
        choose.setDialogTitle("Cargar Archivo");
        FileNameExtensionFilter filtro=new FileNameExtensionFilter(".csv","csv");
        choose.addChoosableFileFilter(filtro);
        choose.setAcceptAllFileFilterUsed(true);
        
        if(choose.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            System.out.println("se abrio archivo");
            path =choose.getSelectedFile();
//            System.out.println(path);
            abierto=true;
        }
        else
        {
            System.out.println("NO se abrio archivo");
            abierto=false;
        }
    }
    
    public void cargarClientes() throws IOException//este metodo me extrae lo que contiene el archivo de texto
    {
        try 
         {
             entrada=new FileReader(path);
             String linea;
             BufferedReader lector =new BufferedReader(entrada);//creo un buffer que me lee por lineas
             while((linea=lector.readLine())!=null)
             {
                 String cadena[];
                 cadena=linea.split(",");
                 Cliente aux=new Cliente(cadena[0],Integer.parseInt(cadena[1]),cadena[2].charAt(0),Integer.parseInt(cadena[3]),cadena[4].replaceFirst("/", ""));
                 clientes[contadorCl]=aux;
                 contadorCl++;
             }
//             System.out.println(contadorCl);
         } catch (FileNotFoundException ex) {
             System.out.print("Error al abrir el archivo"); //agrego excepciones por si hay algun error
         }
            catch (IOException e)
            {
                System.out.println("Error");
            }
         finally
         {
            entrada.close();//simpre cierro mi buffer para evitar problemas
             //System.out.println(obtenerMujeres());
         }
        
    }
    
    public int obtenerMujeres()
    {
        int cont=0;
        for(int i=0; i<contadorCl;i++)
        {
            String sexo=String.valueOf(clientes[i].getSexo());
            if(sexo.equals("F"))
            {
                cont++;
            }
        }
        return cont;
    }
    
    public void cargarProductos() throws IOException
    {
        
            try 
            {
                 entrada=new FileReader(path);
                 String linea;
                 BufferedReader lector =new BufferedReader(entrada);//creo un buffer que me lee por lineas
                 while((linea=lector.readLine())!=null)
                 {
                     String cadena[];
                     cadena=linea.split(",");
                     Producto aux=new Producto(cadena[0],Float.parseFloat(cadena[1]),Integer.parseInt(cadena[2]),cadena[3].replaceFirst("/", ""));
                     productos[contadorP]=aux;
                     contadorP++;
                 }
                 //System.out.println(contadorP);
            } 
            catch (FileNotFoundException ex) 
            {
                 JOptionPane.showMessageDialog(null, "Error al cargar los datos");
                 System.out.print("Error al abrir el archivo"); //agrego excepciones por si hay algun error
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, "Error al cargar los datos");
                System.out.println("Error");
            }
            finally
            {
               entrada.close();//simpre cierro mi buffer para evitar problemas
               //System.out.println(productos[0]);
            }
    }
    
    public boolean getAbierto()
    {
        return abierto;
    }
    
    public void cargarVentas() throws IOException
    {
        try 
            {
                 entrada=new FileReader(path);
                 String linea;
                 BufferedReader lector =new BufferedReader(entrada);//creo un buffer que me lee por lineas
                 while((linea=lector.readLine())!=null)
                 {
                     String cadena[];
                     cadena=linea.split(",");
                     Venta aux=new Venta(Integer.parseInt(cadena[0]),Integer.parseInt(cadena[1]),cadena[2],Integer.parseInt(cadena[3]));
                     ventas[contadorV]=aux;
                     contadorV++;
                 }
                 //System.out.println(contadorV);
            } 
            catch (FileNotFoundException ex) 
            {
                 JOptionPane.showMessageDialog(null, "Error al cargar los datos");
                 System.out.print("Error al abrir el archivo"); //agrego excepciones por si hay algun error
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, "Error al cargar los datos");
                System.out.println("Error");
            }
            finally
            {
               entrada.close();//simpre cierro mi buffer para evitar problemas
               //System.out.println(ventas[0]);
            }
    }
}
