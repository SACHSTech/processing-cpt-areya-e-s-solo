import processing.core.PApplet;
import processing.core.PImage;

public class Sketchcopy extends PApplet {

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

  // Which customer was picked?
  boolean Character1picked = false;
  boolean Character2picked = false;
  boolean Character3picked = false;

  // Which screen is being shown?
  boolean displayPauseScreen = false;

  public void settings() {
    size(850, 500);
  }

  public void setup() {
    background(0);
    loadImages();
    //loadIngredients();
  }

  public void draw() {
    background(0);
    
    if(intScreen == 0){
      welcomeScreen();
      println("welcome screen");
    }
    if (intScreen == 1){
      takeOrder();
      println("Take order");
    }
    
    if (displayPauseScreen){
      pauseScreen();
    }
  }

  public void loadImages() {
    imgExterior = loadImage("Diner_exteriorFull.png");
    checkImageLoaded(imgExterior, "Diner_exteriorFull.png");

    imgWelcome = loadImage("Welcome to.png");
    checkImageLoaded(imgWelcome, "Welcome to.png");

    imgLogo = loadImage("PizzaliciousLogo.png");
    checkImageLoaded(imgLogo, "PizzaliciousLogo.png");

    imgStart = loadImage("Start_button.png");
    checkImageLoaded(imgStart, "Start_button.png");

    imgTutorial = loadImage("tutorial button.png");
    checkImageLoaded(imgTutorial, "tutorial button.png");

    imgGallery = loadImage("Gallery_button.png");
    checkImageLoaded(imgGallery, "Gallery_button.png");

    imgBy = loadImage("byAES.png");
    checkImageLoaded(imgBy, "byAES.png");

    imgPaused = loadImage("paused screen.png");
    checkImageLoaded(imgPaused, "paused screen.png");

    imgOrderStation = loadImage("orderStation.png");
    checkImageLoaded(imgOrderStation, "orderStation.png");

    imgCharacter1 = loadImage("Character1.png");
    checkImageLoaded(imgCharacter1, "Character1.png");

    imgCharacter2 = loadImage("Character2.png");
    checkImageLoaded(imgCharacter2, "Character2.png");

    imgCharacter3 = loadImage("Character3.png");
    checkImageLoaded(imgCharacter3, "Character3.png");

    imgnotePad = loadImage("notePad.png");
    checkImageLoaded(imgnotePad, "notePad.png");
  }

  public void checkImageLoaded(PImage img, String filename) {
    if (img == null) {
      println("Error loading image: " + filename);
    }
  }

  public void loadIngredients(){
    imgPizzaCrust = loadImage("Pizza Crust.png");
    imgPizzaCrust.resize(110, 30);
    
    imgCheese = loadImage("Cheese.png");
    imgCheese.resize(60, 70);
    
    imgBasil = loadImage("Basil.png");
    imgBasil.resize(60, 70);
    
    imgMushroom = loadImage("Mushroom.png");
    imgMushroom.resize(60, 70);

    imgOlives = loadImage("Olives.png");
    imgOlives.resize(60, 70);

    imgPepers = loadImage("pepers.png");
    imgPepers.resize(60, 70);

    imgPepperoni = loadImage("Pepperoni.png");
    imgPepperoni.resize(60, 70);

    imgPineapple = loadImage("Pineapple.png");
    imgPineapple.resize(60, 70);

    imgSause = loadImage("Sause.png");
    imgSause.resize(60, 70);
  }

  public void welcomeScreen(){
    image(imgExterior, 0, 0, width, height);
    image(imgWelcome, 100, 45);
    image(imgLogo, 150, 95);
    image(imgStart, 150, 225);
    image(imgTutorial, 150, 310);
    image(imgGallery, 150, 385);
    image(imgBy, 50, 470);
  }

  public void pauseScreen(){
    image(imgExterior, 0, 0, width, height);
    image(imgLogo, 150, 80);
    image(imgPaused, 140, 220);
    // DO COLLISION
  }

  public void takeOrder(){
    // Add null checks before using images
    if (imgOrderStation != null) {
      image(imgOrderStation, 0, 0, width, height);
    }
    if (imgCharacter1 != null) {
      image(imgCharacter1, 325, 99);
    }
    if (imgCharacter2 != null) {
      image(imgCharacter2, 480, 80);
    }
    if (imgCharacter3 != null) {
      image(imgCharacter3, 680, 85);
    }
    if (imgnotePad != null) {
      if (mouseX >= 800 && mouseY >= 150){
        image(imgnotePad, 520, 125);
      } else {
        image(imgnotePad, 730, 125);
      }
    }
    if (imgCheese != null) {
      image(imgCheese, 285, 365);
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

    // Was the first Character clicked?
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
