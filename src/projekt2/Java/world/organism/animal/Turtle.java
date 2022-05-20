package projekt2.Java.world.organism.animal;

import projekt2.Java.world.World;
import projekt2.Java.world.organism.Animal;
import projekt2.Java.world.organism.Organism;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal {
    public Turtle(World world, Point position) {
        super(world, position, 2, 1, 1, 1, true);
    }
    public Turtle(World world, Point position,int age,int strenght) {
        super(world, position, strenght, 1, age, 1, true);
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(35,115,13));
        g.fillRect(position.x* world.getScale(), position.y* world.getScale(), world.getScale(), world.getScale());
    }

    @Override
    public String getName() {
        return "Turtle";
    }

    @Override
    public Organism clone(Point p) {
        return new Turtle(world,p);
    }

    @Override
    public void action() {
        int chance;
        Random r = new Random();
        chance=r.nextInt(4);
        if (chance==1) {
            super.action();
        }
        else{
            age++;
        }
    }
}
