/**Закрыть сообщение**/
const messageClose = document.getElementById("message-close");
if(messageClose != null){
    messageClose.addEventListener("click", () => {
        let messageWrapper = document.getElementById("message-wrapper");
        messageWrapper.style.display = "none";
    })
}

/**Показать скрыть пароль регистрации**/
const showRegistrationBtn = document.getElementById("showRegistration");
console.log(showRegistrationBtn);
if(showRegistrationBtn != null){
    showRegistrationBtn.addEventListener("click", () => {
        const passwordInput = document.getElementById("password");
        if(passwordInput.type == "password"){
            passwordInput.type = "text";
        }else{
            passwordInput.type = "password";
        }
    })
}
/**Закрыть страницу регистрации**/
const closeLogin = document.getElementById("close-registration");
if(closeLogin != null){
    closeLogin.addEventListener('click', () => {
          window.location.href = '/quantum/main'
    })
}

/**Очистка формы подписки**/
const input = document.getElementById("email");
if(input != null){
    input.value = "";
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

        window.location.href = 'http://localhost:8080/quantum/shop/category/' +
                                                            categoryId + "?pageNumber=0&sortDir=asc&sortField=id";
    });
};

/*Очистка поисковой формы*/
const clearFormBtn = document.getElementById('clearFormBtn');
if(clearFormBtn != null){
    clearFormBtn.addEventListener("click", (e) => {
        e.preventDefault();
        var searchInput = document.getElementById('searchInput');
        searchInput.value="";
    })
}

/**Работа слaйдера*/
const rangeInput = document.querySelectorAll(".range-input input");
const priceInput = document.querySelectorAll(".price-input input");
const progress = document.querySelector(".slider .progress");
let priceGap;
let minVal;
let maxVal;
console.log(priceInput);
if(priceInput != null && priceInput.length > 0){
    let minVal = parseInt(priceInput[0].value);
    let maxVal = parseInt(priceInput[1].value);

    if(progress != null){
        progress.style.right = 100 - (maxVal / rangeInput[1].max) * 100 + "%";
        progress.style.left = (minVal / rangeInput[0].max) * 100 + "%";
    }

    if(rangeInput[1].max > 50000){
        priceGap = 5000;
    }else if(rangeInput[1].max > 400000){
        priceGap = 50000;
    }else{
        priceGap = 500;
    }

    if(priceInput !=null){
        priceInput.forEach(input => {
            input.addEventListener("input", e => {
                let minVal = parseInt(priceInput[0].value);
                let maxVal = parseInt(priceInput[1].value);

                if((maxVal - minVal >= priceGap) && maxVal <= rangeInput[1].value){
                    if(e.target.className === "input-min"){
                        rangeInput[0].value = minVal;
                        progress.style.left = (minVal / rangeInput[0].max) * 100 + "%";
                    }else{
                        rangeInput[1].value = maxVal;
                        progress.style.right = 100 - (maxVal / rangeInput[1].max) * 100 + "%";
                    }
                }
            });
        });

        rangeInput.forEach(input => {
            input.addEventListener("input", e => {
                let minVal = parseInt(rangeInput[0].value);
                let maxVal = parseInt(rangeInput[1].value);

                if(maxVal - minVal < priceGap){
                    if(e.target.className === "range-min"){
                        rangeInput[0].value = maxVal - priceGap;
                    }else{
                        rangeInput[1].value = minVal + priceGap;
                    }
                }else{
                    priceInput[0].value = minVal;
                    priceInput[1].value = maxVal;
                    progress.style.left = (minVal / rangeInput[0].max) * 100 + "%";
                    progress.style.right = 100 - (maxVal / rangeInput[1].max) * 100 + "%";
                }
            });
        });
    };
};


/*Установить текущий урл каждому продукту*/
const currentUrlInputs = document.querySelectorAll(".currentUrlInput");
if(currentUrlInputs != null) {
    currentUrlInputs.forEach(input => {
        input.value = window.location.href;
    })
}

/*Переход к информации о продукте при клике на карточку или добавление в корзину
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
};*/
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
if(tableBody != null){
    orderRecalculation(tableBody);
    productRecalculation(tableBody);
}

/*Функция пересчета заказа*/
function orderRecalculation(tableBody){
    var discountTr = document.getElementById("discount");
    var discount = discountTr.textContent;
    console.log(discount);
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
    var productTotalInput = document.getElementById("orderTotal");
    console.log(productTotalInput);
    productTotalInput.value = finalSum;
    document.getElementById("productSum").textContent =
                                        (orderSum+'').replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1 ') + ' сом';
    document.getElementById("orderSum").textContent =
                                            (finalSum+'').replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1 ') + ' сом';
}

function productRecalculation(tableBody){
    tableBody.addEventListener("change", (event) => {
        var target = event.target;
        var trEvent = target.closest('tr');
        var productId = trEvent.children[0].id;
        var amountProduct = event.target.value;
        location.assign('/quantum/cart/changeAmount?productId=' + productId + '&amountProduct=' + amountProduct);
    })
}

