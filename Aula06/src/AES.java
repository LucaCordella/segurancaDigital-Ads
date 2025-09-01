import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	private static String encriptar(String texto, String chave ) throws Exception {
		Cipher cifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKey chaveSecreta = new SecretKeySpec(chave.getBytes("UTF-8"), "AES");
		IvParameterSpec iv = new IvParameterSpec("LucaCriptografia".getBytes("UTF-8"));
		cifra.init(Cipher.ENCRYPT_MODE, chaveSecreta, iv);
		
		byte[] textoCifrado = cifra.doFinal(texto.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(textoCifrado);
	}
	
	private static String decriptar(String textoCifrado, String chave ) throws Exception {
		Cipher cifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKey chaveSecreta = new SecretKeySpec(chave.getBytes("UTF-8"), "AES");
		IvParameterSpec iv = new IvParameterSpec("LucaCriptografia".getBytes("UTF-8"));
		cifra.init(Cipher.DECRYPT_MODE, chaveSecreta, iv);
		
		byte[] texto = cifra.doFinal(Base64.getDecoder().decode(textoCifrado));
		return new String(texto, "UTF-8");
	}
	
	public static void main(String[] args) {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		String texto = "";
		String chave = "";
		String criptograma = "";
		
		try {
			System.out.print("Digite o texto: ");
			texto = leitor.readLine();
			
			System.out.print("Digite a chave: ");
			chave = leitor.readLine();
			
			criptograma = encriptar(texto, chave);
			System.out.println(criptograma);
			System.out.println(decriptar(criptograma, chave));
			
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}
}