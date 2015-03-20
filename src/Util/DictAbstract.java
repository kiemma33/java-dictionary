/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bt02_1112301.Dict;

import bt02_1112301.FrameDictionary;
import bt02_1112301.ModelDictionary;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public abstract class DictAbstract {
    protected FrameDictionary frameDictionary;
    protected String fileName;
    
    //Danh sách từ
    SortedMap<String,String> listSorted = new TreeMap<String,String>();
    
    //Danh sách để lấy model
    LinkedList<String> listLinked = new LinkedList<String>();
    
    public DictAbstract(FrameDictionary frameDictionary, String fileName){
        this.frameDictionary = frameDictionary;
        this.fileName = fileName;
    }
    
    
    //Load dữ liệu
    public void load(Updateable update, int startPercent, int maxPercent){
        File file = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder;
        Document document ;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
            NodeList list = document.getElementsByTagName("record");
            //list.item(index)
            int size = list.getLength();
            for(int i = 0; i < list.getLength(); i++){
                Node node = list.item(i);
                Element ele = (Element)node;
                String word = ele.getElementsByTagName("word").item(0).getTextContent();
                String mean = ele.getElementsByTagName("meaning").item(0).getTextContent();
                listSorted.put(word, mean);
                if(i%20 == 0 || i == size - 1){
                    int tmp = i + 1;
                    float percent = (float)tmp/size*100/(100/maxPercent);
                    //if(i == size - 2)
                    //    percent = 50f;
                    DecimalFormat df = new DecimalFormat("###.##");
                    update.updatePercentLoaded(startPercent + Float.parseFloat(df.format(percent)));
                }
            }
            Iterator<String> iter = listSorted.keySet().iterator();
            while(iter.hasNext()){
                String key = iter.next();
                listLinked.add(key);
            }
        } catch (Exception ex) {
            Logger.getLogger(ModelDictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Tra nghĩa của một từ
    
    //Lấy key tại phần từ vị trí i
    public String getKeyElementAt(int idx){
        if(listLinked.size() > idx)
            return listLinked.get(idx);
        else
            return "";
    }
    
    //Lấy value tại key k
    public String getValueFromKey(String key){
        return listSorted.get(key);
    }
    
    //Lấy value tại index
    public String getValueFromIndex(int index){
        String word = listLinked.get(index);
        return getValueFromKey(word);
    }
    
    //Lấy size
    public int size(){
        return listLinked.size();
    }
}
