package application;

import java.util.ArrayList;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Player extends Component {
	Boolean stop = false;
	static float NearestDistance = Float.MAX_VALUE;
	static Fixture nearest = null;
	boolean IsKicking = false;
	float charge = 0;
	public BodyDef bd = new BodyDef();
	Body body;
	CircleShape cs = new CircleShape();
	FixtureDef fd = new FixtureDef();
	ImageView playerImage = new ImageView();
	Image image1;
	Image image2;
	Vec2 velocity = new Vec2(0,0);
	ProgressBar ChargeBar = new ProgressBar();
	public ArrayList view = new ArrayList<>();
	float x,y;
	public Player(float x, float y, float radius, int num){
		this.x = x;
		this.y = y;
		view.add(ChargeBar);
		view.add(playerImage);
		
		ChargeBar.setTranslateX(x+50);
		ChargeBar.setTranslateY(y+50);
		ChargeBar.setPrefWidth(60);
		ChargeBar.setPrefHeight(10);
		ChargeBar.setRotate(0);
		ChargeBar.setVisible(false);
		ChargeBar.setProgress(0);
		ChargeBar.setStyle("-fx-accent: red;");
		
		
		
		
		bd.type = BodyType.DYNAMIC;
		bd.position.set(x / Pixel_Per_Meter, y / Pixel_Per_Meter);
		
		body = world.createBody(bd);
		body.setUserData("player");
		
		cs.m_radius = radius/Pixel_Per_Meter;
		
		fd.shape = (cs);
		fd.density = 0.5f;
		fd.friction = 0.3f;
		fd.restitution = 0f;
		
		body.createFixture(fd);
		if(num == 1) {
			image1 = new Image(getClass().getResourceAsStream("player1_R.JPG"));
			image2 = new Image(getClass().getResourceAsStream("player1_L.JPG"));
			playerImage.setImage(image1);
		}
		else if(num == 2) {
			image1 = new Image(getClass().getResourceAsStream("player2_R.JPG"));
			image2 = new Image(getClass().getResourceAsStream("player2_L.JPG"));
			playerImage.setImage(image2);
		}
		playerImage.setFitWidth(200);
		playerImage.setFitHeight(200);
	}
	public void update() {
		if(stop) {
			body.setLinearVelocity(velocity.mul(0));
		}
		else if(charge >= 100) {
			body.setLinearVelocity(velocity.mul(1.5f));
		}
		else {
			body.setLinearVelocity(velocity);
		}
		playerImage.setTranslateX(body.getPosition().x*Pixel_Per_Meter-108);//98
		playerImage.setTranslateY(body.getPosition().y*Pixel_Per_Meter-75);
		ChargeBar.setTranslateX(body.getPosition().x*Pixel_Per_Meter-30);
		ChargeBar.setTranslateY(body.getPosition().y*Pixel_Per_Meter-80);
		ChargeBar.setProgress(charge/100);
		getNearestFixture();
		if(IsKicking && charge < 100) {
			charge += 0.833f;
		}
	}
	public void move_U() {
		velocity = velocity.add(new Vec2(0,-20));
	}
	public void move_D() {
		velocity = velocity.add(new Vec2(0,20));
	}
	public void move_L() {
		velocity = velocity.add(new Vec2(-20,0));
		playerImage.setImage(image2);
	}
	public void move_R() {
		velocity = velocity.add(new Vec2(20,0));
		playerImage.setImage(image1);
	}
	
	public void stop_R() {
		velocity = velocity.add(new Vec2(20,0));
	}
	public void stop_U() {
		velocity = velocity.add(new Vec2(0,-20));
	}
	public void stop_D() {
		velocity = velocity.add(new Vec2(0,20));
	}
	public void stop_L() {
		velocity = velocity.add(new Vec2(-20,0));
	}
	public void getNearestFixture() {
			NearestDistance = Float.MAX_VALUE; 
			AABB aabb = new AABB();
	        aabb.lowerBound.set(new Vec2(0,0));
	        aabb.upperBound.set(new Vec2(1920,1080)); 
	        
			
	        world.queryAABB(new QueryCallback() {
	            @Override
	            public boolean reportFixture(Fixture fixture) {
	            	
	            	float distance = body.getPosition().sub(fixture.getBody().getPosition()).lengthSquared();
	            	//System.out.println(distance);
	    			
	                if (fixture.getBody() == body || !fixture.getBody().getUserData().equals("ball")) {
	                    return true; // 忽略特定物體本身
	                }
	                if(distance < NearestDistance) {
						nearest = fixture;
						NearestDistance = distance;
					}
	                return true;
	            }
	        }, aabb);
		
	}
	public void kick_start() {
		ChargeBar.setVisible(true);
		IsKicking = true;
		//time.play();
	}
	public void kick_end() {
		ChargeBar.setVisible(false);
		//System.out.print(charge);
		if(body.getPosition().sub(nearest.getBody().getPosition()).length() < 15f) {
			Vec2 kickWay = nearest.getBody().getPosition().sub(body.getPosition());
			kickWay.normalize();
			nearest.getBody().applyForce(kickWay.mul(charge*20000), nearest.getBody().getPosition());
		}
		IsKicking = false;
		charge = 0;
		
	}
	
	public void reset() {
		body.setTransform(new Vec2(x / Pixel_Per_Meter, y / Pixel_Per_Meter), 0);
		ChargeBar.setTranslateX(x);
		ChargeBar.setTranslateY(y);
		ChargeBar.setRotate(0);
		ChargeBar.setProgress(0);
		body.setLinearVelocity(new Vec2(0,0));
	}
	
	public void stop() {
		stop = true;
	}
	
	public void start() {
		stop = false;
	}
}
