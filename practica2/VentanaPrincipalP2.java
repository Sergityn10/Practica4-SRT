package practica2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Console;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import practica4.SignatureOptions;
import srt.*;

public class VentanaPrincipalP2 extends S {
  protected Mensajes H;
  protected SignatureOptions signatureOptions;
  public JFrame ventanaPrincipal = null;
  
  protected JPanel panelContenido = null;
  
  protected JMenuBar barraMenu = null;
  
  protected JMenu menuFichero = null;
  
  protected JMenu menuOpciones = null;
  
  protected JMenu menuAyuda = null;
  
  protected JMenuItem menuItemSalir = null;
  
  protected JMenuItem menuItemAcercaDe = null;
  
  protected JMenuItem menuItemOpciones = null;
  
  protected JMenuItem menuItemVerInformacion = null;
  
  protected JMenuItem menuItemCifrar = null;
  
  protected JMenuItem menuItemDescifrar = null;
  
  protected JTextArea areaText = null;
  
  protected JScrollPane scrollMensajes = null;
  
//  private Options options = new Options();
  
  CifradoOriginal keyStroke = null;

  /**
   * Constructor principal de la ventana. Inicializa componentes y configura los algoritmos de cifrado.
   */
  public VentanaPrincipalP2() {
    this.keyStroke = new CifradoOriginal(this);
//    this.options.setSymmetricalCipher(Options.symmetricalAlgorithms[0]);
    try {
      this.signatureOptions = (SignatureOptions) SignatureOptions.load(SignatureOptions.optionsFileName);
    } catch (Exception exception) {
      this.signatureOptions = new SignatureOptions();
    }
  }

  /**
   * Obtiene las opciones de firma digital configuradas en la ventana.
   *
   * @return Las opciones de firma digital configuradas.
   */

  public SignatureOptions getSignatureOptions() {
    return this.signatureOptions; // Asumiendo que existe un campo llamado 'signatureOptions'
  }

  /**
   * Crea e inicializa la ventana principal de la aplicación si aún no existe.
   *
   * @return JFrame configurado y listo para su visualización.
   */
  protected final JFrame crearVentanaPrincipal() {
    if (this.ventanaPrincipal == null) {
      this.ventanaPrincipal = new JFrame();
      this.ventanaPrincipal.setDefaultCloseOperation(3);
      this.ventanaPrincipal.setBounds(new Rectangle(0, 0, 553, 382));
      this.ventanaPrincipal.setPreferredSize(new Dimension(400, 300));
      this.ventanaPrincipal.setResizable(false);
      this.ventanaPrincipal.setJMenuBar(crearBarraMenu());
      this.ventanaPrincipal.setContentPane(crearPanelContenido());
//      this.I.setTitle(this.H.getProperty("title"));
      this.ventanaPrincipal.setTitle("Practica 2");
      Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension dimension2 = this.ventanaPrincipal.getSize();
      if (dimension2.height > dimension1.height)
        dimension2.height = dimension1.height; 
      if (dimension2.width > dimension1.width)
        dimension2.width = dimension1.width; 
      this.ventanaPrincipal.setLocation((dimension1.width - dimension2.width) / 2, (dimension1.height - dimension2.height) / 2);
    } 
    return this.ventanaPrincipal;
  }
  /**
   * Crea el panel principal de la ventana, donde se añaden componentes gráficos.
   *
   * @return JPanel principal configurado.
   */
  protected final JPanel crearPanelContenido() {
    if (this.panelContenido == null) {
      this.panelContenido = new JPanel();
      this.panelContenido.setLayout((LayoutManager)null);
      this.panelContenido.add(crearScrollMensajes());
    } 
    return this.panelContenido;
  }
  /**
   * Crea el panel principal de la ventana, donde se añaden componentes gráficos.
   *
   * @return JPanel principal configurado.
   */
  protected JMenuBar crearBarraMenu() {
    if (this.barraMenu == null) {
      this.barraMenu = new JMenuBar();
      this.barraMenu.add(crearMenuFichero());
      this.barraMenu.add(crearMenuOpciones());
      this.barraMenu.add(crearMenuAyuda());
    } 
    return this.barraMenu;
  }
  /**
   * Crea el menú "Fichero" con las opciones de cifrar, descifrar y salir.
   *
   * @return JMenu configurado.
   */
  private JMenu crearMenuFichero() {
    if (this.menuFichero == null) {
      this.menuFichero = new JMenu();
      this.menuFichero.setText("Fichero");
      this.menuFichero.add(crearMenuItemCifrar());
      this.menuFichero.add(crearMenuItemDescifrar());
      this.menuFichero.add(crearMenuItemSalir());
    } 
    return this.menuFichero;
  }
  /**
   * Crea el menú "Opciones" con opciones adicionales como mostrar algoritmos disponibles.
   *
   * @return JMenu configurado.
   */
  private JMenu crearMenuOpciones() {
    if (this.menuOpciones == null) {
      this.menuOpciones = new JMenu();
      this.menuOpciones.setText("Opciones");
      this.menuOpciones.add(crearMenuItemOpciones());
      this.menuOpciones.add(crearMenuItemVerInformacion());
    } 
    return this.menuOpciones;
  }

  /**
   * Crea el menú "Ayuda" con información acerca de la aplicación.
   *
   * @return JMenu configurado.
   */
  protected final JMenu crearMenuAyuda() {
    if (this.menuAyuda == null) {
      this.menuAyuda = new JMenu();
      this.menuAyuda.setText("Ayuda");
      this.menuAyuda.add(crearMenuItemAcercaDe());
    } 
    return this.menuAyuda;
  }

  /**
   * Crea la opción "Salir" dentro del menú "Fichero" y asigna su acción correspondiente.
   *
   * @return JMenuItem configurado.
   */
  protected final JMenuItem crearMenuItemSalir() {
    if (this.menuItemSalir == null) {
      this.menuItemSalir = new JMenuItem();
      this.menuItemSalir.setText("Salir");
      this.menuItemSalir.addActionListener(new EventoSalir(this));
    } 
    return this.menuItemSalir;
  }

  /**
   * Crea la opción "Acerca de" dentro del menú "Ayuda".
   *
   * @return JMenuItem configurado.
   */
  protected final JMenuItem crearMenuItemAcercaDe() {
    if (this.menuItemAcercaDe == null) {
      this.menuItemAcercaDe = new JMenuItem();
      this.menuItemAcercaDe.setText("Acerca de");
//      this.append.addActionListener(new B(this));
    } 
    return this.menuItemAcercaDe;
  }
  /**
   * Crea la opción "Opciones" dentro del menú "Opciones" con un atajo de teclado.
   *
   * @return JMenuItem configurado.
   */
  protected JMenuItem crearMenuItemOpciones() {
    if (this.menuItemOpciones == null) {
      this.menuItemOpciones = new JMenuItem();
      this.menuItemOpciones.setText("Opciones");
      this.menuItemOpciones.setAccelerator(KeyStroke.getKeyStroke(79, 2, true));
      this.menuItemOpciones.addActionListener(new EventoMostrarAlgoritmosFirmaDisponibles(this));
    } 
    return this.menuItemOpciones;
  }

  /**
   * Crea la opción "Ver información" dentro del menú "Opciones" con un atajo de teclado.
   *
   * @return JMenuItem configurado.
   */
  
  private JMenuItem crearMenuItemVerInformacion() {
    if (this.menuItemVerInformacion == null) {
      this.menuItemVerInformacion = new JMenuItem();
      this.menuItemVerInformacion.setText("Ver información");
      this.menuItemVerInformacion.setAccelerator(KeyStroke.getKeyStroke(86, 2, true));
      this.menuItemVerInformacion.addActionListener(new EventoMostrarAlgoritmosCifradosDisponibles(this));
    } 
    return this.menuItemVerInformacion;
  }


  /**
   * Crea la opción "Cifrar" dentro del menú "Fichero" con un atajo de teclado.
   *
   * @return JMenuItem configurado.
   */
  protected final JMenuItem crearMenuItemCifrar() {
    if (this.menuItemCifrar == null) {
      this.menuItemCifrar = new JMenuItem();
      this.menuItemCifrar.setText("Cifrar");
      this.menuItemCifrar.setAccelerator(KeyStroke.getKeyStroke(67, 2, true));
      this.menuItemCifrar.addActionListener(new MostrarVentanaFicheroCifrar(this));
    } 
    return this.menuItemCifrar;
  }

  /**
   * Crea la opción "Descifrar" dentro del menú "Fichero" con un atajo de teclado.
   *
   * @return JMenuItem configurado.
   */
  
  protected final JMenuItem crearMenuItemDescifrar() {
    if (this.menuItemDescifrar == null) {
      this.menuItemDescifrar = new JMenuItem();
      this.menuItemDescifrar.setText("Descifrar");
      this.menuItemDescifrar.setAccelerator(KeyStroke.getKeyStroke(68, 2, true));
      this.menuItemDescifrar.addActionListener(new EventoMostrarVentanaFicheroDescifrar(this));

    } 
    return this.menuItemDescifrar;
  }

  /**
   * Crea un área de texto para mostrar información en la ventana principal.
   *
   * @return JTextArea configurada.
   */
  
  private JTextArea crearAreaTextoMensajes() {
    if (this.areaText == null) {
      this.areaText = new JTextArea();
      this.areaText.setForeground(Color.red);
    } 
    return this.areaText;
  }
  /**
   * Añade un mensaje al área de texto de la ventana principal.
   * Si la ventana no está visible, imprime el mensaje en la consola.
   *
   * @param paramString Mensaje a mostrar.
   */
  public void mostrarMensaje(String paramString) {
    if (this.ventanaPrincipal != null) {
      this.areaText.append(paramString);
      this.ventanaPrincipal.repaint();
    } else {
      System.out.println(paramString);
    } 
  }

  /**
   * Crea un panel de desplazamiento que contiene el área de texto principal.
   *
   * @return JScrollPane configurado.
   */
  
  protected final JScrollPane crearScrollMensajes() {
    if (this.scrollMensajes == null) {
      this.scrollMensajes = new JScrollPane();
      this.scrollMensajes.setPreferredSize(new Dimension(40, 200));
      this.scrollMensajes.setViewportView(crearAreaTextoMensajes());
      this.scrollMensajes.setSize(new Dimension(547, 325));
    } 
    return this.scrollMensajes;
  }


  /**
   * Punto de entrada de la aplicación. Muestra la ventana principal o ejecuta comandos por consola.
   *
   * @param paramArrayOfString Argumentos de la línea de comandos.
   */
  public static void main(String[] paramArrayOfString) {
    if (paramArrayOfString.length == 0) {
      SwingUtilities.invokeLater(new VisibilizarVentanaPrincipal());
    } else {
      getLocation(paramArrayOfString);
    } 
  }

  /**
   * Muestra una ventana de selección de archivo para cifrar, solicita la frase de paso y realiza el cifrado.
   */
  public final void GUIMostrarVentanaFicheroCifrar() {
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setDialogTitle("Fichero a cifrar");
    int i = jFileChooser.showOpenDialog(this.panelContenido);
    if (i == 0) {
      String str = jFileChooser.getSelectedFile().getAbsolutePath();
      PasswdDialog passwdDialog = new PasswdDialog("Frase de paso", (byte)1);
      passwdDialog.pack();
      Point point = crearVentanaPrincipal().getLocation();
      point.translate(20, 20);
      passwdDialog.setLocation(point);
      passwdDialog.setVisible(true);
      char[] arrayOfChar = passwdDialog.getPasswd();
      if (arrayOfChar != null)
        try {
//          this.keyStroke.procesoCifrado(str, cambioExtensionFichero(str, "cif"), arrayOfChar, this.options.getSymmetricalCipher());
          this.keyStroke.procesoCifrado(str, cambioExtensionFichero(str, "cif"), arrayOfChar, this.signatureOptions.getSymmetricalCipher());
        } catch (Exception exception) {
          this.areaText.append(exception.getMessage());
        }  
    } 
  }

  /**
   * Muestra una ventana de selección de archivo para descifrar, solicita la frase de paso y realiza el descifrado.
   */
  public final void EleccionFicheroADescifrar() {
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setDialogTitle("Fichero a descifrar");
    int i = jFileChooser.showOpenDialog(this.panelContenido);
    if (i == 0) {
      String str = jFileChooser.getSelectedFile().getAbsolutePath();
      Header z = new Header();
      if (this.keyStroke.leerFichero(str, z)) {
        PasswdDialog passwdDialog = new PasswdDialog("Frase de paso", (byte)0);
        passwdDialog.pack();
        Point point = crearVentanaPrincipal().getLocation();
        point.translate(20, 20);
        passwdDialog.setLocation(point);
        passwdDialog.setVisible(true);
        char[] arrayOfChar = passwdDialog.getPasswd();
        if (arrayOfChar != null)
          try {
            this.keyStroke.procesoDescrifrado(str, cambioExtensionFichero(str, "cla"), arrayOfChar, z.getAlgorithm1());
          } catch (Exception exception) {
            this.areaText.append(exception.getMessage());
          }  
      } else {
        this.areaText.append("El fichero no está cifrado.");
      } 
    } 
  }
  
  public void MostrarAlgoritmosCifradosDisponbibles() {
    this.keyStroke.getAlgoritmosCifradoDisponibles();
  }

  /**
   * Funcion que nos permite cambiar o añadir una nueva extensión, si el fichero no la tiene.
   *
   * @param pathFichero Fichero que se va a cambiar o añadir una extension
   * @param extension Una extension del fichero que se quiere crear
   * @return La direccion del fichero con la nueva extensión
   */
  public final String cambioExtensionFichero(String pathFichero, String extension) {
    int i;
    return ((i = pathFichero.lastIndexOf(".")) == -1) ? (String.valueOf(pathFichero) + "." + extension) : (String.valueOf(pathFichero.substring(0, i)) + "." + extension);
  }
  
  public final void getKeyStroke() {
    System.out.println("\nUso: practica2 fichero [algoritmo]\n");
    MostrarAlgoritmosCifradosDisponbibles();
  }
  
  public static final void getLocation(String[] paramArrayOfString) {
    VentanaPrincipalP2 ventanaPrincipalP2 = new VentanaPrincipalP2();
    System.out.println(ventanaPrincipalP2.H.getProperty("version"));
    System.out.println(ventanaPrincipalP2.H.getProperty("product"));
    System.out.println(ventanaPrincipalP2.H.getProperty("copyright"));
    Header z = new Header();
    try {
      char[] arrayOfChar;
      switch (paramArrayOfString.length) {
        case 2:
          System.out.println("Vamos a cifrar el fichero.");
          if ((arrayOfChar = ventanaPrincipalP2.getPasswd()) != null)
            ventanaPrincipalP2.keyStroke.procesoCifrado(paramArrayOfString[0], ventanaPrincipalP2.setExtensionArchivo(paramArrayOfString[0], true), arrayOfChar, paramArrayOfString[1]);
          return;
        case 1:
          if ((new File(paramArrayOfString[0])).isFile()) {
            if (ventanaPrincipalP2.keyStroke.leerFichero(paramArrayOfString[0], z)) {
              System.out.println("Vamos a descifrar el fichero.");
              if ((arrayOfChar = ventanaPrincipalP2.getScreenSize()) != null)
                ventanaPrincipalP2.keyStroke.procesoDescrifrado(paramArrayOfString[0], ventanaPrincipalP2.setExtensionArchivo(paramArrayOfString[0], false), arrayOfChar, z.getAlgorithm1());
            } else {
              System.out.println("Vamos a cifrar el fichero.");
              if ((arrayOfChar = ventanaPrincipalP2.getPasswd()) != null)
                ventanaPrincipalP2.keyStroke.procesoCifrado(paramArrayOfString[0], ventanaPrincipalP2.setExtensionArchivo(paramArrayOfString[0], false), arrayOfChar, ventanaPrincipalP2.signatureOptions.getSymmetricalCipher());
            } 
          } else {
            System.out.println("Problemas con el fichero: " + paramArrayOfString[0]);
          } 
          return;
      } 
      ventanaPrincipalP2.getKeyStroke();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  private String setExtensionArchivo(String paramString, boolean paramBoolean) {
    String str;
    if (paramBoolean) {
      str = String.valueOf(paramString) + ".cif";
    } else {
      str = String.valueOf(paramString) + ".cla";
    } 
    return str;
  }
  
  private char[] getPasswd() {
    char[] arrayOfChar;
    if ((arrayOfChar = getSelectedFile()) != null) {
      String str1 = new String(arrayOfChar);
      System.out.print("Repitir ");
      String str2 = new String(getSelectedFile());
      if (str1.equals(str2))
        return arrayOfChar; 
      System.out.println("Frases diferentes.");
      return null;
    } 
    return null;
  }
  
  private char[] getScreenSize() {
    return getSelectedFile();
  }
  
  private char[] getSelectedFile() {
    char[] arrayOfChar = null;
    Console console;
    return ((console = System.console()) != null && (arrayOfChar = console.readPassword("[%s]", new Object[] { "Frase de paso:" })) != null) ? arrayOfChar : null;
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica2\E.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */