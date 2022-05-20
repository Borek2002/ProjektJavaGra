package projekt2.Java.world.organism.animal;

import projekt2.Java.world.Comment;
import projekt2.Java.world.World;
import projekt2.Java.world.organism.Animal;
import projekt2.Java.world.organism.Organism;

import java.awt.*;
import java.util.Random;

public class Antylope extends Animal {
    public Antylope(World world, Point position) {
        super(world, position, 4, 4, 1, 2, true);
    }
    public Antylope(World world, Point position,int age,int strenght) {
        super(world, position, strenght, 4, age, 2, true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(255,138,110));
        g.fillRect(position.x* world.getScale(), position.y* world.getScale(), world.getScale(), world.getScale());
    }

    @Override
    public String getName() {
        return "Antylope";
    }

    @Override
    public Organism clone(Point p) {
        return new Antylope(world,p);
    }

    @Override
    public void collision(Organism occupied) {
        int chance;
        Point p;
        Random r = new Random();
        chance=r.nextInt(2);
        if (chance==0){
            while(true){
                p=newPosition();
                if (world.getBoard()[(int) p.getY()][(int) p.getX()]==null){
                    Comment.AddComment(this.getName()+" run from "+occupied.getName());
                    makeMove(p);
                    break;
                }
                else if(world.getBoard()[(int) p.getY()][(int) p.getX()].getStrenght()<=this.getStrenght()){
                    super.collision(occupied);
                    break;
                }
            }

        }
    }
}
