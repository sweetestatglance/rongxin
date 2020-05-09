/***************************日期脚本***************************************/
var newdate = null;
//今天
function getToDay(){
	var now = new Date();
	var nowYear = now.getFullYear();
	var nowMonth = now.getMonth();
	var nowDate = now.getDate();
	newdate = new Date(nowYear,nowMonth,nowDate);
	nowMonth = doHandleMonth(nowMonth + 1);
	nowDate = doHandleMonth(nowDate);
	return nowYear+"-"+nowMonth+"-"+nowDate;
}
function doHandleMonth(month){
	if(month.toString().length == 1){
		month = "0" + month;
	}
	return month;
}
//昨天
function getYesterDay(){
	var newtimems=newdate.getTime()-(24*60*60*1000);
	var yesd = new Date(newtimems);
	var yesYear = yesd.getFullYear();
	var yesMonth = yesd.getMonth();
	var yesDate = yesd.getDate();
	yesMonth = doHandleMonth(yesMonth + 1);
	yesDate = doHandleMonth(yesDate);
	return yesYear+"-"+yesMonth+"-"+yesDate;
}
//前7天
function get7DayAgo(){
	var newtimems=newdate.getTime()-(168*60*60*1000);
	var yesd = new Date(newtimems);
	var yesYear = yesd.getFullYear();
	var yesMonth = yesd.getMonth();
	var yesDate = yesd.getDate();
	yesMonth = doHandleMonth(yesMonth + 1);
	yesDate = doHandleMonth(yesDate);
	return yesYear+"-"+yesMonth+"-"+yesDate;
}