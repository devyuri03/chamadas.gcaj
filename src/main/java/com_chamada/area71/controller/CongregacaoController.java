package com_chamada.area71.controller;

import com_chamada.area71.DTO.CongregacaoResponseDTO;
import com_chamada.area71.repository.CongregacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/congregacoes")
@RequiredArgsConstructor
public class CongregacaoController {

    private final CongregacaoRepository congregacaoRepository;

    @GetMapping
    public ResponseEntity<List<CongregacaoResponseDTO>> listar() {
        List<CongregacaoResponseDTO> congregacoes = congregacaoRepository.findAll().stream()
                .map(CongregacaoResponseDTO::from)
                .toList();
        return ResponseEntity.ok(congregacoes);
    }

}
