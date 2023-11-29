package application;
	
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


import java.util.ArrayList;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;
import org.slf4j.LoggerFactory;
	
public class Main extends Application{
	Menu menu = new Menu();	
	Scene MenuScene = new Scene(menu.root,1920,1080);

	public ArrayList<Ball> balllst = new ArrayList<>();
	public static Boolean reset;
	
	//按鍵偵測
	Boolean IsWPress = false;
	Boolean IsAPress = false;
	Boolean IsSPress = false;
	Boolean IsDPress = false;
	Boolean IsSpacePress = false;
	Boolean IsUPPress = false;
	Boolean IsLEFTPress = false;
	Boolean IsDOWNPress = false;
	Boolean IsRIGHTPress = false;
	Boolean IsDIGIT0Press = false;
	
	ImageView button1 = new ImageView(new Image(getClass().getResourceAsStream("button.png")));
    ImageView button2 = new ImageView(new Image(getClass().getResourceAsStream("button.png")));
    
    Text buttonText1 = new Text();
    Text buttonText2 = new Text();
	
	Text score = new Text();
	Text Start_End  = new Text();
	
	Text winner = new Text();

	
	Group root = new Group();
	Scene scene = new Scene(root,1920,1080);
	
	ImageView court = new ImageView(new Image(getClass().getResourceAsStream("court.jpg")));
	
	

	static Player player1 = new Player(500,540,50,1);
	static Player player2 = new Player(1420,540,50,2);
	
	static Ball ball = new Ball(960,540,20);
	
	Wall Wall_U = new Wall(960,-10,10,960);
	Wall Wall_D = new Wall(960,1090,10,960);
	Wall Wall_L = new Wall(-10,540,540,10);
	Wall Wall_R = new Wall(1930,540,540,10);
	
	Goal goal_L = new Goal("L");
	Goal goal_R = new Goal("R");
	
	MyContactListener CTL = new MyContactListener();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			

			button1.setX(840);
			button1.setY(750);
			buttonText1.setText("再來一局");
			buttonText1.setX(865);
			buttonText1.setY(800);
			buttonText1.setFont(Font.font("Arial", FontWeight.BOLD, 45));
			buttonText1.setVisible(false);
			button1.setVisible(false);
			
			
			button2.setX(840);
			button2.setY(900);
			buttonText2.setText("回到首頁");
			buttonText2.setX(865);
			buttonText2.setY(950);
			buttonText2.setFont(Font.font("Arial", FontWeight.BOLD, 45));
			buttonText2.setVisible(false);
			button2.setVisible(false);
			
			Button ReButton = new Button();
	        
	        ReButton.setTranslateX(840);
	        ReButton.setTranslateY(750);
	        ReButton.setPrefWidth(210);
	        ReButton.setPrefHeight(70);
	        ReButton.setOpacity(0);
	        ReButton.setOnAction(event -> {
	            menu.swap = true;
	        });
	        ReButton.setDisable(true);
	        
	        Button QuitButton = new Button();
	        
	        QuitButton.setTranslateX(840);
	        QuitButton.setTranslateY(900);
	        QuitButton.setPrefWidth(210);
	        QuitButton.setPrefHeight(70);
	        QuitButton.setOpacity(0);
	        QuitButton.setOnAction(event -> {
	        	primaryStage.setScene(MenuScene);
				primaryStage.setFullScreen(true);
	        });
	        QuitButton.setDisable(true);
			
			score.setX(720);
			score.setY(200);
			score.setFont(Font.font("Verdana",200));
			score.setStyle("-fx-text-fill: red;");
			
			court.setFitHeight(1080);
			court.setFitWidth(1920);
			
			
			balllst.add(ball);
			
			scene.setOnKeyPressed(e->{
				KeyCode  keycode = e.getCode();
				if(keycode == KeyCode.W && !IsWPress) {
					player1.move_U();
					IsWPress = true;
				}
				else if(keycode == KeyCode.A && !IsAPress) {
					player1.move_L();
					IsAPress = true;
				}
				else if(keycode == KeyCode.S && !IsSPress) {
					player1.move_D();
					IsSPress = true;
				}
				else if(keycode == KeyCode.D && !IsDPress) {
					player1.move_R();
					IsDPress = true;
				}
				else if(keycode == KeyCode.SPACE && !IsSpacePress) {
					player1.kick_start();
					IsSpacePress = true;
					e.consume();
				}
				if(keycode == KeyCode.NUMPAD5 && !IsUPPress) {
					player2.move_U();
					IsUPPress = true;
				}
				else if(keycode == KeyCode.NUMPAD1 && !IsLEFTPress) {
					player2.move_L();
					IsLEFTPress = true;
				}
				else if(keycode == KeyCode.NUMPAD2 && !IsDOWNPress) {
					player2.move_D();
					IsDOWNPress = true;
				}
				else if(keycode == KeyCode.NUMPAD3 && !IsRIGHTPress) {
					player2.move_R();
					IsRIGHTPress = true;
				}
				else if(keycode == KeyCode.NUMPAD0 && !IsDIGIT0Press) {
					player2.kick_start();
					IsDIGIT0Press = true;
				}
			});
			
			scene.setOnKeyReleased(e ->{
				KeyCode keycode = e.getCode();
				if(keycode == KeyCode.W ) {
					player1.stop_D();
					IsWPress = false;
				}
				else if(keycode == KeyCode.A ) {
					player1.stop_R();
					IsAPress = false;
				}
				else if(keycode == KeyCode.S ) {
					player1.stop_U();
					IsSPress = false;
				}
				else if(keycode == KeyCode.D ) {
					player1.stop_L();
					IsDPress = false;
				}
				else if(keycode == KeyCode.SPACE && IsSpacePress ) {
					e.consume();
					player1.kick_end();
					IsSpacePress = false;
				}
				if(keycode == KeyCode.NUMPAD5) {
					player2.stop_D();
					IsUPPress = false;
				}
				else if(keycode == KeyCode.NUMPAD1) {
					player2.stop_R();
					IsLEFTPress = false;
				}
				else if(keycode == KeyCode.NUMPAD2) {
					player2.stop_U();
					IsDOWNPress = false;
				}
				else if(keycode == KeyCode.NUMPAD3) {
					player2.stop_L();
					IsRIGHTPress = false;
				}
				else if(keycode == KeyCode.NUMPAD0 && IsDIGIT0Press ) {
					player2.kick_end();
					IsDIGIT0Press = false;
				}
			});
			
			player1.world.setContactListener(CTL);
			
			//scene.setOnMousePressed(e -> {
				//reset();
				/*Ball tmp = new Ball((float)e.getX(),(float)e.getY(),10);
				balllst.add(tmp);
				root.getChildren().add(tmp.view);*/
			//});

			AnimationTimer timer = new AnimationTimer() {
				@Override
				public void handle(long now) {
					Component.world.step(1/60f, 8, 3);
					if(CTL.reset == true) {
						score.setText(CTL.score1+" : "+CTL.score2);
						FadeTransition fade = new FadeTransition(Duration.millis(2000),score);
						fade.setFromValue(1);
				        fade.setToValue(0);
				        fade.setCycleCount(1);
				        fade.play();
				        reset();
						CTL.reset = false;
						if(CTL.score1 == 9 || CTL.score2 == 9) {
							button1.setVisible(true);
							buttonText1.setVisible(true);
							button2.setVisible(true);
							buttonText2.setVisible(true);
							ReButton.setDisable(false);
							QuitButton.setDisable(false);
							player2.stop();
							player1.stop();
							Start_End.setOpacity(1);
							if(CTL.score1 > CTL.score2) {
								Start_End.setText("Player1\nWin!");
							}
							else {
								Start_End.setText("Player2\nWin!");
							}
							Start_End.setX(610);
							Start_End.setY(420);
							Start_End.setFont(Font.font("Verdana",200));
							Start_End.setTextAlignment(TextAlignment.CENTER);
							CTL.score1 = 0;
							CTL.score2 = 0;
						}
					}
					if(menu.swap) {
						player1.start();
						player2.start();
						reset();
						button1.setVisible(false);
						buttonText1.setVisible(false);
						button2.setVisible(false);
						buttonText2.setVisible(false);
						ReButton.setDisable(true);
						QuitButton.setDisable(true);
						Start_End.setText("Game\nStart");
						Start_End.setX(650);
						Start_End.setY(420);
						Start_End.setFont(Font.font("Verdana",200));
						Start_End.setTextAlignment(TextAlignment.CENTER);
						FadeTransition fade1 = new FadeTransition(Duration.millis(2000),Start_End);
						fade1.setFromValue(1);
				        fade1.setToValue(0);
				        fade1.setCycleCount(1);
				        fade1.play();
						primaryStage.setScene(scene);
						primaryStage.setFullScreen(true);
						menu.swap = false;
					}
					player1.update();
					player2.update();
					ball.update();
				}
			};
			timer.start();
			
			root.getChildren().add(court);
			root.getChildren().add(button1);
			root.getChildren().add(buttonText1);
			root.getChildren().add(ReButton);
			root.getChildren().add(button2);
			root.getChildren().add(buttonText2);
			root.getChildren().add(QuitButton);
			root.getChildren().addAll(goal_L.view);
			root.getChildren().addAll(goal_R.view);
			root.getChildren().add(Wall_U.view);
			root.getChildren().add(Wall_D.view);
			root.getChildren().add(Wall_L.view);
			root.getChildren().add(Wall_R.view);
			root.getChildren().addAll(ball.view);
			root.getChildren().addAll(player1.view);
			root.getChildren().addAll(player2.view);
			root.getChildren().add(score);
			root.getChildren().add(Start_End);
			
			
			primaryStage.setFullScreen(true);
			primaryStage.setScene(MenuScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public static void reset() {
		player1.reset();
		player2.reset();
		ball.reset();
	}
	}



class MyContactListener implements ContactListener{
	public int score1 = 0, score2 = 0;
	public boolean reset = false;
	@Override
	public void beginContact(Contact contact) {
		Fixture f1 = contact.getFixtureA();
		Fixture f2 = contact.getFixtureB();
		
		Body b1 = f1.getBody();
		Body b2 = f2.getBody();
		
		
//		
		if(b1.getUserData().equals("ball")&&b2.getUserData().equals("goal")) {
			if(b1.getPosition().y>47.5 && b1.getPosition().y<63) {
				if(b2.getPosition().x == 0.5f) {
					score2 += 1;
					reset = true;
				}
				else {
					score1 += 1;
					reset = true;
				}
			}
		}
		else if(b1.getUserData().equals("goal")&&b2.getUserData().equals("ball")) {
			if(b2.getPosition().y>47.5 && b2.getPosition().y<63) {
				if(b1.getPosition().x == 0.5f) {
					score2 += 1;
					reset = true;
				}
				else {
					score1 += 1;
					reset = true;
				}
			}
		}
	}

	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}
}


