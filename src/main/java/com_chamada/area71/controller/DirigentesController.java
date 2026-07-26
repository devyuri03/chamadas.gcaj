package com_chamada.area71.controller;

import com_chamada.area71.DTO.DirigentesResponseDTO;
import com_chamada.area71.repository.DirigentesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dirigentes")
@RequiredArgsConstructor
public class DirigentesController {

    private final DirigentesRepository dirigentesRepository;

    @GetMapping






    public ResponseEntity<List<DirigentesResponseDTO>> listar() {
        List<DirigentesResponseDTO> dirigentes = dirigentesRepository.findAll().stream()
                .map(DirigentesResponseDTO::from)
                .toList();
        return ResponseEntity.ok(dirigentes);
    }

}