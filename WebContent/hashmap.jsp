<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HashMap</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css" />
  <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/js/bootstrap-select.min.js"></script>
  <script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
</head>
<style type="text/css">
#bootstrapSelectForm .selectContainer .form-control-feedback {
    /* Adjust feedback icon position */
    right: -15px;
}
.labl {
    color: #ffffff;
    font-size: 16px;
    padding-bottom: 1%;
    padding-left: 32%;
    padding-top: 1%;
}
.box {
    background-color: #337ab7;
}
#actionPerform
{
	display:none;
}
.space {
    padding-bottom: 3%;
}
.full
{
	width:100%;
	background-color:#337AB7 !important;
}
.topSpace {
    padding-top: 3%;
}
.row.collapse.in {
    margin-left: 1%;
}
.actionPerformText {
    color: #13457f;
    margin-left: 18%;
}
.array {
    background-color: #e2e2e2;
    border: 1px solid #a5a5a5;
    border-radius: 3px;
    height: 26px;
    text-align: center;
    min-width: 34px !important;
}
.arrayDiv {
    display: inline-flex;
    overflow-x: auto;
    padding-top: 3%;
    width: 100%;
}
#currentStatus
{
	display:none;
}
#statement
{
	display:none;
}
#showStatement
{
	display:none;
}
.showStatemnt {
    background-color: #e2e2e2;
    height: 174px;
    overflow: auto;
}
.state {
    font-family: monospace;
    font-size: 14px;
    padding-left: 4%;
    padding-top: 3%;
    color: #595959;
}
.topgap {
    margin-top: 20px;
}
.state1 {
    color: #595959;
    font-family: monospace;
    font-size: 14px;
    line-height: 14px;
    padding-left: 4%;
}
.leftmargin
{
	width:124%;
}
.red
{
	color:#FF4401;
}
</style>

<script>
//var re = /[a-zA-Z0-9_$]{2,4};

$(document).ready(function(){
	$x="";
	$(".choose").click(function(){
		$("#currentStatus11").hide();
		$("#arrayDisplay1").hide();
		$("#showStatement1").hide();
		
		
		$("#actionPerform").show();
		$("#currentStatus").show();
		$("#showStatement").show();
		$datatype1=$(this).attr("id");
		$arrayname=$(this).attr("value");
		$x=$arrayname;
		var hashmapname=$(this).attr("value");
		$('#showStatement').html("<p class='state'>HashMap<'"+$datatype1+"'>' "+hashmapname+" = new HashMap<"+$datatype1+">();</p>");
		$('#arrrayNameShow').html(" "+hashmapname);
		$("#statement").show();
				
	var fulldata1 ='hashmapname='+$arrayname
		$.ajax({
      	  url: 'arrayshowonradio',
      	  type: 'GET',
      	  data: fulldata1,
      	  success: function(data) {
      		  //alert(data);
      		//called when successful
      		  $('#arrayDisplay').html(data);
      	  },
      	  error: function(e) {
      		//called when there is an error
      		//console.log(e.message);
      	  }
      	});
	});

	$("#createHashMap").click(function(){
		$value=$("#hashmapname").val();
		$length=$value.length;
		if($length>=15)
		{
			alert("Please put hashmap length below 15 ");
			return false;
		}
		$value1=$("#hashmapsize").val();
		/* if($value1<0)
		{
			alert("chutiy..");
			false false;
		} */
		if(isNaN($value1))
		{
			alert("not a number..");
			return false;
		}
		
	});
	
	$("#putIndexAction").click(function(){
		 //data1=key,index=value
    	 $data1 = $('#addIndexElement').val();
    	 $index = $('#addIndex').val();
    	 $arrayname1 = $x;
         var fulldata ='key='+$data1+'&hashmapname='+$arrayname1+'&value='+$index;
         $('#showStatement').append("<p class='state1'>"+$arrayname1+".put("+$data1+","+$index+");</p>");
         $.ajax({
        	  url: 'puthashmap',
        	  type: 'GET',
        	  data: fulldata,
        	  success: function(data) {
        		  //alert(data);
        		//called when successful
        		$('#addIndexElement').val("");
        		$('#addIndex').val("");
        		  $('#arrayDisplay').html(data);
        	  },
        	  error: function(e) {
        		//called when there is an error
        		//console.log(e.message);
        	  }
        	});
    });
	 $("#removeAction").click(function(){
		 //data1=key,index=value
    	 $data1 = $('#removeElement').val();
    	 $arrayname1 = $x;
         var fulldata ='key='+$data1+'&hashmapname='+$arrayname1;
         $('#showStatement').append("<p class='state1'>"+$arrayname1+".remove("+$data1+");</p>");
         $.ajax({
        	  url: 'removehashmap',
        	  type: 'GET',
        	  data: fulldata,
        	  success: function(data) {
        		  //alert(data);
        		//called when successful
        		$('#removeElement').val("");
        		  $('#arrayDisplay').html(data);
        	  },
        	  error: function(e) {
        		//called when there is an error
        		//console.log(e.message);
        	  }
        	});
    });
	 
	 $("#getkeyAction").click(function(){
    	 $data1 = $('#getkeyElement').val();
    	 //alert( $data1);
    	 $arrayname1 = $x;
         var fulldata ='key='+$data1+'&hashmapname='+$arrayname1;
         $('#showStatement').append("<p class='state1'>"+$arrayname1+".get("+$data1+");</p>");
         $.ajax({
        	  url: 'getkeyhashmap',
        	  type: 'GET',
        	  data: fulldata,
        	  success: function(data) {
        		  //alert(data);
        		//called when successful
        		$('#getkeyElement').val("");
        		  $('#getmessageDisplay').html(data);
        	  },
        	  error: function(e) {
        		//called when there is an error
        		//console.log(e.message);
        	  }
        	});
    });
	  $("#containskeyAction").click(function(){
    	 $data1 = $('#containskeyElement').val();
    	 $arrayname1 = $x;
    	 //alert($data1);
    	 //alert( $arrayname1);
         var fulldata ='key='+$data1+'&hashmapname='+$arrayname1;
         $('#showStatement').append("<p class='state1'>"+$arrayname1+".containsKey("+$data1+");</p>");
         $.ajax({
        	  url: 'containskayhashmap',
        	  type: 'GET',
        	  data: fulldata,
        	  success: function(data) {
        		  //alert(data);
        		//called when successful
        		$('#containskeyElement').val("");
        		  $('#messageDisplay').html(data);
        	  },
        	  error: function(e) {
        		//called when there is an error
        		//console.log(e.message); #indexofarraylist
        	  }
        	});
    });
     $("#containsvalueAction").click(function(){
    	 $data1 = $('#containsvalueElement').val();
    	 $arrayname1 = $x;
    	// alert($data1);
    	 //alert( $arrayname1);
         var fulldata ='key='+$data1+'&hashmapname='+$arrayname1;
         $('#showStatement').append("<p class='state1'>"+$arrayname1+".containsValue("+$data1+");</p>");
         $.ajax({
        	  url: 'containsvaluehashmap',
        	  type: 'GET',
        	  data: fulldata,
        	  success: function(data) {
        		  //alert(data);
        		//called when successful
        		$('#containsvalueElement').val("");
        		  $('#containsvaluemessageDisplay').html(data);
        	  },
        	  error: function(e) {
        		//called when there is an error
        		//console.log(e.message); #indexofarraylist
        	  }
        	});
    });
     $("#isEmptyhashmapCheckAction").click(function(){
		// alert("hi inside is empty arraylist");
		 $data1 = $('#isEmptyHashmapCheck').val();
		 //alert($data1);
	 $arrayname1 = $x;
	 alert($arrayname1);
	 var fulldata ='hashmapname='+$arrayname1;
	 $('#showStatement').append("<p class='state1'>"+$arrayname1+".isEmpty();</p>");
	 $.ajax({
	 	  url: 'isemptyhashmap',
	 	  type: 'GET',
	 	  data: fulldata,
	 	  success: function(data) {
	 		  //alert(data);
	 		//called when successful
	 		  $('#isEmptymessageDisplay').html(data);
	 	  },
	 	  error: function(e) {
	 		//called when there is an error
	 		//console.log(e.message);
	 	  }
	 	});
	 });
	  $("#hashmapsizeshowAction").click(function(){
	 $data1 = $('#hashmapsizeshow').val();
	 $arrayname1 = $x;
	 var fulldata ='hashmapname='+$arrayname1;
	 $('#showStatement').append("<p class='state1'>"+$arrayname1+".size();</p>");
	 $.ajax({
	 	  url: 'sizeofhashmap',
	 	  type: 'GET',
	 	  data: fulldata,
	 	  success: function(data) {
	 		  //alert(data);
	 		//called when successful
	 		  $('#sizemessageDisplay').html(data);
	 	  },
	 	  error: function(e) {
	 		//called when there is an error
	 		//console.log(e.message);
	 	  }
	 	});
	 });
	 
});
	

		




$('.selectpicker').selectpicker();
$(document).ready(function(){
    $("input").focus(function(){
        $(this).css("background-color", "#cccccc");
    });
    $("input").blur(function(){
        $(this).css("background-color", "#ffffff");
    });
});



/* $(document).ready(function(){
    $('#myTable').dataTable();
});
 */

 
 $.fn.pageMe = function(opts){
	    var $this = this,
	        defaults = {
	            perPage: 7,
	            showPrevNext: false,
	            hidePageNumbers: false
	        },
	        settings = $.extend(defaults, opts);
	    
	    var listElement = $this;
	    var perPage = settings.perPage; 
	    var children = listElement.children();
	    var pager = $('.pager');
	    
	    if (typeof settings.childSelector!="undefined") {
	        children = listElement.find(settings.childSelector);
	    }
	    
	    if (typeof settings.pagerSelector!="undefined") {
	        pager = $(settings.pagerSelector);
	    }
	    
	    var numItems = children.size();
	    var numPages = Math.ceil(numItems/perPage);

	    pager.data("curr",0);
	    
	    if (settings.showPrevNext){
	        $('<li><a href="#" class="prev_link">«</a></li>').appendTo(pager);
	    }
	    
	    var curr = 0;
	    while(numPages > curr && (settings.hidePageNumbers==false)){
	        $('<li><a href="#" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
	        curr++;
	    }
	    
	    if (settings.showPrevNext){
	        $('<li><a href="#" class="next_link">»</a></li>').appendTo(pager);
	    }
	    
	    pager.find('.page_link:first').addClass('active');
	    pager.find('.prev_link').hide();
	    if (numPages<=1) {
	        pager.find('.next_link').hide();
	    }
	  	pager.children().eq(1).addClass("active");
	    
	    children.hide();
	    children.slice(0, perPage).show();
	    
	    pager.find('li .page_link').click(function(){
	        var clickedPage = $(this).html().valueOf()-1;
	        goTo(clickedPage,perPage);
	        return false;
	    });
	    pager.find('li .prev_link').click(function(){
	        previous();
	        return false;
	    });
	    pager.find('li .next_link').click(function(){
	        next();
	        return false;
	    });
	    
	    function previous(){
	        var goToPage = parseInt(pager.data("curr")) - 1;
	        goTo(goToPage);
	    }
	     
	    function next(){
	        goToPage = parseInt(pager.data("curr")) + 1;
	        goTo(goToPage);
	    }
	    
	    function goTo(page){
	        var startAt = page * perPage,
	            endOn = startAt + perPage;
	        
	        children.css('display','none').slice(startAt, endOn).show();
	        
	        if (page>=1) {
	            pager.find('.prev_link').show();
	        }
	        else {
	            pager.find('.prev_link').hide();
	        }
	        
	        if (page<(numPages-1)) {
	            pager.find('.next_link').show();
	        }
	        else {
	            pager.find('.next_link').hide();
	        }
	        
	        pager.data("curr",page);
	      	pager.children().removeClass("active");
	        pager.children().eq(page+1).addClass("active");
	    
	    }
	};

	$(document).ready(function(){
	    
	  $('#myTable').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:4});
	    
	});
 
 
 
 
 


$(document).ready(function() {
    $('#bootstrapSelectForm')
        .find('[name="colors"]')
            .selectpicker()
            .change(function(e) {
                // revalidate the color when it is changed
                $('#bootstrapSelectForm').formValidation('revalidateField', 'colors');
            })
            .end()
        .find('[name="language"]')
            .selectpicker()
            .change(function(e) {
                // revalidate the language when it is changed
                $('#bootstrapSelectForm').formValidation('revalidateField', 'language');
            })
            .end()
        .formValidation({
            framework: 'bootstrap',
            excluded: ':disabled',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                colors: {
                    validators: {
                        callback: {
                            message: 'Please choose 2-4 colors you like most',
                            callback: function(value, validator, $field) {
                                // Get the selected options
                                var options = validator.getFieldElements('colors').val();
                                return (options != null && options.length >= 2 && options.length <= 4);
                            }
                        }
                    }
                },
                language: {
                    validators: {
                        notEmpty: {
                            message: 'Please select your native language.'
                        }
                    }
                }
            }
        });
});
</script>
<script src="jquery-1.11.3.min"></script>

<body>
<%  
String firstvalue=(String)session.getAttribute("firstvalue");
%>
<div class="container">
<div style="text-align:center" class="row">
<div class="col-lg-4 col-md-4 col-sm-3">
<div>
<img src="images/logo.jpg" style="width:304px;height:100px;" alt="MY NAME" />
</div>
	
</div>
<div class="col-lg-8 col-md-8 col-sm-9">
		<h2>
			Welcome to HashMap<br>
		</h2>
</div>
</div>
<div class="row">
  <ul class="nav nav-tabs">
    <li class="dropdown"><a href="index.jsp">Home</a></li>
    <li class="dropdown">
      <a class="dropdown-toggle active" data-toggle="dropdown" href="#">List <span class="caret"></span></a>
      <ul class="dropdown-menu">
        <li><a href="arraylist">ArrayList</a></li>
        <li><a href="linkedlist">LinkedList</a></li>                        
      </ul>
    </li>
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">Set <span class="caret"></span></a>
      <ul class="dropdown-menu">
        <li><a href="hashset">HashSet</a></li>
        <li><a href="linkedhashset">LinkedHashSet</a></li>  
        <li><a href="sortedset">SortedSet</a></li>  
        <li><a href="navigableset">NavigableSet</a></li>  
        <li><a href="treeset">TreeSet</a></li>                        
      </ul>
    </li>
    <li class="dropdown active">
      <a class="dropdown-toggle active" data-toggle="dropdown" href="#">Map <span class="caret"></span></a>
      <ul class="dropdown-menu">
        <li><a href="">HashMap</a></li>
        <li><a href="linkedhashmap">LinkedHashMap</a></li>
        <li><a href="treemap">TreeMap</a></li>                        
      </ul>
    </li>
    <li><a href="stack">Stack</a></li>
    <li><a href="queue">Queue</a></li>
    <li><a href="aboutus">About Us</a></li>
    <li><a href="contactus">Contact Us</a></li>
  </ul>
 </div>
<br>
<div class="row">
<div class="col-md-1 col-lg-1 col-sm-12">
</div>
<div class="col-md-3 col-lg-3 col-sm-12">
		<div class="row topgap">
			<form action="createhashmap" role="form" id="bootstrapSelectForm" method="Post" class="form-horizontal">
			  <div class="form-group">
			    <label for="hashmapname">HashMap Name</label>
			    <input type="text" name="hashmapname" class="form-control required" id="hashmapname" placeholder="Enter HashMap Name" required>
			  </div>
			  <div class="form-group">
			    <label for="hashmapsize">HashMap Size</label>
			    <input type="text" name="hashmapize" class="form-control" placeholder="HashMap size initialy size 10" value="" id="hashmapsize">
			  </div>
			  <div class="form-group">
			    <label for="hashmaploadfactor">Load Factor(LF)</label>
			    <input type="text" name="hashmaploadfactor" class="form-control" id="hashmaploadfactor">
			  </div>
			  <div class="form-group">
		        <label for="keydatatype">Key DataType(KT)</label>
		        <div class="selectContainer">
		            <select name="keydatatype" class="form-control" id="keydatatype">
		                <option value="0">Select Key Datatype</option>
		                <option value="string">String</option>
		                <option value="integer">Integer</option>
		                <option value="double">Double</option>
		                <option value="float">Float</option>
		                <option value="load">LoadFactor</option>
		             </select>
		        </div>
		      </div>
		      <div class="form-group">
		        <label for="valuedatatype">Value DataType(VT)</label>
		        <div class="selectContainer">
		            <select name="valuedatatype" class="form-control" id="valuedatatype">
		                <option value="0">Select Value Datatype</option>
		                <option value="string">String</option>
		                <option value="integer">Integer</option>
		                <option value="double">Double</option>
		                <option value="float">Float</option>
		                <option value="load">LoadFactor</option>
		             </select>
		        </div>
		      </div>		  
			  <br/>
			  <div class="form-group">
		        <div>
		            <button type="submit" class="btn btn-default check" id="createHashMap">Create HashMap</button>
		            <button class="btn btn-default" type="reset">Reset</button>
		        </div>
		    </div>
		  </form>
		</div>
<p class="state red">${message} </p>
		<div class="row">
	<div class="row">
      <div class="table-responsive">
       <div class="box tableheading"><p class="labl">Created HashMap</p></div>
        <table class="table table-hover tableheader">
          <thead>
            <tr>
              <th>Name</th>
              <th>Size</th>
              <th>LF</th>
              <th>KT</th>
              <th>VT</th>
              <th>Select</th>
              <th>Delete</th>
            </tr>
          </thead>
 <% int a=0; %>
          <tbody id="myTable">
           <c:forEach items="${SENDARRAY}" var="item">
            <% if(a==0){
            	a=1;%>
            
            	
            
            <%} %>
	            <tr>
	              <td><c:out value="${item.hashMapName}"></c:out></td>
	              <td><c:out value="${item.hashMapSize}"></c:out></td>
	              <td><c:out value="${item.hashmaploadfactor}"></c:out></td>
	              <td><c:out value="${item.hashMapKeyDataType}"></c:out></td>
	              <td><c:out value="${item.hashMapValueDataType}"></c:out></td>
	              <td><input type="radio" class="choose" name="samename" value="<c:out value="${item.hashMapName}"></c:out>" id="<c:out value="${item.hashMapKeyDataType}"></c:out>"></input><input type="hidden" id="hashmap1" value="English"></td>
	              <td><input type="checkbox" name="<c:out value="${item.hashMapName}"></c:out>" id="<c:out value="${item.hashMapName}"></c:out>" value="<c:out value="${item.hashMapKeyDataType}"></c:out>"></input></td>
	            </tr>
            </c:forEach>
           </tbody>
        </table>   
      </div>
      <div class="col-md-12 text-center">
      <ul class="pagination pagination-lg pager" id="myPager"></ul>
      </div>
	</div>  
	</div> 
</div>
<div class="col-md-4 col-lg-4 col-sm-13">
	<div class="row">
		<div class="col-md-2 col-lg-2 col-sm-2"></div>
		<div class="col-md-10 col-lg-10 col-sm-10">
			<div class="" id="actionPerform">
			  <div class="actionPerformDiv"><p class="actionPerformText"><b>Action perform on <span id="arrrayNameShow"></span></b> </p></div>
			  <div class="row space">
			  <button type="button" class="btn btn-info full" data-toggle="collapse" data-target="#addAtIndex">Put Element</button>
			   <div class="topSpace"></div>
			   <div id="addAtIndex" class="collapse">
			       <div class="row">
				      <div class="col-md-2 col-lg-2 col-sm-2">
						  <label for="addIndexElement">Key</label>
				     </div>
				     <div class="col-md-7 col-lg-7 col-sm-7">
						  <input class="form-control" id="addIndexElement" type="text">
					 </div>
				   </div>
				   <div class="topSpace"></div>
					<div class="row">
						  <div class="col-md-2 col-lg-2 col-sm-2">
							  <label for="addIndex">Value</label>
					     </div>
					     <div class="col-md-7 col-lg-7 col-sm-7">
							  <input class="form-control" id="addIndex" type="text">
					     </div>
					     <div class="col-md-3 col-lg-3 col-sm-3">
							  <button type="button" class="btn btn-default" id="putIndexAction">Put</button>
					     </div>
					       <!-------------------------------------------------------------------------------------------------------- popup id ---------------------------------------------------->
					      <div class="top10 col-md-4 col-lg-4 col-sm-4">
						    </div>
						    <div class="top10 col-md-3 col-lg-3 col-sm-3">
							   <button type="button" id="rdoShowPopImg1" name="Show Image" Value="Show Image" Value="Show Image" class="btn btn-default" data-toggle="modal" data-target="#hashMapPutInfo">More Info</button>
						    </div>
					</div>
			    </div>
			  </div>
			  <div class="row space">
				  <button type="button" class="btn btn-info full" data-toggle="collapse" data-target="#remove">Remove</button>
				  <div class="topSpace"></div>
				  <div id="remove" class="collapse">
				      <div class="row">
						    <div class="col-md-2 col-lg-2 col-sm-2">
								  <label for="removeElement">Key</label>
							</div>
							<div class="col-md-7 col-lg-7 col-sm-7">
								  <input class="form-control" id="removeElement" type="text">
						    </div>
						    <div class="col-md-3 col-lg-3 col-sm-3">
								  <button type="button" class="btn btn-default" id="removeAction">remove</button>
						    </div>
						     <!-------------------------------------------------------------------------------------------------------- popup id ---------------------------------------------------->
					      <div class="top10 col-md-4 col-lg-4 col-sm-4">
						    </div>
						    <div class="top10 col-md-3 col-lg-3 col-sm-3">
							   <button type="button" id="rdoShowPopImg1" name="Show Image" Value="Show Image" Value="Show Image" class="btn btn-default" data-toggle="modal" data-target="#hashMapRemoveInfo">More Info</button>
						    </div>
				      </div>
				  </div>
			  </div>
			   <div class="row space">
			  <button type="button" class="btn btn-info full" data-toggle="collapse" data-target="#getkey">Get</button>
			  <div class="topSpace"></div>
				  <div id="getkey" class="collapse">
				   <div class="topSpace"></div>
					<div class="row">
						  <div class="col-md-2 col-lg-2 col-sm-2">
							  <label for="getkey">Key</label>
					     </div>
					     <div class="col-md-7 col-lg-7 col-sm-7">
							  <input class="form-control" id="getkeyElement" type="text">
					     </div>
					     <div class="col-md-3 col-lg-3 col-sm-3">
							  <button type="button" class="btn btn-default" id="getkeyAction">get</button>
					     </div>
					     <div class="getmessageDiv" id="getmessageDisplay">
			    </div>
			     <!-------------------------------------------------------------------------------------------------------- popup id ---------------------------------------------------->
					      <div class="top10 col-md-4 col-lg-4 col-sm-4">
						    </div>
						    <div class="top10 col-md-3 col-lg-3 col-sm-3">
							   <button type="button" id="rdoShowPopImg1" name="Show Image" Value="Show Image" Value="Show Image" class="btn btn-default" data-toggle="modal" data-target="#hashMapGetInfo">More Info</button>
						    </div>
					</div>
				  </div>
			  </div>
			  <div class="row space">
			  <button type="button" class="btn btn-info full" data-toggle="collapse" data-target="#containskey">Contains Key</button>
			   <div class="topSpace"></div>
			   <div id="containskey" class="collapse">
			       <div class="row">
				      <div class="col-md-2 col-lg-2 col-sm-2">
						  <label for="containskey">Key</label>
				     </div>
				     <div class="col-md-7 col-lg-7 col-sm-7">
						  <input class="form-control" id="containskeyElement" type="text">
					 </div>
					 <div class="col-md-3 col-lg-3 col-sm-3">
							  <button type="button" class="btn btn-default" id="containskeyAction">Check</button>
					     </div>
					      <div class="messageDiv" id="messageDisplay">
			    </div>
			     <!-------------------------------------------------------------------------------------------------------- popup id ---------------------------------------------------->
					      <div class="top10 col-md-4 col-lg-4 col-sm-4">
						    </div>
						    <div class="top10 col-md-3 col-lg-3 col-sm-3">
							   <button type="button" id="rdoShowPopImg1" name="Show Image" Value="Show Image" Value="Show Image" class="btn btn-default" data-toggle="modal" data-target="#hashMapContainsKeyInfo">More Info</button>
						    </div>
				   </div>
			    </div>
			   
			  </div>
			  
			   <div class="row space">
			  <button type="button" class="btn btn-info full" data-toggle="collapse" data-target="#containsvalue">Contains Value</button>
			   <div class="topSpace"></div>
			   <div id="containsvalue" class="collapse">
			       <div class="row">
				      <div class="col-md-2 col-lg-2 col-sm-2">
						  <label for="containsvalue">Value</label>
				     </div>
				     <div class="col-md-7 col-lg-7 col-sm-7">
						  <input class="form-control" id="containsvalueElement" type="text">
					 </div>
					 <div class="col-md-3 col-lg-3 col-sm-3">
							  <button type="button" class="btn btn-default" id="containsvalueAction">Check</button>
					     </div>
					      <div class="messageDiv" id="containsvaluemessageDisplay">
			    </div>
			     <!-------------------------------------------------------------------------------------------------------- popup id ---------------------------------------------------->
					      <div class="top10 col-md-4 col-lg-4 col-sm-4">
						    </div>
						    <div class="top10 col-md-3 col-lg-3 col-sm-3">
							   <button type="button" id="rdoShowPopImg1" name="Show Image" Value="Show Image" Value="Show Image" class="btn btn-default" data-toggle="modal" data-target="#hashMapContainsValueInfo">More Info</button>
						    </div>
				   </div>
			    </div>
			   
			  </div>
			  <div class="row space">
			  <button type="button" class="btn btn-info full" data-toggle="collapse" data-target="#isEmptyhashmapCheck">Is Empty</button>
			  <div class="topSpace"></div>
				  <div id="isEmptyhashmapCheck" class="collapse">
				   <div class="topSpace"></div>
					<div class="row">
					     <div class="col-md-3 col-lg-3 col-sm-3">
							  <button type="button" class="btn btn-default" id="isEmptyhashmapCheckAction">Check</button>
					     </div>
					      <div class="isEmptymessageDiv" id="isEmptymessageDisplay">
			              </div>
			               <!-------------------------------------------------------------------------------------------------------- popup id ---------------------------------------------------->
					      <div class="top10 col-md-4 col-lg-4 col-sm-4">
						    </div>
						    <div class="top10 col-md-3 col-lg-3 col-sm-3">
							   <button type="button" id="rdoShowPopImg1" name="Show Image" Value="Show Image" Value="Show Image" class="btn btn-default" data-toggle="modal" data-target="#hashMapIsEmptyInfo">More Info</button>
						    </div>
					</div>
				  </div>
				  
			  </div>
			  
			  <div class="row space">
			  <button type="button" class="btn btn-info full" data-toggle="collapse" data-target="#hashmapsizeshow">Size</button>
			  <div class="topSpace"></div>
				  <div id="hashmapsizeshow" class="collapse">
				   <div class="topSpace"></div>
					<div class="row">
					     <div class="col-md-3 col-lg-3 col-sm-3">
							  <button type="button" class="btn btn-default" id="hashmapsizeshowAction">Check</button>
					     </div>
					      <div class="sizeEmptymessageDiv" id="sizemessageDisplay">
			              </div>
			               <!-------------------------------------------------------------------------------------------------------- popup id ---------------------------------------------------->
					      <div class="top10 col-md-4 col-lg-4 col-sm-4">
						    </div>
						    <div class="top10 col-md-3 col-lg-3 col-sm-3">
							   <button type="button" id="rdoShowPopImg1" name="Show Image" Value="Show Image" Value="Show Image" class="btn btn-default" data-toggle="modal" data-target="#hashMapSizeInfo">More Info</button>
						    </div>
					</div>
				  </div>
				  
			  </div>
		
			</div>
		</div>
	</div>
</div>
<div class="col-md-4 col-lg-4 col-sm-10">
	<div class="row leftmargin"><!-- ----------------------------------------------- leftmargin ------------------------------------------------------------ -->
		<div class="col-md-1 col-lg-1 col-sm-1"></div>
		<div class="col-md-10 col-lg-10 col-sm-10">
		<% if(firstvalue=="1"){ %>
			<div class="" id="currentStatus11">
				<div class="actionPerformDiv">
					<p class="actionPerformText"><b>Current Status of Selected HashMap<span id="arrrayNameShow"></span></b></p>
				</div>
				
				<div class="arrayDiv" id="arrayDisplay1">
					Empty HashMap
				</div>
			</div>
			<%} %>
			<div class="" id="currentStatus">
				<div class="actionPerformDiv">
					<p class="actionPerformText"><b>Current Status of Selected HashMap<span id="arrayNameShow"></span></b></p>
				</div>
				<div class="arrayDiv" id="arrayDisplay">
				<%-- <c:forEach items="${SENDARRAY}" var="item">
	            <tr>
	              <td><c:out value="${item.hashMapName}"></c:out></td>
	              <td><c:out value="${item.hashMapSize}"></c:out></td>
	              <td><c:out value="${item.hashMapDataType}"></c:out></td>
	              <td><input type="radio" class="choose" name="samename" value="<c:out value="${item.hashMapName}"></c:out>" id="<c:out value="${item.hashMapDataType}"></c:out>"></input><input type="hidden" id="array1" value="English"></td>
	              <td><input type="checkbox" name="<c:out value="${item.hashMapName}"></c:out>" id="<c:out value="${item.hashMapName}"></c:out>" value="<c:out value="${item.hashMapDataType}"></c:out>"></input></td>
	            </tr>
            </c:forEach> --%>
			    </div>
			</div>
		 </div>
		 <div class="col-md-1 col-lg-1 col-sm-1"></div>
	</div>
	<div class="row leftmargin"><!-- ------------------------------------------------- leftmargin ---------------------------------------------------------- -->
		<div class="col-md-1 col-lg-1 col-sm-1"></div>
		<div class="col-md-10 col-lg-10 col-sm-10">
			 <% if(firstvalue=="1"){ %> 
			<div class="" id="statement1">
				<div class="actionPerformDiv">
					<p class="actionPerformText"><b>Current Statement<span id="arrrayNameShow"></span></b></p>
				</div>
				
				<div id="showStatement1" class="showStatemnt">
					<c:forEach items="${syntex}" var="item">
		            <tr>
		              <td><c:out value="${item.statement}"></c:out></td>
		            </tr>
	            	</c:forEach>
				</div>
				
				<div id="showStatement" class="showStatemnt">
					<c:forEach items="${syntex}" var="item">
		            <tr>
		              <td><c:out value="${item.statement}"></c:out></td>
		            </tr>
	            	</c:forEach>
				</div>
			</div>
			<% } %> 
			 <div class="" id="statement">
				<div class="actionPerformDiv">
					<p class="actionPerformText"><b>Current Statement<span id="arrrayNameShow"></span></b></p>
				</div>
				
				<div id="showStatement2" class="showStatemnt">
				</div>
			</div>
		</div>
		<div class="col-md-1 col-lg-1 col-sm-1"></div>
	</div>
</div>
</div>
</div>
<div class="modal fade" id="hashMapPutInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="margin-top: 200px;" >
<div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        
      </div>
      <div class="modal-body">
        
        Happy Diwali hashMapPutInfo !!	
 
      <!--  <img alt="logo" src="image/logo.jpg" width="100%" height="20%"/>-->
   
     
    </div>
  </div>
</div>

</div>

<div class="modal fade" id="hashMapRemoveInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="margin-top: 200px;" >
<div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        
      </div>
      <div class="modal-body">
        
        Happy Diwali hashMapRemoveInfo !!	
 
      <!--  <img alt="logo" src="image/logo.jpg" width="100%" height="20%"/>-->
   
     
    </div>
  </div>
</div>

</div>

<div class="modal fade" id="hashMapGetInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="margin-top: 200px;" >
<div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        
      </div>
      <div class="modal-body">
        
        Happy Diwali hashMapRemoveInfo !!	
 
      <!--  <img alt="logo" src="image/logo.jpg" width="100%" height="20%"/>-->
   
     
    </div>
  </div>
</div>

</div>



<div class="modal fade" id="hashMapContainsKeyInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="margin-top: 200px;" >
<div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        
      </div>
      <div class="modal-body">
        
        Happy Diwali hashMapContainsKeyInfo !!	
 
      <!--  <img alt="logo" src="image/logo.jpg" width="100%" height="20%"/>-->
   
     
    </div>
  </div>
</div>

</div>

<div class="modal fade" id="hashMapContainsValueInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="margin-top: 200px;" >
<div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        
      </div>
      <div class="modal-body">
        
        Happy Diwali hashMapContainsValueInfo !!	
 
      <!--  <img alt="logo" src="image/logo.jpg" width="100%" height="20%"/>-->
   
     
    </div>
  </div>
</div>

</div>

<div class="modal fade" id="hashMapIsEmptyInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="margin-top: 200px;" >
<div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        
      </div>
      <div class="modal-body">
        
        Happy Diwali hashMapIsEmptyInfo !!	
 
      <!--  <img alt="logo" src="image/logo.jpg" width="100%" height="20%"/>-->
   
     
    </div>
  </div>
</div>

</div>
<div class="modal fade" id="hashMapSizeInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="margin-top: 200px;" >
<div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        
      </div>
      <div class="modal-body">
        
        Happy Diwali hashMapSizeInfo !!	
 
      <!--  <img alt="logo" src="image/logo.jpg" width="100%" height="20%"/>-->
   
     
    </div>
  </div>
</div>

</div>
</body>
</html>