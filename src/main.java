import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;

public class main extends Application {

	public final static double WORLD_WIDTH = 1100*2;
	public final static double WORLD_HEIGHT = 600*2;
	
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
