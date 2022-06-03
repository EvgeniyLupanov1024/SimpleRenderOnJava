all: Display.class
	java Display

Display.class: Display.java
	javac Display.java

clear:
	rm *.class
