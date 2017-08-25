package com.project.businesslogics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.study.models.LinkedListModel;
import com.study.mvc.HelloWorldController;
import com.study.statementModels.ArrayListStatementModel;

public class LinkedListCollectionLogics {

	public static Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	public static Map<String,LinkedList> arrayNameAndListMap=new LinkedHashMap<String,LinkedList>();
	public static Map<String,LinkedListModel> arrayNameAndModelMap=new LinkedHashMap<String,LinkedListModel>();
	Map<String,ArrayList> map=null;
	ArrayList<Object> temp=null;
	public Map<String,LinkedListModel> createLinkedList(String linkedListName,String dataType) throws Exception{
		/*int size=0;
		if(capacity!=null && capacity!="" && capacity!=" "){
		size=Integer.parseInt(capacity);
		}
		*/
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
		  message1 = "LinkedList<"+dataType+"> "+linkedListName+" =new LinkedList<"+dataType+">();";
		  modelStatement.setStatement(message1);
		  modelList.add(modelStatement);
		  syntexArraylist.put(linkedListName, modelList);
		 
		 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Add model to map 
		  
		LinkedListModel linkedListListCreateModel=new LinkedListModel();
		linkedListListCreateModel.setLinkedListName(linkedListName);
		//linkedListListCreateModel.setLinkedListSize(capacity);
		linkedListListCreateModel.setLinkedListDataType(dataType);
		arrayNameAndModelMap.put(linkedListName, linkedListListCreateModel);
		System.out.println("arrayNameModeMap : "+arrayNameAndModelMap);
		//create arrayList track on basis of arrayName
		LinkedList<Object> all;
		all=new LinkedList<Object>();
		arrayNameAndListMap.put(linkedListName, all);
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
