<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/gyl/common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.12&key=74cb9dc5528e8e3fd38a684e3a28e6d9"></script>
<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/map.js"></script>
<script type="text/javascript">
	$().ready(function(){
	    /* //设置分页要跳转到的url
		$("body").data("url","departmentAction_showPageResult.action");
		//声明分页的事件
		GylUtils.basedata.initEvent();
		//声明删除的事件
	    GylUtils.basedata.deleteObj.deleteFunction({
			id:'deleteSome',
			checkboxname:'ids',
			controlCheckBox:'controlCheckbox',
			url:'departmentAction_deleteDepartments.action'
		});
		//$.deleteObjForm();
		//声明修改的事件
		GylUtils.basedata.updateObj.updateFunction({
			url:'departmentAction_updateUI.action',
			id:'did'
		}); 
		//声明只删除一个的事件
		GylUtils.basedata.deleteObj.deleteOneFunction({
			url:'departmentAction_deleteOneDepartment.action',
			id:'did'
		}); */ 
		
		
	});
</script>
</head>
<body>
	<div id="container"></div> 
</body>
</html>