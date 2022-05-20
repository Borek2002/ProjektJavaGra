package projekt2.Java.world.organism.animal;

import projekt2.Java.world.Comment;
import projekt2.Java.world.World;
import projekt2.Java.world.organism.Animal;
import projekt2.Java.world.organism.Organism;

import java.awt.*;

public class Fox extends Animal {
   public Fox(World world, Point position) {
        super(world, position, 3, 7, 1, 1, true);
    }
   public Fox(World world, Point position,int age,int strenght) {
        super(world, position, strenght, 7, age, 1, true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(224,125,71));
        g.fillRect(position.x*world.getScale(), position.y*world.getScale(), world.getScale(), world.getScale());
    }

    @Override
    public String getName() {
        return "Fox";
    }

    @Override
    public Organism clone(Point p) {
        return new Fox(world,p);
    }

    @Override
    public void action() {
        age++;
        Point p;
        while(true){
            p=newPosition();
            if (world.getBoard()[(int) p.getY()][(int) p.getX()]==null){
                Comment.AddComment(this.getName()+" move from (x: "+this.position.getX()+", y: "+this.position.getY()+") to (x: "+p.getX()+", y: "+p.getY()+")");
                makeMove(p);
                break;
            }
            else if(world.getBoard()[(int) p.getY()][(int) p.getX()].getStrenght()<=this.getStrenght()){
                collision(world.getBoard()[(int)p.getY()][(int)p.getX()]);
                break;
            }

        }
    }
}
