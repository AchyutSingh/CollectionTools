package com.project.businesslogics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.study.models.HashSetModel;
import com.study.models.StackModel;
import com.study.statementModels.ArrayListStatementModel;

public class StackCollectionLogics {

	public static Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	public static Map<String, Stack> arrayNameAndListMap=new LinkedHashMap<String,Stack>();
	public static Map<String,StackModel> arrayNameAndModelMap=new LinkedHashMap<String,StackModel>();
	Map<String,ArrayList> map=null;
	ArrayList<Object> temp=null;
	public Map<String,StackModel> createArrayList(String stackName,String dataType) throws Exception{
		
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
		  message1 = "Stack<"+dataType+"> "+stackName+" =new Stack<"+dataType+">();";
		  modelStatement.setStatement(message1);
		  modelList.add(modelStatement);
		  syntexArraylist.put(stackName, modelList);
		//Store model info on basis of arrayName
		StackModel stackCreateModel=new StackModel();
		stackCreateModel.setStackName(stackName);
		stackCreateModel.setStackDataType(dataType);
		arrayNameAndModelMap.put(stackName, stackCreateModel);
		System.out.println("arrayNameModeMap : "+arrayNameAndModelMap);
		//create arrayList track on basis of arrayName
		Stack<Object> all;
		all=new Stack<Object>();
		arrayNameAndListMap.put(stackName, all);
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
