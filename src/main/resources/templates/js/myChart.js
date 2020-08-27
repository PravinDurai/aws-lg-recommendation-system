/**
 * 
 */
var myChart = document.getElementById('myChart').getContext('2d');
		//Global Options
		Chart.defaults.global.defaultFontFamily='Lato';
		Chart.defaults.global.defaultFontSize=12;
		Chart.defaults.global.defaultFontColor='#777';
		var lineChart = new Chart(myChart, {
			type : 'bar',
			data : {
				labels : [ 'Micro', 'Small', 'Medium', 'Large', 'Xtra Large' ],
				datasets : [ {
					label : 'EMEA',
					data : [ 24, 27, 14, 4, 13 ],
					backgroundColor:[
						'rgba(255,99,132,0.6)',
						'rgba(54,162,235,0.6)',
						'rgba(255,206,86,0.6)',
						'rgba(75,192,192,0.6)',
						'rgba(153,102,255,0.6)'
					],
					borderWidth:1,
					borderColor:'#777',
					hoverBorderWidth:3,
					hoverBorderColor:'#000'
				}]
			},
			options : {

			}
		});