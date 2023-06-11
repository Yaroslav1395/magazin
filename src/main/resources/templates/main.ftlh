<#import "parts/head.ftlh" as h>
<#import "parts/header.ftlh" as hr>
<#import "parts/bannerMain.ftlh" as bm>
<#import "parts/future.ftlh" as fu>
<#import "parts/service.ftlh" as se>
<#import "parts/recommended.ftlh" as r>
<#import "parts/new.ftlh" as n>
<#import "parts/subscribe.ftlh" as s>
<#import "parts/coupons.ftlh" as c>
<#import "parts/footer.ftlh" as f>
<#import "/spring.ftl" as spring />
<#import "parts/message.ftlh" as m>

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

    <#if message??>
        <@m.page>
            <p>${message}</p>
        </@m.page>
    </#if>

    <@bm.page>
    </@bm.page>

    <@fu.page >
    </@fu.page>

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
                                        <img src="/${products[countTwo].productImagesDto[0].src}" alt="${products[countTwo].productImagesDto[0].src}">
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

    <@se.page>
    </@se.page>

    <@n.page>
        <#list newProducts as product>
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
                    <div class="btn"><a href="#"><i class="fa-solid fa-cart-shopping"></i></a></div>
                </div>
            </div>
        </#list>
    </@n.page>

    <@s.page>
         <div>
            <@spring.formInput "subscribeDto.email" "type='email'" "placeholder='Your email address'"/>
            <@spring.showErrors "<br>"/>
         </div>
    </@s.page>

    <@c.page>
    </@c.page>

    <@f.page>
    </@f.page>
</@h.page>


