<#macro page>
<div id="subtotal">
        <h3>Сумма заказа</h3>
        <table>
            <tr>
                <td>Общая сумма</td>
                <td id = "productSum">$ 335</td>
            </tr>
            <tr>
                <td>Доставка</td>
                <td>Бесплатно</td>
            </tr>
            <tr>
                <td>Скидка</td>
                    <td id="discount"><#nested> %</td>
            </tr>
            <tr>
                <td><strong>Сумма</strong></td>
                <td><strong id = "orderSum">$ 335</strong></td>
            </tr>
        </table>

        <form action="/quantum/cart/newOrder" method="post">
            <input id="orderTotal" type="hidden" value="" name= "total"/>
            <button class="normal">Оформить заказ</button>
        </form>

</div>
</section>
</#macro>