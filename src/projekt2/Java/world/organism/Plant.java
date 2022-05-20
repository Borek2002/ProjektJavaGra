package projekt2.Java.world.organism;

import projekt2.Java.world.Comment;
import projekt2.Java.world.World;

import java.awt.*;
import java.util.Random;

public abstract class Plant extends Organism{

    public Plant(World world, Point position, int strenght, int initiative, int age, int range, boolean newBorn) {
        super(world, position, strenght, initiative, age, range, newBorn);
    }

    public void action() {
        this.age++;
        Point p;
        int chance;
        Random r = new Random();
        chance=r.nextInt(10);
        if (chance==1) {
        for (int i=0;i<this.getRange();i++){
            p=newPosition();
            if (world.getBoard()[(int) p.getY()][(int) p.getX()]==null){
                world.place(clone(p),p);
                Comment.AddComment("A new "+this.getName()+" was born");
                }
            }
        }
    }

    public void collision(Organism occupied) {

    }


}
