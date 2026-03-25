
public class Produto {
	public String nome;
	public String codigo;
	public String getNome() {
		return nome;
	}
	public Produto(String nome, String codigo, String descricao, double preco, int qnt) {
		super();
		this.nome = nome;
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.qnt = qnt;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQnt() {
		return qnt;
	}
	public void setQnt(int qnt) {
		this.qnt = qnt;
	}
	public String descricao;
	public double preco;
	public int qnt;
	
	 @Override
	    public String toString() {
	        return nome + ", " + codigo + ", " + descricao + ", " + preco + ", " + qnt;
	    }
}
