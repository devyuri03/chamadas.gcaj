package com_chamada.area71.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ensaio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate data;

    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "ensaio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresencaCongregacao> presencasCongregacao = new ArrayList<>();

    @OneToMany(mappedBy = "ensaio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresencaMaestro> presencasMaestro = new ArrayList<>();

    @OneToMany(mappedBy = "ensaio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresencaDirigentes> presencasDirigentes = new ArrayList<>();

}