package projekt2.Java.world.organism.plant;

import projekt2.Java.world.World;
import projekt2.Java.world.organism.Organism;
import projekt2.Java.world.organism.Plant;

import java.awt.*;

public class Guarana extends Plant {
    public Guarana(World world, Point position) {
        super(world, position,
                0, 0, 1, 1, true);
    }
    public Guarana(World world, Point position,int age,int strenght) {
        super(world, position,
                strenght, 0, age, 1, true);
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(235,77,37));
        g.fillRect(position.x* world.getScale(), position.y* world.getScale(), world.getScale(), world.getScale());
    }

    @Override
    public String getName() {
        return "Guarana";
    }

    @Override
    public Organism clone(Point p) {
        return new Guarana(world,p);
    }
}
