<#macro page>

<section class="section-p1">
        <div class="comment-wrapper">
            <div class="add-comment">
                <form>
                    <textarea name="comment" type="text"></textarea>
                    <button>Отзыв</button>
                </form>
            </div>
            <div class="comments">
            <#nested>
            </div>
        </div>
</section>
</#macro>