package med.voll.api.domain.dto;

import med.voll.api.domain.model.Medico;

public record DatosListadoMedicos(
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email
) {
    public DatosListadoMedicos(Medico medico){
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
