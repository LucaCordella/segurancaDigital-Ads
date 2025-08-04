import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CriptografiaRot13 {
	private static String encriptar(String texto) {
		String cifra = "";
		
		for (int i = 0 ; i < texto.length() ; i++) {
			int letraOriginal = ((int) texto.charAt(i));
			int letraCifrada = (letraOriginal + 13);
			
			if (letraCifrada > 122) {
				letraCifrada = (letraCifrada  - 26);
			}
			
			cifra += ((char) letraCifrada);
		}
		
		return cifra;
	}
	
	public static void main(String[] args) {
		// Declaração de variáveis
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		String texto = "";
		String cifra = "";
		
		// Entrada de dados
		try {
			System.out.print("Digite um texto: ");
			texto = leitor.readLine();
		} catch (Exception erro) {}
		
		// Processamento
		cifra = encriptar(texto);
		System.out.println("Cifra: " + cifra);
		System.out.println("Texto original: " + encriptar(cifra));
	}
}