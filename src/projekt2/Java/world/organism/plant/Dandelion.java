package projekt2.Java.world.organism.plant;

import projekt2.Java.world.World;
import projekt2.Java.world.organism.Organism;
import projekt2.Java.world.organism.Plant;

import java.awt.*;

public class Dandelion extends Plant {
    public Dandelion(World world, Point position) {
        super(world, position,
                0, 0, 1, 3, true);
    }
    public Dandelion(World world, Point position,int age,int strenght) {
        super(world, position,
                strenght, 0, age, 3, true);
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(255,252,54));
        g.fillRect(position.x* world.getScale(), position.y* world.getScale(), world.getScale(), world.getScale());
    }

    @Override
    public String getName() {
        return "Dandelion";
    }

    @Override
    public Organism clone(Point p) {
        return new Dandelion(world,p);
    }
}
