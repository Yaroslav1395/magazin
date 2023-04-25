/*Отправка подписки на сервер*/
const loginForm = document.getElementById("subscribe-form");

loginForm.addEventListener("submit", (event) => {
    console.log("loginForm");
    event.preventDefault();
    loginFromSubmitHandler(event.target);
});
function loginFromSubmitHandler(loginForm){
    const formData  = new FormData(loginForm);
    sendLoginData(formData);
};
function sendLoginData(formData){
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

if(currentUrl.includes('cart/products/')){
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
        window.location.href = 'http://localhost:8080/quantum/cart/products/' + str;
    };
});




