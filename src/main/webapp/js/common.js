"use strict";

$.extend({
	getP: function(name) {
		var vars = [], hash;
		var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
		for (var i = 0; i < hashes.length; i++) {
			try {
				hash = hashes[i].split('=');
				if (hash[0] == name) {

					if (hash[1].substr(hash[1].length - 1) == "#") {
						return hash[1].substr(0, hash[1].length - 1);
					} else {
						return hash[1];
					}
				}
			} catch (e) {
			}
		}
		return "";
	},
	GQSN: function(param) {
		var qpos = window.location.href.indexOf('?');
		if (qpos < 0)
			qpos = window.location.href.length;
		var url = window.location.href.slice(0, qpos);
		var queryString = window.location.href.slice(qpos + 1);
		var pageQueryString = "";

		var hashes = queryString.split('&');
		for (var i = 0; i < hashes.length; i++) {

			try {
				hash = hashes[i].split('=');
				var hashKey = hash[0];
				var hashVal = hash[1];

				if (!hashKey || hashKey.trim() == "" || hashKey == "page") {
					continue;
				}
				if (hashVal.substr(hashVal.length - 1) == "#") {
					hashVal = hashVal.substr(0, hashVal.length - 1);
				} else {
					hashVal = hashVal;
				}

				if (param) {
					for (var fo = 0; fo < param.length; fo++) {
						if (hashKey == param[fo]) {
						} else {
							pageQueryString = pageQueryString + "&" + hashKey + "=" + hashVal;
						}
					}
				} else {
					pageQueryString = pageQueryString + "&" + hashKey + "=" + hashVal;
				}

			} catch (e) {

			}
		}
		return {
			url: url,
			qs: pageQueryString
		};
	}
});

var pagePrev = function() {
};
var pageNext = function() {
};
var pageGo = function() {
};

function drawPage(ele, param) {
	var qpos = window.location.href.indexOf('?');
	if (qpos < 0)
		qpos = window.location.href.length;
	var url = window.location.href.slice(0, qpos);
	var queryString = window.location.href.slice(qpos + 1);
	var pageQueryString = "";

	var hashes = queryString.split('&');
	for (var i = 0; i < hashes.length; i++) {
		try {
			var hash = hashes[i].split('=');
			var hashKey = hash[0];
			var hashVal = hash[1];

			if (!hashKey || hashKey.trim() == "" || hashKey == "page") {
				continue;
			}
			if (hashVal.substr(hashVal.length - 1) == "#") {
				hashVal = hashVal.substr(0, hashVal.length - 1);
			} else {
				hashVal = hashVal;
			}

			pageQueryString = pageQueryString + "&" + hashKey + "=" + hashVal;
		} catch (e) {

		}
	}

	pagePrev = function() {
		if (param.now - 10 > 0) {
			var urlr = url + "?" + pageQueryString + "&page=" + (param.now - 10);
			window.location.href = urlr;
		} else {
			var urlr = url + "?" + pageQueryString + "&page=1";
			window.location.href = urlr;
		}
	}
	pageNext = function() {
		if (param.now + 10 <= param.end) {
			var urlr = url + "?" + pageQueryString + "&page=" + (param.now + 10);
			window.location.href = urlr;
		} else {
			var urlr = url + "?" + pageQueryString + "&page=" + param.end;
			window.location.href = urlr;
		}
	}
	pageGo = function(abs) {
		var urlr = url + "?" + pageQueryString + "&page=" + abs;
		window.location.href = urlr;
	}

	$("#" + ele).append('<li class="arrow_left" onclick="pagePrev();"></li>');
	if (param.end < 10) {
		for (var i = 1; i <= param.end; i++) {
			$("#" + ele).append('<li class="page_num" onclick="pageGo(' + i + ');">' + i + '</li>');
		}
		$("#" + ele + " li").eq(param.now).addClass('active');
	} else {
		if (param.now < 6) {
			for (var i = 1; i < 10; i++) {
				$("#" + ele).append('<li class="page_num" onclick="pageGo(' + i + ');">' + i + '</li>');
			}
			$("#" + ele).append('<li class="combined"></li>');
			$("#" + ele).append('<li class="page_num" onclick="pageGo(' + param.end + ');">' + param.end + '</li>');
			$("#" + ele + " li").eq(param.now).addClass('active');
		} else if (param.now > 5 && param.now + 3 < param.end) {
			$("#" + ele).append('<li class="page_num" onclick="pageGo(1);">1</li>');
			$("#" + ele).append('<li class="combined"></li>');
			for (var i = param.now - 4; i < param.now + 4; i++) {
				$("#" + ele).append('<li class="page_num" onclick="pageGo(' + i + ');">' + i + '</li>');
			}
			$("#" + ele).append('<li class="combined"></li>');
			$("#" + ele).append('<li class="page_num" onclick="pageGo(' + param.end + ');">' + param.end + '</li>');

			$("#" + ele + " li").eq(7).addClass('active');
		} else {
			$("#" + ele).append('<li class="page_num" onclick="pageGo(1);">1</li>');
			$("#" + ele).append('<li class="combined"></li>');
			for (var i = param.end - 8; i < param.end + 1; i++) {
				$("#" + ele).append('<li class="page_num" onclick="pageGo(' + i + ');">' + i + '</li>');
			}
			$("#" + ele + " li").eq(param.now % 10 + 4).addClass('active');
		}
	}
	$("#" + ele).append('<li class="arrow_right" onclick="pageNext();"></li>');

}
