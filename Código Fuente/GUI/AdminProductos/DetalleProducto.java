
package GUI.AdminProductos;

import GUI.AdminClientes.AdminClientes;
import GUI.AdminClientes.Modificar;
import GUI.Utilidades.Boton;
import GUI.Utilidades.Label;
import GUI.Utilidades.Panel;
import GUI.Utilidades.Texto;
import static Principal.Proyecto1_SAP.productos;
import static Principal.Proyecto1_SAP.contadorP;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Daniel Dubón
 */
public class DetalleProducto extends JFrame{
    Panel pane=new Panel();//panel principal
    Panel pane1=new Panel();//panel principal
    
    Color celeste=new Color(0, 102, 204);
    Color menta=new Color(26, 177, 136);
    Color azul=new Color(36, 49, 60);
    Color azulp=new Color(67, 81, 89);
    Color rosa=new Color(202, 41, 91);
    Color gris=new Color(236, 240, 241);
    
    private Boton modificar;
    private Boton eliminar;
    private Boton b;
    private Label md;
    private Label del;
    private Label bb;
    private Label txt;
    private Label n;
    private Label es;
    private Label s;
    private Texto buscar;
    private Label nombre;
    private Label edad;
    private Label sexo;
    private Label foto;
    
    int id=0;
    
    public DetalleProducto()
    {
        setSize(725, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Información de productos");
        setLocationRelativeTo(null);
        setResizable(false);
        agregarpaneles();
        agregarBtn();
        agregarLb();
        agregarTxt();
        cerrar();
    }
    
    private void agregarpaneles()
    {
        getContentPane().add(pane);
        pane.setBackground(Color.WHITE);
        pane1.setBounds(0, 0, 270, 502);
        pane1.setBackground(azul);
        pane.add(pane1);
    }
    
    private void agregarBtn()
    {
        modificar=new Boton("Modificar",66,184,180,40,16);
        modificar.setBackground(menta);
        pane1.add(modificar);
        
        eliminar=new Boton("Eliminar",66,242,180,40,16);
        eliminar.setBackground(menta);
        pane1.add(eliminar);
        
        
        b=new Boton(null,217,72,30,30);
        b.setBackground(azul);
        b.setIcon(setIconoB("Resources\\buscar.png",b));
        pane1.add(b);
        
        b.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
              
                    if(buscar(buscar.getText()))
                    {
                        nombre.setText(productos[id].getNombre());
                        edad.setText(String.valueOf(productos[id].getPrecio()));
                        sexo.setText(String.valueOf(productos[id].getCantidad()));
                        foto.setIcon(setIcono(productos[id].getImagen(),foto));
                        txt.setVisible(false);
                        bb.setVisible(false);
                        n.setVisible(true);
                        es.setVisible(true);
                        s.setVisible(true);
                        nombre.setVisible(true);
                        edad.setVisible(true);
                        sexo.setVisible(true);
                        foto.setVisible(true);
                    }
                    else
                    {
                        txt.setVisible(true);
                        bb.setVisible(true);
                        txt.setText("No se encontraron coincidencias");
                        n.setVisible(false);
                        es.setVisible(false);
                        s.setVisible(false);
                        nombre.setVisible(false);
                        edad.setVisible(false);
                        sexo.setVisible(false);
                        foto.setVisible(false);
                    }
                
            }
        });
        
        modificar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               
                    
                    if(buscar(buscar.getText()))
                    {
                        ModificarProducto abrir=new ModificarProducto(id,productos[id].getNombre(),productos[id].getPrecio(),productos[id].getCantidad(),productos[id].getImagen());
                        abrir.setVisible(true);
                        DetalleProducto.this.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"No se encontraron coincidencias");
                    }
               
            }
        });
        
        eliminar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    if(buscar(buscar.getText()))
                    {
                        int resp=JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar a: "+productos[id].getNombre()+"?","Alerta!",JOptionPane.YES_NO_OPTION);
                        if(resp==0)
                        {
                            for(int i=id;i<contadorP-1;i++)
                            {
                                productos[i]=null;
                                productos[i]=productos[i+1];
                            }contadorP--;
                            
                            if(contadorP==0)
                            {
                                //contadorCl++;
                                productos[0]=null;
                                //System.out.println("hola");
                            }
                            JOptionPane.showMessageDialog(null,"Se ha eliminado correctamente");
                            ocultar();
                            repaint();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"No se encontraron coincidencias");
                    }
               
            }
        });
        
    }
    
    private void agregarLb()
    {
        foto=new Label(435,40,120,130);
        foto.setOpaque(true);
        foto.setIcon(setIcono("Resources\\agregar.png",foto));
        foto.setVisible(false);
        pane.add(foto);
        
        md=new Label(29,184,40,40);
        md.setBackground(menta);
        md.setIcon(setIcono("Resources\\md.png",md));
        md.setOpaque(true);
        pane1.add(md);
        
        del=new Label(29,242,40,40);
        del.setBackground(menta);
        del.setIcon(setIcono("Resources\\del.png",del));
        del.setOpaque(true);
        pane1.add(del);
        
        bb=new Label(440,130,120,120);
        bb.setIcon(setIcono("Resources\\b1.png",bb));
        pane.add(bb);
        
        txt=new Label(395,260,220,15);
        txt.setForeground(rosa);
        txt.setText("Nueva Busqueda");
        pane.add(txt);
        
        int y=-80, x=80;
        n=new Label("Nombre:",340+x,266+y,98,34);
        n.setVisible(false);
        pane.add(n);
        
        es=new Label("Precio:",340+x,306+y,98,34);
        es.setVisible(false);
        pane.add(es);
        
        s=new Label("Cantidad",340+x,346+y,98,34);
        s.setVisible(false);
        pane.add(s);
             
        nombre=new Label("xxxxxxxxxx",420+x,266+y,250,34,true);
        nombre.setVisible(false);
        pane.add(nombre);
        
        edad=new Label("xxxxxxxxxx",420+x,306+y,250,34,true);
        edad.setVisible(false);
        pane.add(edad);
        
        sexo=new Label("xxxxxxxxxx",420+x,346+y,250,34,true);
        sexo.setVisible(false);
        pane.add(sexo);
    }
    
    private void agregarTxt()
    {
        buscar=new Texto(29,72,218,30);
        buscar.setText("Buscar");
        buscar.setBorder(null);
        buscar.setFont(new Font("Arial", Font.BOLD, 15));
        pane1.add(buscar);
        
        buscar.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                buscar.setText("");
                txt.setText("Nueva busqueda");
                ocultar();
                repaint();
            }
        });
    }
    
    private Icon setIcono(String path, JLabel label)
    {
        ImageIcon icon=new ImageIcon(path);
        int ancho=label.getWidth();
        int alto=label.getHeight();
        ImageIcon icono=new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return icono;
    }
    
    private Icon setIconoB(String path, JButton boton)
    {
        ImageIcon icon=new ImageIcon(path);
        int ancho=boton.getWidth();
        int alto=boton.getHeight();
        ImageIcon icono=new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return icono;
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D linea=(Graphics2D)g;
        linea.setStroke(new BasicStroke(2.f));
        linea.setPaint(rosa);
        linea.drawRect(29, 135, 219, 0);
    }
    
    public boolean buscar(String nombre)
    {
        boolean aux=false;
        for(int i=0; i<contadorP;i++)
        {
            if(nombre.equalsIgnoreCase(productos[i].getNombre().trim()))
            {
                aux= true;
                id=i;
                break;
            }
        }
        return aux;
    }
    
    public void cerrar()
    {
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent a){
                MenuProductos abrir=new MenuProductos();
                abrir.setVisible(true);
            }
        });
    }
    
    public void ocultar()
    {
        txt.setVisible(true);
        bb.setVisible(true);
        txt.setText("Nueva Busqueda");
        n.setVisible(false);
        es.setVisible(false);
        s.setVisible(false);
        nombre.setVisible(false);
        edad.setVisible(false);
        sexo.setVisible(false);
        foto.setVisible(false);
    }
}
