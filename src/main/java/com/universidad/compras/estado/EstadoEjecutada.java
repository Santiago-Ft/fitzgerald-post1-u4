package com.universidad.compras.estado;

public class EstadoEjecutada implements EstadoSolicitud {
    @Override
    public void aprobar(ContextoSolicitud contexto) {}
    @Override
    public void rechazar(ContextoSolicitud contexto) {}
    @Override
    public void ejecutar(ContextoSolicitud contexto) {} // No puede volver a ejecutarse
    @Override
    public void cancelar(ContextoSolicitud contexto) {}

    @Override
    public String getNombreEstado() { return "EJECUTADA"; }
}