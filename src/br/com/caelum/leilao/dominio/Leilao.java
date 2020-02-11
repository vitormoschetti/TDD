package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	
	
}
