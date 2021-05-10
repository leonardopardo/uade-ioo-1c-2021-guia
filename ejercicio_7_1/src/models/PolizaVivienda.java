package models;

public class PolizaVivienda extends Poliza {
    private static final Double factorVivienda = 2.0;
    private static final Double factorBienes = 1.25;

    public void calcularPrima(Double montoVivienda, Double montoBienes){
        this.prima = (montoVivienda * factorVivienda) + (montoBienes * factorBienes);
    }
}
