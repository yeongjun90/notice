package com.noticepro.common;

import java.util.HashMap;
import java.util.Map;

public class PageModule {
	public Map processPage(int page, int count, int unit) {

		Map p = new HashMap();
		int ppp = (page - 1) * unit;
		p.put("page", ppp);
		if (page / unit == 0 || page == unit) {
			p.put("start", 1);
		} else {
			p.put("start", (page / unit) * unit + 1);
		}
		if (count / unit == 0) {
			p.put("end", 1);
		} else if (count % unit == 0) {
			p.put("end", count / unit);
		} else {
			p.put("end", count / unit + 1);
		}
		p.put("now", page);
		return p;
	}

	public Map processPage(Map param, int count, int unit) {

		return processPage(Integer.parseInt(param.get("page") + ""), count, unit);
	}

	public Map processPage(int page, int count, Map ret, int unit) {
		Map pageMap = processPage(page, count, unit);
		ret.put("page", pageMap);
		return pageMap;
	}
}
