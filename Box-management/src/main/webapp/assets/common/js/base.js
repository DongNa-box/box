/**
 * 加载页面
 */
function loadPage(url){
	$("#mainDiv").load(url,function(response,status,xhr){
		if(status=="success"){
			if(response){
				try{
					var result = jQuery.parseJSON(response);
					if(result.code==100){ 
						$("#mainDiv").html("");
					}
				}catch(e){
					return response;
				}
			}
		}
	});
}


function ajaxPost(url, params, callback) {
	var result = null;
	$.ajax({
		type : 'post',
		async : false,
		url : url,
		data : params,
		dataType : 'json',
		success : function(data, status){
			result = data;
			if(data&&data.code&&data.code=='101'){
				layer.alert("操作失败，请刷新重试，具体错误："+data.message);
				return false;
			}
			if (callback) { 
				callback.call(this, data, status);
			}
		},
		error : function(err, err1, err2){
		    layer.alert(JSON.stringify(err) + '<br/>err1:' + JSON.stringify(err1) + '<br/>err2:' + JSON.stringify(err2));
		}
	});
	return result;
}


function ajaxGet(url, params, callback) {
	var result = null;
	$.ajax({
		type : 'get',
		async : false,
		url : url,
		data : params,
		dataType : 'json',
		success : function(data, status){
			result = data;
			if(data&&data.code&&data.code=='101'){
				layer.alert("操作失败，请刷新重试，具体错误："+data.message);
				return false;
			}
			if (callback) { 
				callback.call(this, data, status);
			}
		},
		error : function(err, err1, err2){
		    layer.alert(JSON.stringify(err) + '<br/>err1:' + JSON.stringify(err1) + '<br/>err2:' + JSON.stringify(err2));
		}
	});
	return result;
}


function ajaxPostFile(url, params, callback) {
	var result = null;
	$.ajax({
        url : url,
        type : 'post',
        data : params,
        processData:false,
        contentType:false,
        beforeSend:function(request){
        	index = layer.load(1, {shade: [0.8,'#393D49']})
        },
        success:function(data, status){
        	layer.close(index);
			result = data;
			if(data&&data.success){
				layer.msg(data.code, {
					icon : 1,
					time : 1500,
				});
			}
			if (callback) { 
				callback.call(this, data, status);
			}
        },
        error:function(e){
        	layer.close(index);
        	layer.alert("系统出现错误...");
        }
    });
	return result;
}


/**
 * 格式化日期
 */
function formatDate(date, format) {
	if(!date)return date;
	if(typeof date == "number" || typeof date == "string"){
		date = new Date(date);
	}
	return date.Format(format);
}
/**
 * 日期格式化方法
 */
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

/**
 * 日期格式化处理 yyyy-MM-dd 
 * @param value
 * @param row
 * @param index
 * @returns
 */
function formatteryyyyMMdd(value){
	if (value != undefined && value != null && $.trim(value) != '') {
		return formatDate(value, "yyyy-MM-dd");
	}else{
		return value;
	}	
}
/**
 * 日期格式化处理 yyyy-MM-dd HH:mm:ss
 * @param value
 * @param row
 * @param index
 * @returns
 */
function formatteryyyyMMddHHmmss(value){
	if (value != undefined && value != null && $.trim(value) != '') {
		return formatDate(value, "yyyy-MM-dd HH:mm:ss");
	}else{
		return value;
	}	
}

/**
 * 日期格式化处理 HH:mm:ss
 * @param value
 * @param row
 * @param index
 * @returns
 */
function formatterHHmmss(value, row, index){
	if (value != undefined && value != null && $.trim(value) != '') {
		return formatDate(value, "HH:mm:ss");
	}else{
		return value;
	}	
}
/**
 * 序号格式化
 * @param value
 * @param row
 * @param index
 * @returns
 */
function seqFormatter(value, row, index) { 
	if (index != undefined && index != null && $.trim(index) != '') {
		return index+1;
	}else{
		return index;
	}	
}

/**
 * 显示格式化
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operateFormatter(value, row, index) {
	if (value != undefined && value != null && $.trim(value) != '') {
		if(value == '1'){
			//return '<span class="label label-primary">是</span>';
			return '<img src="./assets/common/images/on.png">';
		}else{
			//return '<span class="label label-danger">否</span>';
			return '<img src="./assets/common/images/off.png">';
		}
	}else{
		return value;
	}	
}



/*
 * 性别格式化
 */
function sexFormatter(value, row, index) {
	if (value != undefined && value != null && $.trim(value) != '') {
		if(value == '1'){
			return '男';
		}else{
			return '女';
		}
	}else{
		return value;
	}	
}

/*
 * 格式化表格时间
 */
/**
function timeFormatter(value,row,index){
	if (value != undefined && value != null && $.trim(value) != '') {
		return formatDate(value, "yyyy-MM-dd HH:mm:ss");
	}else{
		return value;
	}		 
}
*/
/*
 * 格式化生日
 */
/**
function birthFormatter(value,row,index){
	if (value != undefined && value != null && $.trim(value) != '') {
		return formatDate(value, "yyyy-MM-dd");
	}else{
		return value;
	}		 
}
*/
/*
 * 用户类别格式化
 */
function userTypeFormatter(value, row, index) {
	if (value != undefined && value != null && $.trim(value) != '') {
	    if(value == '1'){
	    	return '后台管理员';
	    }else if(value == '2'){
			return 'web用户';
		}else if(value == '3'){
			return 'app用户';
		
	}else{
		return value;
	}	
}
 

function jsGetAge(strBirthday) {
	var bDay = new Date(strBirthday),
		nDay = new Date(),
		nbDay = new Date(nDay.getFullYear(),bDay.getMonth(),bDay.getDate()),
		age = nDay.getFullYear() - bDay.getFullYear();
	//if (bDay.getTime()>nDay.getTime()) {return '日期有错'}
	return nbDay.getTime()<=nDay.getTime()?age:--age;
}

}