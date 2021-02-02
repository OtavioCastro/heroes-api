package br.com.opah.heroesapi.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hero")
public class HeroDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer idade;
    private String historia;
    private String fotoPerfil;
    @Builder.Default
    private Boolean favorito = Boolean.FALSE;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "SUPERPOWERS", joinColumns = @JoinColumn(name = "HEROID"))
    private Set<String> superPoderes = new HashSet<>();

}
