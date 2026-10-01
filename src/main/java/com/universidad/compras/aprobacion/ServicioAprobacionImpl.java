package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;
import org.springframework.stereotype.Service;

@Service
public class ServicioAprobacionImpl implements ServicioAprobacion {
    private final Aprobador cadenaInicial;

    public ServicioAprobacionImpl() {
        // Cadena por defecto: Cumplimiento -> Supervisor -> Gerente -> Director Financiero
        Aprobador director = new DirectorFinancieroAprobador(null);
        Aprobador gerente = new GerenteAreaAprobador(director);
        Aprobador supervisor = new SupervisorAreaAprobador(gerente);
        this.cadenaInicial = new RevisorCumplimientoAprobador(supervisor);
    }

    public ServicioAprobacionImpl(Aprobador cadenaPersonalizada) {
        this.cadenaInicial = cadenaPersonalizada;
    }

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        return cadenaInicial.procesar(solicitud);
    }
}