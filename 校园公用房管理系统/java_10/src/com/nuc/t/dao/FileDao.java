package com.nuc.t.dao;

import com.nuc.t.entity.building.House;

import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.*;

/**
 * Created by mxwbq on 2017/6/7.
 */
public class FileDao {
    //File f = new File("E:" + File.separator + "java_Project_idea" + File.separator + "java_10" + File.separator + "message.txt");

    public void outputFile(Map<String, List<House>> m) {
        Map<String, List<House>> map;
        map = m;

        try {
            String line = System.getProperty("line.separator");
            StringBuffer str = new StringBuffer();
            FileWriter fw = new FileWriter("D:"+ File.separator+"Data.txt");
            Set set = map.entrySet();
            Iterator iter = set.iterator();
            while(iter.hasNext()){
                Map.Entry<String, List<House>> entry = (Map.Entry<String, List<House>>)iter.next();
                for (int i = 0; i < entry.getValue().size(); i++) {
                    str.append("#"+entry.getKey()+"#"+entry.getValue().get(i).getId()+"#"+entry.getValue().get(i).getPosition()+"#"+entry.getValue().get(i).getArea()+"#"+entry.getValue().get(i).getUsage()+"#"+entry.getValue().get(i).getServiceCondition()+"#"+entry.getValue().get(i).getSafetyCondition()).append(line);
                }
            }
            fw.write(str.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SchoolMap inputFile() {
        Map<String, List<House>> map = new HashMap<>();
        SchoolMap schoolMap = new SchoolMap(map);
        List<House> list;
        House h;
        String[] name = new String[100];
        Set<String> set = new HashSet<>();
        try {
            String encoding = "utf-8";
            File file = new File("D:" + File.separator + "Data.txt");
            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String lineTXT = null;
                while ((lineTXT = bufferedReader.readLine()) != null) {
                    // System.out.println(lineTXT.toString().trim());
                    String[] c = lineTXT.split("#");
                    set.add(c[1]);
                }
                Iterator iterator = set.iterator();
                for (int i = 0;iterator.hasNext(); i++) {
                    name[i] = (String)iterator.next();
                }
                //System.out.println(set.size());
                for (int i = 0; i < set.size(); i++) {
                    list = new ArrayList<>();
                    schoolMap.increasekData(name[i], list);
                }
                reader = new InputStreamReader(new FileInputStream(file), encoding);
                bufferedReader = new BufferedReader(reader);
                while ((lineTXT = bufferedReader.readLine()) != null) {
                    String[] c = lineTXT.split("#");
                    h = new House();
                    h.setBelong(c[1]);
                    h.setId(c[2]);
                    h.setPosition(c[3]);
                    h.setArea(Double.valueOf(c[4]));
                    h.setUsage(c[5]);
                    h.setServiceCondition(c[6]);
                    h.setSafetyCondition(c[7]);
                    schoolMap.getMap().get(c[1]).add(h);
                }
                reader.close();
            } else {
                System.out.println("找不到文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容失败");
            e.printStackTrace();
        }
        return schoolMap;
    }
}
