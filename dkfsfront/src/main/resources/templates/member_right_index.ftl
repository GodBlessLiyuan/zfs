<!DOCTYPE html>
<html style="overflow-x: hidden">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<title>会员权益</title>
	<link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
	<link rel="stylesheet" href="${basePath}/css/style.css">
</head>

	<body style="background: #F8F8F8;">

	<div class="container" style="margin-top: 20px;">
		<#--font_rule-->
		<div class="row">
			<div style="margin-left: 10%;margin-bottom: 20px;">
				会员规则如下，未勾选表示无权使用：
			</div>
		</div>

		<div class="row">
			<div class="div_spacing" style="margin: 0 auto;width:90%;">
				<table class="table table-bordered table-hover table-condensed table_rights" style="width:100%">
					<thead>
					<tr>
						<td >会员权益</td>
						<td >非会员</td>
						<td >
							<#--<span class="vip_span">VIP会员</span>-->
							VIP会员
						</td>
					</tr>
					</thead>
					<tbody class="table_freemember">
					<tr>
						<td >应用双开</td>
						<td ><img
								src="${basePath}/images/check_redbag_24.png"/></td>
						<td ><img
								src="${basePath}/images/check_redbag_24.png"/></td>
					</tr>

					<tr>
						<td >应用多开</td>
						<td ><img
								src="${basePath}/images/ico_delete_youshang_24.png"/></td>
						<td ><img
								src="${basePath}/images/check_redbag_24.png"/></td>
					</tr>
					<tr>
						<td >免广告</td>
						<td ><img
								src="${basePath}/images/ico_delete_youshang_24.png"/></td>
						<td ><img
								src="${basePath}/images/check_redbag_24.png"/></td>
					</tr>

					</tbody>
				</table>
			</div>
		</div>

		<div class="row">
			<div class="font_hope" style="margin-left: 10%;">
				更多功能开发中，尽情期待......
			</div>
		</div>

<#--		<div class="row" style="margin-top: 250px;">
			<div class="div_openmember" style="margin: 0 auto;">
				开通会员
			</div>
		</div>-->

	</div>

	</body>
</html>
