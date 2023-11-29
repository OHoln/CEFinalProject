package application;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.slf4j.LoggerFactory;

public class Menu {
    
	Boolean swap = false;
    Image shibebe1 = new Image(getClass().getResourceAsStream("shibebe1.png"));
    Image shibebe2 = new Image(getClass().getResourceAsStream("shibebe2.png"));
    ImageView background = new ImageView(new Image(getClass().getResourceAsStream("background.jpg")));
    ImageView font1 = new ImageView(new Image(getClass().getResourceAsStream("font1.png")));
    ImageView font2 = new ImageView(new Image(getClass().getResourceAsStream("font2.png")));
    ImageView soccer = new ImageView(new Image(getClass().getResourceAsStream("soccer.png")));
    ImageView ncucsie = new ImageView(new Image(getClass().getResourceAsStream("ncucsie.png")));
    ImageView button1 = new ImageView(new Image(getClass().getResourceAsStream("button.png")));
    ImageView button2 = new ImageView(new Image(getClass().getResourceAsStream("button.png")));
    public Group root  = new Group();
    float x=150,y=50;
    
    Text startText = new Text("開始遊戲");
    Text quitText = new Text("離開");
    Menu()  {
    	
    	background.setFitWidth(1920);
    	background.setFitHeight(1080);
    	root.getChildren().add(background);
			
    	font1.setFitWidth(400);
        font1.setFitHeight(300);
        font1.setTranslateX(300); 
        font1.setTranslateY(0);                     
        
        font2.setFitWidth(400);
        font2.setFitHeight(300);
        font2.setTranslateX(x+850); 
        font2.setTranslateY(y+30);    
        
        soccer.setFitWidth(80);
        soccer.setFitHeight(80);
        soccer.setTranslateX(x+945); 
        soccer.setTranslateY(y+155); 
        
        ncucsie.setRotate(30);
        ncucsie.setFitWidth(200);
        ncucsie.setFitHeight(120);
        ncucsie.setTranslateX(x+1200); 
        ncucsie.setTranslateY(y+50); 
        ncucsie.setOpacity(0.8);
        

        button1.setTranslateX(x+665); 
        button1.setTranslateY(y+400); 
        
        button2.setTranslateX(x+665); 
        button2.setTranslateY(y+550);  
        
        startText.setX(x+690);
        startText.setY(y+450);
        startText.setFont(Font.font("Arial", FontWeight.BOLD, 45));

        quitText.setX(x+740);
        quitText.setY(y+600);
        quitText.setFont(Font.font("Arial", FontWeight.BOLD, 45));
        
        root.getChildren().addAll(font1,font2,soccer,ncucsie,button1,button2,startText,quitText);
       
        Button startButton = new Button();
        root.getChildren().add(startButton);
        startButton.setTranslateX(x+675);
        startButton.setTranslateY(y+410);
        startButton.setPrefWidth(210);
        startButton.setPrefHeight(70);
        startButton.setOpacity(0);
        startButton.setOnAction(event -> {
            swap = true;
        });
        

        Button quitButton = new Button();
        root.getChildren().add(quitButton);
        quitButton.setTranslateX(x+675);
        quitButton.setTranslateY(y+560);
        quitButton.setPrefWidth(210);
        quitButton.setPrefHeight(70);
        quitButton.setOpacity(0);
        quitButton.setOnAction(event -> {
            Platform.exit();
        });
        
        
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), ncucsie);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0); 
        scaleTransition.setToX(2.0);   
        scaleTransition.setToY(2.0);   
        scaleTransition.setAutoReverse(true); 
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);

        // 開始動畫
        scaleTransition.play();
        Timeline shibebeAnime1 = new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> {
                    ImageView newShibebe1 = new ImageView(shibebe1);
                    newShibebe1.setFitWidth(200);
                    newShibebe1.setFitHeight(200);
                    newShibebe1.setTranslateX(x-300); 
                    newShibebe1.setTranslateY(y+0); 
                    newShibebe1.setOpacity(0.8);
                    font1.toFront();
                    font2.toFront();
                    soccer.toFront();
                    ncucsie.toFront();
                    button1.toFront();
                    button2.toFront();
                    startText.toFront();
                    quitText.toFront();
                    startButton.toFront();
                    quitButton.toFront();
                    root.getChildren().add(newShibebe1);
                    KeyValue keyValue = new KeyValue(newShibebe1.translateXProperty(), 1920);
                    KeyFrame keyFrame = new KeyFrame(Duration.seconds(4), keyValue);

                    Timeline objectTimeline = new Timeline(keyFrame);
                    objectTimeline.setCycleCount(1);
                    objectTimeline.setOnFinished(event2 -> {
                        root.getChildren().remove(newShibebe1); 
                    });
                    
                    objectTimeline.play();
                })
        );
        Timeline shibebeAnime2 = new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> {
                    ImageView newShibebe2 = new ImageView(shibebe2);
                    newShibebe2.setFitWidth(200);
                    newShibebe2.setFitHeight(200);
                    newShibebe2.setTranslateX(x+1820); 
                    newShibebe2.setTranslateY(y+200); 
                    newShibebe2.setOpacity(0.8);
                    root.getChildren().add(newShibebe2);
                    KeyValue keyValue = new KeyValue(newShibebe2.translateXProperty(), -300);
                    KeyFrame keyFrame = new KeyFrame(Duration.seconds(4), keyValue);

                    Timeline objectTimeline = new Timeline(keyFrame);
                    objectTimeline.setCycleCount(1);
                    objectTimeline.setOnFinished(event2 -> {
                        root.getChildren().remove(newShibebe2); 
                    });
                    
                    objectTimeline.play();
                })
        );
        Timeline shibebeAnime3 = new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> {
                    ImageView newShibebe1 = new ImageView(shibebe1);
                    newShibebe1.setFitWidth(200);
                    newShibebe1.setFitHeight(200);
                    newShibebe1.setTranslateX(x-300); 
                    newShibebe1.setTranslateY(y+400); 
                    newShibebe1.setOpacity(0.8);
                    root.getChildren().add(newShibebe1);
                    KeyValue keyValue = new KeyValue(newShibebe1.translateXProperty(), 1920);
                    KeyFrame keyFrame = new KeyFrame(Duration.seconds(4), keyValue);
                    Timeline objectTimeline = new Timeline(keyFrame);
                    objectTimeline.setCycleCount(1);
                    objectTimeline.setOnFinished(event2 -> {
                        root.getChildren().remove(newShibebe1); 
                    });
                    
                    objectTimeline.play();
                })
        );
        Timeline shibebeAnime4 = new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> {
                    ImageView newShibebe2 = new ImageView(shibebe2);
                    newShibebe2.setFitWidth(200);
                    newShibebe2.setFitHeight(200);
                    newShibebe2.setTranslateX(x+1820); 
                    newShibebe2.setTranslateY(y+600); 
                    newShibebe2.setOpacity(0.8);
                    root.getChildren().add(newShibebe2);
                    KeyValue keyValue = new KeyValue(newShibebe2.translateXProperty(), -300);
                    KeyFrame keyFrame = new KeyFrame(Duration.seconds(4), keyValue);

                    Timeline objectTimeline = new Timeline(keyFrame);
                    objectTimeline.setCycleCount(1);
                    objectTimeline.setOnFinished(event2 -> {
                        root.getChildren().remove(newShibebe2); 
                    });
                    
                    objectTimeline.play();
                })
        );
        Timeline shibebeAnime5 = new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> {
                    ImageView newShibebe1 = new ImageView(shibebe1);
                    newShibebe1.setFitWidth(200);
                    newShibebe1.setFitHeight(200);
                    newShibebe1.setTranslateX(x-300); 
                    newShibebe1.setTranslateY(y+800); 
                    newShibebe1.setOpacity(0.8);
                    root.getChildren().add(newShibebe1);
                    KeyValue keyValue = new KeyValue(newShibebe1.translateXProperty(), 1920);
                    KeyFrame keyFrame = new KeyFrame(Duration.seconds(4), keyValue);

                    Timeline objectTimeline = new Timeline(keyFrame);
                    objectTimeline.setCycleCount(1);
                    objectTimeline.setOnFinished(event2 -> {
                        root.getChildren().remove(newShibebe1); 
                    });
                    
                    objectTimeline.play();
                })
        );
        shibebeAnime1.setCycleCount(Timeline.INDEFINITE);
        shibebeAnime1.play();
        shibebeAnime2.setCycleCount(Timeline.INDEFINITE);
        shibebeAnime2.play();        
        shibebeAnime3.setCycleCount(Timeline.INDEFINITE);
        shibebeAnime3.play();         
        shibebeAnime4.setCycleCount(Timeline.INDEFINITE);
        shibebeAnime4.play();  
        shibebeAnime5.setCycleCount(Timeline.INDEFINITE);
        shibebeAnime5.play(); 
		
    	
    	
    }
}
