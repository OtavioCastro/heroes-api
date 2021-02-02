package br.com.opah.heroesapi.usecase.impl;

import br.com.opah.heroesapi.domain.Hero;
import br.com.opah.heroesapi.gateway.HeroGateway;
import br.com.opah.heroesapi.usecase.FindHeroByIdUseCase;
import br.com.opah.heroesapi.usecase.FindHeroesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static java.util.Optional.*;

@Service
@AllArgsConstructor
public class FindHeroByIdUseCaseImpl implements FindHeroByIdUseCase {

    private final HeroGateway gateway;

    @Override
    public Hero execute(Integer id) {
        return of(gateway.findHeroById(id))
                .filter(hero -> nonNull(hero.getId()))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "HERÓI NÃO ENCONTRADO"));
    }
}
