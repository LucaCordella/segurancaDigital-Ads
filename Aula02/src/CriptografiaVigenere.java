import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CriptografiaVigenere {
	public static void main(String[] args) {
		// Declaração de variáveis
		BufferedReader leitor = new BufferedReader(
				                new InputStreamReader(System.in));
		int opcao = 0;
		String mensagem = "";
		String senha = "";
		String cifra = "";
		
		while (true) {
			try {
				// Entrada de dados
				System.out.print("Digite (1) para encriptar, " +
						          		"(2) para decriptar, ou " +
										"(3) para sair: ");
				opcao = Integer.parseInt(leitor.readLine());
				
				if (opcao == 1) {
					System.out.print("Digite a mensagem: ");
					mensagem = leitor.readLine();
				} else if (opcao == 2) {
					System.out.print("Digite o criptograma: ");
					cifra = leitor.readLine();
				} else if (opcao == 3) {
					break;
				}
				
				System.out.print("Digite a senha: ");
				senha = leitor.readLine();
				
				// Processamento
				if (opcao == 1) {
					cifra = encriptar(mensagem, senha);
				} else if (opcao == 2) {
					mensagem = decriptar(cifra, senha);
				}
				
				// Saída de dados
				if (opcao == 1) {
					System.out.println("Cifra: " + cifra);
				} else if (opcao == 2) {
					System.out.println("Mensagem: " + mensagem);
				}
			} catch (Exception erro) {
				System.out.println(erro);
			}
		}
	}
	
	private static String encriptar(String mensagem, String senha) {
		String cifra = "";
		
		for (int i = 0 ; i < mensagem.length() ; i++) {
			int letraMensagem = mensagem.charAt(i);
			int letraSenha = senha.charAt(i % senha.length());
			int letraCifra = (letraMensagem ^ letraSenha);
			
			String temp = Integer.toHexString(0xFF & letraCifra);
			if (temp.length() == 1) {
				temp = "0" + temp;
			}
			
			cifra += temp;
		}
		
		return cifra;
	}
	
	private static String decriptar(String cifra, String senha) {
		String mensagem = "";
		
		for (int i = 0 ; i < cifra.length() ; i+=2) {
			int letraCifra = Integer.parseInt(cifra.substring(i, i + 2), 16);
			int letraSenha = senha.charAt((i / 2) % senha.length());
			int letraMensagem = (letraCifra ^ letraSenha);
			
			mensagem += ((char) letraMensagem);
		}
		
		return mensagem;
	}
}