package srt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/*public class HeaderCifradoMAC {
  private static final byte[] MARK = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
  
  private byte authenticationAlgorithms;
  
  private String cipherAlgorithms;
  
  private String close;
  
  private byte[] flush;
  
  public HeaderCifradoMAC() {
    this.cipherAlgorithms = Options.cipherAlgorithms[0];
    this.close = Options.authenticationAlgorithms[0];
    this.authenticationAlgorithms = 0;
    this.flush = new byte[] { 125, 96, 67, 95, 2, 9, 15, 10 };
  }
  
  public HeaderCifradoMAC(byte paramByte, String paramString1, String paramString2, byte[] paramArrayOfbyte) {
    this.authenticationAlgorithms = paramByte;
    this.cipherAlgorithms = paramString1;
    this.close = paramString2;
    this.flush = paramArrayOfbyte;
  }
  
  public final byte append() {
    return this.authenticationAlgorithms;
  }
  
  public final String I() {
    return this.cipherAlgorithms;
  }
  
  public final String Z() {
    return this.close;
  }
  
  public final byte[] C() {
    return this.flush;
  }
  
  public final boolean load(InputStream paramInputStream) {
    byte[] arrayOfByte = new byte[14];
    boolean bool = false;
    try {
      if (paramInputStream.read(arrayOfByte, 0, 14) == 14) {
        byte b;
        for (b = 0; b < 10 && arrayOfByte[b] == MARK[b]; b = (byte)(b + 1));
        if (b == 10) {
          b = (byte)(b + 1);
          this.authenticationAlgorithms = arrayOfByte[b];
          b = (byte)(b + 1);
          this.cipherAlgorithms = Options.cipherAlgorithms[arrayOfByte[b]];
          b = (byte)(b + 1);
          this.close = Options.authenticationAlgorithms[arrayOfByte[b]];
          b = (byte)(b + 1);
          byte b1 = arrayOfByte[b];
          this.flush = new byte[b1];
          if (paramInputStream.read(this.flush, 0, b1) == b1)
            bool = true; 
        } 
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    return bool;
  }
  
  public final boolean I(OutputStream paramOutputStream) {
    boolean bool = false;
    try {
      paramOutputStream.write(MARK);
      paramOutputStream.write(this.authenticationAlgorithms);
      paramOutputStream.write(Options.search(Options.cipherAlgorithms, this.cipherAlgorithms));
      paramOutputStream.write(Options.search(Options.authenticationAlgorithms, this.close));
      paramOutputStream.write(this.flush.length);
      paramOutputStream.write(this.flush, 0, this.flush.length);
      paramOutputStream.flush();
      bool = true;
    } catch (Exception exception) {}
    return bool;
  }
  
  public final void authenticationAlgorithms() {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream("fileheader.prueba");
      I(fileOutputStream);
      fileOutputStream.close();
      HeaderCifradoMAC z = new HeaderCifradoMAC();
      FileInputStream fileInputStream = new FileInputStream("fileheader.prueba");
      if (z.I(fileInputStream)) {
        System.out.println("Leído, Operación: " + z.append());
        System.out.println("Leído, Algoritmo1: " + z.I());
        System.out.println("Leído, Algoritmo2: " + z.Z());
        System.out.print("Leído, Data     : ");
        for (byte b = 0; b < (z.C()).length; b = (byte)(b + 1)) {
          System.out.print(String.format("0x%h ", new Object[] { Byte.valueOf(z.C()[b]) }));
        } 
      } else {
        System.out.println("Error en la carga");
      } 
      fileInputStream.close();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public static final void main(String[] paramArrayOfString) {
    (new HeaderCifradoMAC()).authenticationAlgorithms();
  }
}*/

public class Header extends BasicHeader {
  private final static byte  MARK[]= {1,2,3,4,5,6,7,8,9,0};
  private final static byte  MARKLENGTH = 10;
  private final static short MINHEADERLENGTH = MARKLENGTH + 4;

  /**
   * Operaci�n realizada, codificada segun las definiciones de <code>Options</code>
   */
  private byte  operation;
  /**
   * Algoritmos usados codificados segun los valores de <code>Options</code>.
   */
  private String  algorithm1,
          algorithm2;
  /**
   * Datos para las operaciones: salt / mac / hash / signature / ...
   */
  private byte data[];

  /**
   * Constructor por defecto.
   */
  public Header() {
    algorithm1 = Options.cipherAlgorithms[0];
    algorithm2 = Options.authenticationAlgorithms[0];
    operation  = Options.OP_NONE;
    data = new byte[] { 0x7d, 0x60, 0x43, 0x5f, 0x02, 0x09, 0x0f, 0x0a};
  }
  /**
   * Constructor. Inicia los atributos con valores suministrados.
   * @param algorithm1 - nombre est�ndar del algoritmo 1
   * @param algorithm2 - nombre est�ndar del algoritmo 2
   * @param data - Datos usados con los algoritmos (salt, ...)
   */
  public Header(byte operation,String algorithm1, String algorithm2,  byte[] data) {
    this.operation  = operation;
    this.algorithm1 = algorithm1;
    this.algorithm2 = algorithm2;
    this.data = data;
  }

  public byte getOperation(){
    return operation;
  }

  public String getAlgorithm1(){
    return algorithm1;
  }

  public String getAlgorithm2(){
    return algorithm2;
  }

  public byte[] getData(){
    return data;
  }

  public void setData(byte[]newData) {
    data = newData;
  }

  /**
   * Intenta cargar los datos de una cabecera desde un InputStream ya abierto.
   * Si tiene exito, los datos quedan en la clase.
   * @param r el InputStream abierto.
   * @throws Exception  Si ocurre un error de entrada o salida.
   * @return true si la carga es correcta, false en otro caso
   */
  public boolean load(InputStream is) throws Exception {
    boolean breturn=false;
    if(super.load(is)) {
      byte[] buffer = getbasicData();
      if(buffer.length>=MINHEADERLENGTH) {
        if (Arrays.equals(MARK,Arrays.copyOf(buffer,MARKLENGTH))) {
          short i = MARKLENGTH;
          operation  = buffer[i++];
          algorithm1 = Options.cipherAlgorithms[buffer[i++]];
          algorithm2 = Options.authenticationAlgorithms[buffer[i++]];
          int dataLength = (buffer[i]>=0) ? buffer[i] : (buffer[i]+256);
          i++;
          data = Arrays.copyOfRange(buffer,i,i+dataLength);
          breturn = true;
        }
      }
    }
    return breturn;
  }

  /**
   * Intenta guardar la cabecera actual en un OutputStream ya abierto.
   * @param fos el OutputStream abierto
   * @throws Exception  Si ocurre un error de entrada o salida.
   * @return true si tiene exito, false en otro caso
   */
  public boolean save(OutputStream os) throws Exception {
    boolean breturn=false;
    byte[] buffer = new byte[MINHEADERLENGTH+data.length];
    short i;

    for(i=0; i<MARKLENGTH; i++)
      buffer[i] = MARK[i];
    buffer[i++] = operation;
    buffer[i++] = (byte)Options.search(Options.cipherAlgorithms,algorithm1);
    buffer[i++] = (byte)Options.search(Options.authenticationAlgorithms,algorithm2);
    buffer[i++] = (byte)data.length;
    for(short j=0; j<data.length; j++)
      buffer[i++] = data[j];

    setbasicData(buffer);
    breturn = super.save(os);
    return breturn;
  }

  /**
   * Prueba el funcionamiento de la clase, creando una cabecera, guardandola en un
   * fichero y recuperandola posteriomente.
   *
   */
  public void test() {
    try {
      String fileName = "fileheader.prueba";
      FileOutputStream fos = new FileOutputStream(fileName);
      save(fos);
      fos.close();

      Header fh2= new Header();
      FileInputStream fis = new FileInputStream(fileName);
      if (fh2.load(fis)){
        System.out.print("\nOperaci�n: " +fh2.getOperation());
        System.out.print(" Algoritmo1: "+fh2.getAlgorithm1());
        System.out.print(" Algoritmo2: "+fh2.getAlgorithm2());
        System.out.print(" Data: ");
        for(byte i=0;i<fh2.getData().length;i++)
          System.out.print(String.format("0x%h ", fh2.getData()[i]));
      }
      else
        System.out.println("Error en la carga");
      fis.close();
    }
    catch (Exception e) {e.printStackTrace(); };
  }

  /**
   * Programa principal para prueba
   *
   */
  public static void main(String args[]){
    byte data[] = new byte[0];

    for(int cipher=0;cipher<Options.cipherAlgorithms.length;cipher++) {
      for(int auth=0;auth<Options.authenticationAlgorithms.length;auth++) {
        Header h = new Header(Options.OP_SYMMETRIC_CIPHER, Options.cipherAlgorithms[cipher],
                Options.authenticationAlgorithms[auth], data);
        h.test();
        byte tmp[] = new byte[data.length+1];
        byte i = 0;
        for(;i<data.length;i++)
          tmp[i]=data[i];
        tmp[i]=(byte)(cipher+auth);
        data = tmp;
      }
    }
  }

}//Header


/* Location:              C:\Users\USUARIO\OneDrive - Universidad de Extremadura\Escritorio\Sergio\Uni\4º-curso\1º-cuatri\SRT\Prácticas-laboratorios\Entrega4\practica4-prototipo.jar!\srt\CifradoOriginal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */