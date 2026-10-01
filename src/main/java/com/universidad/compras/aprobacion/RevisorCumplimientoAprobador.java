package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class RevisorCumplimientoAprobador extends Aprobador {

    public RevisorCumplimientoAprobador(Aprobador siguiente) {
        super(siguiente);
    }

    @Override
    public ResultadoAprobacion procesar(Solicitud solicitud) {
        if ("INTERNACIONAL".equalsIgnoreCase(solicitud.getCategoria())) {
            solicitud.setEstado("APROBADA");
            solicitud.setNivelResolutor("Revisor de Cumplimiento Normativo");
            return new ResultadoAprobacion(true, "Revisor de Cumplimiento Normativo", "Aprobada por cumplir normatividad internacional");
        } else if (siguiente != null) {
            return siguiente.procesar(solicitud);
        }
        return new ResultadoAprobacion(false, "Sin Asignar", "No fue posible procesar la solicitud");
    }
}