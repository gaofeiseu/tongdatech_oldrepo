<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE html>
<!-- 地图2级联动  -->
<head>
	<meta charset="UTF-8">
	<title>ECharts</title>
</head>
<body>
	<div id="main"
		style="height: 600px; border: 1px solid #ccc; padding: 10px;"></div>
	<script src="/echarts/js/echarts-plain-map.js" charset="UTF-8"></script>
	<script type="text/javascript">
	var myChart = echarts.init(document.getElementById('main'));
	var option = {
		title : {
			text : '江苏省客户分布分析图',
			//subtext : '测试数据',
			x : 'right',
			y : 'top'
		},
		tooltip : {
			trigger : 'item'
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : [ '高会员占比(>=2%)，高有效率(>=50%)', '高会员占比(>=2%)，低有效率(<50%)', '中会员占比(1%-2%)，高有效率(>=50%)',
			         '中会员占比(1%-2%)，低有效率(<50%)','低会员占比(<1%)，高有效率(>=50%)','低会员占比(<1%)，低有效率(<50%)']
		},
		dataRange : {
			min : 0,
			max : 6000,
			x : 'left',
			y : 'bottom',
			text : [ '高', '低' ], // 文本，默认为数值文本
			color: ['orangered','yellow','lightskyblue'],
			calculable : true
		},
		toolbox : {
			show : true,
			orient : 'vertical',
			x : 'right',
			y : 'center',
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		series : [ {
			name : '高会员占比(>=2%)，高有效率(>=50%)',
			type : 'map',
			mapType : '江苏',
			roam : true,
			itemStyle : {
				normal : {
					label : {
						show : true
					}
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : [ {
				name : '盐城市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '徐州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南通市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '淮安市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '苏州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '宿迁市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '连云港市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '扬州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南京市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '泰州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '无锡市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '常州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '镇江市',
				value : Math.round(Math.random() * 1000)
			} ]
		}, {
			name : '高会员占比(>=2%)，低有效率(<50%)',
			type : 'map',
			mapType : '江苏',
			itemStyle : {
				normal : {
					label : {
						show : true
					}
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : [ {
				name : '盐城市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '徐州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南通市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '淮安市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '苏州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '宿迁市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '连云港市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '扬州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南京市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '泰州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '无锡市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '常州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '镇江市',
				value : Math.round(Math.random() * 1000)
			} ]
		}, {
			name : '中会员占比(1%-2%)，高有效率(>=50%)',
			type : 'map',
			mapType : '江苏',
			itemStyle : {
				normal : {
					label : {
						show : true
					}
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : [ {
				name : '盐城市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '徐州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南通市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '淮安市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '苏州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '宿迁市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '连云港市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '扬州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南京市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '泰州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '无锡市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '常州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '镇江市',
				value : Math.round(Math.random() * 1000)
			} ]
		},{
			name : '中会员占比(1%-2%)，低有效率(<50%)',
			type : 'map',
			mapType : '江苏',
			itemStyle : {
				normal : {
					label : {
						show : true
					}
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : [ {
				name : '盐城市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '徐州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南通市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '淮安市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '苏州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '宿迁市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '连云港市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '扬州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南京市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '泰州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '无锡市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '常州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '镇江市',
				value : Math.round(Math.random() * 1000)
			} ]
		},{
			name : '低会员占比(<1%)，高有效率(>=50%)',
			type : 'map',
			mapType : '江苏',
			itemStyle : {
				normal : {
					label : {
						show : true
					}
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : [ {
				name : '盐城市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '徐州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南通市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '淮安市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '苏州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '宿迁市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '连云港市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '扬州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南京市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '泰州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '无锡市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '常州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '镇江市',
				value : Math.round(Math.random() * 1000)
			} ]
		},{
			name : '低会员占比(<1%)，低有效率(<50%)',
			type : 'map',
			mapType : '江苏',
			itemStyle : {
				normal : {
					label : {
						show : true
					}
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : [ {
				name : '盐城市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '徐州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南通市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '淮安市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '苏州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '宿迁市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '连云港市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '扬州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '南京市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '泰州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '无锡市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '常州市',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '镇江市',
				value : Math.round(Math.random() * 1000)
			} ]
		} ]
	};
	myChart.setOption(option);
</script>
</body>