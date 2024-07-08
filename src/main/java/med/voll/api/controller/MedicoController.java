package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.DatosActualizarMedico;
import med.voll.api.domain.dto.DatosListadoMedicos;
import med.voll.api.domain.dto.DatosMedico;
import med.voll.api.domain.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repositorio;

    //    @PostMapping
//    public void registrarMedico(@RequestBody @Valid DatosMedico medico){
//        repositorio.save(new Medico(medico));
//    }
    @PostMapping
    public ResponseEntity<DatosListadoMedicos> registrarMedico(@RequestBody @Valid DatosMedico datosMedico) {
        Medico medico = repositorio.save(new Medico(datosMedico));
        URI url = UriComponentsBuilder.fromPath("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosListadoMedicos(medico));
    }

//    @GetMapping
//    public List<DatosListadoMedicos> listarMedicos(){
//        return repositorio.findAll().stream().map(DatosListadoMedicos::new).toList();
//    }

//    @GetMapping
//    public Page<DatosListadoMedicos> listadoMedicos(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable paginacion) {
//        //return repositorio.findAll(paginacion).map(DatosListadoMedicos::new);
//        return repositorio.findByActivoTrue(paginacion).map(DatosListadoMedicos::new);
//    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedicos>> listadoMedicos(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable paginacion) {
        //return repositorio.findAll(paginacion).map(DatosListadoMedicos::new);
        return ResponseEntity.ok(repositorio.findByActivoTrue(paginacion).map(DatosListadoMedicos::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoMedicos> retornarDatosMedico(@PathVariable Long id) {
        Medico medico = repositorio.getReferenceById(id);
        return ResponseEntity.ok(new DatosListadoMedicos(medico));
    }

//    @PutMapping
//    @Transactional //Realiza la transacción para cambiar los datos
//    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico actualizarMedico ){
//        Medico medico = repositorio.getReferenceById(actualizarMedico.id());
//        medico.actualizarDatos(actualizarMedico);
//    }

    @PutMapping
    @Transactional //Realiza la transacción para cambiar los datos
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico actualizarMedico) {
        Medico medico = repositorio.getReferenceById(actualizarMedico.id());
        medico.actualizarDatos(actualizarMedico);
        return ResponseEntity.ok(new DatosListadoMedicos(medico));
    }

    //Delete de la Base de Datos
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void eliminarMedico(@PathVariable Long id){
//        Medico medico = repositorio.getReferenceById(id);
//        repositorio.delete(medico);
//    }

    //Delete Lógico
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void eliminarMedico(@PathVariable Long id){
//        Medico medico = repositorio.getReferenceById(id);
//        medico.desactivarMedico();
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Medico medico = repositorio.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }


}
