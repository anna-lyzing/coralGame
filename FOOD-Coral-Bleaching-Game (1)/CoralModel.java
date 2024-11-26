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

public class CoralModel
{
  private double tempChangeNum = 0;
  
  private int colorBG = 116;
  private double temperature;
  private String coralHealth;
  private int day;
  private double money;
  // CREATE A LIST OF SCENARIOS BELOW!! 
  private String[][] scenarioList = {
    {"You're business is booming! You find that you may need to build a headquarters somewhere but the only land avaliable near you is a forest.", "Don't Build Headquarters", "Build Headquarters"},

    {"There is a growing demand for a certain type of fish in your business.", "Don't change fishing amounts", "Catch them all!"},

    {"You have a lot of excess oil from your company productions.", "Use the expensive but proper disposal methods", "Dispose oil in ocean"},

    {"Your boats pump out a lot of CO2 into the ocean.", "Switch to the expensive boat that reduces carbon footprint", "Who cares!"}
  }; 
  // each scenario is its own array {scenario, option1, option2} (Note: option1 will decrease temp and money effects, option2 will increase temp and money effects)
  private String[] currentScenario;
  // {temp effect, money effect}; each array of points is for the corresponding scenario array (based off index)
  private int[][] scenarioPoints = {
    {1, 100},
    {2, 200},
    {1, 100},
    {2, 150}
  }; //ADD CORRESPONDING POINTS FOR EACH SCENARIO

  private String[][] disasterList = {
    {"A hurricane has just hit the area and devastated your business and the ocean.", "Donate money to help with the ocean", "Work on rebuilding your company."},
    
    {"There is a drought in the area. Water levels are falling and hurting the coral reefs. Your business is losing money.", "Donate money to help with the ocean", "Work on rebuilding your company."}
  };
  private int[] disasterPoints = {3, 400};
  
  private int currentScenarioIndex;
  private String[] choicesDisplayOrder;
  private boolean naturalDisaster = false;

  private boolean gameOver = false;
  


  public void initializeValues()
  {
    temperature = 78.0;
    money = 1000;
    changeCoralHealth();
    day = 0;    
    colorBG = 116;
    gameOver = false;
  }
  public double getTemperature()
  {
    return temperature;
  }
  
  public int getColorBG()
    {
      return colorBG;
    }
  
  public String getCoralHealth()
  {
    return coralHealth;
  }

  public int getDay()
  {
    return day;
  }

  public double getMoney()
  {
    return money;
  }

  public String[] getCurrentScenario()
  {
    return currentScenario;
  }

  public int getCurrentScenarioIndex()
  {
    return currentScenarioIndex;
  }

  public boolean getGameOver()
  {
    return gameOver;
  }

  // allows game to change the temperature after each user decision
  public void changeTemperature(double tempChange)
  {
    tempChangeNum = tempChange;
    if (temperature + tempChange >= 73.0)
    {
      temperature += tempChange;
      changeCoralHealth();
      changeCoralColor();
    }
    else
    {
      temperature = 73.0;
    }
    
  }

  public void changeCoralHealth()
  {
    if (temperature >= 104.0)
    {
      coralHealth = "Dead";
    }
    else if (temperature < 73.0 || temperature > 84.0)
    {
      coralHealth = "Bleached";
    }
    else
    {
      coralHealth = "Healthy";
    }
  }

  public void changeCoralColor()
  {
    int newColor = colorBG + (int)(tempChangeNum*(255/(84-73)));
    if (temperature == 73.0 || newColor == 0)
    {
      colorBG = 0;
    }
    else if (coralHealth == "Healthy" && temperature>73 && newColor<=255)
    {
      colorBG = newColor;
    }
    else
    {
      colorBG = 255;
    }
    
      
    
    
  }

  // increments the day # after each scenario
  public void incrementDays()
  {
    day++;
  }

  // allows game to change the money after each user decision
  public void changeMoney(double moneyChange)
  {
    if (money + moneyChange >= 0)
    {
      money += moneyChange;
    }
    else
    {
      money = 0;
    }
  }

  // grabs a random scenario from scenarioList and makes it the current scenario
  public void randomScenario()
  {
    // Randomly selects a scenario from the scenarioList and assigns it to the instance variable currentScenario
    callND();
    if (naturalDisaster == false)
    {
      currentScenarioIndex = (int)(Math.random() * scenarioList.length);
      currentScenario = scenarioList[currentScenarioIndex];
    }
    else
    {
      currentScenarioIndex = (int)(Math.random() * disasterList.length);
      currentScenario = disasterList[currentScenarioIndex];
    }
       
  }

  // randomizes which option is first or second so that there is no pattern to which thing (the user's business or the coral) the option is beneficial to
  public String[] optionDisplayOrder()
  {
    String tempChoice1 = currentScenario[1];
    String tempChoice2 = currentScenario[2];

    choicesDisplayOrder = new String[2];

    int randomIndex = (int)(Math.random() * 2);
    if (randomIndex == 0)
    {
      choicesDisplayOrder[0] = tempChoice2;
      choicesDisplayOrder[1] = tempChoice1;
    }
    else
    {
      choicesDisplayOrder[0] = tempChoice1;
      choicesDisplayOrder[1] = tempChoice2;
    }

    return choicesDisplayOrder;
  }

  public void playScenario(int decisionNumber)
  {
    // runs through the options in currentScenario and finds the index of the option the user choice, assigning it to userChoiceIndex
    int userChoiceIndex = 0;
    for (int i = 1; i <= 2; i++)
      {
          if (choicesDisplayOrder[decisionNumber] == currentScenario[i])
          {
            userChoiceIndex = i;
          }
      }
    if (naturalDisaster == false)
    {
  
      if (userChoiceIndex == 1)
      {
        changeTemperature(-scenarioPoints[currentScenarioIndex][0]);
        changeMoney(-scenarioPoints[currentScenarioIndex][1]);
      }
      else if (userChoiceIndex == 2)
      {
        changeTemperature(scenarioPoints[currentScenarioIndex][0]);
        changeMoney(scenarioPoints[currentScenarioIndex][1]);
      }
    }
    else
    {
        if (userChoiceIndex == 1)
        {
          changeTemperature(-disasterPoints[0]);
          changeMoney(-disasterPoints[1]);
        }
        else if (userChoiceIndex == 2)
        {
          changeTemperature(disasterPoints[0]);
          changeMoney(disasterPoints[1]);
        }
    }


    if (money <= 0 || coralHealth.equals("Dead"))
    {
      endGame();
    }
    else
    {
      incrementDays();
    }
  }
  
  public void callND()
  {
    int num = (int)(Math.random() * 15);
    if (num == 1)
    {
      naturalDisaster = true;
    }
    else
    {
      naturalDisaster = false;
    }
      
  }

  public void endGame()
  {
    gameOver = true;
  }
}

