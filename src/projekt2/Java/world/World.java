package projekt2.Java.world;

import projekt2.Java.GUI.MainWindow;
import projekt2.Java.world.organism.Organism;
import projekt2.Java.world.organism.animal.*;
import projekt2.Java.world.organism.plant.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import java.io.IOException;

public class World {
    private Dimension dimension;
    private int scale;
    private int width;
    private int height;
    private int turn=1;
    private int coolDown=0;
    private boolean humanAbility=false;
    private Organism board[][];
    public ArrayList<Organism> organismList;
    public Human human;
    public World(Dimension dimension, int scale){
        this.dimension=dimension;
        this.scale=scale;
        width= dimension.width/scale;
        height= dimension.height/scale;
        board = new Organism[height][width];
        System.out.println(width);
        System.out.println(height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = null;
            }
        }
        organismList=new ArrayList<>();
        randomPlace(new Wolf(this,new Point(-1,-1)));
        randomPlace(new Wolf(this,new Point(-1,-1)));
        randomPlace(new Wolf(this,new Point(-1,-1)));
        randomPlace(new Sheep(this,new Point(-1,-1)));
        randomPlace(new Sheep(this,new Point(-1,-1)));
        randomPlace(new Sheep(this,new Point(-1,-1)));
        randomPlace(new Fox(this,new Point(-1,-1)));
        randomPlace(new Fox(this,new Point(-1,-1)));
        randomPlace(new Fox(this,new Point(-1,-1)));
        randomPlace(new Turtle(this,new Point(-1,-1)));
        randomPlace(new Turtle(this,new Point(-1,-1)));
        randomPlace(new Turtle(this,new Point(-1,-1)));
        randomPlace(new Antylope(this,new Point(-1,-1)));
        randomPlace(new Antylope(this,new Point(-1,-1)));
        randomPlace(new Grass(this,new Point(-1,-1)));
        randomPlace(new Grass(this,new Point(-1,-1)));
        randomPlace(new Grass(this,new Point(-1,-1)));
        randomPlace(new Dandelion(this,new Point(-1,-1)));
        randomPlace(new Dandelion(this,new Point(-1,-1)));
        randomPlace(new Dandelion(this,new Point(-1,-1)));
        randomPlace(new Guarana(this,new Point(-1,-1)));
        randomPlace(new Guarana(this,new Point(-1,-1)));
        randomPlace(new Guarana(this,new Point(-1,-1)));
        randomPlace(new PineBorscht(this,new Point(-1,-1)));
        randomPlace(new PineBorscht(this,new Point(-1,-1)));
        randomPlace(new PineBorscht(this,new Point(-1,-1)));
        randomPlace(new WolfBerries(this,new Point(-1,-1)));
        randomPlace(new WolfBerries(this,new Point(-1,-1)));
        randomPlace(new WolfBerries(this,new Point(-1,-1)));
        place(new Human(this,new Point(-1,-1)),new Point(10,10));
    }
    public World(int scale,int width,int height){
        this.scale=scale;
        this.height=height;
        this.width=width;

        board = new Organism[height][width];


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = null;
            }
        }
        organismList=new ArrayList<>();
    }
    public World(){}
    public void addOrganism(Organism organism){
        organismList.add(organism);
        board[(int) organism.getPosition().getY()][(int) organism.getPosition().getX()]=organism;
        Comment.AddComment("New "+organism.getName()+" on (x: "+organism.getPosition().getX()+", y: "+organism.getPosition().getY()+")");
        if (organism.getName()=="Human"){
            human= (Human) organism;
        }
    }

    public void removeOrganism(Organism organism){
        board[(int)organism.getPosition().getY()][(int)organism.getPosition().getX()]=null;
        organismList.remove(organism);

    }
    public static class organizmComparator implements Comparator<Organism> {
        @Override
        public int compare(Organism o1, Organism o2) {
            if (o1.getInitiative() != o2.getInitiative())
                    return Integer.valueOf(o2.getInitiative()).compareTo(o1.getInitiative());
                else
                   return Integer.valueOf(o2.getAge()).compareTo(o1.getAge());
        }
    }


    public void randomPlace(Organism newone){
        Random r = new Random();
        Point newPosition = new Point();
        if (width*height!=organismList.size()){
            while (true){
                newPosition.x=r.nextInt(width);
                newPosition.y=r.nextInt(height);
                if (board[(int) newPosition.getY()][(int) newPosition.getX()]==null){
                    newone.setPosition(newPosition);
                    addOrganism(newone);
                    break;
                }
            }
        }

    }

    public void place(Organism newone,Point position){
        newone.setPosition(position);
        addOrganism(newone);
    }

    public void makeTurn(){
        turn++;
        Collections.sort(organismList,new organizmComparator());
        for (Organism o:organismList){
            o.setNewBorn(false);
        }
        if (humanAbility==false &&coolDown<5){
            coolDown+=1;
        }
        else if(humanAbility== true&&coolDown>0){
            coolDown-=1;
        }

        for (int i=0;i<organismList.size();i++){
            if (organismList.get(i).isNewBorn()==false){

                organismList.get(i).action();
            }
        }
    }
    public void saveWorld(String nameOfFile){
        try{
            nameOfFile+=".txt";
            File file =new File(nameOfFile);
            file.createNewFile();
            PrintWriter p = new PrintWriter(file);
            p.print(scale+" ");
            p.print(width+" ");
            p.print(height+" ");
            p.print(turn+" ");
            p.print(humanAbility+" ");
            p.print(coolDown+" \n");
            for (int i=0;i<organismList.size();i++){
                p.print(organismList.get(i).getName()+" ");
                p.print(organismList.get(i).getPosition().getX()+" ");
                p.print(organismList.get(i).getPosition().getY()+" ");
                p.print(organismList.get(i).getStrenght()+" ");
                p.print(organismList.get(i).getAge()+" ");
                p.println();
            }
            p.close();
        }
        catch (IOException e){
            System.out.println("ERROR: "+e);
        }
    }
    public World LoadFile(String nameOfFile){
        try{
            nameOfFile+=".txt";
            File file=new File(nameOfFile);
            Scanner scanner=new Scanner(file);
            String line= scanner.nextLine();
            String[] preperties=line.split(" ");
            int scale=Integer.parseInt(preperties[0]);
            int width=Integer.parseInt(preperties[1]);
            int height=Integer.parseInt(preperties[2]);
            int turn=Integer.parseInt(preperties[3]);
            boolean humanAbility=Boolean.parseBoolean(preperties[4]);
            int coolDown=Integer.parseInt(preperties[5]);
            World tmp =new World(scale,width,height);
            tmp.width=width;
            tmp.height=height;
            tmp.turn=turn;
            tmp.humanAbility=humanAbility;
            tmp.coolDown=coolDown;
            while (scanner.hasNextLine()){
                line=scanner.nextLine();
                preperties=line.split(" ");
                String name=preperties[0];
                int xP=(int)Double.parseDouble(preperties[1]);
                int yP=(int)Double.parseDouble(preperties[2]);

                Point po=new Point(xP,yP);
                int strenght=Integer.parseInt(preperties[3]);
                int age=Integer.parseInt(preperties[4]);
                if(name=="Antylop"){ place(new Antylope(tmp,new Point(-1,-1),age,strenght),po);
                }
                else if(name=="Fox"){
                    place(new Fox(this,new Point(-1,-1),age,strenght),po);
                }
                else if(name=="Sheep"){
                    place(new Sheep(this,new Point(-1,-1),age,strenght),po);
                }
                else if(name=="Wolf"){
                    place(new Wolf(this,new Point(-1,-1),age,strenght),po);
                }
                else if(name=="Turtle"){
                    place(new Turtle(this,new Point(-1,-1),age,strenght),po);
                }
                else if(name=="Dandelion"){
                    place(new Dandelion(this,new Point(-1,-1)),po);
                }
                else if(name=="Borscht"){
                    place(new PineBorscht(this,new Point(-1,-1)),po);
                }
                else if(name=="Berries"){
                    place(new WolfBerries(this,new Point(-1,-1)),po);
                }
                else if(name=="Grass"){
                    place(new Grass(this,new Point(-1,-1)),po);
                }
                else if(name=="Guarana"){
                    place(new Guarana(this,new Point(-1,-1)),po);
                }
                else if(name=="Human"){
                    place(new Human(this,new Point(-1,-1),age,strenght),po);
                }
            }
            scanner.close();
            return tmp;
        } catch (IOException e) {
            System.out.println("ERROR: "+e);
            return null;
        }
    }
    public Organism[][] getBoard() {
        return board;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public boolean isHumanAbility() {
        return humanAbility;
    }

    public int getTurn() {
        return turn;
    }

    public int getScale() {
        return scale;
    }

    public Human getHuman() {
        return human;
    }

    public void setHumanAbility(boolean humanAbility) {
        this.humanAbility = humanAbility;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
