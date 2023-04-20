<#import "parts/common.ftlh" as c>
<#import "parts/shopBanner.ftlh" as b>
<@c.page>
    <@b.page>
    </@b.page>
    <section id="product1" class="section-p1">
        <div id="catalog">
            <ul>
                <#list categories as category>
                    <li><a href="http://localhost:8080/${category.src}.html">${category.name}</a></li>
                </#list>
            </ul>
        </div>
        <div id="shop-catalog">
            <#list categories as category>
                 <div id="cell">
                    <div class="cel-image-wrapper">
                        <img src="/${category.image}">
                    </div>
                    <h3>${category.name}</h3>
                 </div>
            </#list>
        </div>
    </section>
</@c.page>