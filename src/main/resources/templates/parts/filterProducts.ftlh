<#macro page categoryId pageNumber keyword>
<#if categoryId == 0>
<assign urlAscPrice = "http://localhost:8080/quantum/shop/filter?pageNumber=${pageNumber}&sortDir=asc&sortField=price&keyword=${keyword}">
<assign urlDescPrice = "http://localhost:8080/quantum/shop/filter?pageNumber=${pageNumber}&sortDir=desc&sortField=price&keyword=${keyword}">
<assign urlAscName = "http://localhost:8080/quantum/shop/filter?pageNumber=${pageNumber}&sortDir=asc&sortField=name&keyword=${keyword}">
<assign urlAscName = "http://localhost:8080/quantum/shop/filter?pageNumber=${pageNumber}&sortDir=desc&sortField=name&keyword=${keyword}">
<#else>
<assign urlAscPrice = "http://localhost:8080/quantum/shop/category/${categoryId}?keyword=${keyword}&pageNumber=${pageNumber}&sortDir=asc&sortField=price&keyword=${keyword}">
<assign urlDescPrice = "http://localhost:8080/quantum/shop/category/${categoryId}?keyword=${keyword}&pageNumber=${pageNumber}&sortDir=desc&sortField=price&keyword=${keyword}">
<assign urlAscName = "http://localhost:8080/quantum/shop/category/${categoryId}?keyword=${keyword}&pageNumber=${pageNumber}&sortDir=asc&sortField=name&keyword=${keyword}">
<assign urlAscName = "http://localhost:8080/quantum/shop/category/${categoryId}?keyword=${keyword}&pageNumber=${pageNumber}&sortDir=desc&sortField=name&keyword=${keyword}">
</#if>

<section id="product1">
    <div class="container">
        <div class="function">
            <div class="search">
                <p>Поиск</p>
                <form id="searchProductForm" action="/quantum/shop/filter">
                    <input id="searchInput" type="text" name="keyword" value=${keyword} required>
                    <button class="search-button" type="submit">Найти</button>
                    <button id="clearFormBtn" class="search-button">Очистить</button>
                </form>
            </div>
            <div class="sort">
                <div class="sort-buttons">
                    <p>Цена:</p>
                    <div>
                        <a class="sort-button" href="${urlAscPrice}">
                            <i class="fa-sharp fa-solid fa-arrow-up"></i>
                        </a>
                        <a class="sort-button" href="${urlDescPrice}">
                            <i class="fa-solid fa-arrow-down"></i>
                        </a>
                    </div>
                    <p>Название:</p>
                    <div>
                        <a class="sort-button" href="${urlAscName}">
                            <i class="fa-sharp fa-solid fa-arrow-up"></i>
                        </a>
                        <a class="sort-button" href="${urlDescName}">
                            <i class="fa-solid fa-arrow-down"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</#macro>