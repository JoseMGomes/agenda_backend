package br.fatec.agenda.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AgendaRequest(

    @NotBlank(message = "Descrição obrigatória.")
    @Size(min = 8, max = 255, message = "A descrição deve ter de 8 a 255 caracteres.")
    String description,

    @NotBlank(message = "Duração obrigatória.")
    String duration,

    @NotBlank(message = "Local obrigatório.")
    @Size(min = 3, max = 100, message = "O local deve ter entre 3 e 100 caracteres.")
    String local

) {
    
}