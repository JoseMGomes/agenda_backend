package br.fatec.agenda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fatec.agenda.dtos.AgendaRequest;
import br.fatec.agenda.dtos.AgendaResponse;
import br.fatec.agenda.services.AgendaService;

@RestController
@RequestMapping("agendas")
public class AgendaControllers {
    
    @Autowired
    private AgendaService service;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> getAgenda(){
        return ResponseEntity.ok(service.getAllAgendas());
    }

    @GetMapping("{id}")
    public ResponseEntity<AgendaResponse> getAgenda(@PathVariable long id){
        return ResponseEntity.ok(service.getAgendaById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAgenda(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAgenda(@PathVariable long id, @Validated @RequestBody AgendaRequest agenda){
        service.update(agenda, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<AgendaResponse> saveAgenda(@Validated @RequestBody AgendaRequest agenda){
        AgendaResponse newAgenda = service.save(agenda);
        return ResponseEntity.created(null).body(newAgenda);
    }

}
