
package GUI.AdminVentas;

import GUI.AdminProductos.MenuProductos;
import GUI.AdminProductos.ModificarProducto;
import GUI.Utilidades.Boton;
import GUI.Utilidades.Label;
import GUI.Utilidades.Panel;
import GUI.Utilidades.Texto;
import Principal.Funciones;
import static Principal.Proyecto1_SAP.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniel Dubón
 */
public class BuscarVenta extends JFrame {
    
    Panel pane=new Panel();//panel principal
    Panel pane1=new Panel();//panel principal
    
    Color celeste=new Color(0, 102, 204);
    Color menta=new Color(26, 177, 136);
    Color azul=new Color(36, 49, 60);
    Color azulp=new Color(67, 81, 89);
    Color rosa=new Color(202, 41, 91);
    Color gris=new Color(236, 240, 241);
    
    Funciones fn=new Funciones();//en esta funcion se encuentra mi modificador de texto
    
    private Boton b;
    private Label bb;
    private Label txt;
    private Label n;
    private Label es;
    private Label s;
    private Texto buscar;
    private Label nombre;
    private Label edad;
    private Label sexo;
    private Label iva;
    
    DefaultTableModel tb=new DefaultTableModel();
    JTable mytable=new JTable();
    private Panel panelTabla=new Panel();//panel principal
    private Label ctn=new Label(245,264,157,26);
    private Label name=new Label(90,264,154,26);
    int id=0,z=105;
    
    public BuscarVenta()
    {
        setSize(500, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Información de ventas");
        setLocationRelativeTo(null);
        setResizable(false);
        agregarpaneles();
        agregarBtn();
        agregarLb();
        agregarTxt();
        tabla();
        cerrar();
    }
    
    private void agregarpaneles()
    {
        getContentPane().add(pane);
        pane.setBackground(Color.WHITE);
        pane1.setBounds(0, 0, 500, 100);
        pane1.setBackground(azul);
        pane.add(pane1);
    }
    
    private void agregarBtn()
    {
        
        b=new Boton(null,217+z,32,30,30);
        b.setBackground(azul);
        b.setIcon(setIconoB("Resources\\buscar.png",b));
        pane1.add(b);
        
        b.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                repaint();
                if(vacio())
                {
                    if(verificarNumeros())
                    {
                        if(buscarID(Integer.parseInt(buscar.getText())))
                        {
                            llenarDatos();
                            ver();
                        }
                        else
                        {
                            ocultar();
                            txt.setText("Sin coincidencias");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "El codigo es de tipo numerico");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No se ha registrado ninguna venta aún");
                }
                
            }
        });
        
        
        
    }
    
    private void agregarLb()
    {  
        bb=new Label(185,250,120,120);
        bb.setIcon(setIcono("Resources\\b1.png",bb));
        pane.add(bb);
        
        txt=new Label(140,380,220,15);
        txt.setForeground(rosa);
        txt.setText("Nueva Busqueda");
        pane.add(txt);
        
        int y=-80, x=-190;
        n=new Label("NIT del cliente:",340+x,240+y,130,34);
        n.setVisible(false);
        pane.add(n);
        
        es=new Label("Productos en la venta:",347+x,280+y,180,34);
        es.setVisible(false);
        pane.add(es);
        
        s=new Label("Total de la venta:",340+x,536+y,130,34);
        s.setVisible(false);
        pane.add(s);
             
        nombre=new Label("xxxxxxxxxx",470+x,240+y,250,34,true);
        nombre.setVisible(false);
        pane.add(nombre);
        
        edad=new Label("IVA:",340+x,570+y,250,34);
        edad.setVisible(false);
        pane.add(edad);
        
        iva=new Label("IVA:",472+x,570+y,250,34,true);
        iva.setVisible(false);
        pane.add(iva);
        
        sexo=new Label("xxxxxxxxxx",472+x,536+y,250,34,true);
        sexo.setVisible(false);
        pane.add(sexo);
        
        
        name.setText(fn.texto("Producto", true,3));
        name.setOpaque(true);
        name.setBackground(celeste);
        name.setVisible(false);
        pane.add(name);
        
        
        ctn.setText(fn.texto("Cantidad", true,3));
        ctn.setOpaque(true);
        ctn.setBackground(celeste);
        ctn.setVisible(false);
        pane.add(ctn);
    }
    
    private void agregarTxt()
    {
        buscar=new Texto(29+z,32,218,30);
        buscar.setText("Buscar");
        buscar.setBorder(null);
        buscar.setFont(new Font("Arial", Font.BOLD, 15));
        pane1.add(buscar);
        
        buscar.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                buscar.setText("");
                tb.setRowCount(0);
                txt.setText("Nueva Busqueda");
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
        linea.drawRect(29+z, 95, 219, 0);
    }
    
    public boolean buscarID(int ida)
    {
        boolean aux=false;
        for(int i=0; i<contadorV;i++)
        {
            if(ida==ventas[i].getCodigo())
            {
                aux= true;
                id=ventas[i].getCodigo();
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
                MenuVentas abrir=new MenuVentas();
                abrir.setVisible(true);
            }
        });
    }
    
    private void ver()
    {
        nombre.setText(String.valueOf(ventas[id].getNit()));
        sexo.setText(String.valueOf(redondear(obtenerTotal())));
        iva.setText(String.valueOf(redondear(obtenerTotal()*0.12)));
        iva.setVisible(true);
        txt.setVisible(false);
        bb.setVisible(false);
        n.setVisible(true);
        es.setVisible(true);
        s.setVisible(true);
        nombre.setVisible(true);
        edad.setVisible(true);
        sexo.setVisible(true);
        panelTabla.setVisible(true);
        scrol.setVisible(true);
        name.setVisible(true);
        ctn.setVisible(true);
    }
    
    private void ocultar()
    {
        txt.setVisible(true);
        bb.setVisible(true);
        n.setVisible(false);
        es.setVisible(false);
        s.setVisible(false);
        nombre.setVisible(false);
        edad.setVisible(false);
        sexo.setVisible(false);
        scrol.setVisible(false);
        name.setVisible(false);
        ctn.setVisible(false);
        iva.setVisible(false);
    }
    JScrollPane scrol=new JScrollPane();
    public void tabla()
    {
        //cont=6;
        
        scrol.setBounds(90, 290, 313, 150);
        panelTabla.setLayout(new BorderLayout());
        pane.add(scrol);
        scrol.setViewportView(panelTabla);
        panelTabla.setPreferredSize(new Dimension(295,145));
        tb.addColumn("Producto");
        tb.addColumn("Cantidad");
        mytable.setModel(tb);
        panelTabla.add(mytable);
        mytable.setModel(tb);
        mytable.repaint();
        scrol.setVisible(false);
    }
    
    private boolean vacio()
    {
        boolean aux=true;
        if(ventas[0]==null)
        {
            aux=false;
        }
        return aux;
    }
    public boolean verificarNumeros()
    {
        if(!buscar.getText().matches("[0-9]*$"))
        {
            return false;
        }
        else return true;
    }
    
    public void llenarDatos()
    {
        int cont=0;
        for(int i=0; i<contadorV;i++)
        {
            if(id==ventas[i].getCodigo())
            {
                tb.addRow(new Object[]{ventas[i].getNombreproducto(),ventas[i].getCantidad()});
                if (cont > 8) 
                {
                    panelTabla.setPreferredSize(new Dimension(295, 145 + ((cont - 8) * 16)));
                } else 
                {
                    panelTabla.setPreferredSize(new Dimension(295, 145));
                }
                cont++;
                mytable.setModel(tb);
                mytable.repaint();
            }
        }
    }
    
    private double obtenerTotal()
    {
        double total=0;
        for(int i=0; i<contadorP;i++)
        {
            if(id==ventas[i].getCodigo())
            {
                total+=buscarPrecio(ventas[i].getNombreproducto())*ventas[i].getCantidad();
            }
        }
        return total;
    }
    
    public double buscarPrecio(String nombre)
    {
        int id=0;
        for(int i=0; i<contadorP;i++)
        {
            if(nombre.equalsIgnoreCase(productos[i].getNombre().trim()))
            {
                id=i;
                break;
            }
        }
        return productos[id].getPrecio();
    }
    
    public double redondear(double numero)
    {
        double aux=Math.round(numero*100)/100d;
        return aux;
    }
}
