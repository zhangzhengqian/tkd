﻿hojo.provide("icallcenter.logon");

icallcenter.logon.startLogon = function (loginName, password, extenType) {
	var config = {
        Monitor: true,
        proxy_url: "http://119.254.80.102/",
        extenType: extenType,
        password: password,
		User: loginName,
		busyType: "0"
    };
	icallcenter.logon.initPhone(config);
};

//init softphone
icallcenter.logon.initPhone = function (config) {
    hojo.require("icallcenter.Phone");
    icallcenter.Phone.registerEvent(config);
};

icallcenter.logon.afterPhone = function (phone) {
    console.info("============icallcenter.logon.afterPhone"+phone)
    if (phone) {
        hojo.require("icallcenter.SoftphoneBar");
        softphoneBar = new icallcenter.SoftphoneBar(phone, "softphonebar");
        hojo.require("icallcenter.callProcessor");
        callProcessor = new icallcenter.callProcessor(phone);
    }
};

icallcenter.logon.getUrlValue = function (param) {
    var query = window.location.search;
    var iLen = param.length;
    var iStart = query.indexOf(param);
    if (iStart == -1)
        return "";
    iStart += iLen + 1;
    var iEnd = query.indexOf("&", iStart);
    if (iEnd == -1)
        return query.substring(iStart);
    return query.substring(iStart, iEnd);
};


//登出
icallcenter.logon.logout = function(){
   phone.destroy(true);
};