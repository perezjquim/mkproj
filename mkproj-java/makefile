FILES := $(shell find . -name "*.java")

main: compile exec
compile:
	@echo "Compiling.."
	@javac -d bin $(FILES)
	@jar -cvmf manifest.txt mkproj.jar -C bin . > /dev/null
exec:
	@echo "Executing.."
	@java -jar mkproj.jar
