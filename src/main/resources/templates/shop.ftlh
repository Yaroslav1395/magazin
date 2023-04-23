<#import "parts/common.ftlh" as c>
<#import "parts/shopBanner.ftlh" as b>
<#import "parts/pager.ftlh" as p>
<@c.page>
    <@b.page>
    </@b.page>
    <section id="product1" class="section-p1">
        <div id="catalog">
            <ul id="catalog-ul">
                <#list categories as category>
                    <li id="${category.id}"><a href="#">${category.name}</a></li>
                </#list>
            </ul>
        </div>
        <#if products??>
            <div id="shop-products">
                <div class="pro-container">
                    <#list products.content as product>
                        <div id=${product.id} class="pro" onclick="window.location.href='sproduct.html';">
                            <img src="/${product.productImageDto.imageOne}" alt="${product.name}">
                            <div class="des">
                                <span>${product.categoryDto.name}</span>
                                <h4>${product.name}</h4>
                                <div class="star">
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                </div>
                                <h4>${product.price} сом</h4>
                                <div class="btn"><a href="#"><i class="fa-solid fa-cart-shopping"></i></a></div>
                            </div>
                        </div>
                    </#list>
                </div>
                <@p.pager url products>
                </@p.pager>
            </div>
        <#else>
            <div id="shop-catalog">
                <#list categories as category>
                     <div id="${category.id}" class="cell">
                        <div class="cel-image-wrapper">
                            <img src="/${category.image}">
                        </div>
                        <h3>${category.name}</h3>
                     </div>
                </#list>
            </div>
        </#if>
    </section>
</@c.page>