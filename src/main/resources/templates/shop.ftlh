<#import "parts/head.ftlh" as h>
<#import "parts/header.ftlh" as hr>
<#import "parts/bannerShop.ftlh" as b>
<#import "parts/pager.ftlh" as p>
<#import "parts/footer.ftlh" as f>
<#import "parts/subscribe.ftlh" as s>
<#import "parts/message.ftlh" as m>

<@h.page>
    <#if message??>
        <@m.page>
            <p>${message}</p>
        </@m.page>
    </#if>

    <@hr.page>
    </@hr.page>

    <@b.page>
    </@b.page>

    <section id="product1">
        <div >
            <div class="function">
                <div class="search">
                    <p><i class="fa-solid fa-magnifying-glass"></i></p>
                    <form id="searchProductForm" action="/quantum/shop/filter">
                        <input type="hidden" name="sortDir" value="asc" required>
                        <input type="hidden" name="sortField" value="id" required>
                        <input type="hidden" name="pageNumber" value="0" required>
                        <input type="hidden" name="minInputValue" value="#{minProductPrice}" required>
                        <input type="hidden" name="maxInputValue" value="#{maxProductPrice}" required>
                        <input id="searchInput" type="text" name="keyword" value=${keyword} required>
                        <button class="search-button" type="submit">Найти</button>
                        <button id="clearFormBtn" class="search-button">Очистить</button>
                    </form>
                </div>
                <div class="sort">
                    <div class="sort-buttons">
                        <p>Цена:</p>
                        <div>
                            <#if urlForPriceSortAsc?? && urlForPriceSortDesc??>
                                <a class="sort-button" href="${urlForPriceSortAsc}"><i class="fa-solid fa-arrow-up"></i></a>
                                <a class="sort-button" href="${urlForPriceSortDesc}"><i class="fa-solid fa-arrow-down"></i></a>
                            <#else>
                                <a class="sort-button" href="#"><i class="fa-solid fa-arrow-up"></i></a>
                                <a class="sort-button" href="#"><i class="fa-solid fa-arrow-down"></i></a>
                            </#if>
                        </div>
                        <p>Название:</p>
                        <div>
                            <#if urlForNameSortAsc?? && urlForNameSortDesc??>
                                <a class="sort-button" href="${urlForNameSortAsc}"><i class="fa-solid fa-arrow-up"></i></a>
                                <a class="sort-button" href="${urlForNameSortDesc}"><i class="fa-solid fa-arrow-down"></i></a>
                            <#else>
                                <a class="sort-button" href="#"><i class="fa-solid fa-arrow-up"></i></a>
                                <a class="sort-button" href="#"><i class="fa-solid fa-arrow-down"></i></a>
                            </#if>
                        </div>
                    </div>
                </div>
                <div class="price-input-wrapper">
                    <#assign url = "/quantum/shop/category/" + (categoryId??)?then(categoryId, 2) + "/limit">
                    <form action="${(urlForPriceFilter??)?then(urlForPriceFilter, url)}" method="GET">
                        <input type="hidden" name="sortDir" value="asc" required>
                        <input type="hidden" name="sortField" value="id" required>
                        <input type="hidden" name="pageNumber" value="0" required>
                        <input type="hidden" name="keyword" value="${keyword}" required>
                        <div class="price-input">
                            <div class="field">
                                <span>От</span>
                                <input type="number" min="0" name="minInputValue" class="input-min" value="#{minInputValue}">
                            </div>
                            <div class="field">
                                <span>До</span>
                                <input type="number" min="1" name="maxInputValue" class="input-max" value="#{maxInputValue}">
                            </div>
                            <button type="submit" class="price-input-btn"><i class="fa-solid fa-paper-plane"></i></button>
                        </div>
                        <div class="slider">
                            <div class="progress"></div>
                        </div>
                        <div class="range-input">
                            <input type="range" class="range-min" min="0" max="#{maxProductPrice - 1}" value="#{minInputValue}" step="20">
                            <input type="range" class="range-max" min="0" max="#{maxProductPrice}" value="#{maxInputValue}" step="20">
                        </div>
                    </div>
                    </form>
            </div>
        </div>
    </section>

    <section id="product1">
        <div id="catalog">
            <ul id="catalog-ul">
                <#list categories as category>
                    <li id="${category.id}">
                        <a href="http://localhost:8080/quantum/shop/category/${category.id}?pageNumber=0&sortDir=asc&sortField=name">
                            ${category.name}
                        </a>
                    </li>
                </#list>
            </ul>
        </div>
        <#if products??>
            <div id="shop-products">
                <div class="pro-container" id="pro-container">
                    <#list products.content as product>
                        <div id=${product.id} class="pro">
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
                                <div class="btn"><a class="link" href="#"><i class="fa-solid fa-cart-shopping"></i></a></div>
                            </div>
                        </div>
                    </#list>
                </div>

                <@p.pager urlForPagination products>
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
    <@s.page>
    </@s.page>
    <@f.page>
    </@f.page>
</@h.page>