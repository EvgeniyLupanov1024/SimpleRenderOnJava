all: Display Point3D Polygon3D PointDisplayTranslater MagicCube
	java Display

Display: Display.java
	javac Display.java

Point3D: Point3D.java
	javac Point3D.java

Polygon3D: Polygon3D.java
	javac Polygon3D.java

PointDisplayTranslater: PointDisplayTranslater.java
	javac PointDisplayTranslater.java

MagicCube: MagicCube.java
	javac MagicCube.java

clear:
	rm *.class
