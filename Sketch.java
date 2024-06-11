import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Random;

public class Sketch extends PApplet {
	
  /*
   * NOTE TO SELF 
   * GET THE INGREDIENTS TO DRAG INOT THE NOTE PAD
   * fill out gallery thing
   * make it so that you can press a button (displayed on screen) and move to diffrent page
   * do collision for pause screen 
   * do tutorial screen 
   * do gallery screen
   * make it so that you can't pick on the other character until you pass the level
   * fix adding fruit to the notepad
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
  PImage imgThoughtBubble;
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
  PImage[] drawThoughts = new PImage[8];
  float[] xValueOfFruits = new float[8];
  float[] yValueOfFruits = new float[8];
  float [] speedOfFruits = new float[8];
  float[] fruitinCabinateX = new float[8];
  float[] fruitinCabinateY = new float[8];

  // Which customer was picked?
  boolean Character1picked = false;
  boolean Character2picked = false;
  boolean Character3picked = false;

  // Order Variables
  float[] order1 = new float[4];

  // Which screen is being shown?
  boolean displayPauseScreen = false;
  boolean dipslayKitchen = false;

  // Count down variables
  int timerMIN = 0;
  int timerSEC = 0;
	
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
    
    // Set the x values of fruit in the take out order method
    fruitinCabinateX[0] = 290;
    fruitinCabinateX[1] = 370;
    fruitinCabinateX[2] = 290;
    fruitinCabinateX[3] = 370;
    fruitinCabinateX[4] = 520;
    fruitinCabinateX[5] = 600;
    fruitinCabinateX[6] = 520;
    fruitinCabinateX[7] = 600;

    // Set the y values of fruit in the take out order method 
    fruitinCabinateY[0] = 360;
    fruitinCabinateY[1] = 360;
    fruitinCabinateY[2] = 435;
    fruitinCabinateY[3] = 435;
    fruitinCabinateY[4] = 360;
    fruitinCabinateY[5] = 360;
    fruitinCabinateY[6] = 435;
    fruitinCabinateY[7] = 435;

    // Randomize the first order
    for (int i = 0; i < order1.length; i++){
        order1[i] = random(8);
        if(i >= 1){
          for (int j = 0; i < order1.length; i++){
            if (order1[i] == order1[i - 1]){
              order1[i] = random(8);
            }
          }
        }
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    
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
    
    startCountdownandPoints();
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

    // Load thought bubble
    imgThoughtBubble = loadImage("/Images/ThoughtBubble.png");
    imgThoughtBubble.resize(300, 250);

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

    // Assign fruits in the array - drawThoughts
    drawThoughts[0] = loadImage("/Ingredients/Cheese.png");
    drawThoughts[1] = loadImage("/Ingredients/Basil.png");
    drawThoughts[2] = loadImage("/Ingredients/Mushroom.png");
    drawThoughts[3] = loadImage("/Ingredients/Olives.png");
    drawThoughts[4] = loadImage("/Ingredients/peppers.png");
    drawThoughts[5] = loadImage("/Ingredients/Pepperoni.png");
    drawThoughts[6] = loadImage("/Ingredients/Pineapple.png");
    drawThoughts[7] = loadImage("/Ingredients/Sause.png");


    for(int i = 0; i < drawThoughts.length; i++){
      drawThoughts[i].resize(30, 40);
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
    if (mouseX <= 60 && mouseY >= 150){
      image(imgnotePad, -75, 125);
    }
    else{
      image(imgnotePad, -250, 125);
    }

    // Draw logo
    image(imgLogo, 40, 40);
    
    // Put fruits in the cabinate
    for (int i = 0; i < drawIngredientImages.length; i++){
      image(drawIngredientImages[i], fruitinCabinateX[i], fruitinCabinateY[i]);
    }

    if (Character1picked){
      image(imgThoughtBubble, 450, 20);
      for (int i = 0; i < order1.length; i++){
        image(drawIngredientImages[(int)order1[i]], 505 + i * 50, 55);
      }
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
      if (xValueOfFruits[i] + 60 > xPizzaValue && xValueOfFruits[i] < xPizzaValue + 330 && yValueOfFruits[i] + 70 > 430){
        yValueOfFruits[i] = height + 100;
      }
    }
  }

  public void gallery(){
    //image()
  }

  public void startCountdownandPoints(){
    text("POINTS: ", 40, 60);
    text(timerMIN, 60, 90);
    //text(timerSEC, )
    textSize(20);
    fill(0,0,0);
  }

  public void mouseDragged(){
    for (int i = 0; i < drawIngredientImages.length; i++){
      if (mouseX >= fruitinCabinateX[i] && mouseX <= fruitinCabinateX[i] + 55 && mouseY >= fruitinCabinateY[i] && mouseY <= fruitinCabinateY[i] + 70){
        fruitinCabinateX[i] += mouseX - pmouseX;
        fruitinCabinateY[i] += mouseY - pmouseY;
      }
      // Should I add this to the notepad?
      if (fruitinCabinateX[i] <= 60 && fruitinCabinateY[i] >= 150){
        fruitinCabinateX[i] = 75;
        fruitinCabinateY[i] = 220;
      }
    }
  }

  public void mouseClicked(){
    // Was start button pressed?
    if (mouseX >= 150 && mouseX <= 400 && mouseY >= 225 && mouseY <= 300){
      intScreen = 1;
      timerMIN = minute();
      timerSEC = second();
    }

    // Was the first Character clicked?
    if (mouseX >= 325 && mouseX <= 450 && mouseY >= 99 && mouseY <= 265){
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


}