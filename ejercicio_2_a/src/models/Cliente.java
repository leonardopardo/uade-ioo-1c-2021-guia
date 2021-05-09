package models;

import com.sun.deploy.panel.IProperty;
import com.sun.security.ntlm.Client;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private Integer documento;

    private String nombre;

    private String apellido;

    private List<CajaAhorro> cuentas;

    public Cliente(){
        this.cuentas = new ArrayList<>();
    }

    public Integer getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.trim();
    }

    public void setApellido(String apellido) {
        this.apellido = apellido.trim();
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public List<CajaAhorro> getCuentas() {
        return this.cuentas;
    }

    public void asociarCuenta(CajaAhorro cuenta){
        this.cuentas.add(cuenta);
    }
}
