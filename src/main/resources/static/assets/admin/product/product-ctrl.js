app.controller("product-ctrl", function($scope, $http){
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};
	
	
	$scope.initialize = function(){
		// load product
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
		});
		//load categories
		$http.get("/rest/categories").then(resp => {
			$scope.cates = resp.data;
		});
	}

	$scope.initialize();
	
	$scope.reset = function(){
		$scope.form = {
			image1:"1.jpg",
			active: true,
		};
	}
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}
	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post('/rest/products',item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới sản phẩm thành công");
		}).catch(error => {
			alert("Lỗi thêm mới sản phẩm");
			console.log("Error", error);
		});
	}
	$scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật sản phẩm thành công");
		}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
			console.log("Error", error);
		});
	}
	$scope.delete = function(item){
		$http.delete(`/rest/products/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa sản phẩm thành công");
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);
		});
	}

	$scope.imageChanged = function(event){
		$scope.selectedFile = event.target.files[0];
		$scope.form.image1 = $scope.selectedFile.name;

	}
	$scope.imageChanged2 = function(event){
		$scope.selectedFile2 = event.target.files[0];
		$scope.form.image2 = $scope.selectedFile2.name;
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