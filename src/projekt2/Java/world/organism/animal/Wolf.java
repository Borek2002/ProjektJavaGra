package projekt2.Java.world.organism.animal;

import projekt2.Java.world.World;
import projekt2.Java.world.organism.Animal;
import projekt2.Java.world.organism.Organism;

import java.awt.*;

public class Wolf extends Animal {
    public Wolf(World world, Point position) {
        super(world, position, 9, 5, 1, 1, true);
    }
    public Wolf(World world, Point position,int age,int strenght) {
        super(world, position, strenght, 5, age, 1, true);
    }
    public void paint(Graphics g) {
        g.setColor(new Color(112,112,112));
        g.fillRect(position.x* world.getScale(), position.y* world.getScale(), world.getScale(), world.getScale());
    }

    @Override
    public String getName() {
        return "Wolf";
    }

    @Override
    public Organism clone(Point p) {
        return new Wolf(world,p);
    }
}
