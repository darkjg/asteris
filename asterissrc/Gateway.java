package asterissrc;
/*
Copyright (C) 2012 Johan Ceuppens

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.*;

/*
 * Rectangular Gateways in between levels
 */

class Gateway extends BoundingBox
{
protected Image image;
protected int xoffset = 0;//map x for setting the map coord for the next level
protected int yoffset = 0;
protected int gotolevel = 1;
public Gateway(int startx, int starty, int startw, int starth, Image img, int xx, int yy, int newlevelnumber)
{
	super(startx,starty,startw,starth);

	xoffset = xx;
	yoffset = yy;

	image = img;
	gotolevel = newlevelnumber;
}

public int getnewlevel()
{
	return gotolevel;
}
public Image getImage()
{
	return image;
}

public int getxoffset()
{
	return xoffset;
}

public int getyoffset()
{
	return yoffset;
}

};
