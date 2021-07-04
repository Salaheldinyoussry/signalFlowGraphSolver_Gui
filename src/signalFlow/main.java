package signalFlow;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ibm.icu.impl.Pair;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseMotionAdapter;
import java.awt.SystemColor;
import java.awt.Window.Type;

public class main extends JFrame {
	int counter=1;
	int sbut=-1;
	int sarrow=-1;

	ArrayList<ArrayList<Integer>> nodes =new ArrayList<>();
	ArrayList<ArrayList<Double>> arrows =new ArrayList<>();
	ArrayList<ArrayList<Integer>> arhead =new ArrayList<>();
	
	ArrayList <ArrayList<myPair> > adjList = new ArrayList<>();
	
	
	boolean arrowh=false;
	int hi=-1;
	
	Point pp =new Point();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Canvas c;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField inn;
	private JTextField outn;
	private Button button;
	private Button button_2;
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1186, 679);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		 c = new Canvas() {
	            public void paint(Graphics g)
	            {
	            }
	            
	            
	        };
		 c.addMouseMotionListener(new MouseMotionAdapter() {
		 	@Override
		 	public void mouseDragged(MouseEvent e) {
		 	  if(sarrow!=-1) {
		 		  arrows.get(sarrow).set(2, (double) e.getX());
		 		  arrows.get(sarrow).set(3, (double) e.getY());
		 		  drawAll();
		 	  }
		 	/*  else if(arrowh && hi!=-1) {
		 		  //arhead.get(hi).s
		 		  
		 		  Point cent =new Point(arhead.get(hi).get(6),arhead.get(hi).get(7));
		 	   double ang=1.047; 
		 			   //Math.atan( (e.getY()-pp.getY())/(e.getX()-pp.getX())  );
		 		
		 	   Point op =new Point(arhead.get(hi).get(0),arhead.get(hi).get(1)  );  
		 	   Point np=rotatePoint(op,cent,ang);
		 	  arhead.get(hi).set(0, np.x);
		 	 arhead.get(hi).set(1, np.y);
		 	  
			   Point op1 =new Point(arhead.get(hi).get(2),arhead.get(hi).get(3)  );  
		 	   Point np1=rotatePoint(op1,cent,ang);
		 	  arhead.get(hi).set(2, np1.x);
		 	 arhead.get(hi).set(3, np1.y);
		 	 
			   Point op2 =new Point(arhead.get(hi).get(4),arhead.get(hi).get(5)  );  
		 	   Point np2=rotatePoint(op2,cent,ang);
		 	  arhead.get(hi).set(4, np2.x);
		 	 arhead.get(hi).set(5, np2.y);
		 	 
		 	 
		 //	 arrows.get(hi).set(4,  (arhead.get(hi).get(2) + arhead.get(hi).get(4) )/2 );
		 	 
		 	// arrows.get(hi).set(5,  (arhead.get(hi).get(3) + arhead.get(hi).get(5) )/2);
		 	 
		 	  drawAll();
		 	  
		 	  }*/
		 	  
		 	}
		 });
	        c.setBackground(Color.white);
		 c.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		Graphics gc=  c.getGraphics();
	        
		 		if(sbut==0) {
		 			int x=e.getX()-10;
		 			int y=e.getY()-10;
		 			
		 		gc.fillOval(x, y,20,20);
		          gc.setFont(new Font("Tahoma", Font.BOLD, 10));

	             gc.drawString(""+counter,e.getX() -10 , e.getY()-10);
	             ArrayList<Integer> t=new ArrayList<>();
	             t.add(e.getX());
	             t.add(e.getY());
	             t.add(counter);
	             nodes.add(t);
	             adjList.add(new ArrayList<myPair>());
	             counter++;
	           //  gc.
		 		}
		 		if(sbut==1) {
		 		for(int i=0; i<arhead.size(); i++) {
	 				if(isInside(arhead.get(i).get(0),arhead.get(i).get(1),arhead.get(i).get(2),arhead.get(i).get(3),arhead.get(i).get(4)  ,arhead.get(i).get(5) , e.getX(),e.getY() )){
	 				   pp.x=e.getX();
	 				   pp.y=e.getY();
	 				  hi=i;
	 				  arrowh=true;
	 				 // System.out.println("inside ");
	 				  break;
	 				  
	 				}
		 		}
		 		
		 		if(arrowh) {
		 			int o1=arhead.get(hi).get(0);
	 				int o2=arhead.get(hi).get(1);
	 				int o3=arhead.get(hi).get(2);
	 				int o4=arhead.get(hi).get(3);
	 				int o5=arhead.get(hi).get(4);
	 				int o6=arhead.get(hi).get(5);

	 			
					 
	 				int cx=arhead.get(hi).get(6);
	 				int cy=arhead.get(hi).get(7);
	 				
	 				
		 			if(arhead.get(hi).get(8)==0) {
		 			
		 				arhead.get(hi).set(0, cx);
		 				arhead.get(hi).set(1, cy-10);
		 				
		 				arhead.get(hi).set(2, cx+6);
		 				arhead.get(hi).set(3, cy-25);

		 				arhead.get(hi).set(4, cx-6);
		 				arhead.get(hi).set(5, cy-25);
		 				
		 				arhead.get(hi).set(8, 1);
		 				
		 				
		 				
		 				
		 			}
		 			
		 			else if(arhead.get(hi).get(8)==1) {
		 				
		 				arhead.get(hi).set(0, cx+10);
		 				arhead.get(hi).set(1, cy);
		 				
		 				arhead.get(hi).set(2, cx+25);
		 				arhead.get(hi).set(3, cy+6);

		 				arhead.get(hi).set(4, cx+25);
		 				arhead.get(hi).set(5, cy-6);
		 			
		 				arhead.get(hi).set(8, 2);

		 			}
		 			
		 		else if(arhead.get(hi).get(8)==2) {
		 			

					arhead.get(hi).set(0, cx);
	 				arhead.get(hi).set(1, cy+10);
	 				
	 				arhead.get(hi).set(2, cx+6);
	 				arhead.get(hi).set(3, cy+25);

	 				arhead.get(hi).set(4, cx-6);
	 				arhead.get(hi).set(5, cy+25);
	 			
		 			
	 				arhead.get(hi).set(8, 3);

		 			}
		 			
			else if(arhead.get(hi).get(8)==3) {
		
				arhead.get(hi).set(0, cx-10);
 				arhead.get(hi).set(1, cy);
 				
 				arhead.get(hi).set(2, cx-25);
 				arhead.get(hi).set(3, cy+6);

 				arhead.get(hi).set(4, cx-25);
 				arhead.get(hi).set(5, cy-6);
 			
	
			
 				arhead.get(hi).set(8, 0);

			}
	
		 			 	 arrows.get(hi).set(4,  (double) ((arhead.get(hi).get(2) + arhead.get(hi).get(4) )/2) );
				 	 
				 	 arrows.get(hi).set(5,  (double) ((arhead.get(hi).get(3) + arhead.get(hi).get(5) )/2));		
		 	drawAll();		
		 			
		 		}
		 		arrowh=false;
		 		
		 	
		 	}}
		 
		 	@Override
		 	public void mouseReleased(MouseEvent e) {
             sarrow=-1;
		 	
		 	}
		 	@Override
		 	public void mousePressed(MouseEvent e) {
		 	
		 	
		 		if(sbut==1) {
		 	      /* 		
		 			for(int i=0; i<arhead.size(); i++) {
		 				if(isInside(arhead.get(i).get(0),arhead.get(i).get(1),arhead.get(i).get(2),arhead.get(i).get(3),arhead.get(i).get(4)  ,arhead.get(i).get(5) , e.getX(),e.getY() )){
		 				   pp.x=e.getX();
		 				   pp.y=e.getY();
		 				  hi=i;
		 				  arrowh=true;
		 				  System.out.println("inside ");
		 				  break;
		 				  
		 				}
		 				
		 				
		 			}	 
		 	 		  
		 			*/
		 			
		 	 
		 			if(!arrowh) {
		 			
		 			
		 			for(int i=0; i<arrows.size(); i++) {

		 				System.out.println(e.getX() +" "+e.getY());
		 				
		 				QuadCurve2D q = new QuadCurve2D.Double();
		 				q.setCurve(arrows.get(i).get(0),arrows.get(i).get(1),arrows.get(i).get(2),arrows.get(i).get(3),arrows.get(i).get(4),arrows.get(i).get(5));
		 				
		 				Rectangle2D f=q.getBounds2D();
		 				  System.out.println((f.getCenterX())+"  gg  "+(f.getCenterY()));
		 				if(   Math.abs((f.getCenterX())-e.getX())<55  &&  Math.abs((f.getCenterY())-e.getY())<55 ) {
		 					sarrow=i;
			 				System.out.println("ok");

		 					break;
		 				}
		 				
		 			}}
		 			
		 			
		 		}
		 	
		 	
		 	
		 	
		 	
		 	}
		 });
		c.setBounds(318, 0, 854, 642);
	//	Graphics gc=  c.getGraphics();
     
		contentPane.add(c);

		
	//	Graphics gc=  c.getGraphics();
		
		
		button = new Button("add node\r\n");
		button.setBackground(SystemColor.window);
		button.setFont(new Font("Dialog", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sbut=0;
				button_2.setBackground(Color.white);

				button.setBackground(Color.green);
			  /*	
				Graphics gc=  c.getGraphics();
             //gc.fillRect(100, 100, 100, 100);
			QuadCurve2D q = new QuadCurve2D.Double();
      //   q.setCurve(100 , 100 , 150,100,300,100);
           Graphics2D g2= (Graphics2D) gc;
        //   g2.draw(q);
			
           int x1=100;
           int y1=100;
           int x2=300;
           int y2=100;
           QuadCurve2D.Double my_arc = new QuadCurve2D.Double(100 , 100 , 150,100,300,100);
           QuadCurve2D.Double part1 = new QuadCurve2D.Double();
           QuadCurve2D.Double part2 = new QuadCurve2D.Double();
           my_arc.subdivide(part1, part2);
           
           
           
           double deltaY = y2 - y1 ;
           double deltaX = x2 - x1;
           double angle = Math.atan2(deltaY, deltaX);

           Line2D l = new Line2D.Double((int)x2, (int)y2, (int)x1, (int)y1);

           int px = (int)x2 - 5;
           int py = (int)y2;         

           Polygon pp = new Polygon(new int[] {px, px+5, px+10},new int[] {py, py+15, py}, 3 );

           AffineTransform tt = new AffineTransform();
           tt.rotate(angle, pp.getBounds2D().getCenterX(), pp.getBounds2D().getCenterY());

           g2.draw(tt.createTransformedShape(pp));
           g2.setColor(Color.red);
           g2.draw(l);
           g2.setColor(Color.pink);
           g2.draw(pp);
           */
           
			}
		});
		button.setBounds(22, 38, 119, 34);
		contentPane.add(button);
		
		JLabel lblFrom = new JLabel("from\r\n");
		lblFrom.setForeground(Color.PINK);
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFrom.setBounds(10, 129, 78, 34);
		contentPane.add(lblFrom);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(63, 135, 149, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFrom_1 = new JLabel("to\r\n");
		lblFrom_1.setForeground(Color.PINK);
		lblFrom_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFrom_1.setBounds(22, 186, 78, 34);
		contentPane.add(lblFrom_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(63, 192, 149, 30);
		contentPane.add(textField_1);
		
		Button button_1 = new Button("CONNECT\r\n");				
		button_1.setBackground(Color.PINK);

		
		button_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_2.setBackground(Color.white);
//                if(textField.getText()==null || textField_1.getText()=="" || textField_2.getText()=="") {
//                	
//                }
				int a,b;
				double cc;
				try {
				 a=Integer.parseInt(textField.getText())-1;
				 b=Integer.parseInt(textField_1.getText())-1;
				 cc=Double.parseDouble(textField_2.getText());
				 if(a>=nodes.size() || b>nodes.size()) {
						JOptionPane.showMessageDialog(null, "Enter a valid nodes and gain");

	                	return;		 
				 }
				}
				catch(Exception eee) {
					JOptionPane.showMessageDialog(null, "Enter a valid nodes and gain");

                	return;
				}
				
				Graphics gc=  c.getGraphics();
		          gc.setFont(new Font("Tahoma", Font.BOLD, 10));
 
				//gc.drawLine(nodes.get(a).get(0), nodes.get(a).get(1), nodes.get(b).get(0), nodes.get(b).get(1));
				//gc.fillRect(100, 100, 100, 100);
				 ArrayList <Integer> temp1 =new ArrayList<>();
				 temp1.add(nodes.get(b).get(0)-10);
				 temp1.add(nodes.get(b).get(1));
				 
				 temp1.add(nodes.get(b).get(0)-25);
				 temp1.add(nodes.get(b).get(1)+6);
				 
				 temp1.add(nodes.get(b).get(0)-25);
				 temp1.add(nodes.get(b).get(1)-6);
				 
				 temp1.add(nodes.get(b).get(0));
				 temp1.add(nodes.get(b).get(1));
				 temp1.add(0);
			     adjList.get(a).add(new myPair(b,cc));
				 
				 
				 arhead.add(temp1);
				QuadCurve2D q = new QuadCurve2D.Double();
	         q.setCurve(nodes.get(a).get(0) , nodes.get(a).get(1) , (nodes.get(a).get(0)+nodes.get(b).get(0))/2,(nodes.get(a).get(1)+nodes.get(b).get(1))/2 ,temp1.get(2), nodes.get(b).get(1));
	           Graphics2D g2= (Graphics2D) gc;
	         g2.draw(q);
				int qq=nodes.get(a).get(0);
				int w=nodes.get(a).get(1);
				int ee=(nodes.get(a).get(0)+nodes.get(b).get(0))/2;
			    int r=(nodes.get(a).get(1)+nodes.get(b).get(1))/2;
			    int t=temp1.get(2);
			    int y=nodes.get(b).get(1);
			    ArrayList <Double> temp =new ArrayList<>();
			    temp.add((double) qq);			
			    temp.add((double) w);			
			    temp.add((double) ee);			
			    temp.add((double) r);			
			    temp.add((double) t);			
			    temp.add((double) y);
				 temp.add(cc);
			    arrows.add(temp);
			   drawAll();			    

			}
		});
		button_1.setBounds(63, 312, 149, 34);
		contentPane.add(button_1);
		
		button_2 = new Button("adjust arrow\r\n");
		button_2.setBackground(Color.white);

		button_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adjust ad=new adjust();
				ad.setVisible(true);
				sbut=1;
				button_2.setBackground(Color.green);
				button.setBackground(Color.white);

			}
		});
		button_2.setBounds(160, 38, 119, 34);
		contentPane.add(button_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(63, 250, 149, 30);
		contentPane.add(textField_2);
		
		JLabel lblFrom_1_1 = new JLabel("Gain\r\n");
		lblFrom_1_1.setForeground(Color.PINK);
		lblFrom_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFrom_1_1.setBounds(10, 244, 78, 34);
		contentPane.add(lblFrom_1_1);
		
		Button button_1_1 = new Button("CALCULATE\r\n");
		button_1_1.setBackground(Color.PINK);
		button_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String paths =null;
				String loops =null;
				String deltas = null ;
				String transfer=null;
				int inputNode;
				int outputNode;
				try {
					inputNode=Integer.parseInt(inn.getText());
					 outputNode=Integer.parseInt(outn.getText());

				}
				catch(Exception eee) {
					JOptionPane.showMessageDialog(null, "Enter a valid input and output nodes");
					return;
				}
				
				
				///
				//call the logic class with parameters (adjList , inputNode , outputNode)
			//	and set the values of the string  
				
				
				
				
				////
				
				
				
				
				ans an=new ans(paths,loops,deltas,transfer);
		
		
		
		an.setVisible(true);
			
			}
		});
		button_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		button_1_1.setBounds(63, 495, 149, 34);
		contentPane.add(button_1_1);
		
		JLabel lblFrom_1_1_1 = new JLabel("in\r\n\r\n");
		lblFrom_1_1_1.setForeground(Color.PINK);
		lblFrom_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFrom_1_1_1.setBounds(22, 396, 78, 34);
		contentPane.add(lblFrom_1_1_1);
		
		inn = new JTextField();
		inn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inn.setColumns(10);
		inn.setBounds(63, 402, 149, 30);
		contentPane.add(inn);
		
		JLabel lblFrom_1_1_1_1 = new JLabel("out\r\n");
		lblFrom_1_1_1_1.setForeground(Color.PINK);
		lblFrom_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFrom_1_1_1_1.setBounds(10, 440, 78, 34);
		contentPane.add(lblFrom_1_1_1_1);
		
		outn = new JTextField();
		outn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		outn.setColumns(10);
		outn.setBounds(63, 440, 149, 30);
		contentPane.add(outn);
		
		Button clear = new Button("CLEAR\r\n");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setBackground(Color.white);
				button_2.setBackground(Color.white);

				counter=1;
				 sbut=-1;
				 sarrow=-1;
				 nodes =new ArrayList<>();
				 arrows =new ArrayList<>();
			 arhead =new ArrayList<>();
				 adjList = new ArrayList<>();
				
				
				 arrowh=false;
				 hi=-1;
				
				 pp =new Point();
				 ccl();			
			}
		});
		clear.setFont(new Font("Dialog", Font.PLAIN, 14));
		clear.setBackground(Color.PINK);
		clear.setBounds(63, 552, 149, 34);
		contentPane.add(clear);
	
	}

	public void ccl() {
		Graphics gc=  c.getGraphics();
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());	
	}
	
public void drawAll() {
	
	Graphics gc=  c.getGraphics();
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());
	for(int i=0; i<arrows.size(); i++) {
		QuadCurve2D q = new QuadCurve2D.Double();
		//q.
		Double qq=arrows.get(i).get(0);
		Double w=arrows.get(i).get(1);
		Double ee=(arrows.get(i).get(2));
	    Double r=(arrows.get(i).get(3));
	    Double t=arrows.get(i).get(4);
	    Double y=arrows.get(i).get(5);
          q.setCurve(qq,w,ee,r,t,y);		
        //  int x=q
          Rectangle2D rr=q.getBounds2D();
          
         
          gc.setFont(new Font("Tahoma", Font.BOLD, 10));
          gc.drawString(""+arrows.get(i).get(6)  ,(int)rr.getCenterX() ,(int) rr.getCenterY() +10);

          
          Graphics2D g2= (Graphics2D) gc;
        g2.draw(q);
		
		
	}
	
	for(int i=0; i<nodes.size(); i++) {
		
		gc.fillOval(nodes.get(i).get(0)-10, nodes.get(i).get(1)-10,20,20);
        gc.drawString(""+nodes.get(i).get(2),nodes.get(i).get(0) -10 , nodes.get(i).get(1)-10);
        
        
	}
	
	for(int i=0; i<arhead.size(); i++) {
		gc.drawLine(arhead.get(i).get(0), arhead.get(i).get(1), arhead.get(i).get(2), arhead.get(i).get(3));
		gc.drawLine(arhead.get(i).get(2), arhead.get(i).get(3), arhead.get(i).get(4), arhead.get(i).get(5));
		gc.drawLine(arhead.get(i).get(4), arhead.get(i).get(5), arhead.get(i).get(0), arhead.get(i).get(1));
		//gc.
	   int []X= {arhead.get(i).get(0),arhead.get(i).get(2),arhead.get(i).get(4)};
	    int [] Y = {arhead.get(i).get(1),arhead.get(i).get(3),arhead.get(i).get(5)};
	//    	Graphics2D g2= (Graphics2D) gc;
	 // int [] X = {10 , 100, 200};
	 // int [] Y = {10,30,150};
	  gc.setColor(Color.red);
	  //System.out.println("jjj");
	    gc.fillPolygon(X, Y, 3);  
	
	
	     //g2.fill();
	//	gc.fillOval(nodes.get(i).get(0)-10, nodes.get(i).get(1)-10,20,20);
      //  gc.drawString(""+nodes.get(i).get(2),nodes.get(i).get(0) -10 , nodes.get(i).get(1)-10);
	}	
	
	
}
static double area(int x1, int y1, int x2, int y2,
        int x3, int y3)
{
return Math.abs((x1*(y2-y3) + x2*(y3-y1)+
    x3*(y1-y2))/2.0);
}

/* A function to check whether point P(x, y) lies
inside the triangle formed by A(x1, y1),
B(x2, y2) and C(x3, y3) */
static boolean isInside(int x1, int y1, int x2,
int y2, int x3, int y3, int x, int y)
{  
/* Calculate area of triangle ABC */
double A = area (x1, y1, x2, y2, x3, y3);

/* Calculate area of triangle PBC */ 
double A1 = area (x, y, x2, y2, x3, y3);

/* Calculate area of triangle PAC */ 
double A2 = area (x1, y1, x, y, x3, y3);

/* Calculate area of triangle PAB */  
double A3 = area (x1, y1, x2, y2, x, y);

/* Check if sum of A1, A2 and A3 is same as A */
return (A == A1 + A2 + A3);
}

public Point rotatePoint(Point pt, Point center, double angleRad)
{
    //double angleRad = ((angleDeg/180)*Math.PI);
    double cosAngle = Math.cos(angleRad );
    double sinAngle = Math.sin(angleRad );
    double dx = (pt.x-center.x);
    double dy = (pt.y-center.y);

    pt.x = center.x + (int) (dx*cosAngle-dy*sinAngle);
    pt.y = center.y + (int) (dx*sinAngle+dy*cosAngle);
    return pt;
}
}
