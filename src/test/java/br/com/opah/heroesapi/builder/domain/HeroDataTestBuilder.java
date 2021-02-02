package br.com.opah.heroesapi.builder.domain;

import br.com.opah.heroesapi.domain.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class HeroDataTestBuilder {

    public static Hero getHero(){
        return Hero.builder()
                .id(1)
                .nome("Homem Formiga")
                .idade(26)
                .favorito(true)
                .historia("Cientista que pode reduzir seu tamanho ao de uma formiga")
                .superPoderes(List.of("Super Inteligência"))
                .build();
    }

    public static Page<Hero> getHeroPage(){
        return new PageImpl<>(List.of(getHero()));
    }
}
