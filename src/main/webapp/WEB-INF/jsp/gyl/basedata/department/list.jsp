<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/gyl/common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript">
	$().ready(function(){
	    //设置分页要跳转到的url
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
		}); 
	});
</script>
</head>
<body>
<table id="userwrap">
	<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/css/blue/images/item_point.gif" /> 部门列表 </div> 
     </div>
	<s:form action="departmentAction_showPageResult.action">
	<tr>
    	<td id="rig_top" >
            <div   style="overflow:auto; height:90px; width:1150px;">
            <table class="content" style="font-size:12px;">
                <tr>
                    <td class="one" style="width:66px;">部门名称</td>
                    <td class="inp"><s:textfield name="baseQuery.name"/></td>
                </tr>
				<tr>
                	<td colspan="5"></td>
                    <td height="40" align="center"><input type="submit" value="查询" class="search"/></td>
                </tr>
            </table>
           </div>
		</td>
    </tr>
    </s:form>	
    <s:form action="departmentAction_deleteDepartments.action">
	<tr class="user">
        <td class="right">
        	<table cellspacing="0" cellpadding="0" id="maintain-top" align="center"  width="900" style=" overflow:auto; height:40px;">
                <tr class="one" height="20">
                	<!--  
                	<td class="btn" align="left"><input type="button" value="删除" id="deleteSome"/></td>
                	-->
                	
                	<td class="btn" align="left"><input type="submit" value="删除" id="deleteSome"/></td>
                	
                </tr>
                <tr class="one" height="20">
                	<td><input type="checkbox" id="controlCheckbox"/></td>
                    <td width="300">部门名称</td>
                    <td width="300">描述</td>
					<td width="300">操作</td>
                </tr>
               	<s:iterator value="#departments.rows">
	                <tr align="center">
	                   <td><input type="checkbox" name="ids" value="${did}"/></td>
	                   <!--  
	                   <s:hidden name="did"></s:hidden>
	                   -->
	                   <td><s:property value="name"/></td>
	                   <td><s:property value="description"/></td>
					   <td class="btn" align="center">
					   	<input type="button" value="修改"/>
					    <input type="button" value="删除" id="deleteOne"/>
					   </td>
	                </tr>
              	</s:iterator>
             </table>
            <p style="width:900px; height:20px;font-size:12px;">
	<span class="page" style="float:right">
		<input type="button" param="1" flag="firstPage" value="首页"/>&nbsp;<input type="button" flag="prePage" param="${departments.currentPage-1}" value="上一页" />&nbsp;<input type="button" param="${departments.currentPage+1}" flag="nextPage" last="${departments.totalPages}" value="下一页"/>&nbsp;<input type="button" param="${departments.totalPages}" flag="lastPage" value="尾页"/>&nbsp;<span>转到第<input name="basedataQuery.currentPage" id="pageNo" type="text" value="${basedataQuery.currentPage}" size="4" style="height:16px; width:28px; border:1px solid #999999; background:#FFF; border-radius:0;" />页 </span>&nbsp;&nbsp;<input type="button" value="转" style=" padding:0 10px;"/>
	</span>&nbsp;&nbsp;共有${departments.totalRows}条记录，当前第 ${departments.currentPage}/${departments.totalPages}页</p>
        </td>
    </tr>
</table>
</s:form>
<div>
    <a href="departmentAction_addUI.action">
    	<img src="${pageContext.request.contextPath}/images/createNew.png" />
    </a>
</div>
</body>
</html>