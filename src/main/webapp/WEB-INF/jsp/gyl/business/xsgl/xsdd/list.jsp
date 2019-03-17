<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/gyl/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<!--
    jquery日期样式的导入
-->
<script type="text/javascript">
	$(document).ready(function(){
		$("body").data("url","xsddAction_showXsdd.action?query_zhib.xsddzhubid=");
		//点击主表中的某一行加载子表
		GylUtils.business.zhubTRClick();
		GylUtils.business.disPage.zhubDispageEvent();
		GylUtils.business.disPage.zhibDispageEvent();
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
	<s:form action="xsddAction_showXsdd.action">
	<tr>
    	<td id="rig_top" >
            <div   style="overflow:auto; height:190px; width:1150px;">
            <table class="content" style="font-size:12px;" align="center">
                <tr>
                    <td class="one" style="color:#00F;">单据号</td>
                    <td class="inp"><input type="text"  style="background:url(images/search.png) no-repeat 226px center #F6F9FD;" name="query_zhu.ddh"/></td>
                    <td class="one" style="width:66px;">订货日期</td>
                    <td class="inp"><input type="text" id="dhrq" name="query_zhu.dhrq"/></td>
                </tr>
                <tr>
                    <td style="color:#00F">客户名称</td>
                    <td class="inp"><input type="text"  style="background:url(images/search.png) no-repeat 226px center #F6F9FD;" name="query_zhu.khmc"/></td>
                </tr>
                <tr>
                    <td class="one" style="color:#00F">销售部门</td>
                    <td class="inp"><input type="text"  style="background:url(images/search.png) no-repeat 226px center #F6F9FD;" name="query_zhu.xsbm"/></td>
                    <td class="one" style="color:#00F">业务员</td>
                    <td class="inp"><input type="text"  style="background:url(images/search.png) no-repeat 226px center #F6F9FD;" name="query_zhu.ywy"/></td>
                </tr>
                <tr>
					<td class="one" style="color:#00F">审批人</td>
                    <td class="inp"><input type="text"  style="background:url(images/search.png) no-repeat 226px center #F6F9FD;" name="query_zhu.spr"/></td>
                </tr>
				<tr>
					<td class="one" style="color:#00F">制单人</td>
                    <td class="inp"><input type="text"  style="background:url(images/search.png) no-repeat 226px center #F6F9FD;" name="query_zhu.zdr"/></td>
					<td class="one" style="color:#00F">修改人</td>
                    <td class="inp"><input type="text"  style="background:url(images/search.png) no-repeat 226px center #F6F9FD;" name="query_zhu.xgr"/></td>
				</tr>
				<tr>
					<td class="one" style="width:66px;" name="query_zhu.xgrq">修改日期</td>
                    <td class="inp"><input type="text"/></td>
                    <td colspan="2"><input type="checkbox" name="query_zhu.kpjsbz" value="2"/>是否开票结束</td>
                    <td colspan="2"><input type="checkbox" name="query_zhu.fhjsbz" value="2"/>是否发货结束</td>
				</tr>
				<tr>
                	<td colspan="5"></td>
                    <td height="40" align="center"><input type="submit" value="查询" id="search" class="search"/></td>
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
                    <td width="100">单据号</td>
                    <td width="90">订货日期</td>
                    <td width="90">失效日期</td>
                    <td width="180">客户名称</td>
                    <td width="90">整单扣率</td>
                    <td style="color:#00F;" width="90">销售部门</td>
                    <td  width="90">业务员</td>
                    <td style="color:#00F;"  width="90">开票单位</td>
                    <td width="90">收货地区</td>
                    <td  width="180">收货地址</td>
					<td  width="180">订单状态</td>
					<td  width="180">退货标志</td>
					<td  width="180">是否出库结束</td>
					<td  width="180">是否开票结束</td>
					<td  width="180">是否发货结束</td>
					<td  width="180">整单税价合计</td>
					<td  width="180">收现款金额</td>
                </tr>
                <s:iterator value="#pageResult_zhub.rows" status="st">
                <tr field="item_zhub" id="${xsddzhubid}">
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="ddh"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="dhrq"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="sxrq"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="khmc"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="zdkl"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="sxbm"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="ywy"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="kpdw"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="shdq"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="shdz"/></td>
				   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="state"/></td>
				   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="thbz"/></td>
				   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="ckjsbz"/></td>
				   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="kpjsbz"/></td>
				   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="fhjsbz"/></td>
				   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="zdsjhj"/></td>
				   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="sxkje"/></td>
                </tr>
                </s:iterator>
                <tr>
                   <td style="border:0"></td>
                </tr>
                    </table>
                    <p style="width:1800px; height:20px;font-size:12px;">
	<span class="page" style="float:right">
		<input type="button" param="1" flag="zhub_firstPage" value="首页"/>&nbsp;<input type="button" flag="zhub_prePage" param="${pageResult_zhub.currentPage-1}" value="上一页" />&nbsp;<input type="button" param="${pageResult_zhub.currentPage+1}" last="${pageResult_zhub.totalPages}" flag="zhub_nextPage" value="下一页"/>&nbsp;<input type="button" param="${pageResult_zhub.totalPages}" flag="zhub_lastPage" value="尾页"/>&nbsp;<span>转到第<input name="query_zhub.currentPage" id="pageNo" type="text" value="${pageResult_zhub.currentPage}" size="4" style="height:16px; width:28px; border:1px solid #999999; background:#FFF; border-radius:0;" />页 </span>&nbsp;&nbsp;<input type="button" value="转" style=" padding:0 10px;"/>
	</span>&nbsp;&nbsp;共有${pageResult_zhub.totalRows}条记录，当前第 ${pageResult_zhub.currentPage}/${pageResult_zhub.totalPages}页</p>
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
					<td width="200">发货仓库</td>
					<td width="200">收货单位</td>
					<td width="150">计划发货日期</td>
					<td width="150">要求收货日期</td>
                    <td width="180">应发未出库数量</td>
                    <td width="180">累计发货数量</td>
                    <td width="180">累计出库数量</td>
                    <td width="180">累计开票数量</td>
                    <td width="180">累计应收数量</td>
                    <td width="180">是否出库关闭</td>
                    <td width="180">是否发货关闭</td>
                    <td width="180">是否开票关闭</td>
                    <td width="180">是否收款结束</td>
                    <td width="180">行状态</td>
                </tr>
                <s:iterator value="#pageResult_zhib.rows" status="st1">
               	<tr class="other" field="item_zhib" id="${xsddzhub.xsddzhubid}">
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="hh"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="spbm"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="spmc"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="xh"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="dw"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="sl"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="shulv"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="dpkl"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="wsdj"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="hsdj"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="wsje"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="hsje"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="se"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="zke"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="fhck"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="shdw"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="jhfhrq"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="yqshrq"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="yfwcksl"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="ljfhsl"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="ljcksl"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="ljkpsl"/></td>
                   <td style="border-collapse:collapse;  empty-cells:show;"><s:property value="ljyssl"/></td>
                   <s:if test="isckgb">
                   		<td style="border-collapse:collapse;  empty-cells:show;">是</td>
                   </s:if>
                   <s:else>
                   		<td style="border-collapse:collapse;  empty-cells:show;">否</td>
                   </s:else>
                   <s:if test="isfhgb">
                   		<td style="border-collapse:collapse;  empty-cells:show;">是</td>
                   </s:if>
                   <s:else>
                   		<td style="border-collapse:collapse;  empty-cells:show;">否</td>
                   </s:else>
                   <s:if test="iskpgb">
                   		<td style="border-collapse:collapse;  empty-cells:show;">是</td>
                   </s:if>
                   <s:else>
                   		<td style="border-collapse:collapse;  empty-cells:show;">否</td>
                   </s:else>
                   <s:if test="isskjs">
                   		<td style="border-collapse:collapse;  empty-cells:show;">是</td>
                   </s:if>
                   <s:else>
                   		<td style="border-collapse:collapse;  empty-cells:show;">否</td>
                   </s:else>
                   <s:if test="hstatus==2">
                   		<td style="border-collapse:collapse;  empty-cells:show;">未关闭</td>
                   </s:if>
                   <s:else>
                   		<td style="border-collapse:collapse;  empty-cells:show;">关闭</td>
                   </s:else>
                </tr>
               </s:iterator>
                <tr class="other">
                   <td style="border:0"></td>
                </tr>
              </table>
              <p style="width:1200px; height:20px;font-size:12px;">
	<span class="page" style="float:right">
		<input type="button" param="1" flag="zhib_firstPage" value="首页"/>&nbsp;<input type="button" flag="zhib_prePage" param="${pageResult_zhib.currentPage-1}" value="上一页" />&nbsp;<input type="button" flag="zhib_nextPage" param="${pageResult_zhib.currentPage+1}" last="${pageResult_zhib.totalPages}" value="下一页"/>&nbsp;<input type="button" flag="zhib_lastPage" param="${pageResult_zhib.totalPages}" value="尾页"/>&nbsp;<span>转到第<input name="query_zhib.currentPage" id="pageNo_zhib" type="text" value="${pageResult_zhib.currentPage}" size="4" style="height:16px; width:28px; border:1px solid #999999; background:#FFF; border-radius:0;" />页 </span>&nbsp;&nbsp;<input type="button" value="转" style=" padding:0 10px;"/>
	</span>&nbsp;&nbsp;共有${pageResult_zhib.totalRows}条记录，当前第 ${pageResult_zhib.currentPage}/${pageResult_zhib.totalPages}页</p>
           </div>
    	</td>
    </tr>
    </s:form>
    <tr>
    	<td id="mantain-bot">
            <div class="last"></div>
        </td>
    </tr>
</table>
</body>
</html>
