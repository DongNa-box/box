$.extend($.fn.dataTable.defaults, {
	searching : false,
	lengthChange : false,
    autoWidth:false,	//禁用自动调整列宽
    scrollX:true,
    //scrollXInner:'110%',
    scrollCollapse: true,
    stateSave: true,
    stripeClasses: ['odd', 'even'],//为奇偶行加上样式，兼容不支持CSS伪类的场合
    order: [],			//取消默认排序查询,否则复选框一列会出现小箭头
    /**
    keys: {
    	columns: ':not(:first-child)'
    },
    */
    /**
    fixedColumns:{
    	leftColumns: 1
    },
    */
    select: {
		style: 'multi',//'single'
		selector: 'td:first-child'
	},
	language : {
		processing : '处理中...',
		lengthMenu : '显示 _MENU_ 项结果',
		zeroRecords : '没有匹配结果',
		info : '显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项',
		infoEmpty : '显示第 0 至 0 项结果，共 0 项',
		infoFiltered : '(由 _MAX_ 项结果过滤)',
		infoPostFix : '',
		search : '搜索:',
		url : '',
		emptyTable : '未搜索到数据',
		loadingRecords : '载入中...',
		infoThousands : ',',
		paginate : {
			first : '首页',
			previous : '上页',
			next : '下页',
			last : '末页',
			jump : '跳转'
		},
		aria : {
			sortAscending : ': 以升序排列此列',
			sortDescending : ': 以降序排列此列'
		}
	}
});