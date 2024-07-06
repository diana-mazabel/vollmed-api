package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DatosActualizarMedico;
import med.voll.api.dto.DatosListadoMedicos;
import med.voll.api.dto.DatosMedico;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repositorio;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosMedico medico){
        repositorio.save(new Medico(medico));
    }

//    @GetMapping
//    public List<DatosListadoMedicos> listarMedicos(){
//        return repositorio.findAll().stream().map(DatosListadoMedicos::new).toList();
//    }

    @GetMapping
    public Page<DatosListadoMedicos> listadoMedicos(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable paginacion) {
        //return repositorio.findAll(paginacion).map(DatosListadoMedicos::new);
        return repositorio.findByActivoTrue(paginacion).map(DatosListadoMedicos::new);
    }

    @PutMapping
    @Transactional //Realiza la transacción para cambiar los datos
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico actualizarMedico ){
        Medico medico = repositorio.getReferenceById(actualizarMedico.id());
        medico.actualizarDatos(actualizarMedico);
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public void eliminarMedico(@PathVariable Long id){
//        Medico medico = repositorio.getReferenceById(id);
//        repositorio.delete(medico);
//    }

    //Delete Lógico
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Medico medico = repositorio.getReferenceById(id);
        medico.desactivarMedico();
    }
}
