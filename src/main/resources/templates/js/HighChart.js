/**
 * 
 */

Highcharts.chart('chartContainer',{
	chart: {
		type:'line',
		width=500
	},
	title:{
		text:'EC2 (T2)'
	},
	xAxis:{
		categories:["Micro","Small","Medium","Large","Xtra Large"]
	},
	tooltip{
		formatter:function(){
			return'<strong>'+this.x+'</strong>'+this.y;
		}
	},
	series:[{
		data:[10,20,5,70,30]
	}]
});