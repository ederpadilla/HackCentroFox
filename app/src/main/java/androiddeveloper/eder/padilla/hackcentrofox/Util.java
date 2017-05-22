package androiddeveloper.eder.padilla.hackcentrofox;

import android.util.Log;

import java.util.Random;

/**
 * Created by ederpadilla on 19/05/17.
 */

public class Util {

    public static final String FIREBASE_DB_VENTA = "Ventas";
    public static final String FIREBASE_DB_RUTA = "Rutas";
    public static final String FIREBASE_DB_DONATION = "Donaciones";

    public static void log(String message){
        Log.e("::HACK FOX::",message);
    }

    public static void ERROR(Throwable throwable) {
        Log.e("::::",throwable.getMessage());
    }
    public static String generarCodigoVenta(){
        String codigo=null;
        Random r = new Random();
        char penultimaLetra = (char)(r.nextInt(26) + 'a');
        char ultimaLetra = (char)(r.nextInt(26) + 'a');
        char letrapenultima= Character.toUpperCase(penultimaLetra);
        char letraUltima= Character.toUpperCase(ultimaLetra);
        String nom= "CO";
        int numeroRandom= randomInt(0,1000);
        codigo=nom+numeroRandom+letrapenultima+letraUltima;
        log(codigo);
        return codigo;
    }
    public static String generarCodigoRuta(){
        String codigo=null;
        Random r = new Random();
        char penultimaLetra = (char)(r.nextInt(26) + 'a');
        char ultimaLetra = (char)(r.nextInt(26) + 'a');
        char letrapenultima= Character.toUpperCase(penultimaLetra);
        char letraUltima= Character.toUpperCase(ultimaLetra);
        String nom= "RU";
        int numeroRandom= randomInt(0,1000);
        codigo=nom+numeroRandom+letrapenultima+letraUltima;
        log(codigo);
        return codigo;
    }
    public static String generarCodigoDonacion(){
        String codigo=null;
        Random r = new Random();
        char penultimaLetra = (char)(r.nextInt(26) + 'a');
        char ultimaLetra = (char)(r.nextInt(26) + 'a');
        char letrapenultima= Character.toUpperCase(penultimaLetra);
        char letraUltima= Character.toUpperCase(ultimaLetra);
        String nom= "DO";
        int numeroRandom= randomInt(0,1000);
        codigo=nom+numeroRandom+letrapenultima+letraUltima;
        log(codigo);
        return codigo;
    }
    public static int randomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static final String FIREBASE_DB_PRODUCT_PICTURE_URL = "gs://hackcf7-dc485.appspot.com/products/";
}
