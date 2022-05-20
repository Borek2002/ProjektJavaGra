package projekt2.Java.world.organism.animal;

import projekt2.Java.world.World;
import projekt2.Java.world.organism.Animal;
import projekt2.Java.world.organism.Organism;

import java.awt.*;

public class Sheep extends Animal {
    public Sheep(World world, Point position) {
        super(world, position, 4, 4,1, 1, true);}
    public Sheep(World world, Point position,int age,int strenght) {
        super(world, position, strenght, 4,age, 1, true);}
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(250,250,250));
        g.fillRect(position.x* world.getScale(), position.y* world.getScale(), world.getScale(), world.getScale());
    }

    @Override
    public String getName() {
        return "Sheep";
    }

    @Override
    public Organism clone(Point p) {
        return new Sheep(world,p);
    }


}
