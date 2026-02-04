/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author alumno
 */
public class HASHManager {

    private static final String ALGORITMO = "SHA-256";

    public static byte[] getDigest(byte[] mensaje) throws NoSuchAlgorithmException {
        byte[] resumen = null;
        try {
            MessageDigest algoritmo = MessageDigest.getInstance(ALGORITMO);
            algoritmo.reset();
            algoritmo.update(mensaje);
            resumen = algoritmo.digest();
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
        return resumen;
    }

    public static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    public static boolean compararResumenes(byte[] resumen1, byte[] resumen2) {
        boolean sonIguales;
        try {
            MessageDigest algoritmo = MessageDigest.getInstance(ALGORITMO);
            algoritmo.reset();
            sonIguales = MessageDigest.isEqual(resumen1, resumen2);
        } catch (NoSuchAlgorithmException e) {
            sonIguales = false;
        }
        return sonIguales;
    }
}
