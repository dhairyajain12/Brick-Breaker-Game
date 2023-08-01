import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
class GamePage extends JFrame 
{
	int blocks[][];
	int bwidth;
	int bheight;
	GamePage(int rows,int columns)
	{
		blocks =new int[rows][columns];
		for(int i=0;i<blocks.length;i++)
		{
			for(int j=0;j<blocks[0].length;j++)
			{
				blocks[i][j]=1;
			}
		}
		bwidth=1660/columns;	//46.11
		bheight=370/rows;		//46.25
	}
	public void draw(Graphics2D g)
	{
		for(int i=0;i<blocks.length;i++)
		{
			for(int j=0;j<blocks[0].length;j++)
			{
				if(blocks[i][j] > 0)
				{
					g.setColor(new Color(212, 142, 142));
					g.fillRect(j*bwidth+120 ,i*bheight+80 ,bwidth,bheight);

					//g.setStroke(new BasicStroke(4));
					g.setColor(Color.black);
					g.drawRect(j*bwidth+120 ,i*bheight +80 ,bwidth ,bheight);
				}
			}
		}
	}
	public void setBrickValue(int value ,int rows ,int columns)
	{
		blocks[rows][columns] = value;
	}		
	public static void main(String[] args)
	{
		JFrame obj=new JFrame();
		Game game=new Game();
		obj.setBounds(10, 10, 1900, 1000);
		obj.setTitle("Breakout Ball");		
		obj.setResizable(false);
		obj.setVisible(true);
		obj.add(game);
			obj.setVisible(true);	
	}
}