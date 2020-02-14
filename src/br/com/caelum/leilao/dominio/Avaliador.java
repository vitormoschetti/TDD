package br.com.caelum.leilao.dominio;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Avaliador {

    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;
    private double mediaLances = 0;
    private List<Double> maioresLances;


    public void avalia(Leilao leilao) {

        if(leilao.getLances().isEmpty()) {
            throw new RuntimeException("Não há lances para avaliar");
        }

        OptionalDouble maiorLance = leilao.getLances().stream().mapToDouble(Lance::getValor).max();
        if(maiorLance.isPresent()) this.maiorLance = maiorLance.getAsDouble();

        OptionalDouble menorLance = leilao.getLances().stream().mapToDouble(Lance::getValor).min();
        if(menorLance.isPresent()) this.menorLance = menorLance.getAsDouble();

        OptionalDouble mediaLances = leilao.getLances().stream().mapToDouble(Lance::getValor).average();
        if(mediaLances.isPresent()) this.mediaLances = mediaLances.getAsDouble();

        maioresLances = leilao.getLances().stream()
                .sorted(Comparator.comparingDouble(Lance::getValor).reversed())
                .mapToDouble(Lance::getValor)
                .limit(3)
                .boxed()
                .collect(Collectors.toList());

    }

    public List<Double> getMaioresLances() {
        return maioresLances;
    }

    public double getMediaLances() {
        return mediaLances;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }
}
