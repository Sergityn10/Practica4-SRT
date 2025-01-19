package practica2;

/**
 * Clase utilizada para crear el thread que necesita Swing para inicializar y visualizar
 * la ventana principal de nuestra aplicación.
 */
class VisibilizarVentanaPrincipal implements Runnable {
  public final void run() {
    VentanaPrincipalP2 ventanaPrincipalP2 = new VentanaPrincipalP2();
    ventanaPrincipalP2.crearVentanaPrincipal().setVisible(true);
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica2\VisibilizarVentanaPrincipal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */