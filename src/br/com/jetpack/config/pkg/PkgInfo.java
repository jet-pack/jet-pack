package br.com.jetpack.config.pkg;

import br.com.jetpack.config.pkg.reader.PkgInfoReader;
import br.com.jetpack.config.pkg.reader.PropertyNotFoundException;
import br.com.jetpack.packages.system.PkgAndVersion;

/**
 * Essa classe representa as informações do comum entre o script de instalacao e
 * o script do pacote ja instalado
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
	protected PkgAndVersion[] dependsWithVersion;
	protected String[] depends = new String[] {};
	protected PkgAndVersion[] optDepends;
	protected PkgAndVersion[] conflict;
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
	 * Deve ser pequena e não incluir o nome do pacote. Deve servir apenas para
	 * dar uma idéia geral.
	 * 
	 * @return
	 */
	public String getDesc() {
		return desc;
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
	public PkgAndVersion[] getDependsWithVersion() {
		return dependsWithVersion;
	}

	public String[] getDepends() {
		return depends;
	}

	private String[] fillDependsString() {
		String[] ret;
		ret = new String[dependsWithVersion.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = dependsWithVersion[i].getName();
		}
		return ret;
	}

	/**
	 * Um array com o nome dos pacotes que são necessários para o funcionamento,
	 * porem com caracteristicas adicionais
	 * 
	 * @return
	 */
	public PkgAndVersion[] getOptDepends() {
		return optDepends;
	}

	/**
	 * lUm array com o nome dos pacote que podem conflitar com os pacotes
	 * instalados(dependencias) ou com ele mesmo. conforme o exemplo(mesmo
	 * formato do depends)
	 * 
	 * @return
	 */
	public PkgAndVersion[] getConflict() {
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

	public final void load(PkgInfoReader reader) throws PropertyNotFoundException {
		name = reader.getStr("name");
		version = reader.getStr("version");
		desc = reader.optStr("desc");
		basePackage = reader.optStr("basePackage");
		url = reader.optStr("url");
		groups = reader.optStrArray("groups");
		dependsWithVersion = reader.optPkgAndVersions("depends");
		fillDependsString();
		optDepends = reader.optPkgAndVersions("optdepends");
		conflict = reader.optPkgAndVersions("conflict");
		replaces = reader.optStrArray("replaces");
		backup = reader.optStrArray("backup");
		doLoad(reader);
	}

	protected void doLoad(PkgInfoReader reader) throws PropertyNotFoundException {
		// here nothing...
	}
}
