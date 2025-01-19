package practica2;

//import practica4.OptionsDialog;

import practica4.OptionsDialog;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EventoMostrarAlgoritmosFirmaDisponibles implements ActionListener {
    private final VentanaPrincipalP2 ventanaPrincipal;

    // Constructor que recibe una referencia a VentanaPrincipalP2
    public EventoMostrarAlgoritmosFirmaDisponibles(VentanaPrincipalP2 ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    @Override
    public final void actionPerformed(ActionEvent paramActionEvent) {
        // Crear un objeto OptionsDialog usando los parámetros necesarios
        OptionsDialog optionsDialog = new OptionsDialog(ventanaPrincipal.getSignatureOptions());
        optionsDialog.pack();

        // Obtener la ubicación de la ventana principal
        Point point = ventanaPrincipal.ventanaPrincipal.getLocation();
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