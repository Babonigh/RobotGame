import javafx.scene.Group;

public class Map extends Group {

	private int mapLength;
	private int mapHeight;

	private char[][] map;

	public Map(char[][] arr) {

		map = arr;
		
		this.mapLength = identifyMaxLength();
		this.mapHeight = map.length;

	}

	private int identifyMaxLength() {
		int maxLength = 0;
		for (int i = 0; i < map.length; i++) {
			int length = map[i].length;
			if (length > maxLength) {
				maxLength = length;
			}
		}
		return maxLength;
	}

	public int getHeight() {
		return this.mapHeight;
	}

	public int getLength() {
		return this.mapLength;
	}
	
	public char[][] getMapArray(){
		return map;
	}

}
