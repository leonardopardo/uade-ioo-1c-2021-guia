package models;

import models.enums.EstadoCuenta;
import models.enums.Moneda;

public class CajaAhorro {
    private Integer numero;
    private Moneda moneda;
    private String cbu;
    private Double saldo;
    private Cliente titular;
    private EstadoCuenta estado;

    /**
     * Si al construir el objeto este no especifica una moneda, por defecto es PESO.
     */
    public CajaAhorro(){
        this.estado = EstadoCuenta.ABIERTA;
        this.moneda = Moneda.PESO;
        this.saldo = 0.00;
    }

    /**
     * Sobrecarga de constructor con tipo de moneda para la apertura.
     * @param moneda
     */
    public CajaAhorro(Moneda moneda){
        this.estado = EstadoCuenta.ABIERTA;
        this.moneda = moneda;
        this.saldo = 0.00;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public void setTitular(Cliente titular){
        this.titular = titular;
    }

    public EstadoCuenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    public Double getSaldo(){
        return this.saldo;
    }

    public void setSaldo(Double value){
        this.saldo = value;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
}
