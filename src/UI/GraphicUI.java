package UI;

import Jeu.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicUI extends Frame{

	private Monopoly monopoly;
	
	
    public GraphicUI (Monopoly m){
            super();
            monopoly=m;
            
            
            setLayout(new BorderLayout());
        
            JPanel panelBouton = new JPanel(); 

            JButton btn_nouveau = new JButton("BLA");
            panelBouton.add(btn_nouveau);
            
            JButton btn_infoTerrains = new JButton("BLO");
            panelBouton.add(btn_infoTerrains) ;

            
            JButton btn_quitter = new JButton("BU");
            panelBouton.add(btn_quitter);
            
            this.add(panelBouton,BorderLayout.SOUTH);
            
    }
            
            
     public static void main(String [] args) {
            	
                     Monopoly monopoly = new Monopoly();
                     JFrame cadre = new JFrame();
                                              
                     Toolkit kit = Toolkit.getDefaultToolkit();
                     Dimension screenSize = kit.getScreenSize();
                     
                     cadre.setSize(screenSize.width-200,screenSize.height-65);
                                     
                     cadre.setTitle("Monopoly");
                     
                     cadre.setVisible(true);
                     
                     
                     
		JPanel panel = new JPanel();
                
                
                panel.add(new JLabel(new ImageIcon("/users/info/etu-s2/derouxj/m2105/monopoly2/monopoly2/plateau.jpg")));
                
		cadre.add(panel);
		cadre.setVisible(true);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadre.setSize(1300, 700);
                     
                
                for (Carreau c : monopoly.getCarreaux().values()){
                    
                   
                    
                    
                }
                     
                     
                     
                     
            }
      
     
     
     
     


}