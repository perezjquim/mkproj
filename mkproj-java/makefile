FILES := $(shell find . -name "*.java")

main: compile exec
compile: *.java
	@echo "Compilando.."
	@javac -cp . $(FILES)
	@jar -cvfm mkproj.jar manifest.txt mkproj* > /dev/null
exec:
	@echo "Executando.."
	@java -jar mkproj.jar
