package models;

import models.enums.TipoPoliza;

import java.time.LocalDate;
import java.util.List;

public class Aseguradora {
    private List<Cliente> clientes;
    private List<Poliza> polizas;

    public void crearCliente(String nombre, String apellido, Integer documento, LocalDate edad) {

    }

    public void crearPoliza(Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin, TipoPoliza tipo) {

    }
}
