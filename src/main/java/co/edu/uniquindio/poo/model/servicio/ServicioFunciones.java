package co.edu.uniquindio.poo.model.servicio;

import co.edu.uniquindio.poo.model.Funcion;
import co.edu.uniquindio.poo.model.Sala;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import co.edu.uniquindio.poo.model.repositorio.RegistroFunciones;

public class ServicioFunciones {
    private final RegistroFunciones registroFunciones;

    public ServicioFunciones(RegistroFunciones registroFunciones) {
        this.registroFunciones = registroFunciones;
    }

    public void registrar(Funcion nuevaFuncion) {
        validarSalaDisponible(nuevaFuncion);
        registroFunciones.registrar(nuevaFuncion);
    }

    private void validarSalaDisponible(Funcion nuevaFuncion) {
        Sala sala = nuevaFuncion.getSala();
        for (Funcion existente : registroFunciones.listarPorSala(sala)) {
            boolean empiezaAntesDeQueTermineLaExistente = nuevaFuncion.getFechaHora()
                    .isBefore(existente.calcularHoraFin());
            boolean terminaDespuesDeQueEmpieceLaExistente = nuevaFuncion.calcularHoraFin()
                    .isAfter(existente.getFechaHora());

            if (empiezaAntesDeQueTermineLaExistente && terminaDespuesDeQueEmpieceLaExistente) {
                throw new ValidacionException("La sala ya tiene una funcion que se cruza con ese horario");
            }
        }
    }
}
