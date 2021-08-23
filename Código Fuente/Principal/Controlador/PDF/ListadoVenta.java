
package Principal.Controlador.PDF;

import EstructuraDatos.AlgoritmosVentas;
import Principal.Controlador.VentaAgrupada;
import static Principal.Proyecto1_SAP.contVA;
import static Principal.Proyecto1_SAP.nameuser;
import static Principal.Proyecto1_SAP.ventasAgrupadas;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author Daniel Dubón
 */
public class ListadoVenta {
    
    public void pdf() throws IOException
    {
      PDDocument document = new PDDocument();
      PDPage blankPage = new PDPage();
      
      document.addPage(blankPage);
      PDImageXObject pdImage = PDImageXObject.createFromFile("Resources\\venta.jpg",document);
      PDPageContentStream contents = new PDPageContentStream(document, blankPage);
      contents.drawImage(pdImage, 0, 20);
      
      VentaAgrupada[] l=ordenarVentas(ventasAgrupadas);
      if(contVA<10)
      {
          int cont=0;
          for(int i=0;i<contVA;i++)
          {
            contents.beginText(); 
            contents.setFont(PDType1Font.HELVETICA, 12);
            contents.newLineAtOffset(120, 630+cont);
            String text = String.valueOf(l[i].getCodigo());
            contents.showText(text); 
            contents.endText();

            contents.beginText(); 
            contents.setFont(PDType1Font.HELVETICA, 12);
            contents.newLineAtOffset(280, 630+cont);
            String text2 = String.valueOf(l[i].getNit());
            contents.showText(text2);
            contents.endText();

            contents.beginText(); 
            contents.setFont(PDType1Font.HELVETICA, 13);
            contents.newLineAtOffset(445, 630+cont);
            String text3 = "Q "+String.valueOf(redondear(l[i].getTotal()));
            contents.showText(text3);
            contents.endText();
            cont-=20;
          }
      }
      
      else
      {
          int cont=0;
          for(int i=0;i<10;i++)
          {
            contents.beginText(); 
            contents.setFont(PDType1Font.HELVETICA, 12);
            contents.newLineAtOffset(120, 630+cont);
            String text = String.valueOf(l[i].getCodigo());
            contents.showText(text); 
            contents.endText();

            contents.beginText(); 
            contents.setFont(PDType1Font.HELVETICA, 12);
            contents.newLineAtOffset(280, 630+cont);
            String text2 = String.valueOf(l[i].getNit());
            contents.showText(text2);
            contents.endText();

            contents.beginText(); 
            contents.setFont(PDType1Font.HELVETICA, 13);
            contents.newLineAtOffset(445, 630+cont);
            String text3 = "Q "+String.valueOf(redondear(l[i].getTotal()));
            contents.showText(text3);
            contents.endText();
            cont-=20;
          }
      }
      contents.beginText(); 
      contents.setFont(PDType1Font.HELVETICA, 13);
      contents.newLineAtOffset(205,78);
      String text7 = nameuser;
      contents.showText(text7);
      contents.endText();
      
      contents.beginText(); 
      contents.setFont(PDType1Font.HELVETICA, 13);
      contents.newLineAtOffset(142, 61);
      java.util.Date fecha = new Date();
      String text8 = String.valueOf(fecha);
      contents.showText(text8);
      contents.endText();
      
      contents.close();
      
      document.save("ListadoVenta.pdf");
      document.close();
      JOptionPane.showMessageDialog(null, "Se genero el reporte con éxito");
      contVA=0;
      abrirarchivo("ListadoVenta.pdf");
    }
    
    public void abrirarchivo(String archivo) {

        try {

            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
        }

    }
    
    private VentaAgrupada[] ordenarVentas(VentaAgrupada[] ventas)
    {
        AlgoritmosVentas a=new AlgoritmosVentas();
        a.controlador();
        
        VentaAgrupada intercambio;
        for(int i=0; i<(contVA-1);i++)
        {
            for(int j=0; j<(contVA-1); j++)
            {
               if(ventas[j].getTotal()<ventas[j+1].getTotal())
               {
                   intercambio=ventas[j];
                   ventas[j]=ventas[j+1];
                   ventas[j+1]=intercambio;
               }
            }
        }
        return ventas;
    }
    
    public double redondear(double numero)
    {
        double aux=Math.round(numero*100)/100d;
        return aux;
    }
}
