package br.com.jaman.config.pkg;

/**
 * Essa classe representa as informações necessárias para fazer a instalação do
 * pacote
 * 
 * @author renatol
 * 
 */
public interface PkgInfo {
	/**
	 * Nome do pacote. Esse nome será utilizado para instalá-lo. Deve ser
	 * simples e objetivo e minusculo. Colocar sintaxe
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Deve ser pequena e não incluir o nome do pacote. Deve servir apenas para
	 * dar uma idéia geral.
	 * 
	 * @return
	 */
	public String getDesc();

	/**
	 * Palavras chaves que serão utilizadas na busca dos pacotes
	 * 
	 * @return
	 */
	public String[] getKeywords();

	/**
	 * Versão do pacote. Essa pode ser utilizada em uma eventual atualização
	 * 
	 * @return
	 */
	public String getVersion();

	/**
	 * Define qual o pacote é seu pacote base. Isso deve ser utilizado
	 * principalmente no caso de pacotes que não fazem sentido sozinhos. Será
	 * considerado em caso de remoção do pacote base
	 * 
	 * @return
	 */
	public String getBasePackage();

	/**
	 * Define se é windows ou linux (32 ou 64) Não sei se vai
	 * 
	 * @return
	 */
	public String getArch();

	/**
	 * url do site oficial da ferramenta
	 * 
	 * @return
	 */
	public String getUrl();

	/**
	 * O grupo de pacotes que pertencem a esse pacote
	 * 
	 * @return
	 */
	public String[] getGroups();

	/**
	 * Um array com o nome dos pacote que devem ser instalados antes desse para
	 * funcionar. Se existe uma dependencia com uma versão minima deve ser
	 * especificado >= versão conforme o exemplo
	 * 
	 * @return
	 */
	public String[] getDepends();

	/**
	 * Um array com o nome dos pacotes que são necessários para o funcionamento,
	 * porem com caracteristicas adicionais
	 * 
	 * @return
	 */
	public String[] getOptDepends();

	/**
	 * Um array com o nome dos pacote que podem conflitar com os pacotes
	 * instalados(dependencias) ou com ele mesmo. conforme o exemplo(mesmo
	 * formato do depends)
	 * 
	 * @return
	 */
	public String[] getConflict();

	/**
	 * Um array com os pacotes obsoletos que devem ser removidos com a
	 * instalação atualização desse pacote
	 * 
	 * @return
	 */
	public String[] getReplaces();

	/**
	 * Um array com os arquivos que deve ser feito um backup antes de efetuar
	 * uma remoção/instalação ou atualização do pacote. Esses arquivos podem ser
	 * relativos a instalação ou caminho completo. (VER COMO VAI SER O USO DISSO
	 * 
	 * @return
	 */
	public String[] getBackup();

	/**
	 * Arquivo .java que possue a classe com o script de instalação
	 * 
	 * @return
	 */
	public String getInstallScript();

}
