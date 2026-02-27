import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Cadastrar {

	public static void main(String[] args) {
		Scanner Scan = new Scanner(System.in);
		int inicia = 0;
		String arquivo = "produtos.txt";
		System.out.println("Sistema de cadastro de produto");
		Produto p1 = null;
		while (inicia == 0){
			System.out.println("Digite o nome do produto:");
			String nome = Scan.nextLine();
			System.out.println("Digite a codigo do produto:");
			String codigo = Scan.next();
			Scan.nextLine();
			System.out.println("Digite o descricao do produto:");
			String descricao = Scan.nextLine();
			System.out.println("Digite o preco do produto:");
			double preco = Scan.nextDouble();
			System.out.println("Digite o quantidade disponivel:");
			int qnt = Scan.nextInt();
			 p1 = new Produto(nome, codigo, descricao, preco, qnt);
			System.out.println("Digite 0 para cadastrar novo usuario ou 1 para finalizar:");
			inicia = Scan.nextInt();
			Scan.nextLine();
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
				bw.write(p1.toString());
				bw.newLine();
				System.out.println("Novo produto cadastrado com sucesso: "+ p1.toString());
			} catch (IOException e) {
				System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
			}
        }

	}

}
