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

     <section id="page-header" class="about-header">
             <div id="about-contant">
                 <h3>#quantum#узнайБольше</h3>
                 <p>Узнайте о нас все что нужно</p>
             </div>
     </section>

     <section id="about-header" class="section-p1">
         <img src="/images/about/a6.jpg">
         <div>
             <h2>Кто мы такие?</h2>
             <p>
                 Lorem ipsum, dolor sit amet consectetur adipisicing elit. Tenetur ratione eaque
                 inventore ullam quos sapiente fuga qui facilis nesciunt, quasi iusto porro voluptas
                 vero veniam quidem esse. Necessitatibus aliquid a voluptas repellat at saepe, perspiciatis
                 vel, commodi, ratione deleniti nam! Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                 Cupiditate, commodi quos sapiente fuga qui facilis nesciunt.
             </p>
             <abbr title="">
                 Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea, illum. Quaerat nemo ex
                 optio odit. Lorem ipsum dolor sit amet consectetur adipisicing elit. Quaerat, quidem.
             </abbr>
             <br><br>
             <marquee background-color="#ccc" logo="-1" scrollamount="5" width="100%">
                 Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                 Magni officia asperiores ea eaque minus a non voluptatibus aut porro voluptas?
             </marquee>
         </div>
     </section>

     <section id="about-app" class="section-p1">
         <h1>Скачайте Наше <a href="#">Приложение</a></h1>
         <div class="video">
             <video autoplay muted loop src="/images/about/app-downloud.mp4"></video>
         </div>
     </section>

     <section id="feature" class="section-p1">
         <div class="fe-box">
             <img src="/images/features/f1.png" alt="feature">
             <h6>Онлайн заказ</h6>
         </div>
         <div class="fe-box">
             <img src="/images/features/f2.png" alt="feature">
             <h6>Быстая доствка</h6>
         </div>
         <div class="fe-box">
             <img src="/images/features/f3.png" alt="feature">
             <h6>Кэшбек</h6>
         </div>
         <div class="fe-box">
             <img src="/images/features/f4.png" alt="feature">
             <h6>Акции</h6>
         </div>
         <div class="fe-box">
             <img src="/images/features/f5.png" alt="feature">
             <h6>Партнерство</h6>
         </div>
         <div class="fe-box">
             <img src="/images/features/f6.png" alt="feature">
             <h6>Поддрежка</h6>
         </div>
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