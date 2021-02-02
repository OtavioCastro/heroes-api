package br.com.opah.heroesapi.builder.repository;

import br.com.opah.heroesapi.repository.model.HeroDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Set;

public class HeroDBDataTestBuilder {

    public static HeroDB getHeroDB(){
        return HeroDB.builder()
                .id(1)
                .nome("Homem Formiga")
                .idade(26)
                .historia("Cientista que pode reduzir seu tamanho ao de uma formiga")
                .superPoderes(Set.of("Super Inteligência"))
                .build();
    }

    public static Page<HeroDB> getHeroDBPage(){
        return new PageImpl<>(List.of(getHeroDB()));
    }
}
