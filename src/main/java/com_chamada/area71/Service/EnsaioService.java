package com_chamada.area71.Service;

import com_chamada.area71.DTO.EnsaioRequestDTO;
import com_chamada.area71.DTO.EnsaioResponseDTO;
import com_chamada.area71.exception.EnsaioJaExisteException;
import com_chamada.area71.model.Congregacao;
import com_chamada.area71.model.Ensaio;
import com_chamada.area71.model.Maestro;
import com_chamada.area71.model.PresencaCongregacao;
import com_chamada.area71.model.PresencaMaestro;
import com_chamada.area71.repository.CongregacaoRepository;
import com_chamada.area71.repository.EnsaioRepository;
import com_chamada.area71.repository.MaestroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnsaioService {

    private final EnsaioRepository ensaioRepository;
    private final CongregacaoRepository congregacaoRepository;
    private final MaestroRepository maestroRepository;

    @Transactional
    public EnsaioResponseDTO criar(EnsaioRequestDTO dto) {
        if (ensaioRepository.existsByData(dto.data())) {
            throw new EnsaioJaExisteException(
                    "Já existe um ensaio registrado na data " + dto.data());
        }

        Ensaio ensaio = new Ensaio();
        ensaio.setData(dto.data());
        ensaio.setCriadoEm(LocalDateTime.now());

        dto.congregacoes().forEach(c -> {
            Congregacao congregacao = congregacaoRepository.findById(c.congregacaoId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Congregação não encontrada: " + c.congregacaoId()));

            PresencaCongregacao presenca = new PresencaCongregacao();
            presenca.setEnsaio(ensaio);
            presenca.setCongregacao(congregacao);
            presenca.setQtdAdolescentes(c.qtdAdolescentes());
            presenca.setQtdJovens(c.qtdJovens());
            ensaio.getPresencasCongregacao().add(presenca);
        });

        if (dto.maestros() != null) {
            dto.maestros().forEach(m -> {
                Maestro maestro = maestroRepository.findById(m.maestroId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Maestro não encontrado: " + m.maestroId()));

                PresencaMaestro presenca = new PresencaMaestro();
                presenca.setEnsaio(ensaio);
                presenca.setMaestro(maestro);
                presenca.setPresente(m.presente());
                ensaio.getPresencasMaestro().add(presenca);
            });
        }

        Ensaio salvo = ensaioRepository.save(ensaio);
        return EnsaioResponseDTO.from(salvo);
    }

    @Transactional
    public List<EnsaioResponseDTO> listar() {
        return ensaioRepository.findAll().stream()
                .map(EnsaioResponseDTO::from)
                .toList();
    }

    @Transactional
    public EnsaioResponseDTO buscarPorId(Long id) {
        Ensaio ensaio = ensaioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ensaio não encontrado: " + id));
        return EnsaioResponseDTO.from(ensaio);
    }

}
