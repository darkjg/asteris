package asterissrc;
/*
Copyright (C) 2012 Johan Ceuppens

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/
import java.awt.*;
import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.*;
import java.util.Random;

public class Game extends JPanel implements ActionListener {
private String fileprefix = "./pics/";
private Player player = new Player(100,100,40,40);
private LinkedList boundingboxes = new LinkedList();
private Map map = new Map(0,0,320,200,new ImageIcon(fileprefix+"map-level1-320x200-1.png").getImage());
    public Game() {

	
	
	Timer timer;

        addKeyListener(new TAdapter());
        setFocusable(true);
	
        setBackground(Color.white);
        setDoubleBuffered(true);

        timer = new Timer(40, this);
        timer.start();

	loadlevel1();

    }

    public void addNotify() {
        super.addNotify();
        GameInit();
    }

    public void loadlevel1()
    {
	boundingboxes.add(new BoundingBox(0,160,200,50));
    }

    public void GameInit() {
        LevelInit();
    }


    public void LevelInit() {

    }

/*
 * Collision detection code
 */

    public boolean collisionforfall(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        if (x1 > x2 && y1 + h1 > y2 && x1 < x2 + w2 && y1 < y2 + h2)//FIXME
                return true;
        else
                return false;
    }

    public boolean collision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        if (x1 > x2 && y1 > y2 && x1 < x2 + w2 && y1 < y2 + h2)//FIXME
                return true;
        else
                return false;
    }

    public void DoFallDown()
    {
	
	for (int i = 0; i < boundingboxes.size(); i++) {
		Object o = boundingboxes.get(i);
		BoundingBox bo = (BoundingBox)o;
		if (collisionforfall(player.getx(),player.gety(),player.getw(),player.geth(),bo.getx(),bo.gety(),bo.getw(),bo.geth())) {
			return;
		}
	}
	player.fall();
    }

/*
 * Drawing
 */ 

    public void DrawPlayer(Graphics2D g2d) {
	g2d.drawImage(player.getImage(), player.getx(), player.gety(), this);
    }
	
    public void DrawMap(Graphics2D g2d) {
	g2d.drawImage(map.getImage(), map.getx(), map.gety(), this);
    }
	
    public void paint(Graphics g)
    {
      Graphics2D g2d = (Graphics2D) g;

      g2d.setColor(Color.black);
      g2d.fillRect(0, 0, 320, 200);

      DrawMap(g2d);
      DrawPlayer(g2d);
      DoFallDown();

	//g2d.drawImage("pics/player-right-40x40-1.png",100,100,this);
      Toolkit.getDefaultToolkit().sync();
      g.dispose();

    }

    class TAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
		player.settonotmoving();
	} 
      
        public void keyPressed(KeyEvent e) {

          int key = e.getKeyCode();

	   	if (key == KeyEvent.VK_LEFT) {
			player.settomoving();
			player.moveleft();
	   	}
	   	if (key == KeyEvent.VK_RIGHT) {
			player.settomoving();
			player.moveright();
	   	}
	   	if (key == KeyEvent.VK_UP) {

	   	}
	   	if (key == KeyEvent.VK_DOWN) {
		}	
	   	if (key == KeyEvent.VK_X) {

	   	}
	   	if (key == KeyEvent.VK_Z) {//go back to history of talkmodes
	   	}
		if (key == KeyEvent.VK_ESCAPE) {
	   	}
	}
    }

    public void actionPerformed(ActionEvent e) {
        repaint();  
    }

}
