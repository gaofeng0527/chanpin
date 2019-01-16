layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'laypage'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    var subjectData='';

    //加载页面数据
    $.get("/admin/subjectList", "json", function (result) {
            subjectData = $.parseJSON(result).data;
            //执行加载数据的方法
        subjectsList();
        }
    )

    //添加
    $(".subAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加科目",
            type: 2,
            content: "/admin/addSubject-q",
            success: function (layero, index) {
            }
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        })
        layui.layer.full(index);
    })



    //操作
    $("body").on("click", ".subject_edit", function () {  //编辑
        var _this = $(this);
        var subjectId = _this.attr("data-id");
        var index = layui.layer.open({
            title: "编辑科目",
            type: 2,
            content: "/admin/subject/edit-q/" + subjectId,
            success: function (layero, index) {

            }
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        })
        layui.layer.full(index);


    })

    $("body").on("click", ".down_video", function () {  //下载video.xml
        var _this = $(this);
        var subject_id = _this.attr("data-id");
        window.open("/admin/subject/down/video/"+subject_id);
    })

    $("body").on("click", ".down_course", function () {  //下载course.xml
        var _this = $(this);
        var subject_id = _this.attr("data-id");
        window.open("/admin/subject/down/course/"+subject_id);
    })

    function subjectsList(that) {
        //渲染数据
        function renderDate(data, curr) {
            var dataHtml = '';
            var currData = '';
            if (!that) {
                currData = subjectData.concat().splice(curr * nums - nums, nums);
            } else {
                currData = that.concat().splice(curr * nums - nums, nums);
            }
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td align="left">' + currData[i].title + '</td>'
                        + '<td>' + currData[i].code + '</td>'
                        + '<td>' + currData[i].addTime + '</td>'
                        + '<td>'
                        + '<a class="layui-btn layui-btn-mini subject_edit" data-id = "' + data[i].id + '"><i class="iconfont icon-edit"></i> 编辑</a>'
                        + '<a class="layui-btn layui-btn-normal layui-btn-mini down_video" data-id="' + data[i].id +'"><i class="layui-icon">&#xe600;</i> video_course_cfg_g</a>'
                        + '<a class="layui-btn layui-btn-danger layui-btn-mini down_course" data-id="' + data[i].id + '"><i class="layui-icon">&#xe640;</i> course_cfg_g</a>'
                        + '</td>'
                        + '</tr>';
                }
            } else {
                dataHtml = '<tr><td colspan="4">暂无数据</td></tr>';
            }
            return dataHtml;
        }

        //分页
        var nums = 13; //每页出现的数据量
        if (that) {
            subjectData = that;
        }
        laypage({
            cont: "page",
            pages: Math.ceil(subjectData.length / nums),
            jump: function (obj) {
                $(".news_content").html(renderDate(subjectData, obj.curr));
                $('.news_list thead input[type="checkbox"]').prop("checked", false);
                form.render();
            }
        })
    }
})
