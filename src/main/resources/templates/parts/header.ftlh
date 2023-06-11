<#macro page>
    <section id="header">
        <a href="#"><img src="/images/logo.png" class="logo" alt="Logo"></a>
        <div>
            <ul id="navbar">
                <li><a class="active link" href="http://localhost:8080/quantum/main">Главная</a></li>
                <li><a class="link" href="http://localhost:8080/quantum/shop">Магазин</a></li>
                <li><a class="link" href="http://localhost:8080/quantum/blog">Блог</a></li>
                <li><a class="link" href="http://localhost:8080/quantum/about">О нас</a></li>
                <li><a class="link" href="http://localhost:8080/quantum/contact">Контакты</a></li>
                <#nested>

                <a class="link" href="#" id="close"><i class="fa-solid fa-xmark"></i></a>
            </ul>
        </div>
        <div id="mobile">
            <a href="cart.html"><i class="fa-solid fa-cart-shopping"></i></a>
            <i id="bar" class="fas fa-outdent"></i>
        </div>
    </section>
</#macro>