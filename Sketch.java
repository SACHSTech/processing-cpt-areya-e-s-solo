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
   * Show numbers counting down using startCountdown
   *      if (dipslayKitchen && key == ENTER){
      startCountdown = millis();
    }

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
  PImage imgBasil;
  PImage imgPizzaCrust;
  PImage imgCheese;
  PImage imgMushroom;
  PImage imgOlives;
  PImage imgPepers;
  PImage imgPepperoni;
  PImage imgPineapple;
  PImage imgSause;

  // Ingredient variables 
  int xPizzaValue = 320;
  boolean movePizzaRight = false;
  boolean movePizzaLeft = false;
  PImage[] drawIngredientImages = new PImage[8];
  float[] xValueOfFruits = new float[8];
  float[] yValueOfFruits = new float[8];
  float [] speedOfFruits = new float[8];

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

    // Determine the x-values for all fo the fruits
    for (int i = 0; i < xValueOfFruits.length; i++){
      xValueOfFruits[i] = random(width);
    }

      // Determine how fast each fruit will fall
     for (int i = 0; i < speedOfFruits.length; i++){
        speedOfFruits[i] = (float)random(6);
      }

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    
  ///*
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
    //*/
    //kitchen();
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
    imgStart.resize(250, 300);

    // Load tutorial button
    imgTutorial = loadImage("/Images/tutorial button.png");
    imgTutorial.resize(210, 300);    

    // Load Gallery button
    imgGallery = loadImage("/Images/Gallery_button.png");
    imgGallery.resize(200, 300);

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

    // Load image of pizza crust 
    imgPizzaCrust = loadImage("/Ingredients/Pizza Crust.png");
    imgPizzaCrust.resize(330, 200);

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
    image(imgStart, 150, 235);
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
  // Determine the x-values for all of fruits if they go off screen
  for (int i = 0; i < drawIngredientImages.length; i++){
    if (yValueOfFruits[i] > height){
      yValueOfFruits[i] = 0;
      speedOfFruits[i] =  (float)random(4);
      xValueOfFruits[i] = random(width);
    }
  }

  // Draw the furits
  for (int i = 0; i < drawIngredientImages.length; i++){
    yValueOfFruits[i] += speedOfFruits[i];
    image(drawIngredientImages[i], xValueOfFruits[i], yValueOfFruits[i]);
  }

  // Collision detection
  for (int i = 0; i < drawIngredientImages.length; i++){
    if ()
  }

  }

}