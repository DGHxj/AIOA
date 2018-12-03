var token = "";
$(function() {
	var w = 320, h = 240;
	var pos = 0, ctx = null, saveCB, image = [];

	var canvas = document.createElement("canvas");
	canvas.setAttribute('width', w);
	canvas.setAttribute('height', h);

	console.log(canvas.toDataURL);
	if (canvas.toDataURL) {

		ctx = canvas.getContext("2d");

		image = ctx.getImageData(0, 0, w, h);

		saveCB = function(data) {

			var col = data.split(";");
			var img = image;

			for (var i = 0; i < w; i++) {
				var tmp = parseInt(col[i]);
				img.data[pos + 0] = (tmp >> 16) & 0xff;
				img.data[pos + 1] = (tmp >> 8) & 0xff;
				img.data[pos + 2] = tmp & 0xff;
				img.data[pos + 3] = 0xff;
				pos += 4;
			}

			if (pos >= 4 * w * h) {
				ctx.putImageData(img, 0, 0);
				$.ajax({
					type : "post",
					url : "/face/url",
					data : {
						type : "pixel",
						image : canvas.toDataURL("image/png")
					},
					dataType : "html",
					success : function(data) {
						var str = data.split(",");
						console.log("====" + data);
						pos = 0;
						$("#img").attr("src", "");
						$("#img").attr("src", str[0]);
						token = str[1];
					}
				});
			}
		};

	} else {

		saveCB = function(data) {
			image.push(data);

			pos += 4 * 320;

			if (pos >= 4 * 320 * 240) {
				$.post("/face/url", {
					type : "pixel",
					image : image.join('|')
				});
				pos = 0;
			}
		};
	}

	$("#webcam").webcam({
		width : w,
		height : h,
		mode : "callback",
		swffile : "/assets/js/jscam_canvas_only.swf",

		onSave : saveCB,

		onCapture : function() {
			webcam.save();
		},

		debug : function(type, string) {
			console.log(type + ": " + string);
		}
	});
});

// 拍照
function savePhoto() {
	webcam.capture(1);
}

// 删除当前照片
function delPhoto() {
	$("#img").attr("src", "");
}

// 存入user
function saveUser() {

	var userPhone = $("#userPhone").val();

	var userEmail = $("#userEmail").val();

	var userId = $("#userId").val();

	$.ajax({
		type : "post",
		url : "/face/regist",
		data : {
			"userId" : userId,
			"userPhone" : userPhone,
			"userEmail" : userEmail,
			"imgToken" : token
		},
		dataType : "json",
		success : function(data) {
			alert(data);
			window.location.reload();
			$.getScript('assets/js/face.js');
			window.location.href = 'admin-index';
		}
	});
}