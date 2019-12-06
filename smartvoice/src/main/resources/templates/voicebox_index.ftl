<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>语音盒子</title>
	<link rel="stylesheet" href="./share/css/style.css">

</head>

<body onload="loadMp3()">

    <div class="div_main">
        <div class="div_voice">
            <ul class="content">
            </ul>
        </div>

        <div class="div_tips">
            注：此录音只保存72小时，72小时后将删除，
            本语音由砖助智能助手提供技术支持。
        <#--    <a class="div_a" href="" >点击下载</a>-->
        </div>
    </div>

</body>

<script type="text/javascript">

	function loadMp3() {
	    if(res.status == '1000'){
            var videoArr = ${res.data.voices};
            for(var i=0;i<videoArr.length;i++){
                var content = document.getElementsByTagName("ul")[0];
                content.innerHTML += "<li><video controls=\"controls\" controlsList=\"nodownload\"><source src='"+videoArr[i]+"'type=\"video/mp4\"/></li>"
            }
        }else {
	        return;
        }
	}
</script>
</html>
