package model;

import enums.Continente;

import java.util.ArrayList;
import java.util.List;

public class Pais {
    private Integer id;
    private String nombre;
    private Ciudad capital;
    private List<Pais> limitrofes;
    private List<Provincia> provincias;
    private Continente continente;

    public Pais(){
        this.provincias = new ArrayList<Provincia>();
        this.limitrofes = new ArrayList<Pais>();
    }

    public void establecerCapital(Ciudad ciudad){
        this.capital = ciudad;
    }

    public void appendProvincia(Provincia provincia){
        this.provincias.add(provincia);
    }

    public void appendProvincias(List<Provincia> ps){
        this.provincias.addAll(ps);
    }

    class Cliente{
        public List<CajaAhorro> cuentas;
    }
    class CajaAhorro{
        public String cbu;
        public Cliente cliente;
    }

    public void abrirCajaAhorro(Cliente c){
        CajaAhorro ca = new CajaAhorro();
        ca.cbu = "1232132132132";
        ca.cliente = c;

        c.cuentas.add(ca);

    }
}
