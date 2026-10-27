
package com.example.PaseoAPP.servicios;

import com.example.PaseoAPP.modelos.Espacio;
import com.example.PaseoAPP.repositorios.IRepositorioEspacio;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class EspacioServicio {

    private final IRepositorioEspacio repositorioEspacio;

    public EspacioServicio(IRepositorioEspacio repositorioEspacio) {
        this.repositorioEspacio = repositorioEspacio;
    }

    public Espacio guardarEspacioEnBD(Espacio datos) {
        return repositorioEspacio.save(datos);
    }

    public Espacio modificarEspacioEnBD(Espacio datos, UUID id) {
        Espacio existente = repositorioEspacio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Espacio no encontrado"));

        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setFoto(datos.getFoto());
        existente.setAforo(datos.getAforo());

        return repositorioEspacio.save(existente);
    }

    public List<Espacio> buscarEspaciosEnBD() {
        return repositorioEspacio.findAll();
    }

    public String eliminarEspacioEnBD(UUID id) {
        if (!repositorioEspacio.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Espacio no encontrado");
        }

        repositorioEspacio.deleteById(id);
        return "Espacio eliminado correctamente";
    }
}
