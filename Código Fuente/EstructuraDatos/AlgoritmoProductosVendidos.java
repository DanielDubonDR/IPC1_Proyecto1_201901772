
package EstructuraDatos;

import Principal.Controlador.ProductosUsados;
import static Principal.Proyecto1_SAP.contadorP;
import static Principal.Proyecto1_SAP.contadorV;
import static Principal.Proyecto1_SAP.productos;
import static Principal.Proyecto1_SAP.ventas;

/**
 *
 * @author Daniel Dubón
 */
public class AlgoritmoProductosVendidos {
    String [] pd=new String[100];
    ProductosUsados[] usado=new ProductosUsados[100];
    int cont;
    
    public void obtenerProductos()
    {
        for(int j=0; j<contadorP;j++)
        {
            for(int i=0; i<contadorV;i++)
            {
                if(productos[j].getNombre().equalsIgnoreCase(ventas[i].getNombreproducto()))
                {
                    pd[cont]=ventas[i].getNombreproducto();
                    cont++;
                    break;
                }
            }
        }
//        for(int j=0;j<cont;j++)
//            {
//                System.out.println(pd[j]);
//            }
    }
    
    public void llenar()
    {
        obtenerProductos();
        for(int i=0; i<cont;i++)
        {
            int aux=0;
            for(int j=0;j<contadorV;j++)
            {
                if(pd[i].equalsIgnoreCase(ventas[j].getNombreproducto()))
                {
                    aux+=ventas[j].getCantidad();
                }
            }
            ProductosUsados axx=new ProductosUsados(aux,pd[i]);
            usado[i]=axx;
        }
        
        
    }
    
    private void ordenarUsado()
    {
        ProductosUsados intercambio;
        for(int i=0; i<(cont-1);i++)
        {
            for(int j=0; j<(cont-1); j++)
            {
               if(usado[j].getUsado()<usado[j+1].getUsado())
               {
                   intercambio=usado[j];
                   usado[j]=usado[j+1];
                   usado[j+1]=intercambio;
               }
            }
        }
        for(int j=0;j<cont;j++)
            {
                System.out.println(usado[j]);
            }
    }
    
    public ProductosUsados[] getP()
    {
        ordenarUsado();
        return usado;
    }
    
    public int getCont()
    {
        return cont;
    }
}
