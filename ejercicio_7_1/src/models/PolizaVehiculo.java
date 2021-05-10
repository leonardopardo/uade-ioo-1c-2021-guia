package models;

public class PolizaVehiculo extends Poliza {
    private static final Double factorVehiculo = 0.98;

    public void calcularPrima(Double montoVehiculo){
        this.prima = factorVehiculo * montoVehiculo;
    }
}
