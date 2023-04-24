<#import "parts/common.ftlh" as c>

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
            <input type="number" value="1" name="id">
            <button class="normal">В корзину</button>
            <h4>Описание товара</h4>
            <span>${product.description}</span>
        </div>
    </section>
</@c.page>