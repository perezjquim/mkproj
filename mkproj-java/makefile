FILES := $(shell find . -name "*.java")

main: compile exec
compile:
	@echo "Compiling.."
	@javac -d . -sourcepath src -cp $(FILES)
	@jar -cvfm mkproj.jar manifest.txt mkproj* > /dev/null
exec:
	@echo "Executing.."
	@java -jar mkproj.jar
