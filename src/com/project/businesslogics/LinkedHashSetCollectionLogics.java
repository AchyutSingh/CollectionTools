package com.project.businesslogics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.study.models.LinkedHashSetModel;

public class LinkedHashSetCollectionLogics {

	public static Map<String,Set<Object>> arrayNameAndListMap=new LinkedHashMap<String,Set<Object>>();
	public static Map<String,LinkedHashSetModel> arrayNameAndModelMap=new LinkedHashMap<String,LinkedHashSetModel>();
	Map<String,ArrayList> map=null;
	ArrayList<Object> temp=null;
	public Map<String,LinkedHashSetModel> createArrayList(String linkedHashSetName,String dataType,String capacity,String linkedhashsetloadfactor) throws Exception{
		int size=0;
		if(capacity!=null && capacity!="" && capacity!=" "){
		size=Integer.parseInt(capacity);
		}
		
		//Store model info on basis of arrayName
		LinkedHashSetModel linkedHashSetCreateModel=new LinkedHashSetModel();
		linkedHashSetCreateModel.setLinkedHashSetName(linkedHashSetName);
		linkedHashSetCreateModel.setLinkedHashSetSize(capacity);
		linkedHashSetCreateModel.setLinkedHashSetDataType(dataType);
		linkedHashSetCreateModel.setLinkedHashSetloadfactor(linkedhashsetloadfactor);
		
		arrayNameAndModelMap.put(linkedHashSetName, linkedHashSetCreateModel);
		System.out.println("arrayNameModeMap : "+arrayNameAndModelMap);
		//create arrayList track on basis of arrayName
		Set<Object> all;
		all=new LinkedHashSet<Object>(size);
		arrayNameAndListMap.put(linkedHashSetName, all);
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
