<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE html>
<!-- ��ͼ2������  -->
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
			text : '����ʡ�ͻ��ֲ�����ͼ',
			//subtext : '��������',
			x : 'right',
			y : 'top'
		},
		tooltip : {
			trigger : 'item'
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : [ '�߻�Առ��(>=2%)������Ч��(>=50%)', '�߻�Առ��(>=2%)������Ч��(<50%)', '�л�Առ��(1%-2%)������Ч��(>=50%)',
			         '�л�Առ��(1%-2%)������Ч��(<50%)','�ͻ�Առ��(<1%)������Ч��(>=50%)','�ͻ�Առ��(<1%)������Ч��(<50%)']
		},
		dataRange : {
			min : 0,
			max : 6000,
			x : 'left',
			y : 'bottom',
			text : [ '��', '��' ], // �ı���Ĭ��Ϊ��ֵ�ı�
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
			name : '�߻�Առ��(>=2%)������Ч��(>=50%)',
			type : 'map',
			mapType : '����',
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
				name : '�γ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��ͨ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��Ǩ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '���Ƹ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '�Ͼ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '̩����',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '����',
				value : Math.round(Math.random() * 1000)
			} ]
		}, {
			name : '�߻�Առ��(>=2%)������Ч��(<50%)',
			type : 'map',
			mapType : '����',
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
				name : '�γ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��ͨ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��Ǩ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '���Ƹ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '�Ͼ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '̩����',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '����',
				value : Math.round(Math.random() * 1000)
			} ]
		}, {
			name : '�л�Առ��(1%-2%)������Ч��(>=50%)',
			type : 'map',
			mapType : '����',
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
				name : '�γ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��ͨ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��Ǩ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '���Ƹ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '�Ͼ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '̩����',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '����',
				value : Math.round(Math.random() * 1000)
			} ]
		},{
			name : '�л�Առ��(1%-2%)������Ч��(<50%)',
			type : 'map',
			mapType : '����',
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
				name : '�γ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��ͨ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��Ǩ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '���Ƹ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '�Ͼ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '̩����',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '����',
				value : Math.round(Math.random() * 1000)
			} ]
		},{
			name : '�ͻ�Առ��(<1%)������Ч��(>=50%)',
			type : 'map',
			mapType : '����',
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
				name : '�γ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��ͨ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��Ǩ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '���Ƹ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '�Ͼ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '̩����',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '����',
				value : Math.round(Math.random() * 1000)
			} ]
		},{
			name : '�ͻ�Առ��(<1%)������Ч��(<50%)',
			type : 'map',
			mapType : '����',
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
				name : '�γ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��ͨ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '��Ǩ��',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '���Ƹ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '�Ͼ���',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '̩����',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '������',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '����',
				value : Math.round(Math.random() * 1000)
			} ]
		} ]
	};
	myChart.setOption(option);
</script>
</body>