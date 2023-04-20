<#import "parts/common.ftlh" as c>
<#import "parts/recommended.ftlh" as r>
<#import "parts/new.ftlh" as n>

<@c.page>
    <@r.page>
       <#assign count = 0>
       <#assign countOne = 1>
       <#assign category = ''>
       <#list products as product>
           <#assign count = count + 1>
           <#if products[countOne]??>
                <#assign category = products[countOne].categoryDto.name>
                <#if product.categoryDto.name != category || countOne == 1>
                    <div id="recommended-category">
                        <h3>${products[countOne].categoryDto.name}</h3>
                        <div class="pro-container">
                            <#if count == 1>
                                <#assign countTwo = count - 1>
                            <#else>
                                <#assign countTwo = count>
                            </#if>
                            <#assign countTree = countTwo + 4>
                            <#list products as product>
                                <#if countTwo == countTree>
                                    <#break>
                                </#if>
                                <div class="pro">
                                    <div class="image-wrapper">
                                        <img src="/${products[countTwo].productImageDto.imageOne}" alt="${products[countTwo].productImageDto.imageOne}">
                                    </div>
                                    <div class="des">
                                        <span>${products[countTwo].categoryDto.name}</span>
                                        <h4>${products[countTwo].name}</h4>
                                        <div class="star">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                        <h4>${products[countTwo].price} сом</h4>
                                        <div class="btn"><a href="#"><i class="fa-solid fa-cart-shopping"></i></a></div>
                                    </div>
                                </div>
                                 <#assign countTwo = countTwo + 1>
                            </#list>
                        </div>
                    </div>
                </#if>
           <#else>
                <#break>
           </#if>
           <#assign countOne = countOne + 1>
       </#list>
    </@r.page>
    <@n.page>
        <#list newProducts as product>
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
    </@n.page>
</@c.page>

