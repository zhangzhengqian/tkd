<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!-- Banner -->
<div class="banner">
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="${ctx }/static/img/banner.jpg" alt="" width="1920" />
      <div class="carousel-caption">
        免费使用
      </div>
    </div>
    <div class="item">
      <img src="${ctx }/static/img/banner2.jpg" alt="" width="1920" />
      <div class="carousel-caption">
        精细化运营
      </div>
    </div>
    <div class="item">
      <img src="${ctx }/static/img/banner3.jpg" alt="" width="1920" />
      <div class="carousel-caption">
        数据运营分析
      </div>
    </div>
    </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</div>
<!-- / Banner -->
<div class="alert alert-default">
球友圈提供各项专业服务。。。
</div>
<div class="article">
  <div class="article-title">
    <div class="container"><h3>入驻流程</h3></div><!-- /container -->    
  </div>  
  <div class="container">
    <div class="row process">
      <div class="col-xs-3"><p></p><h4>签署合作协议</h4></div>
      <div class="col-xs-3"><p></p><h4>球馆信息提交</h4></div>
      <div class="col-xs-3"><p></p><h4>平台审核资质</h4></div>
      <div class="col-xs-3"><p></p><h4>管理中心开通</h4></div>
    </div>
    <div class="row process">
      <div class="col-xs-4 col-xs-offset-4">
    	<a href="${ctx }/register/userForm" class="btn btn-lg btn-warning center-block">我要入驻</a>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
$(function(){
	$("#nav_register").attr("class","active");
});
</script>
