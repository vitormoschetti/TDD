package br.com.caelum.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

import java.util.Arrays;

public class AvaliadorTest {

    @Test
    public void deveEntenderLancesEmOrdermCrescente() {

        final Double MAIORLANCE = 400.0;
        final Double MENORLANCE = 150.0;
        final Double MEDIALANCES = 300.0;

        final Usuario jose = new Usuario("josé");
        final Usuario joao = new Usuario("joão");
        final Usuario maria = new Usuario("maria");

        Leilao leilao = new Leilao("Playstation 3 novo em folha");

        leilao.propoe(new Lance(jose, MENORLANCE ));
        leilao.propoe(new Lance(joao, 350.0 ));
        leilao.propoe(new Lance(maria, MAIORLANCE));

        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);

        System.out.println(leiloeiro.getMaioresLances());

        Assertions.assertEquals(MAIORLANCE, leiloeiro.getMaiorLance());
        Assertions.assertEquals(MENORLANCE, leiloeiro.getMenorLance());
        Assertions.assertEquals(MEDIALANCES, leiloeiro.getMediaLances());
    }

    @Test
    public void testaMediaDeZeroLance(){

        Usuario vitor = new Usuario("vitor");
        Leilao leilao = new Leilao("Playstation 32");

        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);

        Assertions.assertEquals(0, leiloeiro.getMediaLances() );

    }

    @Test
    public void testaApenasUmLance(){

        Usuario vitor = new Usuario("vitor");
        Leilao leilao = new Leilao("Playstation 32");

        leilao.propoe(new Lance(vitor, 1000.0));

        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);

        Assertions.assertEquals(1000.0, leiloeiro.getMediaLances());
        Assertions.assertEquals(1000.0, leiloeiro.getMenorLance());
        Assertions.assertEquals(1000.0, leiloeiro.getMediaLances());

    }

    @Test
    public void testaPegaTresMaioresLances() {
        final Double MAIORLANCE = 400.0;
        final Double MENORLANCE = 150.0;

        final Usuario jose = new Usuario("josé");
        final Usuario joao = new Usuario("joão");
        final Usuario maria = new Usuario("maria");

        Leilao leilao = new Leilao("Playstation 3 novo em folha");

//        leilao.propoe(new Lance(jose, MENORLANCE ));
        leilao.propoe(new Lance(joao, 350.0 ));
        leilao.propoe(new Lance(maria, MAIORLANCE));
//        leilao.propoe(new Lance(jose, 150.0));
//        leilao.propoe(new Lance(jose, 100.0));

        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);

        Assertions.assertEquals(Arrays.asList(400.0, 350.0), leiloeiro.getMaioresLances());
    }


}
