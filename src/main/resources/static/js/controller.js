app.controller('mainController', function($scope, $window, $cookies, productService, cartService) {

	$scope.products = [];
	$scope.product = {};
	$scope.cart = [];
	$scope.cart_item = {};
	$scope.cart_empty = true;
	$scope.cart_count = 0;
	$scope.token = {};
    $scope.grand_total = 0;
	$scope.expireDate = new Date();

	this.$onInit = function() {

        // set cookie expiration to June 15, 2020
        $scope.expireDate.setTime(1592179200000);

        // load products
        $scope.loadProducts();

        // retrieve saved generated session token
		if($window.localStorage.getItem('__session_id')) {
			$scope.token = $window.localStorage.getItem('__session_id');
			$scope.loadCartItems($scope.token);
			$scope.cart_empty = false;
		}
		else {
            $scope.token = $scope.generateToken();
		    $window.localStorage.setItem('__session_id', $scope.token);
		}
	}

	$scope.loadProducts = function() {
        productService.getAllProducts()
            .then(function(data) {
                $scope.products = data.payload;
            });
	}

	$scope.loadCartItems = function() {
        cartService.getAllCartItems($scope.token)
            .then(function(data) {
                $scope.cart = data.payload;
            })
            .then(function() {
                $scope.cart_count = $scope.cart.length;
                $scope.grand_total = $scope.getGrandTotal($scope.cart);
            });
	}

	$scope.addItemToCart = function(product) {
        angular.element(document.getElementById('btn-cart'))[0].disabled = true;
	    var item = {
	        'token': $scope.token,
	        'base_price': product.base_price,
	        'brand_name': product.brand_name,
	        'description': product.description,
	        'prod_id': product.prod_id,
	        'prod_name': product.prod_name,
	        'item_count': 1,
	        'total_amount': product.base_price
	    }
        cartService.saveItemToCart(item)
            .then(function(payload) {
                console.log('Saving item to cart');
            })
            .then(function() {
                $scope.loadCartItems();
            })
            .then(function() {
                alert('Item saved to cart');
                angular.element(document.getElementById('btn-cart'))[0].disabled = false;
            });

	}

	$scope.deleteCartItem = function(token, brand_name) {
	    cartService.deleteCartItem(token, brand_name)
	        .then(function(payload) {
	            console.log('Deleting item from cart');
	        })
	        .then(function() {
	            $scope.loadCartItems();
	        })
	        .then(function() {
	            $scope.grand_total = $scope.getGrandTotal($scope.cart);
	        });
	}

	$scope.generateToken = function() {
        var result = '';
        var chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        var length = 30;
        for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
        return result;
	}

	$scope.getGrandTotal = function(cart) {
	    var t = 0;
	    for(var i = 0; i < cart.length; i ++) {
	        t = t + parseFloat(cart[i].total_amount);
	    }
        return t;
	}


});
