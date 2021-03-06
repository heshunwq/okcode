<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>

<div id="breadcrumb">
	<a href="${ctx}/console/home"><i class="icon-home"></i> 首页</a>
	<a href="${ctx}/console/baseConfig"><i class="icon-cog"></i> 系统设置</a>
	<a href="${ctx}/console/baseConfig">基本设置</a>
	<a class="current" href="#">基本信息</a>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title">
					<ul class="nav nav-tabs">
						<li class="active">
						<a href="${ctx}/console/baseConfig">基本信息</a>
						</li>
						<li class="">
						<a href="${ctx}/console/baseConfig/emailConfig">邮箱配置</a>
						</li>
						<li class="">
						<a href="${ctx}/console/baseConfig/runStatus">运行状态</a>
						</li>
					</ul>
				</div>
				<div class="widget-content">
					<div class="row-fluid">
						<div class="span8">
						
							<form id="fform" class="form-horizontal" method="get" action="#">
								<div class="control-group">
									<label class="control-label" for="systemName"><span class="red">*</span> 网站名称</label>
									<div class="controls">
										<input type="text" name="systemName" value="${dto.systemName}" placeholder="网站名称">
									</div>	
								</div>
								<div class="control-group">
									<label class="control-label" for=""><span class="red">*</span> 网站LOGO</label>
									<div class="controls">
										<input type="text" id="logo-img-ipt" name="siteLogoUrl" value="${dto.siteLogoUrl}" placeholder="网站LOGO"/> <input type="button" class="btn" id="view-logo-img-btn" value="浏览图片" />
									</div>	
								</div>
								<div class="control-group">
									<label class="control-label" for="siteUrl"><span class="red">*</span> 网站网址</label>
									<div class="controls">
										<input type="text" name="siteUrl" value="${dto.siteUrl}" placeholder="网站网址">
										<span class="help-block">建议填写检测到的网址：<span id="domain"></span></span>
										<script type="text/javascript">
										var thisurl = window.location+"";
										var thisdomain = thisurl.substring(0,thisurl.indexOf("console/baseConfig")); 
										document.getElementById("domain").appendChild(document.createTextNode(thisdomain));
										</script>
									</div>	
								</div>
								<div class="control-group">
									<label class="control-label" for="siteKeyword"><span class="red">*</span> 网站关键词</label>
									<div class="controls">
										<input type="text" name="siteKeyword" value="${dto.siteKeyword}" placeholder="网站关键词">
										<span class="help-block">会追加显示在title中，多个关键词请用竖线|隔开，建议3到4个关键词</span>
									</div>	
								</div>
								<div class="control-group">
									<label class="control-label" for="siteDescription"><span class="red">*</span> 网站描述</label>
									<div class="controls">
										<textarea rows="4" cols="4" placeholder="网站描述" name="siteDescription">${dto.siteDescription}</textarea>
										<span class="help-block">显示在搜索引擎搜索结果中的描述文字，用于介绍网站，吸引浏览者点击，100字以内</span>
									</div>	
								</div>
								<div class="form-actions">
									<button class="btn btn-primary" id="save-btn" type="button" onclick="doSave()">保存</button>
								</div>
							</form>
						</div>
						<div class="span4">
						</div>
					</div>
				</div>
			</div>					
		</div>
	</div>
</div>
<script type="text/javascript">
function doSave() {
	if ($('#fform').valid()) {
		$.ajax2({
			url : "${ctx}/console/baseConfig/doSaveBaseConfig",
			data : $("#fform").serialize(),
			btn : '#save-btn'
		});
	}
}
$(function() {
	$('#view-logo-img-btn').on("click", function(){
		var finder = new CKFinder();
		//finder.basePath = Drupal.settings.basePath + Drupal.settings.ckfinderPath + '/';
		finder.selectActionFunction = function(fileUrl) {
				$('#logo-img-ipt').val(fileUrl);
		};
		finder.popup();
	});
	
	$('#fform').validate({
		rules: {
			systemName: {
				required: true,
				maxlength:1000
			},
			siteLogoUrl: {
				maxlength:1000
			},
			siteUrl: {
				required: true,
				maxlength:1000
			},
			siteKeyword: {
				required: true,
				maxlength:1000
			},
			siteDescription: {
				required: true,
				maxlength:1000
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
});
</script>