package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;
import java.util.ArrayDeque;
import java.util.Deque;

public class GestorEjecucionSolicitudes {
    private final Deque<ComandoEjecucion> historial = new ArrayDeque<>();

    public void ejecutarOperacion(ComandoEjecucion comando, Solicitud solicitud) {
        comando.ejecutar();
        historial.push(comando);
        solicitud.setEstado("EJECUTADA");
    }

    public void deshacerUltimaOperacion() {
        if (!historial.isEmpty()) {
            ComandoEjecucion ultimoComando = historial.pop();
            ultimoComando.deshacer();
        }
    }

    public int getTamanoHistorial() {
        return historial.size();
    }
}