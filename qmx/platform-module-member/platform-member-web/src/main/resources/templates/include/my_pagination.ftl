<div id="commonPage" style="float:right;padding-right: 60px;"></div>
<script>
    layui.use(['form','laypage'], function(){
        var form = layui.form, laypage = layui.laypage;
        //完整功能
        laypage.render({
            elem: 'commonPage'
            ,count: ${page.total!}
            ,limit:${page.pageSize!}
            ,curr: ${page.pageIndex!}
            ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
            ,jump: function(obj,first){
                //console.log(obj);
                //console.log(obj.curr);
                //console.log(obj.limit);
                //首次不执行
                if(!first){
                    //do something
                    var $currForm = $("form");
                    $currForm.append('<input type="hidden" name="pageSize" value="' + obj.limit + '" />');
                    $currForm.append('<input type="hidden" name="pageIndex" value="' + obj.curr + '" />');
                    $currForm.submit();
                }
            }
        });
    });
</script>