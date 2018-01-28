main: compile exec
compile: *.java
	@echo "Compilando.."
	@javac -d . *.java
	@jar -cvfm mkproj.jar manifest.txt mkproj* > /dev/null
exec:
	@echo "Executando.."
	@java -jar mkproj.jar
