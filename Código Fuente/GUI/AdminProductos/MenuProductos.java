
package GUI.AdminProductos;

import EstructuraDatos.ManejadorArchivos;
import GUI.Menu;
import GUI.Utilidades.Boton;
import GUI.Utilidades.Label;
import GUI.Utilidades.Panel;
import Principal.Funciones;
import static Principal.Proyecto1_SAP.productos;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Daniel Dubón
 */
public class MenuProductos extends JFrame {
    Funciones fn=new Funciones();//en esta funcion se encuentra mi modificador de texto
    Panel pane=new Panel();//panel principal
    Panel pane1=new Panel();//panel principal
    
    //Creacion de nuevos colores
    Color azul=new Color(36, 49, 60);
    Color gris=new Color(236, 240, 241);
    Color celeste=new Color(0, 102, 204);
    Color rosa=new Color(26, 177, 136);
    Color menta=new Color(26, 177, 136);
    
    public MenuProductos()
    {
        setSize(800,430);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Administración de productos");
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
        lb3.setText(fn.texto("Administración de productos", true,6));
        lb3.setHorizontalAlignment(LEFT);
        pane1.add(lb3);
        
        JLabel s0=new JLabel();
        s0.setBounds(15, 12, 60, 60);
        ImageIcon im= new ImageIcon("Resources\\producto.png");
        Icon scale=new ImageIcon(im.getImage().getScaledInstance(s0.getWidth(), s0.getHeight(), Image.SCALE_DEFAULT));
        s0.setIcon(scale);
        pane1.add(s0);
    }
    
    private void botones()
    {
        int x=0, y=-70;
        Boton clientes=new Boton(null,96+x,220+y,126,126);
        clientes.setBackground(gris);
        clientes.setIcon(setIcono("Resources\\barras.png",clientes));
        pane.add(clientes);
        
        Boton cargar=new Boton(null,255+x,220+y,126,126);
        cargar.setBackground(gris);
        cargar.setIcon(setIcono("Resources\\cg.png",cargar));
        pane.add(cargar);
        
        Boton lista=new Boton(null,414+x,220+y,126,126);
        lista.setBackground(gris);
        lista.setIcon(setIcono("Resources\\i.png",lista));
        pane.add(lista);
        
        Boton agregar=new Boton(null,568+x,220+y,126,126);
        agregar.setBackground(gris);
        agregar.setIcon(setIcono("Resources\\mas.png",agregar));
        pane.add(agregar);
        
        
        clientes.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(verificarVacio())
                {
                    JOptionPane.showMessageDialog(null, "No existe ningún producto");
                }
                else
                {
                    DashboardProductos abri=new DashboardProductos();
                    abri.setVisible(true);
                }
            }
        });
        
        cargar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    ManejadorArchivos abrir=new ManejadorArchivos();
                    abrir.abrir();
                    if(abrir.getAbierto())
                    {
                        try 
                        {
                            abrir.cargarProductos();
                            JOptionPane.showMessageDialog(null, "Datos cargados correctamente");
                        } catch (IOException ex) 
                        {
                            JOptionPane.showMessageDialog(null, "Error al cargar datos");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se cargó ningún archivo");
                    }
            }
            
        });
        
        agregar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AgregarProducto abrir=new AgregarProducto();
                abrir.setVisible(true);
                MenuProductos.this.dispose();
            }
        });
        
        lista.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(verificarVacio())
                {
                    JOptionPane.showMessageDialog(null, "No existe ningún cliente registrado");
                }
                else
                {
                    DetalleProducto abri=new DetalleProducto();
                    abri.setVisible(true);
                    MenuProductos.this.dispose();
                }
            }
        });
    }
    
    private void etiquetas()
    {
        int y=-60;
        Label cliente=new Label(96,336+y,126,50);
        cliente.setText(fn.texto("Dashboard de productos", true,3));
        cliente.setForeground(celeste);
        pane.add(cliente);
        
        Label producto=new Label(255,334+y,126,50);
        producto.setText(fn.texto("Carga masiva de productos", true,3));
        producto.setForeground(celeste);
        pane.add(producto);
        
        Label ventas=new Label(414,334+y,126,50);
        ventas.setText(fn.texto("Información de productos", true,3));
        ventas.setForeground(celeste);
        pane.add(ventas);
        
        Label reportes=new Label(568,330+y,126,50);
        reportes.setText(fn.texto("Crear producto", true,3));
        reportes.setForeground(celeste);
        pane.add(reportes);
        
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
        if(productos[0]==null)
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
