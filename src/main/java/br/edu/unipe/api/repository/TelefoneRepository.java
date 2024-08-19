package br.edu.unipe.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.unipe.api.model.Telefone;
import br.edu.unipe.api.model.Usuario;
import feign.Param;

@Repository
public interface TelefoneRepository
        extends JpaRepository<Telefone, Integer> {

    Telefone findByNumero(String numero);

    Telefone findByNumeroAndDdd(String numero, String ddd);

    @Query("select t from Telefone t where t.numero=:numero")
    Telefone buscarPorNumero(@Param("numero") String numero);

    @Query("select t from Telefone t where t.usuario = :usuario")
    List<Telefone> buscarPorUsuario(@Param("usuario") Usuario usuario);

    @Query("select t from Telefone t where t.usuario.id = :usuarioId")
    List<Telefone> buscarPorUsuarioId(@Param("usuarioId") Integer usuarioId);

    public List<Telefone> findByDdd(String ddd);
}
