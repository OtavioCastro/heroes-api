package br.com.opah.heroesapi.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeroDetailResource {

    private Integer id;
    private String nome;
    private Integer idade;
    private String historia;
    private String fotoPerfil;
    private Boolean favorito;
    private List<String> superPoderes;

}
