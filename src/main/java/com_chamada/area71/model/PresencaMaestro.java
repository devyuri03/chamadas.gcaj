package com_chamada.area71.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PresencaMaestro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ensaio_id")
    private Ensaio ensaio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maestro_id")
    private Maestro maestro;

    private Boolean presente;

}
