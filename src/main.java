import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

	public final static double WORLD_WIDTH = 1100;
	public final static double WORLD_HEIGHT = 600;
	
	private RobotGame robotScene = new RobotGame();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setScene(robotScene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch();
	}

}