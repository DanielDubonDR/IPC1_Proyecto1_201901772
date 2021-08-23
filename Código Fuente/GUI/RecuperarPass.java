
package GUI;

import GUI.Utilidades.Boton;
import GUI.Utilidades.Label;
import GUI.Utilidades.Panel;
import GUI.Utilidades.Texto;
import Principal.Funciones;
import static Principal.Proyecto1_SAP.contador;
import static Principal.Proyecto1_SAP.nameuser;
import static Principal.Proyecto1_SAP.users;
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
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author Daniel Dubón
 */
public class RecuperarPass extends JFrame {
    
    Funciones fn=new Funciones();//en esta funcion se encuentra mi modificador de texto
    Panel pane=new Panel();//panel principal
    
    //Creacion de nuevos colores
    Color celeste=new Color(0, 102, 204);
    Color menta=new Color(26, 177, 136);
    Color azul=new Color(36, 49, 60);
    Color azulp=new Color(67, 81, 89);
    Color rosa=new Color(202, 41, 91);
    
    
    private int m=-200;
    Texto User=new Texto(42, 243+m, 305, 35);
    Texto Pass=new Texto(42, 310+m, 305, 35);
    
    public RecuperarPass()
    {
        setSize(388, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Recuperar Contraseña");
        setLocationRelativeTo(null);
        setResizable(false);
        agregarpaneles();
        botones();
        textos();
        close();
    }
    
    private void agregarpaneles()
    {
        getContentPane().add(pane);
        pane.setBackground(azul);
    }
    
    private void botones()
    {
        Boton btnA=new Boton(fn.texto("RECUPERAR", true,4),62,386+m,264,44);
        btnA.setBackground(azulp);
        btnA.setForeground(Color.WHITE);
        btnA.setBorderPainted(true);
        btnA.setBorder(null);
        pane.add(btnA);
        
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
                      if(buscar(Pass.getText()))
                      {
                          System.out.println(users[obtenerID(User.getText())]);
                          int id=obtenerID(User.getText());
                          if(users[id].getUsuario().equals(Pass.getText()) && users[id].getNombre().equals(User.getText()))
                          {
                             JOptionPane.showMessageDialog(null,"Su contraseña es: "+users[id].getContrasenia());
                             Login s=new Login();
                             s.setVisible(true);
                             RecuperarPass.this.dispose();
                          }
                          else
                          {
                              JOptionPane.showMessageDialog(null,"Usuario o nombre incorrecto");
                          }
                      }
                      else
                      {
                          JOptionPane.showMessageDialog(null,"Usuario o nombre incorrecto");
                      }
                  }
            }
        });
        
    }
    public boolean buscar(String user)
    {
        boolean aux=false;
        for(int i=0; i<contador;i++)
        {
            if(user.equals(users[i].getUsuario()))
            {
                aux= true;
                break;
            }
        }
        return aux;
    }
    
    public int obtenerID(String user)
    {
        int aux=0;
        for(int i=0; i<contador;i++)
        {
            if(user.equals(users[i].getUsuario()))
            {
                aux=i;
                break;
            }
        }
        return aux;
    }
    
    public boolean verificarEspacios()
        {
            if(User.getText().equals("")||Pass.equals("")||Pass.equals("Usuario")||User.getText().equals("Nombre"))
            {
                return true;
            }
            else return false;
        }
    
    private void textos()
    {
        
        User.setText("Nombre");
        User.setBorder(null);
        pane.add(User);
        
        Pass.setText("Usuario");
        Pass.setBorder(null);
        pane.add(Pass);
        
        User.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                User.setText("");
            }
        });
        
        Pass.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent a)
            {
                Pass.setText("");
            }
        });
    }
    
   
    
    public void paint(Graphics g){
        super.paint(g);
        
        Graphics2D linea2=(Graphics2D)g;
        linea2.setStroke(new BasicStroke(2.f));
        linea2.setPaint(rosa);
        linea2.drawRect(42, 307+m, 305, 0);
        
        Graphics2D linea3=(Graphics2D)g;
        linea3.setStroke(new BasicStroke(2.f));
        linea3.setPaint(rosa);
        linea3.drawRect(42, 375+m, 305, 0);
        
    }
    
    public void close()
    {
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent a){
                Login abrir=new Login();
                abrir.setVisible(true);
            }
        });
    }
    
}
