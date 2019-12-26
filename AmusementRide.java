import java.util.*;

/*
 * AmusementRide is a generalization of ride objects
 * found at amusement parks.
 * 
 * Note: The non-public fields for this class are 
 * defined at the bottom of the class.
 *
 * @creator John Cody
 * @created 02019.02.27
 */

public abstract class AmusementRide extends Object {

   public final static String DFLT_NAME = "Ride With No Name";
   public final static double DFLT_PRICE = 0.0;
   public final static int DFLT_CAPACITY = 0;

   /**
    * Construct an AmusementRide object.
    *
    * @param n the name of the AmusementRide
    * @param p the price of the AmusementRide
    * @param c the capacity of the AmusementRide
    */
   public AmusementRide(String n, double p, int c) {
      name = (n == null) ? DFLT_NAME : n;
      price = (p < 0) ? DFLT_PRICE : p;
      capacity = (c < 0) ? DFLT_CAPACITY : c;
   }

   public abstract boolean start(); //Prints a message indicating 
   //that a ride has started - include the ride's name & return true

   public abstract void stop(); //Prints a message indicating 
   //that a ride has stopped - include the ride's name
  
   public abstract int load(); //Prints a message indicating that this
   // ride has been loaded - include the ride name & return ride capacity

   public void repair() { //Prints a message indicating that this 
      // ride has been repaired - include the ride's name
      System.out.println("mechanics are on strike"); 
   }


   /**
    * Set the capacity of this AmusementRide.
    *
    * @param maxload  maximum number of riders allowed
    * @return this AmusementRide
    */
   public AmusementRide setCapacity(int maxload) { 
      capacity = (maxload < 0) ? DFLT_CAPACITY : maxload;
      return this; 
   }

   /**
    * Set the price of this AmusementRide.
    *
    * @param price  is USD
    */
   public AmusementRide setPrice(double p) { 
      price = (p < 0) ? DFLT_PRICE : p;
      return this; 
   }

   /**
    * construct a String representation of this ride...
    *
    * @return a String representation of this AmusementRide
    */
   public String toString() {
      return "\"" + name + "\" has capacity of " + capacity + 
             " and costs $" + price;
   }

   /**
    * main() method used has a driver program to test
    * the methods defined in this class...
    */
   public static void main(String[] argv) {

      FerrisWheel f = new FerrisWheel("The Billy Preston", 50, 6.28, 100);
      RollerCoaster r = new RollerCoaster("for(;;) Young", 32, 3.14,
                                          25, 99, 1.618);
      GoKart g = new GoKart("Go Kart Raceway", 24, 4.85, 12);
      
      AmusementRide[] rides = new AmusementRide[3];
      rides[0] = f;
      rides[1] = r;
      rides[2] = g;
      
      for(int i = 0; i < rides.length; i++) {
          System.out.println(rides[i]); 
          rides[i].load();
          if(rides[i].start()) 
              rides[i].stop();
          rides[i].repair();
      }
   }

   
   /*
    * The instance data for an AmusementRide.
    */
   protected int capacity; 
   protected String name; 
   private double price; 
   private Date lastRepair = new Date();
}

class GoKart extends AmusementRide {
    
    public GoKart (String name, int capacity, double price, int numKarts) {
        super(name, price, capacity);
        if (numKarts <= 0)
            throw new IllegalArgumentException("invalid number of karts");
        this.numKarts = numKarts;
        this.BaseString = "The ride " + "\"" + name + "\"";
    }
    public String toString() {
        return "Go Kart Track " + super.toString() + "\n" 
                + "...# of karts: " + numKarts;
    }
    public boolean start() { 
        System.out.println(BaseString + " has started.");
        return true; 
    }
    public void stop() { 
        System.out.println(BaseString + " has stopped.");
    }
    public int load() { 
        System.out.println(BaseString + " has been loaded.");
        return capacity; 
    }
    public void repair() {
        System.out.println(BaseString + " has been repaired.");
    }
    
    String BaseString;
    //Default state value
    private final static int DFLT_NUM_KARTS = 0;
    //Instance variable
    private int numKarts = DFLT_NUM_KARTS; 
}

class FerrisWheel extends AmusementRide {

   /**
    * initialize this object using client supplied data...
    */
   public FerrisWheel(String name, int capacity, double price, int height) {
      super(name, price, capacity);
      if (height <= 0)
         throw new IllegalArgumentException("invalid height");
      this.height = height;
   }

   /**
    * Return a string representation of this object including
    * the state of the AmusementRide part of the object.
    */
   public String toString() {
      return "Ferris wheel " + super.toString() + "\n" +
             "...height: " + height + "; # of spins: " + nSpins;
   }

   /**
    * Spin this ferris wheel once. -done
    */
   public void spin() { nSpins++; }

   public boolean start() { return true; }
   public void stop() { return; }
   public int load() { return capacity; }

   /*
    * default state values...
    */
   private final static int DFLT_MAX_SPINS = 100;
   private final static int DFLT_HEIGHT = -1;

   /*
    * instance variables...
    */
   private int nSpins = 0;
   private int height = DFLT_HEIGHT;    //in feet

}

class RollerCoaster extends AmusementRide {

   public RollerCoaster(String name, int capacity, double price,
                        int len, int speed, double factor) {
      super(name, price, capacity);
      init(len, speed, factor);
   }

   private void init(int len, int speed, double factor) {
      if (len < 0)
         throw new IllegalArgumentException("invalid length");
      trackLength = len;
      if (!setMaxSpeed(speed))
         throw new IllegalArgumentException("invalid speed");
      if (factor < 0)
         throw new IllegalArgumentException("invalid factor");
      whiplashFactor = factor;
   }

   /**
    * maxSpeed is not changed if parameter speed is negative
    *
    * @param speed  new speed
    * @return true  if speed changed; false otherwise
    */
   public boolean setMaxSpeed(int speed) {
      if (speed < 0)
         return false;
      maxSpeed = speed;
      return true;
   }

   /**
    * Return a string representation of this object including
    * the state of the AmusementRide part of the object.
    */
   public String toString() {
      return "Roller coaster " + super.toString() + "\n" +
             "...maxSpeed: " + maxSpeed + 
             "; trackLength: " + trackLength + 
             "; whiplashFactor: " + whiplashFactor;
   }

   public boolean start() { return true; }
   public void stop() { return; }
   public int load() { return capacity; }
      
   /*
    * default state values...
    */
   private final static int DFLT_TRACK_LENGTH = -1;
   private final static int DFLT_MAX_SPEED = 0;
   private final static double DFLT_WHIPLASH_FACTOR = 0.0;

   /*
    * instance variables...
    */
   private int trackLength = DFLT_TRACK_LENGTH;             //in feet
   private int maxSpeed = DFLT_MAX_SPEED;                   //in knots
   private double whiplashFactor = DFLT_WHIPLASH_FACTOR;    //gforces

}