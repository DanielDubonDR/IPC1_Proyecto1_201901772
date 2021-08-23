
package Principal.Controlador;

/**
 *
 * @author Daniel Dubón
 */
public class CantidadProductos {
    private int codigo, nit, cantidad;
    private double total;

    public CantidadProductos(int codigo, int nit, double total,int cantidad)
    {
        setCodigo(codigo);
        setNit(nit);
        setTotal(total);
        setCantidad(cantidad);
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "codigo=" + codigo + ", nit=" + nit + ", cantidad=" + cantidad + ", total=" + total;
    }
    
    
}
