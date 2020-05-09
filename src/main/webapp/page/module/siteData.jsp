<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!-- 站点数据 -->
<DIV class="tagContent selectTag" id=tagContent0>
	<div style="width:100%; float:left">
		<div
			style="width:870px; height:607px; float:left; background:url(content/skin/css/images/module/module.jpg) no-repeat; font-size:14px; font-weight:bold; line-height:30px; margin-left:50px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="51" style="padding-left:310px;">出水总管压力 <input
						type="text" name="textfield" id="textfield" />&nbsp;MPa
					</td>
				</tr>
				<tr>
					<td><table width="100%" height="204" border="0"
							cellpadding="0" cellspacing="0">
							<tr>
								<td width="25%"><div
										style="width:140px; padding-bottom:10px; float:left; border:1px solid #bbb; margin-top:100px; padding-top:82px; padding-left:50px;">
										真空泵<Br />真空形成状态<Br />1号泵&nbsp;<img
											src="content/skin/css/images/module/icon.jpg" width="15"
											height="20" /><br />2号泵&nbsp;<img
											src="content/skin/css/images/module/icon.jpg" width="15"
											height="20" />
									</div></td>
								<td width="31%"><div
										style="width:150px; float:left; margin-bottom:100px;">
										泵后压力<Br /> <input name="" type="text" style="width:110px;" />&nbsp;MPa
									</div></td>
								<td width="44%"><div
										style="width:150px; float:left; margin-bottom:100px;">
										泵后压力<Br /> <input name="" type="text" style="width:110px;" />&nbsp;MPa
									</div></td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="26%">进水量<Br />瞬时流量：<input name="" type="text"
									style="width:80px;" />&nbsp;m3/s<br />进水累计流量：<input name=""
									type="text" style="width:80px;" />&nbsp;m3<br />瞬时流量差值：<input
									name="" type="text" style="width:80px;" />&nbsp;m3
								</td>
								<td width="29%" valign="top">2#水泵<Br />运行频率：<input name=""
									type="text" style="width:80px;" />&nbsp;Hz<br />变频电流：<input
									name="" type="text" style="width:80px;" />&nbsp;A<br />变频电压：<input
									name="" type="text" style="width:80px;" />&nbsp;V
								</td>
								<td width="45%" valign="top">1#水泵<Br />运行频率：<input name=""
									type="text" style="width:80px;" />&nbsp;Hz<br />变频电流：<input
									name="" type="text" style="width:80px;" />&nbsp;A<br />变频电压：<input
									name="" type="text" style="width:80px;" />&nbsp;V<br />液位：<input
									name="" type="text" style="width:80px;" />&nbsp;m<br />余氯：<input
									name="" type="text" style="width:80px;" />&nbsp;mg/L
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td style="padding-top:40px;">进水总管压力：<input name=""
						type="text" style="width:110px;" />&nbsp;MPa
					</td>
				</tr>
			</table>

		</div>
		<div
			style="width:400px; float:right; padding-left:30px; margin-right:150px;">
			<img src="content/skin/css/images/module/video.jpg" width="500"
				height="290" /><br /> <br /> <img
				src="content/skin/css/images/module/video.jpg" width="500"
				height="290" />
		</div>
	</div>
	<div
		style="width:95%; float:left; margin-top:20px; background:#e9eaeb; padding:20px; text-align:left">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td
					style="font-size:14px; font-weight:bold; color:#4a4a4a; padding-bottom:15px;">站点控制</td>
			</tr>
			<tr>
				<td>泵：<input type="radio" name="radio" id="radio" value="radio" />开启&nbsp;&nbsp;<input
					type="radio" name="radio" id="radio" value="radio" />关闭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;变频器频率：<input
					type="radio" name="radio" id="radio" value="radio" />1级&nbsp;&nbsp;<input
					type="radio" name="radio" id="radio" value="radio" />2级&nbsp;&nbsp;<input
					type="radio" name="radio" id="radio" value="radio" />3级&nbsp;&nbsp;<input
					type="radio" name="radio" id="radio" value="radio" />4级&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;加氯控制：<input
					type="radio" name="radio" id="radio" value="radio" />增加&nbsp;&nbsp;<input
					type="radio" name="radio" id="radio" value="radio" />减少
				</td>
			</tr>
			<tr>
				<td style="padding-top:15px;"><input name="" type="button"
					value="确 定"
					style="width:60px; height:21px; background:#369fbc; float:left; line-height:21px; text-align:center; color:#fff; border-radius:6px; border:0px;" />
					<input name="" type="button" value="取 消"
					style="width:60px; height:21px; background:#727273; float:left; line-height:21px; text-align:center; color:#fff; border-radius:6px; border:0px; margin-left:15px;" /></td>
			</tr>
		</table>

	</div>
	<div style="clear:both"></div>
</DIV>