package practica4;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import practica3.Practica3;

public class Practica4 extends Practica3 {
  private JMenuItem menuItemCifradoAsimetrico = null;

  private JMenuItem menuItemDescifradoAsimetrico = null;
  
  private JMenuItem menuItemFirmar = null;
  
  private JMenuItem menuItemVerificarFirma = null;
  
  protected JMenuItem menuItemGenerarNuevasClaves = null;

  protected JMenuItem menuItemVerClaves = null;
  
  protected JMenuItem menuItemCargarClaves = null;
  
  protected JMenuItem menuItemGuardarClaves = null;
  
//  protected SignatureOptions signtureOpts;

  protected ParClavesP4 keypair;

  protected DigitalSignature digitalSignature;
  
  private JMenu menuCifradoSimetrico;
  
  private JMenu menuHashMac;
  
  private JMenu menuCifradoAsimetrico;
  
  private JMenu menuFirmaDigital;

  /**
   * Constructor de la clase Practica4. Inicializa las configuraciones, las claves y la ventana principal.
   */
  public Practica4() {
    //SE CARGAN EN LA VARIABLE signtureOpts el par de claves del fichero por defecto.
    try {
      this.signatureOptions = (SignatureOptions) SignatureOptions.load(SignatureOptions.optionsFileName);
    } catch (Exception exception) {
      this.signatureOptions = new SignatureOptions();
    }

    //Se inicializan los parametros de las opciones de la firma y el par de claves.
    this.digitalSignature = new DigitalSignature(this);
    this.keypair = new ParClavesP4();
    this.keypair.loadKeyPair(this.signatureOptions.getKeyFilename(), this.signatureOptions.getKeyFilePasswd());
    this.crearVentanaPrincipal();
  }
  /**
   * Obtiene las opciones de firma actuales.
   *
   * @return Objeto SignatureOptions con las opciones configuradas.
   */
  public SignatureOptions getSignatureOptions() {
    return this.signatureOptions;
  }
  /**
   * Establece nuevas opciones de firma.
   *
   * @param opciones Objeto SignatureOptions con las nuevas configuraciones.
   */
  public void setSignaturaOption(SignatureOptions opciones) {
    this.signatureOptions = opciones;
  }

  /**
   * Crea la barra de menú principal.
   *
   * @return Objeto JMenuBar con los menús configurados.
   */
  protected final JMenuBar crearBarraMenu() {
    if (this.barraMenu == null) {
      this.barraMenu = new JMenuBar();
      this.barraMenu.add(crearMenuFichero());
      this.barraMenu.add(crearMenuClaves());
      this.barraMenu.add(crearMenuAyuda());
    } 
    return this.barraMenu;
  }
  /**
   * Crea el menú "Fichero" con opciones relacionadas.
   *
   * @return Objeto JMenu con las opciones del menú "Fichero".
   */
  protected final JMenu crearMenuFichero() {
    if (this.menuFichero == null) {
      this.menuFichero = new JMenu();
      this.menuFichero.setText("Fichero");
      this.menuFichero.add(crearMenuCifradoSimetrico());
      this.menuFichero.add(crearMenuHashMAC());
      this.menuFichero.add(crearMenuCifradoAsimetrico());
      this.menuFichero.add(crearMenuFirmaDigital());
      this.menuFichero.add(crearMenuItemSalir());
    } 
    return this.menuFichero;
  }
  /**
   * Crea el menú "Claves" con opciones relacionadas con la gestión de claves.
   *
   * @return Objeto JMenu con las opciones del menú "Claves".
   */
  private JMenu crearMenuClaves() {
    if (this.menuOpciones == null) {
      this.menuOpciones = new JMenu();
      this.menuOpciones.setText("Claves");
      this.menuOpciones.add(this.crearMenuItemOpciones());
      this.menuOpciones.add(getPublicCipher());
      this.menuOpciones.add(crearMenuItemCargarClaves());
      this.menuOpciones.add(crearMenuItemGuardarClaves());
      this.menuOpciones.add(crearMenuItemVerClaves());
    } 
    return this.menuOpciones;
  }

  /**
   * Crea un JMenuItem para "Opciones de claves".
   *
   * @return Objeto JMenuItem para la configuración de opciones de claves.
   */
  protected final JMenuItem crearMenuItemOpciones() {
    if (this.menuItemOpciones == null) {
      this.menuItemOpciones = new JMenuItem();
      this.menuItemOpciones.setText("Opciones de claves");
      this.menuItemOpciones.setAccelerator(KeyStroke.getKeyStroke(79, 2, true));
      this.menuItemOpciones.addActionListener(new EventoMostrarOpcionesClaves(this));
    } 
    return this.menuItemOpciones;
  }
  /**
   * Crea un JMenuItem para "Generar nuevas claves".
   *
   * @return Objeto JMenuItem para la generación de nuevas claves.
   */
  protected final JMenuItem getPublicCipher() {
    if (this.menuItemGenerarNuevasClaves == null) {
      this.menuItemGenerarNuevasClaves = new JMenuItem();
      this.menuItemGenerarNuevasClaves.setText("Generar nuevas claves");
      this.menuItemGenerarNuevasClaves.setAccelerator(KeyStroke.getKeyStroke(71, 2, true));
      this.menuItemGenerarNuevasClaves.addActionListener(new EventoGenerarNuevasClaves(this));
    } 
    return this.menuItemGenerarNuevasClaves;
  }
  /**
   * Crea un JMenuItem para ver las claves actuales.
   *
   * @return Objeto JMenuItem para visualizar las claves.
   */
  protected final JMenuItem crearMenuItemVerClaves() {
    if (this.menuItemVerClaves == null) {
      this.menuItemVerClaves = new JMenuItem();
      this.menuItemVerClaves.setText("Ver claves actuales");
      this.menuItemVerClaves.addActionListener(new EventoMostrarClavesActuales(this));
    } 
    return this.menuItemVerClaves;
  }
  /**
   * Crea un JMenuItem para cargar claves desde un fichero.
   *
   * @return Objeto JMenuItem para cargar claves.
   */
  protected final JMenuItem crearMenuItemCargarClaves() {
    if (this.menuItemCargarClaves == null) {
      this.menuItemCargarClaves = new JMenuItem();
      this.menuItemCargarClaves.setText("Cargar claves desde fichero");
      this.menuItemCargarClaves.addActionListener(new EventoCargarClavesDesdeFichero(this));
    } 
    return this.menuItemCargarClaves;
  }

  /**
   * Crea un JMenuItem para guardar claves en un fichero.
   *
   * @return Objeto JMenuItem para guardar claves.
   */
  protected final JMenuItem crearMenuItemGuardarClaves() {
    if (this.menuItemGuardarClaves == null) {
      this.menuItemGuardarClaves = new JMenuItem();
      this.menuItemGuardarClaves.setText("Guardar claves en fichero");
      this.menuItemGuardarClaves.addActionListener(new EventoGuardarClavesEnFichero(this));
    } 
    return this.menuItemGuardarClaves;
  }

  /**
   * Crea un JMenuItem para cifrar una clave pública (KU).
   *
   * @return Objeto JMenuItem configurado para cifrar claves públicas.
   */
  private JMenuItem crearMenuItemCifradoAsimetrico() {
    if (this.menuItemCifradoAsimetrico == null) {
      this.menuItemCifradoAsimetrico = new JMenuItem();
      this.menuItemCifradoAsimetrico.setText("Cifrar KU");
      this.menuItemCifradoAsimetrico.setAccelerator(KeyStroke.getKeyStroke(85, 2, true));
      this.menuItemCifradoAsimetrico.addActionListener(new EventoCifradoAsimetrico(this));
    } 
    return this.menuItemCifradoAsimetrico;
  }
  /**
   * Crea un JMenuItem para descifrar una clave privada (KR).
   *
   * @return Objeto JMenuItem configurado para descifrar claves privadas.
   */
  private JMenuItem crearMenuItemDescifradoAsimetrico() {
    if (this.menuItemDescifradoAsimetrico == null) {
      this.menuItemDescifradoAsimetrico = new JMenuItem();
      this.menuItemDescifradoAsimetrico.setText("Descifrar KR");
      this.menuItemDescifradoAsimetrico.setAccelerator(KeyStroke.getKeyStroke(82, 2, true));
      this.menuItemDescifradoAsimetrico.addActionListener(new EventoDesifradoAsimetrico(this));
    } 
    return this.menuItemDescifradoAsimetrico;
  }
  /**
   * Crea un JMenuItem para firmar un archivo.
   *
   * @return Objeto JMenuItem configurado para firmar archivos.
   */
  private JMenuItem crearMenuItemFirmarArchivo() {
    if (this.menuItemFirmar == null) {
      this.menuItemFirmar = new JMenuItem();
      this.menuItemFirmar.setText("Firmar");
      this.menuItemFirmar.setAccelerator(KeyStroke.getKeyStroke(70, 2, true));
      this.menuItemFirmar.addActionListener(new EventoFirmarArchivo(this));
    } 
    return this.menuItemFirmar;
  }

  /**
   * Crea un JMenuItem para verificar la firma de un archivo.
   *
   * @return Objeto JMenuItem configurado para verificar archivos.
   */
  private JMenuItem crearMenuItemVerificarFichero() {
    if (this.menuItemVerificarFirma == null) {
      this.menuItemVerificarFirma = new JMenuItem();
      this.menuItemVerificarFirma.setText("Verificar firma");
      this.menuItemVerificarFirma.setAccelerator(KeyStroke.getKeyStroke(87, 2, true));
      this.menuItemVerificarFirma.addActionListener(new EventoVerificarFirma(this));
    } 
    return this.menuItemVerificarFirma;
  }

  /**
   * Crea el menú "Cifrado simetrico" con opciones relacionadas con el cifrado simetrico
   *
   * @return Objeto JMenu con las opciones del menú "Cifrado simetrico".
   */
  private JMenu crearMenuCifradoSimetrico() {
    if (this.menuCifradoSimetrico == null) {
      this.menuCifradoSimetrico = new JMenu("Cifrado simétrico");
      this.menuCifradoSimetrico.add(crearMenuItemCifrar());
      this.menuCifradoSimetrico.add(crearMenuItemDescifrar());
    } 
    return this.menuCifradoSimetrico;
  }

  /**
   * Crea el menú "Hash/MAC" con opciones relacionadas con el cifrado simetrico
   *
   * @return Objeto JMenu con las opciones del menú "Hash/MAC".
   */
  private JMenu crearMenuHashMAC() {
    if (this.menuHashMac == null) {
      this.menuHashMac = new JMenu("Hash/MAC");
      this.menuHashMac.add(crearMenuItemProtegerConHash());
      this.menuHashMac.add(crearMenuItemVerificarConHash());
    } 
    return this.menuHashMac;
  }
  /**
   * Crea el menú "Cifrado Asimétrico" con opciones de cifrado y descifrado de claves públicas.
   *
   * @return Objeto JMenu con las opciones de cifrado asimétrico.
   */
  private JMenu crearMenuCifradoAsimetrico() {
    if (this.menuCifradoAsimetrico == null) {
      this.menuCifradoAsimetrico = new JMenu("Cifrado asimétrico");
      this.menuCifradoAsimetrico.add(crearMenuItemCifradoAsimetrico());
      this.menuCifradoAsimetrico.add(crearMenuItemDescifradoAsimetrico());
    } 
    return this.menuCifradoAsimetrico;
  }
  /**
   * Crea el menú "Firma Digital" con opciones para firmar y verificar archivos.
   *
   * @return Objeto JMenu con las opciones de firma digital.
   */
  private JMenu crearMenuFirmaDigital() {
    if (this.menuFirmaDigital == null) {
      this.menuFirmaDigital = new JMenu("Firma Digital");
      this.menuFirmaDigital.add(crearMenuItemFirmarArchivo());
      this.menuFirmaDigital.add(crearMenuItemVerificarFichero());
    } 
    return this.menuFirmaDigital;
  }

  /**
   * Muestra un mensaje en el cuadro de texto
   * @param mensaje Mensaje que se desea mostrar.
   */
  public final void mostrarMensaje(String mensaje) {
    this.areaText.append(mensaje);
  }

  /**
   * Reinicia el cuadro de texto. Es decir, limpia su contenido para que muestre un cuadrado blanco, sin texto.
   */
  public final void ReiniciarTextArea() {
    this.areaText.setText("");
  }
  
  public static final void main(String[] paramArrayOfString) {
    SwingUtilities.invokeLater(new VisibilizarVentanaPrincipalP4());
  }

  /**
   * Inicia el proceso de cifrado de un archivo usando la clave pública.
   * Se muestra un cuadro de diálogo para seleccionar el archivo a cifrar.
   */
  protected final void procesoCifradoAsimetrico() {
    if (this.keypair.getIsInicialized()) {
      ReiniciarTextArea();
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setDialogTitle("Fichero a cifrar");
      int i = jFileChooser.showOpenDialog(this.panelContenido);
      if (i == 0) {
        String pathFichero = jFileChooser.getSelectedFile().getAbsolutePath();
        VentanaProgressBarFicheros ventana = new VentanaProgressBarFicheros(crearVentanaPrincipal(), "Cifrado de Clave Pública");
        ventana.I("\nProceso de cifrado de <" + pathFichero + "> con Algoritmo: " + this.signatureOptions.getPublicCipher() + "\n");
        (new VentanaCifradoAsimetrico(this, pathFichero, ventana)).execute();
      } 
    } else {
      mostrarMensaje("No tenemos claves.");
    } 
  }
  /**
   * Inicia el proceso de descifrado de un archivo usando la clave pública.
   * Se muestra un cuadro de diálogo para seleccionar el archivo a descifrar.
   */
  protected final void procesoDescifradoAsimetrico() {
    if (this.keypair.getIsInicialized()) {
      ReiniciarTextArea();
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setDialogTitle("Fichero a descifrar");
      int i = jFileChooser.showOpenDialog(this.panelContenido);
      if (i == 0) {
        String pathFichero = jFileChooser.getSelectedFile().getAbsolutePath();
        VentanaProgressBarFicheros ventana = new VentanaProgressBarFicheros(crearVentanaPrincipal(), "Descifrado de Clave Pública");
        ventana.I("\nProceso de descifrado de <" + pathFichero + ">.\n");
        (new VentanaDescifradoAsimetrico(this, pathFichero, ventana)).execute();
      } 
    } else {
      mostrarMensaje("No tenemos claves.");
    } 
  }
  /**
   * Inicia el proceso de firma de un archivo usando la clave privada.
   * Se muestra un cuadro de diálogo para seleccionar el archivo a firmar.
   */
  protected final void procesoFirmaDigital() {
    if (this.keypair.getIsInicialized()) {
      ReiniciarTextArea();
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setDialogTitle("Fichero a firmar");
      int i = jFileChooser.showOpenDialog(this.panelContenido);
      if (i == 0) {
        String pathFichero = jFileChooser.getSelectedFile().getAbsolutePath();
        VentanaProgressBarFicheros ventana = new VentanaProgressBarFicheros(crearVentanaPrincipal(), "Firma con clave privada");
        ventana.I("\nProceso de firma de <" + pathFichero + "> con Algoritmo: " + this.signatureOptions.getSignAlgorithm() + "\n");
        (new VentanaFirmarArchivo(this, pathFichero, ventana)).execute();
      } 
    } else {
      mostrarMensaje("No tenemos claves.");
    } 
  }
  /**
   * Inicia el proceso de verificación de la firma de un archivo.
   * Se muestra un cuadro de diálogo para seleccionar el archivo a verificar.
   */
  protected final void procesoVerificacionFirmaDigital() {
    if (this.keypair.getIsInicialized()) {
      ReiniciarTextArea();
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setDialogTitle("Fichero a verificar");
      int i = jFileChooser.showOpenDialog(this.panelContenido);
      if (i == 0) {
        String pathFichero = jFileChooser.getSelectedFile().getAbsolutePath();
        VentanaProgressBarFicheros ventana = new VentanaProgressBarFicheros(crearVentanaPrincipal(), "Verificación de firma");
        ventana.I("\nProceso de verificación de firma de <" + pathFichero + ">\n");
        (new VentanaVerificarFirma(this, pathFichero, ventana)).execute();
      } 
    } else {
      mostrarMensaje("No tenemos claves.");
    } 
  }
  /**
   * Muestra las claves actuales (pública y privada) en el área de texto.
   *
   * @param claves Objeto que contiene las claves.
   */
  protected final void mostrarParClaves(ParClavesP4 claves) {
    if (claves.getIsInicialized()) {
      ReiniciarTextArea();
      this.areaText.append("Clave pública: " + claves.getPublicKey().toString());
      this.areaText.append("\nClave privada: " + claves.getPrivateKey().toString());
    } else {
      mostrarMensaje("No tenemos claves.");
    } 
  }
  /**
   * Carga las claves desde un archivo de acuerdo con las opciones configuradas.
   *
   * @param parClaves         Objeto que contiene las claves.
   * @param paramSignatureOptions Opciones de configuración para las claves.
   * @return true si las claves se cargaron correctamente, false en caso contrario.
   */
  protected final boolean cargarParClavesDesdeFichero(ParClavesP4 parClaves, SignatureOptions paramSignatureOptions) {
    boolean bool = false;
    ReiniciarTextArea();
    if (parClaves.generateKeyPair())
      bool = parClaves.saveKeyPair(paramSignatureOptions.getKeyFilename(), paramSignatureOptions.getKeyFilePasswd());
    return bool;
  }

  /**
   * Inicializa las claves en base a las opciones de configuración.
   *
   * @param parClaves         Objeto que contiene las claves.
   * @param paramSignatureOptions Opciones de configuración para las claves.
   */
  protected final void inicializarParClaves(ParClavesP4 parClaves, SignatureOptions paramSignatureOptions) {
    parClaves.loadKeyPair(paramSignatureOptions.getKeyFilename(), paramSignatureOptions.getKeyFilePasswd());
  }
  /**
   * Guarda las claves en un archivo usando las opciones configuradas.
   *
   * @param parClaves         Objeto que contiene las claves.
   * @param paramSignatureOptions Opciones de configuración para las claves.
   */
  protected final void guardarParClaves(ParClavesP4 parClaves, SignatureOptions paramSignatureOptions) {
    parClaves.saveKeyPair(paramSignatureOptions.getKeyFilename(), paramSignatureOptions.getKeyFilePasswd());
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\Practica4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */