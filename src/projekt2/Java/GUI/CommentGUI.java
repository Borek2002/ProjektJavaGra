package projekt2.Java.GUI;

import projekt2.Java.world.Comment;
import projekt2.Java.world.World;

import javax.swing.*;
import java.awt.*;

public class CommentGUI extends JPanel {
    final int HEIGHT=650;
    final int WIDTH=300;
    World world;
    public CommentGUI(World world){
        setSize(WIDTH,HEIGHT);
        this.world=world;

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    void drawBackgroung(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(0,0,WIDTH,HEIGHT);
    }
    void draw(Graphics g){
        g.clearRect(0,0,WIDTH,HEIGHT);
        drawBackgroung(g);

    }
}
