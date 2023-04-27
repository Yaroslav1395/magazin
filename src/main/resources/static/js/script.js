/*Отправка подписки на сервер*/
const subscribeForm = document.getElementById("subscribe-form");

subscribeForm.addEventListener("submit", (event) => {
    event.preventDefault();
    subscribeFormSubmitHandler(event.target);
});

function subscribeFormSubmitHandler(loginForm){
    const formData  = new FormData(loginForm);
    sendSubscribeData(formData);
};

function sendSubscribeData(formData){
    const email = formData.get("email");

    axios.post("http://localhost:8080/quantum/main", email,
        {headers: {'Content-Type': 'multipart/form-data'}
    })
        .then(res => console.log(res))
        .catch(err => console.log(err));
};

/*Кнопка открытия и закрытия меню для экранов до 800px*/
const bar = document.getElementById('bar');
const close = document.getElementById('close');
const nav = document.getElementById('navbar');

if(bar){
    bar.addEventListener('click', () => {
        nav.classList.add('active');
    })
};

if(close){
    close.addEventListener('click', () => {
        nav.classList.remove('active');
    })
};

/*Изменение подсветки текущей ссылки в header nav*/
var headerUl = document.getElementById("navbar");
var headersLinks = headerUl.getElementsByClassName("link");
var currentUrl = window.location.href;

for (var i = 0; i < headersLinks.length; i++) {
    headersLinks[i].classList.remove("active");

    var linkLength = headersLinks[i].href.length;
    var splitUrl = currentUrl.substring(0, linkLength);

    if(headersLinks[i].href == splitUrl){
        headersLinks[i].classList.add("active");
    };
};

if(currentUrl.includes('cart/products')){
    var a = document.getElementById('cartLink');
    a.classList.add("active");
}

/*Запрос на получение продуктов по категории*/
const shopCatalog = document.getElementById('shop-catalog');
if(shopCatalog != null){
    shopCatalog.addEventListener("click", (event) => {
        var target = event.target;
        var productCell = target.closest(".cell");
        var categoryId = productCell.id;

        window.location.href = 'http://localhost:8080/quantum/shop/category/' + categoryId + "?products=0";
    });
};

/*Запрос на получение продуктов по категории из боковой навигации*/
const catalog = document.getElementById('catalog-ul');

if(catalog != null){
    catalog.addEventListener("click", (event) => {
        var target = event.target;
        var li = target.closest("li");
        var categoryId = li.id;
        window.location.href = 'http://localhost:8080/quantum/shop/category/' + categoryId + "?products=0";
    });
};

/*Переход к информации о продукте при клике на карточку или добавление в корзину*/
const proContainer = document.getElementById('pro-container');
if(proContainer != null) {
    let cart = [];
    proContainer.addEventListener("click", (event) => {
        event.preventDefault();
        var target = event.target;
        if(target.className == 'pro-container'){
            return;
        };

        var proCard = target.closest('.pro');
        var productId = proCard.id;

        if(target.className == 'btn' || target.className == 'fa-solid fa-cart-shopping' || target == 'a'){
            if(cart.length == 0){
                cart.push(Number(productId));
                console.log(cart);
            };

            if(cart.some((x) => x == productId)){
                console.log("Уже есть");
            }else{
                cart.push(Number(productId));
                var json = JSON.stringify(cart);
                localStorage.setItem('cart', json);
            };

        }else{
            window.location.href = 'http://localhost:8080/quantum/sProduct/' + productId;
        };
    });
};
/*Смена картинок в продукте*/
var mainImg = document.getElementById("MainImg");
var smallImg = document.getElementsByClassName("small-img");
if(mainImg != null && smallImg != null) {
    smallImg[0].onclick = function() {
        mainImg.src = smallImg[0].src;
    };
    smallImg[1].onclick = function() {
        mainImg.src = smallImg[1].src;
    };
    smallImg[2].onclick = function() {
        mainImg.src = smallImg[2].src;
    };
    smallImg[3].onclick = function() {
        mainImg.src = smallImg[3].src;
    };
};

/*Передача данных о продукте при запросе корзины*/
const headerNuv = document.getElementById('navbar');

headerNuv.addEventListener("click", (event) => {
    var target = event.target;
    var ids;
    if(target.className == 'fa-solid fa-cart-shopping'){
        ids = localStorage.getItem('cart');
        console.log(ids);
        str = ids.slice(1, ids.length - 1);
        console.log(str);
        if(str != null && str.length > 2){
            window.location.href = 'http://localhost:8080/quantum/cart/products/' + str;
        }else{
            window.location.href = 'http://localhost:8080/quantum/cart/products';
        }

    };
});

/*Изменение общей стоимости продукта исходя из количества и удаление продукта*/
const tableBody = document.getElementById("tableBody");
console.log(tableBody);
if(tableBody != null){
    orderRecalculation(tableBody);
    tableBody.addEventListener("click", (event) => {
        event.preventDefault();

        var tr = event.target.closest("#line");

        if(event.target.id == "remove"){
            var productId = tr.children[0].id;
            removeProduct(productId)
            tr.remove();
            orderRecalculation(tableBody);
            return;
        }
        var sumTd = tr.children[5];
        var inputValue = tr.children[4].firstElementChild.value;
        var priceText = tr.children[3].textContent;
        var price = priceText.substring(0, priceText.search(" сом"));
        var price2 = price.replaceAll(/\xA0/g, '');
        var sum = parseInt(price2) * parseInt(inputValue);
        sumTd.textContent = (sum+'').replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1 ') + ' сом';
        orderRecalculation(tableBody);
    });
}

/*Функция удаления продукта из локального хранилища*/
function removeProduct(id){
    var cart = JSON.parse(localStorage.getItem("cart"));
    for(let i = 0; i < cart.length; i++){
        if(cart[i] == id){
            cart.splice(i, 1);
            break;
        };
    };

    var json = JSON.stringify(cart);
    localStorage.setItem('cart', json);
};

/*Функция пересчета заказа*/
function orderRecalculation(tableBody){
    var discountTr = document.getElementById("discount");
    var discount = 0;
    if(discountTr.textContent != null){
        discount = discountTr.textContent.substring(0, discountTr.textContent.search(" %"));
        console.log(discountTr);
    }
    var trElements = tableBody.getElementsByTagName("tr");
    var orderSum = 0;
    for(let i = 0; i < trElements.length; i++){
        var td = trElements[i].children[5];
        var tdStr =  td.textContent;
        var productSumStr = tdStr.substring(0, tdStr.search(" сом"));
        var productSum = productSumStr.replaceAll(/\xA0/g, '').replaceAll(' ', '');

        orderSum = orderSum + parseInt(productSum);
    }
    var finalSum = 0;
    if(discount != 0){
        finalSum = orderSum - (orderSum * discount / 100);
    }else{
        finalSum = orderSum;
    };

    document.getElementById("productSum").textContent =
                                        (orderSum+'').replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1 ') + ' сом';
    document.getElementById("orderSum").textContent =
                                            (finalSum+'').replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1 ') + ' сом';
}

/*Отправка купона*/
const couponForm = document.getElementById("couponForm");
console.log(couponForm)
if(couponForm != null){
    couponForm.addEventListener("submit", (event) => {
        event.preventDefault();
        var formData  = new FormData(event.target);
        var coupon = formData.get("coupon");
        console.log(coupon);
        var jsonIds = localStorage.getItem('cart');
        var ids = jsonIds.slice(1, jsonIds.length - 1);
        console.log(ids);
        window.location.href = 'http://localhost:8080/quantum/cart/products/' + ids + '/coupon/' + coupon;
    });
}
