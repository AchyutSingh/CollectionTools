package com.study.mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.businesslogics.ArrayCollectionLogics;
import com.project.businesslogics.HashMapCollectionLogics;
import com.project.businesslogics.HashSetCollectionLogics;
import com.project.businesslogics.LinkedHashMapCollectionLogics;
import com.project.businesslogics.LinkedHashSetCollectionLogics;
import com.project.businesslogics.LinkedListCollectionLogics;
import com.project.businesslogics.NavigableSetCollectionLogics;
import com.project.businesslogics.QueueCollectionLogics;
import com.project.businesslogics.SortedSetCollectionLogics;
import com.project.businesslogics.StackCollectionLogics;
import com.project.businesslogics.TreeMapCollectionLogics;
import com.project.businesslogics.TreeSetCollectionLogics;
import com.study.models.ArrayListCreateModel;
import com.study.models.HashMapModel;
import com.study.models.HashSetModel;
import com.study.models.LinkedHashMapModel;
import com.study.models.LinkedHashSetModel;
import com.study.models.LinkedListModel;
import com.study.models.NavigableSetModel;
import com.study.models.QueueModel;
import com.study.models.SortedSetModel;
import com.study.models.StackModel;
import com.study.models.TreeMapModel;
import com.study.models.TreeSetModel;
import com.study.statementModels.ArrayListStatementModel;

@Controller
public class HelloWorldController {
	
	/*----------------------------ArrayListController---------------------------------------*/
	@RequestMapping("/arraylist")
	public ModelAndView arrayResponse(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		session.setAttribute("firstvalue", "0");
		session.setAttribute("syntex", "0");
		
		System.out.println("inside array controller arraylist");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,ArrayListCreateModel> tempMap=new LinkedHashMap<String,ArrayListCreateModel>();
	    ArrayList<ArrayListCreateModel> arrayListToSend=new ArrayList<ArrayListCreateModel>();
		System.out.println("inside array controller arraylist");
		//create new list and entry in map
		tempMap=ArrayCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		ArrayListCreateModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (ArrayListCreateModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getArrayName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("arraylist", "message", message);
	}
	@RequestMapping("/createarraylist")
	public ModelAndView arrayListResponse(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		session.setAttribute("firstvalue", "1");
		String syntex="";
		
	    Map<String,ArrayListCreateModel> tempMap=new LinkedHashMap<String,ArrayListCreateModel>();
	    ArrayList<ArrayListCreateModel> arrayListToSend=new ArrayList<ArrayListCreateModel>();
	    String message=" ";
	    String message1=" ";
	    try{
	    	String arrayName=request.getParameter("arrayname");
			String arraySize=request.getParameter("arraysize");
			if(arraySize=="")
			{
				arraySize="10";
			}
			String dataType=request.getParameter("datatype");
			
			System.out.println("Array name : "+arrayName+" Array size : "+arraySize+" Data type :"+dataType);
			System.out.println("inside controller arraylist");
			System.out.println("returning List map :"+ArrayCollectionLogics.arrayNameAndListMap);
			///////////////////////////////////////////////////////////////////////////////////////////////////////check if arrayName already exist or not
			Boolean ind=true;
			Set set=ArrayCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(arrayName)){
					   message = arrayName+" allready Created.";
					   ind=false;
					   
					   //ArrayCollectionLogics arrayCollectionLogics=new ArrayCollectionLogics();
						tempMap=ArrayCollectionLogics.arrayNameAndModelMap;
						System.out.println("returning List map :"+tempMap);
						Set tempSet=tempMap.entrySet();
						Iterator tempIt=tempSet.iterator();
						//String me=null;
						ArrayListCreateModel ar=null;
						while(tempIt.hasNext()){
							Map.Entry me2 = (Map.Entry)tempIt.next();
							ar = (ArrayListCreateModel) me2.getValue();
							System.out.println("printing data : "+ar);
							System.out.println("printing data : "+ar.getArrayName());
							arrayListToSend.add(ar);
							
						}
					   
						request.setAttribute("SENDARRAY", arrayListToSend);
					   
					   return new ModelAndView("arraylist", "message", message);
					}
				}
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////create new list and entry in map
			ArrayCollectionLogics arrayCollectionLogics=new ArrayCollectionLogics();
			if(arrayName!=null)
			{
				tempMap=arrayCollectionLogics.createArrayList(arrayName, dataType, arraySize);
				System.out.println("returning List map :"+tempMap);
				Set tempSet=tempMap.entrySet();
				Iterator tempIt=tempSet.iterator();
				String me=null;
				ArrayListCreateModel ar=null;
				while(tempIt.hasNext()){
					Map.Entry me2 = (Map.Entry)tempIt.next();
					ar = (ArrayListCreateModel) me2.getValue();
					System.out.println("printing data : "+ar);
					System.out.println("printing data : "+ar.getArrayName());
					arrayListToSend.add(ar);
					
				}
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////fetch the array statement
				Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist1=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> a2=null;
				Set set12=syntexArraylist1.entrySet();
				Iterator it12=set12.iterator();
				String str12=null;
				while(it12.hasNext()){
					Map.Entry me2 = (Map.Entry)it12.next();
					str12=me2.getKey().toString();
			        
					if(str12!=null){
					if(str12.equalsIgnoreCase(arrayName)){
						a2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						}
					}
				}
				System.out.println("a2----"+a2);
				/////////////////////////////////////////////////////
				session.setAttribute("syntex", a2);
				System.out.println("array to send :"+arrayListToSend);
				request.setAttribute("SENDARRAY", arrayListToSend);
				if(arrayName!=null){
				   message = arrayName+" : Created.";
				}
			}
			else
			{
				tempMap=ArrayCollectionLogics.arrayNameAndModelMap;
				System.out.println("returning List map :"+tempMap);
				Set tempSet=tempMap.entrySet();
				Iterator tempIt=tempSet.iterator();
				//String me=null;
				ArrayListCreateModel ar=null;
				while(tempIt.hasNext()){
					Map.Entry me2 = (Map.Entry)tempIt.next();
					ar = (ArrayListCreateModel) me2.getValue();
					System.out.println("printing data : "+ar);
					System.out.println("printing data : "+ar.getArrayName());
					arrayListToSend.add(ar);
					
				}
			   
				request.setAttribute("SENDARRAY", arrayListToSend);
			}
		    }catch (Exception e) {
		    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
				e.printStackTrace();
			}
	    return new ModelAndView("arraylist", "message", message);
	}
	
	@RequestMapping("/addarray")
	public void addArrayListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////// add value in array
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add arraylist");
	    	String arrayName=request.getParameter("arrayname");
			String data=request.getParameter("data");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.add(data);
					}
				}
			}
			System.out.println("temp----"+temp);
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Add Statement in Array
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".add("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////// Returning data to AJAX
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			
			out.print(returnvalue);
			
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/arrayshowonradio")
	public void arrayShowOnRadioResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller show radio");
	    	String arrayName=request.getParameter("arrayname");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
			
			Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist1=ArrayCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> a2=null;
			Set set12=syntexArraylist1.entrySet();
			Iterator it12=set12.iterator();
			String str12=null;
			while(it12.hasNext()){
				Map.Entry me2 = (Map.Entry)it12.next();
				str12=me2.getKey().toString();
		        
				if(str12!=null){
				if(str12.equalsIgnoreCase(arrayName)){
					a2=(ArrayList<ArrayListStatementModel>) me2.getValue();
					}
				}
			}
			//System.out.println("a2----"+a2.get(0).getStatement());
			session.setAttribute("syntex1", a2);
			
			
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				String str=temp.get(i).toString();
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			out.print(returnvalue);
			
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	
	
	
	@RequestMapping("/addatindexarray")
	public void addAtIndexArrayListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller addAtIndex arraylist");
	    	String arrayName=request.getParameter("arrayname");
			String data=request.getParameter("data");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.add(Integer.parseInt(index), data);
					}
				}
			}
			//request.setAttribute("MODIFIEDARRAYLIST", temp);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".add("+index+","+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				String str=temp.get(i).toString();
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removearray")
	public void removeArrayListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller remove arraylist");
	    	String arrayName=request.getParameter("arrayname");
			String data=request.getParameter("data");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			//request.setAttribute("MODIFIEDARRAYLIST", temp);
			
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".remove("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				String str=temp.get(i).toString();
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/removeatindexarray")
	public void removeAtIndexArrayListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAtIndex arraylist");
	    	String arrayName=request.getParameter("arrayname");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.remove(Integer.parseInt(index));
					}
				}
			}
			
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".remove("+index+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				String str=temp.get(i).toString();
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/getatindexarray")
	public void getAtIndexArrayListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message=" ";
	    Object returnvalue=null;
	    try{
	    	System.out.println("inside controller getAtIndex arraylist");
	    	String arrayName=request.getParameter("arrayname");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					returnvalue=temp.get(Integer.parseInt(index));
					}
				}
			}
			
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".get("+index+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			//String returnvalue="";
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}

	@RequestMapping("/containsarraylist")
	public void containsMethodArrayListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller contains arrayList");
	    	String arrayName=request.getParameter("arraylistname");
			String data=request.getParameter("data");
			System.out.println("arraylistname : "+arrayName+" data : "+data);
			ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					Boolean b=temp.contains(data);
					if(b==true){
						message="Data Found";
					}
					}
				}
			}
			System.out.println("message :"+message);
			//request.setAttribute("message", message);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".contains("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/indexofarraylist")
	public void indexOfMethodArrayListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller indexof arrayList");
	    	String arrayName=request.getParameter("arraylistname");
			String data=request.getParameter("data");
			System.out.println("arraylistname : "+arrayName+" data : "+data);
			ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			int b=0;
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					b=temp.indexOf(data);
					}
				}
			}
			System.out.println("message :"+b);
			//request.setAttribute("message", b);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".indexOf("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(b);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/lastindexofarraylist")
	public void lastIndexOfMethodArrayListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller last index of arrayList");
	    	String arrayName=request.getParameter("arraylistname");
			String data=request.getParameter("data");
			System.out.println("arraylistname : "+arrayName+" data : "+data);
			ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			int b=0;
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					b=temp.lastIndexOf(data);
					}
				}
			}
			System.out.println("message :"+b);
			//request.setAttribute("message", b);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".lastIndexOf("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(b);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	@RequestMapping("/sizeofarraylist")
	public void sizeOfArrayListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    int size=0;
	    try{
	    	System.out.println("inside controller aize of arraylist");
	    	String arrayName=request.getParameter("arraylistname");
	    	ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					size=temp.size();
					}
				}
			}
			request.setAttribute("message", size);
			
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".size("+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(size);
	    }catch (Exception e) {
	    	 String message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/isemptyarraylist")
	public void isEmptyMethodArrayListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message="Not Empty";
	    try{
	    	System.out.println("inside controller is empty arraylist");
	    	String arrayName=request.getParameter("arraylistname");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					Boolean b=temp.isEmpty();
					if(b==true){
						message="Empty";
					}
					}
				}
			}
			request.setAttribute("message", message);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".isEmpty("+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/removeallarray")
	public void removeAllArrayListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAll arraylist");
	    	String arrayName=request.getParameter("arrayname");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.removeAll(temp);
					}
				}
			}
		
			//request.setAttribute("MODIFIEDARRAYLIST", temp);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".removeAll("+arrayName+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				String str=temp.get(i).toString();
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	/*----------------------------HashSetController---------------------------------------*/
	@RequestMapping("/hashset")
	public ModelAndView hashSetResponse(HttpServletRequest request, HttpServletResponse response){
		System.out.println("inside controller hashSet");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,HashSetModel> tempMap=new LinkedHashMap<String,HashSetModel>();
	    ArrayList<HashSetModel> arrayListToSend=new ArrayList<HashSetModel>();
		//create new list and entry in map
		tempMap=HashSetCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		HashSetModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (HashSetModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getHashSetName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("hashset", "message", message);
	}
	@RequestMapping("/createhashset")
	public ModelAndView createHashsetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,HashSetModel> tempMap=new LinkedHashMap<String,HashSetModel>();
	    ArrayList<HashSetModel> arrayListToSend=new ArrayList<HashSetModel>();
	    String message=" ";
	    try{
	    	String hashSetName=request.getParameter("hashsetname");
			String hashSetSize=request.getParameter("hashsetsize");
			String hashSetLoadFactor=request.getParameter("hashsetloadfactor");
			String dataType=request.getParameter("datatype");
			
			System.out.println("Hashset name : "+hashSetName+" hashset size : "+hashSetSize+" Data type :"+dataType);
			System.out.println("inside controller hashMap");
			System.out.println("returning List map :"+ArrayCollectionLogics.arrayNameAndListMap);
			//check if arrayName already exist or not
			Set set=HashSetCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(hashSetName)){
					   message = "Array : "+hashSetName+" : allready Created.";
					   return new ModelAndView("hashset", "model", map);
					}
				}
			}
			//create new list and entry in map
			HashSetCollectionLogics arrayCollectionLogics=new HashSetCollectionLogics();
			tempMap=arrayCollectionLogics.createArrayList(hashSetName, dataType, hashSetSize,hashSetLoadFactor);
			System.out.println("returning List map :"+tempMap);
			Set tempSet=tempMap.entrySet();
			Iterator tempIt=tempSet.iterator();
			String me=null;
			HashSetModel ar=null;
			while(tempIt.hasNext()){
				Map.Entry me2 = (Map.Entry)tempIt.next();
				ar = (HashSetModel) me2.getValue();
				System.out.println("printing data : "+ar);
				System.out.println("printing data : "+ar.getHashSetName());
				arrayListToSend.add(ar);
			}
			System.out.println("array to send :"+arrayListToSend);
			request.setAttribute("SENDARRAY", arrayListToSend);
			if(hashSetName!=null){
			   message = "HashSet : "+hashSetName+" : Created.";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	    return new ModelAndView("hashset", "message", message);
	}
	@RequestMapping("/hashsetshowonradio")
	public void hashSetShowOnRadioResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add arraylist");
	    	String arrayName=request.getParameter("arrayname");
			Set<Object> temp=new HashSet<Object>();
			map=HashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator<Object> itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
			
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/addhashset")
	public void addHashSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add HashSet");
	    	String arrayName=request.getParameter("hashsetname");
			String data=request.getParameter("data");
			Set<Object> temp=new HashSet<Object>();
			map=HashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp= (Set<Object>) me2.getValue();
					temp.add(data);
					}
				}
			}
			System.out.println("Temp-----:"+temp);
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/sizeofhashSet")
	public void addAtIndexHasSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    int size=0;
	    try{
	    	System.out.println("inside controller addAtIndex HashSet");
	    	String arrayName=request.getParameter("hashsetname");
			Set<Object> temp=new HashSet<Object>();
			map=HashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					size=temp.size();
					}
				}
			}
			request.setAttribute("message", size);
			request.setAttribute("message", size);
			out.print(size);
	    }catch (Exception e) {
	    	 String message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removehashset")
	public void removeHashSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out =response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller remove HashSet");
	    	String arrayName=request.getParameter("hashsetname");
			String data=request.getParameter("data");
			Set<Object> temp=new HashSet<Object>();
			map=HashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/removeallhashset")
	public void removeAllHashSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAll HashSet");
	    	String arrayName=request.getParameter("hashsetname");
			Set<Object> temp=new HashSet<Object>();
			map=HashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					temp.removeAll(temp);
					}
				}
			}
		
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/containshashset")
	public void containsMethodHashSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller removeAll HashSet");
	    	String arrayName=request.getParameter("hashsetname");
			String data=request.getParameter("data");
			System.out.println("hashsetname : "+arrayName+" data : "+data);
			Set<Object> temp=new HashSet<Object>();
			map=HashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					Boolean b=temp.contains(data);
					if(b==true){
						message="Data Found";
					}
					}
				}
			}
			System.out.println("message :"+message);
			request.setAttribute("message", message);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/isemptyhashset")
	public void isEmptyMethodHashSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message="Not Empty";
	    try{
	    	System.out.println("inside controller removeAll HashSet");
	    	String arrayName=request.getParameter("hashsetname");
			String data=request.getParameter("data");
			Set<Object> temp=new HashSet<Object>();
			map=HashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					Boolean b=temp.isEmpty();
					if(b==true){
						message="Empty";
					}
					}
				}
			}
			request.setAttribute("message", message);
			request.setAttribute("message", message);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	/*----------------------------LinkedHashMapController---------------------------------------*/
	
	@RequestMapping("/linkedhashmap")
	public ModelAndView linkedHashMapResponse(HttpServletRequest request, HttpServletResponse response){
		System.out.println("inside controller LinkedHashMap");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,LinkedHashMapModel> tempMap=new LinkedHashMap<String,LinkedHashMapModel>();
	    ArrayList<LinkedHashMapModel> arrayListToSend=new ArrayList<LinkedHashMapModel>();
		//create new list and entry in map
		tempMap=LinkedHashMapCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		LinkedHashMapModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (LinkedHashMapModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getLinkedHashMapName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("linkedhashmap", "message", message);
	}
	@RequestMapping("/createlinkedhashmap")
	public ModelAndView createLinkedHashMapResponse(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		session.setAttribute("firstvalue", "1");
		String syntex="";
		 
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,LinkedHashMapModel> tempMap=new LinkedHashMap<String,LinkedHashMapModel>();
	    ArrayList<LinkedHashMapModel> arrayListToSend=new ArrayList<LinkedHashMapModel>();
	    String message=" ";
	    String message1=" ";
	    try{
	    	String linkedHashMapName=request.getParameter("linkedhashmapname");
			String linkedHashMapSize=request.getParameter("linkedhashmapsize");
			String keyDataType=request.getParameter("keydatatype");
			String valueDataType=request.getParameter("valuedatatype");
			String linkedHashMaploadfactor=request.getParameter("linkedhashmaploadfactor");
			if(linkedHashMapSize=="")
			{
				linkedHashMapSize="10";
			}
			
			System.out.println("linkedhashmap name : "+linkedHashMapName+" LinkedHashMap size : "+linkedHashMapSize+"key Data type :"+keyDataType);
			System.out.println("inside controller linkedHashMap");
			System.out.println("returning List map :"+LinkedHashMapCollectionLogics.arrayNameAndListMap);
			//check if LinkedHashMapName already exist or not
			Boolean ind=true;
			Set set=LinkedHashMapCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(linkedHashMapName)){
					   message = linkedHashMapName+" allready Created.";
					   ind=false;
					   
						tempMap=LinkedHashMapCollectionLogics.arrayNameAndModelMap;
						System.out.println("returning List map :"+tempMap);
						Set tempSet=tempMap.entrySet();
						Iterator tempIt=tempSet.iterator();
						//String me=null;
						LinkedHashMapModel ar=null;
						while(tempIt.hasNext()){
							Map.Entry me2 = (Map.Entry)tempIt.next();
							ar = (LinkedHashMapModel) me2.getValue();
							System.out.println("printing data : "+ar);
							System.out.println("printing data : "+ar.getLinkedHashMapName());
							arrayListToSend.add(ar);
							
						}
					   
						request.setAttribute("SENDARRAY", arrayListToSend);
					   
					   return new ModelAndView("linkedhashmap", "message", message);
					}
				}
			}
			//create new list and entry in map
			LinkedHashMapCollectionLogics arrayCollectionLogics=new LinkedHashMapCollectionLogics();
			if(linkedHashMapName!=null){
				tempMap=arrayCollectionLogics.createArrayList(linkedHashMapName, keyDataType,valueDataType, linkedHashMapSize,linkedHashMaploadfactor);
				System.out.println("returning List map :"+tempMap);
				Set tempSet=tempMap.entrySet();
				Iterator tempIt=tempSet.iterator();
				String me=null;
				LinkedHashMapModel ar=null;
				while(tempIt.hasNext()){
					Map.Entry me2 = (Map.Entry)tempIt.next();
					ar = (LinkedHashMapModel) me2.getValue();
					System.out.println("printing data : "+ar);
					System.out.println("printing data : "+ar.getLinkedHashMapName());
					arrayListToSend.add(ar);
				}
				
				Map<String, ArrayList<ArrayListStatementModel>> syntexArraylist1=HashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> a2=null;
				Set set12=syntexArraylist1.entrySet();
				Iterator it12=set12.iterator();
				String str12=null;
				while(it12.hasNext()){
					Map.Entry me2 = (Map.Entry)it12.next();
					str12=me2.getKey().toString();
			        
					if(str12!=null){
					if(str12.equalsIgnoreCase(linkedHashMapName)){
						a2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						}
					}
				}
				System.out.println("a2----"+a2);
				/////////////////////////////////////////////////////
				session.setAttribute("syntex", a2);
				
				System.out.println("array to send :"+arrayListToSend);
				request.setAttribute("SENDARRAY", arrayListToSend);
				if(linkedHashMapName!=null){
				   message = "LinkedHashMap : "+linkedHashMapName+" : Created.";
				}else{
					tempMap=arrayCollectionLogics.createArrayList(linkedHashMapName, keyDataType,valueDataType, linkedHashMapSize,linkedHashMaploadfactor);
					System.out.println("returning List map :"+tempMap);
					Set tempSet1=tempMap.entrySet();
					Iterator tempIt1=tempSet1.iterator();
					String me1=null;
					LinkedHashMapModel ar1=null;
					while(tempIt1.hasNext()){
						Map.Entry me2 = (Map.Entry)tempIt1.next();
						ar = (LinkedHashMapModel) me2.getValue();
						System.out.println("printing data : "+ar);
						System.out.println("printing data : "+ar.getLinkedHashMapName());
						arrayListToSend.add(ar);
					}
				}
				request.setAttribute("SENDARRAY", arrayListToSend);
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	    return new ModelAndView("linkedhashmap", "message", message);
	}
	
	@RequestMapping("/putlinkedhashmap")
	public void putLinkedHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller put linkedHashMap");
	    	String linkedhashmapName=request.getParameter("linkedhashmapname");
	    	String keyData=request.getParameter("key");
			String valueData=request.getParameter("value");
			Map<Object,Object> temp=new LinkedHashMap<Object,Object>();
			map=LinkedHashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedhashmapName)){
					temp=(Map<Object,Object>) me2.getValue();
					temp.put(keyData, valueData);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(linkedhashmapName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Set set2=temp.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it2=set2.iterator();
			while(it2.hasNext()){
				Map.Entry me2 = (Map.Entry)it2.next();
				Object key= me2.getKey();
				Object value=  me2.getValue();
				System.out.println("key------"+key+"value-----"+value);
				returnvalue+="<div class='array'>"+key+" , "+value+"</div>";
				
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/isemptylinkedhashmap")
	public void isEmptyLinkedHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message="Not Empty";
	    try{
	    	System.out.println("inside controller is empty linkedHashMap");
	    	String linkedhashmapName=request.getParameter("linkedhashmapname");
			
			Map<Object,	Object> temp=new LinkedHashMap<Object,Object>();
			map=LinkedHashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedhashmapName)){
					temp=(Map<Object, Object>) me2.getValue();
					Boolean b=temp.isEmpty();
					if(b==true){
						message="Empty";
					}
					}
				}
			}
			request.setAttribute("message", message);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedhashmapName+".isEmpty("+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedHashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedHashMapCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedhashmapName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/sizeoflinkedhashmap")
	public void sizeOfLinkedHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    int size=0;
	    try{
	    	System.out.println("inside controller size of linkedhashmap");
	    	String linkedhashmapName=request.getParameter("linkedhashmapname");
	    	Map<Object,Object> temp=new LinkedHashMap<Object,Object>();
			map=LinkedHashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedhashmapName)){
					temp=(Map<Object, Object>) me2.getValue();
					size=temp.size();
					}
				}
			}
			request.setAttribute("message", size);
			
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedhashmapName+".size("+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedHashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedHashMapCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedhashmapName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(size);
	    }catch (Exception e) {
	    	 String message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removelinkedhashmap")
	public void removeLinkedHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller remove LinkedHashMap");
	    	String linkedhashmapName=request.getParameter("linkedhashmapname");
			String data=request.getParameter("key");
			Map<Object,Object> temp=new LinkedHashMap<Object,Object>();
			map=LinkedHashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedhashmapName)){
					temp=(Map<Object, Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(linkedhashmapName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Set set2=temp.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it2=set2.iterator();
			while(it2.hasNext()){
				Map.Entry me2 = (Map.Entry)it2.next();
				Object key= me2.getKey();
				Object value=  me2.getValue();
				System.out.println("key------"+key+"value-----"+value);
				returnvalue+="<div class='array'>"+key+" , "+value+"</div>";
				
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/getkeylinkedhashmap")
	public void getKeyLinkedHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message=" ";
	    Object returnvalue=null;
	    try{
	    	System.out.println("inside controller getKey linkedHashMap");
	    	String linkedhashmapName=request.getParameter("linkedhashmapname");
			String data=request.getParameter("key");
			Map<Object,Object> temp=new LinkedHashMap<Object,Object>();
			map=LinkedHashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedhashmapName)){
					temp=(Map<Object, Object>) me2.getValue();
					returnvalue=temp.get(data);
					}
				}
			}
			
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedhashmapName+".get("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedHashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedHashMapCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedhashmapName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			//String returnvalue="";
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/containskeylinkedhashmap")
	public void containsKeyMethodLinkedHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller contains LinkedHashMap");
	    	String linkedhashmapName=request.getParameter("linkedhashmapname");
	    	String data=request.getParameter("key");
	    	System.out.println("LinkedHashMapname : "+linkedhashmapName+" data : "+data);
			Map<Object,Object> temp=new LinkedHashMap<Object,Object>();
			map=LinkedHashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedhashmapName)){
					temp=(Map<Object, Object>) me2.getValue();
					Boolean b=temp.containsKey(data);
					if(b==true){
						message="Data Found";
					}
					}
				}
			}
			System.out.println("message :"+message);
			//request.setAttribute("message", message);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedhashmapName+".containsKey("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedHashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedHashMapCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedhashmapName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/containsvaluelinkedhashmap")
	public void containsValueMethodLinkedHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller contains linkedhashmap");
	    	String linkedhashmapName=request.getParameter("linkedhashmapname");
			String data=request.getParameter("key");
			System.out.println("hashmap : "+linkedhashmapName+" key : "+data);
			Map<Object,Object> temp=new LinkedHashMap<Object,Object>();
			map=LinkedHashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedhashmapName)){
					temp=(Map<Object, Object>) me2.getValue();
					Boolean b=temp.containsValue(data);
					if(b==true){
						message="Data Found";
					}
					}
				}
			}
			System.out.println("message :"+message);
			//request.setAttribute("message", message);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedhashmapName+".containsValue("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedHashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedHashMapCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedhashmapName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
/*----------------------------HashMapController---------------------------------------*/
	
	@RequestMapping("/hashmap")
	public ModelAndView hashMapResponse(HttpServletRequest request, HttpServletResponse response){
		System.out.println("inside controller hashMap");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,HashMapModel> tempMap=new LinkedHashMap<String,HashMapModel>();
	    ArrayList<HashMapModel> arrayListToSend=new ArrayList<HashMapModel>();
		//create new list and entry in map
		tempMap=HashMapCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		HashMapModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (HashMapModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getHashMapName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("hashmap", "message", message);
	}
	@RequestMapping("/createhashmap")
	public ModelAndView createHashMapResponse(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		session.setAttribute("firstvalue", "1");
		String syntex="";
		 
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,HashMapModel> tempMap=new LinkedHashMap<String,HashMapModel>();
	    ArrayList<HashMapModel> arrayListToSend=new ArrayList<HashMapModel>();
	    String message=" ";
	    String message1=" ";
	    try{
	    	String hashMapName=request.getParameter("hashmapname");
			String hashMapSize=request.getParameter("hashmapize");
			String keyDataType=request.getParameter("keydatatype");
			String valueDataType=request.getParameter("valuedatatype");
			String hashmaploadfactor=request.getParameter("hashmaploadfactor");
			if(hashMapSize=="")
			{
				hashMapSize="10";
			}
			System.out.println("HashMap name : "+hashMapName+" Array size : "+hashMapSize+"key Data type :"+keyDataType);
			System.out.println("inside controller hashMap");
			System.out.println("returning List map :"+HashMapCollectionLogics.arrayNameAndListMap);
			//check if arrayName already exist or not
			Boolean ind=true;
			Set set=ArrayCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(hashMapName)){
					   message = hashMapName+" allready Created.";
					   ind=false;
					   
						tempMap=HashMapCollectionLogics.arrayNameAndModelMap;
						System.out.println("returning List map :"+tempMap);
						Set tempSet=tempMap.entrySet();
						Iterator tempIt=tempSet.iterator();
						//String me=null;
						HashMapModel ar=null;
						while(tempIt.hasNext()){
							Map.Entry me2 = (Map.Entry)tempIt.next();
							ar = (HashMapModel) me2.getValue();
							System.out.println("printing data : "+ar);
							System.out.println("printing data : "+ar.getHashMapName());
							arrayListToSend.add(ar);
							
						}
					   
						request.setAttribute("SENDARRAY", arrayListToSend);
					   
					   return new ModelAndView("hashmap", "message", message);
					}
				}
			}
			
			//create new list and entry in map
			HashMapCollectionLogics arrayCollectionLogics=new HashMapCollectionLogics();
			if(hashMapName!=null){
				tempMap=arrayCollectionLogics.createArrayList(hashMapName, keyDataType,valueDataType, hashMapSize,hashmaploadfactor);
				System.out.println("returning List map :"+tempMap);
				Set tempSet=tempMap.entrySet();
				Iterator tempIt=tempSet.iterator();
				String me=null;
				HashMapModel ar=null;
				while(tempIt.hasNext()){
					Map.Entry me2 = (Map.Entry)tempIt.next();
					ar = (HashMapModel) me2.getValue();
					System.out.println("printing data : "+ar);
					System.out.println("printing data : "+ar.getHashMapName());
					arrayListToSend.add(ar);
				}
				
				Map<String, ArrayList<ArrayListStatementModel>> syntexArraylist1=HashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> a2=null;
				Set set12=syntexArraylist1.entrySet();
				Iterator it12=set12.iterator();
				String str12=null;
				while(it12.hasNext()){
					Map.Entry me2 = (Map.Entry)it12.next();
					str12=me2.getKey().toString();
			        
					if(str12!=null){
					if(str12.equalsIgnoreCase(hashMapName)){
						a2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						}
					}
				}
				System.out.println("a2----"+a2);
				/////////////////////////////////////////////////////
				session.setAttribute("syntex", a2);
				
				System.out.println("array to send :"+arrayListToSend);
				request.setAttribute("SENDARRAY", arrayListToSend);
				if(hashMapName!=null){
				   message = "HashMap : "+hashMapName+" : Created.";
				}else{
					tempMap=arrayCollectionLogics.createArrayList(hashMapName, keyDataType,valueDataType, hashMapSize,hashmaploadfactor);
					System.out.println("returning List map :"+tempMap);
					Set tempSet1=tempMap.entrySet();
					Iterator tempIt1=tempSet1.iterator();
					String me1=null;
					HashMapModel ar1=null;
					while(tempIt1.hasNext()){
						Map.Entry me2 = (Map.Entry)tempIt1.next();
						ar = (HashMapModel) me2.getValue();
						System.out.println("printing data : "+ar);
						System.out.println("printing data : "+ar.getHashMapName());
						arrayListToSend.add(ar);
					}
				}
				request.setAttribute("SENDARRAY", arrayListToSend);
			}
		
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	    return new ModelAndView("hashmap", "message", message);
	}
	
	
	@RequestMapping("/puthashmap")
	public void putHasMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller put HashMap");
	    	String hashMapName=request.getParameter("hashmapname");
			String keyData=request.getParameter("key");
			String valueData=request.getParameter("value");
			HashMap<Object,Object> temp=new HashMap<Object,Object>();
			map=HashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(hashMapName)){
					temp=(HashMap<Object, Object>) me2.getValue();
					temp.put(keyData, valueData);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(hashMapName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Set set2=temp.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it2=set2.iterator();
			while(it2.hasNext()){
				Map.Entry me2 = (Map.Entry)it2.next();
				Object key= me2.getKey();
				Object value=  me2.getValue();
				System.out.println("key------"+key+"value-----"+value);
				returnvalue+="<div class='array'>"+key+" , "+value+"</div>";
				
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/isemptyhashmap")
	public void isEmptyMethodHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message="Not Empty";
	    try{
	    	System.out.println("inside controller is empty hashmap");
	    	String arrayName=request.getParameter("hashmapname");
			HashMap<Object,Object> temp=new HashMap<Object,Object>();
			map=HashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(HashMap<Object, Object>) me2.getValue();
					Boolean b=temp.isEmpty();
					if(b==true){
						message="Empty";
					}
					}
				}
			}
			request.setAttribute("message", message);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".isEmpty("+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=HashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=HashMapCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/sizeofhashmap")
	public void sizeOfHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    int size=0;
	    try{
	    	System.out.println("inside controller size of hashmap");
	    	String arrayName=request.getParameter("hashmapname");
	    	HashMap<Object,Object> temp=new HashMap<Object,Object>();
			map=HashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(HashMap<Object, Object>) me2.getValue();
					size=temp.size();
					}
				}
			}
			request.setAttribute("message", size);
			
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".size("+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=HashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=HashMapCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(size);
	    }catch (Exception e) {
	    	 String message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removehashmap")
	public void removeHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller remove HashMap");
	    	String hashMapName=request.getParameter("hashmapname");
			String data=request.getParameter("key");
			HashMap<Object,Object> temp=new HashMap<Object,Object>();
			map=HashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(hashMapName)){
					temp=(HashMap<Object, Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(hashMapName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Set set2=temp.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it2=set2.iterator();
			while(it2.hasNext()){
				Map.Entry me2 = (Map.Entry)it2.next();
				Object key= me2.getKey();
				Object value=  me2.getValue();
				System.out.println("key------"+key+"value-----"+value);
				returnvalue+="<div class='array'>"+key+" , "+value+"</div>";
				
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/getkeyhashmap")
	public void getKeyHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message=" ";
	    Object returnvalue=null;
	    try{
	    	System.out.println("inside controller getKey hashmap");
	    	String hashMapName=request.getParameter("hashmapname");
			String data=request.getParameter("key");
			HashMap<Object,Object> temp=new HashMap<Object,Object>();
			map=HashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(hashMapName)){
					temp=(HashMap<Object, Object>) me2.getValue();
					returnvalue=temp.get(data);
					}
				}
			}
			
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = hashMapName+".get("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=HashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=HashMapCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(hashMapName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			//String returnvalue="";
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/containskayhashmap")
	public void containsKeyMethodHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller contains hashmap");
	    	String arrayName=request.getParameter("hashmapname");
			String data=request.getParameter("key");
			System.out.println("arraylistname : "+arrayName+" data : "+data);
			HashMap<Object,Object> temp=new HashMap<Object,Object>();
			map=HashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(HashMap<Object, Object>) me2.getValue();
					Boolean b=temp.containsKey(data);
					if(b==true){
						message="Data Found";
					}
					}
				}
			}
			System.out.println("message :"+message);
			//request.setAttribute("message", message);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".containsKey("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=HashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=HashMapCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/containsvaluehashmap")
	public void containsValueMethodHashMapResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller contains hashmap");
	    	String arrayName=request.getParameter("hashmapname");
			String data=request.getParameter("key");
			System.out.println("hashmap : "+arrayName+" key : "+data);
			HashMap<Object,Object> temp=new HashMap<Object,Object>();
			map=HashMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(HashMap<Object, Object>) me2.getValue();
					Boolean b=temp.containsValue(data);
					if(b==true){
						message="Data Found";
					}
					}
				}
			}
			System.out.println("message :"+message);
			//request.setAttribute("message", message);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = arrayName+".containsValue("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=HashMapCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=HashMapCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(arrayName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	/*----------------------------LinkedListController---------------------------------------*/
	@RequestMapping("/linkedlist")
	public ModelAndView linkedListResponse(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		session.setAttribute("firstvalue", "0");
		session.setAttribute("syntex", "0");
		
		System.out.println("inside linkedlist controller linkedlist");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,LinkedListModel> tempMap=new LinkedHashMap<String,LinkedListModel>();
	    ArrayList<LinkedListModel> arrayListToSend=new ArrayList<LinkedListModel>();
		System.out.println("inside linkedlist controller linkedlist");
		//create new list and entry in map
		tempMap=LinkedListCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		LinkedListModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (LinkedListModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getLinkedListName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("linkedlist", "message", message);
	}
	@RequestMapping("/createlinkedlist")
	public ModelAndView createLinkedListResponse(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		session.setAttribute("firstvalue", "1");
		String syntex="";
		
	    Map<String, LinkedListModel> tempMap=new LinkedHashMap<String,LinkedListModel>();
	    ArrayList<LinkedListModel> arrayListToSend=new ArrayList<LinkedListModel>();
	    String message=" ";
	    String message1=" ";
	    try{
	    	String linkedListName=request.getParameter("linkedlistname");
			/*String linkedListSize=request.getParameter("linkedlistsize");
			if(linkedListSize=="")
			{
				linkedListSize="10";
			}*/
			String dataType=request.getParameter("datatype");
			
			System.out.println("LinkedList name : "+linkedListName+" Data type :"+dataType);
			System.out.println("inside controller linkedlist");
			System.out.println("returning List map :"+LinkedListCollectionLogics.arrayNameAndListMap);
			///////////////////////////////////////////////////////////////////////////////////////////////////////check if linkedlistName already exist or not
			Boolean ind=true;
			Set set=LinkedListCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(linkedListName)){
					   message = linkedListName+" allready Created.";
					    ind=false;
					    tempMap=LinkedListCollectionLogics.arrayNameAndModelMap;
						System.out.println("returning LinkedList map :"+tempMap);
						Set tempSet=tempMap.entrySet();
						Iterator tempIt=tempSet.iterator();
						//String me=null;
						LinkedListModel ar=null;
						while(tempIt.hasNext()){
							Map.Entry me2 = (Map.Entry)tempIt.next();
							ar = (LinkedListModel) me2.getValue();
							System.out.println("printing data : "+ar);
							System.out.println("printing data : "+ar.getLinkedListName());
							arrayListToSend.add(ar);
							
						}
					   
						request.setAttribute("SENDARRAY", arrayListToSend);
					   return new ModelAndView("linkedlist", "message", message);
					}
				}
			}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////create new list and entry in map
			LinkedListCollectionLogics linkedListCollectionLogics=new LinkedListCollectionLogics();
			if(linkedListName!=null)
			{
				tempMap=linkedListCollectionLogics.createLinkedList(linkedListName, dataType);
				System.out.println("returning List map :"+tempMap);
				Set tempSet=tempMap.entrySet();
				Iterator tempIt=tempSet.iterator();
				String me=null;
				LinkedListModel ar=null;
				while(tempIt.hasNext()){
					Map.Entry me2 = (Map.Entry)tempIt.next();
					ar = (LinkedListModel) me2.getValue();
					System.out.println("printing data : "+ar);
					System.out.println("printing data : "+ar.getLinkedListName());
					arrayListToSend.add(ar);
					
				}
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////fetch the array statement
				Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist1=LinkedListCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> a2=null;
				Set set12=syntexArraylist1.entrySet();
				Iterator it12=set12.iterator();
				String str12=null;
				while(it12.hasNext()){
					Map.Entry me2 = (Map.Entry)it12.next();
					str12=me2.getKey().toString();
			        
					if(str12!=null){
					if(str12.equalsIgnoreCase(linkedListName)){
						a2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						}
					}
				}
				System.out.println("a2----"+a2);
				/////////////////////////////////////////////////////
				session.setAttribute("syntex", a2);
				System.out.println("array to send :"+arrayListToSend);
				request.setAttribute("SENDARRAY", arrayListToSend);
				if(linkedListName!=null){
				   message = linkedListName+" : Created.";
				}
			}
			else
			{
				tempMap=LinkedListCollectionLogics.arrayNameAndModelMap;
				System.out.println("returning List map :"+tempMap);
				Set tempSet=tempMap.entrySet();
				Iterator tempIt=tempSet.iterator();
				//String me=null;
				LinkedListModel ar=null;
				while(tempIt.hasNext()){
					Map.Entry me2 = (Map.Entry)tempIt.next();
					ar = (LinkedListModel) me2.getValue();
					System.out.println("printing data : "+ar);
					System.out.println("printing data : "+ar.getLinkedListName());
					arrayListToSend.add(ar);
					
				}
			   
				request.setAttribute("SENDARRAY", arrayListToSend);
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	    return new ModelAndView("linkedlist", "message", message);
	}
	
	@RequestMapping("/addlinkedlist")
	public void addLinkedListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////// add value in array
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add linkedlist");
	    	String linkedListName=request.getParameter("linkedlistname");
	    		String data=request.getParameter("data");
	    	LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedListName)){
					temp=(LinkedList<Object>) me2.getValue();
					temp.add(data);
					}
				}
			}
			System.out.println("temp----"+temp);
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Add Statement in Array
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedListName+".add("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=ArrayCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedListName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			if(linkedListName!=null){
			   message = "Data Added successfully";
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////// Returning data to AJAX
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			out.print(returnvalue);
			
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/linkedlistshowonradio")
	public void linkedListshowonradioResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
			PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller show radio");
	    	String linkedListName=request.getParameter("linkedlistname");
		
			LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedListName)){
					temp=(LinkedList<Object>) me2.getValue();
				
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(linkedListName!=null){
			   message = "Data Added successfully";
			}
			
			Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist1=LinkedListCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> a2=null;
			Set set12=syntexArraylist1.entrySet();
			Iterator it12=set12.iterator();
			String str12=null;
			while(it12.hasNext()){
				Map.Entry me2 = (Map.Entry)it12.next();
				str12=me2.getKey().toString();
		        
				if(str12!=null){
				if(str12.equalsIgnoreCase(linkedListName)){
					a2=(ArrayList<ArrayListStatementModel>) me2.getValue();
					}
				}
			}
			//System.out.println("a2----"+a2.get(0).getStatement());
			session.setAttribute("syntex1", a2);
			
			
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				String str=temp.get(i).toString();
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			out.print(returnvalue);
			
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/addatindexlinkedlist")
	public void addAtIndexLinkedListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller addAtIndex LinkedList");
	    	String linkedListName=request.getParameter("linkedlistname");
			String data=request.getParameter("data");
			String index=request.getParameter("index");
			LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedListName)){
					temp=(LinkedList<Object>) me2.getValue();
					temp.add(Integer.parseInt(index), data);
					}
				}
			}
			//request.setAttribute("MODIFIEDARRAYLIST", temp);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedListName+".add("+index+","+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedListName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				String str=temp.get(i).toString();
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removelinkedlist")
	public void removeLinkedListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
			PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller remove LinkedList");
	    	String linkedListName=request.getParameter("linkedlistname");
			String data=request.getParameter("data");
			LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedListName)){
					temp=(LinkedList<Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			//request.setAttribute("MODIFIEDARRAYLIST", temp);
			
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedListName+".remove("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedListName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				String str=temp.get(i).toString();
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/removeatindexlinkedlist")
	public void removeAtIndexLinkedListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAtIndex LinkedList");
	    	String linkedListName=request.getParameter("linkedlistname");
			String index=request.getParameter("index");
			LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedListName)){
					temp=(LinkedList<Object>) me2.getValue();
					temp.remove(Integer.parseInt(index));
					}
				}
			}
			
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedListName+".remove("+index+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedListName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				String str=temp.get(i).toString();
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	@RequestMapping("/getatindexlinkedlist")
	public void getAtIndexLinkedListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    String message=" ";
	    Object returnvalue=null;
	    try{
	    	System.out.println("inside controller getAtIndex linkedlist");
	    	String linkedlistName=request.getParameter("linkedlistname");
			String index=request.getParameter("index");
			LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedlistName)){
					temp=(LinkedList<Object>) me2.getValue();
					returnvalue=temp.get(Integer.parseInt(index));
					}
				}
			}
			
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedlistName+".get("+index+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedlistName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			//String returnvalue="";
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}

	@RequestMapping("/containslinkedlist")
	public void containsMethodLinkedListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller contains linkedList");
	    	String linkedListName=request.getParameter("linkedlistname");
			String data=request.getParameter("data");
			System.out.println("linkedlistname : "+linkedListName+" data : "+data);
			LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedListName)){
					temp=(LinkedList<Object>) me2.getValue();
					Boolean b=temp.contains(data);
					if(b==true){
						message="Data Found";
					}
					}
				}
			}
			System.out.println("message :"+message);
			//request.setAttribute("message", message);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedListName+".contains("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedListName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/indexoflinkedlist")
	public void indexOfMethodLinkedListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller indexof linkedList");
	    	String linkedListName=request.getParameter("linkedlistname");
			String data=request.getParameter("data");
			System.out.println("linkedlistname : "+linkedListName+" data : "+data);
			LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			int b=0;
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedListName)){
					temp=(LinkedList<Object>) me2.getValue();
					b=temp.indexOf(data);
					}
				}
			}
			System.out.println("message :"+b);
			//request.setAttribute("message", b);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedListName+".indexOf("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedListName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(b);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/lastindexoflinkedlist")
	public void lastIndexOfMethodLinkedListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller last index of linkedList");
	    	String linkedListName=request.getParameter("linkedlistname");
			String data=request.getParameter("data");
			System.out.println("linkedlistname : "+linkedListName+" data : "+data);
			LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			int b=0;
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedListName)){
					temp=(LinkedList<Object>) me2.getValue();
					b=temp.lastIndexOf(data);
					}
				}
			}
			System.out.println("message :"+b);
			//request.setAttribute("message", b);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedListName+".lastIndexOf("+data+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedListName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(b);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	@RequestMapping("/sizeoflinkedlist")
	public void sizeOfLinkedListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    int size=0;
	    try{
	    	System.out.println("inside controller aize of linkedList");
	    	String linkedListName=request.getParameter("linkedlistname");
	    	LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedListName)){
					temp=(LinkedList<Object>) me2.getValue();
					size=temp.size();
					}
				}
			}
			request.setAttribute("message", size);
			
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedListName+".size("+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedListName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(size);
	    }catch (Exception e) {
	    	 String message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/isemptylinkedlist")
	public void isEmptyMethodLinkedListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    String message="Not Empty";
	    try{
	    	System.out.println("inside controller is empty linkedlist");
	    	String linkedListName=request.getParameter("linkedlistname");
	    	LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedListName)){
					temp=(LinkedList<Object>) me2.getValue();
					Boolean b=temp.isEmpty();
					if(b==true){
						message="Empty";
					}
					}
				}
			}
			request.setAttribute("message", message);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedListName+".isEmpty("+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedListName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
@RequestMapping("/removealllinkedlist")
	public void removeAllLinkedListResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Map<String, LinkedList> map=new LinkedHashMap<String,LinkedList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAll LinkedList");
	    	String linkedListName=request.getParameter("linkedlistname");
	    	LinkedList<Object> temp=new LinkedList<Object>();
			map=LinkedListCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(linkedListName)){
					temp=(LinkedList<Object>) me2.getValue();
					temp.removeAll(temp);
					}
				}
			}
		
			//request.setAttribute("MODIFIEDARRAYLIST", temp);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = linkedListName+".removeAll("+linkedListName+");";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);

			  Map<String,ArrayList<ArrayListStatementModel>> syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				ArrayList<ArrayListStatementModel> temp2=new ArrayList<ArrayListStatementModel>();
				syntexArraylist2=LinkedListCollectionLogics.syntexArraylist;
				Set set2=syntexArraylist2.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(linkedListName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				String str=temp.get(i).toString();
				returnvalue+="<div class='array'>"+str+"</div>";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}

	/*----------------------------QueueController---------------------------------------*/
	
	@RequestMapping("/queue")
	public ModelAndView queueResponse(HttpServletRequest request, HttpServletResponse response){
		System.out.println("inside array controller queue");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,QueueModel> tempMap=new LinkedHashMap<String,QueueModel>();
	    ArrayList<QueueModel> arrayListToSend=new ArrayList<QueueModel>();
		System.out.println("inside array controller queue");
		//create new list and entry in map
		tempMap=QueueCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		QueueModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (QueueModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getQueueName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("queue", "message", message);
	}
	@RequestMapping("/createqueue")
	public ModelAndView createQueueResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,QueueModel> tempMap=new LinkedHashMap<String,QueueModel>();
	    ArrayList<QueueModel> arrayListToSend=new ArrayList<QueueModel>();
	    String message=" ";
	    try{
	    	String queueName=request.getParameter("queuename");
			String queueSize=request.getParameter("queuesize");
			String dataType=request.getParameter("datatype");
			
			System.out.println("Queue name : "+queueName+" Queue size : "+queueSize+" Data type :"+dataType);
			System.out.println("inside controller queue");
			System.out.println("returning List map :"+QueueCollectionLogics.arrayNameAndListMap);
			//check if arrayName already exist or not
			Set set=QueueCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(queueName)){
					   message = "Queue : "+queueName+" : allready Created.";
					   return new ModelAndView("queue", "model", map);
					}
				}
			}
			//create new list and entry in map
			QueueCollectionLogics arrayCollectionLogics=new QueueCollectionLogics();
			tempMap=arrayCollectionLogics.createArrayList(queueName, dataType, queueSize);
			System.out.println("returning List map :"+tempMap);
			Set tempSet=tempMap.entrySet();
			Iterator tempIt=tempSet.iterator();
			String me=null;
			QueueModel ar=null;
			while(tempIt.hasNext()){
				Map.Entry me2 = (Map.Entry)tempIt.next();
				ar = (QueueModel) me2.getValue();
				System.out.println("printing data : "+ar);
				System.out.println("printing data : "+ar.getQueueName());
				arrayListToSend.add(ar);
			}
			System.out.println("array to send :"+arrayListToSend);
			request.setAttribute("SENDARRAY", arrayListToSend);
			if(queueName!=null){
			   message = "Queue : "+queueName+" : Created.";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	    return new ModelAndView("queue", "message", message);
	}
	
	@RequestMapping("/addqueue")
	public void addQueueResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Queue> map=new LinkedHashMap<String,Queue>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add queue");
	    	String arrayName=request.getParameter("queuename");
			String data=request.getParameter("data");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=QueueCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.add(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/addatindexqueue")
	public void addAtIndexQueueResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Queue> map=new LinkedHashMap<String,Queue>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller addAtIndex queue");
	    	String arrayName=request.getParameter("queuename");
			String data=request.getParameter("data");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=QueueCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.add(Integer.parseInt(index), data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removequeue")
	public void removeQueueResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Queue> map=new LinkedHashMap<String,Queue>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller remove queue");
	    	String arrayName=request.getParameter("queuename");
			String data=request.getParameter("data");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=QueueCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/removeatindexqueue")
	public void removeAtIndexQueueResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Queue> map=new LinkedHashMap<String,Queue>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAtIndex queue");
	    	String arrayName=request.getParameter("queuename");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=QueueCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.remove(Integer.parseInt(index));
					}
				}
			}
			
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/removeallqueue")
	public void removeAllQueueResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Queue> map=new LinkedHashMap<String,Queue>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAll queue");
	    	String arrayName=request.getParameter("queuename");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=QueueCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.removeAll(temp);
					}
				}
			}
		
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
/*----------------------------StackController---------------------------------------*/
	
	@RequestMapping("/stack")
	public ModelAndView stackResponse(HttpServletRequest request, HttpServletResponse response){
		System.out.println("inside array controller stack");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,StackModel> tempMap=new LinkedHashMap<String,StackModel>();
	    ArrayList<StackModel> arrayListToSend=new ArrayList<StackModel>();
		System.out.println("inside array controller stack");
		//create new list and entry in map
		tempMap=StackCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		StackModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (StackModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getStackName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("stack", "message", message);
	}
	@RequestMapping("/createstack")
	public ModelAndView createStackResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,StackModel> tempMap=new LinkedHashMap<String,StackModel>();
	    ArrayList<StackModel> arrayListToSend=new ArrayList<StackModel>();
	    String message=" ";
	    try{
	    	String stackName=request.getParameter("stackname");
			String dataType=request.getParameter("datatype");
			
			System.out.println("Stack name : "+stackName+" Stack size : "+" Data type :"+dataType);
			System.out.println("inside controller stack");
			System.out.println("returning List map :"+StackCollectionLogics.arrayNameAndListMap);
			//check if arrayName already exist or not
			Set set=StackCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(stackName)){
					   message = "Stack : "+stackName+" : allready Created.";
					   return new ModelAndView("stack", "model", map);
					}
				}
			}
			//create new list and entry in map
			StackCollectionLogics arrayCollectionLogics=new StackCollectionLogics();
			tempMap=arrayCollectionLogics.createArrayList(stackName, dataType);
			System.out.println("returning List map :"+tempMap);
			Set tempSet=tempMap.entrySet();
			Iterator tempIt=tempSet.iterator();
			String me=null;
			StackModel ar=null;
			while(tempIt.hasNext()){
				Map.Entry me2 = (Map.Entry)tempIt.next();
				ar = (StackModel) me2.getValue();
				System.out.println("printing data : "+ar);
				System.out.println("printing data : "+ar.getStackName());
				arrayListToSend.add(ar);
			}
			System.out.println("array to send :"+arrayListToSend);
			request.setAttribute("SENDARRAY", arrayListToSend);
			if(stackName!=null){
			   message = "Stack : "+stackName+" : Created.";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	    return new ModelAndView("stack", "message", message);
	}
	
	@RequestMapping("/addstack")
	public void addStackResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Map<String, Stack> map=new LinkedHashMap<String,Stack>();
		Map<String, ArrayList<ArrayListStatementModel>> tempSyntax=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add stack");
	    	String stackName=request.getParameter("stackname");
			String data=request.getParameter("data");
			Stack<Object> temp=new Stack<Object>();
			map=StackCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(stackName)){
					temp=(Stack<Object>) me2.getValue();
					temp.push(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(stackName!=null){
			   message = "Data Added successfully";
			}
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> temp2=null;
			tempSyntax=StackCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String message1=null;
			  modelStatement=new ArrayListStatementModel(); 
			  message1 = stackName+".push();";
			  modelStatement.setStatement(message1);
			  modelList.add(modelStatement);
			  tempSyntax.put(stackName, modelList);
			  //////////////////////////returning syntax
				Set set2=tempSyntax.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(stackName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/popstack")
	public void popStackResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Map<String, Stack> map=new LinkedHashMap<String,Stack>();
		Map<String, ArrayList<ArrayListStatementModel>> tempSyntax=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	    Object message=null;
	    try{
	    	System.out.println("inside controller pop stack");
	    	String stackName=request.getParameter("stackname");
			Stack<Object> temp=new Stack<Object>();
			map=StackCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(stackName)){
					temp=(Stack<Object>) me2.getValue();
					message=temp.pop();
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> temp2=null;
			tempSyntax=StackCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String syntax=null;
			  modelStatement=new ArrayListStatementModel(); 
			  syntax = stackName+".pop();";
			  modelStatement.setStatement(syntax);
			  modelList.add(modelStatement);
			  tempSyntax.put(stackName, modelList);
			  //////////////////////////returning syntax
				Set set2=tempSyntax.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(stackName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/addatindexstack")
	public void addAtIndexStackResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Map<String, Stack> map=new LinkedHashMap<String,Stack>();
		Map<String, ArrayList<ArrayListStatementModel>> tempSyntax=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	    Object message=null;
	    try{
	    	System.out.println("inside controller addAtIndex stack");
	    	String stackName=request.getParameter("stackname");
			String data=request.getParameter("data");
			String index=request.getParameter("index");
			Stack<Object> temp=new Stack<Object>();
			map=StackCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(stackName)){
					temp=(Stack<Object>) me2.getValue();
					temp.add(Integer.parseInt(index), data);
					}
				}
			}
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> temp2=null;
			tempSyntax=StackCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String syntax=null;
			  modelStatement=new ArrayListStatementModel(); 
			  syntax = stackName+".add("+index+","+data+");";
			  modelStatement.setStatement(syntax);
			  modelList.add(modelStatement);
			  tempSyntax.put(stackName, modelList);
			  //////////////////////////returning syntax
				Set set2=tempSyntax.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(stackName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			
			out.print(returnvalue);
			
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removestack")
	public void removeStackResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Map<String, Stack> map=new LinkedHashMap<String,Stack>();
		Map<String, ArrayList<ArrayListStatementModel>> tempSyntax=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	    Object message=null;
	    try{
	    	System.out.println("inside controller remove stack");
	    	String stackName=request.getParameter("stackname");
			String data=request.getParameter("data");
			Stack<Object> temp=new Stack<Object>();
			map=StackCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(stackName)){
					temp=(Stack<Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> temp2=null;
			tempSyntax=StackCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String syntax=null;
			  modelStatement=new ArrayListStatementModel(); 
			  syntax = stackName+".remove("+data+");";
			  modelStatement.setStatement(syntax);
			  modelList.add(modelStatement);
			  tempSyntax.put(stackName, modelList);
			  //////////////////////////returning syntax
				Set set2=tempSyntax.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(stackName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/removeatindexstack")
	public void removeAtIndexStackResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Map<String, Stack> map=new LinkedHashMap<String,Stack>();
		Map<String, ArrayList<ArrayListStatementModel>> tempSyntax=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	    Object message=null;
	    try{
	    	System.out.println("inside controller remove atindex stack");
	    	String stackName=request.getParameter("stackname");
			String data=request.getParameter("data");
			String index=request.getParameter("index");
			Stack<Object> temp=new Stack<Object>();
			map=StackCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(stackName)){
					temp=(Stack<Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> temp2=null;
			tempSyntax=StackCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String syntax=null;
			  modelStatement=new ArrayListStatementModel(); 
			  syntax = stackName+".remove("+index+","+data+");";
			  modelStatement.setStatement(syntax);
			  modelList.add(modelStatement);
			  tempSyntax.put(stackName, modelList);
			  //////////////////////////returning syntax
				Set set2=tempSyntax.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(stackName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/searchstack")
	public void searchStackResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Map<String, Stack> map=new LinkedHashMap<String,Stack>();
		Map<String, ArrayList<ArrayListStatementModel>> tempSyntax=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	    Object message=null;
	    try{
	    	System.out.println("inside controller search stack");
	    	String stackName=request.getParameter("stackname");
			String data=request.getParameter("data");
			Stack<Object> temp=new Stack<Object>();
			map=StackCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(stackName)){
					temp=(Stack<Object>) me2.getValue();
					message=temp.search(data);
					}
				}
			}
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> temp2=null;
			tempSyntax=StackCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String syntax=null;
			  modelStatement=new ArrayListStatementModel(); 
			  syntax = stackName+".search("+data+");";
			  modelStatement.setStatement(syntax);
			  modelList.add(modelStatement);
			  tempSyntax.put(stackName, modelList);
			  //////////////////////////returning syntax
				Set set2=tempSyntax.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(stackName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			/*String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}*/
			
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/indexofstack")
	public void indexOfStackResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Map<String, Stack> map=new LinkedHashMap<String,Stack>();
		Map<String, ArrayList<ArrayListStatementModel>> tempSyntax=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	    Object message=null;
	    try{
	    	System.out.println("inside controller index stack");
	    	String stackName=request.getParameter("stackname");
			String data=request.getParameter("data");
			Stack<Object> temp=new Stack<Object>();
			map=StackCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(stackName)){
					temp=(Stack<Object>) me2.getValue();
					message=temp.indexOf(data);
					}
				}
			}
			System.out.println("index of :"+message);
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> temp2=null;
			tempSyntax=StackCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String syntax=null;
			  modelStatement=new ArrayListStatementModel(); 
			  syntax = stackName+".indexOf("+data+");";
			  modelStatement.setStatement(syntax);
			  modelList.add(modelStatement);
			  tempSyntax.put(stackName, modelList);
			  //////////////////////////returning syntax
				Set set2=tempSyntax.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(stackName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			/*String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}*/
			
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/peekstack")
	public void peekStackResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Map<String, Stack> map=new LinkedHashMap<String,Stack>();
		Map<String, ArrayList<ArrayListStatementModel>> tempSyntax=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	    Object message=null;
	    try{
	    	System.out.println("inside controller peek stack");
	    	String stackName=request.getParameter("stackname");
			Stack<Object> temp=new Stack<Object>();
			map=StackCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(stackName)){
					temp=(Stack<Object>) me2.getValue();
					message=temp.peek();
					}
				}
			}
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> temp2=null;
			tempSyntax=StackCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String syntax=null;
			  modelStatement=new ArrayListStatementModel(); 
			  syntax = stackName+".peek();";
			  modelStatement.setStatement(syntax);
			  modelList.add(modelStatement);
			  tempSyntax.put(stackName, modelList);
			  //////////////////////////returning syntax
				Set set2=tempSyntax.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(stackName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			/*String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}*/
			
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/isemptystack")
	public void isEmptyStackResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Map<String, Stack> map=new LinkedHashMap<String,Stack>();
		Map<String, ArrayList<ArrayListStatementModel>> tempSyntax=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	    String message="Empty";
	    try{
	    	System.out.println("inside controller isEmpty stack");
	    	String stackName=request.getParameter("stackname");
			Stack<Object> temp=new Stack<Object>();
			map=StackCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				boolean b=true;
				if(key!=null){
				if(key.equalsIgnoreCase(stackName)){
					temp=(Stack<Object>) me2.getValue();
					b=temp.isEmpty();
					if(b==false){
						message="Not Empty";
					}
					}
				}
			}
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> temp2=null;
			tempSyntax=StackCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String syntax=null;
			  modelStatement=new ArrayListStatementModel(); 
			  syntax = stackName+".isEmpty();";
			  modelStatement.setStatement(syntax);
			  modelList.add(modelStatement);
			  tempSyntax.put(stackName, modelList);
			  //////////////////////////returning syntax
				Set set2=tempSyntax.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(stackName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			/*String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}*/
			
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	@RequestMapping("/sizeofstack")
	public void sizeOfStackResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Map<String, Stack> map=new LinkedHashMap<String,Stack>();
		Map<String, ArrayList<ArrayListStatementModel>> tempSyntax=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	    Object message=null;
	    try{
	    	System.out.println("inside controller size stack");
	    	String stackName=request.getParameter("stackname");
			Stack<Object> temp=new Stack<Object>();
			map=StackCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				boolean b=true;
				if(key!=null){
				if(key.equalsIgnoreCase(stackName)){
					temp=(Stack<Object>) me2.getValue();
					message=temp.size();
					
					}
				}
			}
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> temp2=null;
			tempSyntax=StackCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String syntax=null;
			  modelStatement=new ArrayListStatementModel(); 
			  syntax = stackName+".size();";
			  modelStatement.setStatement(syntax);
			  modelList.add(modelStatement);
			  tempSyntax.put(stackName, modelList);
			  //////////////////////////returning syntax
				Set set2=tempSyntax.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(stackName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			/*String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}*/
			
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	@RequestMapping("/removeallstack")
	public void removeAllStackResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Map<String, Stack> map=new LinkedHashMap<String,Stack>();
		Map<String, ArrayList<ArrayListStatementModel>> tempSyntax=new LinkedHashMap<String,ArrayList<ArrayListStatementModel>>();
	    Object message=null;
	    try{
	    	System.out.println("inside controller remove stack");
	    	String stackName=request.getParameter("stackname");
			Stack<Object> temp=new Stack<Object>();
			map=StackCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(stackName)){
					temp=(Stack<Object>) me2.getValue();
					temp.removeAll(temp);
					}
				}
			}
			ArrayListStatementModel modelStatement=null;
			ArrayList<ArrayListStatementModel> temp2=null;
			tempSyntax=StackCollectionLogics.syntexArraylist;
			ArrayList<ArrayListStatementModel> modelList=new ArrayList<ArrayListStatementModel>();
			String str1=null;
			String syntax=null;
			  modelStatement=new ArrayListStatementModel(); 
			  syntax = stackName+".removeAll("+");";
			  modelStatement.setStatement(syntax);
			  modelList.add(modelStatement);
			  tempSyntax.put(stackName, modelList);
			  //////////////////////////returning syntax
				Set set2=tempSyntax.entrySet();
				@SuppressWarnings("rawtypes")
				Iterator it1=set2.iterator();
				
				while(it1.hasNext()){
					Map.Entry me2 = (Map.Entry)it1.next();
					String key=(String) me2.getKey();
					if(key!=null){
					if(key.equalsIgnoreCase(stackName)){
						temp2=(ArrayList<ArrayListStatementModel>) me2.getValue();
						temp2.add(modelStatement);
						}
					}
				}
				

			System.out.println("a2----"+temp2);
			session.setAttribute("syntex", temp2);
			String returnvalue="";
			for(int i=0;i<temp.size();i++){
				//temp=(ArrayList<Object>) temp.get(i);
				String str=temp.get(i).toString();
				System.out.println("data------"+str);
				returnvalue+="<div class='array'>"+str+"</div>";
			}
			
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	/*----------------------------TreeSetController---------------------------------------*/
	@RequestMapping("/treeset")
	public ModelAndView treeSetResponse(HttpServletRequest request, HttpServletResponse response){
		System.out.println("inside controller treeset");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,TreeSetModel> tempMap=new LinkedHashMap<String,TreeSetModel>();
	    ArrayList<TreeSetModel> arrayListToSend=new ArrayList<TreeSetModel>();
	
		//create new list and entry in map
		tempMap=TreeSetCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		TreeSetModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (TreeSetModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getTreeSetName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("treeset", "message", message);
	}
	@RequestMapping("/createtreeset")
	public ModelAndView createTreeSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,TreeSetModel> tempMap=new LinkedHashMap<String,TreeSetModel>();
	    ArrayList<TreeSetModel> arrayListToSend=new ArrayList<TreeSetModel>();
	    String message=" ";
	    try{
	    	String treeSetName=request.getParameter("treesetname");
			String treeSetSize=request.getParameter("treesetsize");
			String dataType=request.getParameter("datatype");
			
			System.out.println("TreeSet name : "+treeSetName+" TreeSet size : "+treeSetSize+" Data type :"+dataType);
			System.out.println("inside controller treeset");
			System.out.println("returning List map :"+TreeSetCollectionLogics.arrayNameAndListMap);
			//check if arrayName already exist or not
			Set set=TreeSetCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(treeSetName)){
					   message = "TreeSet : "+treeSetName+" : allready Created.";
					   return new ModelAndView("treeset", "model", map);
					}
				}
			}
			//create new list and entry in map
			TreeSetCollectionLogics arrayCollectionLogics=new TreeSetCollectionLogics();
			tempMap=arrayCollectionLogics.createArrayList(treeSetName, dataType);
			System.out.println("returning List map :"+tempMap);
			Set tempSet=tempMap.entrySet();
			Iterator tempIt=tempSet.iterator();
			String me=null;
			TreeSetModel ar=null;
			while(tempIt.hasNext()){
				Map.Entry me2 = (Map.Entry)tempIt.next();
				ar = (TreeSetModel) me2.getValue();
				System.out.println("printing data : "+ar);
				System.out.println("printing data : "+ar.getTreeSetName());
				arrayListToSend.add(ar);
			}
			System.out.println("array to send :"+arrayListToSend);
			request.setAttribute("SENDARRAY", arrayListToSend);
			if(treeSetName!=null){
			   message = "TreeSet : "+treeSetName+" : Created.";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	    return new ModelAndView("treeset", "message", message);
	}
@RequestMapping("/treesetshowonradio")
	public void treeSetShowOnRadioResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add treeSetList");
	    	String treesetName=request.getParameter("treesetname");
			Set<Object> temp=new TreeSet<Object>();
			map=TreeSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(treesetName)){
					temp=(Set<Object>) me2.getValue();
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(treesetName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator<Object> itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
			
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}

@RequestMapping("/addtreeset")
	public void addTreeSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add treeset");
	    	String treesetName=request.getParameter("treesetname");
			String data=request.getParameter("data");
		    Set<Object> temp=new TreeSet<Object>();
			map=TreeSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(treesetName)){
					temp=(Set<Object>) me2.getValue();
					temp.add(data);
					}
				}
			}
			System.out.println("Temp-----:"+temp);
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(treesetName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
@RequestMapping("/sizeoftreeSet")
	public void sizeOfTreeSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    int size=0;
	    try{
	    	System.out.println("inside controller sizeOf TreeSet");
	    	String treesetName=request.getParameter("treesetname");
			Set<Object> temp=new TreeSet<Object>();
			map=TreeSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(treesetName)){
					temp=(Set<Object>) me2.getValue();
					size=temp.size();
					}
				}
			}
			request.setAttribute("message", size);
			request.setAttribute("message", size);
			out.print(size);
	    }catch (Exception e) {
	    	 String message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
@RequestMapping("/removetreeset")
	public void removeTreeSetResponse(HttpServletRequest request, HttpServletResponse response)throws IOException{
		PrintWriter out =response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller remove TreeSet");
	    	String treesetName=request.getParameter("treesetname");
			String data=request.getParameter("data");
			Set<Object> temp=new TreeSet<Object>();
			map=TreeSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(treesetName)){
					temp=(Set<Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(treesetName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/removealltreeset")
	public void removeAllTreeSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAll treeset");
	    	String treesetName=request.getParameter("treesetname");
			Set<Object> temp=new TreeSet<Object>();
			map=TreeSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(treesetName)){
					temp=(Set<Object>) me2.getValue();
					temp.removeAll(temp);
					}
				}
			}
		
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(treesetName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	@RequestMapping("/addatindextreeset")
	public void addAtIndexTreeSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller addAtIndex treeset");
	    	String arrayName=request.getParameter("treesetname");
			String data=request.getParameter("data");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=TreeSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.add(Integer.parseInt(index), data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/containstreeset")
	public void containsMethodTreeSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller contains TreeSet");
	    	String treesetName=request.getParameter("treesetname");
			String data=request.getParameter("data");
			System.out.println("TreeSetname : "+treesetName+" data : "+data);
			Set<Object> temp=new TreeSet<Object>();
			map=TreeSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(treesetName)){
					temp=(Set<Object>) me2.getValue();
					Boolean b=temp.contains(data);
					if(b==true){
						message="Data Found";
					}
					}
				}
			}
			System.out.println("message :"+message);
			request.setAttribute("message", message);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/isemptytreeset")
	public void isEmptyMethodTreeSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message="Not Empty";
	    try{
	    	System.out.println("inside controller isEmpty TreeSet");
	    	String treesetName=request.getParameter("treesetname");
			String data=request.getParameter("data");
			Set<Object> temp=new TreeSet<Object>();
			map=TreeSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(treesetName)){
					temp=(Set<Object>) me2.getValue();
					Boolean b=temp.isEmpty();
					if(b==true){
						message="Empty";
					}
					}
				}
			}
			request.setAttribute("message", message);
			request.setAttribute("message", message);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	/*----------------------------LinkedHashSetController---------------------------------------*/
	@RequestMapping("/linkedhashset")
	public ModelAndView linkedHashSetResponse(HttpServletRequest request, HttpServletResponse response){
		System.out.println("inside controller LinkedHashSet");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,LinkedHashSetModel> tempMap=new LinkedHashMap<String,LinkedHashSetModel>();
	    ArrayList<LinkedHashSetModel> arrayListToSend=new ArrayList<LinkedHashSetModel>();
		//create new list and entry in map
		tempMap=LinkedHashSetCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		LinkedHashSetModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (LinkedHashSetModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getLinkedHashSetName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("linkedhashset", "message", message);
	}
	@RequestMapping("/createlinkedhashset")
	public ModelAndView createLinkedHashSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,LinkedHashSetModel> tempMap=new LinkedHashMap<String,LinkedHashSetModel>();
	    ArrayList<LinkedHashSetModel> arrayListToSend=new ArrayList<LinkedHashSetModel>();
	    String message=" ";
	    try{
	    	String linkedHashSetName=request.getParameter("linkedhashsetname");
			String linkedHashSetSize=request.getParameter("linkedhashsetsize");
			String linkedHashSetloadfactor=request.getParameter("linkedhashsetloadfactor");
			String dataType=request.getParameter("datatype");
			
			System.out.println("linkedhashmap name : "+linkedHashSetName+" Array size : "+linkedHashSetSize+" Data type :"+dataType);
			System.out.println("inside controller hashMap");
			System.out.println("returning List map :"+LinkedHashSetCollectionLogics.arrayNameAndListMap);
			//check if arrayName already exist or not
			Set set=LinkedHashSetCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(linkedHashSetName)){
					   message = "Array : "+linkedHashSetName+" : allready Created.";
					   return new ModelAndView("linkedhashset", "model", map);
					}
				}
			}
			//create new list and entry in map
			LinkedHashSetCollectionLogics arrayCollectionLogics=new LinkedHashSetCollectionLogics();
			tempMap=arrayCollectionLogics.createArrayList(linkedHashSetName, dataType, linkedHashSetSize, linkedHashSetloadfactor);
			System.out.println("returning List map :"+tempMap);
			Set tempSet=tempMap.entrySet();
			Iterator tempIt=tempSet.iterator();
			String me=null;
			LinkedHashSetModel ar=null;
			while(tempIt.hasNext()){
				Map.Entry me2 = (Map.Entry)tempIt.next();
				ar = (LinkedHashSetModel) me2.getValue();
				System.out.println("printing data : "+ar);
				System.out.println("printing data : "+ar.getLinkedHashSetName());
				arrayListToSend.add(ar);
			}
			System.out.println("array to send :"+arrayListToSend);
			request.setAttribute("SENDARRAY", arrayListToSend);
			if(linkedHashSetName!=null){
			   message = "linkedhashset : "+linkedHashSetName+" : Created.";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	    return new ModelAndView("linkedhashset", "message", message);
	}
	@RequestMapping("/linkedhashsetshowonradio")
	public void linkedhashSetShowOnRadioResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add arraylist");
	    	String arrayName=request.getParameter("arrayname");
			Set<Object> temp=new LinkedHashSet<Object>();
			map=ArrayCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator<Object> itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
			
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/addlinkedhashset")
	public void addLinkedHashSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add linkedHashMap");
	    	String arrayName=request.getParameter("linkedhashsetname");
			String data=request.getParameter("data");
			Set<Object> temp=new LinkedHashSet<Object>();
			map=LinkedHashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					temp.add(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/sizeoflinkedhashSet")
	public void sizeOfLinkedHasSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    int size=0;
	    try{
	    	System.out.println("inside controller sizeof linkedhashset");
	    	String arrayName=request.getParameter("linkedhashsetname");
			Set<Object> temp=new LinkedHashSet<Object>();
			map=LinkedHashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					size=temp.size();
					}
				}
			}
			request.setAttribute("message", size);
			request.setAttribute("message", size);
			out.print(size);
	    }catch (Exception e) {
	    	 String message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removelinkedhashset")
	public void removelinkedHashSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out =response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller remove linkedHashMap");
	    	String arrayName=request.getParameter("linkedhashsetname");
			String data=request.getParameter("data");
			Set<Object> temp=new LinkedHashSet<Object>();
			map=LinkedHashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removealllinkedhashset")
	public void removeAlllinkedHashSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAll LinkedHashSet");
	    	String arrayName=request.getParameter("linkedhashsetname");
			Set<Object> temp=new LinkedHashSet<Object>();
			map=LinkedHashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					temp.removeAll(temp);
					}
				}
			}
		
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
			String returnvalue="";
			Iterator itr=temp.iterator();
			while(itr.hasNext()){
				String latestValue=(String) itr.next();
				returnvalue+="<div class='array'>"+latestValue+"</div>";
			}
			out.print(returnvalue);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	@RequestMapping("/containslinkedhashset")
	public void containsMethodLinkedHashSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message="Data Not Found ";
	    try{
	    	System.out.println("inside controller contains linkedhashset");
	    	String arrayName=request.getParameter("linkedhashsetname");
			String data=request.getParameter("data");
			System.out.println("linkedhashhashsetname : "+arrayName+" data : "+data);
			Set<Object> temp=new LinkedHashSet<Object>();
			map=LinkedHashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					Boolean b=temp.contains(data);
					if(b==true){
						message="Data Found";
					}
					}
				}
			}
			System.out.println("message :"+message);
			request.setAttribute("message", message);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	@RequestMapping("/isemptylinkedhashset")
	public void isEmptyMethodLinkedHashSetResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message="Not Empty";
	    try{
	    	System.out.println("inside controller removeAll HashSet");
	    	String arrayName=request.getParameter("linkedhashsetname");
			String data=request.getParameter("data");
			Set<Object> temp=new LinkedHashSet<Object>();
			map=LinkedHashSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(Set<Object>) me2.getValue();
					Boolean b=temp.isEmpty();
					if(b==true){
						message="Empty";
					}
					}
				}
			}
			request.setAttribute("message", message);
			request.setAttribute("message", message);
			out.print(message);
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	/*----------------------------TreeMapController---------------------------------------*/
	
	
	@RequestMapping("/treemap")
	public ModelAndView treeMapResponse(HttpServletRequest request, HttpServletResponse response){
		System.out.println("inside array controller treesmap");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,TreeMapModel> tempMap=new LinkedHashMap<String,TreeMapModel>();
	    ArrayList<TreeMapModel> arrayListToSend=new ArrayList<TreeMapModel>();
		System.out.println("inside array controller treeset");
		//create new list and entry in map
		tempMap=TreeMapCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		TreeMapModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (TreeMapModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getTreeMapName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("treemap", "message", message);
	}
	@RequestMapping("/createtreemap")
	public ModelAndView createTreeMapResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,TreeMapModel> tempMap=new LinkedHashMap<String,TreeMapModel>();
	    ArrayList<TreeMapModel> arrayListToSend=new ArrayList<TreeMapModel>();
	    String message=" ";
	    try{
	    	String treeMapName=request.getParameter("treemapname");
			String treeMapSize=request.getParameter("treemapsize");
			String dataType=request.getParameter("datatype");
			
			System.out.println("TreeMap name : "+treeMapName+" TreeMap size : "+treeMapSize+" Data type :"+dataType);
			System.out.println("inside controller treemap");
			System.out.println("returning List map :"+TreeMapCollectionLogics.arrayNameAndListMap);
			//check if arrayName already exist or not
			Set set=TreeMapCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(treeMapName)){
					   message = "TreeMap : "+treeMapName+" : allready Created.";
					   return new ModelAndView("treemap", "model", map);
					}
				}
			}
			//create new list and entry in map
			TreeMapCollectionLogics arrayCollectionLogics=new TreeMapCollectionLogics();
			tempMap=arrayCollectionLogics.createArrayList(treeMapName, dataType, treeMapSize);
			System.out.println("returning List map :"+tempMap);
			Set tempSet=tempMap.entrySet();
			Iterator tempIt=tempSet.iterator();
			String me=null;
			TreeMapModel ar=null;
			while(tempIt.hasNext()){
				Map.Entry me2 = (Map.Entry)tempIt.next();
				ar = (TreeMapModel) me2.getValue();
				System.out.println("printing data : "+ar);
				System.out.println("printing data : "+ar.getTreeMapName());
				arrayListToSend.add(ar);
			}
			System.out.println("array to send :"+arrayListToSend);
			request.setAttribute("SENDARRAY", arrayListToSend);
			if(treeMapName!=null){
			   message = "TreeSet : "+treeMapName+" : Created.";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	    return new ModelAndView("treemap", "message", message);
	}
	
	@RequestMapping("/addtreemap")
	public void addTreeMapResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add treeset");
	    	String arrayName=request.getParameter("treesetname");
			String data=request.getParameter("data");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=TreeMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.add(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/addatindextreemap")
	public void addAtIndexTreeMapResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller addAtIndex treeset");
	    	String arrayName=request.getParameter("treesetname");
			String data=request.getParameter("data");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=TreeMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.add(Integer.parseInt(index), data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removetreemap")
	public void removeTreeMapResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller remove treeset");
	    	String arrayName=request.getParameter("treesetname");
			String data=request.getParameter("data");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=TreeMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/removeatindextreemap")
	public void removeAtIndexTreeMapResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAtIndex treeset");
	    	String arrayName=request.getParameter("treesetname");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=TreeMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.remove(Integer.parseInt(index));
					}
				}
			}
			
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/removealltreemap")
	public void removeAllTreeMapResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Map<Object, Object>> map=new LinkedHashMap<String,Map<Object, Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAll treeset");
	    	String arrayName=request.getParameter("treesetname");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=TreeMapCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.removeAll(temp);
					}
				}
			}
		
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	/*----------------------------SortedSetController---------------------------------------*/
	
	@RequestMapping("/sortedset")
	public ModelAndView sortedSetResponse(HttpServletRequest request, HttpServletResponse response){
		System.out.println("inside array controller sortedset");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,SortedSetModel> tempMap=new LinkedHashMap<String,SortedSetModel>();
	    ArrayList<SortedSetModel> arrayListToSend=new ArrayList<SortedSetModel>();
		System.out.println("inside array controller treeset");
		//create new list and entry in map
		tempMap=SortedSetCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		SortedSetModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (SortedSetModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getSortedSetName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("sortedset", "message", message);
	}
	@RequestMapping("/createsortedset")
	public ModelAndView createSortedSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,SortedSetModel> tempMap=new LinkedHashMap<String,SortedSetModel>();
	    ArrayList<SortedSetModel> arrayListToSend=new ArrayList<SortedSetModel>();
	    String message=" ";
	    try{
	    	String sortedSetName=request.getParameter("sortedsetname");
			String sortedSetSize=request.getParameter("sortedsetsize");
			String dataType=request.getParameter("datatype");
			
			System.out.println("TreeSet name : "+sortedSetName+" TreeSet size : "+sortedSetSize+" Data type :"+dataType);
			System.out.println("inside controller treeset");
			System.out.println("returning List map :"+SortedSetCollectionLogics.arrayNameAndListMap);
			//check if arrayName already exist or not
			Set set=SortedSetCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(sortedSetName)){
					   message = "TreeSet : "+sortedSetName+" : allready Created.";
					   return new ModelAndView("sortedset", "model", map);
					}
				}
			}
			//create new list and entry in map
			SortedSetCollectionLogics arrayCollectionLogics=new SortedSetCollectionLogics();
			tempMap=arrayCollectionLogics.createArrayList(sortedSetName, dataType, sortedSetSize);
			System.out.println("returning List map :"+tempMap);
			Set tempSet=tempMap.entrySet();
			Iterator tempIt=tempSet.iterator();
			String me=null;
			SortedSetModel ar=null;
			while(tempIt.hasNext()){
				Map.Entry me2 = (Map.Entry)tempIt.next();
				ar = (SortedSetModel) me2.getValue();
				System.out.println("printing data : "+ar);
				System.out.println("printing data : "+ar.getSortedSetName());
				arrayListToSend.add(ar);
			}
			System.out.println("array to send :"+arrayListToSend);
			request.setAttribute("SENDARRAY", arrayListToSend);
			if(sortedSetName!=null){
			   message = "TreeSet : "+sortedSetName+" : Created.";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	    return new ModelAndView("sortedset", "message", message);
	}
	
	@RequestMapping("/addsortedset")
	public void addSortedSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add treeset");
	    	String arrayName=request.getParameter("treesetname");
			String data=request.getParameter("data");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=SortedSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.add(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/addatindexsortedset")
	public void addAtIndexSortedSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller addAtIndex treeset");
	    	String arrayName=request.getParameter("sortedsetname");
			String data=request.getParameter("data");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=SortedSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.add(Integer.parseInt(index), data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removesortedset")
	public void removeSortedSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller remove treeset");
	    	String arrayName=request.getParameter("treesetname");
			String data=request.getParameter("data");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=SortedSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/removeatindexsortedset")
	public void removeAtIndexSortedSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAtIndex treeset");
	    	String arrayName=request.getParameter("treesetname");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=SortedSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.remove(Integer.parseInt(index));
					}
				}
			}
			
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/removeallsortedset")
	public void removeAllSortedSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAll treeset");
	    	String arrayName=request.getParameter("treesetname");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=SortedSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.removeAll(temp);
					}
				}
			}
		
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	/*----------------------------NavigableSetController---------------------------------------*/

	@RequestMapping("/navigableset")
	public ModelAndView navigableSetResponse(HttpServletRequest request, HttpServletResponse response){
		System.out.println("inside array controller sortedset");
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,NavigableSetModel> tempMap=new LinkedHashMap<String,NavigableSetModel>();
	    ArrayList<NavigableSetModel> arrayListToSend=new ArrayList<NavigableSetModel>();
		System.out.println("inside array controller treeset");
		//create new list and entry in map
		tempMap=NavigableSetCollectionLogics.arrayNameAndModelMap;
		Set tempSet=tempMap.entrySet();
		Iterator tempIt=tempSet.iterator();
		String me=null;
		NavigableSetModel ar=null;
		while(tempIt.hasNext()){
			Map.Entry me2 = (Map.Entry)tempIt.next();
			ar = (NavigableSetModel) me2.getValue();
			System.out.println("printing data : "+ar);
			System.out.println("printing data : "+ar.getNavigableSetName());
			arrayListToSend.add(ar);
		}
		System.out.println("send data from array :"+arrayListToSend);
		request.setAttribute("SENDARRAY", arrayListToSend);

		String message = "";
		return new ModelAndView("navigableset", "message", message);
	}
	@RequestMapping("/createnavigableset")
	public ModelAndView createNavigableSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String,ArrayList> map=new LinkedHashMap<String,ArrayList>();
	    Map<String,NavigableSetModel> tempMap=new LinkedHashMap<String,NavigableSetModel>();
	    ArrayList<NavigableSetModel> arrayListToSend=new ArrayList<NavigableSetModel>();
	    String message=" ";
	    try{
	    	String navigableSetName=request.getParameter("navigablesetname");
			String navigableSetSize=request.getParameter("navigablesetsize");
			String dataType=request.getParameter("datatype");
			
			System.out.println("TreeSet name : "+navigableSetName+" TreeSet size : "+navigableSetSize+" Data type :"+dataType);
			System.out.println("inside controller treeset");
			System.out.println("returning List map :"+NavigableSetCollectionLogics.arrayNameAndListMap);
			//check if arrayName already exist or not
			Set set=NavigableSetCollectionLogics.arrayNameAndListMap.entrySet();
			Iterator it=set.iterator();
			String str=null;
			while(it.hasNext()){
				Map.Entry me = (Map.Entry)it.next();
				str=me.getKey().toString();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		        
				if(str!=null){
				if(str.equalsIgnoreCase(navigableSetName)){
					   message = "TreeSet : "+navigableSetName+" : allready Created.";
					   return new ModelAndView("sortedset", "model", map);
					}
				}
			}
			//create new list and entry in map
			NavigableSetCollectionLogics arrayCollectionLogics=new NavigableSetCollectionLogics();
			tempMap=arrayCollectionLogics.createArrayList(navigableSetName, dataType, navigableSetSize);
			System.out.println("returning List map :"+tempMap);
			Set tempSet=tempMap.entrySet();
			Iterator tempIt=tempSet.iterator();
			String me=null;
			NavigableSetModel ar=null;
			while(tempIt.hasNext()){
				Map.Entry me2 = (Map.Entry)tempIt.next();
				ar = (NavigableSetModel) me2.getValue();
				System.out.println("printing data : "+ar);
				System.out.println("printing data : "+ar.getNavigableSetName());
				arrayListToSend.add(ar);
			}
			System.out.println("array to send :"+arrayListToSend);
			request.setAttribute("SENDARRAY", arrayListToSend);
			if(navigableSetName!=null){
			   message = "TreeSet : "+navigableSetName+" : Created.";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	    return new ModelAndView("navigableset", "message", message);
	}
	
	@RequestMapping("/addnavigableset")
	public void addNavigableSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller add treeset");
	    	String arrayName=request.getParameter("treesetname");
			String data=request.getParameter("data");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=NavigableSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.add(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/addatindexnavigableset")
	public void addAtIndexNavigableSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller addAtIndex treeset");
	    	String arrayName=request.getParameter("sortedsetname");
			String data=request.getParameter("data");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=NavigableSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.add(Integer.parseInt(index), data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	
	@RequestMapping("/removenavigableset")
	public void removeNavigableSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller remove treeset");
	    	String arrayName=request.getParameter("treesetname");
			String data=request.getParameter("data");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=NavigableSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.remove(data);
					}
				}
			}
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
			e.printStackTrace();
		}
	   
	}
	@RequestMapping("/removeatindexnavigableset")
	public void removeAtIndexNavigableSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAtIndex treeset");
	    	String arrayName=request.getParameter("treesetname");
			String index=request.getParameter("index");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=NavigableSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.remove(Integer.parseInt(index));
					}
				}
			}
			
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	   
	}
	
	@RequestMapping("/removeallnavigableset")
	public void removeAllNavigableSetResponse(HttpServletRequest request, HttpServletResponse response){
		Map<String, Set<Object>> map=new LinkedHashMap<String,Set<Object>>();
	    String message=" ";
	    try{
	    	System.out.println("inside controller removeAll treeset");
	    	String arrayName=request.getParameter("treesetname");
			ArrayList<Object> temp=new ArrayList<Object>();
			map=NavigableSetCollectionLogics.arrayNameAndListMap;
			Set set=map.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator it=set.iterator();
			while(it.hasNext()){
				Map.Entry me2 = (Map.Entry)it.next();
				String key=(String) me2.getKey();
				if(key!=null){
				if(key.equalsIgnoreCase(arrayName)){
					temp=(ArrayList<Object>) me2.getValue();
					temp.removeAll(temp);
					}
				}
			}
		
			request.setAttribute("MODIFIEDARRAYLIST", temp);
			if(arrayName!=null){
			   message = "Data Added successfully";
			}
	    }catch (Exception e) {
	    	 message = "Please check data entry for "+e.getMessage()+" , "+e.getClass();
		}
	}
	   
	    /*----------------------------AboutUsController---------------------------------------*/
	    @RequestMapping("/aboutus")
		public ModelAndView aboutUsResponse(HttpServletRequest request, HttpServletResponse response){
		    return new ModelAndView("aboutus", "message", null);
		}
	    /*----------------------------ContactUsSetController---------------------------------------*/
	    @RequestMapping("/contactus")
		public ModelAndView contactUsResponse(HttpServletRequest request, HttpServletResponse response){
		    return new ModelAndView("contactus", "message", null);
		}

}
