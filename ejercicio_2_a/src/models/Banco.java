package models;

import models.enums.EstadoCuenta;
import models.enums.Moneda;
import sun.rmi.server.InactiveGroupException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Banco {
    private Integer codigo;
    private String nombre;
    private Integer sucursal;
    private List<CajaAhorro> cuentas;
    private List<Cliente> clientes;

    //region ExceptionMessages
    private static final String CLIENTE_REPETIDO_EXCEPTION = "El cliente que intenta registrar ya se ecunentra en el sistema.";
    private static final String CLIENTE_INEXISTENTE_EXCEPTION = "El cliente con el que intenta operar no es un cliente válido.";
    private static final String CUENTA_INEXISTENTE_EXCEPTION = "El número suministrado no pertenece a una cuenta válida.";
    private static final String EXTRAER_EXCEPTION = "La cuenta no tiene saldo suficiente para realizar la operación.";
    private static final String ESTADO_CUENTA_EXCEPTION = "La cuenta con la que itenta operar no posee un estado válido para la operación que desea realizar.";
    //endregion

    private static Banco instance;

    private Banco(){
        this.codigo = 1;
        this.sucursal = 1;
        this.nombre = "Microcentro - Casa Central";
        this.cuentas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    /**
     * Alta Cliente
     * @param nombre
     * @param apellido
     * @param documento
     * @throws Exception
     */
    public void altaCliente(String nombre, String apellido, Integer documento) throws Exception{

        if(this.buscarCliente(documento) != null)
            throw new Exception(CLIENTE_REPETIDO_EXCEPTION);

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDocumento(documento);

        this.clientes.add(cliente);
    }

    /**
     * Alta Caja Ahorro.
     * @param moneda
     * @param dniCliente
     * @throws Exception
     */
    public void altaCajaAhorro(Moneda moneda, Integer dniCliente) throws Exception{

        Cliente cliente = this.buscarCliente(dniCliente); // Objeto Cliente

        if(cliente == null)
            throw new Exception(CLIENTE_INEXISTENTE_EXCEPTION); // si se ejecuta esto, el código termió acá !!!

        CajaAhorro cajaAhorro = new CajaAhorro();

        // esta lógica búsca el último número de cuenta y le suma 1
        int numeroCuenta = this.cuentas.size() > 0
                ? this.cuentas.size() + 1
                : 1;

        cajaAhorro.setNumero(numeroCuenta); // generado en el código
        cajaAhorro.setTitular(cliente); // envío el objeto cliente
        cajaAhorro.setMoneda(moneda); // por argumento

        cliente.asociarCuenta(cajaAhorro);

        this.cuentas.add(cajaAhorro);
    }

    /**
     * Depositar.
     * @param cuentaNumero
     * @param value
     * @throws Exception
     */
    public void depositar(Integer cuentaNumero, Double value) throws Exception{

        CajaAhorro cajaAhorro = this.buscarCajaAhorro(cuentaNumero);

        if(cajaAhorro.getEstado() != EstadoCuenta.ABIERTA)
            throw new Exception(ESTADO_CUENTA_EXCEPTION);

        cajaAhorro.setSaldo(cajaAhorro.getSaldo() + value);
    }

    /**
     * Extraer.
     * @param cuentaNumero
     * @param value
     * @throws Exception
     */
    public void extraer(Integer cuentaNumero, Double value) throws Exception{

        CajaAhorro cajaAhorro = buscarCajaAhorro(cuentaNumero);

        Double saldo = cajaAhorro.getSaldo();

        if(saldo < value)
            throw new Exception(EXTRAER_EXCEPTION);

        cajaAhorro.setSaldo(saldo - value);
    }

    /**
     * Cerrar Cuenta y retornar saldo.
     * @param cuentaNumero
     * @return
     * @throws Exception
     */
    public Double cerrarCajaAhorro(Integer cuentaNumero) throws Exception{

        CajaAhorro cajaAhorro = this.buscarCajaAhorro(cuentaNumero);

        if (cajaAhorro.getEstado() != EstadoCuenta.ABIERTA)
            throw new Exception(ESTADO_CUENTA_EXCEPTION);

        cajaAhorro.setEstado(EstadoCuenta.CERRADA);

        return cajaAhorro.getSaldo();
    }

    /**
     * Cuentas de un cliente.
     * @param dni
     * @return
     * @throws NullPointerException
     */
    public List<CajaAhorro> cuentasDeUnCliente(Integer dni) throws Exception{
        Cliente cliente = this.buscarCliente(dni);
        return cliente.getCuentas();
    }

    //region Otros métodos útiles
    public CajaAhorro buscarCajaAhorro(Integer numero) throws Exception{
        CajaAhorro cajaAhorro = cuentas.stream()
                .filter(cuenta -> cuenta.getNumero() == numero)
                .findFirst()
                .get();

        if(cajaAhorro == null)
            throw new NullPointerException(CUENTA_INEXISTENTE_EXCEPTION);

        return cajaAhorro;
    }

    public Cliente buscarCliente(Integer dni) throws Exception{

        for (Cliente cliente: this.clientes) {
            if(cliente.getDocumento().equals(dni)) return cliente;
        }

        return null;
    }
    //endregion

    //region Singleton Pattern
    public static Banco getInstance(){

        if(instance == null){
            instance = new Banco();
        }

        return instance;
    }

    public void clean(){
        this.clientes.removeAll(this.clientes);
        this.cuentas.removeAll(this.cuentas);
    }
    //endregion
}
