const app = angular.module("shopping-cart-app",[])
app.controller("ctrl",function ($scope,$http) {

    // Mananer shopping-cart
    $scope.cart = {
        address: "",
        products : [],
        items:[],
        account_id:"",

        //Add item
        add(id){

		var item=this.items.find(item => item.id==id);
		if(item){
            alert("Sản phẩm đã thêm giỏ hàng thành công")
			item.qty++;
			this.saveToLocalStorage();
            location.reload();
		}
		else{	
                $http.get(`/rest/products/${id}`).then(resp =>{
                    if(resp.data.amount==1){
                        alert("Sản phẩm hiện đang hết hàng")
                    }
                    else {
                        alert("Sản phẩm đã thêm giỏ hàng thành công")
                        resp.data.qty = 1;
                        this.items.push(resp.data);
                        this.saveToLocalStorage();
                        location.reload();
                    }
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
        },

        getId(){
            var keyword = $("#username").text();
            $http.get('/rest/accounts/getId?username='+keyword).then(resp => {
              this.account_id= resp.data.id;
            }).catch(error => {
                console.log("Error", error);
            });
        },

        purchase() {
            const order = {
                createDate: new Date(),
                address: this.address,
                status: false,
                account: {id: this.account_id},
                get orderDetails() {
                    return $scope.cart.items.map(item => {
                        $http.put('/rest/products/updateByOrder?id='+item.id).then(resp => {
                        }).catch(error => {
                            console.log("Error", error);
                        });
                        console.log(item.id)
                        return {
                            product: {id: item.id},
                            quantity: item.qty
                        }
                    });
                }
            };
            if (!order.address) {
                alert("Ban chưa nhập địa chỉ")
            }
            else {
                // Thuc hien dat hang
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
    }

    $scope.cart.loadFromLocalStorage();
    $scope.cart.getId();






});