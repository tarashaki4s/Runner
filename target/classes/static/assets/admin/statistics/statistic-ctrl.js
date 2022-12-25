app.controller("statistic-ctrl", function($scope, $http){
	$scope.sales = [];
	$scope.revenues = [];

	$scope.initialize = function(){
		// load product
		$http.get("/rest/statistics/sales").then(resp => {
			$scope.items = resp.data;
		});
		//load categories
		$http.get("/rest/statistics/revenue").then(resp => {
			$scope.cates = resp.data;
		});
	}

	$scope.initialize();

	 $scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
      $scope.series = ['Series A', 'Series B'];
      $scope.data = [
        [65, 59, 80, 81, 56, 55, 40],
        [28, 48, 40, 19, 86, 27, 90]
      ];
      $scope.onClick = function (points, evt) {
        console.log(points, evt);
      };
      $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }, { yAxisID: 'y-axis-2' }];
      $scope.options = {
        scales: {
          yAxes: [
            {
              id: 'y-axis-1',
              type: 'linear',
              display: true,
              position: 'left'
            },
            {
              id: 'y-axis-2',
              type: 'linear',
              display: true,
              position: 'right'
            }
          ]
        }
      };
});
