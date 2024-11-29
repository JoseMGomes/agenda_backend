package br.fatec.agenda.services;

import java.util.stream.Collectors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.agenda.mappers.AgendaMappers;
import br.fatec.agenda.repository.AgendaRepository;
import jakarta.persistence.EntityNotFoundException;
import br.fatec.agenda.dtos.AgendaRequest;
import br.fatec.agenda.dtos.AgendaResponse;
import br.fatec.agenda.entities.Agenda;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    public List<AgendaResponse> getAllAgendas() {
        return repository.findAll().stream().map(a -> AgendaMappers.toDTO(a)).collect(Collectors.toList());
    }

    public AgendaResponse getAgendaById(long id) {
        Agenda agenda = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        return AgendaMappers.toDTO(agenda);
    }

    public void delete(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Produto não encontrado");
        }
    }

    public AgendaResponse save(AgendaRequest agenda) {
        Agenda newAgenda = repository.save(AgendaMappers.toEntity(agenda));
        return AgendaMappers.toDTO(newAgenda);
    }

    public void update(AgendaRequest agenda, long id) {
        Agenda aux = repository.getReferenceById(id);

        aux.setDescription(agenda.description());
        aux.setDuration(agenda.duration());
        aux.setLocal(agenda.local());

        repository.save(aux);

    }

}
