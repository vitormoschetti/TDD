package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		if(podeAdicionarNaLista(lance))
			this.lances.add(lance);
	}

	private boolean podeAdicionarNaLista(Lance lance) {
		return lances.isEmpty() || (usuarioAtualNaoDeuUltimoLance(lance) && usuarioNaoDeuMaisQue5Lances(lance));
	}

	private boolean usuarioNaoDeuMaisQue5Lances(Lance lance) {
		int total = 0;
		for(Lance l: lances) {
			if(l.getUsuario().equals(lance.getUsuario())) total++;
		}
		return total < 5;
	}

	private boolean usuarioAtualNaoDeuUltimoLance(Lance lance) {
		return !lances.get(lances.size()-1).getUsuario().equals(lance.getUsuario());
	}


	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}


	public void dobraLance(Usuario usuario) {

		List<Lance> lancesUsuario = todosLancesDoUsuario(usuario);

		if(lancesUsuario.size() > 0){
			Lance ultimoLanceUsuario = lancesUsuario.get(lancesUsuario.size() - 1);
			propoe(new Lance(ultimoLanceUsuario.getUsuario(), ultimoLanceUsuario.getValor()*2));
		}

	}

	private List<Lance> todosLancesDoUsuario(Usuario usuario) {
		return lances.stream().filter(u -> u.getUsuario().equals(usuario)).collect(Collectors.toList());
	}
}
