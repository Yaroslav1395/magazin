<#macro page categoryId productId>

<section class="section-p1">
        <div class="comment-wrapper">
            <div class="add-comment">
                <form action="/quantum/sProduct/addComment" method="get">
                    <textarea name="comment" type="text"></textarea>
                    <input type="hidden" value=${categoryId} name="categoryId">
                    <input type="hidden" value=${productId} name="productId">
                    <button>Отзыв</button>
                </form>
            </div>
            <div class="comments">
            <#nested>
            </div>
        </div>
</section>
</#macro>