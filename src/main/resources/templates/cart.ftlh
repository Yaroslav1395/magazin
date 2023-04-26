<#import "parts/common.ftlh" as c>
<#import "parts/cartBanner.ftlh" as b>
<#import "parts/cartForm.ftlh" as f>
<#import "parts/cartSubtotal.ftlh" as s>
<@c.page>
    <@b.page>
    </@b.page>
    <section id="cart" class="section-p1">
        <table width="100%">
            <thead>
                <tr>
                    <td>Удалить</td>
                    <td>Картинка</td>
                    <td>Название</td>
                    <td>Цена</td>
                    <td>Количество</td>
                    <td>Сумма</td>
                </tr>
            </thead>
            <tbody id="tableBody">
                <#if products??>
                    <#list products as product>
                        <tr id="line">
                            <td id="${product.id}"><a href="#"><i id="remove" class="fa-regular fa-circle-xmark"></i></a></td>
                            <td><img src="/${product.productImageDto.imageOne}" alt=""></td>
                            <td>${product.name}</td>
                            <td id="price">${product.price} сом</td>
                            <td><input id="amount" type="number" min="0" value="1"></td>
                            <td id="sum">${product.price} сом</td>
                        </tr>
                    </#list>
                <#else>
                    <div class="empty">
                        <h3>В корзине пусто</h3>
                    </div>
                </#if>
            </tbody>
        </table>
    </section>
    <@f.page>
        <#if coupon??>
            <#if coupon.active == true>
                <div>
                    <p>Вы использовали купон, который предоставляет скидку ${coupon.percent} %.</p>
                    <p>Купон активен до ${coupon.activeUntil}</p>
                </div>
            </#if>
        </#if>
    </@f.page>
    <@s.page>
        <#if coupon??>
            <#if coupon.active == true>
                ${coupon.percent} %
            </#if>
        </#if>
    </@s.page>
</@c.page>