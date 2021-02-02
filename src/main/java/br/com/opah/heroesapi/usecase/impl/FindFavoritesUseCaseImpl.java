package br.com.opah.heroesapi.usecase.impl;

import br.com.opah.heroesapi.domain.Hero;
import br.com.opah.heroesapi.gateway.HeroGateway;
import br.com.opah.heroesapi.usecase.FindFavoritesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindFavoritesUseCaseImpl implements FindFavoritesUseCase {

    private final HeroGateway gateway;

    @Override
    public Page<Hero> execute(String nome, Integer page, Integer heroesPerPage, String orderBy, String direction) {
        return gateway.findFavorites(nome, page, heroesPerPage, orderBy, direction);
    }
}
