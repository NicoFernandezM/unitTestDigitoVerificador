import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DigitoVerificadorTest {
    private char [][]rut;
    private char [][] rutInvertidoEsperado;
    private int [] rutPruebas;
    private int [] digitosEsperados;

    @BeforeEach
    public void init() {
        rut =  new char [][] {{'1', '2', '3', '4', '5', '6', '7', '8'},
                              {'2', '0', '1', '0', '4', '0', '3', '8'},
                              {'1', '1', '1', '1', '1', '1', '1', '1'}};
        rutInvertidoEsperado = new char [][] {{'8', '7', '6', '5', '4', '3', '2', '1'},
                                              {'8', '3', '0', '4', '0', '1', '0', '2'},
                                              {'1', '1', '1', '1', '1', '1', '1', '1'}};
        rutPruebas = new int [] {138, 58, 32};
        digitosEsperados = new int [] {5, 8, 1};

    }

    @Test
    public void calcularDigitoVerificadorTest() {
        boolean digitoCorrecto = true;

        for(int i = 0; i < rutPruebas.length; i++) {
            int digitosObtenido = DigitoVerificador.calcularDigitoVerificador(rutPruebas[i]);

            if (digitosEsperados[i] != digitosObtenido) {
                digitoCorrecto = false;
                break;
            }
        }

        assertTrue(digitoCorrecto);
    }

    @Test
    public void invertirRutTest() {
        boolean rutInvertidoCorrecto = true;
        for(int i = 0; i < rut.length; i++) {
            for(int j = 0; j < rut[i].length; j++) {
                char [] rutInvertidoObtenido = DigitoVerificador.invertirRut(rut[i]);

                if (rutInvertidoEsperado[i][j] != rutInvertidoObtenido[j]) {
                    rutInvertidoCorrecto = false;
                    break;
                }
            }
        }

        assertTrue(rutInvertidoCorrecto);
    }

    @AfterEach
    void teardown() {
        rut =  null;
        rutInvertidoEsperado = null;
        rutPruebas = null;
        digitosEsperados = null;
    }
}
