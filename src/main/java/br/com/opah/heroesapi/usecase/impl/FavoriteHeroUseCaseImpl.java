package br.com.opah.heroesapi.usecase.impl;

import br.com.opah.heroesapi.gateway.HeroGateway;
import br.com.opah.heroesapi.usecase.FavoriteHeroUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class FavoriteHeroUseCaseImpl implements FavoriteHeroUseCase {

    private final HeroGateway gateway;

    @Override
    public void execute(Integer id) {
        final var hero = Optional.of(gateway.findHeroById(id))
                .filter(heroGateway -> nonNull(heroGateway.getId()))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "HERÓI NÃO ENCONTRADO"));
        final var heroUpdate = hero.toBuilder()
                .favorito(Boolean.TRUE)
                .build();
        gateway.favoriteOrDisfavorHero(heroUpdate);
    }
}
