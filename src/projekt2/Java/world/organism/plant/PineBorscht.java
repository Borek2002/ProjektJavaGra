package projekt2.Java.world.organism.plant;

import projekt2.Java.world.Comment;
import projekt2.Java.world.World;
import projekt2.Java.world.organism.Animal;
import projekt2.Java.world.organism.Organism;
import projekt2.Java.world.organism.Plant;

import java.awt.*;

public class PineBorscht extends Plant {
    public PineBorscht(World world, Point position) {
        super(world, position,
                10, 0, 1, 1, true);
    }
    public PineBorscht(World world, Point position,int age,int strenght) {
        super(world, position,
                strenght, 0, strenght, 1, true);
    }

    @Override
    public String getName() {
        return "Borscht";
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(30,16,189));
        g.fillRect(position.x* world.getScale(), position.y* world.getScale(), world.getScale(), world.getScale());
    }

    @Override
    public Organism clone(Point p) {
        return new PineBorscht(world,p);
    }

    @Override
    public void action() {
        int x= (int) this.getPosition().getX();
        int y= (int) this.getPosition().getY();
        if (y>0){
            if ( world.getBoard()[y-1][x] instanceof Animal){
                Comment.AddComment("Pine Borscht kill "+world.getBoard()[y-1][x].getName());
                world.removeOrganism(world.getBoard()[y-1][x]);
            }
        }
        if (y<world.getHeight()-1){
            if ( world.getBoard()[y+1][x] instanceof Animal){
                Comment.AddComment("Pine Borscht kill "+world.getBoard()[y+1][x].getName());
                world.removeOrganism(world.getBoard()[y+1][x]);
            }
        }
        if (x>0){
            if ( world.getBoard()[y][x-1] instanceof Animal){
                Comment.AddComment("Pine Borscht kill "+world.getBoard()[y][x-1].getName());
                world.removeOrganism(world.getBoard()[y][x-1]);
            }
        }
        if (x< world.getWidth()-1){
            if ( world.getBoard()[y][x+1] instanceof Animal){
                Comment.AddComment("Pine Borscht kill "+world.getBoard()[y][x+1].getName());
                world.removeOrganism(world.getBoard()[y][x+1]);
            }
        }
        super.action();
    }
}
