app.controller("order-ctrl", function($scope, $http, $location) {
	
	$scope.items = [];

	$scope.initialize = function(){
		// load product
		$http.get("/rest/orders/list").then(resp => {
			$scope.items = resp.data;
		});

	}

	$scope.initialize();
    
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
    
});