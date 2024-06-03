import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
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

  // Which customer was pciked?
  boolean Character1picked = false;
  boolean Character2picked = false;
  boolean Character3picked = false;

  // Which screen is being shown?
  boolean displayPauseScreen = false;
	
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

    loadImages();
    //loadIngredients();
    
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
    /*
    if (displayPauseScreen){
      pauseScreen();
    }
    */

    welcomeScreen();

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
  }

  public void loadIngredients(){
    // Load image of pizza crust 
    imgPizzaCrust = loadImage("/Ingredients/Pizza Crust.png");
    imgPizzaCrust.resize(110, 30);
    
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
    imgPepers = loadImage("/Ingredients/pepers.png");
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
    image(imgCheese, 285, 365);

    // Which character was selected?
    if (Character1picked){
      // DO THIS
    }
  }

  public void mouseDragged(){
    // Drag ingredients into the notepad
  }

  public void mouseClicked(){
    // Was start button pressed?
    if (mouseX >= 150 && mouseX <= 400 && mouseY >= 225 && mouseY <= 300){
      intScreen = 1;
    }
    if (mouseX >= 325 && mouseX <= 405){
      Character1picked = true;
    }
  }

  public void keyPressed(){
    if (key == 'x'){
      displayPauseScreen = true;
    }
  }

  public void kitchen(){

  }

}