package br.com.jetpack.config.pkg;

/**
 * Essa classe representa as informações do comum entre o script de instalacao
 * e o script do pacote ja instalado
 * 
 * @author renatol
 * 
 */
public abstract class PkgInfo {

	protected String name;
	protected String desc;
	protected String version;
	protected String basePackage;
	protected String url;
	protected String[] groups;
	protected String[] depends;
	protected String[] optDepends;
	protected String[] conflict;
	protected String[] replaces;
	protected String[] backup;

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
	 * Deve ser pequena e não incluir o nome do pacote. Deve servir apenas
	 * para
	 * dar uma idéia geral.
	 * 
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Versão do pacote. Essa pode ser utilizada em uma eventual
	 * atualização
	 * 
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Define qual o pacote é seu pacote base. Isso deve ser utilizado
	 * principalmente no caso de pacotes que não fazem sentido sozinhos.
	 * Será
	 * considerado em caso de remoção do pacote base
	 * 
	 * @return
	 */
	public String getBasePackage() {
		return basePackage;
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
	 * Um array com o nome dos pacote que devem ser instalados antes desse
	 * para
	 * funcionar. Se existe uma dependencia com uma versão minima deve ser
	 * especificado >= versão conforme o exemplo
	 * 
	 * @return
	 */
	public String[] getDepends() {
		return depends;
	}

	/**
	 * Um array com o nome dos pacotes que são necessários para o
	 * funcionamento,
	 * porem com caracteristicas adicionais
	 * 
	 * @return
	 */
	public String[] getOptDepends() {
		return optDepends;
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
	 * Um array com os arquivos que deve ser feito um backup antes de
	 * efetuar
	 * uma remoção/instalação ou atualização do pacote. Esses arquivos
	 * podem ser
	 * relativos a instalação ou caminho completo. (VER COMO VAI SER O USO
	 * DISSO
	 * 
	 * @return
	 */
	public String[] getBackup() {
		return backup;
	}

}
