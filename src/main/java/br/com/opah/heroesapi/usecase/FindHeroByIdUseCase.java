package br.com.opah.heroesapi.usecase;

import br.com.opah.heroesapi.domain.Hero;

public interface FindHeroByIdUseCase {
    Hero execute(Integer id);
}
