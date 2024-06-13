import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

public class Sketch extends PApplet {
	
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
  PImage imgStars;
  PImage gameOverScreen;
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

  // Gallery Images + Varables
  PImage imgGallery1;
  PImage imgGallery2;
  PImage imgGallery3;
  PImage imgLeftOrRight;
  int galleryPage = 0;

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
  int notePadNumber = 0;
  boolean[] hideIngredient = new boolean[8];
  int currentIngredientIndex = 0;
  boolean[] collectedIngredients = new boolean[8];

  // Which customer was picked?
  boolean Character1picked = false;
  boolean Character2picked = false;
  boolean Character3picked = false;

  // Order Variables
  float[] order1 = {9, 9, 9, 9};

  // Which screen is being shown?
  boolean displayPauseScreen = false;
  boolean dipslayKitchen = false;

  // Level Variables
  boolean level1 = false;
  boolean level2 = false;
  boolean level3 = false;

  // Lives variables 
  int numberOfLives = 5;
  PImage[] drawHearts = new PImage[5];

  // Star varibale
  boolean drawStars;
  float timeSinceStars = 0;

  // Timer variables
  int rectLength = 120;
  int startTimer1;

  // Lose Screen Variables
  boolean outOfTime = false;

  // Other variables
  boolean isNotepadVisible = false;
	
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
    loadGalleryImages();

    // Determine the x-values for all fo the fruits
    for (int i = 0; i < xValueOfFruits.length; i++){
      xValueOfFruits[i] = random(width);
    }

    // Determine how fast each fruit will fall
    for (int i = 0; i < speedOfFruits.length; i++){
      speedOfFruits[i] = (float)random(5);
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
    float randomNumber = 0;
    for (int i = 0; i < order1.length; i++){
        while (true){
          randomNumber = myRandom.nextInt(8);
          if (!isInTheArray(randomNumber)){
            break;
          }
        }
        order1[i] = randomNumber;
    }

    // Set all ingredient hide statuses to false 
    for (int i = 0; i < hideIngredient.length; i++){
      hideIngredient[i] = false;
    }

    // Set start timer1
    startTimer1 = millis();
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    
    // Display welcome screen 
    if(intScreen == 0){
      welcomeScreen();
    }

    // Display take order screen
    if (intScreen == 1){
      takeOrder();
    }

    // Display pause screen
    if (displayPauseScreen){
      pauseScreen();
    }

    // Player on level 1
    if (level1 && !outOfTime){  
      if (dipslayKitchen){
        kitchen();
      } 
      timer1(); 
      lives();
    }

    // Did the player collect the right ingredient?
    if (drawStars){
      image(imgStars, xPizzaValue, 380);
    }

    // What page of the gallery is the player on?
    if (galleryPage == 1){
      gallery1();
    }
    else if (galleryPage == 2){
      gallery2();
    }
    else if (galleryPage == 3){
      gallery3();
    }

    if (numberOfLives == 0 || outOfTime){
      youLose();
    }

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

    // Load stars
    imgStars = loadImage("/Images/stars.png");
    imgStars.resize(200, 150);

    // Load heart
    for (int i = 0; i < drawHearts.length; i++){
      drawHearts[i] = loadImage("/Images/heart.png");
      drawHearts[i].resize(100, 90);
    }

    // Load game over screen
    gameOverScreen = loadImage("/Images/gameOver.png");
    gameOverScreen.resize(850, 500);

    // Load tutorial screen 

  }

  public void loadIngredients(){

    // Load image of pizza crust 
    imgPizzaCrust = loadImage("/Ingredients/Pizza Crust.png");
    imgPizzaCrust.resize(300, 200);

    // Assign fuirts a place in the array - drawIngredientImages
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

  public void loadGalleryImages(){

    imgGallery1 = loadImage("/GalleryPics/Gallery1.png");
    imgGallery1.resize(850, 500);

    imgGallery2 = loadImage("/GalleryPics/Gallery2.png");
    imgGallery2.resize(850, 500);

    imgGallery3 = loadImage("/GalleryPics/Gallery3.png");
    imgGallery3.resize(850, 500);

    imgLeftOrRight = loadImage("/GalleryPics/leftright buttons.png");
    imgLeftOrRight.resize(120, 100);
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
// NEEDS WORK
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
      isNotepadVisible = true;
    }
    else{
      image(imgnotePad, -250, 125);
      isNotepadVisible = false;
    }

    // Draw logo
    image(imgLogo, 40, 40);
    
    // Put fruits in the cabinate
    for (int i = 0; i < drawIngredientImages.length; i++){
      image(drawIngredientImages[i], fruitinCabinateX[i], fruitinCabinateY[i]);
    }

    // If character 1 is picked...
    if (Character1picked){
      level1 = true;
      image(imgThoughtBubble, 450, 20);
      for (int i = 0; i < order1.length; i++){
        image(drawIngredientImages[(int)order1[i]], 505 + i * 50, 55);
      }
    }

    if (Character2picked){
      level2 = true;
      //image(imgThoughtBubble, )
    }
    /*
    // Display collected ingredients on the notepad
    if (isNotepadVisible){
      displayCollectedIngredientsOnNotepad(-75);
    } 
    else {
      displayCollectedIngredientsOnNotepad(-250);
    }
    */
  }
/*
  public void displayCollectedIngredientsOnNotepad(int notePadX){
    int baseY = 200;
    int spacebetweenIngredients = 40;

    for (int i = 0; i < collectedIngredients.length; i++){
      if (collectedIngredients[i]){
        image(drawIngredientImages[i], (float)notePadX + 50, baseY + i * spacebetweenIngredients);
      }
    }
  }

  public void collectedIngredients(int index){
      if (index >= 0 && index < collectedIngredients.length) {
        collectedIngredients[index] = true;
      }
  }
    */
  public void kitchen(){
    background(imgKitchen);
    image(imgPizzaCrust, xPizzaValue, 420); 

  // Should I move the pizza
    if (xPizzaValue < 625 && movePizzaRight){
      xPizzaValue += 3;
      movePizzaRight = true;
    }
    if (xPizzaValue > 5 && movePizzaLeft){
      xPizzaValue -= 3;
      movePizzaLeft = true;
    }

    // Determine the x-values for all of fruits if they go off screen
    for (int i = 0; i < drawIngredientImages.length; i++){
      if (yValueOfFruits[i] > height && !hideIngredient[i]){
        yValueOfFruits[i] = 0;
        speedOfFruits[i] =  (float)random(4);
        xValueOfFruits[i] = random(width);
      }
      else if (yValueOfFruits[i] > height && hideIngredient[i]){
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

    // Collision detection + draw Stars or minus lives
    for (int i = 0; i < drawIngredientImages.length; i++) {
      if (xValueOfFruits[i] + 45 > xPizzaValue && xValueOfFruits[i] < xPizzaValue + 315 && yValueOfFruits[i] + 70 > 430) {
        if (drawIngredientImages[(int) order1[currentIngredientIndex]] == drawIngredientImages[i]) {
          yValueOfFruits[i] = height + 100;
          timeSinceStars = millis();
          drawStars = true;
          currentIngredientIndex++;
        } else {
          yValueOfFruits[i] = height + 100;
          numberOfLives--;
          drawHearts[numberOfLives] = null;
          if (numberOfLives == 0) {
            outOfTime = true;
            youLose();
          }
        }
      }    

    // Draw stars 
    if (drawStars && millis() >= timeSinceStars + 1000) {
      drawStars = false;
    }
    
    if (drawStars && millis() >= timeSinceStars + 1000){
      drawStars = false;
    }
  }
  }

  public void gallery1(){
    image(imgGallery1, 0, 0);
    image(imgLeftOrRight, 400, 440);
  }

  public void gallery2(){
    image(imgGallery2, 0, 0);
    image(imgLeftOrRight, 400, 440);
  }

  public void gallery3(){
    image(imgGallery3, 0, 0);
    image(imgLeftOrRight, 400, 440);
  }
  
  public void lives(){
  for (int i = 0; i < numberOfLives; i++) {
        if (drawHearts[i] != null) {
            image(drawHearts[i], 35 + (i * 40), 25);
        }
    }
  }

  public void timer1(){
    rect(700, 20, 120, 20);
    fill(113, 171, 139);
    rect(700, 20, rectLength, 20);
    fill(0, 0, 0);
    
    int elpasedTime = (millis() - startTimer1) / 1000;
    rectLength = 120 - elpasedTime;
  
    if (rectLength <= 0) {
      outOfTime = true;
      rectLength = 0;
      youLose();
    }
  }

  public void youLose(){
    image(gameOverScreen, 0, 0);
  }

  public boolean isInTheArray(float ingredients){
    for (int i = 0; i < order1.length; i++){
      if (order1[i] == ingredients){
        return true;
      }
    }
    return false;
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
    }

    // Was the first Character selected?
    if (mouseX >= 325 && mouseX <= 450 && mouseY >= 99 && mouseY <= 265){
      Character1picked = true;
    }

    // Was the second chracter selected?
    if (mouseX >= 480 && mouseX <= 550 && mouseY >= 99 && mouseY <= 265){
      Character2picked = true;
      System.out.println("Hello");
    }

    // Was the gallery button pressed?
    if (mouseX >= 150 && mouseX <= 400 && mouseY >= 350 && mouseY <= 480){
      galleryPage = 1;
    }

    // Which gallery screen should be shown?
    if (galleryPage == 1 && mouseX >= 400 && mouseX <= 460 && mouseY >= 440 && mouseY <= 490){
      galleryPage = 2;
    }
    
    if (galleryPage == 2 && mouseX >= 400 && mouseX <= 460 && mouseY >= 440 && mouseY <= 490){
      galleryPage = 3;
    }

    // Did you move the ingredient off of the screen 
    for (int i = 0; i < drawIngredientImages.length; i++){
      if (dist(mouseX, mouseY, xValueOfFruits[i], yValueOfFruits[i]) < 60){
        hideIngredient[i] = true;
      }
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

    // Which gallery page should be displayed?
    if (galleryPage == 1 && keyCode == RIGHT){
      galleryPage = 2;
    }

    if (galleryPage == 2 && keyCode == RIGHT){
      galleryPage = 3;
    }
    else if (galleryPage == 2 && keyCode == LEFT){
      galleryPage = 1;
    }

    if (galleryPage == 3 && keyCode == LEFT){
      galleryPage = 2;
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