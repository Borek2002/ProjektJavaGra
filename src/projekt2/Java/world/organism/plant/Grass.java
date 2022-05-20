package projekt2.Java.world.organism.plant;

import projekt2.Java.world.World;
import projekt2.Java.world.organism.Organism;
import projekt2.Java.world.organism.Plant;

import java.awt.*;

public class Grass extends Plant {
    public Grass(World world, Point position) {
        super(world, position, 0, 0, 1, 1, true);
    }
    public Grass(World world, Point position,int age,int streght) {
        super(world, position, streght, 0, age, 1, true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(63,222,30));
        g.fillRect(position.x* world.getScale(), position.y* world.getScale(), world.getScale(), world.getScale());
    }

    @Override
    public String getName() {
        return "Grass";
    }

    @Override
    public Organism clone(Point p) {
        return new Grass(world,p);
    }
}
