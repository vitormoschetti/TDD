package br.com.caelum.tests;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    private Usuario jose;
    private Usuario joao;
    private Usuario maria;
    private Leilao leilao;

    @Before
    public void setUp(){
        jose = new Usuario("josé");
        joao = new Usuario("joão");
        maria = new Usuario("maria");
        leilao = new Leilao("Playstation 3 novo em folha");
    }

    @Test
    public void deveReceberApenasUmLance() {

        leilao.propoe(new Lance(new Usuario("Steve"), 2000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 1);

    }

    @Test
    public void deveReceberVariosLances() {

        leilao.propoe(new Lance(new Usuario("Steve"), 2000.0));
        leilao.propoe(new Lance(new Usuario("Mark"), 2300.0));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 1);

    }

    //Um usuario não pode fazer dois lances seguidos
    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {

        leilao.propoe(new Lance(jose, 2000.0));
        leilao.propoe(new Lance(jose, 4000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.1);

    }

    //Um usuario só pode fazer no máximo 5 lances
    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario(){

        leilao.propoe(new Lance(jose, 2000.0));
        leilao.propoe(new Lance(joao, 2500.0));

        leilao.propoe(new Lance(jose, 3000.0));
        leilao.propoe(new Lance(joao, 3500.0));

        leilao.propoe(new Lance(jose, 4000.0));
        leilao.propoe(new Lance(joao, 4500.0));

        leilao.propoe(new Lance(jose, 5000.0));
        leilao.propoe(new Lance(joao, 5500.0));

        leilao.propoe(new Lance(jose, 6000.0));
        leilao.propoe(new Lance(joao, 6500.0));

        //Não deve sair na lista
        leilao.propoe(new Lance(jose, 7000.0));

        assertEquals(10, leilao.getLances().size());
        assertEquals(6500.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(),1);

    }

    @Test
    public void deveDobrarOLance(){
        leilao.dobraLance(joao);
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(joao, 500.0));
        leilao.propoe(new Lance(maria, 500.0));
        leilao.propoe(new Lance(joao, 10000.0));
        leilao.propoe(new Lance(maria, 1500.0));

        leilao.dobraLance(joao);
        leilao.dobraLance(jose);

        assertEquals(5, leilao.getLances().size());
        assertEquals(20000.0, leilao.getLances().stream().mapToDouble(Lance::getValor).max().getAsDouble(), 0.1);

        leilao.dobraLance(joao);

        assertEquals(5, leilao.getLances().size());
        assertEquals(20000.0, leilao.getLances().stream().mapToDouble(Lance::getValor).max().getAsDouble(), 1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExceptionComValorNulo(){
        leilao.propoe(new Lance(joao, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExceptionComValorNegativo(){
        leilao.propoe(new Lance(joao, -1));
    }

}
