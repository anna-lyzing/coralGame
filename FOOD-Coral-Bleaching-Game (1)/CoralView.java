import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.*; 
import java.io.*;
import java.util.*;
import java.net.URL; 
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

public class CoralView
{
  private GridPane gridPane;
  private Button startButton = new Button("Start");
  private Label introLabel = new Label("Welcome to the Coral Reef Simulator!\n\n\n You are a wealthy citizen living \non the coast side, in a beach house that has a \nclear view of the atlantic and it's beautiful ecosystem. \n\n You own a large but local organization \nthat manages fishing and other activities. \nHowever, with the power of wealth, you have the responsibility \nof making crucial decisions everyday. \n\n Is it possible to maximize the amount of profit \nyou have sustainbly? How do your choices affect the\n health of coral reefs? \n\n Press the start button to begin!");
  private Button button1 = new Button("Option 1");
  private Button button2 = new Button("Option 2");
  private Label questionLabel = new Label("Question");
  private Label moneyLabel = new Label("Money: $");
  private Label healthLabel = new Label("Coral Health: ");
  private Label temperatureLabel = new Label("Temperature: ");
  private Label dayLabel = new Label("Day: ");
  private Image factoryImage;
  private Image coralImage;
  Rectangle rect = new Rectangle(5, 5, 200, 140); 

  private Label endingLabel = new Label("Game Over!");
  private Button endButton = new Button("Start Over");
  
  public void start(Stage primaryStage)
  {  
    // creating grid and setting the grid alignment
    gridPane = new GridPane(); 

    //Setting size for the pane  
    gridPane.setMinSize(600, 500); 

    //Setting the padding  
    gridPane.setPadding(new Insets(10, 10, 10, 10)); 

    //Setting the vertical and horizontal gaps between the columns 
    gridPane.setVgap(5); 
    gridPane.setHgap(5); 

    gridPane.setAlignment(Pos.TOP_LEFT);

    // add buttons to gridpane
    gridPane.add(startButton, 20, 6);
    startButton.setWrapText(true);

    // add introduction/instructions
    gridPane.add(introLabel, 20, 5);
    introLabel.setWrapText(true);
    
    Scene scene = new Scene(gridPane);
    primaryStage.setTitle("Coral Bleaching Game"); //set stage title
    primaryStage.setScene(scene); //place stage on scene
    primaryStage.show(); //display stage       
  }

  public void startGame()
  {
    gridPane.getChildren().clear();

    // add buttons and labels to grid pane
    gridPane.add(button1, 0, 2);
    button1.setWrapText(true);
    button1.setMaxSize(200.0, 50.0);
    
    gridPane.add(button2, 0, 3);
    button2.setWrapText(true);
    button2.setMaxSize(200.0, 50.0);

    gridPane.add(questionLabel, 0, 0);

    questionLabel.setMaxSize(400.0, 50.0);
    questionLabel.setWrapText(true);
    gridPane.add(moneyLabel, 5, 5);
    gridPane.add(healthLabel, 5, 6);
    gridPane.add(temperatureLabel, 5,7);
    gridPane.add(dayLabel, 5, 8);

    makeImage();
  }

  public void makeImage() 
  {
    try
    {
      factoryImage = new Image(new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUwmUMbZZtk6DgF9njvIWdl50JMM7lq_oEaQ&s").openStream());
    }
    catch (IOException e)
    {
      factoryImage = null;
    }
    
    //adding image
    ImageView imageView = new ImageView(factoryImage);

    //Setting the position of the image 
    imageView.setX(50); 
    imageView.setY(25); 

    //setting the fit height and width of the image view 
    imageView.setFitHeight(135); 
    imageView.setFitWidth(150); 

    //Setting the preserve ratio of the image view 
    imageView.setPreserveRatio(true);  

    gridPane.add(imageView, 0, 10);

    try
      {
        coralImage = new Image(new FileInputStream("image.png"));
        //coralImage = new Image(new URL("image.png").openStream());
      }
      catch (IOException e)
      {
        coralImage = null;
      }

    //adding image
    ImageView imageView1 = new ImageView(coralImage);

    //Setting the position of the image 
    imageView1.setX(50); 
    imageView1.setY(25); 

    //setting the fit height and width of the image view 
    imageView1.setFitHeight(180); 
    imageView1.setFitWidth(200); 

    //Setting the preserve ratio of the image view 
    imageView1.setPreserveRatio(true);  

    rect.setFill(Color.rgb(200, 0, 0));

    gridPane.add(rect,5,2);    
    gridPane.add(imageView1, 5, 2);

  }
  public Rectangle getCoralColor()
  {
    return rect;
  }
  
  // getters for buttons and labels
  public Button getStartButton()
  {
    return startButton;
  }
  
  public Button getButton1()
  {
    return button1;
  }

  public Button getButton2()
  {
    return button2;
  }

  public Label getQuestionLabel()
  {
    return questionLabel;
  }

  public Label getMoneyLabel()
  {
    return moneyLabel;
  }

  public Label getHealthLabel()
  {
    return healthLabel;
  }

  public Label getTemperatureLabel()
  {
    return temperatureLabel;
  }

  public Label getDayLabel()
  {
    return dayLabel;
  }

  
  public Image getFactoryImage()
  {
    return factoryImage;
  }

  public Label getEndingLabel()
  {
    return endingLabel;
  }

  public Button getEndButton()
    {
      return endButton;
    }
  

  public void endGame()
    {
      gridPane.getChildren().clear();

      // add buttons and labels to grid pane
      gridPane.add(endingLabel, 0, 2);
      endingLabel.setWrapText(true);
      
      gridPane.add(endButton, 2, 2);

      
    }



  
  
}