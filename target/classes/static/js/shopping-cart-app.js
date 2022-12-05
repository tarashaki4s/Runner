const app = angular.module("shopping-cart-app",[])
app.controller("ctrl",function ($scope,$http) {

    // Mananer shopping-cart
    $scope.cart = {
        products : [],
        items:[],

        //Add item
        add(id){
	alert("Sản phẩm đã thêm giỏ hàng thành công")

		var item=this.items.find(item => item.id==id);
		if(item){
			item.qty++;
			this.saveToLocalStorage();
		}
		else{	
			$http.get(`/rest/products/${id}`).then(resp =>{
				resp.data.qty=1;
				this.items.push(resp.data);
				this.saveToLocalStorage();
			})
		} 
			
            
        },
        //Delete item
        remove(id){
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index,1);
            this.saveToLocalStorage();
        },
        //Delete all item
        clear(){
            this.items = [];
            this.saveToLocalStorage();
        },
        //Charge of a product
        amt_of(item){},
        //Total product
        get count(){
            return this.items
                .map(item => item.qty)
                .reduce((total,qty)=>total += qty,0);
        },
        //Total charge of product
        get amount(){
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total,qty)=>total += qty,0);
        },
        //Save to localstorage
        saveToLocalStorage(){
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart",json);
        },
        //Read data from localstorage
        loadFromLocalStorage(){
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json):[];
        },

        search(){
            var keyword = angular.copy($scope.form.keyword);
            $http.get('/rest/products/search?name='+keyword).then(resp => {
                this.products = resp.data;
            }).catch(error => {
                console.log("Error", error);
            });
        }
    }

    $scope.cart.loadFromLocalStorage();





   $scope.order = {
        createDate: new Date(),
        address: "",
        account: { username: $("#username").text() },
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price,
                    quantity: item.qty
                }
            });
        },
        purchase() {
            var order = angular.copy(this);
            //Thuc hien dat hang
            $http.post("/rest/orders", order).then(resp => {
                alert("Đặt hàng thành công !");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("Lỗi!");
                console.log(error);
            })
        }
    }
});