
package GUI;

import GUI.Utilidades.Boton;
import GUI.Utilidades.Label;
import GUI.Utilidades.Panel;
import GUI.Utilidades.Texto;
import Principal.Funciones;
import static Principal.Proyecto1_SAP.contador;
import static Principal.Proyecto1_SAP.users;
import static Principal.Proyecto1_SAP.nameuser;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author Daniel Dubón
 */
public class Login extends JFrame {
    
    Funciones fn=new Funciones();//en esta funcion se encuentra mi modificador de texto
    Panel pane=new Panel();//panel principal
    
    //Creacion de nuevos colores
    Color celeste=new Color(0, 102, 204);
    Color menta=new Color(26, 177, 136);
    Color azul=new Color(36, 49, 60);
    Color azulp=new Color(67, 81, 89);
    Color rosa=new Color(202, 41, 91);
    
    JPasswordField Pass=new JPasswordField();
    Texto User=new Texto(42, 243, 305, 35);
    
    public Login()
    {
        setSize(388, 508);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login");
        setLocationRelativeTo(null);
        setResizable(false);
        agregarpaneles();
        imagen();
        botones();
        tex();
        textos();
    }
    
    private void agregarpaneles()
    {
        getContentPane().add(pane);
        pane.setBackground(azul);
    }
    
    private void botones()
    {
        Boton btnlogin=new Boton(fn.texto("INGRESAR", true,4),56,36,128,34);
        btnlogin.setBackground(azul);
        btnlogin.setForeground(Color.WHITE);
        pane.add(btnlogin);
        
        Boton btnR=new Boton(fn.texto("REGISTRAR", true,4),201,36,128,34);
        btnR.setBackground(azul);
        btnR.setForeground(Color.WHITE);
        pane.add(btnR);
        
        Boton btnA=new Boton(fn.texto("INICIAR SESION", true,4),62,386,264,44);
        btnA.setBackground(azulp);
        btnA.setForeground(Color.WHITE);
        btnA.setBorderPainted(true);
        btnA.setBorder(null);
        pane.add(btnA);
        
        btnR.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Registro abrir=new Registro();
                abrir.setVisible(true);
                Login.this.dispose();
            }
        });
        
        btnA.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                  String pass=String.valueOf(Pass.getPassword());
                  if(verificarEspacios(pass))
                  {
                      JOptionPane.showMessageDialog(null,"Llene todos los campos");
                  }
                  else
                  {
                      if(buscar(User.getText()))
                      {
                          System.out.println(users[obtenerID(User.getText())]);
                          int id=obtenerID(User.getText());
                          if(users[id].getContrasenia().equals(pass))
                          {
                             nameuser=users[id].getNombre();
                             Menu abrir=new Menu();
                             abrir.setVisible(true);
                             Login.this.dispose();
                          }
                          else
                          {
                              JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta");
                          }
                      }
                      else
                      {
                          JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta");
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
    
    public boolean verificarEspacios(String pass)
        {
            if(User.getText().equals("")||pass.equals("")||pass.equals("Contraseña")||User.getText().equals("Usuario"))
            {
                return true;
            }
            else return false;
        }
    
    private void imagen()
    {
        JLabel s0 = new JLabel(new ImageIcon("Resources\\img1.png"));
        s0.setBounds(129, 98, 131, 131);
        pane.add(s0);
    }
    
    private void textos()
    {
        
        User.setText("Usuario");
        User.setBorder(null);
        pane.add(User);
        
        
        Pass.setBounds(42, 310, 305, 35);
        Pass.setOpaque(false);
        Pass.setForeground(Color.white);
        Pass.setHorizontalAlignment(CENTER);
        Pass.setText("Contraseña");
        Pass.setEchoChar((char)0);
        Pass.setBorder(null);
        Pass.setFont(new Font("Verdana", Font.PLAIN, 13));
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
                Pass.setEchoChar('•');
            }
        });
    }
    
    public void tex()
    {
        Label recuperar=new Label(fn.texto("¿Olvidó su contraseña?", true,2),130,448,200,10);
        recuperar.setForeground(Color.white);
        pane.add(recuperar);
        
        recuperar.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent a)
            {
                RecuperarPass z=new RecuperarPass();
                z.setVisible(true);
                Login.this.dispose();
            }
        });
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D linea=(Graphics2D)g;
        linea.setStroke(new BasicStroke(2.f));
        linea.setPaint(menta);
        linea.drawRect(59, 98, 128, 0);
        
        Graphics2D linea2=(Graphics2D)g;
        linea2.setStroke(new BasicStroke(2.f));
        linea2.setPaint(rosa);
        linea2.drawRect(42, 307, 305, 0);
        
        Graphics2D linea3=(Graphics2D)g;
        linea3.setStroke(new BasicStroke(2.f));
        linea3.setPaint(rosa);
        linea3.drawRect(42, 375, 305, 0);
        
    }
}
