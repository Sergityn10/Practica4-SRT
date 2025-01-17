package practica4;

import java.awt.Container;
import java.awt.Insets;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import srt.C;

public class Z implements C {
  JDialog j;
  
  Container add;
  
  JProgressBar append;
  
  JTextArea getContentPane;
  
  public Z(JFrame paramJFrame, String paramString) {
    this.j = new JDialog(paramJFrame, paramString);
    this.add = this.j.getContentPane();
    this.j.setDefaultCloseOperation(2);
    this.append = new JProgressBar();
    this.append.setStringPainted(true);
    this.append.setValue(0);
    this.getContentPane = new JTextArea(7, 80);
    this.getContentPane.setMargin(new Insets(5, 5, 5, 5));
    this.getContentPane.setEditable(false);
    this.add.add(new JScrollPane(this.getContentPane), "Center");
    this.add.add(this.append, "North");
    this.j.pack();
    this.j.setVisible(true);
  }
  
  public final void I(String paramString) {
    this.getContentPane.append(paramString);
  }
  
  public final void I(int paramInt) {
    this.append.setValue(paramInt);
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\CifradoOriginal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */