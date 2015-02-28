package windows;

import hoverboard.BDD;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Cavoleau
 */
public abstract class Widget extends JInternalFrame implements ActionListener, MouseMotionListener {
    protected int idDashboard;
    protected int idWidget;
    protected int height;
    protected int width;
    protected int positionX;
    protected int positionY;
    protected int oldX;
    protected int oldY;
    
    protected BDD connexion = new BDD();
    protected Dimension buttonSize = new Dimension(16,15);
    protected JButton save = new JButton(new ImageIcon("src/ressources/save.png"));
    protected JButton del = new JButton(new ImageIcon("src/ressources/delete.png"));
    protected JPanel buttons = new JPanel();
    protected JPanel settings = new JPanel();
    protected JPanel content = new JPanel();
    
    @SuppressWarnings("LeakingThisInConstructor")
    public Widget(){
        super();
        this.save.setPreferredSize(buttonSize);
        this.del.setPreferredSize(buttonSize);
        save.addActionListener(this);
        del.addActionListener(this);
        buttons.add(save);
        buttons.add(del);
        settings.setLayout(new BorderLayout());
        settings.add(buttons, BorderLayout.EAST);
        this.add(settings, BorderLayout.NORTH);
        this.add(content, BorderLayout.CENTER);
        this.setResizable(true);
        this.setVisible(true);
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        
        settings.addMouseMotionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == save) {
            this.updateWidget(this.idWidget);
        }
        else if (source == del){
            this.dispose();
            this.connexion.deleteWidget(this.idWidget);
            System.out.println("Je supprime "+this.idWidget);
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent event) {
       // if (contains(e.getX(),e.getY())) { A utiliser pour que le post it reste dans le cadre
            positionX += event.getX();
            positionY += event.getY();
            setBounds(positionX,positionY,height,width);
       //} 
    }
    @Override
    public void mouseMoved(MouseEvent event) {
	 
    }
    
    public void updateWidget(int idWidget) {
        System.out.println ("Ici update"); 
        // POUR LE LOCAL : Je récupère le nouveau contenu du JTextField, je l'envoie au fichier .xml correspondant
        // POUR LE ONLINE : Ensuite je fais un Update BDD SET content = content
    }
}