package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class DirectorFinancieroAprobador extends Aprobador {

    public DirectorFinancieroAprobador(Aprobador siguiente) {
        super(siguiente);
    }

    @Override
    public ResultadoAprobacion procesar(Solicitud solicitud) {
        solicitud.setEstado("APROBADA");
        solicitud.setNivelResolutor("Director Financiero");
        return new ResultadoAprobacion(true, "Director Financiero", "Aprobada por el Director Financiero");
    }
}