package practica3;

import java.awt.Point;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import practica2.VentanaPrincipalP2;
import practica4.SignatureOptions;
import srt.Options;
import srt.PasswdDialog;
import srt.Header;

public class Practica3 extends VentanaPrincipalP2 {
  protected JMenuItem menuItemProtegesConHash = null;
  
  protected JMenuItem menuItemVerificarConHash = null;
  
//  private Options optionsP3 = new Options();
  
  FileProtectoMAC fileProtector = null;
  
  public Practica3() {
    this.fileProtector = new FileProtectoMAC(this);
    /*this.optionsP3.setSymmetricalCipher(Options.symmetricalAlgorithms[0]);
    this.optionsP3.setAuthenticator(Options.hashmacAlgorithms[0]);*/
    try {
      this.signatureOptions = (SignatureOptions) SignatureOptions.load(SignatureOptions.optionsFileName);
    } catch (Exception exception) {
      this.signatureOptions = new SignatureOptions();
    }
    this.crearBarraMenuP3();
    this.crearVentanaPrincipal();
  }
  /**
   * Crea y retorna la barra de menú principal.
   *
   * @return Barra de menú principal con opciones configuradas.
   */
  protected final JMenuBar crearBarraMenuP3() {
    if (this.barraMenu == null) {
      this.barraMenu = new JMenuBar();
      this.barraMenu.add(crearMenuFicheroP3());
      this.barraMenu.add(crearMenuOpcionesP3());
      this.barraMenu.add(crearMenuAyuda());
    } 
    return this.barraMenu;
  }
  /**
   * Crea y retorna el menú "Fichero" con las opciones de cifrado y descifrado.
   *
   * @return Menú "Fichero" configurado.
   */
  protected final JMenu crearMenuFicheroP3() {
    if (this.menuFichero == null) {
      this.menuFichero = new JMenu();
      this.menuFichero.setText("Fichero");
      this.menuFichero.add(crearMenuItemCifrar());
      this.menuFichero.add(crearMenuItemDescifrar());
      this.menuFichero.add(crearMenuItemProtegerConHash());
      this.menuFichero.add(crearMenuItemVerificarConHash());
      this.menuFichero.add(crearMenuItemSalir());
    } 
    return this.menuFichero;
  }
  /**
   * Crea y retorna el menú "Opciones".
   *
   * @return Menú "Opciones" configurado.
   */
  protected final JMenu crearMenuOpcionesP3() {
    if (this.menuOpciones == null) {
      this.menuOpciones = new JMenu();
      this.menuOpciones.setText("Opciones");
      this.menuOpciones.add(crearMenuItemOpcionesP3());
      this.menuOpciones.add(crearMenuItemVerInformacionP3());
    } 
    return this.menuOpciones;
  }
  /**
   * Crea y retorna un menú item para configurar opciones.
   *
   * @return Menú item "Opciones" configurado.
   */
  protected final JMenuItem crearMenuItemOpcionesP3() {
    if (this.menuItemOpciones == null) {
      this.menuItemOpciones = new JMenuItem();
      this.menuItemOpciones.setText("Opciones");
      this.menuItemOpciones.setAccelerator(KeyStroke.getKeyStroke(79, 2, true));
      this.menuItemOpciones.addActionListener(new EventoMostrarOpcionesFirmas(this));
    } 
    return this.menuItemOpciones;
  }
  /**
   * Crea la opción "Ver información" dentro del menú "Opciones" con un atajo de teclado.
   *
   * @return JMenuItem configurado.
   */

  private JMenuItem crearMenuItemVerInformacionP3() {
    if (this.menuItemVerInformacion == null) {
      this.menuItemVerInformacion = new JMenuItem();
      this.menuItemVerInformacion.setText("Ver información");
      this.menuItemVerInformacion.setAccelerator(KeyStroke.getKeyStroke(73, 2, true));
      this.menuItemVerInformacion.addActionListener(new EventoVerInformacion(this));
    } 
    return this.menuItemVerInformacion;
  }
  /**
   * Crea y retorna el menú item para proteger un archivo con hash.
   *
   * @return Menú item "Proteger con hash" configurado.
   */
  protected final JMenuItem crearMenuItemProtegerConHash() {
    if (this.menuItemProtegesConHash == null) {
      this.menuItemProtegesConHash = new JMenuItem();
      this.menuItemProtegesConHash.setText("Proteger con hash");
      this.menuItemProtegesConHash.setAccelerator(KeyStroke.getKeyStroke(72, 2, true));
      this.menuItemProtegesConHash.addActionListener(new EventoProtegerConHash(this));
    } 
    return this.menuItemProtegesConHash;
  }
  /**
   * Crea y retorna el menú item para verificar un hash.
   *
   * @return Menú item "Verificar hash" configurado.
   */
  protected final JMenuItem crearMenuItemVerificarConHash() {
    if (this.menuItemVerificarConHash == null) {
      this.menuItemVerificarConHash = new JMenuItem();
      this.menuItemVerificarConHash.setText("Verificar hash");
      this.menuItemVerificarConHash.setAccelerator(KeyStroke.getKeyStroke(86, 2, true));
      this.menuItemVerificarConHash.addActionListener(new EventoVerificarConHash(this));
    } 
    return this.menuItemVerificarConHash;
  }
  /**
   * Muestra un mensaje en el área de texto de la interfaz.
   *
   * @param mensaje Mensaje que se desea mostrar.
   */
  public void mostrarMensaje(String mensaje) {
    this.areaText.append(mensaje);
    this.ventanaPrincipal.repaint();
  }
  /**
   * Método principal para iniciar la aplicación.
   *
   * @param args Argumentos de línea de comandos.
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new EventoVisibilizarVentanaPractica3());
  }
  /**
   * Protege un archivo utilizando un hash o HMAC. Utiliza los objetos guardados en la clase
   */
  public final void protegerConHash() {
    String clavePrivada;
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setDialogTitle("Fichero a proteger con Hash/HMac");
    if (Options.isTypeAlgorithm(Options.hashAlgorithms, this.signatureOptions.getAuthenticator())) {
      clavePrivada = "Secreto";
    } else {
      clavePrivada = "Contraseña";
    } 
    int i = jFileChooser.showOpenDialog(this.panelContenido);
    if (i == 0) {
      String rutaArchivo = jFileChooser.getSelectedFile().getAbsolutePath();
      PasswdDialog passwdDialog = new PasswdDialog(clavePrivada, (byte)1);
      passwdDialog.pack();
      Point point = crearVentanaPrincipal().getLocation();
      point.translate(20, 20);
      passwdDialog.setLocation(point);
      passwdDialog.setVisible(true);
      String clave = new String(passwdDialog.getPasswd());
      try {
        if (Options.isTypeAlgorithm(Options.hashAlgorithms, this.signatureOptions.getAuthenticator())) {
          this.fileProtector.applyHash(rutaArchivo, cambioExtensionFichero(rutaArchivo, "hsh"), clave, this.getSignatureOptions().getAuthenticator());
        } else {
          this.fileProtector.applyHMAC(rutaArchivo, cambioExtensionFichero(rutaArchivo, "mac"), clave, this.getSignatureOptions().getAuthenticator());
        } 
      } catch (Exception exception) {
        this.areaText.append(exception.getMessage());
      } 
    } 
  }
  /**
   * Verifica el hash de un archivo seleccionado.
   */
  public final void verificarHash() {
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setDialogTitle("Fichero a verificar");
    int i = jFileChooser.showOpenDialog(this.panelContenido);
    if (i == 0) {
      String rutaArchivo = jFileChooser.getSelectedFile().getAbsolutePath();
      Header encabezado = new Header();
      if (this.fileProtector.cargarCabeceraArchivo(rutaArchivo, encabezado)) {
        String tipoClave;
        if (Options.isTypeAlgorithm(Options.hashAlgorithms, encabezado.getAlgorithm2())) {
          tipoClave = "Secreto";
        } else {
          tipoClave = "Contraseña";
        } 
        PasswdDialog passwdDialog = new PasswdDialog(tipoClave, (byte)0);
        passwdDialog.pack();
        Point point = crearVentanaPrincipal().getLocation();
        point.translate(20, 20);
        passwdDialog.setLocation(point);
        passwdDialog.setVisible(true);
        String clave = new String(passwdDialog.getPasswd());
        try {
          if (Options.isTypeAlgorithm(Options.hashAlgorithms, encabezado.getAlgorithm2())) {
            this.fileProtector.verifyHash(rutaArchivo, cambioExtensionFichero(rutaArchivo, "cla"), clave, encabezado.getAlgorithm2());
          } else {
            this.fileProtector.verifyHMAC(rutaArchivo, cambioExtensionFichero(rutaArchivo, "cla"), clave, encabezado.getAlgorithm2());
          } 
        } catch (Exception exception) {
          this.areaText.append(exception.getMessage());
        } 
      } else {
        this.areaText.append("El fichero no contine un hash.");
      } 
    } 
  }
  /**
   * Muestra información sobre los algoritmos de cifrado y hash disponibles.
   */
  public final void verInformacion() {
    this.fileProtector.MostrarInformacionAlgoritmosCifrado();
    this.fileProtector.MostrarInformacionAlgoritmosResumen();
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica3\S.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */