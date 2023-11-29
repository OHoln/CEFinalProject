package application;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Component {
	Rectangle view = new Rectangle();
	BodyDef bd = new BodyDef();
	Body body;
	PolygonShape ps = new PolygonShape();
	FixtureDef fd = new FixtureDef();
	
	Wall(float x, float y, float height, float width){
		view.setY(y-height);
		view.setX(x-width);
		view.setHeight(height*2);
		view.setWidth(width*2);
		view.setFill(Color.AQUA);
		
		bd.type = BodyType.STATIC;
		bd.position = (new Vec2(x/Pixel_Per_Meter,y/Pixel_Per_Meter));
		
		body = world.createBody(bd);
		body.setUserData("wall");
		
		ps.setAsBox(width/Pixel_Per_Meter, height/Pixel_Per_Meter);
		
		fd.shape = (ps);
		fd.density = 1;
		fd.friction = 0.3f;
		fd.restitution = 0.5f;
		body.createFixture(fd);
	}
}
