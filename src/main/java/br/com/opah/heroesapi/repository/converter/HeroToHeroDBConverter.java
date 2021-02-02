package br.com.opah.heroesapi.repository.converter;

import br.com.opah.heroesapi.domain.Hero;
import br.com.opah.heroesapi.repository.model.HeroDB;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HeroToHeroDBConverter {

    private ModelMapper mapper;

    public HeroDB convert(Hero hero){
        return mapper.map(hero, HeroDB.class);
    }

}
