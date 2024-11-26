import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;
import java.net.URL; 
import javafx.scene.shape.*;
import javafx.scene.paint.Color;



public class CoralController
{
  private CoralModel model;
  private CoralView view;
  private int decisionNum;

  public CoralController(CoralModel model, CoralView view)
  {
    this.model = model;
    this.view = view;

    view.getStartButton().setOnAction(e -> handleStartButton());
    view.getButton1().setOnAction(e -> handleButton1());
    view.getButton2().setOnAction(e -> handleButton2());

    view.getEndButton().setOnAction(e -> handleEndButton());
  }

  public void handleStartButton()
  {
    model.initializeValues();
    model.randomScenario();
    view.getQuestionLabel().setText(model.getCurrentScenario()[0]);
    view.getMoneyLabel().setText("Money: $" + model.getMoney());
    view.getHealthLabel().setText("Coral Health: " + model.getCoralHealth());
    view.getTemperatureLabel().setText("Temperature: " + model.getTemperature() + "°F");
    view.getDayLabel().setText("Day: " + model.getDay());



    String[] displayOrder = model.optionDisplayOrder();
    view.getButton1().setText(displayOrder[0]);
    view.getButton2().setText(displayOrder[1]);

    view.startGame();

    int col = model.getColorBG();
    view.getCoralColor().setFill(Color.rgb(255, col, col));
  }

  public void handleButton1()
  {
    decisionNum = 0;

    updateButtons();
  }

  public void handleButton2()
  {
    decisionNum = 1;

    updateButtons();
  }

  public void updateButtons()
  {
    if (model.getGameOver() == false)
      {
        model.playScenario(decisionNum);

        view.getHealthLabel().setText("Coral Health: " + model.getCoralHealth());
        view.getMoneyLabel().setText("Money: $" + model.getMoney());
        view.getTemperatureLabel().setText("Temperature: " + model.getTemperature() + "°F");
        view.getDayLabel().setText("Day: " + model.getDay());


        int col = model.getColorBG();
        view.getCoralColor().setFill(Color.rgb(255, col, col));

        model.randomScenario();
        String[] displayOrder = model.optionDisplayOrder();

        view.getButton1().setText(displayOrder[0]);
        view.getButton2().setText(displayOrder[1]);

        view.getQuestionLabel().setText(model.getCurrentScenario()[0]);

      }
    else
    {
      view.endGame();
      view.getEndingLabel().setText("Game over! \n\nYou made it to day " + model.getDay() + " \nwith $" + model.getMoney() + "and a " + model.getCoralHealth() + " coral. The final temperature was " + model.getTemperature() + "°F.");
    }


  }


  public void handleEndButton()
  {
    handleStartButton();
  }  
}
