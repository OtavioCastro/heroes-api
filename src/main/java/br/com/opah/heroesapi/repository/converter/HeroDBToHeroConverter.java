package br.com.opah.heroesapi.repository.converter;

import br.com.opah.heroesapi.domain.Hero;
import br.com.opah.heroesapi.repository.model.HeroDB;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HeroDBToHeroConverter {

    private ModelMapper mapper;

    public Hero convert(HeroDB heroDB){
        return mapper.map(heroDB, Hero.class);
    }

}
