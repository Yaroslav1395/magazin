<#import "parts/head.ftlh" as h>
<#import "parts/header.ftlh" as hr>
<#import "parts/message.ftlh" as m>
<#import "parts/footer.ftlh" as f>
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

    <#if message??>
        <@m.page>
            <p>${message}</p>
        </@m.page>
    </#if>

    <section class="login-registration">
            <div class="form-box login" id="registration-form-box">
                <div class="form-value">
                    <form action="/quantum/login" id="registration-form" method="post">
                        <h2>Login</h2>
                        <div class="inputbox">
                            <@spring.formInput "userLoginDto.username" "required='required'" "username"/>
                            <label for="username">Email</label>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                        <div class="inputbox">
                            <a id="showRegistration"><i class="fa-solid fa-eye"></i></a>
                            <@spring.formPasswordInput  "userLoginDto.password" "required='required'"/>
                            <label for="password">Password</label>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                        <div class="forget">
                            <label for=""><input type="checkbox">Remeber me <a href="#">Forget password</a></label>
                        </div>
                        <button>Login</button>
                        <div class="register">
                            <p>No account?<a href="/quantum/registration">Registration</a></p>
                        </div>
                        <div class="form-close" id="close-registration">&#10006</div>
                    </form>
                </div>
            </div>
        </section>
        <@f.page>
        </@f.page>
</@h.page>