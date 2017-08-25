package com.project.businesslogics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.study.models.QueueModel;

public class QueueCollectionLogics {

	public static Map<String,Queue> arrayNameAndListMap=new LinkedHashMap<String,Queue>();
	public static Map<String,QueueModel> arrayNameAndModelMap=new LinkedHashMap<String,QueueModel>();
	Map<String,ArrayList> map=null;
	ArrayList<Object> temp=null;
	public Map<String,QueueModel> createArrayList(String queueName,String dataType,String capacity) throws Exception{
		int size=0;
		if(capacity!=null && capacity!="" && capacity!=" "){
		size=Integer.parseInt(capacity);
		}
		
		//Store model info on basis of arrayName
		QueueModel queueCreateModel=new QueueModel();
		queueCreateModel.setQueueName(queueName);
		queueCreateModel.setQueueSize(capacity);
		queueCreateModel.setQueueDataType(dataType);
		arrayNameAndModelMap.put(queueName, queueCreateModel);
		System.out.println("arrayNameModeMap : "+arrayNameAndModelMap);
		//create arrayList track on basis of arrayName
		Queue<Object> all;
		all=new LinkedList<Object>();
		arrayNameAndListMap.put(queueName, all);
		System.out.println(all);
		System.out.println(arrayNameAndListMap);
		return arrayNameAndModelMap;
	}
	
	public ArrayList<Object> addArrayListResponse(String arrayName,String data){
		map=new LinkedHashMap<String,ArrayList>();
			temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			Iterator it=set.iterator();
			while(it.hasNext()){
				String me=(String) it.next();
				if(me!=null){
				if(me.toString().equalsIgnoreCase(arrayName)){
					temp=map.get(me);
					temp.add(data);
					}
				}
			} 
			return temp;
	}
	
	public ArrayList<Object> addAtIndexArrayListResponse(String arrayName,String data,String index){
		map=new LinkedHashMap<String,ArrayList>();
			temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			Iterator it=set.iterator();
			while(it.hasNext()){
				String me=(String) it.next();
				if(me!=null){
				if(me.toString().equalsIgnoreCase(arrayName)){
					temp=map.get(me);
					temp.add(Integer.parseInt(index), data);
					}
				}
			}
			return temp;
	}
	
	public ArrayList<Object> removeArrayListResponse(String arrayName,String data){
		map=new LinkedHashMap<String,ArrayList>();
		temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			Iterator it=set.iterator();
			while(it.hasNext()){
				String me=(String) it.next();
				if(me!=null){
				if(me.toString().equalsIgnoreCase(arrayName)){
					temp=map.get(me);
					temp.remove(data);
					}
				}
			}
			return temp;
	}
	
	public ArrayList<Object> removeAtIndexArrayListResponse(String arrayName,String data,String index){
		map=new LinkedHashMap<String,ArrayList>();
		temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			Iterator it=set.iterator();
			while(it.hasNext()){
				String me=(String) it.next();
				if(me!=null){
				if(me.toString().equalsIgnoreCase(arrayName)){
					temp=map.get(me);
					temp.remove(Integer.parseInt(index));
					}
				}
			}
			return temp;
	}
	public ArrayList<Object> removeAllArrayListResponse(String arrayName){
		map=new LinkedHashMap<String,ArrayList>();
		temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			Iterator it=set.iterator();
			while(it.hasNext()){
				String me=(String) it.next();
				if(me!=null){
				if(me.toString().equalsIgnoreCase(arrayName)){
					temp=map.get(me);
					temp.removeAll(temp);
					}
				}
			}
			return temp;
	
}
}
