package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public interface ObservadorEstado {
    void enCambioEstado(Solicitud solicitud, String estadoAnterior, String estadoNuevo, String detalle);
}