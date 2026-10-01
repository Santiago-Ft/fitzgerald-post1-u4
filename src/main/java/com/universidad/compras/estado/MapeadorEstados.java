package com.universidad.compras.estado;

public class MapeadorEstados {
    public static EstadoSolicitud obtenerEstado(String estadoStr) {
        if (estadoStr == null) return new EstadoPendiente();
        switch (estadoStr) {
            case "APROBADA": return new EstadoAprobada();
            case "EJECUTADA": return new EstadoEjecutada();
            case "RECHAZADA": return new EstadoRechazada();
            case "CANCELADA": return new EstadoCancelada();
            default: return new EstadoPendiente();
        }
    }
}