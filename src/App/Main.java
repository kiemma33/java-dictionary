/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bt02_1112301;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Nguyễn Chiến Thắng
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new FrameDictionary();
//        SortedMap<String,String> m = new TreeMap<String,String>();
//        m.put("a", "thang");
//        m.put("d", "thang");
//        m.put("c", "thang");
//        m.put("e", "thang");
        
        
    }
    
    static private String getKeyOfSortedMap(Map<String,String> list, int index){
        Iterator<String> iter = list.keySet().iterator();
        while(iter.hasNext()){
            String key = iter.next();
            if(index == 0)
                return key;
            index--;
        }
        return null;
    }
    
}
