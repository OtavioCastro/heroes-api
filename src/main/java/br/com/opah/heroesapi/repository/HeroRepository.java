package br.com.opah.heroesapi.repository;

import br.com.opah.heroesapi.repository.model.HeroDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends CrudRepository<HeroDB, Integer> {
    List<HeroDB> findAll();
    List<HeroDB> findByFavoritoTrue();
    Page<HeroDB> findDistinctByNomeContaining(String nome, Pageable pageRequest);
    Page<HeroDB> findDistinctByNomeContainingAndFavoritoTrue(String nome, Pageable pageRequest);
}
