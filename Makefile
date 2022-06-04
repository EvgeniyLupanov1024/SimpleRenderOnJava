all: Display.class Point3D.class Polygon3D.class PointDisplayTranslater.class MagicCube.class Object.class
	java Display

Display.class: Display.java
	javac Display.java

# рендер
Point3D.class: Point3D.java
	javac Point3D.java

Polygon3D.class: Polygon3D.java
	javac Polygon3D.java

PointDisplayTranslater.class: PointDisplayTranslater.java
	javac PointDisplayTranslater.java

# объекты
Object.class: Object.java
	javac Object.java

MagicCube.class: MagicCube.java
	javac MagicCube.java


clear:
	rm *.class
