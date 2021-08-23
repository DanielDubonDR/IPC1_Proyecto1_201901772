
package Principal.Controlador;

/**
 *
 * @author Daniel Dubón
 */
public class ProductosUsados {
    private int usado;
    private String nombre;
    
    public ProductosUsados(int usado, String nombre)
    {
        setUsado(usado);
        setNombre(nombre);
    }

    public int getUsado() {
        return usado;
    }

    public void setUsado(int usado) {
        this.usado = usado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "usado=" + usado + ", nombre=" + nombre;
    }
    
    
}
