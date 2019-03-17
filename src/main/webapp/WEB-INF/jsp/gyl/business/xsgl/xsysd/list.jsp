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
	$("body").ready(function() {
		$("body").data("url","xsysdAction_showXsysd.action?query_zhib.xsysdzhubid=");
		//点击主表中的某一行加载子表
		GylUtils.business.zhubTRClick();
		GylUtils.business.disPage.zhubDispageEvent();
		GylUtils.business.disPage.zhibDispageEvent();
		//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
		$('#dhrq').datepick({dateFormat: 'yy-mm-dd'}); 
		$('#sxrq').datepick({dateFormat: 'yy-mm-dd'}); 
	});
	/* $(function() {
		$('.tab li').mouseover(function() {
			var num = $(this).index() - 1;
			$(this).addClass('current').siblings().removeClass();
			$('this:eq(num)').remove('span');
		})
	})
	$(function() {
		$('li').mouseover(function() {
			$(this).addClass('current').siblings().removeClass('current');
		})
	})
	function data() {
		window.open("customer.html", 100, 200);
	} */
</script>
</head>
<body>
	<table id="wrap">
		<s:form action="xsysdAction_showXsysd.action">
			<tr>
				<td id="rig_top">
					<div style="overflow: auto; height: 190px; width: 1150px;">
						<table class="content" style="font-size: 12px;">
							<!-- <tr>
								<td class="one" style="color: #00F;">公司</td>
								<td class="inp"><input type="text" name="query_zhub.gs" /></td>
							</tr> -->
							<tr>
								<td style="color: #00F">单据日期</td>
								<td class="inp"><input type="text" name="query_zhub.djrq" /></td>
								<td style="color: #00F">仓库</td>
								<td class="inp"><input type="text"
									style="text-align: right" name="query_zhub.ck" /></td>
								<td style="color: #00F">库管员</td>
								<td class="inp"><input type="text"
									style="text-align: right" name="query_zhub.kgy" /></td>
							</tr>
							<tr>
								<td class="one" style="color: #00F">部门</td>
								<td class="inp"><input type="text" name="query_zhub.bm" /></td>
								<td class="one" style="color: #00F">客户</td>
								<td class="inp"><input type="text" name="query_zhub.kh" /></td>
							</tr>
							<tr>
								<td style="color: #00F">单据状态</td>
								<td class="inp"><input type="text"
									name="query_zhub.status" /></td>
								<td class="one" style="color: #00F">审批人</td>
								<td class="inp"><input type="text" name="query_zhub.shr" /></td>
								<td class="one" style="width: 66px;">审批日期</td>
								<td class="inp"><input type="text" id='dhrq'
									name="query_zhub.shrq" /></td>
							</tr>
							<tr>
								<td class="one" style="color: #00F">制单人</td>
								<td class="inp"><input type="text" name="query_zhub.zdr" /></td>
								<td class="one" style="width: 66px;">制单日期</td>
								<td class="inp"><input type="text" name="query_zhub.zdrq" /></td>
								<td class="one" style="color: #00F">修改人</td>
								<td class="inp"><input type="text"
									style="background: url(images/search.png) no-repeat 226px center #F6F9FD;"
									onclick="data()" /></td>
							</tr>
							<tr>
								<td class="one" style="width: 66px;">修改日期</td>
								<td class="inp"><input type="text" /></td>
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
				<td id="rig_top">
					<div style="overflow: auto; height: 230px; width: 1150px;">
						<table cellspacing="0" cellpadding="0" id="maintain-top"
							align="center" width="2000" style="font-size: 12px;">
							<tr class="one">
								<td width="100">单据号</td>
								<td width="90">公司</td>
								<td width="90">单据日期</td>
								<td width="180">仓库</td>
								<td width="90">库管员</td>
								<td style="color: #00F;" width="90">部门</td>
								<td width="90">客户</td>
								<td style="color: #00F;" width="90">是否退货</td>
								<td width="90">单据状态</td>
							</tr>
							<s:iterator value="#pageResult_zhub.rows" status="st">
								<tr field="item_zhub" id="${xsysdzhubid}">
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="ddh" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="gs" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="djrq" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="ck" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="kgy" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="bm" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="kh" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="isth" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="status" /></td>
								</tr>
							</s:iterator>
							<tr>
								<td style="border: 0"></td>
							</tr>
							<!-- <tr style="background: #ECF4FB; font-size: 12px;" height="30">
								<td colspan="5"
									style="border-right: 0; border-top: 1px solid #8FAFD7;">&nbsp;&nbsp;共有
									120 条记录，当前第 1/10 页</td>
								<td colspan="4"
									style="border-right: 0; padding-right: 80px; border-top: 1px solid #8FAFD7;"
									align="right" class="page"><input type="button" value="首页" />&nbsp;<input
									type="button" value="上一页" />&nbsp;<input type="button"
									value="下一页" />&nbsp;<input type="button" value="尾页" />&nbsp;<span>转到第
										<input name="textfield" type="text" size="4"
										style="height: 16px; width: 28px; border: 1px solid #999999; background: #FFF; border-radius: 0;" />
										页
								</span>&nbsp;&nbsp;<input type="button" value="转"
									style="padding: 0 10px;" /></td>
							</tr> -->
						</table>
						 <p style="width:1800px; height:20px;font-size:12px;">
						<span class="page" style="float:right">
							<input type="button" param="1" flag="zhub_firstPage" value="首页"/>&nbsp;<input type="button" flag="zhub_prePage" param="${pageResult_zhub.currentPage-1}" value="上一页" />&nbsp;<input type="button" param="${pageResult_zhub.currentPage+1}" last="${pageResult_zhub.totalPages}" flag="zhub_nextPage" value="下一页"/>&nbsp;<input type="button" param="${pageResult_zhub.totalPages}" flag="zhub_lastPage" value="尾页"/>&nbsp;<span>转到第<input name="query_zhub.currentPage" id="pageNo" type="text" value="${pageResult_zhub.currentPage}" size="4" style="height:16px; width:28px; border:1px solid #999999; background:#FFF; border-radius:0;" />页 </span>&nbsp;&nbsp;<input type="button" value="转" style=" padding:0 10px;"/>
						</span>&nbsp;&nbsp;共有${pageResult_zhub.totalRows}条记录，当前第 ${pageResult_zhub.currentPage}/${pageResult_zhub.totalPages}页</p>
					</div>
				</td>
			</tr>
			<tr>
				<td id="right_center">
					<div style="height: 213px; width: 1150px; overflow: auto;">
						<table cellspacing="0" cellpadding="0" id="maintain-right"
							style="font-size: 12px;">
							<tr class="two" align="center">
								<td width="50">行号</td>
								<td
									style="color: #00F; border: 1px solid #9EB6CE; border-left: 0;"
									" width="100">商品编码</td>
								<td width="200">商品名称</td>
								<td width="100">型号</td>
								<td width="100">单位</td>
								<td width="100">数量</td>
								<td width="100">生产日期</td>
								<td width="100">失效日期</td>
								<td width="100">应发数量</td>
								<td width="100">实发数量</td>
								<td width="100">单价</td>
								<td width="100">金额</td>
								<td width="100">发货日期</td>
								<td width="200">赠品</td>
								<td width="200">源头单据号</td>
								<td width="200">源头行号</td>
								<td width="200">来源单据号</td>
								<td width="200">来源行号</td>
								<td width="200">累计发货数量</td>
								<td width="150">是否发货关闭</td>
							</tr>
							<s:iterator value="#pageResult_zhib.rows" status="st">
								<tr class="other">
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="hh" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="spbm" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="spmc" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="xh" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="dw" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="sl" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="scrq" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="sxrq" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="yfsl" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="sfsl" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="dj" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="je" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="fhrq" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="zp" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="ytdjh" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="ythh" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="lydjh" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="lyhh" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="ljfhsl" /></td>
									<td style="border-collapse: collapse; empty-cells: show;"><s:property
											value="isfhgb" /></td>
								</tr>
							</s:iterator>
							<tr class="other">
								<td style="border: 0"></td>
							</tr>
							<!-- <tr style="background: #ECF4FB; font-size: 12px;" height="30">
								<td colspan="13"
									style="border-right: 0; border-top: 1px solid #8FAFD7;">&nbsp;&nbsp;共有
									120 条记录，当前第 1/10 页</td>
								<td colspan="11"
									style="border-right: 0; padding-right: 80px; border-top: 1px solid #8FAFD7;"
									align="right" class="page"><input type="button" value="首页" />&nbsp;<input
									type="button" value="上一页" />&nbsp;<input type="button"
									value="下一页" />&nbsp;<input type="button" value="尾页" />&nbsp;<span>转到第
										<input name="textfield" type="text" size="4"
										style="height: 16px; width: 28px; border: 1px solid #999999; background: #FFF; border-radius: 0;" />
										页
								</span>&nbsp;&nbsp;<input type="button" value="转"
									style="padding: 0 10px;" /></td>
							</tr> -->
						</table>
						 <p style="width:1800px; height:20px;font-size:12px;">
						<span class="page" style="float:right">
							<input type="button" param="1" flag="zhib_firstPage" value="首页"/>&nbsp;<input type="button" flag="zhib_prePage" param="${pageResult_zhib.currentPage-1}" value="上一页" />&nbsp;<input type="button" flag="zhib_nextPage" param="${pageResult_zhib.currentPage+1}" last="${pageResult_zhib.totalPages}" value="下一页"/>&nbsp;<input type="button" flag="zhib_lastPage" param="${pageResult_zhib.totalPages}"  value="尾页"/>&nbsp;<span>转到第<input name="query_zhib.currentPage" id="pageNo_zhib" type="text" value="${pageResult_zhib.currentPage}" size="4" style="height:16px; width:28px; border:1px solid #999999; background:#FFF; border-radius:0;" />页 </span>&nbsp;&nbsp;<input type="button" value="转" style=" padding:0 10px;"/>
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
