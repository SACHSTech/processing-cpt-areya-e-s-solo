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
  PImage imgThoughtBubble2;
  PImage imgStars;
  PImage gameOverScreen;
  PImage imgtutorial;
  PImage imgRemember;
  PImage img3;
  PImage img2;
  PImage img1;
  PImage imgWinScreen;
  PImage imgOptions;
  PImage imgChalkBoard;
  PImage imgtutorialScreen;

  // Load Images of pizza toppings
  PImage imgPizzaCrust;
  PImage imgCheckMark;
  PImage[] drawIngredientImages = new PImage[8];
  PImage[] drawThoughts = new PImage[8];
  PImage imgX;

  // Gallery Images + Varables
  PImage imgGallery1;
  PImage imgGallery2;
  PImage imgGallery3;
  PImage imgLeftOrRight;
  int galleryPage = 0;

  // Ingredient variables 
  int xPizzaValue = 320;
  int currentIngredientIndex = 0;
  boolean movePizzaRight = false;
  boolean movePizzaLeft = false;
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
  float[] order1 = {9, 9, 9, 9};
  float[] order2 = {9, 9, 9, 9};
  float[] order3 = {9, 9, 9, 9};
  int num1;
  int num2;
  int num3;

  // Which screen is being shown?
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
  int startCountDown = -1;
  int startTimer1;
  boolean countdownStarted = false;
  boolean countdownFinished = false;

  // Lose Screen Variables
  boolean outOfTime = false;
  boolean gameWon = false;

  // Math variables
  boolean mathAnswered = false;
  String userMathInput = "";
  int checkMarkStartTime = 0;
  int checkMarkDuration = 200;
  boolean showCheckMark = false;
  boolean showX = false;
  int xStartTime = 2000;
  boolean checkMath = false;
  int answer = 0;

  // Tutoiral variable
  int tutorialPage = 0;
  boolean showTutorial = false;
  boolean characterSelected = false;
  boolean onMiniKitchen = false;
  boolean displayTakeOrder = false;
  boolean displayWelcomeScreen = true;
  int pauseScreenStartTime = 0;
  boolean miniCollision = false;
  int elpasedTime = 0;
  boolean showRemember = false;
  boolean showMiniTakeOrder = false;
  boolean ongoingtutorial = false;
  boolean showMiniYouWin = false;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(850, 500);
  }

  /** 
   * Called once at the beginning of execution.
   * Initalizes preset values
   */
  public void setup() {

    // Set background colour
    background(0);

    // Load Images 
    loadImages();
    loadIngredients();
    loadGalleryImages();

    // Determine the x-values for all of the falling fruits
    for (int i = 0; i < xValueOfFruits.length; i++){
      xValueOfFruits[i] = random(width);
    }

    // Determine the speed at which each fruit will fall
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
    float randomNumber1 = 0;
    for (int i = 0; i < order1.length; i++){
        while (true){
          randomNumber1 = myRandom.nextInt(8);
          if (!isInThe1stArray(randomNumber1)){
            break;
          }
        }
        order1[i] = randomNumber1;
    }

    // Randomize the second order
    float randomNumber2 = 0;
    for (int i = 0; i < order2.length; i++){
        while (true){
          randomNumber2 = myRandom.nextInt(8);
          if (!isInThe2ndArray(randomNumber2)){
            break;
          }
        }
        order2[i] = randomNumber2;
    }

    // Randomize the second order
    float randomNumber3 = 0;
    for (int i = 0; i < order3.length; i++){
        while (true){
          randomNumber3 = myRandom.nextInt(8);
          if (!isInThe3rdArray(randomNumber3)){
            break;
          }
        }
        order3[i] = randomNumber3;
    }    

    // Set randomized numbers for math euqation
    num1 = myRandom.nextInt(5);
    num2 = myRandom.nextInt(6);
    num3 = myRandom.nextInt(3);

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    
    // Display welcome screen 
    if(displayWelcomeScreen){
      welcomeScreen();
    }

    // Display take order screen
    if (displayTakeOrder){
      takeOrder();
    }

    // Player on level 1
    if (level1 && !outOfTime){  
      if (dipslayKitchen){
        kitchen();
      } 
      timer1(); 
      lives();
    }

    // Player on level 2
    if (level2 && !outOfTime){
      if (!mathAnswered){
        keyPad();
      }
      if(dipslayKitchen){
        kitchen();
      }
      timer1();
      lives();
    }

    // Player on level 3
    if (level3 && !outOfTime){
      if (!mathAnswered){
        keyPad();
      }
      if (dipslayKitchen){
        kitchen();
      }
      timer1();
      lives();
    }

    // Show checkmark or 'x' 
    if (showCheckMark) {
      drawCheckMark();
      if (millis() - checkMarkStartTime > checkMarkDuration) {
          showCheckMark = false;
          mathAnswered = true;
      }
    }
    else if (showX) {
        drawX();
        if (millis() - xStartTime > checkMarkDuration) {
            showX = false;
            mathAnswered = false;
        }
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

    // What page of the tutorial is the player on?
    if (showTutorial){
      tutorial();
    }
    else if (showRemember){
      remember();
    }
    else if (showMiniTakeOrder){
      miniTakeOrder();
    }
    else if (characterSelected){
      miniTakeOrderpt2();
    }
    else if (onMiniKitchen){
      miniKitchen();
    }
    
    if (showMiniYouWin){
      miniYouWin();
    }

    // If the player won the game...
    if (gameWon){
      youWin();
    }

    // If the palyer loses the game...
    if (numberOfLives == 0 || outOfTime){
      youLose();
    }

    // Show the navigation bar
    image(imgOptions, 800, 390);

  }
  
  /**
   * Loads images needed to run the program
   */
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
    imgnotePad.resize(300, 400);    

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
      drawHearts[i].resize(100, 100);
    }

    // Load game over screen
    gameOverScreen = loadImage("/Images/gameOver.png");
    gameOverScreen.resize(850, 500);

    // Load tutorial screen 
    imgtutorialScreen = loadImage("/Images/tutorial screen.png");
    imgtutorialScreen.resize(850, 500);

    // Load 3
    img3 = loadImage("/Images/3.png");
    img3.resize(200, 210);

    // Load 2
    img2 = loadImage("/Images/2.png");
    img2.resize(200, 210);

    // Load 1
    img1 = loadImage("/Images/1.png");
    img1.resize(200, 210);

    // Load Win Screen
    imgWinScreen = loadImage("/Images/winScreen.png");
    imgWinScreen.resize(850, 500);

    // Load thought bubble 2
    imgThoughtBubble2 = loadImage("/Images/thoughtBubble2.png");
    imgThoughtBubble2.resize(300, 250);

    // Load check mark 
    imgCheckMark = loadImage("/Images/checkMark.png");
    imgCheckMark.resize(150, 150);

    // Load X
    imgX = loadImage("/Images/x.png");
    imgX.resize(150, 150);

    // Load chalk board
    imgChalkBoard = loadImage("/Images/chalkboard.png");
    imgChalkBoard.resize(850, 500);

    // Load Options
    imgOptions = loadImage("/Images/options.png");
    imgOptions.resize(100, 100);

    // Load the reminder screen
    imgRemember = loadImage("/Images/remember.png");
    imgRemember.resize(850, 500);
  }

  /**
   * Loads images (of ingredients) needed to run the program 
   */
  public void loadIngredients(){

    // Load image of pizza crust 
    imgPizzaCrust = loadImage("/Ingredients/Pizza Crust.png");
    imgPizzaCrust.resize(200, 200);

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

  /**
   * Loads images needed to run the program 
   */
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
  
  /**
   * Displays a welcome screen when called upon and utalizes user input
   */
  public void welcomeScreen(){
    background(imgExterior);
    image(imgWelcome, 100, 45);
    image(imgLogo, 150, 95);
    image(imgStart, 150, 235);
    image(imgTutorial, 150, 310);
    image(imgGallery, 150, 385);
    image(imgBy, 50, 470);
  }

  /**
   * Displays an order station when called upon and utalizes user input
   */
  public void takeOrder(){ 
    image(imgOrderStation, 0, 0);
    image(imgCharacter1, 325, 99);
    textSize(30);
    text("Level 1", 355, 300);
    image(imgCharacter2, 480, 80);
    text("Level 2", 525, 300);
    text("Level 3", 715, 300);
    image(imgCharacter3, 680, 85);

    // Draw Notepad 
    textSize(30);
    fill(10);
    text("RECIPE", 10, 100);
    image(imgnotePad, 0, 125);

    // Draw logo
    image(imgLogo, 150, 40);
    
    // Put fruits in the cabinet
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
  
    // If character 2 is picked
    if (Character2picked){
      level2 = true;
      image(imgThoughtBubble2, 250, 20);
      for (int i = 0; i < order2.length; i++){
        image(drawIngredientImages[(int)order2[i]], 310 + i * 50, 35);
      }
    }

    if (Character3picked){
      level3 = true;
      image(imgThoughtBubble2, 450, 20);
      for (int i = 0; i < order1.length; i++){
        image(drawIngredientImages[(int)order1[i]], 510 + i * 50, 35);
      }
    }
  }

  /**
   * Displays a math problem when called upon
   */
  public void keyPad(){
    image(imgChalkBoard, 0, 0);
    textSize(100);
    fill(255, 255, 255);
    text(num1, 240, 200);
    text('x', 300, 200);
    text(num2, 360, 200);
    text('+', 415, 200);
    text(num3, 475, 200);
    fill(0);
    text(userMathInput, 400, 350);
    if (checkMath){
      checkMath();
    }
  }

  /**
   * Verifies that math input from user is equivalent to the computed answer 
   */
  public void checkMath(){
    answer = num1 * num2 + num3;
    String strAnswer = "" + answer;
    if(userMathInput != null && userMathInput.trim().equals(strAnswer.trim())){
      checkMarkStartTime = millis();
      showCheckMark = true;
      showX = false;
      mathAnswered = true;
    }
    else {
      xStartTime = millis();
      showX = true;
      showCheckMark = false;
      mathAnswered = false;
    }
  }

  /**
   * Draws a check mark when called upon for a certain period of time
   */
  public void drawCheckMark(){
    image(imgCheckMark, 550, 250);
  }

  /**
   * Draws an 'x' mark when called upon for a certain period of time
   */
  public void drawX(){
    image(imgX, 550, 250);
  }

  /**
   * Displays a kitchen when called upon and utalizes user input 
   */
  public void kitchen(){
    background(imgKitchen);
    image(imgPizzaCrust, xPizzaValue, 420); 
    
    // Start count down
    if (!countdownFinished && Character1picked || !countdownFinished && Character2picked || !countdownFinished && Character3picked){
      countDown();
    }

    if (Character1picked){
      // Draw the needed ingredients
      fill(161, 159, 159);
      rect(0, 100, 70, 265);
      for (int i = 0 ; i < order1.length; i++){
        fill(135, 132, 122);
        image(drawIngredientImages[(int)order1[i]], 5, 100 + (i * 65));
      }
    }

    if (Character2picked){
      // Draw the needed ingredients
      fill(161, 159, 159);
      rect(0, 100, 70, 265);
      for (int i = 0 ; i < order2.length; i++){
        fill(135, 132, 122);
        image(drawIngredientImages[(int)order2[i]], 5, 100 + (i * 65));
      }
    }

    if (Character3picked){
      // Draw the needed ingredients
      fill(161, 159, 159);
      rect(0, 100, 70, 265);
      for (int i = 0 ; i < order3.length; i++){
        fill(135, 132, 122);
        image(drawIngredientImages[(int)order3[i]], 5, 100 + (i * 65));
      }
    }

    // Should I move the pizza
    if (xPizzaValue < 725 && movePizzaRight){
      xPizzaValue += 3;
      movePizzaRight = true;
    }
    if (xPizzaValue > 5 && movePizzaLeft){
      xPizzaValue -= 3;
      movePizzaLeft = true;
    }

    if (countdownFinished){
    
    // Determine the x-values for all of fruits if they go off screen
    for (int i = 0; i < drawIngredientImages.length; i++){
      if (yValueOfFruits[i] > height){
        yValueOfFruits[i] = 0;
        speedOfFruits[i] =  (float)random(4);
        xValueOfFruits[i] = random(width);
      }
    }

    // Draw the falling furits
    for (int i = 0; i < drawIngredientImages.length; i++){
      yValueOfFruits[i] += speedOfFruits[i];
      image(drawIngredientImages[i], xValueOfFruits[i], yValueOfFruits[i]);
    }

    // Collision detection + draw Stars or minus lives
    for (int i = 0; i < drawIngredientImages.length; i++) {
      if (xValueOfFruits[i] + 30 > xPizzaValue && xValueOfFruits[i] < xPizzaValue + 150 && yValueOfFruits[i] + 70 > 440) {
        if (currentIngredientIndex < order1.length && drawIngredientImages[(int)order1[currentIngredientIndex]] == drawIngredientImages[i]){
          yValueOfFruits[i] = height + 100;
          timeSinceStars = millis();
          drawStars = true;
          currentIngredientIndex++;
        } else {
          yValueOfFruits[i] = height + 100;
          numberOfLives--;
          if (numberOfLives == 0) {
            outOfTime = true;
            youLose();
          }
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

    if (currentIngredientIndex >= order1.length){
      gameWon = true;
      numberOfLives = 5;
    }
    }
  }

  /**
   * Displays a cout down scren (counts down from 3) when called upon 
   */
  public void countDown(){
    if (!countdownStarted){
      startCountDown = millis();
      countdownStarted = true;
    }

    int elpasedTime = millis() - startCountDown;

    if (elpasedTime < 1000){
      image(img3, 325, 200);
    }
    else if (elpasedTime < 2000){
      image(img2, 325, 200);
    }
    else if (elpasedTime < 3000){
      image(img1, 325, 200);
    }
    else {
      // Reset the countdown for later
      countdownStarted = false;
    }

    if (elpasedTime >= 3000){
      countdownFinished = true;
    }

  }
  
  /**
   * Displays the tutorial home scren when called upon
   */
  public void tutorial(){
    if (tutorialPage == 0){
      miniCollision = false;
      ongoingtutorial = true;
      image(imgtutorialScreen, 0, 0);
    }
  }

  /**
   * Displays a take order screen that can only be accessed trough the tutorial 
   */
  public void miniTakeOrder(){
    image(imgOrderStation, 0, 0);
    image(imgCharacter1, 325, 99);
    fill(0);
    textSize(40);

    // Draw Notepad 
    textSize(30);
    fill(10);
    text("RECIPE", 10, 100);
    image(imgnotePad, 0, 125);    

    text("Click on the character", 20, 40);
    
    // Put the ingredients in the cabinets
    for (int i = 0; i < drawIngredientImages.length; i++){
      image(drawIngredientImages[i], fruitinCabinateX[i], fruitinCabinateY[i]);
    } 
  }

  /**
   * Displays another take order screen that can only be accessed through the tutorial
   */
  public void miniTakeOrderpt2(){
    image(imgOrderStation, 0, 0);
    image(imgCharacter1, 325, 99);
    textSize(20);
    text("Drag the ingredients onto the recipe pad and then press k", 20, 40);

    // Draw Notepad 
    textSize(30);
    fill(10);
    text("RECIPE", 10, 100);
    image(imgnotePad, 0, 125);

    // Draw order
    image(imgThoughtBubble, 450, 50);
    for (int i = 0; i < 2; i++){
      image(drawIngredientImages[(int)order1[i]], 505 + i * 50, 85);
    }

    // Put the ingredients in the cabinets
    for (int i = 0; i < drawIngredientImages.length; i++){
      image(drawIngredientImages[i], fruitinCabinateX[i], fruitinCabinateY[i]);
    }     
  }
  
  /**
   * Displays a kitchen screen that can only be accessed through the tutorial
   */
  public void miniKitchen(){
        
    background(imgKitchen);
    image(imgPizzaCrust, xPizzaValue, 420); 
    
    // Draw the needed ingredients
    fill(161, 159, 159);
    rect(0, 100, 70, 265);
    for (int i = 0 ; i < 2; i++){
      fill(135, 132, 122);
      image(drawIngredientImages[(int)order1[i]], 5, 100 + (i * 65));
    }

    // Should I move the pizza
    if (xPizzaValue < 625 && movePizzaRight){
      xPizzaValue += 3;
      movePizzaRight = true;
    }
    if (xPizzaValue > 5 && movePizzaLeft){
      xPizzaValue -= 3;
      movePizzaLeft = true;
    }
    
    // Draw the falling furits
    for (int i = 0; i < drawIngredientImages.length; i++){
      yValueOfFruits[i] += speedOfFruits[i];
      image(drawIngredientImages[i], xValueOfFruits[i], yValueOfFruits[i]);
    }

    // Collision detection + draw Stars or minus lives
    for (int i = 0; i < drawIngredientImages.length; i++) {
      if (xValueOfFruits[i] + 30 > xPizzaValue && xValueOfFruits[i] < xPizzaValue + 150 && yValueOfFruits[i] + 70 > 440) {
          onMiniKitchen = false;
          showMiniYouWin = true;
          break;
      }          
    }

    // Determine the x-values for all of fruits if they go off screen
    for (int i = 0; i < drawIngredientImages.length; i++){
      if (yValueOfFruits[i] > height){
        yValueOfFruits[i] = 0;
        speedOfFruits[i] =  (float)random(4);
        xValueOfFruits[i] = random(width);
      }
    }        
  }

  /**
   * Displays a youWin screen that can only be accessed through the tutoiral 
   */
  public void miniYouWin(){
    image(imgWinScreen, 0, 0);
    textSize(30);
    fill(255, 255, 255);
    text("Once you collect all the listed ingreidents ",25, 30);
    text("you will see this: ",25, 60);
  }

  /**
   * Displays a reminder screen that can only be accessed through the tutorial
   */
  public void remember(){
    image(imgRemember, 0, 0);
  }

  /**
   * Displays the first page of the gallery 
   */
  public void gallery1(){
    image(imgGallery1, 0, 0);
    image(imgLeftOrRight, 400, 440);
  }

  /***
   * Displays the second page of the gallery 
   */
  public void gallery2(){
    image(imgGallery2, 0, 0);
    image(imgLeftOrRight, 400, 440);
  }

  /***
   * Displays the third page of the gallery 
   */
  public void gallery3(){
    image(imgGallery3, 0, 0);
    image(imgLeftOrRight, 400, 440);
  }
  
  /***
   * Displays the amount of remaning lives that the user has 
   */
  public void lives(){
  for (int i = 0; i < numberOfLives; i++) {
        if (drawHearts[i] != null) {
            image(drawHearts[i], 35 + (i * 40), 25);
        }
    }
  }

  /***
   * Displays the amount of timer the player has left to complete a level 
   */
  public void timer1(){
    rect(700, 20, 120, 20);
    fill(113, 171, 139);
    rect(700, 20, rectLength, 20);
    fill(0, 0, 0);
    
    elpasedTime = (millis() - startTimer1) / 1000;
    rectLength = 120 - elpasedTime;
  
    if (rectLength <= 0) {
      outOfTime = true;
      rectLength = 0;
      youLose();
    }
  }

  /***
   * Displays a 'you lose' screen if the player runs out of timer or loses all of their lives 
   */
  public void youLose(){
    image(gameOverScreen, 0, 0);
  }

  /***
   * Displays a you win screen if the user successful completes a level
   */
  public void youWin(){
    image(imgWinScreen, 0, 0);;
  } 

  /***
   * 
   * Ensures that ingredients requested by the character are not duplicated
   * 
   * @param ingredients Intakes the ingredient requested
   * @return if the ingredient is already called upon or not
   */
  public boolean isInThe1stArray(float ingredients){
    for (int i = 0; i < order1.length; i++){
      if (order1[i] == ingredients){
        return true;
      }
    }
    return false;
  }
  
  /***
   * 
   * Ensures that ingredients requested by the character are not duplicated
   * 
   * @param ingredients Intakes the ingredient requested
   * @return if the ingredient is already called upon or not
   */
  public boolean isInThe2ndArray(float ingredients){
    for (int i = 0; i < order1.length; i++){
      if (order2[i] == ingredients){
        return true;
      }
    }
    return false;
    }

    /***
   * 
   * Ensures that ingredients requested by the character are not duplicated
   * 
   * @param ingredients Intakes the ingredient requested
   * @return if the ingredient is already called upon or not
   */
  public boolean isInThe3rdArray(float ingredients){
    for (int i = 0; i < order1.length; i++){
      if (order2[i] == ingredients){
        return true;
      }
    }
    return false;
    }
  
  /**
   * Does a certain funciton of the mouse is dragged
   */
  public void mouseDragged(){
    for (int i = 0; i < drawIngredientImages.length; i++){
      if (mouseX >= fruitinCabinateX[i] && mouseX <= fruitinCabinateX[i] + 55 && mouseY >= fruitinCabinateY[i] && mouseY <= fruitinCabinateY[i] + 70){
        fruitinCabinateX[i] += mouseX - pmouseX;
        fruitinCabinateY[i] += mouseY - pmouseY;
      }
    }
  }

  /**
   * Does a certain function/action if a certain part of the screen is clicked on
   */
  public void mouseClicked(){
    // Was start button pressed?
    if (mouseX >= 150 && mouseX <= 400 && mouseY >= 225 && mouseY <= 300){
      displayTakeOrder = true;
    }

    // Was the first Character selected?
    if (mouseX >= 325 && mouseX <= 450 && mouseY >= 99 && mouseY <= 265){
      startTimer1 = millis();
      displayWelcomeScreen = false;
      Character1picked = true;
    }

    // Was the second chracter selected?
    if (mouseX >= 480 && mouseX <= 650 && mouseY >= 95 && mouseY <= 265){
      startTimer1 = millis();
      displayWelcomeScreen = false;
      Character2picked = true;
    }

    // Was the third character selected?
    if (mouseX >= 700 && mouseX <= 850 && mouseY >= 99 && mouseY <= 265){
      startTimer1 = millis();
      displayWelcomeScreen = false;
      Character3picked = true;
    }

    // Was the gallery button pressed?
    if (mouseX >= 150 && mouseX <= 400 && mouseY >= 380 && mouseY <= 480){
      galleryPage = 1;
    }

    // Which gallery screen should be shown? - going to the right
    if (mouseX >= 423 && mouseX <= 460 && mouseY >= 440 && mouseY <= 490) {
      if (galleryPage == 1) {
        galleryPage = 2;
      } else if (galleryPage == 2) {
        galleryPage = 3;
      }
    }

    // Which gallery screen should be shown? - going to the left
    if (mouseX >= 370 && mouseX <= 418 && mouseY >= 440 && mouseY <= 490) {
      if (galleryPage == 2) {
        galleryPage = 1;
      } else if (galleryPage == 3) {
        galleryPage = 2;
      }
    }

    // Was the tutorial selected
    if (mouseX > 150 && mouseX <= 400 && mouseY >= 310 && mouseY <= 370){
      showTutorial = true;
      tutorial();
    }

    // Was the first Character selected on the tutorial screen?
    if (mouseX >= 325 && mouseX <= 450 && mouseY >= 99 && mouseY <= 265 && ongoingtutorial){
    tutorialPage = 0;
    showMiniTakeOrder = false;
    characterSelected = true;
    }

    // Collision for pause screen on options image
    if (mouseX >= 800 && mouseY >= 390 && mouseY <= 423) {
      pauseScreenStartTime = millis();
      if (millis() <= pauseScreenStartTime + 6000){
        delay(3000);
      }
    }    

    // Collision for home screen on options image
    if (mouseX >= 800 && mouseY >= 424 && mouseY <= 460) {
      displayWelcomeScreen = true;
      characterSelected = false;
      Character1picked = false;
      Character2picked = false;
      Character3picked = false;
      displayTakeOrder = false;
      dipslayKitchen = false;
      showTutorial = false;
      showMiniTakeOrder = false;
      onMiniKitchen = false;
      miniCollision = false;
      drawStars = false;
      mathAnswered = true;
      elpasedTime = 0;
      showX = false;
      showCheckMark = false;
      mathAnswered = false;
      rectLength = 120;
      galleryPage = 0;
      level1 = false;
      level2 = false;
      level3 = false;
      gameWon = false;
      showMiniYouWin = false;
      tutorialPage = 0;
      numberOfLives = 5; 
      outOfTime = false;
      rectLength = 120;
    }

    // Collision for take order on optioons image
    if (mouseX >= 800 && mouseY >= 465 && mouseY <= height) {
      displayTakeOrder = true;
      displayWelcomeScreen = true;
      characterSelected = false;
      Character1picked = false;
      Character2picked = false;
      Character3picked = false;
      dipslayKitchen = false;
      showMiniTakeOrder = false;
      showTutorial = false;
      showMiniYouWin = false;
      miniCollision  = false;
      onMiniKitchen = false;
      mathAnswered = true;
      mathAnswered = false;
      gameWon = false;
      miniCollision = false;
      drawStars = false;
      elpasedTime = 0;
      rectLength = 120;
      galleryPage = 0;
      showX = false;
      showCheckMark = false;
      level1 = false;
      level2 = false;
      level3 = false;
      numberOfLives = 5;
      tutorialPage = 0;
      if (numberOfLives == 0 || outOfTime){
        numberOfLives = 5; 
        outOfTime = false;
        rectLength = 120;
      }
    }
    
   }   

   /**
    * Does a certain function/action if a certain key is clicked on
    */
  public void keyPressed(){
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
    if (keyCode == RIGHT) {
      if (galleryPage == 1) {
        galleryPage = 2;
      } else if (galleryPage == 2) {
        galleryPage = 3;
      }
    } else if (keyCode == LEFT) {
      if (galleryPage == 2) {
        galleryPage = 1;
      } else if (galleryPage == 3) {
        galleryPage = 2;
      }
    }

    // User input for math question
    if (key == BACKSPACE && userMathInput.length() > 0){
      userMathInput = "";
    }

    if (key == ENTER && !mathAnswered){
      checkMath = true;
    }

    if (showTutorial && keyCode == UP){
      showTutorial = false;
      showRemember = true;
      showMiniTakeOrder = false;
    }
    else if (showRemember && keyCode == UP){
      showRemember = false;
      showMiniTakeOrder = true;
    }

    // move pizza on miniKitchen 
    if (onMiniKitchen && key == RIGHT){
      xPizzaValue++;
    }
    if (onMiniKitchen && key == LEFT){
      xPizzaValue--;
    }

    if (key == 'k' && characterSelected) {
      characterSelected = false;
      onMiniKitchen = true;
    }
  }

  /**
   * Stops a certain function/action from continuing if a certain key is clicked on 
   */
  public void keyReleased(){
    if (keyCode == RIGHT){
      movePizzaRight = false;
    }
    if (keyCode == LEFT){
      movePizzaLeft = false;
    }
  }

  /**
   * Takes in a string inputted by the usser
   */
  public void keyTyped(){
    if (key != CODED && !mathAnswered){
      userMathInput += key;
    }
  }

}