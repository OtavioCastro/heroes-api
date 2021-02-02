package br.com.opah.heroesapi.utils;

import br.com.opah.heroesapi.repository.HeroRepository;
import br.com.opah.heroesapi.repository.model.HeroDB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DBService {

    private final HeroRepository repository;

    public void instantiateDatabase() throws ParseException {

        final var hero1 = HeroDB.builder()
                .nome("Homem Aranha")
                .idade(20)
                .historia("Mordido por uma aranha radioativa, o estudante do ensino médio Peter Parker ganhou a velocidade, a força e os poderes de uma aranha. ")
                .fotoPerfil("caminhoFoto")
                .superPoderes(Set.of("Super força", "Inteligência", "Velocidade", "Reflexos"))
                .build();


        final var hero2 = HeroDB.builder()
                .nome("Homem de Ferro")
                .idade(50)
                .historia("Ferido, capturado e forçado a construir uma arma por seus inimigos, o industrial bilionário Tony Stark ")
                .fotoPerfil("caminhoFoto")
                .superPoderes(Set.of("Inteligência", "Dinheiro", "Tecnologias militares"))
                .build();

        final var hero3 = HeroDB.builder()
                .nome("Wolverine")
                .idade(100)
                .historia("Nascido com sentidos sobre-humanos e o poder de curar quase qualquer ferida, ")
                .fotoPerfil("caminhoFoto")
                .superPoderes(Set.of("Super força", "Regeneração", "Adamantium"))
                .build();

        final var hero4 = HeroDB.builder()
                .nome("Capitão América")
                .idade(100)
                .historia("Prometendo servir seu país de qualquer maneira que pudesse, o jovem Steve Rogers tomou o soro do super soldado para se tornar o exército de um homem só da América. ")
                .fotoPerfil("caminhoFoto")
                .superPoderes(Set.of("Super força", "Inteligência", "Resistência", "Reflexos"))
                .build();

        final var hero5 = HeroDB.builder()
                .nome("Hulk")
                .idade(45)
                .historia("Pego em uma explosão de bomba gama enquanto tentava salvar a vida de um adolescente, ")
                .fotoPerfil("caminhoFoto")
                .superPoderes(Set.of("Super força", "Regeneração"))
                .build();

        final var hero6 = HeroDB.builder()
                .nome("Tocha humana")
                .idade(25)
                .historia("Em consequência da exposição aos raios cósmicos que sofreu durante um voo espacial, Johnny adquiriu o poder de envolver seu corpo em chamas. " +
                        "Com o corpo nesse estado ele é capaz de voar, disparar chamas e absorver energia do calor. ")
                .fotoPerfil("caminhoFoto")
                .superPoderes(Set.of("Fogo"))
                .build();

        final var hero7 = HeroDB.builder()
                .nome("Coisa")
                .idade(40)
                .historia("Ben Grimm, após uma viagem espacial, foi exposto a raios cósmicos que transformaram seu corpo em algo rochoso, adquirindo também uma super força.")
                .fotoPerfil("caminhoFoto")
                .superPoderes(Set.of("Corpo de pedra", "Super força"))
                .build();

        final var hero8 = HeroDB.builder()
                .nome("Ciclope")
                .idade(23)
                .historia("Scott Summers é o mutante líder dos X-Men")
                .fotoPerfil("caminhoFoto")
                .superPoderes(Set.of("Raios oculares"))
                .build();

        final var hero9 = HeroDB.builder()
                .nome("Jean Grey")
                .idade(23)
                .historia("Jean é um dos membros mais importantes dos X-Men, sendo intitulada por diversas vezes como o coração do grupo.")
                .fotoPerfil("caminhoFoto")
                .superPoderes(Set.of("Poderes telepáticos", "Telecinese"))
                .build();

        final var hero10 = HeroDB.builder()
                .nome("Demolidor")
                .idade(35)
                .historia("Matthew Murdock salva um homem de idade de um caminhão em andamento que continha uma carga radioativa. " +
                        "Como consequência do acidente a carga começa a verter do caminhão, cegando Murdock.")
                .fotoPerfil("caminhoFoto")
                .superPoderes(Set.of("Super sentidos", "Força"))
                .build();

        final var hero11 = HeroDB.builder()
                .nome("Noturno")
                .idade(30)
                .historia("Como um mutante, Noturno possui agilidade sobre-humana, e a capacidade de teletransporte, invisibilidade em sombras profundas, e as mãos e os pés aderirem a parede.")
                .fotoPerfil("caminhoFoto")
                .superPoderes(Set.of("Teletransporte", "Super agilidade"))
                .build();

        repository.saveAll(List.of(hero1, hero2, hero3, hero4, hero5, hero6, hero7, hero8, hero9, hero10, hero11));
    }

}
