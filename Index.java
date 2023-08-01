import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
class Index extends JFrame implements MouseListener
{
	JProgressBar pb;
	JLabel lblimg,lblstart1,lblstart2;
	JPanel pnl;
	Index()
	{
		/*image*/
		ImageIcon image=new ImageIcon("break breaker.png");

		/*border*/
		Border border=BorderFactory.createLineBorder(new Color(35, 10, 120),6);

		/*progressbar*/
		pb=new JProgressBar(0,100);
		pb.setValue(0);
		pb.setStringPainted(true);
		pb.setBounds(111,920,1686,30);
		pb.setForeground(new Color(42, 1, 43));	
		pb.setFont(new Font("SANS_SERIF",Font.BOLD,16));
		pb.setVisible(false);
		pb.setBorder(BorderFactory.createLineBorder(Color.white,0));
	
		/*game image*/
		lblimg=new JLabel();
		lblimg.setIcon(image);
		lblimg.setBounds(13,8,1880,948);

		/*start 1*/
		lblstart1=new JLabel("START");
		lblstart1.setFont(new Font("MONOSPACED",Font.BOLD,56));
		lblstart1.setForeground(new Color(30, 3, 66));
		lblstart1.setBounds(876,600,200,100);

		/*start 2*/
		lblstart2=new JLabel("START");
		lblstart2.setFont(new Font("MONOSPACED",Font.BOLD,56));
		lblstart2.setForeground(Color.white);
		lblstart2.setBounds(878,603,200,100);

		/*panel*/
		pnl=new JPanel();
		pnl.setBorder(border);
		pnl.setBackground(Color.black);
		
		/*add components to panel*/
		pnl.add(pb);	
		pnl.add(lblstart1);
		pnl.add(lblstart2);
		pnl.add(lblimg);
		
		/*set layout*/
		pnl.setLayout(null);
		
		/*add panel*/
		add(pnl);

		/*add mouse listener*/
		lblstart1.addMouseListener(this);
	}
	public void mouseEntered(MouseEvent me)
	{
	}
	public void mouseExited(MouseEvent me)
	{
	}
	public void mouseClicked(MouseEvent me)
	{
		int j;
		if(me.getSource()==lblstart1)
		{
			pb.setVisible(true);
			for(j=0;j<=100;j++)
			{
				if(j==100)
				{
					this.setVisible(false);

					ImageIcon logo=new ImageIcon("break_breaker_logo.jpg");


					Game g=new Game();

					GamePage game=new GamePage(1,1);
					game.setTitle("Break Breaker Game");
					game.setVisible(true);
					game.setSize(1910,1000);
					game.setResizable(false);
					game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					game.setIconImage(logo.getImage());

					game.add(g);
						game.setVisible(true);
						
				}
				if(j<=100 && j>80)
				{
					pb.paintImmediately(0,0,1686,30);
					pb.setValue(j);
					pb.setString("Ready To Play");		
					j++;

					try
					{
						Thread.sleep(100);
					}
						catch(Exception e)
					{
						System.out.println(e);
					}
				}
				if(j<=80 && j>40)
				{
					pb.paintImmediately(0,0,1686,30);
					pb.setValue(j);
					pb.setString("Starting Break breaker Game");		
					j++;

					try
					{
						Thread.sleep(30);
					}
						catch(Exception e)
					{
						System.out.println(e);
					}
				}
				if(j<=40 && j>20)
				{
					pb.paintImmediately(0,0,1686,30);
					pb.setValue(j);
					pb.setString("Preparing Resources");		
					j++;

					try
					{
						Thread.sleep(200);
					}
						catch(Exception e)
					{
						System.out.println(e);
					}
				}
				if(j<=20 && j>=0)
				{
					pb.paintImmediately(0,0,1686,30);
					pb.setValue(j);
					pb.setString("Loading...");		
					j++;

					try
					{
						Thread.sleep(100);
					}
						catch(Exception e)
					{
						System.out.println(e);
					}
				}
				
			}	
		}
	}
	public void mousePressed(MouseEvent me)
	{
	}
	public void mouseReleased(MouseEvent me)
	{
	}
	public static void main(String[] ar)
	{
		ImageIcon logo=new ImageIcon("break_breaker_logo.jpg");

		Index obj=new Index();
		obj.setTitle("Break Breaker Game");
		obj.setSize(1920,1000);
		obj.setVisible(true);
		obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.setIconImage(logo.getImage());
		
	}	
}