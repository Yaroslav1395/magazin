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
    if(headersLinks[i].href == currentUrl){
        headersLinks[i].classList.add("active");
    };
};

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
        console.log(li);
        var categoryId = li.id;
        console.log(categoryId);
        window.location.href = 'http://localhost:8080/quantum/shop/category/' + categoryId + "?products=0";
    });
};


