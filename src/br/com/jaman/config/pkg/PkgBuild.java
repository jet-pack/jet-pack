package br.com.jaman.config.pkg;

import br.com.jaman.packages.installer.InstallerScript;

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
public class PkgBuild implements PkgInfo {

	private String name;
	private String desc;
	private String[] keywords;
	private String version;
	private String basePackage;
	private String arch;
	private String url;
	private String[] groups;
	private String[] depends;
	private String[] optDepends;
	private String[] makeDepends;
	private String[] conflict;
	private String[] replaces;
	private String[] backup;
	private String installScript;
	private String[] jarsInstall;
	private String[] source;

	/**
	 * Nome do pacote. Esse nome será utilizado para instalá-lo. Deve ser
	 * simples e objetivo e minusculo. Colocar sintaxe
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Deve ser pequena e não incluir o nome do pacote. Deve servir apenas para
	 * dar uma idéia geral.
	 * 
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Palavras chaves que serão utilizadas na busca dos pacotes
	 * 
	 * @return
	 */
	public String[] getKeywords() {
		return keywords;
	}

	/**
	 * Versão do pacote. Essa pode ser utilizada em uma eventual atualização
	 * 
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Define qual o pacote é seu pacote base. Isso deve ser utilizado
	 * principalmente no caso de pacotes que não fazem sentido sozinhos. Será
	 * considerado em caso de remoção do pacote base
	 * 
	 * @return
	 */
	public String getBasePackage() {
		return basePackage;
	}

	/**
	 * Define se é windows ou linux (32 ou 64) Não sei se vai
	 * 
	 * @return
	 */
	public String getArch() {
		return arch;
	}

	/**
	 * url do site oficial da ferramenta
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * O grupo de pacotes que pertencem a esse pacote
	 * 
	 * @return
	 */
	public String[] getGroups() {
		return groups;
	}

	/**
	 * Um array com o nome dos pacote que devem ser instalados antes desse para
	 * funcionar. Se existe uma dependencia com uma versão minima deve ser
	 * especificado >= versão conforme o exemplo
	 * 
	 * @return
	 */
	public String[] getDepends() {
		return depends;
	}

	/**
	 * Um array com o nome dos pacotes que são necessários para o funcionamento,
	 * porem com caracteristicas adicionais
	 * 
	 * @return
	 */
	public String[] getOptDepends() {
		return optDepends;
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
	 * Um array com o nome dos pacote que podem conflitar com os pacotes
	 * instalados(dependencias) ou com ele mesmo. conforme o exemplo(mesmo
	 * formato do depends)
	 * 
	 * @return
	 */
	public String[] getConflict() {
		return conflict;
	}

	/**
	 * Um array com os pacotes obsoletos que devem ser removidos com a
	 * instalação atualização desse pacote
	 * 
	 * @return
	 */
	public String[] getReplaces() {
		return replaces;
	}

	/**
	 * Um array com os arquivos que deve ser feito um backup antes de efetuar
	 * uma remoção/instalação ou atualização do pacote. Esses arquivos podem ser
	 * relativos a instalação ou caminho completo. (VER COMO VAI SER O USO DISSO
	 * 
	 * @return
	 */
	public String[] getBackup() {
		return backup;
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
	 * Jars que são dependencias para execução da instalação
	 * 
	 * @return
	 */
	public String[] getJarsInstall() {
		return jarsInstall;
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

}
