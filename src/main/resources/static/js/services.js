app.factory('productService', function($q, $http) {
    return {
        getAllProducts: function() {
            var def = $q.defer();
            $http.get(ws_url + '/product')
                .then(function(payload) {
                    def.resolve(payload.data);
                });
                return def.promise;
        }
    }
});

app.factory('cartService', function($q, $http) {
    return {
        getAllCartItems: function(token) {
            var def = $q.defer();
            $http.get(ws_url + '/cart/' + token)
                .then(function(payload) {
                    def.resolve(payload.data);
                });
                return def.promise;
        },
        saveItemToCart: function(cart_item) {
            var def = $q.defer();
            var conf = {
                method: 'POST',
                url: ws_url + '/cart',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: cart_item
            }
            $http(conf).then(function(payload) {
                def.resolve(payload.data);
            });
            return def.promise;
        },
        deleteCartItem: function(token, brand_name) {
            var def = $q.defer();
            var conf = {
                method: 'DELETE',
                url: ws_url + '/cart/' + token + '/' + brand_name
            }
            $http(conf).then(function(payload) {
                def.resolve(payload.data);
            });
            return def.promise;
        }
    }
});