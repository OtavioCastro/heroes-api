package br.com.opah.heroesapi.gateway;

import br.com.opah.heroesapi.domain.Hero;
import org.springframework.data.domain.Page;

public interface HeroGateway {
    Page<Hero> findHeroes(String nome, Integer page, Integer heroesPerPage, String orderBy, String direction);
    Page<Hero> findFavorites(String nome, Integer page, Integer heroesPerPage, String orderBy, String direction);
    Hero findHeroById(Integer id);
    void favoriteOrDisfavorHero(Hero hero);
}
