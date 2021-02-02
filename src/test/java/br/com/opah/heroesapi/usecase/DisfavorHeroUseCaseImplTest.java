package br.com.opah.heroesapi.usecase;

import br.com.opah.heroesapi.builder.domain.HeroDataTestBuilder;
import br.com.opah.heroesapi.domain.Hero;
import br.com.opah.heroesapi.gateway.HeroGateway;
import br.com.opah.heroesapi.usecase.impl.DisfavorHeroUseCaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DisfavorHeroUseCaseImplTest {

    @InjectMocks
    private DisfavorHeroUseCaseImpl disfavorHeroUseCase;

    @Mock
    private HeroGateway heroGateway;

    private static final Integer ID = 1;

    @Test
    public void shouldDisfavorHero(){
        final var heroMock = HeroDataTestBuilder.getHero();

        doReturn(heroMock)
                .when(heroGateway).findHeroById(anyInt());

        disfavorHeroUseCase.execute(ID);

        verify(heroGateway, atLeastOnce()).findHeroById(anyInt());
    }

    @Test(expected = HttpClientErrorException.class)
    public void shouldNotFoundHero(){
        doReturn(Hero.builder().build())
                .when(heroGateway).findHeroById(anyInt());

        disfavorHeroUseCase.execute(ID);

        verify(heroGateway, atLeastOnce()).findHeroById(anyInt());
    }
}
