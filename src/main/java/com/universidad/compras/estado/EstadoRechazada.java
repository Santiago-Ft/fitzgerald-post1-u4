package com.universidad.compras.estado;

public class EstadoRechazada implements EstadoSolicitud {
    @Override public void aprobar(ContextoSolicitud c) {}
    @Override public void rechazar(ContextoSolicitud c) {}
    @Override public void ejecutar(ContextoSolicitud c) {}
    @Override public void cancelar(ContextoSolicitud c) {}
    @Override public String getNombreEstado() { return "RECHAZADA"; }
}