//jquery validate 自定义扩展method

//手机号码验证
$.validator.addMethod("isMobile", function(value, element) {
	value = $.trim(value);
	var length = value.length;
	var mobile = /^1[3|4|5|7|8]\d{9}$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "手机号码");

//特殊字符
$.validator.addMethod("specialCharValidate", function(value, element) {
	var length = value.length;
	var pattern = new RegExp("[_！`~!@%#$^&*=|{}':;',\\[\\]<>/?\\.；：%……+￥【】‘”“'。，、？]"); 
	return this.optional(element) ||  !pattern.test(value);
}, "特殊字符");

//ip地址
$.validator.addMethod("ip", function(value, element) {    
    return this.optional(element) || /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9]):\d{0,5}$/.test(value);    
}, "请填写正确的IP地址。");

//6位数字
$.validator.addMethod("isSixNum", function(value, element) {
	var num = /^\d{6}$/;
	return this.optional(element) ||  num.test(value);
}, "6位数字");

//10位数字
$.validator.addMethod("isTenNum", function(value, element) {
	var num = /^\d{10}$/;
	return this.optional(element) ||  num.test(value);
}, "10位数字");

//中文字两个字节  
$.validator.addMethod("byteMaxLength", function(value,  
        element, param) {  
    var length = value.length;  
    for ( var i = 0; i < value.length; i++) {  
        if (value.charCodeAt(i) > 127) {  
            length++;  
        }  
    }  
    return this.optional(element) || (length <= param);  
}, $.validator.format("不能超过{0}个字节")); 


// 验证值小数位数不能超过两位
$.validator.addMethod("twoDecimalPlaces", function(value, element) {
	var decimal = /^-?\d+(\.\d{1,2})?$/;
	return this.optional(element) || (decimal.test(value));
}, $.validator.format("小数位最多为两位"));
