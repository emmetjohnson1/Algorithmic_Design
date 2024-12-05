/*
 * Emmet Johnson 
 */
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Triangles extends Canvas
{

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Triangles!");
		//size of the frame 500 x 500
		frame.setSize(500, 500);
		frame.add(new Triangles());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g)
	{
		
		super.paint(g);
		//setting initial variables, x, y, and z
		int side = 500; 
		int x = 0; 
		int y = 500;
		
		int x1 = x, y1 = y; 
		int x2 = x + side, y2 = y; 
		int x3 = (x+side)/2, y3 = y - side; 
		
		//Draw original triangle
		int[] xPoints = {x1, x2, x3};
		int[] yPoints = {y1, y2, y3};
		g.setColor(Color.black);
		g.fillPolygon(xPoints, yPoints, 3);
		
		//calling draw triangle and passing in points [] x, [] y, the side length, and lastly the depth or in 
		//our case the pixel
		drawTriangle(xPoints,yPoints,side,4,g);

		
	}
	
	
	
	private void drawTriangle(int[] xPoints, int[] yPoints, int side, int depth, Graphics g)
	{
		//depth is set to 4, and subtracts each time, this way it creates a pixel limit of 4 
		if(depth > 0)
		{
			
			//drawing white triangle that is inverted from the black triangle
			int midx1 = (xPoints[0] + xPoints[1])/2, midy1 = (yPoints[0] + yPoints[1])/2; //bottom
			int midx2 = (xPoints[1] + xPoints[2])/2, midy2 = (yPoints[1]+yPoints[2])/2; //left
			int midx3 = (xPoints[2]+xPoints[0])/2, midy3 = (yPoints[0]+yPoints[2])/2; //right
			//this is done by calculating the midpoints of the initial black triangle
			//midpoint formula x1 + x2 / 2 ....
			
			int[] xIPoints = {midx1, midx2, midx3};
			int[] yIPoints = {midy1, midy2, midy3};
			//defining an array of the midpoints to then call our fill polygon method
			
			//creating the inverted triangle
			g.setColor(Color.white);
			g.fillPolygon(xIPoints, yIPoints, 3);
			
			//LEFT side of inverted triangle
			int[] leftX = {xPoints[0], midx1, midx3};
			int[] leftY = {yPoints[0], midy1, midy3};
			
			//recursive method drawTriangle of call stack
			drawTriangle(leftX, leftY, side / 2, depth - 1, g);
			
			
			//RIGHT side of inverted
			int[] rightX = {midx1, xPoints[1], midx2};
			int [] rightY = {midy1, yPoints[1], midy2};
			drawTriangle(rightX, rightY, side / 2, depth - 1, g); 
			
			//TOP of inverted triangle
			int[] topX = {midx3, midx2, xPoints[2]}; 
			int[] topY = {midy3, midy2, yPoints[2]};
			drawTriangle(topX, topY, side / 2, depth - 1, g); 
		}	
	}
}
