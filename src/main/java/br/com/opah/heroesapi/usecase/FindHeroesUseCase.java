package br.com.opah.heroesapi.usecase;

import br.com.opah.heroesapi.domain.Hero;
import org.springframework.data.domain.Page;

public interface FindHeroesUseCase {
    Page<Hero> execute(String nome, Integer page, Integer heroesPerPage, String orderBy, String direction);
}
