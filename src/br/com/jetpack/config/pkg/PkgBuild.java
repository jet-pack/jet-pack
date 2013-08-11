package br.com.jetpack.config.pkg;

import br.com.jetpack.config.pkg.reader.PkgInfoReader;
import br.com.jetpack.config.pkg.reader.PropertyNotFoundException;

/**
 * 
 * Class que representa um script de instalação
 * 
 * @author renatol
 * 
 *         Em qualquer propriedade pode ser utilizado as variaveis já existentes
 * 
 * 
 */
public class PkgBuild extends PkgInfo {

	private String[] makeDepends;
	private String installScript;
	private String[] source;
	private String arch;

	/**
	 * Define se é windows ou linux (32 ou 64) Não sei se vai
	 * 
	 * @return
	 */
	public String getArch() {
		return arch;
	}

	/**
	 * Pacotes que são necessários apenas para instalação (não para execução)
	 * 
	 * @return
	 */
	public String[] getMakeDepends() {
		return makeDepends;
	}

	/**
	 * Arquivo .java que possue a classe com o script de instalação
	 * 
	 * @return
	 */
	public String getInstallScript() {
		return installScript;
	}

	/**
	 * Lista de arquivos ou pastas (compartilhamento). Arquivos zip serão
	 * descompactados na raiz. No exemplo ele utiliza as variveis name
	 * (declaradas la em cima,o nome do pacote) e versão(version)
	 * 
	 * @return
	 */
	public String[] getSource() {
		return source;
	}

	@Override
	protected void doLoad(PkgInfoReader reader) throws PropertyNotFoundException {
		makeDepends = reader.optStrArray("makedepends");
		source = reader.optStrArray("source");
		installScript = reader.optStr("installScript");

		if (source.equals("") && installScript.equals("")) {
			throw new PropertyNotFoundException("Deve ser definido ou a propriedade %s ou %s", "installScript", "source");
		}
		arch = reader.optStr("arch");
	}
}
