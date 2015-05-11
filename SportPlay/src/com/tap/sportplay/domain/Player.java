package com.tap.sportplay.domain;


public class Player extends BaseAuditEntity {

	private String name;
	private Position position;
	private Avaliacao avaliacao;
	private boolean mensalista;
	
	public Player(){
		this.name = name;
		setAvaliacao(new Avaliacao());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public boolean isMensalista() {
		return mensalista;
	}

	public void setMensalista(boolean mensalista) {
		this.mensalista = mensalista;
	}
	
}
