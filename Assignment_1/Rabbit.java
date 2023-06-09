package Assignments.Assignment_1;

public class Rabbit extends Animal {
    private boolean CanSeeFox = false; 
    private int distanceToFox;
    private int directionToFox;
    private int currentDirection;


    public Rabbit(Model model, int row, int column) {
        super(model, row, column);
    }
    
  int decideMove() {
    //Look for Fox in all Directions
    for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) {
      if (look(i) == Model.FOX) 
      {
        if(canMove(Model.turn(i, 5)))
        {
          return Model.turn(i, 5);
        }
        else if(canMove(Model.turn(i, 3))){
          return Model.turn(i, 3);
        }
        else if(canMove(Model.turn(i, 7))){
          return Model.turn(i, 7);
        }
        else if (canMove(Model.turn(i, 1))) {
          return Model.turn(i, 1);
        }
        else if (canMove(Model.turn(i, 2))) {
          return Model.turn(i, 2);
        }
        else if (canMove(Model.turn(i, 6))) {
          return Model.turn(i, 6);
        }
        else if(canMove(Model.turn(i, 0))){
          return Model.turn(i, 0);
        }
        else if(canMove(Model.turn(i, 4))){
          return Model.turn(i, 4);
        }
      }
    }
      return Model.STAY;
  }
}
  
  

