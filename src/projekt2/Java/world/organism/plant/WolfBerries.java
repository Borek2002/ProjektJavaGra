package projekt2.Java.world.organism.plant;

import projekt2.Java.world.World;
import projekt2.Java.world.organism.Organism;
import projekt2.Java.world.organism.Plant;

import java.awt.*;

public class WolfBerries extends Plant {
    public WolfBerries(World world, Point position) {
        super(world, position,
                99, 0, 1, 1, true);
    }
    public WolfBerries(World world, Point position,int age,int strenght) {
        super(world, position,
                strenght, 0, age, 1, true);
    }
    @Override
    public String getName() {
        return "Berries";
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(153,36,166));
        g.fillRect(position.x* world.getScale(), position.y* world.getScale(), world.getScale(), world.getScale());
    }

    @Override
    public Organism clone(Point p) {
        return new WolfBerries(world,p);
    }
}
