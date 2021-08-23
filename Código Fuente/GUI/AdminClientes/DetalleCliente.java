
package GUI.AdminClientes;

import GUI.Menu;
import GUI.Utilidades.Boton;
import GUI.Utilidades.Label;
import GUI.Utilidades.Panel;
import GUI.Utilidades.Texto;
import Principal.Controlador.Cliente;
import static Principal.Proyecto1_SAP.clientes;
import static Principal.Proyecto1_SAP.contadorCl;
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

/**
 *
 * @author Daniel Dubón
 */
public class DetalleCliente extends JFrame {
    
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
    private Label nt;
    private Texto buscar;
    private Label nombre;
    private Label edad;
    private Label sexo;
    private Label nit;
    private Label foto;
    
    int id=0;
    
    public DetalleCliente()
    {
        setSize(725, 430);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Información Clientes");
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
                if(verificarNumeros())
                {
                    if(buscar(Integer.parseInt(buscar.getText())))
                    {
                        nombre.setText(clientes[id].getNombre());
                        edad.setText(String.valueOf(clientes[id].getEdad()));
                        sexo.setText(String.valueOf(clientes[id].getSexo()));
                        nit.setText(String.valueOf(clientes[id].getNit()));
                        foto.setIcon(setIcono(clientes[id].getAvatar(),foto));
//                        System.out.println(clientes[id].getAvatar());
                        txt.setVisible(false);
                        bb.setVisible(false);
                        n.setVisible(true);
                        es.setVisible(true);
                        s.setVisible(true);
                        nt.setVisible(true);
                        nombre.setVisible(true);
                        edad.setVisible(true);
                        sexo.setVisible(true);
                        nit.setVisible(true);
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
                        nt.setVisible(false);
                        nombre.setVisible(false);
                        edad.setVisible(false);
                        sexo.setVisible(false);
                        nit.setVisible(false);
                        foto.setVisible(false);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Ingrese solo numeros en los campos que lo requieran");
                }
            }
        });
        
        modificar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(verificarNumeros())
                {
                    
                    if(buscar(Integer.parseInt(buscar.getText())))
                    {
                        Modificar abrir=new Modificar(id,clientes[id].getNombre(),clientes[id].getEdad(),clientes[id].getSexo(),clientes[id].getNit(),clientes[id].getAvatar());
                        abrir.setVisible(true);
                        DetalleCliente.this.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"No se encontraron coincidencias");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Ingrese solo numeros en los campos que lo requieran");
                }
            }
        });
        
        eliminar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(verificarNumeros())
                {
                    if(buscar(Integer.parseInt(buscar.getText())))
                    {
                        int resp=JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar a: "+clientes[id].getNombre()+"?","Alerta!",JOptionPane.YES_NO_OPTION);
                        if(resp==0)
                        {
                            for(int i=id;i<contadorCl-1;i++)
                            {
                                clientes[i]=null;
                                clientes[i]=clientes[i+1];
                            }contadorCl--;
                            if(contadorCl==0)
                            {
                                //contadorCl++;
                                clientes[0]=null;
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
                else
                {
                    JOptionPane.showMessageDialog(null,"Ingrese solo numeros en los campos que lo requieran");
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
        
        es=new Label("Edad:",340+x,306+y,98,34);
        es.setVisible(false);
        pane.add(es);
        
        s=new Label("Sexo",340+x,346+y,98,34);
        s.setVisible(false);
        pane.add(s);
        
        nt=new Label("NIT:",340+x,386+y,98,34);
        nt.setVisible(false);
        pane.add(nt);
        
        nombre=new Label("xxxxxxxxxx",420+x,266+y,250,34,true);
        nombre.setVisible(false);
        pane.add(nombre);
        
        edad=new Label("xxxxxxxxxx",420+x,306+y,250,34,true);
        edad.setVisible(false);
        pane.add(edad);
        
        sexo=new Label("xxxxxxxxxx",420+x,346+y,250,34,true);
        sexo.setVisible(false);
        pane.add(sexo);
        
        nit=new Label("xxxxxxxxxx",420+x,386+y,250,34,true);
        nit.setVisible(false);
        pane.add(nit);
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
        linea.drawRect(29, 135, 219, 0);
    }
    
    public boolean buscar(int nit)
    {
        boolean aux=false;
        for(int i=0; i<contadorCl;i++)
        {
            if(nit==clientes[i].getNit())
            {
                aux= true;
                id=i;
                break;
            }
        }
        return aux;
    }
    
    public boolean verificarNumeros()
    {
        if(buscar.getText().matches("[0-9]*$"))
        {
            return true;
        }
        else return false;
    }
    
    public void cerrar()
    {
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent a){
                AdminClientes abrir=new AdminClientes();
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
        nt.setVisible(false);
        nombre.setVisible(false);
        edad.setVisible(false);
        sexo.setVisible(false);
        nit.setVisible(false);
        foto.setVisible(false);
    }
}
