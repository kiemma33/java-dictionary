/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bt02_1112301;

import bt02_1112301.Dict.DictAbstract;
import bt02_1112301.Dict.EnViDict;
import bt02_1112301.Dict.ViEnDict;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nguyễn Chiến Thắng
 */
public class ModelDictionary implements ListModel{
    FrameDictionary frameDictionary;
    
    ViEnDict viEnDict = new ViEnDict(frameDictionary, "Viet_Anh.xml");
    EnViDict enViDict = new EnViDict(frameDictionary, "Anh_Viet.xml");
    
    public ModelDictionary(FrameDictionary frameDictionary){
        this.frameDictionary = frameDictionary;
    }
    
    //load dữ liệu
    public void loadData(){
       enViDict.load(frameDictionary, 0, 50);
       viEnDict.load(frameDictionary, 50, 50);
    }
  
    
    @Override
    public int getSize() {
        return getDict().size();
    }

    public DictAbstract getDict(){
        if(frameDictionary.isEnViSelected())
            return enViDict;
        else 
            return viEnDict;
    }
    
    //Lấy nghĩa của từ
    public String getMeanFromWord(String word){
        return getDict().getValueFromKey(word);
    }
    
    //Lấy nghĩa từ index
    public String getMeanFromIndex(int index){
        return getDict().getValueFromIndex(index);
    }
    
    //Lay phan tu tai
    @Override
    public Object getElementAt(int index) {
        return getDict().getKeyElementAt(index);
    }

    
//    //Lấy phần tử thứ i của SortedMap
//    private String getKeyOfSortedMap(Map<String,String> list, int index){
//        Iterator<String> iter = list.keySet().iterator();
//        while(iter.hasNext()){
//            String key = iter.next();
//            if(index == 0)
//                return key;
//            index--;
//        }
//        return null;
//    }

    @Override
    public void addListDataListener(ListDataListener l) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*
     * Paramterized method to sort Map e.g. HashMap or Hashtable in Java
     * throw NullPointerException if Map contains null key
     */
//    public static <K extends Comparable,V extends Comparable> Map<K,V> sortByKeys(Map<K,V> map){
//        List<K> keys = new LinkedList<K>(map.keySet());
//        Collections.sort(keys);
//      
//        //LinkedHashMap will keep the keys in the order they are inserted
//        //which is currently sorted on natural ordering
//        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
//        for(K key: keys){
//            sortedMap.put(key, map.get(key));
//        }
//      
//        return sortedMap;
//    }
//  
//    /*
//     * Java method to sort Map in Java by value e.g. HashMap or Hashtable
//     * throw NullPointerException if Map contains null values
//     * It also sort values even if they are duplicates
//     */
//    public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
//        List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());
//      
//        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {
//            @Override
//            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
//                return o1.getValue().compareTo(o2.getValue());
//            }
//        });
//      
//        //LinkedHashMap will keep the keys in the order they are inserted
//        //which is currently sorted on natural ordering
//        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
//      
//        for(Map.Entry<K,V> entry: entries){
//            sortedMap.put(entry.getKey(), entry.getValue());
//        }
//      
//        return sortedMap;
//    }

    
}
