import java.security.MessageDigest;

public class SHA256 {
	public static byte[] calcularByte(String texto) throws Exception {
		MessageDigest objHash = MessageDigest.getInstance("SHA-256");
		return objHash.digest(texto.getBytes("UTF-8"));
	}
	
	public static String calcularString(String texto) throws Exception {
		String retorno = "";
		byte[] resumo = calcularByte(texto);
		
		for (int i = 0 ; i < resumo.length ; i++) {
			String letra = Integer.toHexString(0xFF & resumo[i]);
			if (letra.length() == 1) letra = "0" + letra;
			retorno += letra;
		}
		return retorno;
	}
}