import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RobotGame extends Scene {

	private static Group root = new Group();
	public static double SQUARE_SIZE;
	
	private final double WORLD_WIDTH;
	private final double WORLD_HEIGHT;
	
	public static int level = 1;
	private ArrayList<Robot> robots = new ArrayList<Robot>();

	public RobotGame(double width, double height) {
		
		super(root, width, height);
		this.WORLD_WIDTH = width;
		this.WORLD_HEIGHT = height;

		generateLevel(level);
		
	}

	private void generateLevel1() {

		Map level = new Map(Maps.map1);
		
		generateMap(level);

	}

	private void generateMap(Map level) {

		double squareLength = WORLD_WIDTH/level.getLength();
		double squareHeight = WORLD_HEIGHT/level.getHeight();
			
		SQUARE_SIZE = Math.min(squareHeight,squareLength);

		char[][] map = level.getMapArray();
		
		for (int r = 0; r < map.length; r++) {

			char[] tiles = map[r];

			for (int c = 0; c < tiles.length; c++) {

				Node t = generateTile(tiles[c]);
				t.setTranslateX(t.getTranslateX() + c * SQUARE_SIZE);
				t.setTranslateY(t.getTranslateY() + r * SQUARE_SIZE);
				root.getChildren().add(t);
			}

		}

	}

	private Node generateTile(char type) {

		switch (type) {
		case '#':
			return new Wall();
		case 'R':
			Robot robot = new Robot(SQUARE_SIZE);
			robots.add(robot);
			return robot;
		case 'o':
			return new Circle(50);
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




