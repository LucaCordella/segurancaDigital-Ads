import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class LinearFeedbackShiftRegister {
	private static void inicializarRegistrador(int[] registrador) {
		BufferedReader leitor = new BufferedReader(
								new InputStreamReader(System.in));
		try {
			System.out.print("Digite uma chave de 4 letras: ");
			String chave = leitor.readLine();
			for (int i = 0 ; i < 4 ; i++) {
				int letra = chave.charAt(i); 
				String binario = Integer.toBinaryString(letra);
				int quantidadeDeZeros = (8 - binario.length());
				for (int j = 0 ; j < quantidadeDeZeros ; j++) {
					binario = ("0" + binario);
				}
				for (int j = 0 ; j < 8 ; j++) {
					registrador[(i * 8) + j] = Integer.parseInt( /**/
									binario.substring(j, j + 1));
				}
			}
		} catch (Exception erro) {}
	}
	
	private static int rotacionar(int[] registrador, int tipo) {
		int retorno = registrador[0];
		int xor = 0;
		
		if (tipo == 0) {
			xor = registrador[31] ^ registrador[6] ^
			      registrador[4] ^ registrador[2] ^
				  registrador[1] ^ registrador[0];
		} else {
			xor = registrador[31] ^ registrador[6] ^
				  registrador[5] ^ registrador[1];
		}
		
		for (int i = 0 ; i < 31 ; i++) {
			registrador[i] = registrador[i + 1];
		}
		registrador[31] = xor;
		
		return retorno;
	}
	
	public static void main(String[] args) {
		try {
			int[] cabeca = new int[32];
			int[] gerador0 = new int[32];
			int[] gerador1 = new int[32];
			FileWriter escritor = new FileWriter("lfsr.txt");
			
			inicializarRegistrador(cabeca);
			inicializarRegistrador(gerador0);
			inicializarRegistrador(gerador1);
		
			int operando0 = 0;
			int operando1 = 0;
			int xor = 0;
			String acumulador = "";
			
			for (int i = 1 ; i < 838860900 ; i++) {
				if (rotacionar(cabeca, 0) == 0) {
					operando0 = rotacionar(gerador0, 0);
					operando1 = gerador1[0];
				} else {
					operando0 = gerador0[0];
					operando1 = rotacionar(gerador1, 1);
				}
			
				xor = (operando0 ^ operando1);
				acumulador += ("" + xor);
				if (i % 8 == 0) { /**/
					escritor.write((char) 
								Integer.parseInt(acumulador, 2));
					acumulador = "";
				}
			}
			escritor.close();
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}
}