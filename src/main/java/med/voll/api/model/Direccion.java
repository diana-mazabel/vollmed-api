package med.voll.api.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import med.voll.api.dto.DatosDireccion;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String calle;
    private String numero;
    private String distrito;
    private String ciudad;
    private String complemento;

    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.complemento = direccion.complemento();
    }

    public Direccion actualizarDireccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.complemento = direccion.complemento();
        return this;
    }

}
