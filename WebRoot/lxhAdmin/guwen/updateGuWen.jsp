<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
request.setAttribute("path",path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>更新咨询顾问</title>
    <!-- Bootstrap -->
    <link href="<%=path %>/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path %>/static/css/zaiXianCePing.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="<%=path %>/static/js/html5shiv.min.js"></script>
    <script src="<%=path %>/static/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="../adminNav.jsp"></jsp:include>
<div class="container-fluid main">
    <div class="row">
        <div class="col-xs-12 col-sm-9 col-md-8 col-sm-offset-2 col-md-offset-2 right_content">
            <div class="row tianXieXinXi">
                <h4>更新咨询顾问</h4>
                <div class="col-md-12 col-md-offset-3 content_jiChu">
                    <form class="form-horizontal" id="form" method="post" enctype="multipart/form-data" action="${path}/guwen/update.shtml">
                    	<input type="hidden" value="${guWen.guid}" name="guWenGuid">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">顾问姓名</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="name" name="name" value="${guWen.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="indexMiaoShu" class="col-sm-2 control-label">首页使用的描述语句</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="indexMiaoShu" name="indexMiaoShu" value="${guWen.indexMiaoShu}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="indexPhoto" class="col-sm-2 control-label">首页图片-放首页时用到</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="file" class="form-control" id="indexPhoto" name="indexPhoto">
                                <span>尺寸自己控制，适合首页即可，图片要小，要清晰</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="indexXyBj" class="col-sm-2 control-label">首页选用</label>
                            <div class="col-sm-8 col-md-6">
                                <select class="form-control" name="indexXyBj" id="indexXyBj">
                                	<c:if test="${guWen.indexXyBj==0}">
                                		<option value="0" selected="selected">不选用</option>
										<option value="1">选用</option>
                                	</c:if>
                                	<c:if test="${guWen.indexXyBj==1}">
                                		<option value="1" selected="selected">选用</option>
                                		<option value="0">不选用</option>
                                	</c:if>
								</select>
                            </div>
	                    </div>
	                    <div class="form-group">
                            <label for="indexWeiZhi" class="col-sm-2 control-label">首页选用时，放置的位置</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="indexWeiZhi" name="indexWeiZhi" placeholder="整数值" value="${guWen.indexWeiZhi}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="photo" class="col-sm-2 control-label">列表图片</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="file" class="form-control" id="photo" name="photo">
                                <span>尺寸建议：380*265</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="shengFeng" class="col-sm-2 control-label">身份</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="shengFeng" name="shengFeng" placeholder="eg：留学顾问" value="${guWen.shenFen}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ruHangShiJian" class="col-sm-2 control-label">入行时间</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="ruHangShiJian" name="ruHangShiJian" placeholder="eg：2012-11" value="${guWen.ruHangShiJian}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="zixunxingshi" class="col-sm-2 control-label">咨询形式</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="zixunxingshi" name="zixunxingshi" placeholder="多个形式以顿号隔开" value="${guWen.ziXunXingShi}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="shanchangguojia" class="col-sm-2 control-label">擅长国家</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="shanchangguojia" name="shanchangguojia" placeholder="多个国家以顿号隔开" value="${guWen.shanChangGuoJia}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="shanChangZhuanYe" class="col-sm-2 control-label">擅长专业</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="shanChangZhuanYe" name="shanChangZhuanYe" placeholder="多个形式以顿号隔开" value="${guWen.shanChangZuanYe}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="zhiYeXingTiao" class="col-sm-2 control-label">职业信条</label>
                            <div class="col-sm-8 col-md-6">
                                <textarea class="form-control" rows="2" id="zhiYeXingTiao" name="zhiYeXingTiao">${guWen.zhiYeXinTiao}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="xueYuanPingJia" class="col-sm-2 control-label">学员评价</label>
                            <div class="col-sm-8 col-md-6">
                                <textarea class="form-control" rows="3" id="xueYuanPingJia" name="xueYuanPingJia">${guWen.xueYuanPinJia}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="renqi" class="col-sm-2 control-label">人气</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="renqi" name="renqi" placeholder="整数值" value="${guWen.renQi}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="xinJiPingJia" class="col-sm-2 control-label">星级评价-几颗星</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="xinJiPingJia" name="xinJiPingJia" placeholder="填写1-5的整数" value="${guWen.xinJiPingJia}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="weekQianDanLiang" class="col-sm-2 control-label">周-签单量</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="weekQianDanLiang" name="weekQianDanLiang" placeholder="整数值"  value="${guWen.weekQianDanLiang}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="monthQianDanLiang" class="col-sm-2 control-label">月-签单量</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="monthQianDanLiang" name="monthQianDanLiang" placeholder="整数值" value="${guWen.monthQianDanLiang}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="yearQianDanLiang" class="col-sm-2 control-label">年-签单量</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="yearQianDanLiang" name="yearQianDanLiang" placeholder="整数值"  value="${guWen.yearQianDanLiang}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dianhua" class="col-sm-2 control-label">联系电话</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="dianhua" name="dianhua" placeholder="多个以顿号隔开" value="${guWenContent.dianHua}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="QQ" class="col-sm-2 control-label">QQ</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="QQ" name="QQ" value="${guWenContent.QQ}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="weiXin" class="col-sm-2 control-label">微信号</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="weiXin" name="weiXin" value="${guWenContent.weiXin}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="boKeUrl" class="col-sm-2 control-label">博客链接</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="text" class="form-control" id="boKeUrl" name="boKeUrl" value="${guWenContent.boKeUrl}">
                            </div>
                        </div>
                         <div class="form-group">
                            <label for="jieshao" class="col-sm-2 control-label">详细介绍</label>
                            <div class="col-sm-8 col-md-6">
                                <textarea class="form-control" rows="5" id="jieshao" name="jieshao">${guWenContent.jieShao}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="picture" class="col-sm-2 control-label">内容页面的大图片</label>
                            <div class="col-sm-8 col-md-6">
                                <input type="file" class="form-control" id="picture" name="picture">
                                <span>尺寸建议：700*350</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 col-md-8 text-center">
                                <button type="submit" class="btn btn-warning" id="submit">提交修改</button>
                                <br>
                                <span>${msg}</span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--提示窗口-->
<div class="modal fade" id="tiShi">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <p></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<jsp:include page="../adminFooter.jsp"></jsp:include>
<script src="<%=path %>/static/js/jquery-1.11.0.js"></script>
<script src="<%=path %>/static/js/bootstrap.min.js"></script>
<script>
    $(function(){
        $("#submit").click(function(){
            var name = $("#name").val();
            var jieshao = $("#jieshao").val();
            if(name==""){
                $(".modal-body p").text("名称没填");
                $('#tiShi').modal({
                    keyboard: false
                });
            }else if(jieshao==""){
                $(".modal-body p").text("详细介绍没填");
                $('#tiShi').modal({
                    keyboard: false
                });
            }else{
            	return ture;
            }

			return false;
        });


    });
</script>
</body>
</html>