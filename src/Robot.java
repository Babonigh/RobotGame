import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Robot extends Group {

	private int squareSize;
	private AnimationTimer at;

	private enum State {
		BUSY, FREE
	};

	private enum Operation {
		ROTATE_LEFT, ROTATE_RIGHT, MOVE_FORWARD
	};

	private State state = State.FREE;
	private ArrayList<Operation> stack = new ArrayList<Operation>();

	public Robot(int size) {
		this(Color.RED, size);
	}

	public Robot(Color color, int size) {

		this.squareSize = size;
		final Color GRAY = Color.GRAY;

		final double SIZE = squareSize - 2;
		final double MIDDLE = SIZE / 2;
		final double BODY_RADIUS = (SIZE - SIZE / 4) / 2;
		final double EYE_ANGLE = 20;
		final double BACK_ANGLE = 60;
		final double EYE_RADIUS = SIZE / 15;

		this.setTranslateX(1);
		this.setTranslateY(1);

		Rectangle bg = new Rectangle();
		bg.setWidth(SIZE);
		bg.setHeight(SIZE);
		bg.setFill(Color.TRANSPARENT);

		Arc body = new Arc();
		body.setRadiusX(BODY_RADIUS);
		body.setRadiusY(BODY_RADIUS);
		body.setCenterX(MIDDLE);
		body.setCenterY(MIDDLE);
		body.setStartAngle(-BACK_ANGLE);
		body.setLength(180 + 2 * BACK_ANGLE);
		body.setFill(GRAY);

		Circle leftEye = new Circle();
		leftEye.setRadius(EYE_RADIUS);
		leftEye.setCenterX(MIDDLE - BODY_RADIUS * Math.cos(Math.toRadians(90 - EYE_ANGLE)));
		leftEye.setCenterY(MIDDLE - BODY_RADIUS * Math.sin(Math.toRadians(90 - EYE_ANGLE)));
		leftEye.setFill(color);

		Circle rightEye = new Circle();
		rightEye.setRadius(EYE_RADIUS);
		rightEye.setCenterX(MIDDLE + BODY_RADIUS * Math.cos(Math.toRadians(90 - EYE_ANGLE)));
		rightEye.setCenterY(MIDDLE - BODY_RADIUS * Math.sin(Math.toRadians(90 - EYE_ANGLE)));
		rightEye.setFill(color);

		Rectangle wheels = new Rectangle();
		wheels.setWidth(9 / 10d * SIZE);
		wheels.setHeight(BODY_RADIUS * Math.sin(Math.toRadians(BACK_ANGLE)) * 1.5);
		wheels.setTranslateX((SIZE - wheels.getWidth()) / 2d);
		wheels.setTranslateY(MIDDLE - wheels.getHeight() / 2);
		wheels.setFill(color);

		Polygon triangle = new Polygon(MIDDLE + BODY_RADIUS / 2, MIDDLE, MIDDLE - BODY_RADIUS / 2, MIDDLE, MIDDLE,
				MIDDLE - Math.sqrt(3) * (BODY_RADIUS / 2));
		triangle.setTranslateY(Math.sqrt(3) * (BODY_RADIUS / 6));
		triangle.setFill(color);

		this.getChildren().addAll(bg, rightEye, leftEye, wheels, body, triangle);

		this.setOnMouseClicked(event -> {

			if (event.getButton() == MouseButton.PRIMARY) {
				this.rotateLeft();
			} else {
				this.moveForward();
			}

		});
	}

	private void checkStack() {

		if (stack.size() != 0) {

			switch (stack.get(0)) {

			case ROTATE_LEFT:
				stack.remove(0);
				rotateLeft();
				break;
			case ROTATE_RIGHT:
				stack.remove(0);
				rotateRight();
				break;
			case MOVE_FORWARD:
				stack.remove(0);
				moveForward();
				break;
			default:
				stack.remove(0);
				break;

			}

		}

	}

	public void moveForward(int n) {
		for (int i = 0; i < n; i++) {
			moveForward();
		}
	}

	public void moveForward() {
		if (isBusy()) {
			addToStack(Operation.MOVE_FORWARD);
			return;
		}
		setState(State.BUSY);
		moveForwardAnimation();
	}

	public void rotateRight() {
		if (isBusy()) {
			addToStack(Operation.ROTATE_RIGHT);
			return;
		}
		setState(State.BUSY);
		rotateRightAnimation();
	}

	public void rotateLeft() {
		if (isBusy()) {
			addToStack(Operation.ROTATE_LEFT);
			return;
		}
		setState(State.BUSY);
		rotateLeftAnimation();
	}

	private void rotateLeftAnimation() {
		at = new AnimationTimer() {
			long rotate = 0;

			@Override
			public void handle(long now) {
				setRotate(getRotate() + 2);
				rotate += 2;
				if (rotate == 90) {
					setState(State.FREE);
					this.stop();
				}
			}
		};
		at.start();
	}

	private void rotateRightAnimation() {
		at = new AnimationTimer() {
			long rotate = 0;

			@Override
			public void handle(long now) {
				setRotate(getRotate() - 2);
				rotate -= 2;
				if (rotate == -90) {
					setState(State.FREE);
					this.stop();
				}
			}
		};
		at.start();
	}

	private void moveForwardAnimation() {
		at = new AnimationTimer() {
			long move = 0;

			@Override
			public void handle(long now) {
				setTranslateX(Math.cos(Math.toRadians(270 + getRotate())) + getTranslateX());
				setTranslateY(Math.sin(Math.toRadians(270 + getRotate())) + getTranslateY());
				move++;
				if (move == squareSize) {
					setState(State.FREE);
					this.stop();
				}
			}
		};
		at.start();
	}

	private boolean isBusy() {
		if (this.state == State.BUSY) {
			return true;
		} else {
			return false;
		}
	}

	private void setState(State s) {
		this.state = s;
		if (s == State.FREE) {
			checkStack();
		}
	}

	private void addToStack(Operation o) {
		stack.add(o);
	}

	public boolean checkCollision(Node n) {

		if (n.equals(this)) {
			System.out.println("THIS");
			return false;
		}

		double minX = n.getTranslateX();
		double minY = n.getTranslateY();
		Node parent = null;
		if (!n.getStyleClass().toString().equals("root")) {
			parent = n.getParent();
			while (!parent.getStyleClass().toString().equals("root")) {
				minX += parent.getTranslateX();
				minY += parent.getTranslateY();
				parent = parent.getParent();
			}
		}

		if (n instanceof Group) {
			for (Node childNode : ((Group) n).getChildren()) {
				if (checkCollision(childNode)) {
					return true;
				}
			}
		} else {
			double nodeWidth = n.getBoundsInLocal().getWidth();
			double nodeHeight = n.getBoundsInLocal().getHeight();
			
			return this.getBoundsInLocal().intersects(minX, minY, nodeWidth, nodeHeight);
		}

		return false;

	}

	private Position getPos() {
		
		double minX = this.getTranslateX();
		double minY = this.getTranslateY();
		Node parent = this.getParent();
		while (!parent.getStyleClass().toString().equals("root")) {
			minX += parent.getTranslateX();
			minY += parent.getTranslateY();
			parent = parent.getParent();
		}
		
		return new Position(minX,minY);

	}
}

class Position{
	
	private double xPos;
	private double yPos;
	
	protected Position(double x, double y){
		this.xPos = x;
		this.yPos = y;
	}
	
	protected double getMinX(){
		return this.xPos;
	}
	protected double getMinY(){
			return this.yPos;
	}
}
