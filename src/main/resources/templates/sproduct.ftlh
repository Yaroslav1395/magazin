<#import "parts/head.ftlh" as h>
<#import "parts/header.ftlh" as hr>
<#import "parts/comments.ftlh" as com>
<#import "parts/footer.ftlh" as f>

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

    <section id="prodetails" class="section-p1">
        <div class="single-pro-image">
            <div class="big-img-wrepper">
                <img src="/${product.productImagesDto[0].src}" width="100%" id="MainImg" alt="">
            </div>
            <#if product.productImagesDto??>
                <div class="small-img-group">
                    <#list product.productImagesDto as image>
                         <div class="small-img-col">
                            <img class="small-img" src="/${image.src}" width="100%" id="small-img" alt="${image.src}">
                         </div>
                    </#list>
                </div>
            </#if>
        </div>

        <div class="single-pro-details">
            <div class="comapany">
                <h6>${product.categoryDto.name}</h6>
                <img src="/${product.companyDto.image}">
            </div>
            <h4>${product.name}</h4>
            <h2>${product.price} сом</h2>
            <form action="/quantum/shop/addProduct" method="post">
                <input type="number" min="0" value="1" name="productAmount">
                <input type="hidden" value="${product.id}" name="productId">
                <input class="currentUrlInput" type="hidden" name="currentUrl" required value="">
                <button class="normal">В корзину</button>
            </form>
            <h4>Описание товара</h4>
            <span>${product.description}</span>
        </div>
    </section>

    <@com.page product.categoryDto.id product.id>
        <#if product.reviewDtoList??>
            <#list product.reviewDtoList as comment>
                <div class="comment">
                    <div class="user-data">
                        <div class="user">
                            <p>${comment.userDto.firstName} ${comment.userDto.lastName}</p>
                        </div>
                        <div class="data">
                            <p>${comment.dateTime}</p>
                        </div>
                    </div>
                    <div class="comment-description">
                        <p>${comment.message}</p>
                    </div>
                </div>
            </#list>
        </#if>
    </@com.page>
    <section id="recommended" class="section-p1">
        <h2>Аналогичная продукция</h2>
        <p>Предлагаем ознакомится с лидерами продаж</p>
        <div class="pro-container">
            <#if products??>
                <#list products as product>
                    <div class="pro">
                        <div class="image-wrapper">
                            <img src="/${product.productImagesDto[0].src}" alt="${product.name}">
                        </div>
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
                            <h4>${product.price}</h4>
                            <form action="/quantum/shop/addProduct" method="post">
                                <input type="hidden" name="productId" required value="${product.id}"></p>
                                <input type="hidden" name="productAmount" required value="1"></p>
                                <input class="currentUrlInput" type="hidden" name="currentUrl" required value=""></p>
                                <button class="product-btn" type="submit"><i class="fa-solid fa-cart-shopping"></i></button>
                            </form>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </section>
    <@f.page>
    </@f.page>
</@h.page>
