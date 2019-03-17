<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/gyl/common/common.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<!--
    jquery日期样式的导入
-->
<script type="text/javascript">
	$(document).ready(function(){
		$("#wrap").data("url","xsyddAction_showXsydd.action?query_zhib.xsyddzhubid=");
		//addEvent_Page();
		//给主表和子表的td添加click事件
		GylUtils.business.update.initEvent();
		//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
		$('#dhrq').datepick({dateFormat: 'yy-mm-dd'}); 
		$('#sxrq').datepick({dateFormat: 'yy-mm-dd'}); 
		
	});
	
	$(function(){
	$('.tab li').mouseover(function(){
		var num = $(this).index()-1;
		$(this).addClass('current').siblings().removeClass();
		$('this:eq(num)').remove('span');
	})
	})
	$(function(){
	$('li').mouseover(function(){
		$(this).addClass('current').siblings().removeClass('current');
	})
	})
	function data(){
	window.open("customer.html",100,200);
	}
</script>
</head>
<body>
<table id="wrap">
	<s:form action="xsyddAction_showXsyddByDDH.action">
	<tr>
    	<td id="rig_top" >
            <div   style="overflow:auto; height:190px; width:1150px;">
            <table class="content" style="font-size:12px;">
                <tr>
                    <td class="one" style="color:#00F;">单据号</td>
                    <td class="inp"><s:textfield name="query_zhub.ddh"/></td>
                </tr>
                <tr>
                	<td colspan="5"></td>
                    <td height="40" align="center"><input type="submit" id="search"  value="查询" class="search"/></td>
                </tr>
            </table>
           </div>
		</td>
    </tr>
	<tr>
    	<td id="rig_top" >
            <div   style=" overflow:auto; height:230px; width:1150px;">
            <table cellspacing="0" cellpadding="0" id="maintain-top" align="center"  width="2000"  style="font-size:12px;">
                <tr class="one">
                    <td width="90">订货日期</td>
                    <td width="180">客户名称</td>
                    <td style="color:#00F;" width="90">销售部门</td>
                    <td  width="90">业务员</td>
					<td  width="180">订单状态</td>
                </tr>
                <s:if test="query_zhub.ddh!=null">
	                <tr field="item_zhu" id="${query_zhub.xsyddzhubid}">
	                	<!-- 
	                		idEdit为1代表可以修改
	                		               为0代表不可以修改
	                	 -->
	                   <s:hidden name="query_zhub.xsyddzhubid"></s:hidden>
	                   <td style="border-collapse:collapse;  empty-cells:show;" item="dhrq_zhu"><s:property value="query_zhub.dhrq"/></td>
	                   <td style="border-collapse:collapse;  empty-cells:show;" item="khmc_zhu"><s:property value="query_zhub.khmc"/></td>
	                   <td style="border-collapse:collapse;  empty-cells:show;" item="sxbm_zhu"><s:property value="query_zhub.sxbm"/></td>
	                   <td style="border-collapse:collapse;  empty-cells:show;" item="ywy_zhu"><s:property value="query_zhub.ywy"/></td>
					   <td style="border-collapse:collapse;  empty-cells:show;" item="state_zhu"><s:property value="query_zhub.state"/></td>
	                </tr>
                </s:if>
                <tr>
                   <td style="border:0"></td>
                </tr>
                    </table>
           </div>
		</td>
    </tr>
    <tr>
    	<td id="right_center" >
            <div  style="height:213px; width:1150px; overflow:auto;">
            <table cellspacing="0" cellpadding="0"  id="maintain-right"  style="font-size:12px;">
                <tr class="two" align="center">
                    <td width="50">行号</td>
                    <td width="100">商品编码</td>
                    <td width="200">商品名称</td>
                    <td width="100">型号</td>
                    <td width="100">单位</td>
                    <td width="100">数量</td>
                    <td width="100">税率</td>
                    <td width="100">单品扣率</td>
					<td width="100">无税单价</td>
					<td width="100">含税单价</td>
					<td width="100">无税金额</td>
					<td width="100">含税金额</td>
					<td width="100">税额</td>
					<td width="200">折扣额</td>
					<td width="200">收货单位</td>
                </tr>
                <s:iterator value="#pageResult_zhib.rows" status="st1">
               	<tr class="other" field="item_zhib" id="${xsyddzhub.xsyddzhubid}">
                   <td style="border-collapse:collapse;  empty-cells:show;" item="hh_zhi"><s:property value="hh"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="spbm_zhi"><s:property value="spbm"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="spmc_zhi"><s:property value="spmc"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="xh_zhi"><s:property value="xh"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="dw_zhi"><s:property value="dw"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="sl_zhi"><s:property value="sl"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="shulv_zhi"><s:property value="shulv"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="dpkl_zhi"><s:property value="dpkl"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="wsdj_zhi"><s:property value="wsdj"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="hsdj_zhi"><s:property value="hsdj"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="wsje_zhi"><s:property value="wsje"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="hsje_zhi"><s:property value="hsje"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="se_zhi"><s:property value="se"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="zke_zhi"><s:property value="zke"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;" item="shdw_zhi"><s:property value="shdw"/></td>
                </tr>
               </s:iterator>
                <tr class="other">
                   <td style="border:0"></td>
                </tr>
              </table>
              <p style="width:1800px; height:20px;font-size:12px;">
	<span class="page" style="float:right">
		<input type="button" param="1" flag="zhib_sy" value="首页"/>&nbsp;<input type="button" flag="zhib_syy" param="${pageResult_zhib.currentPage-1}" value="上一页" />&nbsp;<input type="button" flag="zhib_xyy" param="${pageResult_zhib.currentPage+1}" value="下一页"/>&nbsp;<input type="button" flag="zhib_wy" param="${pageResult_zhib.totalPages}" value="尾页"/>&nbsp;<span>转到第<input name="query_zhib.currentPage" id="pageNo_zhib" type="text" value="${pageResult_zhib.currentPage}" size="4" style="height:16px; width:28px; border:1px solid #999999; background:#FFF; border-radius:0;" />页 </span>&nbsp;&nbsp;<input type="button" value="转" style=" padding:0 10px;"/>
	</span>&nbsp;&nbsp;共有${pageResult_zhib.totalRows}条记录，当前第 ${pageResult_zhib.currentPage}/${pageResult_zhib.totalPages}页</p>
           </div>
    	</td>
    </tr>
    <tr class="save">
       <td height="30" align="right" style="padding-right"><input type="button" value="保存" class="search"/></td>
     </tr>
     </s:form>
</table>
</body>
</html>
