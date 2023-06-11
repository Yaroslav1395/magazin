<#import "parts/head.ftlh" as h>
<#import "parts/header.ftlh" as hr>
<#import "parts/bannerShop.ftlh" as bc>
<#import "parts/footer.ftlh" as f>
<#import "parts/subscribe.ftlh" as s>
<#import "/spring.ftl" as spring />

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

     <@bc.page>
     </@bc.page>

     <section id="blog">
        <div class="blog-box">
            <div class="blog-img">
                <img src="/images/blog/b1.jpg" alt="">
            </div>
            <div class="blog-details">
                <h4>Новая разработка от компании BiSmart</h4>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis nisi
                    praesentium tenetur reprehenderit, facere consectetur laboriosam magni sed et quod,
                    distinctio incidunt ipsa adipisci corrupti vitae omnis! Repellat, mi nima voluptatibus?
                </p>
                <a href="#">Продолжить чтение</a>
            </div>
            <h1>13/01</h1>
        </div>
        <div class="blog-box">
            <div class="blog-img">
                <img src="/images/blog/b2.jpg" alt="">
            </div>
            <div class="blog-details">
                <h4>Новая разработка от компании BiSmart</h4>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis nisi
                    praesentium tenetur reprehenderit, facere consectetur laboriosam magni sed et quod,
                    distinctio incidunt ipsa adipisci corrupti vitae omnis! Repellat, mi nima voluptatibus?
                </p>
                <a href="#">Продолжить чтение</a>
            </div>
            <h1>13/01</h1>
        </div>
        <div class="blog-box">
            <div class="blog-img">
                <img src="/images/blog/b3.jpg" alt="">
            </div>
            <div class="blog-details">
                <h4>Новая разработка от компании BiSmart</h4>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis nisi
                    praesentium tenetur reprehenderit, facere consectetur laboriosam magni sed et quod,
                    distinctio incidunt ipsa adipisci corrupti vitae omnis! Repellat, mi nima voluptatibus?
                </p>
                <a href="#">Продолжить чтение</a>
            </div>
            <h1>13/01</h1>
        </div>
        <div class="blog-box">
            <div class="blog-img">
                <img src="/images/blog/b4.jpg" alt="">
            </div>
            <div class="blog-details">
                <h4>Новая разработка от компании BiSmart</h4>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis nisi
                    praesentium tenetur reprehenderit, facere consectetur laboriosam magni sed et quod,
                    distinctio incidunt ipsa adipisci corrupti vitae omnis! Repellat, mi nima voluptatibus?
                </p>
                <a href="#">Продолжить чтение</a>
            </div>
            <h1>13/01</h1>
        </div>
        <div class="blog-box">
            <div class="blog-img">
                <img src="/images/blog/b5.jpg" alt="">
            </div>
            <div class="blog-details">
                <h4>Новая разработка от компании BiSmart</h4>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis nisi
                    praesentium tenetur reprehenderit, facere consectetur laboriosam magni sed et quod,
                    distinctio incidunt ipsa adipisci corrupti vitae omnis! Repellat, mi nima voluptatibus?
                </p>
                <a href="#">Продолжить чтение</a>
            </div>
            <h1>13/01</h1>
        </div>
        <div class="blog-box">
            <div class="blog-img">
                <img src="/images/blog/b6.jpg" alt="">
            </div>
            <div class="blog-details">
                <h4>Новая разработка от компании BiSmart</h4>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis nisi
                    praesentium tenetur reprehenderit, facere consectetur laboriosam magni sed et quod,
                    distinctio incidunt ipsa adipisci corrupti vitae omnis! Repellat, mi nima voluptatibus?
                </p>
                <a href="#">Продолжить чтение</a>
            </div>
            <h1>13/01</h1>
        </div>
        <div class="blog-box">
            <div class="blog-img">
                <img src="/images/blog/b7.jpg" alt="">
            </div>
            <div class="blog-details">
                <h4>Новая разработка от компании BiSmart</h4>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis nisi
                    praesentium tenetur reprehenderit, facere consectetur laboriosam magni sed et quod,
                    distinctio incidunt ipsa adipisci corrupti vitae omnis! Repellat, mi nima voluptatibus?
                </p>
                <a href="#">Продолжить чтение</a>
            </div>
            <h1>13/01</h1>
        </div>
     </section>

     <section id="pagination" class="section-p1">
        <a href="#">1</a>
        <a href="#">2</a>
        <a href="#"><i class="fa-solid fa-arrow-right"></i></a>
     </section>

     <@s.page>
        <div>
           <@spring.formInput "subscribeDto.email" "type='email'" "placeholder='Your email address'"/>
           <@spring.showErrors "<br>"/>
        </div>
     </@s.page>

     <@f.page>
     </@f.page>

</@h.page>