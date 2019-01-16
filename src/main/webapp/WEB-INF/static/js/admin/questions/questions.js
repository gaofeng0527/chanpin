layui.config({
    base: "/lib/layui/lay/modules/"
}).use(['form', 'layer', 'jquery', 'element', 'rest', 'laypage'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        element = layui.element,
        rest = layui.rest,
        $ = layui.jquery;
    var questionData;


    element.on('tab(subjectTab)', function (data) {
        var elem = $(this).attr('lay-id');
        if (elem == 'question') {
            showQuestion();
        }
    });
    //显示测评题列表
    function showQuestion() {
        $("#questionDiv").hide();
        $("#questionDivEdit").hide();
        $(".questionList").show();
        $("#questionAddBtn").show();
        //查询某科目下的所有章节
        var subjectId = $.trim($("#gsubjectId").val());
        if (subjectId == '') {
            layer.msg("请先选择科目！");
            return false;
        }

        rest.get({
            url: '/admin/question/list',
            data: {subjectId: subjectId},
            success: function (result) {
                if(result.success){
                    questionData = result.data;
                    questionList(questionData);
                }else{
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 2000);
                }
            }, fail: function (failResponse) {}
        });
    }

    //渲染测评题列表
    function questionList(that) {
        //渲染数据
        function renderDate(data, curr) {
            var dataHtml = '';
            if (!that) {
                currData = questionData.concat().splice(curr * nums - nums, nums);
            } else {
                currData = that.concat().splice(curr * nums - nums, nums);
            }
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td align="left">' + currData[i].title + '</td>'
                        + '<td>' + currData[i].know.title + '</td>'
                        + '<td>' + currData[i].answer + '</td>'
                        + '<td>'
                        + '<a class="layui-btn layui-btn-mini question_edit" data-id="' + data[i].id + '"><i class="iconfont icon-edit"></i> 编辑</a>'
                        + '<a class="layui-btn layui-btn-danger layui-btn-mini news_del" data-id="' + data[i].id + '"><i class="layui-icon">&#xe640;</i> 删除</a>'
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
            questionData = that;
        }
        laypage({
            cont: "qpage",
            pages: Math.ceil(questionData.length / nums),
            jump: function (obj) {
                $(".question_content").html(renderDate(questionData, obj.curr));
                form.render();
            }
        })
    }

    //点击添加测评题
    $(".question_btn").click(function () {
        $("#questionAddBtn").hide();
        $("#questionDiv").show();
        $(".questionList").hide();
        $("#questionDivEdit").hide();
        //查询所有章节
        var subjectId = $.trim($("#gsubjectId").val());
        if (subjectId == '') {
            layer.msg("请先选择科目！");
            return false;
        }
    });

    //添加测评题
    form.on("submit(addQuestion)", function (data) {
        data.field.subjectId = $("#gsubjectId").val();
        rest.create({
            url: "/admin/question/add",
            data: data.field,
            success: function (result) {
                if (result.success) {
                    setTimeout(function () {
                        top.layer.msg("测评题添加成功！");
                    }, 1000);
                    $(':input','#addQuestionForm').not(':button,:submit,reset,:hidden').val('');
                } else {
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 5000);
                }
                showQuestion();
            },
            fail: function (failResponse) {}
        });
        return false;
    });

    //点击编辑测评题
    $("body").on("click",".question_edit",function(){
        $("#questionAddBtn").hide();
        $("#questionDiv").hide();
        $(".questionList").hide();
        $("#questionDivEdit").show();
        var _this = $(this);
        var question_id = _this.attr("data-id");
        //查询所有章节
        var subjectId = $("#gsubjectId").val();
        if (subjectId < 0) {
            layer.msg("请先选择科目！");
        }
        rest.get({
            url: '/admin/question/edit/' + question_id, success: function (result) {
                if (result.success) {
                    var q = result.data;
                    $("#eqid").val(q.id);
                    $("#eqcode").val(q.qcode);
                    $("#eqtitle").val(q.title);
                    $("#eqtype").val(q.type);
                    $("#optiona").val(q.optiona);
                    $("#optionb").val(q.optionb);
                    $("#optionc").val(q.optionc);
                    $("#optiond").val(q.optiond);
                    $("#optione").val(q.optione);
                    $("#optionf").val(q.optionf);
                    $("#optiong").val(q.optionf);
                    $("#eanswer").val(q.answer);
                    $("#eanalysis").val(q.analysis);
                    $("#escore").val(q.score);
                    $("#knowTitle").val(q.knowTitle);
                } else {
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 2000);
                }
            },
            fail: function (failResponse) {}
        });
    });

    //编辑测评题
    form.on("submit(editQuestion)", function (data) {
        data.field.subjectId = $("#gsubjectId").val();
        rest.create({
            url: "/admin/question/edit",
            data: data.field,
            success: function (result) {
                if (result.success) {
                    setTimeout(function () {
                        top.layer.msg("测评题修改成功！");
                    }, 1000);
                } else {
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 5000);
                }
                showQuestion();
            },fail: function (failResponse) {}
        });
        return false;
    })

    //科目修改
    form.on("submit(editSubject)", function (data) {
        rest.create({
            url: "/admin/subject/edit",
            data: data.field,
            success: function (result) {
                if (result.success) {
                    setTimeout(function () {
                        top.layer.msg("科目修改成功！");
                    }, 1000);
                }else{
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 5000);
                }
            },
            fail: function (failResponse) {}
        });
        return false;
    })

    //科目添加
    form.on("submit(addSubject)", function (data) {
        rest.create({
            url: "/admin/subject/add",
            data: data.field,
            success: function (result) {
                if (result.success) {
                    $("#gsubjectId").val(result.data);
                    setTimeout(function () {
                        top.layer.msg("科目添加成功，可以操作章节！");
                    }, 1000);
                }else{
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 5000);
                }
            },
            fail: function (failResponse) {}
        });
        return false;
    })

})
