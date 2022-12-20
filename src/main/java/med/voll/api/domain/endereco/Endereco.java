package med.voll.api.domain.endereco;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
		
	
	private String logradouro;
	
	private String bairro;
	
	private String cep;
	
	private String numero;
	
	private String complemento;
	
	private String cidade;
	
	private String uf;
	

	public Endereco() {}
	public Endereco(DadosEndereco endereco) {
		this.logradouro = endereco.logradouro();
		this.bairro = endereco.bairro();
		this.cep = endereco.cep();
		this.numero = endereco.numero();
		this.complemento = endereco.complemento();
		this.cidade = endereco.cidade();
		this.uf = endereco.uf();
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bairro, cep, cidade, complemento, logradouro, numero, uf);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(cidade, other.cidade) && Objects.equals(complemento, other.complemento)
				&& Objects.equals(logradouro, other.logradouro) && Objects.equals(numero, other.numero)
				&& Objects.equals(uf, other.uf);
	}
	
	public void atualizarInformacoes(DadosEndereco dados) {
		if(dados.logradouro() != null) {
			this.logradouro = dados.logradouro();
		}
		
		if(dados.bairro() != null) {
			this.bairro = dados.bairro();
		}
		
		if(dados.cep() != null) {
			this.cep = dados.cep();
		}
		
		if(dados.uf() != null) {
			this.uf = dados.uf();
		}
		
		if(dados.cidade() != null) {
			this.cidade = dados.cidade();
		}
		
		if(dados.numero() != null) {
			this.numero = dados.numero();
		}
		
		if(dados.complemento() != null) {
			this.complemento = dados.complemento();
		}
		
	}
	
	
	
}