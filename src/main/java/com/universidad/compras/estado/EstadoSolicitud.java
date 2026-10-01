package com.universidad.compras.estado;

public interface EstadoSolicitud {
    void aprobar(ContextoSolicitud contexto);
    void rechazar(ContextoSolicitud contexto);
    void ejecutar(ContextoSolicitud contexto);
    void cancelar(ContextoSolicitud contexto);
    String getNombreEstado();
}