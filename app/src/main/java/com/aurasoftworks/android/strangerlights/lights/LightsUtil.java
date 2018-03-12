package com.aurasoftworks.android.strangerlights.lights;

import java.util.ArrayList;
import java.util.HashMap;

public class LightsUtil {

    private static final HashMap<Character, Byte> CHARACTER_MAP;
    static {
        CHARACTER_MAP = new HashMap<>();

        CHARACTER_MAP.put('A', (byte)0);
        CHARACTER_MAP.put('B', (byte)1);
        CHARACTER_MAP.put('C', (byte)2);
        CHARACTER_MAP.put('D', (byte)3);
        CHARACTER_MAP.put('E', (byte)4);
        CHARACTER_MAP.put('F', (byte)5);
        CHARACTER_MAP.put('G', (byte)6);
        CHARACTER_MAP.put('H', (byte)7);
        CHARACTER_MAP.put('I', (byte)8);
        CHARACTER_MAP.put('J', (byte)9);
        CHARACTER_MAP.put('K', (byte)10);
        CHARACTER_MAP.put('L', (byte)11);
        CHARACTER_MAP.put('M', (byte)12);
        CHARACTER_MAP.put('N', (byte)13);
        CHARACTER_MAP.put('O', (byte)14);
        CHARACTER_MAP.put('P', (byte)15);
        CHARACTER_MAP.put('Q', (byte)16);
        CHARACTER_MAP.put('R', (byte)17);
        CHARACTER_MAP.put('S', (byte)18);
        CHARACTER_MAP.put('T', (byte)19);
        CHARACTER_MAP.put('U', (byte)20);
        CHARACTER_MAP.put('V', (byte)21);
        CHARACTER_MAP.put('W', (byte)22);
        CHARACTER_MAP.put('X', (byte)23);
        CHARACTER_MAP.put('Y', (byte)24);
        CHARACTER_MAP.put('Z', (byte)25);
        CHARACTER_MAP.put(' ', (byte)26);
    }

    public static byte[] getLightBytesFromString(String input) {

        ArrayList<Byte> byteList = new ArrayList<>();

        char[] characters = input.toCharArray();
        for(char c : characters) {
            Byte charByte = CHARACTER_MAP.get(c);
            if(charByte != null) {
                byteList.add(charByte);
            }
        }

        if(!byteList.isEmpty()) {
            byte[] compiledBytes = new byte[byteList.size()];
            for(int i = 0; i < compiledBytes.length; i++) {
                compiledBytes[i] = byteList.get(i);
            }
            return compiledBytes;
        }

        return null;
    }
}
