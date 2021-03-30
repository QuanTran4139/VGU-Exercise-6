var jsonfile = {
   "jsonarray": [
   {
      "Gender": "Male",
      "Number": 10
   }, {
      "Gender": "Female",
      "Number": 5
   }, {
	   "Gender": "Other",
	   "Number": 2
   }]
};

Chart.defaults.scale.ticks.beginAtZero = true;

var labels = jsonfile.jsonarray.map(function(e) {
	return e.Gender;
});
var data = jsonfile.jsonarray.map(function(e) {
	return e.Number;
});


var ctx = GenderChart.getContext('2d');
var config = {
	type: 'bar',
	data: {
		labels: labels,
		datasets: [{
			label: 'Number of Input',
			data: data,
			backgroundColor: 'rgba(0, 100, 200, 99)'
		}]
	}
};

var chart = new Chart(ctx, config);