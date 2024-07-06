package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.DatosActualizarMedico;
import med.voll.api.dto.DatosMedico;
import med.voll.api.enums.Especialidad;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosMedico medico) {
        this.activo = true;
        this.nombre = medico.nombre();
        this.telefono = medico.telefono();
        this.email = medico.email();
        this.documento = medico.documento();
        this.especialidad = medico.especialidad();
        this.direccion = new Direccion(medico.direccion());
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.nombre() != null) {
            this.nombre = datosActualizarMedico.nombre();
        }
        if (datosActualizarMedico.documento() != null) {
            this.documento = datosActualizarMedico.documento();
        }
        if (datosActualizarMedico.direccion() != null) {
            this.direccion = direccion.actualizarDireccion(datosActualizarMedico.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
