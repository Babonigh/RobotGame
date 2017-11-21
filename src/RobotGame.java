import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RobotGame extends Scene {

	public static Group root = new Group();
	public static int squareSize = 50;

	private int level = 1;
	private Robot[] robots;

	public RobotGame() {
		super(root, main.WORLD_WIDTH, main.WORLD_HEIGHT);
		generateLevel(level);
		Robot r1 = new Robot(squareSize);
		Robot r2 = new Robot(Color.BLUE,squareSize);
		Robot r3 = new Robot(Color.GREEN,squareSize);
		
		r1.setTranslateX(100);
		r2.setTranslateX(400);
		r3.setTranslateX(300);
		
		Group robots = new Group(r1,r2,r3);
		robots.setTranslateX(200);
		robots.setTranslateY(200);
		root.getChildren().addAll(robots);
		Wall w = new Wall();
		
		w.setTranslateX(400);
		w.setTranslateY(200);
		
		root.getChildren().add(w);
	}

	private void generateLevel1() {

		robots = new Robot[1];
		
		generateMap(Maps.map1);

	}

	private void generateMap(char[][] map) {

		squareSize = 50*2;
		
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
