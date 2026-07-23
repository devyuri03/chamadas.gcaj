package com_chamada.area71.controller;

import com_chamada.area71.DTO.EnsaioRequestDTO;
import com_chamada.area71.DTO.EnsaioResponseDTO;
import com_chamada.area71.Service.EnsaioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ensaios")
@RequiredArgsConstructor
public class EnsaioController {

    private final EnsaioService ensaioService;

    @PostMapping
    public ResponseEntity<EnsaioResponseDTO> criar(@RequestBody @Valid EnsaioRequestDTO dto) {
        EnsaioResponseDTO response = ensaioService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EnsaioResponseDTO>> listar() {
        return ResponseEntity.ok(ensaioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnsaioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ensaioService.buscarPorId(id));
    }

}
