<#import "parts/common.ftlh" as c>
<#import "parts/cartBanner.ftlh" as b>
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
            <tbody>
                <#if products??>
                    <#list products as product>
                        <tr>
                            <td><a href="#"><i class="fa-regular fa-circle-xmark"></i></a></td>
                            <td><img src="/${product.productImageDto.imageOne}" alt=""></td>
                            <td>${product.name}</td>
                            <td>${product.price} сом</td>
                            <td><input type="number" value="1"></td>
                            <td>сом</td>
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
</@c.page>