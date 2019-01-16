$(function(){
	sendemail();
});

function sendemail(){
	if(countdown<0){
		$("#mmBox").text("--");
		$("#ssBox").text("--");
	}else{
		settime();
	}
}

function settime() {
    if (countdown <= 0) {
    	$("#mmBox").text("--");
		$("#ssBox").text("--");
    	var timer2=window.setTimeout(function(){
    		window.location.href="/index";
			window.clearTimeout(timer2);
		},2000);
        return;
    } else {
    	//$("html,body").css("overflow","unset");
    	var mmss = fromTime(countdown);
    	countdown = parseInt(countdown)-1000;
    	var Time = mmss.split(",");
    	$("#mmBox").text((Time[0]<10?"0"+Time[0]:Time[0]));
		$("#ssBox").text((Time[1]<10?"0"+Time[1]:Time[1]));
    } 
	setTimeout(function() {settime();},1000) 
}

//把毫秒化成分和秒
function fromTime(timeNum){
	var mm = "";
	var ss = "";
	var allSS = parseInt(timeNum)/1000;
	mm = Math.floor(allSS/60);
	ss = allSS%60;
	return mm+","+ss;
}

function closebox(){
	$("#searchRes").css("display","none");
}

function daxiao(allRes){
	if(allRes<=10){
		return "小";
	}else if(allRes>10){
		return "大";
	}
}

function danshuang(allRes){
	if(allRes%2==0){
		return "双";
	}else{
		return "单";
	}
}

function findsearch(){
	var keyval = $("#searchKey").val().trim();
	$.ajax({ 
		  type:'POST', 
		  url:'/findqiNum', 
		  data:{"qiNum":keyval},
		  success:function(data){ 
			  if(data.msg==0){
				  $("#contentTR").html("<td style='color:#999; font-size:15px;' colspan='3'>没有找到有关结果</td>");
			  }else{
				  $("#contentTR").html("<td>"+data.resdate.qiNum+"</td><td>"+data.resdate.Num+"</td><td>"+data.resdate.creantime+"</td>");
			  }
			  $("#searchRes").css("display","block");
		  }, 
		  error:function(){ 
		    alert("系统繁忙，请稍后再试!");
		  } 
	}); 
}

function formd(qiNum, Num){
	var jsond = {};
	jsond.qiNum = qiNum;
	jsond.Num = Num;
	return jsond;
}

function bigsamll(allRes){
	if(allRes<=10){
		return 4;
	}else if(allRes>10){
		return 3;
	}
}

function signDouble(allRes){
	if(allRes%2==0){
		return 6;
	}else{
		return 5;
	}
}

function subsTime(timedate){
	timedate = timedate.substring(11,timedate.length);
	return timedate;
}

function writResult(allRes){
	if(allRes==3||allRes=="3"){
		return 4;
	}else if(allRes==4||allRes=="4"){
		return 5;
	}else if(allRes==5||allRes=="5"){
		return 6;
	}else if(allRes==6||allRes=="6"){
		return 7;
	}else if(allRes==7||allRes=="7"){
		return 8;
	}else if(allRes==8||allRes=="8"){
		return 9;
	}else if(allRes==9||allRes=="9"){
		return 10;
	}else if(allRes==10||allRes=="10"){
		return 11;
	}else if(allRes==11||allRes=="11"){
		return 12;
	}else if(allRes==12||allRes=="12"){
		return 13;
	}else if(allRes==13||allRes=="13"){
		return 14;
	}else if(allRes==14||allRes=="14"){
		return 15;
	}else if(allRes==15||allRes=="15"){
		return 16;
	}else if(allRes==16||allRes=="16"){
		return 17;
	}else if(allRes==17||allRes=="17"){
		return 18;
	}else if(allRes==18||allRes=="18"){
		return 19;
	}
}

function formPointer(x,y){
	var jsond = {};
	jsond.x = x;
	jsond.y = y;
	return jsond;
}

//封装一个画线的方法
function drawLine(x1,y1,x2,y2,index,objNum,colorstyle){
	var top = 0;
	var left = 0;
	var width = 0;
	var height = 0;
	var starPoint1 = 0;
	var overPoint1 = 0;
	var starPoint2 = 0;
	var overPoint2 = 0;
	if(x2>x1){
		top=y1;
		left=x1;
		width = x2-x1;
		height = y2 - y1;
		starPoint1 = 0;
		overPoint1 = 0;
		starPoint2 = width;
		overPoint2 = height;
	}else{
		top=y1;
		left=x2;
		width = x1-x2;
		height = y2 - y1;
		starPoint1 = width;
		overPoint1 = 0;
		starPoint2 = 0;
		overPoint2 = height;
	}
	var cava = '<canvas width="'+(width+2)+'" height="'+height+'" style="position: absolute; top: '+top+'px; left: '+left+'px;"></canvas>'
	$("#canvasBox"+objNum).append(cava);
	
	var canvas=$("#canvasBox"+objNum+" canvas")[index];
	var ctx=canvas.getContext('2d');
	ctx.beginPath();
	ctx.moveTo(starPoint1,overPoint1);
	ctx.lineTo(starPoint2,overPoint2);
	ctx.strokeStyle = colorstyle;
	ctx.stroke();
}

$(function(){
$("#zoushiMap").click(function(){
	$("#zoushiBox").show();
	$("#resuBox").hide();
});

$("#reSultBtn").click(function(){
	$("#zoushiBox").hide();
	$("#resuBox").show();
});

});