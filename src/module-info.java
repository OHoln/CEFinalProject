module FinalProject {
	requires javafx.controls;
	requires jbox2d.library;
	requires org.slf4j;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
