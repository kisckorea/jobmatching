package kr.ac.kaist.ks.jobmatching.datastructure;

import java.util.HashMap;
import java.util.Iterator;

public class ScalableMatrix {
	
	private HashMap<String, HashMap> outerMap;
	
	public ScalableMatrix() {
		outerMap = new HashMap<String, HashMap>();
	}
	
	public void addElement(String key1, String key2, double value) {
		HashMap<String, Double> innerMap = outerMap.get(key1);
        if (innerMap==null) {
            innerMap = new HashMap<String, Double>();
            outerMap.put(key1,innerMap);
        }
        innerMap.put(key2,value);
    }
	
	public double getElement(String key1, String key2) {
        
		Double result = null;
		HashMap innerMap = outerMap.get(key1);
        if(innerMap.containsKey(key2)){
        	result = (Double) innerMap.get(key2);
        }
        else{
        	String temp = key1;
        	key1 = key2;
        	key2 = temp;
        	
        	innerMap = outerMap.get(key1);
        	if(innerMap.containsKey(key2)){
        		result = (Double) innerMap.get(key2);
        	}
        }
        
        return result;
    }
	
	
	public void deleteElemnt(String row){
		outerMap.remove(row);	
		
		Iterator itr = outerMap.keySet().iterator();
				
		while(itr.hasNext()){
			String key = (String) itr.next();

			if(outerMap.get(key).containsKey(row)){
				outerMap.get(key).remove(row);
			}
		}
	}
	
	public void displayAll(){
		Iterator itr = outerMap.keySet().iterator();
		while(itr.hasNext()){
			String key = (String) itr.next();
			System.out.print(key+"  ");
			Iterator itrInner = outerMap.get(key).keySet().iterator();
			while(itrInner.hasNext()){
				String innerKey = (String) itrInner.next();
				System.out.print(" "+outerMap.get(key).get(innerKey));
			}
			System.out.println("");
		}
		System.out.println("");
	}

	
public static void main(String[] args){
	
	ScalableMatrix matrix = new ScalableMatrix();
	
	matrix.addElement("1", "1", 1.2);
	
	matrix.addElement("2", "1", 1.3);
	matrix.addElement("2", "2", 1.4);
	
	matrix.addElement("3", "1", 1.5);
	matrix.addElement("3", "2", 1.6);
	matrix.addElement("3", "3", 1.7);
	
	matrix.addElement("4", "1", 1.8);
	matrix.addElement("4", "2", 1.9);
	matrix.addElement("4", "3", 1.11);
	matrix.addElement("4", "4", 1.12);
	
	matrix.addElement("5", "1", 1.13);
	matrix.addElement("5", "2", 1.14);
	matrix.addElement("5", "3", 1.15);
	matrix.addElement("5", "4", 1.16);
	matrix.addElement("5", "5", 1.17);

	
	matrix.displayAll();
	
	matrix.deleteElemnt("3");
	
	matrix.displayAll();
	
	matrix.deleteElemnt("1");

	matrix.displayAll();
	
	System.out.println(matrix.getElement("2", "4"));
}

}