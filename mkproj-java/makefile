FILES := $(shell find . -name "*.java")

main: compile exec
compile:
	@echo "Compilando.."
	@javac -d . -sourcepath src -cp class/$(FILES)
	@jar -cvfm mkproj.jar manifest.txt mkproj* > /dev/null
exec:
	@echo "Executando.."
	@java -jar mkproj.jar
