
package Principal.Controlador;

/**
 *
 * @author Daniel Dubón
 */
public class Cliente {
    private String nombre, avatar;
    private int edad, nit;
    private char sexo;
    
    public Cliente(String nombre, int edad, char sexo, int nit, String avatar)
    {
        setNombre(nombre);
        setEdad(edad);
        setSexo(sexo);
        setNit(nit);
        setAvatar(avatar);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", avatar=" + avatar + ", edad=" + edad + ", nit=" + nit + ", sexo=" + sexo + '}';
    }
    
}
