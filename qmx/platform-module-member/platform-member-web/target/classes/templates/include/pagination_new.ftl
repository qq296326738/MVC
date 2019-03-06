<!--分页-->
<nav class="text-center gds-page" aria-label="Page navigation">
    <ul class="pagination">
        <li>
            <a>
            共${page.total!}条记录
            </a>
        </li>
        <#if page.hasPrevious>
            <li>
                <a class="gds-btn" href="javascript:doPage(${page.pageIndex-1})" aria-label="Previous">
                    <span aria-hidden="true">上一页</span>
                </a>
            </li>
        <#else>
            <li class="disabled">
                <a class="gds-btn" aria-label="Previous">
                    <span aria-hidden="true">上一页</span>
                </a>
            </li>
        </#if>
        <#if page.pageIndex < page.pages>
            <li>
                <a class="gds-btn" href="javascript:doPage(${page.pageIndex+1})" aria-label="Next">
                    <span aria-hidden="true">下一页</span>
                </a>
            </li>
        <#else>
            <li class="disabled">
                <a class="gds-btn" aria-label="Next">
                    <span aria-hidden="true">下一页</span>
                </a>
            </li>
        </#if>
        <li><a>共${page.pages!}页</a></li>
    </ul>
    <div class="form-group">
    第<input type="text" name="pageIndex" value="${page.pageIndex!1}" max="${page.pages!}" class="gds-btn gds-page-input" />页
    </div>
    <button type="submit" class="gds-btn">跳转</button>
</nav>
<script type="text/javascript">
    function doPage(page){
        $("input[name=pageIndex]").val(page);
        $("form").submit();
    }
</script>