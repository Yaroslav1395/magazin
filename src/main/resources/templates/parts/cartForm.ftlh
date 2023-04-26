<#macro page>
<section id="cart-add" class="section-p1">
    <div id="coupon">
        <h3>Использовать купон</h3>
        <div>
            <form id="couponForm">
                <input type="text" name="coupon" placeholder="Введите номер купона">
                <button type="submit" class="normal">Использовать</button>
            </form>
        </div>
        <#nested>
    </div>
</#macro>