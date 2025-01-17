package practica2;

//import practica4.OptionsDialog;

import practica4.OptionsDialog;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class D implements ActionListener {
  D(VentanaPrincipalP2 paramVentanaPrincipalP2) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    OptionsDialog optionsDialog = new OptionsDialog(VentanaPrincipalP2.Z(this.getLocation));
    optionsDialog.pack();
    Point point = this.getLocation.I().getLocation();
    point.translate(20, 20);
    optionsDialog.setLocation(point);
    optionsDialog.setVisible(true);
  }
}*/
public class D implements ActionListener {
    private final VentanaPrincipalP2 ventanaPrincipal;

    // Constructor que recibe una referencia a VentanaPrincipalP2
    public D(VentanaPrincipalP2 ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    @Override
    public final void actionPerformed(ActionEvent paramActionEvent) {
        // Crear un objeto OptionsDialog usando los parámetros necesarios
        OptionsDialog optionsDialog = new OptionsDialog(ventanaPrincipal.getSignatureOptions());
        optionsDialog.pack();

        // Obtener la ubicación de la ventana principal
        Point point = ventanaPrincipal.I.getLocation();
        point.translate(20, 20); // Desplazar la ubicación para posicionar el diálogo
        optionsDialog.setLocation(point);

        // Hacer visible el cuadro de diálogo
        optionsDialog.setVisible(true);
    }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica2\D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */