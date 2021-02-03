package br.com.opah.heroesapi.controller.converter;

import br.com.opah.heroesapi.controller.resource.HeroDetailResource;
import br.com.opah.heroesapi.controller.resource.HeroResource;
import br.com.opah.heroesapi.domain.Hero;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HeroToHeroDetailResourceConverter {

    private final ModelMapper mapper;

    public HeroDetailResource convert(Hero hero){
        return mapper.map(hero, HeroDetailResource.class);
    }
}
