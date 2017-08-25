package com.project.businesslogics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.study.models.ArrayListCreateModel;
import com.study.mvc.HelloWorldController;
import com.study.statementModels.ArrayListStatementModel;

public class ArrayCollectionLogics {

	public static Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	public static Map<String,ArrayList> arrayNameAndListMap=new LinkedHashMap<String,ArrayList>();
	public static Map<String,ArrayListCreateModel> arrayNameAndModelMap=new LinkedHashMap<String,ArrayListCreateModel>();
	Map<String,ArrayList> map=null;
	ArrayList<Object> temp=null;
	public Map<String,ArrayListCreateModel> createArrayList(String arrayName,String dataType,String capacity) throws Exception{
		int size=0;
		if(capacity!=null && capacity!="" && capacity!=" "){
		size=Integer.parseInt(capacity);
		}
		
		//Store model info on basis of arrayName
		System.out.println(dataType+"............");
		if(dataType!=null)
		{
			if(dataType.equals("0"))
			{
				dataType="Object";
			}
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Add statement to map
		ArrayListStatementModel modelStatement=null;
		ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
		String str1=null;
		String message1=null;
		  modelStatement=new ArrayListStatementModel(); 
		  message1 = "ArrayList<"+dataType+"> "+arrayName+" =new ArrayList<"+dataType+">();";
		  modelStatement.setStatement(message1);
		  modelList.add(modelStatement);
		  syntexArraylist.put(arrayName, modelList);
		 
		 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Add model to map 
		  
		ArrayListCreateModel arrayListCreateModel=new ArrayListCreateModel();
		arrayListCreateModel.setArrayName(arrayName);
		arrayListCreateModel.setArraySize(capacity);
		arrayListCreateModel.setArrayDataType(dataType);
		arrayNameAndModelMap.put(arrayName, arrayListCreateModel);
		System.out.println("arrayNameModeMap : "+arrayNameAndModelMap);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// create List
		ArrayList<Object> all;
		all=new ArrayList<Object>(size);
		arrayNameAndListMap.put(arrayName, all);
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


