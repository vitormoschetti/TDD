package br.com.caelum.leilao.dominio;

public class Lance {

	private Usuario usuario;
	private double valor;
	
	public Lance(Usuario usuario, double valor) {
		if(valor <= 0) throw new IllegalArgumentException("O valor passado não é válido");
		this.usuario = usuario;
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public double getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "Lance{" +
				"usuario=" + usuario.getNome() +
				", valor=" + valor +
				'}';
	}
}
