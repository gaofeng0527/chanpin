layui.config({
    base: "/lib/layui/lay/modules/"
}).use(['form', 'layer', 'jquery', 'element', 'rest', 'laypage'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        element = layui.element,
        rest = layui.rest,
        $ = layui.jquery;
    var chapterData, knowData, questionData;
    var qcurr;


    element.on('tab(subjectTab)', function (data) {
        var elem = $(this).attr('lay-id');
        if (elem == 'chapter') {
            showChapter();
        } else if (elem == 'know') {
            showKonw();
        } else if (elem == 'question') {
            showQuestion();
        }
    });

    //显示章节列表
    function showChapter() {
        $("#chapterDivEntry").hide();
        $("#chapterDivEdit").hide();
        $(".chapterList").show();
        $("#chapterAddBtn").show();
        //查询某科目下的所有章节
        var subjectId = $("#gsubjectId").val();
        if ($.trim(subjectId)=='') {
            layer.msg("请先选择科目！");
            return false;
        }
        rest.get({
            url: '/admin/chapter/list',
            data: {subjectId: subjectId},
            success: function (result) {
                if(result.success){
                    chapterData = result.data;
                    chapterList(chapterData);
                }else{
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 5000);
                }

            }, fail: function (failResponse) {}
        });
    }

    //章节列表
    function chapterList(that) {
        //渲染数据
        function renderDate(data, curr) {
            var dataHtml = '';
            if (!that) {
                currData = chapterData.concat().splice(curr * nums - nums, nums);
            } else {
                currData = that.concat().splice(curr * nums - nums, nums);
            }
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td align="left">' + currData[i].ccode + '</td>'
                        + '<td>' + currData[i].title + '</td>'
                        + '<td>' + currData[i].subject.title + '</td>'
                        + '<td>'
                        + '<a class="layui-btn layui-btn-mini chapter_edit" data-id="' + currData[i].id + '"><i class="iconfont icon-edit"></i> 编辑</a>'
                        + '<a class="layui-btn layui-btn-danger layui-btn-mini news_del" data-id="' + currData[i].id + '"><i class="layui-icon">&#xe640;</i> 删除</a>'
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
            chapterData = that;
        }
        laypage({
            cont: "page",
            pages: Math.ceil(chapterData.length / nums),
            jump: function (obj) {
                $(".chapter_content").html(renderDate(chapterData, obj.curr));
                form.render();
            }
        })
    }

    //点击添加
    $(".chapter_btn").click(function () {
        $("#chapterAddBtn").hide();
        $("#chapterDivEntry").show();
        $(".chapterList").hide();
        $("#chapterDivEdit").hide();
    });

    //章节添加
    form.on("submit(addChapter)", function (data) {
        data.field.subjectId = $("#gsubjectId").val();
        console.log("addChapter:"+data.field);
        rest.create({
            url: "/admin/chapter/add",
            data: data.field,
            success: function (result) {
                if (result.success) {
                    setTimeout(function () {
                        top.layer.msg("章节添加成功！");
                    }, 1000);
                    $(':input','#addChapterForm').not(':button,:submit,reset,:hidden').val('');
                } else {
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 5000);
                }
                showChapter();
            },
            fail: function (failResponse) {}
        });
        return false;
    });

    //点击编辑
    $("body").on("click", ".chapter_edit", function () {

        $("#chapterAddBtn").hide();
        $("#chapterDivEntry").hide();
        $(".chapterList").hide();
        $("#chapterDivEdit").show();
        var _this = $(this);
        var chapter_id = _this.attr("data-id");

        rest.get({
            url: '/admin/chapter/edit/' + chapter_id, success: function (result) {
                if (result.success) {
                    var chapter = result.data;
                    $("#eccode").val(chapter.ccode);
                    $("#chapter_edit_sid").val(chapter.subjectId);
                    $("#ecctitle").val(chapter.cctitle);
                    $("#etitle").val(chapter.title);
                    $("#evideo").val(chapter.video);
                    $("#ecid").val(chapter.id);
                    $("#eorderL").val(chapter.orderL);

                } else {
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 2000);
                }
            },
            fail: function (failResponse) {

            }
        });
    });

    //章节编辑
    form.on("submit(editChapter)", function (data) {
        data.field.subjectId = $("#gsubjectId").val();
        rest.create({
            url: "/admin/chapter/edit",
            data: data.field,
            success: function (result) {
                if (result.success) {
                    setTimeout(function () {
                        top.layer.msg("章节修改成功！");
                    }, 1000);
                } else {
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 5000);
                }
                showChapter();
            },
            fail: function (failResponse) {}
        });
        return false;
    })

    //显示知识点列表
    function showKonw() {
        $("#knowDiv").hide();
        $("#knowDivEdit").hide();
        $(".knowList").show();
        $("#knowAddBtn").show();
        var subjectId = $("#gsubjectId").val();
        if ($.trim(subjectId)=='') {
            layer.msg("请先选择科目！");
            return false;
        }
        rest.get({
            url: '/admin/know/list',
            data: {subjectId: subjectId},
            success: function (result) {
                if(result.success){
                    knowData = result.data;
                    knowList(knowData);
                }else{
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 5000);
                }

            }, fail: function (failResponse) {}
        });
    }

    //知识点列表
    function knowList(that) {
        //渲染数据
        function renderDate(data, curr) {
            var dataHtml = '';
            var currData = '';
            if (!that) {
                currData = knowData.concat().splice(curr * nums - nums, nums);
            } else {
                currData = that.concat().splice(curr * nums - nums, nums);
            }
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td align="left">' + currData[i].kcode + '</td>'
                        + '<td>' + currData[i].title + '</td>'
                        + '<td>'
                        + '<a class="layui-btn layui-btn-mini know_edit" data-id="' + currData[i].id + '"><i class="iconfont icon-edit"></i> 编辑</a>'
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
            knowData = that;
        }
        laypage({
            cont: "kpage",
            pages: Math.ceil(knowData.length / nums),
            jump: function (obj) {
                $(".know_content").html(renderDate(knowData, obj.curr));
                form.render();
            }
        })
    }

    //点击添加知识点
    $(".know_btn").click(function () {
        $("#knowAddBtn").hide();
        $("#knowDiv").show();
        $(".knowList").hide();
        $("#knowDivEdit").hide();
        var subjectId = $("#gsubjectId").val();
        if ($.trim(subjectId) == '') {
            layer.msg("请先选择科目！");
            return false;
        }
        rest.get({
            url: '/admin/know/getCode/' + subjectId, success: function (result) {
                if (result.success) {
                    $("#addKnowCode").val(result.data);
                } else {
                    setTimeout(function () {
                        top.layer.msg("获取知识点编号错误，错误原因：" + result.message);
                    }, 2000);
                }
            },
            fail: function (failResponse) {}
        });
    });

    //添加知识点
    form.on("submit(addKnow)", function (data) {
        data.field.subjectId = $("#gsubjectId").val();
        console.log(data.field);
        rest.create({
            url: "/admin/know/add",
            data: data.field,
            success: function (result) {
                if (result.success) {
                    setTimeout(function () {
                        top.layer.msg("知识点添加成功！");
                    }, 1000);
                    $(':input','#addKnowForm').not(':button,:submit,reset,:hidden').val('');
                } else {
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 5000);
                }
                showKonw();
            },
            fail: function (failResponse) {}
        });
        return false;
    });

    //点击编辑知识点
    $("body").on("click",".know_edit",function(){
        $("#knowAddBtn").hide();
        $("#knowDiv").hide();
        $(".knowList").hide();
        $("#knowDivEdit").show();
        var _this = $(this);
        var know_id = _this.attr("data-id");
        var subjectId = $("#gsubjectId").val();
        if (subjectId < 0) {
            layer.msg("请先选择科目！");
        }
        rest.get({
            url: '/admin/know/edit/' + know_id, success: function (result) {
                if (result.success) {
                    var know = result.data;
                    $("#kid").val(know.id);
                    $("#know_add_sid").val(know.subjectId);
                    $("#ekcode").val(know.kcode);
                    $("#ktitle").val(know.title);

                } else {
                    setTimeout(function () {
                        top.layer.msg("获取知识点失败（" + know + "），错误原因：" + result.message);
                    }, 2000);
                }
            },
            fail: function (failResponse) {}
        });
    });

    //编辑知识点
    form.on("submit(editKnow)", function (data) {
        data.field.subjectId = $("#gsubjectId").val();
        rest.create({
            url: "/admin/know/edit",
            data: data.field,
            success: function (result) {
                if (result.success) {
                    setTimeout(function () {
                        top.layer.msg("知识点修改成功！");
                    }, 1000);
                } else {
                    setTimeout(function () {
                        top.layer.msg(result.message);
                    }, 5000);
                }
                showKonw();
            },fail: function (failResponse) {}
        });
        return false;
    })

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
                        + '<a class="layui-btn layui-btn-mini question_edit" data-id="' + currData[i].id + '"  data-curr = "'+curr+'"><i class="iconfont icon-edit"></i> 编辑</a>'
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

        rest.get({
            url: '/admin/question/getCode/' + subjectId, success: function (result) {
                if (result.success) {
                    $("#addQuestionCode").val(result.data);
                } else {
                    setTimeout(function () {
                        top.layer.msg("获取知识点编号错误，错误原因：" + result.message);
                    }, 2000);
                }
            },
            fail: function (failResponse) {}
        });

        rest.get({
            url: '/admin/know/list',
            data: {subjectId: subjectId},
            success: function (result) {
                var klist = result.data;
                $(".aknow").empty();
                for (var i = 0; i < klist.length; i++) {
                    $(".aknow").append("<option value="+klist[i].id+">"+klist[i].title+"</option>");
                }
                form.render('select');
            }, fail: function (failResponse) {}
        });
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
                    $("#qdesc").val(q.desc);
                    $("#question_edit_sid").val(q.subjectId);
                    $("#eqid").val(q.id);
                    $("#eqcode").val(q.qcode);
                    $("#eqtitle").val(q.title);
                    $("#eqknowId").val(q.knowId);
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
                    koptions(subjectId,q.knowId);
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

    //动态加载知识点select
    function koptions(subjectId,knowId){
        rest.get({
            url: '/admin/know/list',
            data: {subjectId: subjectId},
            success: function (result) {
                var klist = result.data;
                console.log(klist);
                $("#eqknowId").empty();
                for (var i = 0; i < klist.length; i++) {
                    if(knowId == klist[i].id){
                        $("#eqknowId").append("<option value="+klist[i].id+" selected ='selected'>"+klist[i].title+"</option>");
                    }else{
                        $("#eqknowId").append("<option value="+klist[i].id+">"+klist[i].title+"</option>");
                    }

                }
                form.render('select');
            }, fail: function (failResponse) {}
        });
    }

})
