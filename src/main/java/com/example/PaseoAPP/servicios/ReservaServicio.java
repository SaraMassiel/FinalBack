
package com.example.PaseoAPP.servicios;

import com.example.PaseoAPP.modelos.Reserva;
import com.example.PaseoAPP.repositorios.IRepositorioReserva;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaServicio {

    private final IRepositorioReserva repositorioReserva;

    public ReservaServicio(IRepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public Reserva guardarReservaEnBD(Reserva datos) {
        return repositorioReserva.save(datos);
    }

    public Reserva modificarReservaEnBD(Reserva datos, UUID id) {
        Reserva existente = repositorioReserva.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Reserva no encontrada"));

        existente.setFecha(datos.getFecha());
        existente.setTiempo(datos.getTiempo());

        return repositorioReserva.save(existente);
    }

    public List<Reserva> buscarReservasEnBD() {
        return repositorioReserva.findAll();
    }

    public String eliminarReservaEnBD(UUID id) {
        if (!repositorioReserva.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Reserva no encontrada");
        }

        repositorioReserva.deleteById(id);
        return "Reserva eliminada correctamente";
    }
}
