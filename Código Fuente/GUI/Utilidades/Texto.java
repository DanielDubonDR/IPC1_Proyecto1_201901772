
package GUI.Utilidades;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

/**
 *
 * @author Daniel Dubón
 */
public class Texto extends JTextField{
    public Texto(int x, int y, int w, int h)
    {
        this.setOpaque(false);
        this.setBounds(x, y, w, h);
        this.setHorizontalAlignment(CENTER);
        this.setForeground(Color.white);
        this.setFont(new Font("Verdana", Font.PLAIN, 13));
    }
}
