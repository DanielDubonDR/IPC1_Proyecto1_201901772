/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Controlador.PDF;

import EstructuraDatos.AlgoritmoProductosVendidos;
import Principal.Controlador.ProductosUsados;
import static Principal.Proyecto1_SAP.nameuser;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Daniel Dubón
 */
public class ListadoPVendidos {
    public void pdf() throws IOException
    {
      PDDocument document = new PDDocument();
      PDPage blankPage = new PDPage();
      
      document.addPage(blankPage);
      PDPageContentStream contents = new PDPageContentStream(document, blankPage);
      
      contents.beginText(); 
      contents.setFont(PDType1Font.COURIER_BOLD, 16);
      contents.newLineAtOffset(130, 700);
      String text = "LISTADO DE LOS PRODUCTOS MÁS VENDIDOS";
      contents.showText(text); 
      contents.endText();
      
      contents.beginText(); 
      contents.setFont(PDType1Font.COURIER_BOLD, 13);
      contents.newLineAtOffset(110, 640);
      String text1 = "Nombre del Producto";
      contents.showText(text1);
      contents.endText();
      
      contents.beginText(); 
      contents.setFont(PDType1Font.COURIER_BOLD, 13);
      contents.newLineAtOffset(350, 640);
      String text0 = "Cantidad Vendida";
      contents.showText(text0);
      contents.endText();
      
      AlgoritmoProductosVendidos m=new AlgoritmoProductosVendidos();
      m.llenar();
      ProductosUsados[] productos=m.getP();
      
      if(m.getCont()<10)
      {
        int cont=0;
        for(int i=0; i<m.getCont();i++)
        {
            contents.beginText(); 
            contents.setFont(PDType1Font.COURIER, 13);
            contents.newLineAtOffset(80, 600+cont);
            String text2 = String.valueOf(i+1)+". "+String.valueOf(productos[i].getNombre());
            contents.showText(text2);
            contents.endText();
            
            contents.beginText(); 
            contents.setFont(PDType1Font.COURIER, 13);
            contents.newLineAtOffset(350, 600+cont);
            String text12 = String.valueOf(productos[i].getUsado());
            contents.showText(text12);
            contents.endText();
            
            cont+=-20;
        }
      }
      else
      {
        int cont=0;
        for(int i=0; i<10;i++)
        {
            contents.beginText(); 
            contents.setFont(PDType1Font.COURIER, 13);
            contents.newLineAtOffset(80, 600+cont);
            String text2 = String.valueOf(i+1)+". "+String.valueOf(productos[i].getNombre());
            contents.showText(text2);
            contents.endText();
            
            contents.beginText(); 
            contents.setFont(PDType1Font.COURIER, 13);
            contents.newLineAtOffset(350, 600+cont);
            String text12 = String.valueOf(productos[i].getUsado());
            contents.showText(text12);
            contents.endText();
            
            cont+=-20;
        }
      }
      
      contents.beginText(); 
      contents.setFont(PDType1Font.COURIER, 10);
      contents.newLineAtOffset(80,78);
      String text7 = "GENERADO POR:"+nameuser;
      contents.showText(text7);
      contents.endText();
      
      contents.beginText(); 
      contents.setFont(PDType1Font.COURIER, 10);
      contents.newLineAtOffset(80, 61);
      java.util.Date fecha = new Date();
      String text8 = "FECHA Y HORA: "+String.valueOf(fecha);
      contents.showText(text8);
      contents.endText();
      
      contents.close();
      
      document.save("ListadoTopProductos.pdf");
      document.close();
      JOptionPane.showMessageDialog(null, "Se genero el reporte con éxito");
      abrirarchivo("ListadoTopProductos.pdf");
    }
    
    public void abrirarchivo(String archivo) {

        try {

            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
        }

    }
}
