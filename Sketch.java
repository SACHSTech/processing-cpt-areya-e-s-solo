import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Random;

public class Sketch extends PApplet {
	
  /*
   * NOTE TO SELF 
   * CHEESE IMAGE GIVING ISSUES 
   * make it so that you can press a button (displayed on screen) and move to diffrent page
   * make it do that you have to pass the firt bit befofre you can jump around 
   *   ArrayList<Integer> whichFruits = new ArrayList<Integer>();
   */

   Random myRandom = new Random();

  // Import images
  PImage imgExterior; 
  PImage imgStart;
  PImage imgGallery;
  PImage imgTutorial;
  PImage imgWelcome;
  PImage imgLogo;
  PImage imgBy;
  PImage imgOrderStation;
  PImage imgCharacter1;
  PImage imgCharacter2;
  PImage imgCharacter3;
  PImage imgnotePad;
  PImage imgPaused;
  PImage imgKitchen;
  int intScreen = 0;

  // Load Images of pizza toppings
  // Basil
    PImage imgBasil;
    int intBasil = 0;
    boolean drawBasil;
  // Pizza Crust
    PImage imgPizzaCrust;
  // Cheese
    PImage imgCheese;
    int intCheese = 1;
    boolean drawCheese;
  // Mushroom
    PImage imgMushroom;
    int intMushroom = 2;
    boolean drawMushroom;
  // Olives
    PImage imgOlives;
    int intOlives = 3;
    boolean drawOlives;
  // Pepers
    PImage imgPepers;
    int intPepers = 4;
    boolean drawPepers;
  // Pepperoni
    PImage imgPepperoni;
    int intPepperoni = 5;
    boolean drawPepperoni;
  // Pineapple
    PImage imgPineapple;
    int intPineapple = 6;
    boolean drawPineapple;
  // Sause
    PImage imgSause;
    int intSause = 7;
    boolean drawSause;

  // Ingredient variables 
  PImage[] drawIngredientImages = new PImage[8];
  float[] xValueOfFruits = new float[8];
  //float[] yValueofFruit = new float[8];
  int xPizzaValue = 320;
  boolean movePizzaRight = false;
  boolean movePizzaLeft = false;

  // Which customer was picked?
  boolean Character1picked = false;
  boolean Character2picked = false;
  boolean Character3picked = false;

  // Which screen is being shown?
  boolean displayPauseScreen = false;
  boolean dipslayKitchen = false;

  // Count down variables
  int startCountdown;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(850, 500);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {

    // Set background colour
    background(0);

    // Load Images 
    loadImages();
    loadIngredients();

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    
    /*
    if(intScreen == 0){
      welcomeScreen();
    }
    if (intScreen == 1){
      takeOrder();
    }
    
    if (displayPauseScreen){
      pauseScreen();
    }

    if (dipslayKitchen){
      kitchen();
    }
    */
    kitchen();
  }

  public void loadImages(){
    
    // Load Exterior of Dinner
    imgExterior = loadImage("/Images/Diner_exteriorFull.png");
    imgExterior.resize(width, height);

    // Load welcome sign
    imgWelcome = loadImage("/Images/Welcome to.png");
    imgWelcome.resize(300, 200);

    // Load logo 
    imgLogo = loadImage("/Images/PizzaliciousLogo.png");
    imgLogo.resize(200, 130);

    // Load Start Button
    imgStart = loadImage("/Images/Start_button.png");
    imgStart.resize(250, 150);

    // Load tutorial button
    imgTutorial = loadImage("/Images/tutorial button.png");
    imgTutorial.resize(200, 100);    

    // Load Gallery button
    imgGallery = loadImage("/Images/Gallery_button.png");
    imgGallery.resize(200, 100);

    // Load by Areya E-S
    imgBy = loadImage("/Images/byAES.png");
    imgBy.resize(200, 100);

    // Load paused screen
    imgPaused = loadImage("/Images/paused screen.png");
    imgPaused.resize(250, 275);

    // Load order station
    imgOrderStation = loadImage("/Images/orderStation.png");
    imgOrderStation.resize(width, height);

    // Load the first character
    imgCharacter1 = loadImage("/Images/Character1.png");
    imgCharacter1.resize(300, 400);

    // Load Second character
    imgCharacter2 = loadImage("/Images/Character2.png");
    imgCharacter2.resize(300, 405);

    // Load third Character
    imgCharacter3 = loadImage("/Images/Character3.png");
    imgCharacter3.resize(300, 400);

    // Load image of notepad 
    imgnotePad = loadImage("/Images/notePad.png");
    imgnotePad.resize(400, 500);    

    // Load image of Kitchen
    imgKitchen = loadImage("/Images/KitchenFull.png");
    imgKitchen.resize(width, height);
  }

  public void loadIngredients(){
    // I THINK YOU CAN DELETE THIS 
    /*
    // Load image of pizza crust 
    imgPizzaCrust = loadImage("/Ingredients/Pizza Crust.png");
    imgPizzaCrust.resize(330, 200);
    
    // Load image of chesse
    imgCheese = loadImage("/Ingredients/Cheese.png");
    imgCheese.resize(60, 70);
    
    // Load image of basil 
    imgBasil = loadImage("/Ingredients/Basil.png");
    imgBasil.resize(60, 70);
    
    // Load image of mushroom 
    imgMushroom = loadImage("/Ingredients/Mushroom.png");
    imgMushroom.resize(60, 70);

    // Load image of olive
    imgOlives = loadImage("/Ingredients/Olives.png");
    imgOlives.resize(60, 70);

    // Load image of pepers
    imgPepers = loadImage("/Ingredients/peppers.png");
    imgPepers.resize(60, 70);

    // Load image of peperonie
    imgPepperoni = loadImage("/Ingredients/Pepperoni.png");
    imgPepperoni.resize(60, 70);

    // Load image of pineapple
    imgPineapple = loadImage("/Ingredients/Pineapple.png");
    imgPineapple.resize(60, 70);

    // Load image of sause
    imgSause = loadImage("/Ingredients/Sause.png");
    imgSause.resize(60, 70);
    */

    // Assign fuirts a place in the array
    drawIngredientImages[0] = loadImage("/Ingredients/Cheese.png");
    drawIngredientImages[1] = loadImage("/Ingredients/Basil.png");
    drawIngredientImages[2] = loadImage("/Ingredients/Mushroom.png");
    drawIngredientImages[3] = loadImage("/Ingredients/Olives.png");
    drawIngredientImages[4] = loadImage("/Ingredients/peppers.png");
    drawIngredientImages[5] = loadImage("/Ingredients/Pepperoni.png");
    drawIngredientImages[6] = loadImage("/Ingredients/Pineapple.png");
    drawIngredientImages[7] = loadImage("/Ingredients/Sause.png");

    for (int i = 0; i < drawIngredientImages.length; i++){
      drawIngredientImages[i].resize(60, 70);
    }
  }

  public void welcomeScreen(){
    background(imgExterior);
    image(imgWelcome, 100, 45);
    image(imgLogo, 150, 95);
    image(imgStart, 150, 225);
    image(imgTutorial, 150, 310);
    image(imgGallery, 150, 385);
    image(imgBy, 50, 470);
  }

  public void pauseScreen(){
    background(imgExterior);
    image(imgLogo, 150, 80);
    image(imgPaused, 140, 220);
    // DO COLLISION
  }

  public void takeOrder(){
    image(imgOrderStation, 0, 0);
    image(imgCharacter1, 325, 99);
    image(imgCharacter2, 480, 80);
    image(imgCharacter3, 680, 85);

    // Bring out notepad
    if (mouseX >= 800 && mouseY >= 150){
      image(imgnotePad, 520, 125);
    }
    else{
      image(imgnotePad, 730, 125);
    }
    /*
    // Which character was selected?
    if (Character1picked){
      // DO THIS
    }
    */
  }

  public void mouseDragged(){
    // Drag ingredients into the notepad
  }

  public void mouseClicked(){
    // Was start button pressed?
    if (mouseX >= 150 && mouseX <= 400 && mouseY >= 225 && mouseY <= 300){
      intScreen = 1;
    }

    // Was the first Character clicked?
    if (mouseX >= 325 && mouseX <= 405){
      Character1picked = true;
    }
  }

  public void keyPressed(){
    if (key == 'x'){
      displayPauseScreen = true;
    }
    if(key == 'k'){
      dipslayKitchen = true;
    }
    if (dipslayKitchen && keyCode == RIGHT){
      movePizzaRight = true;
    }
    if (dipslayKitchen && keyCode == LEFT){
      movePizzaLeft = true;
    }
    if (dipslayKitchen && key == ENTER){
      startCountdown = millis();
    }
  }

  public void keyReleased(){
    if (keyCode == RIGHT){
      movePizzaRight = false;
    }
    if (keyCode == LEFT){
      movePizzaLeft = false;
    }
  }

  public void kitchen(){
    background(imgKitchen);
    image(imgPizzaCrust, xPizzaValue, 420);

    // Show numbers counting down using startCountdown 

    // Should I move the pizza
    if (xPizzaValue < 625 && movePizzaRight){
      xPizzaValue+=2;
      movePizzaRight = true;
    }
    if (xPizzaValue > 5 && movePizzaLeft){
      xPizzaValue-=2;
      movePizzaLeft = true;
    }

    // Fix this
    // Determine the x-values for all fo the fruits
  for (int i = 0; i < xValueOfFruits.length; i++){
    xValueOfFruits[i] = random(width);
  }

  for (int i = 0; i < drawIngredientImages.length; i++){
    image(drawIngredientImages[i], xValueOfFruits, (float)random(4));
  }


    /*
    // FINNISH THIS 
    // Make it rain fruit!
    for (int i = 0; i < yValueofFruit.length; i++){

    }
    if (drawBasil){
      image(imgBasil, xValueOfFruits[intBasil], yValueofFruit);
    }
    if (drawCheese){
      image(imgCheese, xValueOfFruits[intCheese], yValueofFruit);
    }
    if (drawMushroom){
      image(imgMushroom, xValueOfFruits[intMushroom], i);
    }
    
    // Choose a fruit number 
    for (int i = 0; i < whichFruits.length; i++){
      if (i == intBasil){
        drawBasil = true;
      }
      else if (i == intCheese){
        drawCheese = true;
      }
      else if (i == intMushroom){
        drawMushroom = true;
      }
      else if (i == intOlives){
        drawOlives = true;
      }
      else if (i == intPepperoni){
        drawPepperoni = true;
      }
      else if (i == intPepers){
        drawPepers = true;
      }
      else if (i == intPineapple){
        drawPineapple = true;
      }
      else if (i == intSause){
        drawSause = true;
      }
    }
    */

    /*
    // Give each fruit a random number

    for (int i = 0; i < 6; i++){
      int intFruitNumebr = myRandom.nextInt(7);
      //int intFruitNumebr = myRandom.nextInt(696) + 5;
      whichFruits[i] = (intFruitNumebr);
    }

    // Which fruit was drawn?
    for(int i = 0; i < whichFruits.length; i++){
      if (whichFruits[i] == 1){
        image(imgBasil, )
      }
    }
    */

  }

}