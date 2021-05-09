package model;

import java.util.ArrayList;
import java.util.List;

public class Provincia {
    private Integer id;
    private String nombre;
    private Ciudad capital;
    private List<Ciudad> ciudades;
    private List<Provincia> limitrofes;

    public Provincia(){
        this.ciudades = new ArrayList<Ciudad>();
        this.limitrofes = new ArrayList<Provincia>();
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Ciudad getCapital() {
        return capital;
    }

    public void establecerCapital(Ciudad ciudad){
        this.capital = ciudad;
    }
}
