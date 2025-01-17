package srt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutBox extends JDialog implements ActionListener {
  JPanel panel1 = new JPanel();
  
  JPanel panel2 = new JPanel();
  
  JPanel insetsPanel1 = new JPanel();
  
  JPanel insetsPanel2 = new JPanel();
  
  JPanel insetsPanel3 = new JPanel();
  
  JButton button1 = new JButton();
  
  JLabel imageLabel = new JLabel();
  
  JLabel label1 = new JLabel();
  
  JLabel label2 = new JLabel();
  
  JLabel label3 = new JLabel();
  
  JLabel label4 = new JLabel();
  
  BorderLayout borderLayout1 = new BorderLayout();
  
  BorderLayout borderLayout2 = new BorderLayout();
  
  FlowLayout flowLayout1 = new FlowLayout();
  
  GridLayout gridLayout1 = new GridLayout();
  
  Mensajes mensajes;
  
  public AboutBox(Mensajes paramMensajes) {
    enableEvents(64L);
    this.mensajes = paramMensajes;
    try {
      add();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    pack();
  }
  
  private void add() {
    this.imageLabel.setIcon(new ImageIcon(AboutBox.class.getResource(this.mensajes.getProperty("icono"))));
    setTitle("Acerca de...");
    setResizable(false);
    setModal(true);
    this.panel1.setLayout(this.borderLayout1);
    this.panel2.setLayout(this.borderLayout2);
    this.insetsPanel1.setLayout(this.flowLayout1);
    this.insetsPanel2.setLayout(this.flowLayout1);
    this.insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    this.gridLayout1.setRows(4);
    this.gridLayout1.setColumns(1);
    this.label1.setText(this.mensajes.getProperty("product"));
    this.label2.setText(this.mensajes.getProperty("version"));
    this.label3.setText(this.mensajes.getProperty("copyright"));
    this.label4.setText(this.mensajes.getProperty("comments"));
    this.insetsPanel3.setLayout(this.gridLayout1);
    this.insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
    this.button1.setText("Ok");
    this.button1.addActionListener(this);
    this.insetsPanel2.add(this.imageLabel, (Object)null);
    this.panel2.add(this.insetsPanel2, "West");
    getContentPane().add(this.panel1, (Object)null);
    this.insetsPanel3.add(this.label1, (Object)null);
    this.insetsPanel3.add(this.label2, (Object)null);
    this.insetsPanel3.add(this.label3, (Object)null);
    this.insetsPanel3.add(this.label4, (Object)null);
    this.panel2.add(this.insetsPanel3, "Center");
    this.insetsPanel1.add(this.button1, (Object)null);
    this.panel1.add(this.insetsPanel1, "South");
    this.panel1.add(this.panel2, "North");
  }
  
  protected final void processWindowEvent(WindowEvent paramWindowEvent) {
    if (paramWindowEvent.getID() == 201)
      cancel(); 
    super.processWindowEvent(paramWindowEvent);
  }
  
  private final void cancel() {
    dispose();
  }
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    if (paramActionEvent.getSource() == this.button1)
      cancel(); 
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\srt\AboutBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */