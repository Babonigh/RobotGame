import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RobotGame extends Scene {

	private static Group root = new Group();
	public static int squareSize = 50;

	private int level = 1;
	private Robot[] robots;

	public RobotGame() {
		super(root, main.WORLD_WIDTH, main.WORLD_HEIGHT);
		generateLevel(level);
		Robot r1 = new Robot(squareSize);
		Robot r2 = new Robot(Color.BLUE,squareSize);
		Robot r3 = new Robot(Color.GREEN,squareSize);
		root.getChildren().addAll(r1,r2,r3);
		
		Group g = new Group();
		root.getChildren().add(g);
		g.setTranslateX(100);
		
		Group g2 = new Group();
		g.getChildren().add(g2);
		g2.setTranslateY(100);
		
		Group g3 = new Group();
		g2.getChildren().add(g3);
		g3.setTranslateX(-50);
		g3.setTranslateY(100);
		
		Rectangle r = new Rectangle(0,0,-1,-1);
		g3.getChildren().add(r);
		r1.checkCollision(root);
		r1.checkCollision(g);
		r1.checkCollision(g2);
		r1.checkCollision(g3);
		r1.checkCollision(r);
		root.getChildren().add(new Circle(200,200,10));
	}

	private void generateLevel1() {

		robots = new Robot[1];
		
		generateMap(Maps.map1);

	}

	private void generateMap(char[][] map) {

		squareSize = 50;
		
		for (int r = 0; r < map.length; r++) {

			char[] tiles = map[r];

			for (int c = 0; c < tiles.length; c++) {

				Node t = generateTile(tiles[c]);
				t.setTranslateX(c*squareSize);
				t.setTranslateY(r*squareSize);
				root.getChildren().add(t);
			}

		}

	}

	private Node generateTile(char type) {

		switch (type) {
		case '#':
			return new Wall();
		default:
			return new Rectangle();
		}
	}

	private void generateLevel(int level) {

		root.getChildren().clear();

		switch (level) {
		case 1:
			generateLevel1();
			return;

		}

	}

}
