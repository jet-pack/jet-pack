package br.com.jetpack.config.pkg.reader;

public class PropertyNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PropertyNotFoundException(String key) {
		super(String.format("A chave %s nao foi encontrada.", key));
	}

	public PropertyNotFoundException(String msg, Object... args) {
		super(String.format(msg, args));
	}

}
