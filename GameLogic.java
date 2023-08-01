import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer.*;

class Game extends JPanel implements KeyListener,ActionListener
{
	boolean play=false;
	int point=0;

	int totalBlocks=288;
	
	Timer time;
	int delay=8;

	int PlayerX=900;

	int ballPosX=970;
	int ballPosY=930;
	int ballXdir=-1;
	int ballYdir=-2;

	GamePage blocks;
	
	Game()
	{
		blocks=new GamePage(8,36);
		addKeyListener(this);	

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		time=new Timer(delay,this);
		time.start();

		
	}
	public void paint(Graphics g)
	{
		//background
	 	g.setColor(Color.black);
		g.fillRect(1,1,1900,1000);

		blocks.draw((Graphics2D) g);
		
		g.setColor(new Color(1, 137, 145));
		g.fillRect(0,0,5,1000);
		g.fillRect(0,0,1910,5);
		g.fillRect(1899,0,5,1000);

		// scores
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,40));
		g.drawString(""+point,1750,40);

		// pddle
		g.setColor(new Color(7, 206, 217));
		g.fillRoundRect(PlayerX,948,160,14,10,10);

		//ball
		g.setColor(new Color(33, 196, 29));
		g.fillOval(ballPosX, ballPosY, 18, 18);

		//message to start game
		if(!play)
		{
			g.drawString("Press ( Enter ) To Play",795,640);
			g.setColor(Color.red);
			g.setFont(new Font("arial",Font.BOLD,40));
		}

		//won the game
		if(totalBlocks<=0)
		{
			play=false;
			ballPosX=0;
			ballPosY=0;
		
			g.drawString("YOU  WON",870,600);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.setColor(Color.red);

			g.drawString("Press ( Enter ) To Play Again",795,640);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.setColor(Color.red);
		}
		
		//lose the game
		if(ballPosY>970)
		{
			play=false;
			ballPosX=0;
			ballPosY=0;

			g.drawString("YOU  LOSE",870,600);
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));

			g.drawString("Press ( Enter ) To Play Again",795,640);
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
		}
		g.dispose();
	}
	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode()==KeyEvent.VK_RIGHT || ke.getKeyCode()==KeyEvent.VK_D)
		{
			if(PlayerX >= 1738)
			{
				PlayerX=1738;
			}
			else
			{
				moveRight();
			}
		}
			
		if(ke.getKeyCode()==KeyEvent.VK_LEFT || ke.getKeyCode()==KeyEvent.VK_A)
		{
			if(PlayerX<=5)
			{
				PlayerX=5;
			}
			else
			{
				moveLeft();
			}
		}

		if(ke.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(!play)
			{
				play=true;
				ballPosX=970;
				ballPosY=930;
				ballXdir=-1;
				ballYdir=-2;
				PlayerX=900;
				point=0;
				totalBlocks=288;
				blocks=new GamePage(8,36);

				repaint();
			}
		}
	}
	public void keyReleased(KeyEvent ke)
	{
	}
	public void keyTyped(KeyEvent ke)
	{
	}

	public void moveRight()
	{
		//play=true;
		PlayerX+=40;
	}
	
	public void moveLeft()
	{
		//play=true;
		PlayerX-=40;
	}

	public void actionPerformed(ActionEvent ae)
	{
		time.start();
		if(play)
		{			
			if(new Rectangle(ballPosX, ballPosY, 18, 18).intersects(new Rectangle(PlayerX,948,60,14)))
			{
				ballYdir = -ballYdir;
				ballXdir = -2;
			}
			else if(new Rectangle(ballPosX, ballPosY, 18, 18).intersects(new Rectangle(PlayerX+100,948,60,14)))
			{
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
			}
			else if(new Rectangle(ballPosX, ballPosY, 18, 18).intersects(new Rectangle(PlayerX+40,948,60,14)))
			{
				ballYdir = -ballYdir;
			}
			
			// check map collision with the ball		
			A: for(int i = 0; i<blocks.blocks.length; i++)
			{
				for(int j =0; j<blocks.blocks[0].length; j++)
				{				
					if(blocks.blocks[i][j] > 0)
					{
						//scores++;
						int brickX = j * blocks.bwidth + 120;
						int brickY = i * blocks.bheight + 80;
						int brickWidth = blocks.bwidth;
						int brickHeight = blocks.bheight;

					
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 18, 18);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect))
						{					
							blocks.setBrickValue(0, i, j);
							point+=5;	
							totalBlocks--;
							
							// when ball hit right or left of brick
							if(ballPosX + 17 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width)	
							{
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else
							{
								ballYdir = -ballYdir;				
							}
							
							break A;
						}
					}
				}
			}
			
			ballPosX += ballXdir*2;
			ballPosY += ballYdir*2;
			
			if(ballPosX < 0)
			{
				ballXdir = -ballXdir;
			}
			if(ballPosY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballPosX > 1879)
			{
				ballXdir = -ballXdir;
			}		
			
			repaint();		
		}
	}
}