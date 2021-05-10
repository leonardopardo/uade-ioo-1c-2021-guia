package models;

import java.time.LocalDate;

public class PolizaVida extends Poliza {
    private static final Double factorVida = 1.25;

    public void calcularPrima(Double monto, LocalDate edad){
        this.prima = factorVida * monto * (LocalDate.now().getYear() - edad.getYear());
    }
}
