package be.ccfun.day18;

public class PolygonTest {

	public static void main(String[] args) {
		Polygon polygon = new Polygon();
		polygon.addVertex(new Position(3,1)); // (1,3)
		polygon.addVertex(new Position(2,4)); // (4,2)
		polygon.addVertex(new Position(4,6)); // (6,4)
		polygon.addVertex(new Position(0,5)); // (5,0)
		polygon.addVertex(new Position(1,2)); // (2,1)
		System.out.println(polygon.perimeter());
		System.out.println(polygon.area());
		System.out.println(polygon.pickStheorem());
	}

}
