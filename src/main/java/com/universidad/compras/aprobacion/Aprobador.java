package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public abstract class Aprobador {
    protected Aprobador siguiente;

    public Aprobador(Aprobador siguiente) {
        this.siguiente = siguiente;
    }

    public abstract ResultadoAprobacion procesar(Solicitud solicitud);
}