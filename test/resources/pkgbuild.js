name: "name",
desc: "descrição do pacote",
version: "1.2.3",
basePackage: "my-base-package",
arch: "",
url: "",
groups: [
	"package1",
	"package2",
	"package3"
],
depends: [
	"package1>=1.2.3",
	"package2>=1.2.4",
	"package3"
],
optdepends: [
	"libjpeg: JPEG images support",
	"giflib: GIF images support"
],
makedepends: [
	"gcc",
	"otherpackage"
],
conflict:[ 
	"package1>=1.2.3",
	"package2>=1.2.4"
],
replaces: [
	"package1",
	"package2"
],
backup: [
	"dentro_da_instalacao/config.txt",
	"C:/caminho_fixo/config.txt"
],
installScript: "install.rb",
source: [
	"\\\\maquina\\packages\\mypackageFiles",
	"\\\\maquina\\packages\\$name-$version.zip",
	"C:/temp/file.txt"
]
