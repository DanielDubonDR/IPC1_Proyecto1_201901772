
package Principal.Controlador;

import EstructuraDatos.AlgoritmosVentas;
import static Principal.Proyecto1_SAP.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Dubón
 */
public class Reporte {
    
    StringBuilder listClientes=new StringBuilder();
    StringBuilder listProductos=new StringBuilder();
    StringBuilder listVentas=new StringBuilder();
    
    public void reporteClientes()
    {
        Cliente[] edades=ordenarEdad(clientes);
        
//        System.out.println(edades[0].getEdad());
//        System.out.println(edades[1].getEdad());
        crearHeaderClientes();
        for(int i=contadorCl-1; i>=0;i--)
        {
            listClientes.append("<tr>");
            listClientes.append("<td>"+edades[i].getNombre()+"</td>");
            listClientes.append("<td>"+edades[i].getEdad()+"</td>");
            listClientes.append("<td>"+edades[i].getSexo()+"</td>");
            listClientes.append("<td>"+edades[i].getNit()+"</td>");
            listClientes.append("</tr>");
        }
        crearFinalClientes();
        crearArchivo(listClientes.toString(),"Reporte Clientes.html");
    }
    
    public void reporteProductos()
    {
        Producto[] precios=ordenarProducto(productos);
        
        crearHeaderProductos();
        for(int i=contadorP-1; i>=0;i--)
        {
            listProductos.append("<tr>");
            listProductos.append("<td>"+precios[i].getNombre()+"</td>");
            listProductos.append("<td class=\"w3-center\">"+precios[i].getPrecio()+"</td>");
            listProductos.append("<td class=\"w3-center\">"+precios[i].getCantidad()+"</td>");
            listProductos.append("</tr>");
        }
        crearFinalProductos();
        crearArchivo(listProductos.toString(),"Reporte Productos.html");
    }
    
    public void reporteVentas()
    {
        AlgoritmosVentas a=new AlgoritmosVentas();
        a.controlador();
        
        VentaAgrupada[] totales=ordenarVentas(ventasAgrupadas);
        
        crearHeaderVentas();
        for(int i=contVA-1; i>=0;i--)
        {
            listVentas.append("<tr>");
            listVentas.append("<td>"+totales[i].getCodigo()+"</td>");
            listVentas.append("<td class=\"w3-center\">"+totales[i].getNit()+"</td>");
            listVentas.append("<td class=\"w3-center\">"+redondear(totales[i].getTotal())+"</td>");
            listVentas.append("</tr>");
        }
        crearFinalVentas();
        contVA=0;
        crearArchivo(listVentas.toString(),"Reporte Ventas.html");
    }
    
    private Cliente[] ordenarEdad(Cliente[] clientes)
    {
        Cliente intercambio;
        for(int i=0; i<(contadorCl-1);i++)
        {
            for(int j=0; j<(contadorCl-1); j++)
            {
               if(clientes[j].getEdad()>clientes[j+1].getEdad())
               {
                   intercambio=clientes[j];
                   clientes[j]=clientes[j+1];
                   clientes[j+1]=intercambio;
               }
            }
        }
        return clientes;
    }
    
    private Producto[] ordenarProducto(Producto[] productos)
    {
        Producto intercambio;
        for(int i=0; i<(contadorP-1);i++)
        {
            for(int j=0; j<(contadorP-1); j++)
            {
               if(productos[j].getPrecio()>productos[j+1].getPrecio())
               {
                   intercambio=productos[j];
                   productos[j]=productos[j+1];
                   productos[j+1]=intercambio;
               }
            }
        }
        return productos;
    }
    
    private VentaAgrupada[] ordenarVentas(VentaAgrupada[] ventas)
    {
        VentaAgrupada intercambio;
        for(int i=0; i<(contVA-1);i++)
        {
            for(int j=0; j<(contVA-1); j++)
            {
               if(ventas[j].getTotal()>ventas[j+1].getTotal())
               {
                   intercambio=ventas[j];
                   ventas[j]=ventas[j+1];
                   ventas[j+1]=intercambio;
               }
            }
        }
        return ventas;
    }
    
    public void crearArchivo(String contenido,String titulo){//este metodo me crea archivos planos
        
        File archivo = new File(titulo);
        
        try {
            FileWriter escritor = new FileWriter(archivo);//declaro mi variable que va a leer los datos
            escritor.write(contenido);
            escritor.close();
            JOptionPane.showMessageDialog(null, "Se generó el archivo con éxito");
            abrirarchivo(titulo);
        } 
        catch (IOException ex) {
            ex.printStackTrace(); 
        }
        
    }
    
    public void abrirarchivo(String archivo) {

        try {

            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
        }

    }
    
    private void crearHeaderClientes()
    {
        listClientes.append("<!DOCTYPE html>\n" +
        "<html>\n" +
        "<title>Reporte Clientes</title>\n" +
        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
        "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
        "<body>\n" +
        "<div class=\"w3-container w3-deep-purple w3-margin-bottom w3-center\">\n" +
        "<h1>Reporte Clientes</h1>\n" +
        "<a href=\"Reporte Productos.html\"><span class=\"w3-left w3-xxlarge\">«</span></a>\n" +
        "<a href=\"Reporte Ventas.html\"><span class=\"w3-right w3-xxlarge\">»</span></a>\n" +
        "</div>\n" +
        "<div class=\"w3-container\">\n" +
        "<input class=\"w3-input w3-border w3-padding\" type=\"text\" placeholder=\"Buscar por NIT\" id=\"myInput\" onkeyup=\"myFunction()\">\n" +
        "<table class=\"w3-table-all w3-margin-top\" id=\"myTable\">\n" +
        "<tr>\n" +
        "<th style=\"width:35%;\">Nombre</th>\n" +
        "<th style=\"width:20%;\">Edad</th>\n" +
        "<th style=\"width:20%;\">Sexo</th>\n" +
        "<th style=\"width:25%;\">NIT</th>\n" +
        "</tr>");
    }
    
    private void crearFinalClientes()
    {
        listClientes.append("</table>\n" +"<br><p>Reporte generado por: "+nameuser+"</p><br>\n" +"</div>\n" +"<script>\n" +
        "function myFunction() {\n" +
        "var input, filter, table, tr, td, i;\n" +
        "input = document.getElementById(\"myInput\");\n" +
        "filter = input.value.toUpperCase();\n" +
        "table = document.getElementById(\"myTable\");\n" +
        "tr = table.getElementsByTagName(\"tr\");\n" +
        "for (i = 0; i < tr.length; i++) {\n" +
        "td = tr[i].getElementsByTagName(\"td\")[3];\n" +
        "if (td) {\n" +
        "txtValue = td.textContent || td.innerText;\n" +
        "if (txtValue.toUpperCase().indexOf(filter) > -1) {\n" +
        "tr[i].style.display = \"\";\n" +
        "} else {\n" +
        "tr[i].style.display = \"none\";\n" +
        "}\n" +
        "}\n" +
        "}\n" +
        "}\n" +
        "</script>\n" +
        "</div>\n" +
        "</body>\n" +
        "</html>");
    }
    
    private void crearHeaderProductos()
    {
        listProductos.append("<!DOCTYPE html>\n" +
        "<html>\n" +
        "<title>Reporte Productos</title>\n" +
        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
        "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
        "<body>\n" +
        "<div class=\"w3-container w3-green w3-margin-bottom w3-center\">\n" +
        "<h1>Reporte Productos</h1>\n" +
        "<a href=\"Reporte Ventas.html\"><span class=\"w3-left w3-xxlarge\">«</span></a>\n" +
        "<a href=\"Reporte Clientes.html\"><span class=\"w3-right w3-xxlarge\">»</span></a>\n" +
        "</div>\n" +
        "<div class=\"w3-container\"> \n" +
        "<input class=\"w3-input w3-border w3-padding\" type=\"text\" placeholder=\"Buscar por Nombre\" id=\"myInput\" onkeyup=\"myFunction()\">\n" +
        "<table class=\"w3-table w3-bordered w3-margin-top\" id=\"myTable\">\n" +
        "<tr>\n" +
        "<th style=\"width:35%;\">Nombre</th>\n" +
        "<th style=\"width:30%;\" class=\"w3-center\">Precio</th>\n" +
        "<th style=\"width:35%;\" class=\"w3-center\">Cantidad</th>\n" +
        "</tr>");
    }
    
    private void crearFinalProductos()
    {
        listProductos.append("</table><br><p>Reporte generado por: "+nameuser+"</p><br></div>\n" +
        "<script>\n" +
        "function myFunction() {\n" +
        "var input, filter, table, tr, td, i;\n" +
        "input = document.getElementById(\"myInput\");\n" +
        "filter = input.value.toUpperCase();\n" +
        "table = document.getElementById(\"myTable\");\n" +
        "tr = table.getElementsByTagName(\"tr\");\n" +
        "for (i = 0; i < tr.length; i++) {\n" +
        "td = tr[i].getElementsByTagName(\"td\")[0];\n" +
        "if (td) {\n" +
        "txtValue = td.textContent || td.innerText;\n" +
        "if (txtValue.toUpperCase().indexOf(filter) > -1) {\n" +
        "tr[i].style.display = \"\";\n" +
        "} else {\n" +
        "tr[i].style.display = \"none\";\n" +
        "}}}}</script>\n" +
        "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js\"></script>");
        
        Producto[] precios=ordenarProducto(productos);
        for(int i=0; i<contadorP-1;i++)
        {
            if(precios[i].getCantidad()==0)
            {
                listProductos.append("<script>$(function() {$(\"table tbody tr:nth-child("+(contadorP-i+1)+")\").css(\"background-color\", \"#ff5252\");});</script>");
            }
            else if(precios[i].getCantidad()==1)
            {
                listProductos.append("<script>$(function() {$(\"table tbody tr:nth-child("+(contadorP-i+1)+")\").css(\"background-color\", \"#ff793f\");});</script>");
            }
        }
        
        listProductos.append("</div></body></html>");
    }
    
    private void crearHeaderVentas()
    {
        listVentas.append("<!DOCTYPE html>\n" +
        "<html>\n" +
        "<title>Reporte Ventas</title>\n" +
        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
        "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
        "<body>\n" +
        "<div class=\"w3-container w3-teal w3-center w3-margin-bottom\">\n" +
        "<h1>Reporte Ventas</h1>\n" +
        "<a href=\"Reporte Clientes.html\"><span class=\"w3-left w3-xxlarge\">«</span></a>\n" +
        "<a href=\"Reporte Productos.html\"><span class=\"w3-right w3-xxlarge\">»</span></a>\n" +
        "</div>\n" +
        "<div class=\"w3-container\">  \n" +
        "<input class=\"w3-input w3-border w3-padding\" type=\"text\" placeholder=\"Buscar por Código\" id=\"myInput\" onkeyup=\"myFunction()\">\n" +
        "<table class=\"w3-table-all w3-margin-top\" id=\"myTable\">\n" +
        "<tr>\n" +
        "<th style=\"width:35%;\" class=\"w3-center\">Código</th>\n" +
        "<th style=\"width:35%;\" class=\"w3-center\">NIT</th>\n" +
        "<th style=\"width:30%;\" class=\"w3-center\">Total</th>\n" +
        "</tr>");
    }
    
    private void crearFinalVentas()
    {
        listVentas.append("</table><br><p>Reporte generado por: "+nameuser+"</p><br></div>\n" +
        "<script>\n" +
        "function myFunction() {\n" +
        "var input, filter, table, tr, td, i;\n" +
        "input = document.getElementById(\"myInput\");\n" +
        "filter = input.value.toUpperCase();\n" +
        "table = document.getElementById(\"myTable\");\n" +
        "tr = table.getElementsByTagName(\"tr\");\n" +
        "for (i = 0; i < tr.length; i++) {\n" +
        "td = tr[i].getElementsByTagName(\"td\")[0];\n" +
        "if (td) {\n" +
        "txtValue = td.textContent || td.innerText;\n" +
        "if (txtValue.toUpperCase().indexOf(filter) > -1) {\n" +
        "tr[i].style.display = \"\";\n" +
        "} else {\n" +
        "tr[i].style.display = \"none\";\n" +
        "}}}}\n" +
        "</script></body></html>");
    }
    
    public double redondear(double numero)
    {
        double aux=Math.round(numero*100)/100d;
        return aux;
    }
}

