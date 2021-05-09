package tests;

import com.sun.security.ntlm.Client;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import models.Banco;
import models.Cliente;
import models.enums.Moneda;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.Assertions.*;

public class FeaturesTest {
    @Test
    public void puede_agregar_un_cliente_que_no_existe()throws Exception{
        Banco instance = Banco.getInstance();
        instance.clean();

        instance.altaCliente("Leonardo", "Pardo", 28986736);

        assertEquals("Leonardo", Banco.getInstance().buscarCliente(28986736).getNombre());
        assertNotEquals("Martín", Banco.getInstance().buscarCliente(28986736).getNombre());
    }

    @Test
    public void no_puede_agregar_un_cliente_que_ya_existe()throws Exception{
        Banco instance = Banco.getInstance();
        instance.clean();

        instance.altaCliente("Leonardo", "Pardo", 28986736);

        Exception exception = assertThrows(Exception.class, () -> {
            instance.altaCliente("Leonardo", "Pardo", 28986736);
        });

        String expectedMessage = "El cliente que intenta registrar ya se ecunentra en el sistema.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void puede_dar_de_alta_una_caja_de_ahorro() throws Exception {
        Banco instance = Banco.getInstance();
        instance.clean();

        instance.altaCliente("Leonardo", "Pardo", 28986736);

        instance.altaCajaAhorro(Moneda.PESO, 28986736);

        assertTrue(instance.buscarCliente(28986736).getCuentas().size() == 1);
    }
}
