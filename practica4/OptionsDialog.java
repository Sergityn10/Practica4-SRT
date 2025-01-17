package practica4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class OptionsDialog extends JDialog implements ActionListener {
  private static final long serialVersionUID = 1L;
  
  private SignatureOptions signatureOptions;
  
  JPanel panelPublicCiphers = new JPanel();
  
  JPanel panelDigitalSigners = new JPanel();
  
  JPanel panelPublicKeyFile = new JPanel();
  
  JPanel panelPasswdPublicKeyFile = new JPanel();
  
  JPanel panelKR = new JPanel();
  
  JPanel panelButton = new JPanel();
  
  JPanel panelSymmetricalCiphers = new JPanel();
  
  JPanel panelHashAlgorithms = new JPanel();
  
  JPanel panelSymmetricalAndHash = new JPanel();
  
  JButton buttonOK = new JButton();
  
  JButton buttonCancel = new JButton();
  
  JButton buttonExplore = new JButton();
  
  JLabel label1 = new JLabel();
  
  JLabel label2 = new JLabel();
  
  JLabel label3 = new JLabel();
  
  JLabel label4 = new JLabel();
  
  JLabel labelSymmetricalAlgorithm = new JLabel();
  
  JLabel labelHashAlgorithm = new JLabel();
  
  JTextField textField1 = new JTextField(20);
  
  JPasswordField passwdField = new JPasswordField("", 20);
  
  JComboBox jlPublicCiphers = new JComboBox<String>(SignatureOptions.publicAlgorithms);
  
  JComboBox jlSigners = new JComboBox<String>(SignatureOptions.signAlgorithms);
  
  JComboBox jlSymmetricalCiphers = new JComboBox<String>(SignatureOptions.symmetricalAlgorithms);
  
  JComboBox jlHashs = new JComboBox<String>(SignatureOptions.hashmacAlgorithms);
  
  public OptionsDialog(SignatureOptions paramSignatureOptions) {
    this.signatureOptions = paramSignatureOptions;
    enableEvents(64L);
    try {
      add();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    pack();
  }
  
  private void add() {
    setTitle("Opciones");
    setResizable(false);
    setModal(true);
    this.jlSymmetricalCiphers.setSelectedItem(this.signatureOptions.getSymmetricalCipher());
    this.jlHashs.setSelectedItem(this.signatureOptions.getAuthenticator());
    this.labelSymmetricalAlgorithm.setText("Algoritmo Cifrado PBE");
    this.labelHashAlgorithm.setText("Algoritmo Hash/HMac");
    this.panelSymmetricalCiphers.add(this.labelSymmetricalAlgorithm);
    this.panelSymmetricalCiphers.add(this.jlSymmetricalCiphers);
    this.panelSymmetricalCiphers.setBorder(BorderFactory.createEtchedBorder());
    this.panelHashAlgorithms.add(this.labelHashAlgorithm);
    this.panelHashAlgorithms.add(this.jlHashs);
    this.panelHashAlgorithms.setBorder(BorderFactory.createEtchedBorder());
    this.panelSymmetricalAndHash.add(this.panelSymmetricalCiphers);
    this.panelSymmetricalAndHash.add(this.panelHashAlgorithms);
    this.jlSymmetricalCiphers.setSelectedItem(this.signatureOptions.getSymmetricalCipher());
    this.jlHashs.setSelectedItem(this.signatureOptions.getAuthenticator());
    this.jlPublicCiphers.setSelectedItem(this.signatureOptions.getPublicCipher());
    this.jlSigners.setSelectedItem(this.signatureOptions.getSignAlgorithm());
    this.textField1.setText(this.signatureOptions.getKeyFilename());
    this.passwdField.setText(new String(this.signatureOptions.getKeyFilePasswd()));
    this.label2.setText("Algoritmo de Firma: ");
    this.label3.setText("Fichero de Claves: ");
    this.label4.setText("Contraseña: ");
    this.label1.setText("Cifrado Clave Pública: ");
    this.panelPublicCiphers.add(this.label1);
    this.panelPublicCiphers.add(this.jlPublicCiphers);
    this.panelPublicCiphers.setBorder(BorderFactory.createEtchedBorder());
    this.panelDigitalSigners.add(this.label2);
    this.panelDigitalSigners.add(this.jlSigners);
    this.panelDigitalSigners.setBorder(BorderFactory.createEtchedBorder());
    this.panelKR.add(this.panelPublicCiphers);
    this.panelKR.add(this.panelDigitalSigners);
    this.panelKR.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    this.panelPasswdPublicKeyFile.add(this.label4);
    this.panelPasswdPublicKeyFile.add(this.passwdField);
    this.panelPublicKeyFile.setLayout(new BorderLayout());
    this.panelPublicKeyFile.add(this.label3, "West");
    this.panelPublicKeyFile.add(this.textField1, "Center");
    this.panelPublicKeyFile.add(this.buttonExplore, "East");
    this.panelPublicKeyFile.add(this.panelPasswdPublicKeyFile, "South");
    this.panelPublicKeyFile.setBorder(BorderFactory.createEtchedBorder());
    this.buttonOK.setText("Aceptar");
    this.buttonCancel.setText("Cancelar");
    this.buttonExplore.setText("Examinar...");
    this.buttonOK.addActionListener(this);
    this.buttonCancel.addActionListener(this);
    this.buttonExplore.addActionListener(this);
    this.panelButton.add(this.buttonOK);
    this.panelButton.add(this.buttonCancel);
    this.panelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    getContentPane().setLayout(new GridLayout(4, 0));
    getContentPane().add(this.panelSymmetricalAndHash);
    getContentPane().add(this.panelKR);
    getContentPane().add(this.panelPublicKeyFile);
    getContentPane().add(this.panelButton);
  }
  
  protected final void processWindowEvent(WindowEvent paramWindowEvent) {
    if (paramWindowEvent.getID() == 201)
      cancel(); 
    super.processWindowEvent(paramWindowEvent);
  }
  
  private final void cancel() {
    dispose();
  }
  
  public final void actionPerformed(ActionEvent paramActionEvent) {
    if (paramActionEvent.getSource() == this.buttonOK) {
      this.signatureOptions.setSymmetricalCipher((String)this.jlSymmetricalCiphers.getSelectedItem());
      this.signatureOptions.setAuthenticator((String)this.jlHashs.getSelectedItem());
      this.signatureOptions.setPublicCipher((String)this.jlPublicCiphers.getSelectedItem());
      this.signatureOptions.setSignAlgorithm((String)this.jlSigners.getSelectedItem());
      this.signatureOptions.setKeyFilename(this.textField1.getText());
      this.signatureOptions.setKeyFilePasswd(this.passwdField.getPassword());
      try {
        this.signatureOptions.save(SignatureOptions.optionsFileName);
      } catch (Exception exception) {
        exception.printStackTrace();
      } 
      cancel();
    } else if (paramActionEvent.getSource() == this.buttonExplore) {
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setDialogTitle("Fichero de claves");
      jFileChooser.setCurrentDirectory(new File("."));
      int i = jFileChooser.showOpenDialog(getContentPane());
      if (i == 0) {
        String str = jFileChooser.getSelectedFile().getAbsolutePath();
        this.signatureOptions.setKeyFilename(str);
        this.textField1.setText(str);
      } 
    } 
    if (paramActionEvent.getSource() == this.buttonCancel)
      cancel(); 
  }
}


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\practica4\OptionsDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */