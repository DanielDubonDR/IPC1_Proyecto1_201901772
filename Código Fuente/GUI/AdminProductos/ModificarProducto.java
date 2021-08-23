
package GUI.AdminProductos;

import GUI.Utilidades.Boton;
import GUI.Utilidades.Label;
import GUI.Utilidades.Panel;
import GUI.Utilidades.Texto;
import Principal.Controlador.Producto;
import Principal.Funciones;
import static Principal.Proyecto1_SAP.contadorP;
import static Principal.Proyecto1_SAP.productos;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Daniel Dubón
 */
public class ModificarProducto extends JFrame {
    Funciones fn=new Funciones();//en esta funcion se encuentra mi modificador de texto
    Panel pane=new Panel();//panel principal
    
    //Creacion de nuevos colores
    Color celeste=new Color(0, 102, 204);
    Color menta=new Color(26, 177, 136);
    Color azul=new Color(36, 49, 60);
    Color azulp=new Color(67, 81, 89);
    Color rosa=new Color(202, 41, 91);
    Color gris=new Color(236, 240, 241);
    
    private boolean abierto;
    
    int y=30;
    //hacer global los campos de texto
    private Texto Nombre=new Texto(38, 150+y, 313, 31);
    private Texto Edad=new Texto(38, 214+y, 313, 31);
    private Texto Nit=new Texto(38, 282+y, 313, 31);
    
    private String name, imagen;
    private int cantidad,id;
    private float precio;
    
    JComboBox combo=new JComboBox();
    private File path;
    
    private void setNombre(String nombre)
    {
        this.name=nombre;
    }
    
    private void setPrecio(float precio)
    {
        this.precio=precio;
    }
    
    
    private void setCantidad(int cantidad)
    {
        this.cantidad=cantidad;
    }
    
    private void setImagen(String imagen)
    {
        this.imagen=imagen;
    }
    
    private void setId(int id)
    {
        this.id=id;
    }
    
    public ModificarProducto(int id, String nombre, float precio, int cantidad, String imagen)
    {
        setSize(388, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Modificar Producto");
        setLocationRelativeTo(null);
        setResizable(false);
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
        setCantidad(cantidad);
        setImagen(imagen);
        agregarpaneles();
        botones();
        textos();
        agregarLabel();
        close();
    }
    
    private void agregarpaneles()
    {
        getContentPane().add(pane);
        pane.setBackground(azul);
    }
    
    private void botones()
    {
        Boton cliente=new Boton(null,130,20,126,130);
        cliente.setBackground(azulp);
        cliente.setIcon(setIcono(imagen,cliente));
        pane.add(cliente);
         
        Boton btnA=new Boton(fn.texto("MODIFICAR", true,4),38,416-y,313,44);
        btnA.setBackground(azulp);
        btnA.setForeground(Color.WHITE);
        btnA.setBorderPainted(true);
        btnA.setBorder(null);
        pane.add(btnA);
        
        cliente.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                abrir();
                if(abierto)
                {
                    cliente.setIcon(setIcono(path.toString(),cliente));
                }
            }
        });
        
        btnA.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(verificarEspacios())
                {
                    JOptionPane.showMessageDialog(null,"Llene todos los campos");
                }
                else
                { 
                    
                        if(buscar(Nombre.getText()))
                        {
                            JOptionPane.showMessageDialog(null,"Ya existe un producto con el mismo nombre");
                        }
                        else
                        {
                            if(abierto)
                            {
                                Producto aux=new Producto(Nombre.getText(),Float.parseFloat(Edad.getText()),Integer.parseInt(Nit.getText()),path.toString());
                                productos[id]=aux;
                                //contadorCl++;
                                JOptionPane.showMessageDialog(null,"Cliente modificado con éxito");
                                DetalleProducto z=new DetalleProducto();
                                z.setVisible(true);
                                ModificarProducto.this.dispose();
                            }
                            else
                            {
                                Producto aux=new Producto(Nombre.getText(),Float.parseFloat(Edad.getText()),Integer.parseInt(Nit.getText()),imagen);
                                productos[id]=aux;
                                JOptionPane.showMessageDialog(null,"Producto modificado con éxito");
                                DetalleProducto z=new DetalleProducto();
                                z.setVisible(true);
                                ModificarProducto.this.dispose();
                            }
                        }
                  
                }
            }
        });
    }
    
    public void agregarLabel()
    {
        Label user=new Label(145,183+y,100,20);
        user.setText(fn.texto("Nombre", true,3));
        pane.add(user);
        
        Label nombre=new Label(145,248+y,100,20);
        nombre.setText(fn.texto("Precio", true,3));
        pane.add(nombre);
        
        Label pass=new Label(145,315+y,100,20);
        pass.setText(fn.texto("Cantidad", true,3));
        pane.add(pass);
        
    }
    
    private void textos()
    {
        Nombre.setText(name);
        pane.add(Nombre);
        Edad.setText(String.valueOf(precio));
        pane.add(Edad);
        Nit.setText(String.valueOf(cantidad));
        pane.add(Nit);
    }
        
    public void close()
    {
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent a){
                DetalleProducto z=new DetalleProducto();
                z.setVisible(true);
            }
        });
    }
    
    private Icon setIcono(String path, JButton boton)
    {
        ImageIcon icon=new ImageIcon(path);
        int ancho=boton.getWidth();
        int alto=boton.getHeight();
        ImageIcon icono=new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return icono;
    }
    

 
    private void abrir()
    {
        JFileChooser choose=new JFileChooser();
        choose.setCurrentDirectory(new java.io.File("."));
        choose.setDialogTitle("Cargar Archivo");
        FileNameExtensionFilter filtro=new FileNameExtensionFilter("jpg, png, jpeg & gif","jpg","png","gif","jpeg");
        choose.addChoosableFileFilter(filtro);
        choose.setAcceptAllFileFilterUsed(true);
        
        if(choose.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            path =choose.getSelectedFile();
            abierto=true;
        }
        else
        {
            System.out.println("No se abrio archivo");
            abierto=false;
        }
    }
    
    public boolean verificarEspacios()
        {
            if(Nombre.getText().equals("")|Edad.getText().equals("")|Nit.getText().equals(""))
            {
                return true;
            }
            else return false;
        }
    
    public boolean buscar(String nombre)
    {
        boolean aux=false;
        if(nombre.equalsIgnoreCase(name))
        {
            aux=false;
        }
        else
        {
            for(int i=0; i<contadorP;i++)
            {
                if(nombre.equalsIgnoreCase(productos[i].getNombre()))
                {
                    aux= true;
                    break;
                }
            }
        }
        return aux;
    }
    
   
}
