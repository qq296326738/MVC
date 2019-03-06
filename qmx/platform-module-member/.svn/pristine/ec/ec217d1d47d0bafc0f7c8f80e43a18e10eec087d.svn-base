<div id="example" class="pagination">
    <input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
    <input type="hidden" name="pageIndex" id="pageIndex" value="${page.pageIndex}"/>
    <ul class="pagination" style="width: 100%;margin-left: 60%;" id="pageLimit"></ul></div>
<script>
    $(function () {

        var $pageSize = $("#pageSize");
        var $pageIndex = $("#pageIndex");
        var $listForm = $("#listForm");
        var totalPages = ${(page.pages)!0};
        var options = {
            currentPage: $pageIndex.val(),
            totalPages: totalPages,
            numberOfPages:$pageSize.val(),
            bootstrapMajorVersion: 3,
            itemContainerClass: function (type, page, current) {
                return (page === current) ? "active" : "pointer-cursor";
            },
            itemTexts: function (type, page, current) {
                switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "尾页";
                    case "page":
                        return page;
                }
            },
            onPageClicked: function(e,originalEvent,type,page){
                $pageIndex.val(page);
                //@$pageSize.val();
                $listForm.submit();
            }
        };
        $('#pageLimit').bootstrapPaginator(options);
    });


    /*$('#pageLimit').bootstrapPaginator({
        currentPage: 1,
        totalPages: 10,
        size:"normal",
        bootstrapMajorVersion: 3,
        alignment:"right",
        numberOfPages:5,
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first": return "首页";
                case "prev": return "上一页";
                case "next": return "下一页";
                case "last": return "末页";
                case "page": return page;
            }
        }
    });*/


</script>