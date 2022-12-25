app.controller("order-ctrl", function($scope, $http, $location) {
	
	$scope.items = [];
	$scope.sales = [];
    $scope.revenue = [];

	$scope.orders = [];
	$scope.initialize = function(){
		// load product
		$http.get("/rest/orders/list").then(resp => {
			$scope.items = resp.data;
		});
		$http.get("/rest/orders/list/orderconfirm").then(resp => {
			$scope.orders = resp.data;
		});

	}

	$scope.initialize();

    $scope.initializeStatistic = function(){
    // load product
        $http.get("/rest/statistics/sales").then(resp => {
            $scope.sales = resp.data;
            console.log(resp.data);
        });
                //load categories
        $http.get("/rest/statistics/revenue").then(resp => {
            $scope.revenue = resp.data;
        });
    }

    $scope.initializeStatistic();

    $scope.labels = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul","Aug", "Sep", "Oct", "Nov", "Dec"];
    $scope.series = ['Doanh số'];
    $scope.onClick = function (points, evt) {
      console.log(points, evt);
    };
    $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }];
    $scope.options = {
      scales: {
        yAxes: [
          {
            id: 'y-axis-1',
            type: 'linear',
            display: true,
            position: 'left'
          },
        ]
      }
    };


  $scope.updatestatus = function(item){

		$http.put(`/rest/orders/updateStatus/${item.id}`,item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật sản phẩm thành công");
			$scope.initialize();
		}).catch(error => {
			alert("Lỗi update sản phẩm");
			console.log("Error", error);
		});
	}

	$scope.pager = {
		page: 0,
		size: 10,
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first(){
			this.page = 0;
		},
		prev(){
			this.page--;
			if(this.page < 0){
				this.last();
			}
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		last(){
			this.page = this.count - 1;
		}
	}

	$scope.pagerOrderConfirm = {
		page: 0,
		size: 10,
		get orders(){
			var start = this.page * this.size;
			return $scope.orders.slice(start, start + this.size);
		},
		get countOrderconfirm(){
			return Math.ceil(1.0 * $scope.orders.length / this.size);
		},
		first(){
			this.page = 0;
		},
		prev(){
			this.page--;
			if(this.page < 0){
				this.last();
			}
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		last(){
			this.page = this.count - 1;
		}
	}
    
});