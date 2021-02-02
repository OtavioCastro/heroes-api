package br.com.opah.heroesapi.repository.impl;

import br.com.opah.heroesapi.domain.Hero;
import br.com.opah.heroesapi.gateway.HeroGateway;
import br.com.opah.heroesapi.repository.HeroRepository;
import br.com.opah.heroesapi.repository.converter.HeroDBToHeroConverter;
import br.com.opah.heroesapi.repository.converter.HeroToHeroDBConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
@AllArgsConstructor
public class HeroGatewayImpl implements HeroGateway {

    private final HeroDBToHeroConverter toHeroConverter;
    private final HeroToHeroDBConverter toHeroDBConverter;

    private final HeroRepository repository;

    @Override
    public Page<Hero> findHeroes(String nome, Integer page, Integer heroesPerPage, String orderBy, String direction) {
        final var pageRequest = PageRequest.of(page, heroesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findDistinctByNomeContaining(nome, pageRequest)
                .map(toHeroConverter::convert);
    }

    @Override
    public Page<Hero> findFavorites(String nome, Integer page, Integer heroesPerPage, String orderBy, String direction) {
        final var pageRequest = PageRequest.of(page, heroesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findDistinctByNomeContainingAndFavoritoTrue(nome, pageRequest)
                .map(toHeroConverter::convert);
    }

    @Override
    public Hero findHeroById(Integer id) {
        return repository.findById(id)
                .map(toHeroConverter::convert)
                .orElseGet(Hero::new);
    }

    @Override
    public void favoriteOrDisfavorHero(Hero hero) {
        ofNullable(hero)
                .map(toHeroDBConverter::convert)
                .map(repository::save);
    }
}
