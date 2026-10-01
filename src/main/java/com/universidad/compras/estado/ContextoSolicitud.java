package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class ContextoSolicitud {
    private final Solicitud solicitud;
    private EstadoSolicitud estadoActual;

    public ContextoSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
        this.estadoActual = MapeadorEstados.obtenerEstado(solicitud.getEstado());
    }

    public void cambiarEstado(EstadoSolicitud nuevoEstado) {
        this.estadoActual = nuevoEstado;
        this.solicitud.setEstado(nuevoEstado.getNombreEstado());
    }

    public void aprobar() { estadoActual.aprobar(this); }
    public void rechazar() { estadoActual.rechazar(this); }
    public void ejecutar() { estadoActual.ejecutar(this); }
    public void cancelar() { estadoActual.cancelar(this); }

    public EstadoSolicitud getEstadoActual() { return estadoActual; }
    public Solicitud getSolicitud() { return solicitud; }
}