package br.edu.unipe.api.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.edu.unipe.api.exceptions.ResourceNotFoundException;
import br.edu.unipe.api.model.Telefone;
import br.edu.unipe.api.model.dto.TelefoneDTO;
import br.edu.unipe.api.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service

public class TelefoneService {

    private final TelefoneRepository repository;
    private final ModelMapper modelMapper;

    //classe conversora para entity
    public Telefone convertToEntity(TelefoneDTO telefoneDTO) {
        return modelMapper.map(telefoneDTO, Telefone.class);
    }

    //classe conversora para dto
    public TelefoneDTO convertToDto(Telefone telefone) {
        return modelMapper.map(telefone, TelefoneDTO.class);
    }
    //valida a existencia do id
    private void validarExistenciaId(Integer id) {
        if (id == null || !repository.existsById(id)) {
            throw new ResourceNotFoundException("Telefone não existe para o id " + id);
        }
    }

    public List<Telefone> listarTelefones() {
        return repository.findAll();
    }

    public TelefoneDTO salvar(TelefoneDTO telefoneDTO) {
        var telefone = repository.save(convertToEntity(telefoneDTO));
        return convertToDto(telefone);
    }

    public TelefoneDTO consultar(Integer id) {
        validarExistenciaId(id);
        return convertToDto(repository.findById(id).get());
    }

    public Telefone alterar(Telefone telefone) {
        validarExistenciaId(telefone.getId());
        return repository.save(telefone);
    }

    public void deletar(Integer id) {
        log.info("Start - Excluindo telefone ID {} ", id);
        repository.deleteById(id);
        log.info("End - Excluindo telefone ID {} ", id);
    }

    public Telefone buscarPorNumero(String numero) {
        var telefone = repository.buscarPorNumero(numero);
        if (telefone == null)
            throw new ResourceNotFoundException("Telefone não localizado " + numero);
        return telefone;
    }

    public List<Telefone> buscarPorUsuarioId(Integer usuarioId) {
        return repository.buscarPorUsuarioId(usuarioId);
    }

    public List<Telefone> listarPorDdd(String ddd) {
        return repository.findByDdd(ddd);
    }




}
