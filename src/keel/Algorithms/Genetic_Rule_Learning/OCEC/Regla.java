package keel.Algorithms.Genetic_Rule_Learning.OCEC;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Regla
    implements Comparable {

  int[] antecedente;
  int[] valor;
  int clase;
  String nombreClase;
  String[] atributos;
  double rs;

  public Regla() {
  }

  public Regla(Organizacion org) {
    antecedente = new int[org.nUtiles];
    valor = new int[org.nUtiles];
    clase = org.clase;
    int j = 0;
    for (int i = 0; i < org.Uorg.length; i++) {
      if (org.Uorg[i]) {
        antecedente[j] = i;
        valor[j] = org.ForgValue[i];
        j++;
      }
    }
    rs = 0;
  }

  public void asignarNombres(String clase, String[] atributos) {
    this.nombreClase = clase;
    this.atributos = new String[antecedente.length];
    for (int i = 0; i < antecedente.length; i++) {
      this.atributos[i] = atributos[antecedente[i]];
    }
  }

  public void calculaRelativeSupport(myDataset train) {
    int nEjemplosPositivos, nCubiertos;
    nEjemplosPositivos = nCubiertos = 0;
    for (int i = 0; i < train.size(); i++) {
      if (train.getOutputAsInteger(i) == clase) {
        nEjemplosPositivos++;
        double[] ejemplo = train.getExample(i);
        boolean cubierto = cubre(ejemplo);
        if (cubierto) {
          nCubiertos++;
        }
      }
    }
    rs = 1.0 * nCubiertos / nEjemplosPositivos;
  }

  public boolean cubre(double[] ejemplo) {
    boolean cubierto = true;
    for (int j = 0; (j < antecedente.length) && (cubierto); j++) {
      //System.out.print(" Mira["+j+"] -> ("+valor[j]+", "+ejemplo[antecedente[j]]+")");
      cubierto = (valor[j] == ejemplo[antecedente[j]]);
    }
    //System.out.println("");
    return cubierto;
  }

  public String printString() {
    String cadena = new String("");
    cadena += " IF ";
    for (int i = 0; i < antecedente.length - 1; i++) {
      cadena += this.atributos[i] + " = " + this.valor[i] + " AND ";
    }
    cadena += this.atributos[atributos.length - 1] + " = " +
        this.valor[valor.length - 1] + " THEN Class =  " + nombreClase + " (RS: "+rs+")\n";
    return cadena;
  }

  public double matchValue(double [] example){
    double mv = 0;
    int terms = 0;
    for (int j = 0; (j < antecedente.length); j++) {
      //System.out.print(" Mira["+j+"] -> ("+valor[j]+", "+ejemplo[antecedente[j]]+")");
      if (valor[j] == example[antecedente[j]]){
        terms++;
      }
    }
    mv = 1.0*terms/antecedente.length;
    return mv;
  }

  public int compareTo(Object a) {
    if ( ( (Regla) a).rs < this.rs) {
      return -1;
    }
    if ( ( (Regla) a).rs > this.rs) {
      return 1;
    }
    return 0;
  }

}