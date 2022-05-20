package projekt2.Java.world.organism;

import projekt2.Java.world.Comment;
import projekt2.Java.world.World;

import java.awt.*;

public abstract class Animal extends Organism {
    public Animal(World world, Point position, int strenght, int initiative, int age, int range, boolean newBorn) {
        super(world, position, strenght, initiative, age, range, newBorn);
    }

    public void breed(Organism org){
        Point newPos=findFreeField(org);
        if (newPos!=org.position){
            world.place(clone(newPos),newPos);
            Comment.AddComment("New "+org.getName()+" born on ("+newPos.getX()+", "+newPos.getY()+")");
        }
    }
    public void action(){
        this.age++;
        Point p;
        for (int i=0;i<this.getRange();i++){
            p=newPosition();
            if (world.getBoard()[(int) p.getY()][(int) p.getX()]==null){
                Comment.AddComment(this.getName()+" move from (x: "+this.position.getX()+", y: "+this.position.getY()+") to (x: "+p.getX()+", y: "+p.getY()+")");
                makeMove(p);
            }
            else {
                collision(world.getBoard()[(int)p.getY()][(int)p.getX()]);
            }
            if (world.getBoard()[(int) p.getY()][(int) p.getX()]!=this)break;
        }
    }

    public void collision(Organism oc){

        if (oc.getName()=="Turtle"&&this.getStrenght()<5){
            Comment.AddComment(oc.getName()+" fight off the "+this.getName()+" atack");
        }
        else if(oc.getName()==this.getName()&&oc!=this){
            if (oc.getAge()<=4&&this.getAge()<=4){
                Comment.AddComment(this.getName()+" to young to breed");
            }
            else if (findFreeField(this)!=this.position){
                breed(this);
            }
            else if(findFreeField(oc)!=oc.position){
                breed(oc);
            }
            else Comment.AddComment("There's no open field to give birth "+this.getName());
        }
        else if(oc.getName()=="Guarana"){
            this.setStrenght(this.getStrenght()+3);
            world.removeOrganism(oc);
            makeMove(oc.getPosition());
            Comment.AddComment(this.getName()+" move from (x: "+this.position.getX()+", y: "+this.position.getY()+") to " +
                    "(x: "+oc.position.getX()+", y: "+oc.getPosition().getY()+") ");
            Comment.AddComment(this.getName()+" eat Guarna, new strenght "+this.getStrenght());
        }
        else if(oc.getName()=="Pine Borscht"||oc.getName()=="Wolf Berries"){
            Comment.AddComment(oc.getName()+" kill "+this.getName());
            world.removeOrganism(this);
        }
        else{
            fight(oc);
        }
    }
    public void fight(Organism oc){
        if (oc.getStrenght() > this.getStrenght()) {
            Comment.AddComment(this.getName()+" move from (x: "+this.position.getX()+", y: "+this.position.getY()+") to " +
                    "(x: "+oc.position.getX()+", y: "+oc.getPosition().getY()+")");
            Comment.AddComment(oc.getName()+" kill "+this.getName());
            world.removeOrganism(this);}
        else if(oc.getStrenght() <= this.getStrenght()){
            Comment.AddComment(this.getName()+" move from (x: "+this.position.getX()+", y: "+this.position.getY()+") to " +
                    "(x: "+oc.position.getX()+", y: "+oc.getPosition().getY()+")");
            Comment.AddComment(this.getName()+" kill "+oc.getName());
            world.removeOrganism(oc);
            makeMove(oc.position);
        }
    }
}

