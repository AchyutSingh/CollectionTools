package com.project.businesslogics;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


import com.study.models.LinkedHashMapModel;
import com.study.statementModels.ArrayListStatementModel;

public class LinkedHashMapCollectionLogics {

	public static Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	public static Map<String,Map<Object,Object>> arrayNameAndListMap=new LinkedHashMap<String,Map<Object,Object>>();
	public static Map<String,LinkedHashMapModel> arrayNameAndModelMap=new LinkedHashMap<String,LinkedHashMapModel>();
	Map<String,ArrayList> map=null;
	ArrayList<Object> temp=null;
	public Map<String,LinkedHashMapModel> createArrayList(String linkedHashMapName,String keyDataType,String valueDataType,String capacity,String linkedhashmaploadfactor) throws Exception{
		int size=0;
		if(capacity!=null && capacity!="" && capacity!=" "){
		size=Integer.parseInt(capacity);
		}
		System.out.println(keyDataType+"............");
		if(keyDataType!=null)
		{
			if(keyDataType.equals("0"))
			{
				keyDataType="Object";
			}
		}
		System.out.println(valueDataType+"............");
		if(valueDataType!=null)
		{
			if(valueDataType.equals("0"))
			{
				valueDataType="Object";
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Add statement to map
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Add statement to map
ArrayListStatementModel modelStatement=null;
ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
String str1=null;
String message1=null;
modelStatement=new ArrayListStatementModel(); 
message1 = "HashMap<"+keyDataType+","+valueDataType+"> "+linkedHashMapName+" =new HashMap<"+keyDataType+","+valueDataType+">("+capacity+");";
modelStatement.setStatement(message1);
modelList.add(modelStatement);
syntexArraylist.put(linkedHashMapName, modelList);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Add model to map 

		//Store model info on basis of arrayName
		LinkedHashMapModel linkedHashMapCreateModel=new LinkedHashMapModel();
		linkedHashMapCreateModel.setLinkedHashMapName(linkedHashMapName);
		linkedHashMapCreateModel.setLinkedHashMapSize(capacity);
		linkedHashMapCreateModel.setLinkedHashMapKeyDataType(keyDataType);
		linkedHashMapCreateModel.setLinkedHashMapValueDataType(valueDataType);
		linkedHashMapCreateModel.setLinkedHashMaploadfactor(linkedhashmaploadfactor);
		arrayNameAndModelMap.put(linkedHashMapName, linkedHashMapCreateModel);
		System.out.println("arrayNameModeMap : "+arrayNameAndModelMap);
		//create arrayList track on basis of arrayName
		Map<Object,Object> all;
		all=new LinkedHashMap<Object,Object>(size);
		arrayNameAndListMap.put(linkedHashMapName, all);
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
