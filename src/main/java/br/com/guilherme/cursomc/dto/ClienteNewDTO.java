package br.com.guilherme.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.guilherme.cursomc.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "O campo é obrigatório")
	@Length(min = 5, max = 120, message = "O nome deve ser entre 5 e 120 caracteres")
	private String nome;
		
	@NotEmpty(message = "O campo é obrigatório")
	@Email(message = "Email inválido")
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
	@NotEmpty(message = "O campo é obrigatório")
	private String logradouro;
	@NotEmpty(message = "O campo é obrigatório")
	private String numero;
	private String complemento;
	private String bairro;
	@NotEmpty(message = "O campo é obrigatório")
	private String cep;
	@NotEmpty(message = "O campo é obrigatório")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;
	
	public ClienteNewDTO() {		
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}
	
	
}

