
package GUI.Reportes;

import GUI.Menu;
import GUI.Utilidades.Boton;
import GUI.Utilidades.Label;
import GUI.Utilidades.Panel;
import Principal.Funciones;
import static Principal.Proyecto1_SAP.clientes;
import static Principal.Proyecto1_SAP.ventas;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Daniel Dubón
 */
public class MenuReporte extends JFrame{
    
    Funciones fn=new Funciones();//en esta funcion se encuentra mi modificador de texto
    Panel pane=new Panel();//panel principal
    Panel pane1=new Panel();//panel principal
    
    //Creacion de nuevos colores
    Color azul=new Color(36, 49, 60);
    Color gris=new Color(236, 240, 241);
    Color celeste=new Color(0, 102, 204);
    Color rosa=new Color(26, 177, 136);
    Color menta=new Color(26, 177, 136);
    
    public MenuReporte()
    {
        setSize(500,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Administración de Reportes");
        setLocationRelativeTo(null);
        setResizable(false);
        agregarpaneles();
        textos();
        botones();
        etiquetas();
        cerrar();
    }
    
    private void agregarpaneles()
    {
        getContentPane().add(pane);
        pane.setBackground(Color.WHITE);
        pane1.setBounds(0, 0, 800, 90);
        pane1.setBackground(azul);
        pane.add(pane1);
    }
    
    private void textos()
    {
        Label lb3=new Label(77,12,800,60);
        lb3.setText(fn.texto("Administración de reportes", true,6));
        lb3.setHorizontalAlignment(LEFT);
        pane1.add(lb3);
        
        JLabel s0=new JLabel();
        s0.setBounds(15, 12, 60, 60);
        ImageIcon im= new ImageIcon("Resources\\reporte.png");
        Icon scale=new ImageIcon(im.getImage().getScaledInstance(s0.getWidth(), s0.getHeight(), Image.SCALE_DEFAULT));
        s0.setIcon(scale);
        pane1.add(s0);
    }
    
    private void botones()
    {
        int x=0, y=-70;
        Boton clientes=new Boton(null,106+x,220+y,126,126);
        clientes.setBackground(gris);
        clientes.setIcon(setIcono("Resources\\html.png",clientes));
        pane.add(clientes);
        
        Boton cargar=new Boton(null,265+x,220+y,126,126);
        cargar.setBackground(gris);
        cargar.setIcon(setIcono("Resources\\reporte.png",cargar));
        pane.add(cargar);
        
        
        clientes.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ReporteHtml abrir=new ReporteHtml();
                abrir.setVisible(true);
                MenuReporte.this.dispose();
            }
        });
        
        cargar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                 ReportePDFA a=new ReportePDFA();
                 a.setVisible(true);
                 MenuReporte.this.dispose();
            }
            
        });
        
    }
    
    private void etiquetas()
    {
        int y=-60;
        Label cliente=new Label(106,336+y,126,50);
        cliente.setText(fn.texto("Reportes en HTML", true,3));
        cliente.setForeground(celeste);
        pane.add(cliente);
        
        Label producto=new Label(265,334+y,126,50);
        producto.setText(fn.texto("Reportes en PDF", true,3));
        producto.setForeground(celeste);
        pane.add(producto);
        
    }
    
    private Icon setIcono(String path, JButton boton)
    {
        ImageIcon icon=new ImageIcon(path);
        int ancho=boton.getWidth();
        int alto=boton.getHeight();
        ImageIcon icono=new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return icono;
    }
    
    public boolean verificarVacio()
    {
        if(ventas[0]==null && clientes[0]==null)
        {
            return true;
        }
        return false;
    }
    
    public void cerrar()
    {
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent a){
                Menu abrir=new Menu();
                abrir.setVisible(true);
            }
        });
    }
}
