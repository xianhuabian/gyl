<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/gyl/common/common.jsp"%>
<html>
<head>
	<title>用户信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/images/title_arrow.gif"/> 部门信息修改
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="departmentAction_update.action">
    	<s:hidden name="uid"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/css/blue/images/item_point.gif" /> 部门信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                <s:hidden name="did"></s:hidden>
                    <tr><td>部门名称</td>
                        <td><s:textfield name="name" cssClass="InputStyle"></s:textfield>
							（登录名要唯一）
						</td>
                    </tr>
					<tr><td>部门描述</td>
                        <td><s:textfield name="description" cssClass="InputStyle"></s:textfield></td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/css/blue/images/item_point.gif" /> 部门信息修改 </div> 
        </div>
        
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
</body>
</html>
