import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class DiffieHellman {
	// Constantes da classe 
	private static final BigInteger p = new BigInteger("102031405123416071809152453627382938465749676859789");
	private static final BigInteger g = new BigInteger("1234567890123456789012345");
	
	// Métdos da classe
	public static void main(String[] args) {
		BufferedReader leitor = new BufferedReader(
								new InputStreamReader(System.in));
		
		BigInteger minhaChaveSecreta = null;
		BigInteger minhaChavePublica = null;
		BigInteger chavePublicaDoComunicante = null;
		BigInteger nossaChaveCompartilhada = null;
		
		try {
			System.out.print("Sua chave secreta: ");
			minhaChaveSecreta = new BigInteger(leitor.readLine());
			
			minhaChavePublica = g.modPow(minhaChaveSecreta, p);
			System.out.println("Publique essa chave pública: " +
												minhaChavePublica);
			
			System.out.print("Digite a chave pública do comunicante: ");
			chavePublicaDoComunicante = new BigInteger(leitor.readLine());
			
			nossaChaveCompartilhada = chavePublicaDoComunicante.modPow(minhaChaveSecreta, p);
			System.out.println("Nossa chave compartilhada: " +
										nossaChaveCompartilhada);
			
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}
}