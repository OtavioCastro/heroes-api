package br.com.opah.heroesapi.usecase;

import br.com.opah.heroesapi.builder.domain.HeroDataTestBuilder;
import br.com.opah.heroesapi.domain.Hero;
import br.com.opah.heroesapi.gateway.HeroGateway;
import br.com.opah.heroesapi.usecase.impl.FindHeroByIdUseCaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FindHeroByIdUseCaseImplTest {

    @InjectMocks
    private FindHeroByIdUseCaseImpl findHeroByIdUseCase;

    @Mock
    private HeroGateway heroGateway;

    private static final Integer ID = 1;

    @Test
    public void shouldFindHeroById(){
        final var heroMock = HeroDataTestBuilder.getHero();
        doReturn(heroMock)
                .when(heroGateway).findHeroById(anyInt());

        final var heroResponse = findHeroByIdUseCase.execute(ID);

        assertEquals(heroMock.getId(), heroResponse.getId());
        verify(heroGateway, atLeastOnce()).findHeroById(anyInt());
    }

    @Test(expected = HttpClientErrorException.class)
    public void shouldNotFoundHero(){
        doReturn(Hero.builder().build())
                .when(heroGateway).findHeroById(anyInt());

        findHeroByIdUseCase.execute(ID);

        verify(heroGateway, atLeastOnce()).findHeroById(anyInt());
    }
}
