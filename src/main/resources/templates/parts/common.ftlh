<#macro page>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Electronic shop</title>
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    </head>
    <body>
        <section id="header">
            <a href="#"><img src="/images/logo.png" class="logo" alt="Logo"></a>

            <div>
                <ul id="navbar">
                    <li><a class="active link" href="http://localhost:8080/quantum/main">Главная</a></li>
                    <li><a class="link" href="http://localhost:8080/quantum/shop">Магазин</a></li>
                    <li><a class="link" href="http://localhost:8080/quantum/blog">Блог</a></li>
                    <li><a class="link" href="http://localhost:8080/quantum/about">О нас</a></li>
                    <li><a class="link" href="http://localhost:8080/quantum/contact">Контакты</a></li>
                    <li id="lg-bag"><a id = "cartLink" class="link" href="#"><i class="fa-solid fa-cart-shopping"></i></a></li>
                    <a class="link" href="#" id="close"><i class="fa-solid fa-xmark"></i></a>
                </ul>
            </div>
            <div id="mobile">
                <a href="cart.html"><i class="fa-solid fa-cart-shopping"></i></a>
                <i id="bar" class="fas fa-outdent"></i>
            </div>
        </section>

        <#nested>

        <section id="newsletter" class="section-p1 section-m1">
            <div class="newstext">
                <h4>Подпишись чтобы быть в курсе событий</h4>
                <p>
                    Получай письма на почту о наших <span>специальных предложениях</span>
                </p>
            </div>
            <div class="form">
                <@sf.form action="http://localhost:8080/quantum/main/subscribe" method="post" id="subscribe-form">
                    <@sf.input type="email" path="email" placeholder="Your email address"/>
                    <@sf.errors path="email"/>
                    <button class="normal" type="submit">Subscribe</button>
                </@sf.form>
            </div>
        </section>

         <footer class="section-p1">
            <div class="col">
                <img class="logo" src="/images/logo.png" alt="">
                <h4>Контакты</h4>
                <p><strong>Адрес: </strong> г.Бишкек ул. Советская д.1 кв.1</p>
                <p><strong>Телефон: </strong>+996 554 60 11 90</p>
                <p><strong>Рабочее время: </strong>10:00 - 18:00, Пн - Пт</p>
                <div class="follow">
                    <h4>Следи за нами</h4>
                    <div class="icon">
                        <i class="fa-brands fa-facebook-f"></i>
                        <i class="fa-brands fa-twitter"></i>
                        <i class="fa-brands fa-square-instagram"></i>
                        <i class="fa-brands fa-telegram"></i>
                    </div>
                </div>
            </div>
            <div class="col">
                <h4>О нас</h4>
                <a href="#">Компания</a>
                <a href="#">Доставка</a>
                <a href="#">Политика приватности</a>
                <a href="#">Комерчесике предложения</a>
                <a href="#">Свяжитесь с нами</a>
            </div>
            <div class="col">
                <h4>Мой аккаунт</h4>
                <a href="#">Главная</a>
                <a href="#">Магазин</a>
                <a href="#">О нас</a>
                <a href="#">Комерчесике предложения</a>
                <a href="#">Контакты</a>
            </div>
            <div class="col install">
                <h4>Установить приложение</h4>
                <p>Из App Store или Google Play</p>
                <div class="row">
                    <img src="/images/pay/app.jpg" alt="">
                    <img src="/images/pay/play.jpg" alt="">
                </div>
                <p>Защищенная оплата</p>
                <img src="/images/pay/pay.png" alt="">
            </div>
         </footer>
         <div class="subscribe-request active" id="subscribe-request">
            <div class="request-content">
                <div class="close" id="close-message"><i class="fa-solid fa-xmark"></i></div>
                <p></p>
            </div>
         </div>
         <script src="/js/script.js"></script>
    </body>
</html>
</#macro>