package com.essence;

import com.essence.Controller.AppController;
import com.essence.Gui.LoginLayout;
import com.essence.Service.SpringService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AdminJavaAppApplication extends Application{

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(AdminJavaAppApplication.class);
	private static ConfigurableApplicationContext applicationContext;
	private SpringService springService;
	private AppController controller;
	private LoginLayout loginLayout = new LoginLayout();

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Fx start");
		controller.setPrimaryStage(primaryStage);
		primaryStage.setScene(new Scene(loginLayout,400,400));
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
		loginLayout.setCtrl(controller);

		controller.setSpringService(springService);


	}
	public static void main(String[] args) {
		launch(args);
	}


}
