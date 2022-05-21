package projekt2.Java.world.organism.animal;

import projekt2.Java.world.Comment;
import projekt2.Java.world.World;
import projekt2.Java.world.organism.Animal;
import projekt2.Java.world.organism.Organism;

import java.awt.*;
import java.util.Random;

public class Human extends Animal {
    private int tab[];
    private Point newPosition;
    public Human(World world, Point position) {
        super(world, position, 5, 4, 1, 1, true);
        tab=new int[2];
        tab[0]=0;
        tab[1]=0;
    }
    public Human(World world, Point position,int age,int strenght) {
        super(world, position, strenght, 4, age, 1, true);
        tab=new int[2];
        tab[0]=0;
        tab[1]=0;
    }

    @Override
    public void action() {
        this.age++;
        specialAbility();
        Point p=new Point();
        p.setLocation(position);
        for (int i=0;i<this.getRange();i++){
            p.x+=tab[1];
            p.y+=tab[0];
            if (world.getBoard()[(int)p.getY()][(int) p.getX()]==null||world.getBoard()[(int)p.getY()][(int) p.getX()].getName()=="Human"){
                Comment.AddComment(this.getName()+" move from (x: "+this.position.getX()+", y: "+this.position.getY()+") to (x: "+p.getX()+", y: "+p.getY()+")");
                makeMove(p);
            }
            else {
                super.collision(world.getBoard()[(int)p.getY()][(int)p.getX()]);
            }
            if (world.getBoard()[(int) p.getY()][(int) p.getX()]!=this)break;
        }
        tab[0]=0;
        tab[1]=0;
        if(world.isHumanAbility()== true&&world.getCoolDown()>0){
            range=2;
        }
    }

    public void specialAbility(){
        if (world.isHumanAbility()==true&&world.getCoolDown()==0){
            range=1;
            world.setHumanAbility(false);
        }
        else if(world.isHumanAbility()==true&&world.getCoolDown()<=2&&world.getCoolDown()!=0){
            int chance;
            Random r = new Random();
            chance=r.nextInt(2);
            if (chance==1)range=1;
        }
        if (world.isHumanAbility()==true){
            Comment.AddComment("Round with special skills "+world.getCoolDown());
        }
        else if(world.isHumanAbility()==false){
            Comment.AddComment("Too low cool down. Your cooldown is "+world.getCoolDown());
        }
    }
    @Override
    public String getName() {
        return "Human";
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0,0,0));
        g.fillRect(position.x* world.getScale(), position.y* world.getScale(), world.getScale(), world.getScale());
    }

    @Override
    public Organism clone(Point p) {
        return null;
    }

    @Override
    public Point getPosition() {
        return super.getPosition();
    }

    public void setNewPosition(Point newPosition) {
        this.newPosition = newPosition;
    }

    public int[] getTab() {
        return tab;
    }

    public void setTab(int[] tab) {
        this.tab = tab;
    }
}

