package br.com.caelum.tests;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeilaoTest {

    @Test
    public void deveReceberApenasUmLance() {

        Leilao leilao = new Leilao("Macbook Pro 15");
        leilao.propoe(new Lance(new Usuario("Steve"), 2000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor());

    }

    @Test
    public void deveReceberVariosLances() {

        Leilao leilao = new Leilao("Macbook Pro 15");
        leilao.propoe(new Lance(new Usuario("Steve"), 2000.0));
        leilao.propoe(new Lance(new Usuario("Mark"), 2300.0));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor());

    }

    //Um usuario não pode fazer dois lances seguidos

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new Leilao("Mackbook Pro 15");
        Usuario u = new Usuario("Vitor Moschetti");

        leilao.propoe(new Lance(u, 2000.0));
        leilao.propoe(new Lance(u, 4000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor());

    }

    //Um usuario só pode fazer no máximo 5 lances
    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario(){

        Leilao leilao = new Leilao("Mackbook Pro 15");
        Usuario u1 = new Usuario("Vitor Moschetti");
        Usuario u2 = new Usuario("Amanda Sierra");

        leilao.propoe(new Lance(u1, 2000.0));
        leilao.propoe(new Lance(u2, 2500.0));

        leilao.propoe(new Lance(u1, 3000.0));
        leilao.propoe(new Lance(u2, 3500.0));

        leilao.propoe(new Lance(u1, 4000.0));
        leilao.propoe(new Lance(u2, 4500.0));

        leilao.propoe(new Lance(u1, 5000.0));
        leilao.propoe(new Lance(u2, 5500.0));

        leilao.propoe(new Lance(u1, 6000.0));
        leilao.propoe(new Lance(u2, 6500.0));

        //Não deve sair na lista
        leilao.propoe(new Lance(u1, 7000.0));

        assertEquals(10, leilao.getLances().size());
        assertEquals(6500.0, leilao.getLances().get(leilao.getLances().size()-1).getValor());

    }

}
