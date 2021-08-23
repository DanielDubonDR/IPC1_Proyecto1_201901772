
package Principal;

/**
 *
 * @author Daniel Dubón
 */
public class Funciones {
    
    public String texto(String txt, boolean negrita, int size){
    
        StringBuilder texto = new StringBuilder();
        texto.append("<html>")
                .append(negrita?"<b>":"")
                .append("<FONT").append(" SIZE='").append(size).append("'>")
                .append(txt)
                .append("</FONT>")
                .append(negrita?"</b>":"")
        .append("</html>");
    
        return texto.toString();
    }
    
    public void agruparVentas()
    {
//        for()
//        {
//            
//        }
    }
    
}
