package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;
import java.util.ArrayList;
import java.util.List;

public class PublicadorNotificaciones {
    private final List<ObservadorEstado> observadores = new ArrayList<>();

    public void suscribir(ObservadorEstado observador) {
        observadores.add(observador);
    }

    public void desuscribir(ObservadorEstado observador) {
        observadores.remove(observador);
    }

    public void notificar(Solicitud solicitud, String estadoAnterior, String estadoNuevo, String detalle) {
        solicitud.setEstado(estadoNuevo);
        for (ObservadorEstado obs : observadores) {
            obs.enCambioEstado(solicitud, estadoAnterior, estadoNuevo, detalle);
        }
    }
}