package application;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Goal extends Component {
	BodyDef bd = new BodyDef();
	Body body;
	PolygonShape ps1 = new PolygonShape();
	PolygonShape ps2 = new PolygonShape();
	PolygonShape ps3 = new PolygonShape();
	FixtureDef fd1 = new FixtureDef();
	FixtureDef fd2 = new FixtureDef();
	FixtureDef fd3 = new FixtureDef();
	Rectangle up = new Rectangle();
	Rectangle side = new Rectangle();
	Rectangle down = new Rectangle();
	ArrayList view = new ArrayList<>();
	Goal(String a){
		
		if(a.equals("L")) {
			bd.type = BodyType.STATIC;
			bd.position = new Vec2(5/Pixel_Per_Meter,550/Pixel_Per_Meter);
			ps1.setAsBox(5/Pixel_Per_Meter, 100/Pixel_Per_Meter);

			ps2.setAsBox(20/Pixel_Per_Meter, 5/Pixel_Per_Meter, new Vec2(25/Pixel_Per_Meter,-95/Pixel_Per_Meter),0);
			ps3.setAsBox(20/Pixel_Per_Meter, 5/Pixel_Per_Meter, new Vec2(25/Pixel_Per_Meter,95/Pixel_Per_Meter),0);
			
			body = world.createBody(bd);
			body.setUserData("goal");
			
			fd1.shape = (ps1);
			fd1.density = 0.5f;
			fd1.friction = 0.3f;
			fd1.restitution = 0f;
			fd2.shape = (ps2);
			fd2.density = 0.5f;
			fd2.friction = 0.3f;
			fd2.restitution = 0f;
			fd3.shape = (ps3);
			fd3.density = 0.5f;
			fd3.friction = 0.3f;
			fd3.restitution = 0f;
			
			body.createFixture(fd1);
			body.createFixture(fd2);
			body.createFixture(fd3);
			
			side.setX(0);
			side.setY(450);
			side.setHeight(200);
			side.setWidth(10);
			side.setFill(Color.WHITE);
			up.setX(10);
			up.setY(450);
			up.setHeight(10);
			up.setWidth(40);
			up.setFill(Color.WHITE);
			down.setX(10);
			down.setY(640);
			down.setHeight(10);
			down.setWidth(40);
			down.setFill(Color.WHITE);
			
			view.add(up);
			view.add(side);
			view.add(down);
		}
		else if(a.equals("R")) {
			bd.type = BodyType.STATIC;
			
			bd.position = new Vec2(1915/Pixel_Per_Meter,550/Pixel_Per_Meter);
			ps1.setAsBox(5/Pixel_Per_Meter, 100/Pixel_Per_Meter);

			ps2.setAsBox(20/Pixel_Per_Meter, 5/Pixel_Per_Meter, new Vec2(-25/Pixel_Per_Meter,-95/Pixel_Per_Meter),0);
			ps3.setAsBox(20/Pixel_Per_Meter, 5/Pixel_Per_Meter, new Vec2(-25/Pixel_Per_Meter,95/Pixel_Per_Meter),0);
			
			body = world.createBody(bd);
			body.setUserData("goal");
			
			fd1.shape = (ps1);
			fd1.density = 0.5f;
			fd1.friction = 0.3f;
			fd1.restitution = 0f;
			fd2.shape = (ps2);
			fd2.density = 0.5f;
			fd2.friction = 0.3f;
			fd2.restitution = 0f;
			fd3.shape = (ps3);
			fd3.density = 0.5f;
			fd3.friction = 0.3f;
			fd3.restitution = 0f;
			
			body.createFixture(fd1);
			body.createFixture(fd2);
			body.createFixture(fd3);
			
			side.setX(1910);
			side.setY(450);
			side.setHeight(200);
			side.setWidth(10);
			side.setFill(Color.WHITE);
			up.setX(1870);
			up.setY(450);
			up.setHeight(10);
			up.setWidth(40);
			up.setFill(Color.WHITE);
			down.setX(1870);
			down.setY(640);
			down.setHeight(10);
			down.setWidth(40);
			down.setFill(Color.WHITE);
			
			view.add(up);
			view.add(side);
			view.add(down);
		}
	}	
}
