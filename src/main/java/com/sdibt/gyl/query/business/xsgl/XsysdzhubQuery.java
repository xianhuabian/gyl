package com.sdibt.gyl.query.business.xsgl;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.sdibt.gyl.domain.business.xsgl.Xsysdzhib;
import com.sdibt.gyl.query.BaseQuery;

public class XsysdzhubQuery extends BaseQuery {
	private Long xsysdzhubid;//主键
	private Long ddh;
	private String gs;
	private Date rq;// 日期
	private Date qsrq;// 起算日期
	private String ks;// 客商
	private String bm;// 部门
	private String ywy;// 业务员
	private String sxbz;// 生效标志
	private String zdr;// 制单人
	private Date zdrq;// 制单日期
	private String shr;// 审核人
	private Date shrq;// 审核日期
	private String qzr;// 签字人
	private Date qzrq;// 签字日期
	private String xgr;// 修改人
	private Date xgrq;// 修改日期

	
	public Long getXsysdzhubid() {
		return xsysdzhubid;
	}

	public void setXsysdzhubid(Long xsysdzhubid) {
		this.xsysdzhubid = xsysdzhubid;
	}

	public Long getDdh() {
		return ddh;
	}

	public void setDdh(Long ddh) {
		this.ddh = ddh;
	}

	public String getGs() {
		return gs;
	}

	public void setGs(String gs) {
		this.gs = gs;
	}

	public Date getRq() {
		return rq;
	}

	public void setRq(Date rq) {
		this.rq = rq;
	}

	public Date getQsrq() {
		return qsrq;
	}

	public void setQsrq(Date qsrq) {
		this.qsrq = qsrq;
	}

	public String getKs() {
		return ks;
	}

	public void setKs(String ks) {
		this.ks = ks;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getYwy() {
		return ywy;
	}

	public void setYwy(String ywy) {
		this.ywy = ywy;
	}

	public String getSxbz() {
		return sxbz;
	}

	public void setSxbz(String sxbz) {
		this.sxbz = sxbz;
	}

	public String getZdr() {
		return zdr;
	}

	public void setZdr(String zdr) {
		this.zdr = zdr;
	}

	public Date getZdrq() {
		return zdrq;
	}

	public void setZdrq(Date zdrq) {
		this.zdrq = zdrq;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public Date getShrq() {
		return shrq;
	}

	public void setShrq(Date shrq) {
		this.shrq = shrq;
	}

	public String getQzr() {
		return qzr;
	}

	public void setQzr(String qzr) {
		this.qzr = qzr;
	}

	public Date getQzrq() {
		return qzrq;
	}

	public void setQzrq(Date qzrq) {
		this.qzrq = qzrq;
	}

	public String getXgr() {
		return xgr;
	}

	public void setXgr(String xgr) {
		this.xgr = xgr;
	}

	public Date getXgrq() {
		return xgrq;
	}

	public void setXgrq(Date xgrq) {
		this.xgrq = xgrq;
	}


	@Override
	public Map<String, Object> buildWhere() {
		if (this.ddh != null) {
			this.keyValues.put("ddh", ddh);
		}
//		if (this.gs != null) {
//			this.keyValues.put("gs", gs);
//		}
		if (this.rq != null) {
			this.keyValues.put("rq", rq);
		}
		if (this.qsrq != null) {
			this.keyValues.put("qsrq", qsrq);
		}

		if (StringUtils.isNotBlank(ks)) {
			this.keyValues.put("ks", ks);
		}
		if (StringUtils.isNotBlank(bm)) {
			this.keyValues.put("bm", bm);
		}
		if (StringUtils.isNotBlank(ywy)) {
			this.keyValues.put("ywy", ywy);
		}
		if (this.sxbz != null) {
			this.keyValues.put("sxbz", sxbz);
		}

		if (StringUtils.isNotBlank(zdr)) {
			this.keyValues.put("zdr", zdr);
		}
		if (this.zdrq != null) {
			this.keyValues.put("zdrq", zdrq);
		}
		if (StringUtils.isNotBlank(shr)) {
			this.keyValues.put("shr", shr);
		}
		if (this.shrq != null) {
			this.keyValues.put("shrq", shrq);
		}
		if (StringUtils.isNotBlank(qzr)) {
			this.keyValues.put("qzr", qzr);
		}
		if (this.qzrq != null) {
			this.keyValues.put("qzrq", qzrq);
		}
		if (StringUtils.isNotBlank(xgr)) {
			this.keyValues.put("xgr", xgr);
		}
		if (this.xgrq != null) {
			this.keyValues.put("xgrq", xgrq);
		}
		if (this.xsysdzhubid != null) {
			this.keyValues.put("xsysdzhubid", xsysdzhubid);
		}
		return this.keyValues;
	}
}
