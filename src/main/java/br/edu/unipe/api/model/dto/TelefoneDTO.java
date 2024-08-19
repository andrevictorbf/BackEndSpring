package br.edu.unipe.api.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TelefoneDTO {

    private Integer id;
    @NotEmpty
    private String ddd;
    @NotEmpty
    private String numero;
    @NotEmpty
    private Integer usuarioId;

}
