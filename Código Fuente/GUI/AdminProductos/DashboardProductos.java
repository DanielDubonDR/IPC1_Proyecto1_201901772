
package GUI.AdminProductos;

import EstructuraDatos.AlgoritmoProductosVendidos;
import EstructuraDatos.ManejadorArchivos;
import GUI.Utilidades.Label;
import GUI.Utilidades.Panel;
import Principal.Controlador.ProductosUsados;
import Principal.Funciones;
import static Principal.Proyecto1_SAP.contadorP;
import static Principal.Proyecto1_SAP.productos;
import static Principal.Proyecto1_SAP.ventas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author Daniel Dubón
 */
public class DashboardProductos extends JFrame{
    Funciones fn=new Funciones();//en esta funcion se encuentra mi modificador de texto
    Panel pane=new Panel();//panel principal
    Panel pane1=new Panel();//panel de barra
    Panel panelTabla=new Panel();//panel principal
    
    //Creacion de nuevos colores
    Color azul=new Color(36, 49, 60);
    Color gris=new Color(236, 240, 241);
    Color celeste=new Color(0, 102, 204);
    Color menta=new Color(26, 177, 136);
    
    private JTable tabla;
    
    
    public DashboardProductos()
    {
        setSize(820, 630);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Dashboard Clientes");
        setLocationRelativeTo(null);
        setResizable(false);
        agregarpaneles();
        etiquetas();
        agregarTabla();
        graficarPie();
        graficarBarras();
//        cerrar();
    }
    
    private void agregarpaneles()
    {
        //panel principal
        getContentPane().add(pane);
        
        //panel dash
        pane.setBackground(Color.WHITE);
        pane1.setBounds(0, 0, 810, 85);
        pane1.setBackground(azul);
        pane.add(pane1);
        
        //tabla
        JScrollPane scrol=new JScrollPane();
        scrol.setBounds(10, 126, 790, 165);
        panelTabla.setLayout(new BorderLayout());
        pane.add(scrol);
        scrol.setViewportView(panelTabla);
        panelTabla.setPreferredSize(new Dimension(750,162+((contadorP-10)*16)));
    }
    
    private void etiquetas()
    {
        int y=100, x=65;
        if(contadorP>10)
        {
            x=61;
        }
        Label nombre=new Label(10,y,197+x,26);
        nombre.setText(fn.texto("Nombre", true,3));
        nombre.setOpaque(true);
        nombre.setBackground(celeste);
        pane.add(nombre);
        
        Label edad=new Label(208+x,y,196+x,26);
        edad.setText(fn.texto("Precio", true,3));
        edad.setOpaque(true);
        edad.setBackground(celeste);
        pane.add(edad);
        
        Label sexo=new Label(405+(x*2),y,198+(x),26);
        sexo.setText(fn.texto("Cantidad", true,3));
        sexo.setOpaque(true);
        sexo.setBackground(celeste);
        pane.add(sexo);
        
        
        Label lb3=new Label(77,12,800,60);
        lb3.setText(fn.texto("Dashboard Productos", true,6));
        lb3.setHorizontalAlignment(LEFT);
        pane1.add(lb3);
        
        JLabel s0=new JLabel();
        s0.setBounds(15, 12, 55, 55);
        ImageIcon im= new ImageIcon("Resources\\clientesr.png");
        Icon scale=new ImageIcon(im.getImage().getScaledInstance(s0.getWidth(), s0.getHeight(), Image.SCALE_DEFAULT));
        s0.setIcon(scale);
        pane1.add(s0);
    }
    
    Object[][] filas=new Object[contadorP][3];
    
    private void agregarTabla()
    {
        String[] nombreC={"Nombre","Precio","Cantidad"};
        
        for(int i=0; i<contadorP;i++)
        {
            if(productos[i] !=null)
            {
                filas[i][0]=productos[i].getNombre();
                filas[i][1]=productos[i].getPrecio();
                filas[i][2]=productos[i].getCantidad();
            }
        }
        
        tabla=new JTable(filas,nombreC);
        tabla.repaint();
        panelTabla.add(tabla);
    }
    
    public void graficarPie()
    {
        Label a =new Label("No se ha registrado ninguna venta",470,410,250,30);
        pane.add(a);
        if(ventas[0]==null)
        {
            
            a.setVisible(true);
            
        }
        else
        {
            mostrar();
            a.setVisible(false);
        }
    }
    
    public void mostrar()
    {
        DefaultPieDataset dataset=new DefaultPieDataset();
        AlgoritmoProductosVendidos z=new AlgoritmoProductosVendidos();
        z.llenar();
        ProductosUsados[] prod=z.getP();
        if(z.getCont()<10)
        {
            for(int i=0; i<z.getCont();i++)
            {
                dataset.setValue(prod[i].getNombre(), prod[i].getUsado());
            }
        }
        else
        {
            for(int i=0; i<10;i++)
            {
                dataset.setValue(prod[i].getNombre(), prod[i].getUsado());
            }
        }
        //dataset.setValue("Hombres", (contadorCl-a.obtenerMujeres()));
        JFreeChart chart = ChartFactory.createPieChart3D("Gráfica de las top 10 ventas",dataset);
        PiePlot3D plot=(PiePlot3D)chart.getPlot();
//        plot.setStartAngle(180);
        plot.setForegroundAlpha(0.60f);
        plot.setInteriorGap(0.02);
        plot.setBackgroundPaint(gris);
        ChartPanel panel= new ChartPanel(chart);
        panel.setBounds(440, 326, 333, 255); //30, 320
        pane.add(panel);
    }
    
    public void graficarBarras()
    {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Edades de los clientes", vectoredad(), 10);
        
        JFreeChart barChart = ChartFactory.createHistogram("Productos", "Precios Q", "Cantidad de productos", dataset, PlotOrientation.VERTICAL, false, true, false);
        XYPlot xyplot = (XYPlot)barChart.getPlot();
        xyplot.setBackgroundPaint(gris);
        XYItemRenderer item = xyplot.getRenderer();
        Color a=new Color(58, 166, 41);
        item.setSeriesPaint(0,celeste);
       
        xyplot.setRenderer(item);
        ChartPanel panel = new ChartPanel(barChart);
        panel.setBounds(20, 320, 410, 250);
        pane.add(panel);
    }
    
    private double[] vectoredad()
    {
        double [] vecaux=new double[contadorP];
        for(int i=0; i<contadorP;i++)
        {
             vecaux[i]=productos[i].getPrecio();
        }
        return vecaux;
    }
}
