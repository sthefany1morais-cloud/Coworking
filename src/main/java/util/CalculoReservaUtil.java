package util;



import model.espacos.Espaco;
import model.espacos.SalaDeReuniao;

import java.time.Duration;
import java.time.LocalDateTime;

public class CalculoReservaUtil {

    public static double calcularHoras(LocalDateTime inicio, LocalDateTime fim) {
        long minutos = Duration.between(inicio, fim).toMinutes();
        return minutos / 60.0;
    }

    public static double calcularValor(Espaco espaco, double horas, boolean projetor) {
        if (espaco instanceof SalaDeReuniao) {
            return ((SalaDeReuniao) espaco).calcularCustoReserva(horas, projetor);
        }
        return espaco.calcularCustoReserva(horas);
    }

    public static double calcularCustoParaView(Espaco espaco, LocalDateTime inicio, LocalDateTime fim, boolean projetor) {
        double horas = calcularHoras(inicio, fim);
        return calcularValor(espaco, horas, projetor);
    }
}
