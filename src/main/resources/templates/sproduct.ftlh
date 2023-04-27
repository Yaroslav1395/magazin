<#import "parts/common.ftlh" as c>
<#import "parts/comments.ftlh" as com>
<@c.page>
<section id="prodetails" class="section-p1">
        <div class="single-pro-image">
            <div class="big-img-wrepper">
                <img src="/${product.productImageDto.imageOne}" width="100%" id="MainImg" alt="">
            </div>

            <div class="small-img-group">
                <div class="small-img-col">
                    <img class="small-img" src="/${product.productImageDto.imageOne}" width="100%" id="small-img" alt="">
                </div>
                <div class="small-img-col">
                    <img  class="small-img" src="/${product.productImageDto.imageTwo}" width="100%" id="small-img" alt="">
                </div>
                <div class="small-img-col">
                    <img class="small-img" src="/${product.productImageDto.imageThree}" width="100%" id="small-img" alt="">
                </div>
                <div class="small-img-col">
                    <img class="small-img" src="/${product.productImageDto.imageFour}" width="100%" id="small-img" alt="">
                </div>
            </div>
        </div>

        <div class="single-pro-details">
            <div class="comapany">
                <h6>${product.categoryDto.name}</h6>
                <img src="/${product.companyDto.image}">
            </div>
            <h4>${product.name}</h4>
            <h2>${product.price} сом</h2>
            <input type="number" min="0" value="1" name="id">
            <button class="normal">В корзину</button>
            <h4>Описание товара</h4>
            <span>${product.description}</span>
        </div>
</section>
<@com.page>
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
                            <img src="/${product.productImageDto.imageOne}" alt="${product.name}">
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
                            <div class="btn"><a href="#"><i class="fa-solid fa-cart-shopping"></i></a></div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
</section>
</@c.page>