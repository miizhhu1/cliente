package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.services.ServicioCliente;

public class ServicioClienteTest {

	

	 // PRUEBA 1: Crear un cliente
    @Test
    public void testCrearCliente() {

        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = new Cliente(
                "1723456789", "Ana", "Lopez");

        Cliente resultado = servicio.crear(cliente);

        assertNotNull(resultado);
        assertEquals("1723456789", resultado.getCedula());
        assertEquals("Ana", resultado.getNombre());
        assertEquals("Lopez", resultado.getApellido());
    }

    // PRUEBA 2: No permitir clientes con cédula duplicada
    @Test
    public void testCrearClienteDuplicado() {

        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente1 = new Cliente(
                "1723456789", "Ana", "Lopez");

        Cliente cliente2 = new Cliente(
                "1723456789", "Pedro", "Perez");

        servicio.crear(cliente1);

        Cliente resultado = servicio.crear(cliente2);

        assertNull(resultado);
        assertEquals(1, servicio.listar().size());
    }

    // PRUEBA 3: Listar clientes
    @Test
    public void testListarClientes() {

        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente1 = new Cliente(
                "111", "Ana", "Lopez");

        Cliente cliente2 = new Cliente(
                "222", "Pedro", "Perez");

        servicio.crear(cliente1);
        servicio.crear(cliente2);

        List<Cliente> resultado = servicio.listar();

        assertEquals(2, resultado.size());
        assertEquals("Ana", resultado.get(0).getNombre());
        assertEquals("Pedro", resultado.get(1).getNombre());
    }

    // PRUEBA 4: Actualizar un cliente existente
    @Test
    public void testActualizarCliente() {

        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = new Cliente(
                "1723456789", "Ana", "Lopez");

        servicio.crear(cliente);

        Cliente clienteActualizado = new Cliente(
                "1723456789", "Maria", "Gomez");

        Cliente resultado = servicio.actualizar(
                "1723456789", clienteActualizado);

        assertNotNull(resultado);
        assertEquals("1723456789", resultado.getCedula());
        assertEquals("Maria", resultado.getNombre());
        assertEquals("Gomez", resultado.getApellido());
    }

    // PRUEBA 5: Actualizar un cliente inexistente
    @Test
    public void testActualizarClienteInexistente() {

        ServicioCliente servicio = new ServicioCliente();

        Cliente clienteActualizado = new Cliente(
                "999", "Maria", "Gomez");

        Cliente resultado = servicio.actualizar(
                "999", clienteActualizado);

        assertNull(resultado);
        assertTrue(servicio.listar().isEmpty());
    }

    // PRUEBA 6: Eliminar un cliente existente
    @Test
    public void testEliminarCliente() {

        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = new Cliente(
                "1723456789", "Ana", "Lopez");

        servicio.crear(cliente);

        boolean resultado = servicio.eliminar("1723456789");

        assertTrue(resultado);
        assertTrue(servicio.listar().isEmpty());
    }

    // PRUEBA 7: Eliminar un cliente inexistente
    @Test
    public void testEliminarClienteInexistente() {

        ServicioCliente servicio = new ServicioCliente();

        boolean resultado = servicio.eliminar("999");

        assertFalse(resultado);
        assertTrue(servicio.listar().isEmpty());
    }
}
