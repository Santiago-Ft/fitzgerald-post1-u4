package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class GerenteAreaAprobador extends Aprobador {

    public GerenteAreaAprobador(Aprobador siguiente) {
        super(siguiente);
    }

    @Override
    public ResultadoAprobacion procesar(Solicitud solicitud) {
        if (solicitud.getMonto() <= 10000000) {
            solicitud.setEstado("APROBADA");
            solicitud.setNivelResolutor("Gerente de Área");
            return new ResultadoAprobacion(true, "Gerente de Área", "Aprobada por monto dentro del límite de Gerente");
        } else if (siguiente != null) {
            return siguiente.procesar(solicitud);
        }
        return new ResultadoAprobacion(false, "Sin Asignar", "Monto excede los niveles configurados");
    }
}