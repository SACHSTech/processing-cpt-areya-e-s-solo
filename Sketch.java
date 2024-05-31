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
  PImage imgCheese;
  int intScreen = 0;

  // Which customer was pciked?
  boolean Character1picked = false;
  boolean Character2picked = false;
  boolean Character3picked = false;
	
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
    */

    takeOrder();
  }

  public void loadImages(){
    
    // Load Exterior of Dinner
    imgExterior = loadImage("/Images/Diner_exteriorFull.png");
    // Resize to fit screen
    imgExterior.resize(width, height);

    // Load welcome sign
    imgWelcome = loadImage("/Images/Welcome to.png");
    // Resize to fit screen
    imgWelcome.resize(300, 200);

    // Load logo 
    imgLogo = loadImage("/Images/PizzaliciousLogo.png");
    // Resize to fit screen 
    imgLogo.resize(200, 130);

    // Load Start Button
    imgStart = loadImage("/Images/Start_button.png");
    // Resize to fit screen
    imgStart.resize(250, 150);

    // Load tutorial button
    imgTutorial = loadImage("/Images/tutorial button.png");
    // Resize tutorial button 
    imgTutorial.resize(200, 100);    

    // Load Gallery button
    imgGallery = loadImage("/Images/Gallery_button.png");
    // Resize to fit screen
    imgGallery.resize(200, 100);

    // Load by Areya E-S
    imgBy = loadImage("/Images/byAES.png");
    // Resize to fit screen
    imgBy.resize(200, 100);

    // Load order station
    imgOrderStation = loadImage("/Images/orderStation.png");
    // Resize to fit screen
    imgOrderStation.resize(width, height);

    // Load the first character
    imgCharacter1 = loadImage("/Images/Character1.png");
    // Resize to fit screen
    imgCharacter1.resize(300, 400);

    // Load Second character
    imgCharacter2 = loadImage("/Images/Character2.png");
    // Resize to fit screen
    imgCharacter2.resize(300, 405);

    // Load third Character
    imgCharacter3 = loadImage("/Images/Character3.png");
    // Resize to fit screen 
    imgCharacter3.resize(300, 400);

    // Load image of notepad 
    imgnotePad = loadImage("/Images/notePad.png");
    // Resize to fit screen
    imgnotePad.resize(400, 500);

    // Load image of chesse
    imgCheese = loadImage("/Images/Cheese.png");
    // Resize to fit screen 
    imgCheese.resize(60, 70);
    
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

  public void mouseClicked(){
    // Was start button pressed?
    if (mouseX >= 150 && mouseX <= 400 && mouseY >= 225 && mouseY <= 325){
      intScreen = 1;
    }
    if (mouseX >= 325 && mouseX <= 405){
      Character1picked = true;
      System.out.println("HELLO");
    }
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
}