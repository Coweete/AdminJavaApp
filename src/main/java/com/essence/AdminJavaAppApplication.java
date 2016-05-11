package com.essence;

import com.essence.Controller.AppController;
import com.essence.Gui.LoginScene;
import com.essence.Service.SpringService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication
public class AdminJavaAppApplication extends Application{

	@Autowired
	ResourceLoader resourceLoader;

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(AdminJavaAppApplication.class);
	private static ConfigurableApplicationContext applicationContext;
	private SpringService springService;
	private AppController controller;
	//private LoginLayout loginLayout = new LoginLayout();
	private LoginScene loginScene = new LoginScene();

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Fx start");
		controller.setPrimaryStage(primaryStage);
		primaryStage.setScene(new Scene(loginScene,400,400));
		primaryStage.show();
	}

	public void init()throws Exception{

		System.out.println("STARTING Admin App");
		SpringApplication app = new SpringApplication(AdminJavaAppApplication.class);
		app.setWebEnvironment(false);
		applicationContext = app.run();
		applicationContext.getAutowireCapableBeanFactory().autowireBean(app);

		springService = new SpringService();
		controller = new AppController();
		loginScene.setController(controller);
		//loginLayout.setCtrl(controller);
		controller.setSpringService(springService);


	}
	public static void main(String[] args) {
		launch(args);
	}


}
