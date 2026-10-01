package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class SupervisorAreaAprobador extends Aprobador {

    public SupervisorAreaAprobador(Aprobador siguiente) {
        super(siguiente);
    }

    @Override
    public ResultadoAprobacion procesar(Solicitud solicitud) {
        if (solicitud.getMonto() <= 2000000) {
            solicitud.setEstado("APROBADA");
            solicitud.setNivelResolutor("Supervisor de Área");
            return new ResultadoAprobacion(true, "Supervisor de Área", "Aprobada por monto dentro del límite de Supervisor");
        } else if (siguiente != null) {
            return siguiente.procesar(solicitud);
        }
        return new ResultadoAprobacion(false, "Sin Asignar", "Monto excede los niveles configurados");
    }
}