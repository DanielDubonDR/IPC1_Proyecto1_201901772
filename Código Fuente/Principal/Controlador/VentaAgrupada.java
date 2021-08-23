/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Controlador;

/**
 *
 * @author Daniel Dubón
 */
public class VentaAgrupada {
    private int codigo, nit;
    private double total;

    public VentaAgrupada(int codigo, int nit, double total)
    {
        setCodigo(codigo);
        setNit(nit);
        setTotal(total);
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

    @Override
    public String toString() {
        return "codigo=" + codigo + ", nit=" + nit + ", total=" + total;
    }
    
    
}
