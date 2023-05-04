<#import "parts/head.ftlh" as h>
<#import "parts/header.ftlh" as hr>
<#import "parts/bannerShop.ftlh" as b>
<#import "parts/pager.ftlh" as p>
<#import "parts/footer.ftlh" as f>

<#if pageNumber?? && keyword??>
<#assign urlAscPrice = "http://localhost:8080/quantum/shop/filter?pageNumber=${pageNumber}&sortDir=asc&sortField=price&keyword=${keyword}">
<#assign urlDescPrice = "http://localhost:8080/quantum/shop/filter?pageNumber=${pageNumber}&sortDir=desc&sortField=price&keyword=${keyword}">
<#assign urlAscName = "http://localhost:8080/quantum/shop/filter?pageNumber=${pageNumber}&sortDir=asc&sortField=name&keyword=${keyword}">
<#assign urlDescName = "http://localhost:8080/quantum/shop/filter?pageNumber=${pageNumber}&sortDir=desc&sortField=name&keyword=${keyword}">
</#if>
<#if categoryId?? && pageNumber?? && keyword??>
<#assign urlAscPrice = "http://localhost:8080/quantum/shop/category/${categoryId}?keyword=${keyword}&pageNumber=${pageNumber}&sortDir=asc&sortField=price">
<#assign urlDescPrice = "http://localhost:8080/quantum/shop/category/${categoryId}?keyword=${keyword}&pageNumber=${pageNumber}&sortDir=desc&sortField=price">
<#assign urlAscName = "http://localhost:8080/quantum/shop/category/${categoryId}?keyword=${keyword}&pageNumber=${pageNumber}&sortDir=asc&sortField=name">
<#assign urlDescName = "http://localhost:8080/quantum/shop/category/${categoryId}?keyword=${keyword}&pageNumber=${pageNumber}&sortDir=desc&sortField=name">
</#if>


<@h.page>

    <@hr.page>
    </@hr.page>

    <@b.page>
    </@b.page>

    <section id="product1">
        <div class="container">
            <div class="function">
                <div class="search">
                    <p>Поиск</p>
                    <form id="searchProductForm" action="/quantum/shop/filter">
                        <input type="hidden" name="sortDir" value="asc" required>
                        <input type="hidden" name="sortField" value="id" required>
                        <input type="hidden" name="pageNumber" value="0" required>
                        <input id="searchInput" type="text" name="keyword" value=${keyword} required>
                        <button class="search-button" type="submit">Найти</button>
                        <button id="clearFormBtn" class="search-button">Очистить</button>
                    </form>
                </div>
                <div class="sort">
                    <div class="sort-buttons">
                        <p>Цена:</p>
                        <div>
                            <#if urlAscPrice?? && urlAscName??>
                                <a class="sort-button" href="${urlAscPrice}"><i class="fa-solid fa-arrow-up"></i></a>
                                <a class="sort-button" href="${urlAscPrice}"><i class="fa-solid fa-arrow-down"></i></a>
                            <#else>
                                <a class="sort-button" href="#"><i class="fa-solid fa-arrow-up"></i></a>
                                <a class="sort-button" href="#"><i class="fa-solid fa-arrow-down"></i></a>
                            </#if>
                        </div>
                        <p>Название:</p>
                        <div>
                            <#if urlAscPrice?? && urlAscName??>
                                <a class="sort-button" href="${urlAscName}"><i class="fa-solid fa-arrow-up"></i></a>
                                <a class="sort-button" href="${urlDescName}"><i class="fa-solid fa-arrow-down"></i></a>
                            <#else>
                                <a class="sort-button" href="#"><i class="fa-solid fa-arrow-up"></i></a>
                                <a class="sort-button" href="#"><i class="fa-solid fa-arrow-down"></i></a>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <section id="product1">
        <div id="catalog">
            <ul id="catalog-ul">
                <#list categories as category>
                    <li id="${category.id}">
                        <a href="http://localhost:8080/quantum/shop/category/${category.id}?products=0&sortDir=asc&sortField=name&keyword=${category.name}">
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

    <@f.page>
    </@f.page>
</@h.page>