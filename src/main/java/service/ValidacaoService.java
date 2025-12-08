package service;

import execoes.ValidacaoException;

import java.time.LocalDateTime;
import java.util.List;

public class ValidacaoService {

    public static void validarPeriodo(LocalDateTime inicio, LocalDateTime fim) throws ValidacaoException {
        if (inicio == null || fim == null) {
            throw new ValidacaoException(List.of("Datas de início e fim são obrigatórias."));
        }
        if (inicio.isAfter(fim)) {
            throw new ValidacaoException(List.of("Data de início deve ser anterior à de fim."));
        }
    }
}
