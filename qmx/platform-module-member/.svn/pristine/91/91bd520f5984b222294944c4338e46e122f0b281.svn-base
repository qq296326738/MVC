var form;
layui.use('form', function () {
    form = layui.form;
});

function loadArea() {
    var provinceHtml = '';
    $.ajax({
        url: "/common/area",
        type: "GET",
        data: {parentId: null},
        dataType: "json",
        cache: false,
        async: false,
        success: function (json) {
            $.each(json.data, function (value, name) {
                provinceHtml += '<option value="' + value + '">' + name + '</option>';
            });
        }
    });
    $('select[name=province]').append(provinceHtml);
    form.render();

    form.on('select(province)', function (data) {
        var value = data.value;
        if (value == "") {
            return;
        }
        $('select[name=city]').html('<option value="">请选择</option>').parent().hide();
        $('select[name=county]').html('<option value="">请选择</option>').parent().hide();
        var cityHtml = '';
        $.ajax({
            url: "/common/area",
            type: "GET",
            data: {parentId: data.value},
            dataType: "json",
            cache: false,
            async: false,
            success: function (json) {
                $.each(json.data, function (value, name) {
                    cityHtml += '<option value="' + value + '">' + name + '</option>';
                });
            }
        });
        $('select[name=city]').append(cityHtml).parent().show();
        form.render();
    });

    form.on('select(city)', function (data) {
        var value = data.value;
        if (value == "") {
            return;
        }
        $('select[name=county]').html('<option value="">请选择</option>').parent().hide();
        var countyHtml = '';
        $.ajax({
            url: "/common/area",
            type: "GET",
            data: {parentId: data.value},
            dataType: "json",
            cache: false,
            async: false,
            success: function (json) {
                $.each(json.data, function (value, name) {
                    countyHtml += '<option value="' + value + '">' + name + '</option>';
                });
            }
        });
        $('select[name=county]').append(countyHtml).parent().show();
        form.render();
    });
}

function bindArea(area) {
    $('select[name=province]').html('<option value="">请选择</option>');
    $('select[name=city]').html('<option value="">请选择</option>');
    $('select[name=county]').html('<option value="">请选择</option>');
    if (area.province != null) {
        var provinceHtml = '';
        $.ajax({
            url: "/common/area",
            type: "GET",
            data: {parentId: null},
            dataType: "json",
            cache: false,
            async: false,
            success: function (json) {
                $.each(json.data, function (value, name) {
                    if (value == area.province) {
                        provinceHtml += '<option value="' + value + '" selected>' + name + '</option>';
                    } else {
                        provinceHtml += '<option value="' + value + '">' + name + '</option>';
                    }
                });
            }
        });
        $('select[name=province]').append(provinceHtml);
    }
    if (area.city != "") {
        var cityHtml = '';
        $.ajax({
            url: "/common/area",
            type: "GET",
            data: {parentId: area.province},
            dataType: "json",
            cache: false,
            async: false,
            success: function (json) {
                $.each(json.data, function (value, name) {
                    if (value == area.city) {
                        cityHtml += '<option value="' + value + '" selected>' + name + '</option>';
                    } else {
                        cityHtml += '<option value="' + value + '">' + name + '</option>';
                    }
                });
            }
        });
        $('select[name=city]').append(cityHtml).parent().show();
    }
    if (area.county != "") {
        var countyHtml = '';
        $.ajax({
            url: "/common/area",
            type: "GET",
            data: {parentId: area.city},
            dataType: "json",
            cache: false,
            async: false,
            success: function (json) {
                $.each(json.data, function (value, name) {
                    if (value == area.county) {
                        countyHtml += '<option value="' + value + '" selected>' + name + '</option>';
                    } else {
                        countyHtml += '<option value="' + value + '">' + name + '</option>';
                    }
                });
            }
        });
        $('select[name=county]').append(countyHtml).parent().show();
    }
    form.render();
}