$("document").ready(function() {

	/*
	 * // 我的消息靠右(作用于历史记录) function wordRight() { var code = "." +
	 * $("input[type=hidden]").val(); $(code).attr("align", "right");
	 * $("ul:first").parent().scrollTop(10000); }
	 */
	$("#message").focus();
	// 销毁消息1方法
	function destory() {
		console.log("destory");
		console.log("length " + $("ul:first>li").length);
		while ($("ul:first>li").length > 31) {
			$("ul:first>li:first").remove();
			console.log("destorying..");
		}
	};
	// 格式化日期
	Date.prototype.myformat = function() {
		var str = "";
		str += this.getYear() + 1900 + "-" + (this.getMonth() + 1) + "-"
				+ this.getDate() + " " + this.getHours() + ":"
				+ this.getMinutes() + ":" + this.getSeconds();
		return str;
	};

	// 转换对象
	function parseObj(strData) {
		return (new Function("return " + strData))();
	};

	function getFromQueue(ul) {
		var html = "";
		$.post("getQueueServlet", function(data, textStatus) {
			if ("success" == textStatus) {
				var queue = parseObj(data);
				for (var i = 0; i < queue.length; ++i) {
					html += " <li >" + " <table cellpadding='4'>";
					if ($("input[type=hidden]").val() == queue[i].code) {
						html += "<tr align='right'>"
								+ "<td  width='90%'>"
								+ "<div style='margin:3px' align='right'>"
								+ "<font size=1px>"
								+ queue[i].name
								+ new Date(queue[i].message.created_dt.time)
										.myformat()
						+"</font>"
								+ "</div>"
								+ "<div class='sendright'>"
								+ queue[i].message.content
								+ "</div>"
								+ "</td>"
								+ "<td valign='top' width='10%' "
								+ ">"
								+ "<img alt='not find' align='absmiddle' style='width:45px;margin-top:2px;border-radius:45px;box-shadow:0px 0px 3px #7E7E7E;' src='avatar/"
								+ queue[i].code + ".png'/>" + "</td>"

					} else {
						html += "<tr align='left'>"
								+ "<td valign='top' width='10%' "
								+ ">"
								+ "<img alt='not find' align='absmiddle'  style='width:45px;margin-top:2px;border-radius:45px;box-shadow:0px 0px 3px #7E7E7E;' src='avatar/"
								+ queue[i].code
								+ ".png'/>"
								+ "</td>"
								+ "<td  width='90%'>"
								+ "<div style='margin:3px'  align='left'>"
								+ "<font size=1px>"
								+ queue[i].name
								+ new Date(queue[i].message.created_dt.time)
										.myformat()
						+"</font>" + "</div>" + "<div class='sendleft'>"
								+ queue[i].message.content + "</div>" + "</td>"
					}
					html += "</tr>" + "</table>" + "</li>";

				};

				$(ul).append(html);
				// wordRight();
				destory();
				$(ul).parent().scrollTop(1000000);
				console.log("getfromqueue end");
			}
		}, "html");
	};

	$("#history").hide();
	getFromQueue("ul:first");
	
	$(function() {
		var socket = null;
		// 创建socket对象
		// var socketUrl="ws://"+
		// window.location.host+"/webchat0.2/chatSocket";
		window.WebSocket = window.WebSocket || window.MozWebSocket;
		if (window.WebSocket) {
			socket = new WebSocket(socketUrl);
		} else {
			alert('Not support websocket');
		}
		// 连接创建后调用
		socket.onopen = function() {
			alert("成功连接聊天服务器 ");

		};
	$("#logOut").click(function(){socket.close();
			return true;})
		
		// 接收到服务器消息后调用
		socket.onmessage = function(message) {
			console.log(message);console.log("111");
			var data = parseObj(message.data);
			console.log(data);console.log("222");

			// 解析json

			var json = parseObj(data.text);
			console.log("333");
			console.log("jsonsjosn"+json);
			// console.log(json.message.content);
			var str = "";
			var onlineLists = "";

			if ("userNameOnline" in json) {
				// str += "<li style='text-align:center;'>"
				// + json.info + "</li>";
				for (var i = 0; i < json.userNameOnline.length; i++) {
					onlineLists += "<div class='d1'   style='margin:3px'>"+"<a class='a1' href='javascript:;' onclick='sixin(this)'>"+"<img alt='not find' align='absmiddle' style='width:45px;margin-top:2px;border-radius:45px;box-shadow:0px 0px 6px #7E7E7E;' src='avatar/"
							+ json.userCodeOnline[i]
							+ ".png'/>&nbsp;"+"<span>"
							+ json.userNameOnline[i]+"<span>"+"</a></div>";
				}
				$("#onlineLists").empty();
				$("#onlineLists").append(onlineLists);
			} 
			else {
				if ("time" in json) {
					str += "<li style='text-align:center;'>"
							+ new Date(json.time.time).myformat() + "</li>"
				}
				str += " <li >" + " <table cellpadding='4'>";
				if ($("input[type=hidden]").val() == json.code) {
					str += "<tr align='right'>"
							+ "<td  width='90%'>"
							+ "<div style='margin:3px' align='right'>"
							+ "<font size=1px>"
							+ json.name
							+ "</font>"
							+ "</div>"
							+ "<div class='sendright'>"
							+ json.message.content
							+ "</div>"
							+ "</td>"
							+ "<td valign='top' width='10%' class="
							+ json.code
							+ "x"
							+ ">"
							+ "<img alt='not find' align='absmiddle' style='width:45px;margin-top:2px;border-radius:45px;box-shadow:0px 0px 3px #7E7E7E;' src='avatar/"
							+ json.code + ".png'/>" + "</td>"

				} else {
					str += "<tr align='left'>"
							+ "<td valign='top' width='10%' class="
							+ json.code
							+ "x"
							+ ">"
							+ "<img alt='not find' align='absmiddle'  style='width:45px;margin-top:2px;border-radius:45px;box-shadow:0px 0px 3px #7E7E7E;' src='avatar/"
							+ json.code + ".png'/>" + "</td>"
							+ "<td  width='90%'>"
							+ "<div style='margin:3px'  align='left'>"
							+ "<font size=1px>" + json.name + "</font>"
							+ "</div>" + "<div class='sendleft'>"
							+ json.message.content + "</div>" + "</td>"
				}
			}

			str += "</tr>" + "</table>" + "</li>";
			// 添加li前
			destory();
			// div添加after
			$("ul:first").append(str);
			$("ul:first").parent().scrollTop(100000);
			// wordRight();
		};
		// 关闭连接的时候调用
		socket.onclose = function() {
			alert("close");
			socket.close();
			$("#title1").text("未连接");
			return true;
		};
		// 出错时调用
		socket.onerror = function() {
			alert("error");
		};
		// 鼠标及回车发送消息
		$("#sendButton").click(sendMessage);
		$('#message').keydown(function(event) {
					if (event.keyCode == "13") {
						sendMessage();
					}
				});
		// 清空
		$("#reset").click(function() {
					$("#message").val("");
				});
		// 禁用刷新
		$(document).bind("contextmenu", function(e) {
					// alert("sorry! No right-clicking!");
					return false;
				});
		$(document).bind("keydown", function(e) {
					e = window.event || e;
					if (e.keyCode == 116) {
						e.keyCode = 0;
						return false;
					}
				});

		// 发送消息
		function sendMessage() {
			var theString = $.trim($("#message").val());

			// 进行正则转换

			if (theString.length != 0) {

				theString = theString.replace(/\[\/表情([0-9]*)\]/g,
						'<img src=img/face/$1.gif border=0/>');

				theString = theString.replace(new RegExp("\"", "g"), "&quot;");
				theString = theString.replace(new RegExp("\'", "g"), "&#39;");

				theString = theString.replace(new RegExp("\n", "g"), "<br>");
				theString = theString.replace(new RegExp("\r", "g"), "<br>");

				theString = filterMessage(theString);

				socket.send(theString);
			}
			$("#message").val("");
			$("#message").focus();
		}
		
	})
	$("#showHistory").click(function() {
				$("ul:eq(1)>li").remove();
				$("#history").toggle();
				if ($("#history").is(":visible"))
					getFromQueue("ul:eq(1)");
					
			})
	function filterMessage(mess) {
		reg = /s[ ]*b|f[ ]*u[ ]*c[ ]*k|c[ ]*a[ ]*o|傻[ ]*逼/gim;
		mess = mess.replace(reg, "*");
		return mess;
	}
})