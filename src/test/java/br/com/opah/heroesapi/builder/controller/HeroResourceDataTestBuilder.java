/*
package br.com.opah.heroesapi.builder.controller;

import br.com.opah.heroesapi.controller.resource.HeroResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class HeroResourceDataTestBuilder {

    public static HeroResource getHeroResource(){
        return HeroResource.builder()
                .id(1)
                .nome("Homem Formiga")
                .idade(26)
                .historia("Cientista que pode reduzir seu tamanho ao de uma formiga")
                .superPoderes(List.of("Super Inteligência"))
                .build();
    }

    public static Page<HeroResource> getHeroPageResource(){
        return new PageImpl<>(List.of(getHeroResource()));
    }
}
*/
