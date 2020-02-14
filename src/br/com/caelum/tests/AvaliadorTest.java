package br.com.caelum.tests;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Assert;
import org.junit.Test;

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

		leilao.propoe(new Lance(jose, MENORLANCE));
		leilao.propoe(new Lance(joao, 350.0));
		leilao.propoe(new Lance(maria, MAIORLANCE));

		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);

		System.out.println(leiloeiro.getMaioresLances());

		Assert.assertEquals(MAIORLANCE, leiloeiro.getMaiorLance(), 00000.1);
		Assert.assertEquals(MENORLANCE, leiloeiro.getMenorLance(), 0.1);
		Assert.assertEquals(MEDIALANCES, leiloeiro.getMediaLances(), 0.1);
	}

	@Test
	public void testaMediaDeZeroLance() {

		Usuario vitor = new Usuario("vitor");
		Leilao leilao = new Leilao("Playstation 32");

		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);

		Assert.assertEquals(0, leiloeiro.getMediaLances(), 0.1);

	}

	@Test
	public void testaApenasUmLance() {

		Usuario vitor = new Usuario("vitor");
		Leilao leilao = new Leilao("Playstation 32");

		leilao.propoe(new Lance(vitor, 1000.0));

		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);

		Assert.assertEquals(1000.0, leiloeiro.getMediaLances(), 0.1);
		Assert.assertEquals(1000.0, leiloeiro.getMenorLance(), 1);
		Assert.assertEquals(1000.0, leiloeiro.getMediaLances(), 1);

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
		leilao.propoe(new Lance(joao, 350.0));
		leilao.propoe(new Lance(maria, MAIORLANCE));
//        leilao.propoe(new Lance(jose, 150.0));
//        leilao.propoe(new Lance(jose, 100.0));

		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);

		Assert.assertEquals(Arrays.asList(400.0, 350.0), leiloeiro.getMaioresLances());
	}

    @Test(expected  = RuntimeException.class)
    public void deveLancarExceptionPorNaoTerLances(){

    }

}
