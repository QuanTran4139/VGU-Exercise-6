var jsonfile = {
   "jsonarray": [
   {
      "Scale": "1",
      "Number": 10
   }, {
      "Scale": "2",
      "Number": 4
   }, {
	   "Scale": "3",
	   "Number": 3
   }, {
	   "Scale": "4",
	   "Number": 2
   }, {
	   "Scale": "5",
	   "Number": 1
   }]
};

Chart.defaults.scale.ticks.beginAtZero = true;

var labels = jsonfile.jsonarray.map(function(e) {
	return e.Scale;
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
			label: 'Evaluation Scale',
			data: data,
			backgroundColor: 'rgba(0, 119, 204, 0.3)'
		}]
	}
};

var chart = new Chart(ctx, config);