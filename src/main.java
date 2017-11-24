import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;

public class main extends Application {

	public final static double WINDOW_WIDTH = 1100;
	public final static double WINDOW_HEIGHT = 600;
	
	private RobotGame robotScene = new RobotGame(WINDOW_WIDTH,WINDOW_HEIGHT);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setScene(robotScene);
		primaryStage.show();
		
	}
	
	
	public static void main(String[] args) {		
		

		
		
		launch();
	}

}
