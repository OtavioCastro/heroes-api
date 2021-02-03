package br.com.opah.heroesapi.controller;

import br.com.opah.heroesapi.controller.converter.HeroToHeroDetailResourceConverter;
import br.com.opah.heroesapi.controller.converter.HeroToHeroResourceConverter;
import br.com.opah.heroesapi.controller.resource.HeroDetailResource;
import br.com.opah.heroesapi.controller.resource.HeroResource;
import br.com.opah.heroesapi.usecase.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/heroes")
public class HeroController {

    private final FindHeroesUseCase findHeroesUseCase;
    private final FindHeroByIdUseCase findHeroByIdUseCase;
    private final FindFavoritesUseCase findFavoritesUseCase;
    private final FavoriteHeroUseCase favoriteHeroUseCase;
    private final DisfavorHeroUseCase disfavorHeroUseCase;

    private final HeroToHeroResourceConverter toHeroResourceConverter;
    private final HeroToHeroDetailResourceConverter toHeroDetailResourceConverter;

    @GetMapping
    @ApiOperation("Busca paginada de heróis")
    public Page<HeroResource> findHeroes(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "heroesPerPage", defaultValue = "10") Integer heroesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        return findHeroesUseCase.execute(nome, page, heroesPerPage, orderBy, direction)
                .map(toHeroResourceConverter::convert);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Retorna o Herói do ID solicitado")
    public HeroDetailResource findHero(@PathVariable("id") Integer id){

        return toHeroDetailResourceConverter.convert(findHeroByIdUseCase.execute(id));
    }

    @GetMapping(value = "/favorites")
    @ApiOperation("Busca paginada de heróis favoritos")
    public Page<HeroResource> findFavorites(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "heroesPerPage", defaultValue = "10") Integer heroesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        return findFavoritesUseCase.execute(nome, page, heroesPerPage, orderBy, direction)
                .map(toHeroResourceConverter::convert);
    }

    @PutMapping(value = "/favorites/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Favoritar o Herói")
    public void favoriteHero(@PathVariable("id") Integer id){
        favoriteHeroUseCase.execute(id);
    }

    @DeleteMapping(value = "/favorites/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Desfavoritar o Herói")
    public void disfavorHero(@PathVariable("id") Integer id){
        disfavorHeroUseCase.execute(id);
    }
}
