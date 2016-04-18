<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<style type="text/css">
    #welcome_robot{
        width: 300px;height: 100%;margin:0 auto;margin-top: 80px;position: relative;
    }
    #welcome_robot>div{
        position: absolute;
        border: 5px solid #fff;
        font-size: 20px;
        font-weight: bold;
        color: #fff;
        border-radius: 40%;
    }
    #r_head{
        left: 100px;top:0;
        width: 100px;height: 100px;line-height: 100px;display: none;
    }
    #r_hand_left{
        left: 10px;top:110px;
        width: 40px;-webkit-transform:rotate(45deg);height: 120px;display: none;
    }
    #r_hand_right{
        right: 10px;top:110px;
        width: 40px;-webkit-transform:rotate(315deg);height: 120px;display: none;
    }
    #r_body{
        left: 75px;top:110px;
        width:150px;height: 150px;padding: 10px 5px;display: none;
    }
    #r_foot_left{
        left: 75px;top:260px;-webkit-transform:rotate(30deg);
        width: 40px;height: 120px;display: none;
    }
    #r_foot_right{
        right: 95px;top:260px;
        width:40px;height: 120px;display: none;
    }
    .w_box{
        font-weight: bold;
        font-size: 20px;
        border-radius: 5px;
        padding: 13px 8px;
        margin:30px 40px;
        background-color: #e4eefc;
    }

</style>
<div id="jingle_welcome" class="slide">
    <div class="slideWrapper" style="color: #fff;font-size: 1.5em;text-shadow: 1px 2px 1px rgba(0,0,3,0.5)">
        <div style="text-align: center; height: 100%;background-color: rgb(97, 179, 81)">
            <div id="welcome_jingle" style="height: 140px;width: 140px;border-radius: 50%;border: 8px solid #fff;font-size: 40px;font-weight:bold;line-height: 124px;margin: 40px auto">
                Jingle
            </div>
            <div style="margin-top: 100px;font-weight: 900;">�ƶ�Web�������</div>
        </div>
        <div style="text-align: center;height: 100%;background-color: #9B59B6;">
            <div id="welcome_robot">
                <div id="r_head">Jinlge</div>
                <div id="r_hand_left">��ӭ����</div>
                <div id="r_body" style="border-radius: 25%;">��ӭ��������Ϊhtml,���ⷢ�Ӱɣ�</div>
                <div id="r_hand_right">��ӭ����</div>
                <div id="r_foot_left">��ק����</div>
                <div id="r_foot_right">��ը��Ŷ</div>
            </div>
        </div>
        <div style="text-align: center;height: 100%;background-color: #95A5A6">
            <div id="w_box_1" class="w_box alizarin" style="margin-top: 80px;">WebApp���</div>
            <div id="w_box_2" class="w_box violet">���ʺ���������Hybrid Mobile App</div>
            <div id="w_box_3" class="w_box peter-river">��õ�Mobile���</div>
            <div id="w_box_4" class="w_box emerland">������߻���Ŭ��ѧϰ+����ing</div>
        </div>
        <div style="text-align: center;height: 100%;background-color: #E67E22;position:relative;">
            <div style="margin-top: 100px;font-weight: 900;">С�����ǣ��п����<br/>������<br/>�����˷���������� <span style="font-size: 2px;color: rgb(233, 153, 83)">app</span></div>
            <button id="welcome_btn" onclick="Jingle.Welcome.hide()">���Ƿ���</button>
        </div>
    </div>
</div>