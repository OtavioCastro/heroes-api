package br.com.opah.heroesapi.usecase;

import br.com.opah.heroesapi.builder.domain.HeroDataTestBuilder;
import br.com.opah.heroesapi.gateway.HeroGateway;
import br.com.opah.heroesapi.usecase.impl.FindFavoritesUseCaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FindFavoritesUseCaseImplTest {

    @InjectMocks
    private FindFavoritesUseCaseImpl findFavoritesUseCase;

    @Mock
    private HeroGateway heroGateway;

    private static final String NOME = "";
    private static final Integer PAGE = 0;
    private static final Integer HEROES_PER_PAGE = 10;
    private static final String ORDER_BY = "id";
    private static final String DIRECTION = "ASC";

    @Test
    public void shouldFindFavorites(){
        final var heroPageMock = HeroDataTestBuilder.getHeroPage();

        doReturn(heroPageMock)
                .when(heroGateway).findFavorites(anyString(), anyInt(), anyInt(), anyString(), anyString());

        final var heroPageResponse = findFavoritesUseCase.execute(NOME, PAGE, HEROES_PER_PAGE, ORDER_BY, DIRECTION);

        final var heroPageMockObj = heroPageMock.get().findFirst().get();
        final var heroPageResponseObj = heroPageResponse.get().findFirst().get();

        assertEquals(heroPageMockObj.getId(), heroPageResponseObj.getId());
        verify(heroGateway, atLeastOnce()).findFavorites(anyString(), anyInt(), anyInt(), anyString(), anyString());
    }
}
