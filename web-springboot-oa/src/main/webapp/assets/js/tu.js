var chart = null;
// 获取 CSV 数据并初始化图表
// $.getJSON('https://data.jianshukeji.com/jsonp?filename=csv/analytics.csv&callback=?',
// function (csv) {
$.getJSON('/face/weekData', function(csv) {

	chart = Highcharts.chart('container', {
		data : {
			csv : csv
		},
		title : {
			text : '签到记录'
		},
		subtitle : {
			text : '数据来源: 每日签到'
		},
		xAxis : {
			title : {
				text : '签到日期'
			},
			tickInterval : 24 * 3600 * 1000, // 坐标轴刻度间隔为一天
			tickWidth : 1,
			gridLineWidth : 1,
			labels : {
				align : 'left',
				x : -8,
				y : -3
			},
			// 时间格式化字符
			// 默认会根据当前的刻度间隔取对应的值，即当刻度间隔为一周时，取 week 值
			dateTimeLabelFormats : {
				week : '%Y-%m-%d'
			}
		},
		yAxis : { // 放置在左边（默认在坐标）
			title : {
				text : '签到时间'
			},
			tickInterval : 3600, // 坐标轴刻度间隔为一小时
			tickWidth : 1,
			gridLineWidth : 1,
			labels : {
				formatter : function() {
					var hh = Math.floor(this.value / 3600);
					var mm = Math.floor(this.value % 3600 / 60);
					var ss = Math.floor(this.value % 60);
					if (hh != 0) {
						return hh + '点' + mm + '分' + ss + '秒';
					} else {
						return mm + '分' + ss + '秒';
					}
				}
			},
			showFirstLabel : false
		},
		legend : {
			align : 'left',
			verticalAlign : 'top',
			y : 10,
			floating : true,
			borderWidth : 0
		},
		tooltip : {
			enabled : true,
			formatter : function() {
				var hh = Math.floor(this.y / 3600);
				var mm = Math.floor(this.y % 3600 / 60);
				var ss = Math.floor(this.y % 60);
				if (hh == 0) {
					return ' ' + this.series.name + ' : ' + mm + '分'
							+ ss + '秒';
				} else {
					return ' ' + this.series.name + ' : ' + hh + '点'
							+ mm + '分' + ss + '秒';
				}
			}
		},
		plotOptions : {
			series : {
				cursor : 'pointer',
				point : {
					events : {
						// 数据点点击事件
						// 其中 e 变量为事件对象，this 为当前数据点对象
						click : function(e) {
							var hh = Math.floor(this.y / 3600);
							var mm = Math.floor(this.y % 3600 / 60);
							var ss = Math.floor(this.y % 60);
							$('.message').html(
									' 签到时间：'
											+ Highcharts.dateFormat('%Y-%m-%d',
													this.x) + ' ' + hh + '点'
													+ mm + '分' + ss + '秒');
						}
					}
				},
				marker : {
					lineWidth : 1
				}
			}
		}
	});
});
