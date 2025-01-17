package practica4;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import practica3.Practica3;

public class Practica4 extends Practica3 {
  private JMenuItem getKeyFilePasswd = null;
  
  private JMenuItem getKeyFilename = null;
  
  private JMenuItem getPublicCipher = null;
  
  private JMenuItem getSelectedFile = null;
  
  protected JMenuItem getSignAlgorithm = null;
  
  protected JMenuItem invokeLater = null;
  
  protected JMenuItem load = null;
  
  protected SignatureOptions A;

  protected ParClavesP4 keypair;

  protected DigitalSignature digitalSignature;
  
  private JMenu optionsFileName;
  
  private JMenu setAccelerator;
  
  private JMenu setDialogTitle;
  
  private JMenu setText;


  public Practica4() {
    try {
      this.A = (SignatureOptions) SignatureOptions.load(SignatureOptions.optionsFileName);
    } catch (Exception exception) {
      this.A = new SignatureOptions();
    } 
    this.digitalSignature = new DigitalSignature(this);
    this.keypair = new ParClavesP4();
    this.keypair.I(this.A.getKeyFilename(), this.A.getKeyFilePasswd());
    this.I();
  }

  public SignatureOptions getSignatureOptions() {
    return this.A;
  }

  public void setSignaturaOption(SignatureOptions a) {
    this.A = a;
  }
  
  protected final JMenuBar Z() {
    if (this.C == null) {
      this.C = new JMenuBar();
      this.C.add(getKeyFilePasswd());
      this.C.add(getKeyFilename());
      this.C.add(C());
    } 
    return this.C;
  }
  
  protected final JMenu getKeyFilePasswd() {
    if (this.B == null) {
      this.B = new JMenu();
      this.B.setText("Fichero");
      this.B.add(showOpenDialog());
      this.B.add(M());
      this.B.add(N());
      this.B.add(O());
      this.B.add(funcionSalirVentana());
    } 
    return this.B;
  }
  
  private JMenu getKeyFilename() {
    if (this.D == null) {
      this.D = new JMenu();
      this.D.setText("Claves");
      this.D.add(D());
      this.D.add(getPublicCipher());
      this.D.add(invokeLater());
      this.D.add(load());
      this.D.add(getSignAlgorithm());
    } 
    return this.D;
  }
  
  protected final JMenuItem D() {
    if (this.F == null) {
      this.F = new JMenuItem();
      this.F.setText("Opciones de claves");
      this.F.setAccelerator(KeyStroke.getKeyStroke(79, 2, true));
      this.F.addActionListener(new B(this));
    } 
    return this.F;
  }
  
  protected final JMenuItem getPublicCipher() {
    if (this.getSignAlgorithm == null) {
      this.getSignAlgorithm = new JMenuItem();
      this.getSignAlgorithm.setText("Generar nuevas claves");
      this.getSignAlgorithm.setAccelerator(KeyStroke.getKeyStroke(71, 2, true));
      this.getSignAlgorithm.addActionListener(new E(this));
    } 
    return this.getSignAlgorithm;
  }
  
  protected final JMenuItem getSignAlgorithm() {
    if (this.J == null) {
      this.J = new JMenuItem();
      this.J.setText("Ver claves actuales");
      this.J.addActionListener(new G(this));
    } 
    return this.J;
  }
  
  protected final JMenuItem invokeLater() {
    if (this.invokeLater == null) {
      this.invokeLater = new JMenuItem();
      this.invokeLater.setText("Cargar claves desde fichero");
      this.invokeLater.addActionListener(new H(this));
    } 
    return this.invokeLater;
  }
  
  protected final JMenuItem load() {
    if (this.load == null) {
      this.load = new JMenuItem();
      this.load.setText("Guardar claves en fichero");
      this.load.addActionListener(new K(this));
    } 
    return this.load;
  }
  
  private JMenuItem optionsFileName() {
    if (this.getKeyFilePasswd == null) {
      this.getKeyFilePasswd = new JMenuItem();
      this.getKeyFilePasswd.setText("Cifrar KU");
      this.getKeyFilePasswd.setAccelerator(KeyStroke.getKeyStroke(85, 2, true));
      this.getKeyFilePasswd.addActionListener(new L(this));
    } 
    return this.getKeyFilePasswd;
  }
  
  private JMenuItem setAccelerator() {
    if (this.getKeyFilename == null) {
      this.getKeyFilename = new JMenuItem();
      this.getKeyFilename.setText("Descifrar KR");
      this.getKeyFilename.setAccelerator(KeyStroke.getKeyStroke(82, 2, true));
      this.getKeyFilename.addActionListener(new M(this));
    } 
    return this.getKeyFilename;
  }
  
  private JMenuItem setDialogTitle() {
    if (this.getPublicCipher == null) {
      this.getPublicCipher = new JMenuItem();
      this.getPublicCipher.setText("Firmar");
      this.getPublicCipher.setAccelerator(KeyStroke.getKeyStroke(70, 2, true));
      this.getPublicCipher.addActionListener(new N(this));
    } 
    return this.getPublicCipher;
  }
  
  private JMenuItem setText() {
    if (this.getSelectedFile == null) {
      this.getSelectedFile = new JMenuItem();
      this.getSelectedFile.setText("Verificar firma");
      this.getSelectedFile.setAccelerator(KeyStroke.getKeyStroke(87, 2, true));
      this.getSelectedFile.addActionListener(new O(this));
    } 
    return this.getSelectedFile;
  }
  
  private JMenu showOpenDialog() {
    if (this.optionsFileName == null) {
      this.optionsFileName = new JMenu("Cifrado simétrico");
      this.optionsFileName.add(F());
      this.optionsFileName.add(J());
    } 
    return this.optionsFileName;
  }
  
  private JMenu M() {
    if (this.setAccelerator == null) {
      this.setAccelerator = new JMenu("Hash/MAC");
      this.setAccelerator.add(G());
      this.setAccelerator.add(H());
    } 
    return this.setAccelerator;
  }
  
  private JMenu N() {
    if (this.setDialogTitle == null) {
      this.setDialogTitle = new JMenu("Cifrado asimétrico");
      this.setDialogTitle.add(optionsFileName());
      this.setDialogTitle.add(setAccelerator());
    } 
    return this.setDialogTitle;
  }
  
  private JMenu O() {
    if (this.setText == null) {
      this.setText = new JMenu("Firma Digital");
      this.setText.add(setDialogTitle());
      this.setText.add(setText());
    } 
    return this.setText;
  }
  
  public final void I(String paramString) {
    this.S.append(paramString);
  }
  
  public final void P() {
    this.S.setText("");
  }
  
  public static final void main(String[] paramArrayOfString) {
    SwingUtilities.invokeLater(new D());
  }
  
  protected final void Q() {
    if (this.keypair.I()) {
      P();
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setDialogTitle("Fichero a cifrar");
      int i = jFileChooser.showOpenDialog(this.Z);
      if (i == 0) {
        String str = jFileChooser.getSelectedFile().getAbsolutePath();
        Z z = new Z(I(), "Cifrado de Clave Pública");
        z.I("\nProceso de cifrado de <" + str + "> con Algoritmo: " + this.A.getPublicCipher() + "\n");
        (new F(this, str, z)).execute();
      } 
    } else {
      I("No tenemos claves.");
    } 
  }
  
  protected final void R() {
    if (this.keypair.I()) {
      P();
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setDialogTitle("Fichero a descifrar");
      int i = jFileChooser.showOpenDialog(this.Z);
      if (i == 0) {
        String str = jFileChooser.getSelectedFile().getAbsolutePath();
        Z z = new Z(I(), "Descifrado de Clave Pública");
        z.I("\nProceso de descifrado de <" + str + ">.\n");
        (new J(this, str, z)).execute();
      } 
    } else {
      I("No tenemos claves.");
    } 
  }
  
  protected final void T() {
    if (this.keypair.I()) {
      P();
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setDialogTitle("Fichero a firmar");
      int i = jFileChooser.showOpenDialog(this.Z);
      if (i == 0) {
        String str = jFileChooser.getSelectedFile().getAbsolutePath();
        Z z = new Z(I(), "Firma con clave privada");
        z.I("\nProceso de firma de <" + str + "> con Algoritmo: " + this.A.getSignAlgorithm() + "\n");
        (new practica4.S(this, str, z)).execute();
      } 
    } else {
      I("No tenemos claves.");
    } 
  }
  
  protected final void U() {
    if (this.keypair.I()) {
      P();
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setDialogTitle("Fichero a verificar");
      int i = jFileChooser.showOpenDialog(this.Z);
      if (i == 0) {
        String str = jFileChooser.getSelectedFile().getAbsolutePath();
        Z z = new Z(I(), "Verificación de firma");
        z.I("\nProceso de verificación de firma de <" + str + ">\n");
        (new A(this, str, z)).execute();
      } 
    } else {
      I("No tenemos claves.");
    } 
  }
  
  protected final void I(ParClavesP4 paramI) {
    if (paramI.I()) {
      P();
      this.S.append("Clave pública: " + paramI.Z().toString());
      this.S.append("\nClave privada: " + paramI.C().toString());
    } else {
      I("No tenemos claves.");
    } 
  }
  
  protected final boolean I(ParClavesP4 parClaves, SignatureOptions paramSignatureOptions) {
    boolean bool = false;
    P();
    if (parClaves.B())
      bool = parClaves.Z(paramSignatureOptions.getKeyFilename(), paramSignatureOptions.getKeyFilePasswd());
    return bool;
  }
  
  protected final void Z(ParClavesP4 parClaves, SignatureOptions paramSignatureOptions) {
    parClaves.I(paramSignatureOptions.getKeyFilename(), paramSignatureOptions.getKeyFilePasswd());
  }
  
  protected final void C(ParClavesP4 parClaves, SignatureOptions paramSignatureOptions) {
    parClaves.Z(paramSignatureOptions.getKeyFilename(), paramSignatureOptions.getKeyFilePasswd());
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\Practica4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */