package projekt2.Java.world.organism;

import projekt2.Java.world.World;


import java.awt.*;
import java.util.Random;

public abstract class Organism {
    protected World world;
    protected Point position;
    protected int strenght;
    protected int initiative;
    protected int age;
    protected int range;
    protected boolean newBorn;
    public Organism(World world,Point position,int strenght,int initiative,int age, int range,boolean newBorn){
        this.world=world;
        this.position=position;
        this.strenght=strenght;
        this.initiative=initiative;
        this.age=age;
        this.range=range;
        this.newBorn=newBorn;
    }
    public void makeMove(Point p){
        world.getBoard()[(int)this.position.getY()][(int)this.position.getX()]=null;
        world.getBoard()[p.y][p.x]=this;
        this.position.setLocation(p);
    }
    public Point newPosition(){
        Point p = new Point();
        p.setLocation(position.getX(),position.getY());
        boolean canMove[]={true,true,true,true};
        if (position.getY()==0)canMove[0]=false;
        if (position.getY()==world.getHeight()-1)canMove[1]=false;
        if (position.getX()==0)canMove[2]=false;
        if (position.getX()==world.getWidth()-1)canMove[3]=false;
        while (true){
            Random r = new Random();
            int direction=r.nextInt(4);
            if (direction==0&&canMove[direction]==true) {//up
                p.y--;
                return p;
            }
            else if (direction==1&&canMove[direction]==true){//down
                p.y++;
                return p;
            }
            else if (direction==2&&canMove[direction]==true){//left
                p.x--;
                return p;
            }
            else if(direction==3&&canMove[direction]==true){//right
                p.x++;
                return p;
            }
        }
    }

    public Point findFreeField(Organism organism)
    {
        int chance=-1;
        Point p=new Point();
        Random r = new Random();
       p.setLocation(organism.position);
        boolean canMove[]={true,true,true,true};
        if (organism.position.getY()==0)canMove[0]=false;
        if (organism.position.getY()==world.getHeight()-1)canMove[1]=false;
        if (organism.position.getX()==0)canMove[2]=false;
        if (organism.position.getY()==world.getWidth()-1)canMove[3]=false;
        while(chance!=3){
             chance=r.nextInt(4);
            if (chance>=3&&canMove[0]==true&&world.getBoard()[(int) p.getY()-1][(int) p.getX()]==null){
                p.y--;
                return p;}
            else if (chance>=2&&canMove[1]==true&&world.getBoard()[(int) p.getY()+1][(int) p.getX()]==null){
                p.y++;
                return p;}
            else if (chance>=1&&canMove[2]==true&&world.getBoard()[(int) p.getY()][(int) p.getX()-1]==null){
                p.x--;
                return p;}
            else if (chance>=1&&canMove[3]==true&&world.getBoard()[(int) p.getY()][(int) p.getX()-1]==null){
                p.x++;
                return p;
            }
        }
        return p;
    }
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public int getRange() {
        return range;
    }

    public int getStrenght() {
        return strenght;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setNewBorn(boolean newBorn) {
        this.newBorn = newBorn;
    }

    public boolean isNewBorn() {
        return newBorn;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public abstract void paint(Graphics g);
    public abstract String getName();
    public abstract void action();
    public abstract void collision(Organism occupied);
    public abstract Organism clone(Point p);
}
