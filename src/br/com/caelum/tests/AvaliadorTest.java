package br.com.caelum.tests;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;

public class AvaliadorTest {

	private static final Double MAIORLANCE = 400.0;
	private static final Double MENORLANCE = 150.0;
	private static final Double MEDIALANCES = 300.0;
	private Usuario jose;
	private Usuario joao;
	private Usuario maria;
	private Leilao leilao;
	private Avaliador leiloeiro;

	@Before
	public void setUp(){
		jose = new Usuario("josé");
		joao = new Usuario("joão");
		maria = new Usuario("maria");
		leilao = new Leilao("Playstation 3 novo em folha");
		leiloeiro = new Avaliador();
	}

	@Test
	public void deveEntenderLancesEmOrdermCrescente() {
		leilao.propoe(new Lance(jose, MENORLANCE));
		leilao.propoe(new Lance(joao, 350.0));
		leilao.propoe(new Lance(maria, MAIORLANCE));

		leiloeiro.avalia(leilao);

		Assert.assertEquals(MAIORLANCE, leiloeiro.getMaiorLance(), 00000.1);
		Assert.assertEquals(MENORLANCE, leiloeiro.getMenorLance(), 0.1);
		Assert.assertEquals(MEDIALANCES, leiloeiro.getMediaLances(), 0.1);
	}

	@Test(expected = RuntimeException.class)
	public void testaMediaDeZeroLance() {

		Leilao leilao = new Leilao("Playstation 32");

		leiloeiro.avalia(leilao);
	}

	@Test
	public void testaApenasUmLance() {

		leilao.propoe(new Lance(joao, 1000.0));

		leiloeiro.avalia(leilao);

		Assert.assertEquals(1000.0, leiloeiro.getMediaLances(), 0.1);
		Assert.assertEquals(1000.0, leiloeiro.getMenorLance(), 1);
		Assert.assertEquals(1000.0, leiloeiro.getMediaLances(), 1);

	}

	@Test
	public void testaPegaTresMaioresLances() {
		leilao.propoe(new Lance(joao, 350.0));
		leilao.propoe(new Lance(maria, MAIORLANCE));

		leiloeiro.avalia(leilao);

		Assert.assertEquals(Arrays.asList(400.0, 350.0), leiloeiro.getMaioresLances());
	}

}
