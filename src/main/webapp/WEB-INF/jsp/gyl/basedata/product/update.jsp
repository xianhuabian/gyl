<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/gyl/common/common.jsp"%>
<html>
<head>
	<title>部门信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/images/title_arrow.gif"/> 商品信息修改
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="productAction_update.action">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/css/blue/images/item_point.gif" /> 商品信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<s:hidden name="pid"></s:hidden>
                    <tr><td>商品名称</td>
                        <td><s:textfield name="spmc" cssClass="InputStyle"></s:textfield>
						</td>
                    </tr>
                    <tr><td>型号</td>
                        <td><s:textarea name="xh" cssClass="InputStyle"></s:textarea></td>
                    </tr>
                    <tr><td>商品编码</td>
                        <td><s:textfield name="spbm" cssClass="InputStyle"></s:textfield>
						</td>
                    </tr>
                    <tr><td>单位</td>
                        <td><s:textarea name="dw" cssClass="InputStyle"></s:textarea></td>
                    </tr>
                    <tr><td>税率</td>
                        <td><s:textarea name="shulv" cssClass="InputStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/images/save.png"/>
        </div>
    </s:form>
</div>
</body>
</html>
