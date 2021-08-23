
package Principal.Controlador;

/**
 *
 * @author Daniel Dubón
 */
public class Producto {
    private String nombre, imagen;
    private float precio;
    private int cantidad;
    
    
    public Producto(String nombre, float precio, int cantidad, String imagen)
    {
        setNombre(nombre);
        setPrecio(precio);
        setCantidad(cantidad);
        setImagen(imagen);
    }
    
    public void setNombre(String nombre)
    {
        this.nombre=nombre;
    }
    
    public void setImagen(String imagen)
    {
        this.imagen=imagen;
    }
    
    public void setPrecio(float precio)
    {
        this.precio=precio;
    }
    
    public void setCantidad(int cantidad)
    {
        this.cantidad=cantidad;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public String getImagen()
    {
        return imagen;
    }
    
    public float getPrecio()
    {
        return precio;
    }
    
    public int getCantidad()
    {
        return cantidad;
    }

    @Override
    public String toString() {
        return  "nombre=" + nombre + " imagen=" + imagen + " precio=" + precio + " cantidad=" + cantidad;
    }
    
    
}
