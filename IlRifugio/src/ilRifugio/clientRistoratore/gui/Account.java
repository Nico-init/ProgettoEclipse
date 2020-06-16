package ilRifugio.clientRistoratore.gui;

public class Account {
	
	private String nome;
	private String username;
	private RuoloAccount ruolo;
	
	public Account(String n, String u, RuoloAccount r) {
		nome = n;
		username = u;
		ruolo = r;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public RuoloAccount getRuolo() {
		return ruolo;
	}
	public void setRuolo(RuoloAccount ruolo) {
		this.ruolo = ruolo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}	

}
