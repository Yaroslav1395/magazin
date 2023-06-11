<#import "parts/head.ftlh" as h>
<#import "parts/header.ftlh" as hr>
<#import "parts/bannerCart.ftlh" as bc>
<#import "parts/cartForm.ftlh" as cf>
<#import "parts/cartSubtotal.ftlh" as cs>
<#import "parts/subscribe.ftlh" as s>
<#import "parts/footer.ftlh" as f>
<#import "parts/message.ftlh" as m>
<@h.page>
    <@hr.page>
        <#if userRoles??>
            <#list userRoles as role>
                <#if role.role == 'ROLE_ADMIN'>
                    <li><a class="link" href="/quantum/admin">Администрирование</a></li>
                </#if>
            </#list>
        </#if>
        <div class="icon-btns">
            <#if userRoles??>
                <li id="lg-bag"><a href="/quantum/cart"><i class="fa-solid fa-cart-shopping"></i></a></li>
                <li id="lg-bag"><a href="/quantum/logout"><i class="fa-solid fa-right-from-bracket"></i></a></li>
            <#else>
                <li id="lg-bag"><a href="/quantum/cart"><i class="fa-solid fa-cart-shopping"></i></a></li>
                <li id="lg-bag"><a href="/quantum/registration"><i class="fa-solid fa-user-plus"></i></a></li>
                <li id="lg-bag"><a href="/quantum/login"><i class="fa-solid fa-right-to-bracket"></i></a></li>
            </#if>
        </div>
    </@hr.page>
    <section id="cart" class="section-p1">
            <table width="100%">
                <thead>
                    <tr>
                        <td>Картинка</td>
                        <td>Название</td>
                        <td>Цена</td>
                        <td>Количество</td>
                        <td>Сумма</td>
                    </tr>
                </thead>
                <tbody id="tableBody">
                    <#if order.products??>
                        <#list order.products as product>
                            <tr id="line">
                                <td><img src="/${product.productImages[0].src}" alt="${product.name}"></td>
                                <td id="${product.id}">${product.name}</td>
                                <td id="price">${product.price} сом</td>
                                <#assign productSum = product.amount * product.price>
                                <td>${product.amount}</td>
                                <td id="sum">${productSum} сом</td>
                            </tr>
                        </#list>
                    <#else>
                        <div class="empty">
                            <h3>В корзине пусто</h3>
                        </div>
                    </#if>
                </tbody>
            </table>

            <div>
                <p>Сумма заказа: ${order.total}</p>
                <p>Время заказа: ${order.dateTime}</p>
                <p>Имя: ${order.user.firstName}</p>
                <p>Фамилия: ${order.user.lastName}</p>
            </div>
        </section>
    <@f.page>
    </@f.page>
</@h.page>