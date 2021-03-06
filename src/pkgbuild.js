//Em qualquer propriedade pode ser utilizado as variaveis j� existentes


//Nome do pacote. Esse nome ser� utilizado para instal�-lo.
//Deve ser simples e objetivo e minusculo. Colocar sintaxe
name: "name",

//Deve ser pequena e n�o incluir o nome do pacote.
//Deve servir apenas para dar uma id�ia geral.
desc: "descri��o do pacote",

//Vers�o do pacote. Essa pode ser utilizada em uma eventual
//atualiza��o
version: "1.2.3",

//Define qual o pacote � seu pacote base. Isso deve ser utilizado
//principalmente no caso de pacotes que n�o fazem sentido sozinhos.
//Ser� considerado em caso de remo��o do pacote base
basePackage: "my-base-package",

//Define se � windows ou linux (32 ou 64) N�o sei se vai
arch: "",

//url do site oficial da ferramenta
url: "",

//O grupo de pacotes que pertencem a esse pacote
groups: [
	"package1",
	"package2",
	"package3"
],

//Um array com o nome dos pacote que devem ser instalados
//antes desse para funcionar. Se existe uma dependencia
//com uma vers�o minima deve ser especificado >= vers�o
//conforme o exemplo
depends: [
	"package1>=1.2.3",
	"package2>=1.2.4"
],

//Um array com o nome dos pacotes que s�o necess�rios para 
//o funcionamento, porem com caracteristicas adicionais 
optdepends: [
	"libjpeg: JPEG images support",
	"giflib: GIF images support"
],

//Pacotes que s�o necess�rios apenas para instala��o (n�o para execu��o)
makedepends: [
	"gcc",
	"otherpackage"
],

//Um array com o nome dos pacote que podem conflitar
// com os pacotes instalados(dependencias) ou com ele
// mesmo.
//conforme o exemplo(mesmo formato do depends)
conflict:[ 
	"package1>=1.2.3",
	"package2>=1.2.4"
],

//Um array com os pacotes obsoletos que devem ser
//removidos com a instala��o atualiza��o desse pacote
replaces: [
	"package1",
	"package2"
],

//Um array com os arquivos que deve ser feito um backup
//antes de efetuar uma remo��o/instala��o ou atualiza��o 
//do pacote. 
//Esses arquivos podem ser relativos a instala��o ou caminho
//completo.
//(VER COMO VAI SER O USO DISSO
backup: [
	"dentro_da_instalacao/config.txt",
	"C:/caminho_fixo/config.txt"
],

//Arquivo .rb que possue a classe com o script de instala��o
installScript: "install.rb",

//Lista de arquivos ou pastas (compartilhamento). Arquivos zip ser�o descompactados na raiz.
//No exemplo ele utiliza as variveis name (declaradas la em cima,o nome do pacote) e vers�o(version)
source: [
	"\\\\maquina\\packages\\mypackageFiles",
	"\\\\maquina\\packages\\$name-$version.zip",
	"C:/temp/file.txt"
]