<#import "parts/head.ftlh" as h>
<#import "parts/header.ftlh" as hr>
<#import "parts/message.ftlh" as m>
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


    <section id="cart" class="section-admin">
        <a href="/quantum/admin/allUsers">Получить список всех пользователей</a>
        <p>Получить пользователя по Email</p>
        <form action="/quantum/admin/user" method="get">
            <input type="email" placeholder="Введите Email" name="email">
            <button type="submit">Получить пользователя</button>
        </form>
        <#if users??>
            <table width="100%">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Имя</td>
                        <td>Фамилия</td>
                        <td>Email</td>
                    </tr>
                </thead>
                <tbody id="tableBody">
                    <#list users as user>
                        <tr id="line">
                            <td id="${user.id}">${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.email}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
        <#if user??>
            <p>${user.id} ${user.firstName} ${user.lastName} ${user.email}
        </#if>
    </section>

    <section id="cart" class="section-admin">
        <a href="/quantum/admin/subscribes">Получить список Email подписанных на новости</a>
        <#if subscribes??>
            <table width="100%">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Email</td>
                    </tr>
                </thead>
                <tbody id="tableBody">
                    <#list subscribes as subscribe>
                        <tr id="line">
                            <td id="${subscribe.id}">${subscribe.id}</td>
                            <td>${subscribe.email}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
    </section>

    <section id="cart" class="section-admin">
        <a href="/quantum/admin/categories">Получить список категорий продуктов</a>
        <p>Создать категорию</p>
        <form action="/quantum/admin/newCategory" method="post" enctype="multipart/form-data">
            <div>
                <label for="image">Выберите картинку для категории:</label>
                <input id="image" type="file" name="image">
            </div>
            <div>
                <label for="image">Введите название категории:</label>
                <input id="categoryName" type="text" name="categoryName">
            </div>
            <button type="submit">Отправить</button>
        </form>
        <p>Найти категорию по Id</p>
        <form action="/quantum/admin/categoryId" method="get">
            <div>
                <label for="image">Введите ID категории:</label>
                <input id="categoryName" type="number" min="1" value="1" name="categoryId">
            </div>
            <button type="submit">Отправить</button>
        </form>

        <#if categories??>
            <section id="cart" class="section-admin">
                <table width="100%">
                    <thead>
                        <tr>
                            <td>Id</td>
                            <td>Название</td>
                            <td>Картинка</td>
                        </tr>
                    </thead>
                    <tbody id="tableBody">
                        <#list categories as category>
                            <tr id="line">
                                <td id="${category.id}">${category.id}</td>
                                <td>${category.name}</td>
                                <td><img src="/${category.src}" alt="{category.name}"></td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </section>
        </#if>
        <#if newCategory??>
            <p>Новая категория добавлена</p>
            <p>ID: ${newCategory.id}</p>
            <p>Название: ${newCategory.name}</p>
            <img src="/${newCategory.src}" alt="${newCategory.name}">
        </#if>
        <#if categoryById??>
            <p>Новая категория добавлена</p>
            <p>ID: ${categoryById.id}</p>
            <p>Название: ${categoryById.name}</p>
            <img src="/${categoryById.src}" alt="${categoryById.name}">
        </#if>
    </section>

    <section id="cart" class="section-admin">
        <a href="/quantum/admin/allProducts">Получить список продуктов</a>
        <p>Поиск продуктов по категории</p>
        <form action="/quantum/admin/getProductsBy/categoryId" method="get">
            <div>
                <label for="image">Введите ID категории:</label>
                <input id="categoryName" type="number" min="1" value="1" name="categoryId">
            </div>
            <button type="submit">Отправить</button>
        </form>
        <p>Поиск продуктов по названию</p>
        <form action="/quantum/admin/getProductsBy/keyword" method="get">
            <div>
                <label for="image">Введите название продукта:</label>
                <input id="keyword" type="text" name="keyword">
            </div>
            <button type="submit">Отправить</button>
        </form>
        <p>Поиск продуктов по ID компании</p>
        <form action="/quantum/admin/getProductsBy/companyId" method="get">
            <div>
                <label for="image">Введите название продукта:</label>
                <input id="companyId" type="number" min="1" value="1" name="companyId">
            </div>
            <button type="submit">Отправить</button>
        </form>

        <p>Создать новый продукт</p>
        <form action="/quantum/admin/newProduct" method="post" enctype="multipart/form-data">
            <div>
                <@spring.formInput "productNewDto.name" "required='required'" "text"/>
                <label for="name">Название продукта</label>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div>
                <@spring.formInput "productNewDto.title" "required='required'" "text"/>
                <label for="title">Заголовок продукта</label>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div>
                <@spring.formInput "productNewDto.price" "required='required'"/>
                <label for="email">Цена</label>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div>
                <@spring.formTextarea "productNewDto.description" "required='required'"/>
                <label for="description">Описание товара</label>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div>
                <@spring.formInput "productNewDto.categoryId" "required='required'"/>
                <label for="categoryId">ID категории</label>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div>
                <@spring.formInput "productNewDto.companyId" "required='required'"/>
                <label for="companyId">ID компании</label>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div>
                <label for="image">Выберите картинки для категории:</label>
                <input id="image" type="file" name="files" multiple="multiple">
            </div>
            <button>Отправить</button>
        </form>


        <#if newProduct??>
            <p>Продукт сохранен</p>
            <p>Id продукта: ${newProduct.id}</p>
            <p>Имя продукта: ${newProduct.name}</p>
            <p>Заголовок продукта: ${newProduct.title}</p>
            <p>Цена продукта: ${newProduct.price}</p>
            <p>Описание продукта: ${newProduct.description}</p>
            <p>Название фирмы: ${newProduct.companyDto.name}</p>
            <p>Название категории: ${newProduct.categoryDto.name}</p>
            <div>
            <#if newProduct.productImagesDto??>
                <#list newProduct.productImagesDto as image>
                    <img src="/${image.src}">
                </#list>
            </#if>
        </#if>
        <#if products??>
            <table width="100%">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Название</td>
                        <td>Заголовок</td>
                        <td>Цена</td>
                        <td>Картинка</td>
                        <td>Категория</td>
                        <td>Компания</td>
                    </tr>
                </thead>
                <tbody id="tableBody">
                    <#list products as product>
                        <tr id="line">
                            <td id="${product.id}">${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.title}</td>
                            <td>${product.price}</td>
                            <td><img src="/${product.productImageDto.imageOne}" alt="{category.name}"></td>
                            <td>${product.categoryDto.name}</td>
                            <td>${product.companyDto.name}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
    </section>

    <section id="cart" class="section-admin">
        <a href="/quantum/admin/allOrders">Получить все ордера</a>
        <a href="/quantum/admin/allOrders">Получить все ордера</a>
        <p>Поиск заказов по email</p>
        <form action="/quantum/admin/getOrderBy/userEmail" method="get">
            <div>
                <label for="image">Введите email пользователя:</label>
                <input id="userEmail" type="text" name="userEmail" required>
            </div>
            <button type="submit">Отправить</button>
        </form>

        <p>Поиск заказов за период</p>
        <form action="/quantum/admin/getOrderBy/date" method="post">
            <div>
                <label for="image">Начало периода:</label>
                <input id="fromDate" type="date" name="fromDate" required>
            </div>
            <div>
                <label for="image">Конец периода:</label>
                <input id="toDate" type="date" name="toDate" required>
            </div>
            <button type="submit">Отправить</button>
        </form>

        <#if orders??>
            <#list orders as order>
                <div id="cart" class="section-p1">
                    <table width="100%">
                        <thead>
                            <tr>
                                <td>Id</td>
                                <td>Название</td>
                                <td>Цена</td>
                                <td>Количество</td>
                                <td>Сумма</td>
                                <td>Картинка</td>
                            </tr>
                        </thead>
                        <tbody id="tableBody">
                            <#list order.products as product>
                                <#assign sum = product.price * product.amount>
                                <tr id="line">
                                    <td id="${product.id}">${product.id}</td>
                                    <td>${product.name}</td>
                                    <td>${product.price}</td>
                                    <td>${product.amount}</td>
                                    <td>${sum}</td>
                                    <#if product.productImages??>
                                        <td><img src="/${product.productImages[0].src}" alt="{product.productImages[0].src}"></td>
                                    </#if>
                                </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>

                <p>Id ордера ${order.id}</p>
                <#if order.dateTime??>
                    <p>Время создания: ${order.dateTime}</p>
                </#if>
                <p>Общая сумма: ${order.total}</p>
                <p>Имя пользователя: ${order.user.firstName}</p>
                <p>Фамилия пользователя: ${order.user.lastName}</p>
            </#list>
        </#if>
    </section>
</@h.page>