package projekt2.Java.GUI;

import projekt2.Java.world.Comment;
import projekt2.Java.world.World;
import projekt2.Java.world.organism.animal.*;
import projekt2.Java.world.organism.plant.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class MainWindow extends JFrame {
    ScreenBoard screenBoard;
    CommentGUI commentGUI;
    JButton newgame,save,load,quit,nextTurn;
    World world;
    Dimension dimension,sdimension;
    JPanel jpanel;
    JTextArea textArea;
    JScrollPane scrollPane;
    JFrame input;
    final int SWIDTH=700;
    final int SHEIGHT=700;
    final int SCALE=25;
    public int SSCALE=25;
    public MainWindow(){
        dimension=new Dimension(1300,800);
        sdimension=new Dimension(SWIDTH,SHEIGHT);
        setSize(dimension);
        setTitle("Gra Zwierzaki");
        addButtons();
        jpanel=new JPanel();
        backGround();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);//brak mozliwości powiekszania ekranu
        setLayout(null);
        setVisible(true);
        setFocusable(true);
    }

    void initScreenBoard(){
        screenBoard=new ScreenBoard(world);
        screenBoard.setBounds(0,2*SCALE,screenBoard.WIDTH,screenBoard.HEIGHT);
        add(screenBoard);

    }
    void backGround(){

        jpanel.setBounds(0,0,1300,800);
        jpanel.setBackground(Color.BLACK);
        add(jpanel);
    }
    void addButtons(){
        newgame=new JButton();
        newgame.setBounds(0,0,4*SCALE,2*SCALE);
        newgame.setText("New Game");
        newgame.setBackground(new Color(60,184,35));
        newgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comment.RemoveComments();
                input=new JFrame();
                SSCALE = Integer.parseInt(JOptionPane.showInputDialog(input, "Podaj skale ", "25"));
                if (screenBoard!=null)remove(screenBoard);
                if (commentGUI!=null)remove(commentGUI);
                if (textArea!=null)remove(textArea);
                if (scrollPane!=null)remove(scrollPane);
                repaint();
                world = new World(sdimension, SSCALE);
                backGround();
                addNewGameGUI();
                addButtons();
                initScreenBoard();
                backGround();
                Graphics g = screenBoard.getGraphics();
                screenBoard.draw(g);
                refreshCommentGUI();
            }
        });
        add(newgame);

        load=new JButton();
        load.setBounds(4*SCALE,0,4*SCALE,2*SCALE);
        load.setText("Load Game");
        load.setBackground(new Color(235,215,89));
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    input=new JFrame();
                    String nameOfFile = JOptionPane.showInputDialog(input, "Podaj nazwe pliku", "SaveGame");
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
                    world =new World(scale,width,height);
                    world.setTurn(turn);
                    world.setHumanAbility(humanAbility);
                    world.setCoolDown(coolDown);
                    while (scanner.hasNextLine()){
                        line=scanner.nextLine();
                        preperties=line.split(" ");
                        String name=preperties[0];
                        int xP=(int)Double.parseDouble(preperties[1]);
                        int yP=(int)Double.parseDouble(preperties[2]);

                        Point po=new Point(xP,yP);
                        int strenght=Integer.parseInt(preperties[3]);
                        int age=Integer.parseInt(preperties[4]);
                        if(Objects.equals(name, "Antylope")){ world.place(new Antylope(world,new Point(-1,-1),age,strenght),po);
                        }
                        else if(Objects.equals(name, "Fox")){

                            world.place(new Fox(world,new Point(-1,-1),age,strenght),po);
                        }
                        else if(Objects.equals(name, "Sheep")){
                            world.place(new Sheep(world,new Point(-1,-1),age,strenght),po);
                        }
                        else if(Objects.equals(name, "Wolf")){
                            world.place(new Wolf(world,new Point(-1,-1),age,strenght),po);
                        }
                        else if(Objects.equals(name, "Turtle")){
                            world.place(new Turtle(world,new Point(-1,-1),age,strenght),po);
                        }
                        else if(Objects.equals(name, "Dandelion")){
                            world.place(new Dandelion(world,new Point(-1,-1),age,strenght),po);
                        }
                        else if(Objects.equals(name, "Borscht")){
                            world.place(new PineBorscht(world,new Point(-1,-1),age,strenght),po);
                        }
                        else if(Objects.equals(name, "Berries")){
                            world.place(new WolfBerries(world,new Point(-1,-1),age,strenght),po);
                        }
                        else if(Objects.equals(name, "Grass")){
                            world.place(new Grass(world,new Point(-1,-1),age,strenght),po);
                        }
                        else if(Objects.equals(name, "Guarana")){
                            world.place(new Guarana(world,new Point(-1,-1),age,strenght),po);
                        }
                        else if(Objects.equals(name, "Human")){
                            world.place(new Human(world,new Point(-1,-1),age,strenght),po);
                        }
                    }
                    scanner.close();
                } catch (IOException o) {
                    System.out.println("ERROR: "+o);
                }
                if (screenBoard!=null)remove(screenBoard);
                if (commentGUI!=null)remove(commentGUI);
                if (textArea!=null)remove(textArea);
                if (scrollPane!=null)remove(scrollPane);
                repaint();
                backGround();
                addNewGameGUI();
                addButtons();
                initScreenBoard();
                backGround();
                Graphics g = screenBoard.getGraphics();
                screenBoard.draw(g);
                refreshCommentGUI();
            }
        });

        add(load);

        quit=new JButton();
        quit.setBounds(2*4*SCALE,0,4*SCALE,2*SCALE);
        quit.setText("Quit");
        quit.setBackground(new Color(254,32,2));
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(quit);


    }

    void addNewGameGUI(){
        nextTurn=new JButton();
        nextTurn.setBounds(SWIDTH+3*SCALE,2*SCALE+650,300/2,2*SCALE);
        nextTurn.setText("Next Round");
        nextTurn.setBackground(new Color(165,215,89));
        nextTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (screenBoard!=null)remove(screenBoard);
                Comment.RemoveComments();
                world.makeTurn();
                initScreenBoard();
                backGround();
                refreshCommentGUI();
                Graphics g = screenBoard.getGraphics();
                screenBoard.draw(g);
                repaint();
            }
        });

        nextTurn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (world!=null){
                    int keyCode=e.getKeyCode();
                    if (world.getHuman()!=null){
                        Point p= world.getHuman().getPosition();
                        int[] tab=world.getHuman().getTab();
                        if (keyCode == KeyEvent.VK_UP) {
                            if (p.y!=0){
                                tab[0]=-1;
                            }
                        } else if (keyCode == KeyEvent.VK_DOWN) {
                        if (p.y!= world.getHeight()-1){
                            tab[0]=1;
                        }

                    } else if (keyCode == KeyEvent.VK_LEFT) {
                        if (p.x!= 0){
                            tab[1]=-1;
                        }

                    } else if (keyCode == KeyEvent.VK_RIGHT) {
                            if (p.x != world.getWidth() - 1) {
                                tab[1]=1;
                            }
                        }
                        else if(keyCode==KeyEvent.VK_C){
                            if (world.isHumanAbility()==false&&world.getCoolDown()==5){
                                world.setHumanAbility(true);
                                world.getHuman().setRange(2);
                            }
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        add(nextTurn);

        save=new JButton();
        save.setBounds(SWIDTH+3*SCALE+300/2,2*SCALE+650,300/2,2*SCALE);
        save.setText("Save");
        save.setBackground(new Color(32,15,217));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input=new JFrame();
                String nameOfFile = JOptionPane.showInputDialog(input, "Podaj nazwe pliku", "SaveGame");
                world.saveWorld(nameOfFile);
            }
        });
        add(save);

        textArea=new JTextArea(WIDTH,HEIGHT);
        textArea.setText(Comment.getText());
        scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setBounds(SWIDTH+3*SCALE,2*SCALE,300,650);
        add(scrollPane);

    }

    void refreshCommentGUI(){
        textArea.setText(Comment.getText());
    }

}
