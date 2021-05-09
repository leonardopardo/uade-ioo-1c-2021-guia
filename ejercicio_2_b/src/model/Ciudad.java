package model;

public class Ciudad {
    private Integer id;
    private String nombre;

    public Integer getId(){
        return this.id;
    }

    public String getNombre(){
        return this.nombre.toUpperCase();
    }
}
