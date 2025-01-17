package practica4;

/*class D implements Runnable {
  public final void run() {
    Practica4 practica4 = new Practica4();
    Practica4.I(practica4).setVisible(true);
  }
}*/

public class D implements Runnable {
  @Override
  public final void run() {
    // Crear una instancia de Practica4 (asumo que extiende JFrame)
    Practica4 practica4 = new Practica4();

    // Configurar la ventana como visible
    practica4.I.setVisible(true);
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */