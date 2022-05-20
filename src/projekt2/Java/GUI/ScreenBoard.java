package projekt2.Java.GUI;

import projekt2.Java.world.World;
import projekt2.Java.world.organism.Organism;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScreenBoard extends JPanel {

    final int HEIGHT=700;
    final int WIDTH=700;
    World world;
    ArrayList<Organism> organismsListToPaint;
    public ScreenBoard(World world){
        setSize(WIDTH,HEIGHT);
        this.world=world;
        organismsListToPaint=new ArrayList<Organism>(world.organismList);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    void drawBackgroung(Graphics g){
        g.setColor(Color.lightGray);
        g.fillRect(0,0,WIDTH,HEIGHT);
    }
    void draw(Graphics g){
        g.clearRect(0,0,WIDTH,HEIGHT);
        drawBackgroung(g);
        for (int i=0; i<organismsListToPaint.size();i++){
            organismsListToPaint.get(i).paint(g);
        }
    }
}
