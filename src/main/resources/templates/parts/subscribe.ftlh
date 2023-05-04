<#macro page>
    <section id="newsletter" class="section-p1 section-m1">
        <div class="newstext">
            <h4>Подпишись чтобы быть в курсе событий</h4>
            <p>
                Получай письма на почту о наших <span>специальных предложениях</span>
            </p>
        </div>
        <div class="form">
            <form id="subscribeForm" action="/quantum/main" method="post">
            <#nested>
            <div>
               <button id="subscribeButton" type="submit" class="normal">Подписаться</button>
            </div>
            </form>
        </div>
    </section>
</#macro>