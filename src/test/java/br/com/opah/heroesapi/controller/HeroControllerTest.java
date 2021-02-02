package br.com.opah.heroesapi.controller;

import br.com.opah.heroesapi.builder.domain.HeroDataTestBuilder;
import br.com.opah.heroesapi.controller.converter.HeroToHeroResourceConverter;
import br.com.opah.heroesapi.domain.Hero;
import br.com.opah.heroesapi.usecase.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HeroControllerTest {

    @InjectMocks
    private HeroController heroController;

    @Mock
    private FindHeroesUseCase findHeroesUseCase;
    @Mock
    private FindHeroByIdUseCase findHeroByIdUseCase;
    @Mock
    private FindFavoritesUseCase findFavoritesUseCase;
    @Mock
    private FavoriteHeroUseCase favoriteHeroUseCase;
    @Mock
    private DisfavorHeroUseCase disfavorHeroUseCase;

    @Spy
    private HeroToHeroResourceConverter toHeroResourceConverter = new HeroToHeroResourceConverter(new ModelMapper());

    private static final String NOME = "";
    private static final Integer PAGE = 0;
    private static final Integer HEROES_PER_PAGE = 10;
    private static final String ORDER_BY = "id";
    private static final String DIRECTION = "ASC";
    private static final Integer ID = 1;

    @Test
    public void shouldFindHeroes(){
        final var heroPageMock = HeroDataTestBuilder.getHeroPage();

        doReturn(heroPageMock)
                .when(findHeroesUseCase).execute(anyString(), anyInt(), anyInt(), anyString(), anyString());

        final var heroPageResourceResponse = heroController.findHeroes(NOME, PAGE, HEROES_PER_PAGE, ORDER_BY, DIRECTION);

        final var heroPageMockObj = heroPageMock.get().findFirst().get();
        final var heroPageResponseObj = heroPageResourceResponse.get().findFirst().get();

        assertEquals(heroPageMockObj.getId(), heroPageResponseObj.getId());
        verify(findHeroesUseCase, atLeastOnce()).execute(anyString(), anyInt(), anyInt(), anyString(), anyString());
        verify(toHeroResourceConverter, atLeastOnce()).convert(any(Hero.class));
    }

    @Test
    public void shouldFindHeroById(){
        final var heroMock = HeroDataTestBuilder.getHero();

        doReturn(heroMock)
                .when(findHeroByIdUseCase).execute(anyInt());

        final var heroResponse = heroController.findHero(ID);

        assertEquals(heroMock.getId(), heroResponse.getId());
        verify(findHeroByIdUseCase, atLeastOnce()).execute(anyInt());
        verify(toHeroResourceConverter, atLeastOnce()).convert(any(Hero.class));
    }

    @Test
    public void shouldFindFavorites(){
        final var heroFavPageMock = HeroDataTestBuilder.getHeroPage();

        doReturn(heroFavPageMock)
                .when(findFavoritesUseCase).execute(anyString(), anyInt(), anyInt(), anyString(), anyString());

        final var heroFavPageResourceResponse = heroController.findFavorites(NOME, PAGE, HEROES_PER_PAGE, ORDER_BY, DIRECTION);

        final var heroFavPageMockObj = heroFavPageMock.get().findFirst().get();
        final var heroFavPageResponseObj = heroFavPageResourceResponse.get().findFirst().get();

        assertEquals(heroFavPageMockObj.getId(), heroFavPageResponseObj.getId());
        verify(findFavoritesUseCase, atLeastOnce()).execute(anyString(), anyInt(), anyInt(), anyString(), anyString());
        verify(toHeroResourceConverter, atLeastOnce()).convert(any(Hero.class));
    }

    @Test
    public void shouldFavoriteHero(){

        doNothing().when(favoriteHeroUseCase).execute(anyInt());

        heroController.favoriteHero(ID);
        verify(favoriteHeroUseCase, atLeastOnce()).execute(anyInt());
    }

    @Test
    public void shouldDisfavorHero(){

        doNothing().when(disfavorHeroUseCase).execute(anyInt());

        heroController.disfavorHero(ID);
        verify(disfavorHeroUseCase, atLeastOnce()).execute(anyInt());
    }
}
