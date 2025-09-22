import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RSA {
	private static KeyPair gerarParDeSenhas() throws Exception {
		KeyPairGenerator objGerador = KeyPairGenerator.getInstance("RSA");
		objGerador.initialize(2048);
		return objGerador.generateKeyPair();
	}
	
	private static String encriptarRSA(byte[] chaveSimetrica, KeyPair objChaves) throws Exception {
		Cipher objCifra = Cipher.getInstance("RSA");
		objCifra.init(Cipher.ENCRYPT_MODE, objChaves.getPublic());
		byte[] temp = objCifra.doFinal(chaveSimetrica);
		return Base64.getEncoder().encodeToString(temp);
	}
	
	private static byte[] decriptarRSA(String criptograma, KeyPair objChaves) throws Exception {
		Cipher objCifra = Cipher.getInstance("RSA");
		objCifra.init(Cipher.DECRYPT_MODE, objChaves.getPrivate());
		byte[] temp = objCifra.doFinal(Base64.getDecoder().decode(criptograma));
		return temp;
	}
	
	private static String encriptarAES(String texto, byte[] chaveSimetrica) throws Exception {
		Cipher cifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKey chaveSecreta = new SecretKeySpec(chaveSimetrica, "AES");
		IvParameterSpec iv = new IvParameterSpec("LucaCriptografia".getBytes("UTF-8"));
		cifra.init(Cipher.ENCRYPT_MODE, chaveSecreta, iv);
		byte[] textoCifrado = cifra.doFinal(texto.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(textoCifrado);
	}
	
	private static String decriptarAES(String textoCifrado, byte[] chaveSimetrica) throws Exception {
		Cipher cifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKey chaveSecreta = new SecretKeySpec(chaveSimetrica, "AES");
		IvParameterSpec iv = new IvParameterSpec("LucaCriptografia".getBytes("UTF-8"));
		cifra.init(Cipher.DECRYPT_MODE, chaveSecreta, iv);
		byte[] texto = cifra.doFinal(Base64.getDecoder().decode(textoCifrado));
		return new String(texto, "UTF-8");
	}
	
	private static byte[] calcularHash(byte[] senha) throws Exception {
		MessageDigest objHash = MessageDigest.getInstance("SHA-256");
		return objHash.digest(senha);
	}
	
	public static void main(String[] args) {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		try {
			KeyPair parDeChaves = gerarParDeSenhas();
			
			System.out.print("Digite o texto: ");
			String texto = leitor.readLine();
			
			byte[] senha = new byte[100];
			for (int i = 0 ; i < 100 ; i++) {
				senha[i] = ((byte) (256 * Math.random()));
			}
			
			System.out.println(encriptarRSA(senha, parDeChaves));
			byte[] resumo = calcularHash(senha);
			System.out.println(encriptarAES(texto, resumo));
			
			System.out.print("Digite o criptograma da senha: ");
			String criptogramaSenha = leitor.readLine();
			senha = decriptarRSA(criptogramaSenha, parDeChaves);
			
			resumo = calcularHash(senha);
			
			System.out.print("Digite o criptograma do texto: ");
			String criptogramaTexto = leitor.readLine();
			System.out.println(decriptarAES(criptogramaTexto, resumo));
			
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}
}