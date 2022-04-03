import java.util.Scanner;

public class DigitoVerificador {
    private final static Scanner scanner = new Scanner(System.in);
    private final static int VALOR_MAXIMO = 99999999;
    private final static int VALOR_MINIMO = 1000000;

    public static void main(String[] args) {
        llamarFunciones();
        scanner.close();
    }

    public static String pedirRut() {
        System.out.println("Por favor ingrese su rut sin puntos ni digito verificador.");
        return scanner.nextLine();
    }

    public static int verificarNumeroRut(String line) {
        int rut = 0;
        try {
            rut = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            pedirRut();
        }

        return rut;
    }

    public static char [] convertirIntAArreglo(int rut) {
        return String.valueOf(rut).toCharArray();
    }

    public static char [] invertirRut(char[] rut) {
        char[] rutInvertido = new char[rut.length];

        for (int i = rut.length - 1, j = 0; i >= 0; i--, j++) {
            rutInvertido[j] = rut[i];
        }

        return rutInvertido;
    }

    public static int [] crearCadenaParaMultiplicar(char[] rutInvertido) {
        int[] cadenaParaMultiplicar = new int[rutInvertido.length];
        int numeroMultiplicador = 2;

        for (int i = 0; i < rutInvertido.length; i++) {
            if (numeroMultiplicador < 8) {
                cadenaParaMultiplicar[i] = numeroMultiplicador;
                numeroMultiplicador++;
            } else {
                numeroMultiplicador = 2;
                i--;
            }
        }

        return cadenaParaMultiplicar;
    }

    public static int multiplicarCadenas(int[] cadenaParaMultiplicar, char[] rutInvertido) {
        int multiplicacionCadenas = 0;

        for (int i = 0; i < rutInvertido.length; i++) {
            int digitoRutAMultiplicar = convertirCharAInt(rutInvertido[i]);
            int auxMultiplicacion = cadenaParaMultiplicar[i] * digitoRutAMultiplicar;
            multiplicacionCadenas += auxMultiplicacion;
        }

        return multiplicacionCadenas;
    }

    public static int calcularDigitoVerificador (int multiplicacionCadenas) {
        int division = multiplicacionCadenas / 11;
        int multiplicacionFinal = division * 11;

        return 11 - (multiplicacionCadenas - multiplicacionFinal);
    }

    public static int convertirCharAInt(char caracterRutInvertido) {
        String digitoString = String.valueOf(caracterRutInvertido);
        return Integer.parseInt(digitoString);
    }

    public static void imprimirDigitoVerificador(int resultadoDigitoVerificador) {

        if (resultadoDigitoVerificador == 11) {
            System.out.println("El dígito verificador es: 0");

        } else if (resultadoDigitoVerificador == 10) {
            System.out.println("El dígito verificador es: k");

        } else {
            System.out.println("El dígito verificador es: " + resultadoDigitoVerificador);
        }
    }

    public static void llamarFunciones() {
        int rut;

        do {
            String line = pedirRut();
            rut = verificarNumeroRut(line);
        }while(rut < VALOR_MINIMO || rut > VALOR_MAXIMO);

        char[] chars = convertirIntAArreglo(rut);
        char[] rutInvertido = invertirRut(chars);
        int[] cadenaParaMultiplicar = crearCadenaParaMultiplicar(rutInvertido);
        int multiplicacionCadenas = multiplicarCadenas(cadenaParaMultiplicar, rutInvertido);
        int resultadoDigitoVerificador = calcularDigitoVerificador(multiplicacionCadenas);
        imprimirDigitoVerificador(resultadoDigitoVerificador);
    }
}