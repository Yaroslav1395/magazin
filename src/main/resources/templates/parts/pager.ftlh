<#macro pager url products>
    <#if products.getTotalPages() gt 3 && products??>
        <#assign
            totalPages = products.getTotalPages()
            pageNumber =  products.getNumber() + 1

            head = (pageNumber > 2)?then([1, -1], [1, 2])
            tail = (pageNumber < totalPages - 2)?then([-1, totalPages], [totalPages - 1, totalPages])
            bodyBefore = (pageNumber > 2 && pageNumber < totalPages - 2)?then([pageNumber - 1], [])
            bodyAfter = (pageNumber > 1 && pageNumber < totalPages - 2)?then([pageNumber + 1], [])

            body = head + bodyBefore + (pageNumber > 2 && pageNumber < totalPages - 1)?then([pageNumber], []) + bodyAfter + tail
        >
    <#else>
        <#assign body = 1..products.getTotalPages()>
    </#if>

    <div id="pagination" class="section-p1">
        <#if products.getTotalPages() gt 3 && (pageNumber - 1) gt 0>
            <a href="${url}pageNumber=${(pageNumber - 1) - 1}"><i class="fa-solid fa-arrow-left"></i></a>
        </#if>

        <#list body as p>

            <#if (p - 1) == products.getNumber()>
                <a class="active" href="${url}pageNumber=${p - 1}">${p}</a>
            <#elseif p == -1>
                <a class="disabled" href="#">...</a>
            <#else>
                <a href="${url}pageNumber=${p - 1}">${p}</a>
            </#if>

        </#list>

        <#if products.getTotalPages() gt 3 && pageNumber != products.getTotalPages()>
            <a href="${url}pageNumber=${(pageNumber - 1) + 1}"><i class="fa-solid fa-arrow-right"></i></a>
        </#if>
    </div>
</#macro>