package br.com.opah.heroesapi.repository;

import br.com.opah.heroesapi.builder.domain.HeroDataTestBuilder;
import br.com.opah.heroesapi.builder.repository.HeroDBDataTestBuilder;
import br.com.opah.heroesapi.domain.Hero;
import br.com.opah.heroesapi.repository.converter.HeroDBToHeroConverter;
import br.com.opah.heroesapi.repository.converter.HeroToHeroDBConverter;
import br.com.opah.heroesapi.repository.impl.HeroGatewayImpl;
import br.com.opah.heroesapi.repository.model.HeroDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HeroGatewayImplTest {

    @InjectMocks
    private HeroGatewayImpl heroGateway;

    @Mock
    private HeroRepository heroRepository;

    @Spy
    private HeroDBToHeroConverter toHeroConverter = new HeroDBToHeroConverter(new ModelMapper());
    @Spy
    private HeroToHeroDBConverter toHeroDBConverter = new HeroToHeroDBConverter(new ModelMapper());

    private static final String NOME = "";
    private static final Integer PAGE = 0;
    private static final Integer HEROES_PER_PAGE = 10;
    private static final String ORDER_BY = "id";
    private static final String DIRECTION = "ASC";
    private static final Integer ID = 1;

    @Test
    public void shouldFindHeroesDB(){
        final var heroDBPageMock = HeroDBDataTestBuilder.getHeroDBPage();

        doReturn(heroDBPageMock)
                .when(heroRepository).findDistinctByNomeContaining(anyString(), any(Pageable.class));

        final var heroDBPageResponse = heroGateway.findHeroes(NOME, PAGE, HEROES_PER_PAGE, ORDER_BY, DIRECTION);

        final var heroDBPageMockObj = heroDBPageMock.get().findFirst().get();
        final var heroDBPageResponseObj = heroDBPageResponse.get().findFirst().get();

        assertEquals(heroDBPageMockObj.getId(), heroDBPageResponseObj.getId());
        verify(heroRepository, atLeastOnce()).findDistinctByNomeContaining(anyString(), any(Pageable.class));
    }

    @Test
    public void shouldFindFavorites(){
        final var heroDBPageMock = HeroDBDataTestBuilder.getHeroDBPage();

        doReturn(heroDBPageMock)
                .when(heroRepository).findDistinctByNomeContainingAndFavoritoTrue(anyString(), any(Pageable.class));

        final var heroDBPageResponse = heroGateway.findFavorites(NOME, PAGE, HEROES_PER_PAGE, ORDER_BY, DIRECTION);

        final var heroDBPageMockObj = heroDBPageMock.get().findFirst().get();
        final var heroDBPageResponseObj = heroDBPageResponse.get().findFirst().get();

        assertEquals(heroDBPageMockObj.getId(), heroDBPageResponseObj.getId());
        verify(heroRepository, atLeastOnce()).findDistinctByNomeContainingAndFavoritoTrue(anyString(), any(Pageable.class));
    }

    @Test
    public void shouldFindHeroById(){
        final var heroDBMock = HeroDBDataTestBuilder.getHeroDB();

        doReturn(Optional.of(heroDBMock))
                .when(heroRepository).findById(anyInt());

        final var heroDBResponse = heroGateway.findHeroById(ID);

        assertEquals(heroDBMock.getId(), heroDBResponse.getId());
        verify(heroRepository, atLeastOnce()).findById(anyInt());
        verify(toHeroConverter, atLeastOnce()).convert(any(HeroDB.class));
    }

    @Test
    public void shouldFavoriteOrDisfavorHero(){
        final var heroMock = HeroDataTestBuilder.getHero();

        heroGateway.favoriteOrDisfavorHero(heroMock);

        verify(heroRepository, atLeastOnce()).save(any(HeroDB.class));
        verify(toHeroDBConverter, atLeastOnce()).convert(any(Hero.class));
    }
}
