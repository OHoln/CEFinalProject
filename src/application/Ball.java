package application;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Ball extends Component {
	public BodyDef bd = new BodyDef();
	Body body;
	CircleShape cs = new CircleShape();
	FixtureDef fd = new FixtureDef();
	Image img1 = new Image(getClass().getResourceAsStream("ball.png"));
	ImageView view = new ImageView(img1);
	
	Vec2 force = new Vec2(2000,0);
	float max_velocity = 20;
	float x,y;
	Ball(float x, float y, float radius){
		this.x = x;
		this.y = y;
		
		view.setFitWidth(radius*3);
		view.setFitHeight(radius*3);
		
		
		bd.type = BodyType.DYNAMIC;
		bd.position.set(x / Pixel_Per_Meter, y / Pixel_Per_Meter);;
		
		body = world.createBody(bd);
		body.setUserData("ball");
		
		cs.m_radius = radius/Pixel_Per_Meter;
		
		fd.shape = (cs);
		fd.density = 50;
		fd.friction = 0.3f;
		fd.restitution = 1f;
		
		body.createFixture(fd);
	}
	public void ApplyForce() {
		if(body.getLinearVelocity().length() < max_velocity) {
			body.setLinearVelocity(new Vec2(10,0));
			//body.applyForce(force,body.getWorldCenter());
		}
	}
	
	public void stop(){
		body.setLinearVelocity(new Vec2(0,0));
	}
	
	public void update() {
		body.setAngularVelocity(60);
		view.setTranslateX(body.getPosition().x*Pixel_Per_Meter-27);
		view.setTranslateY(body.getPosition().y * Pixel_Per_Meter-30);
		view.setRotate(body.getAngle());
		body.setLinearVelocity(body.getLinearVelocity().mul(0.992f));
	}
	public void reset() {
		body.setTransform(new Vec2(x / Pixel_Per_Meter, y / Pixel_Per_Meter), 0);
		body.setLinearVelocity(new Vec2(0,0));
	}
}


