package com.zangmz.hit.medicineneo4j.utils;

import java.util.ArrayList;
import java.util.List;

public class WordUtils {

    public static List<String> splitWordSet(String wordSet){
        List<String> result = new ArrayList<>();
        for(String s:wordSet.split(" ")){
            if(!s.equals("<SKIP>")&&!s.equals("out_of_vocabulary")) {
                result.add(s);
            }
        }
        return result;
    }

}
