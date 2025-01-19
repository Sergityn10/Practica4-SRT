package practica3;

/**
 * Clase para crear el thread que utiliza Swing para inicializar la ventana principal,
 * correspondiente con la practica 3
 *
 */
public class EventoVisibilizarVentanaPractica3 implements Runnable {
  public final void run() {
    Practica3 practica3 = new Practica3();
    practica3.ventanaPrincipal.setVisible(true);
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica3\J.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */