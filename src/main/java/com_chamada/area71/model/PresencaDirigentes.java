package com_chamada.area71.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PresencaDirigentes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ensaio_id")
    private Ensaio ensaio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dirigente_id")
    private Dirigentes dirigente;

    private Boolean presente;

}
