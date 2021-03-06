<!-- 模仿天猫整站j2ee 教程 为how2j.cn 版权所有-->
<!-- 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关-->
<!-- 供购买者学习，请勿私自传播，否则自行承担相关法律责任-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
$(function(){
	
	$("#addForm").submit(function(){
		if(!checkEmpty("name","分类名称"))
			return false;
		if(!checkEmpty("categoryPic","分类图片"))
			return false;
		return true;
	});
});

</script>

<title>分类管理</title>


<div class="workingArea">
	<h1 class="label label-info" >分类管理</h1>
	<br>
	<br>
	
	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>图片</th>
					<th>分类名称</th>
					<th>属性管理</th>
					<th>产品管理</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${categorys}" var="c">
				
				<tr>
					<td>${c.id}</td>
					<td><img height="40px" src="img/category/${c.id}.jpg"></td>
					<td>${c.name}</td>
					 	
					<td><a href="admin_property_list?cid=${c.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>					
					<td><a href="admin_product_list?cid=${c.id}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>					
					<td><a href="admin_category_edit?id=${c.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><a deleteLink="true" href="admin_category_delete?id=${c.id}"><span class=" 	glyphicon glyphicon-trash"></span></a></td>
	                     <!-- 删除的时候弹出一个窗口，确认是否删除 -->
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div style="text-align:right">
            <a href="?start=0">首页</a>
            <c:if test="${start-count>=0}">
               <a href="?start=${start-count }">上一页</a>
            </c:if>
            <c:if test="${start-count<0}">  <!--防止越界  -->
               <a href="?start=0">上一页</a>        
            </c:if>
            <c:if test="${start+count<=last}">
              <a href="?start=${start+count }">下一页</a>
            </c:if>
            <c:if test="${start+count>last}">
              <a href="?start=${last }">下一页</a> <!-- 防止越界 -->
            </c:if>
            <a href="?start=${last }">末页</a>
       </div>
	</div>
	
	<%-- 
	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>
	--%>
	<div class="panel panel-warning addDiv">
	  <div class="panel-heading">新增分类</div>
	  <div class="panel-body">
	    	<!-- <form method="post" id="addForm" action="admin_category_add" enctype="multipart/form-data">      -->
	    	    <form method="post" id="addForm" action="admin_category_add">   
	    		<table class="addTable">
	    			<tr>
	    				<td>分类名称</td>
	    				<td><input  id="name" name="name" type="text" class="form-control"></td>
	    			</tr>
	    			<!-- 
	    			<tr>
	    				<td>分类圖片</td>
	    				<td>
	    					<input id="categoryPic" accept="image/*" type="file" name="filepath" />
	    				</td>
	    			</tr>-->
	    			<tr class="submitTR">
	    				<td colspan="2" align="center">
	    					<button type="submit" class="btn btn-success">提 交</button>
	    				</td>
	    			</tr>
	    		</table>
	    	</form>
	  </div>
	</div>
	
</div>

<%@include file="../include/admin/adminFooter.jsp"%> 