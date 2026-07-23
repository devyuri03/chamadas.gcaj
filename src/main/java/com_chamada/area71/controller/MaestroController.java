package com_chamada.area71.controller;

import com_chamada.area71.DTO.MaestroResponseDTO;
import com_chamada.area71.repository.MaestroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/maestros")
@RequiredArgsConstructor
public class MaestroController {

    private final MaestroRepository maestroRepository;

    @GetMapping
    public ResponseEntity<List<MaestroResponseDTO>> listar() {
        List<MaestroResponseDTO> maestros = maestroRepository.findAll().stream()
                .map(MaestroResponseDTO::from)
                .toList();
        return ResponseEntity.ok(maestros);
    }

}
