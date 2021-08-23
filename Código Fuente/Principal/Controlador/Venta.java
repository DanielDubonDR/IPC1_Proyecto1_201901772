
package Principal.Controlador;

/**
 *
 * @author Daniel Dubón
 */
public class Venta {
    
    private int codigo, nit, cantidad;
    private String producto;

    public Venta(int codigo, int nit, String producto, int cantidad)
    {
        setCodigo(codigo);
        setNit(nit);
        setProducto(producto);
        setCantidad(cantidad);
    }
    
    public void setCodigo(int codigo) 
    {
        this.codigo = codigo;
    }

    public void setNit(int nit) 
    {
        this.nit = nit;
    }

    public void setCantidad(int cantidad) 
    {
        this.cantidad = cantidad;
    }

    public void setProducto(String producto) 
    {
        this.producto = producto;
    }

    public int getCodigo() 
    {
        return codigo;
    }

    public int getNit() 
    {
        return nit;
    }

    public int getCantidad()
    {
        return cantidad;
    }

    public String getNombreproducto()
    {
        return producto;
    }

    @Override
    public String toString() {
        return "codigo=" + codigo + " nit=" + nit + " cantidad=" + cantidad + " nombreproducto=" + producto;
    }
    
    
}
