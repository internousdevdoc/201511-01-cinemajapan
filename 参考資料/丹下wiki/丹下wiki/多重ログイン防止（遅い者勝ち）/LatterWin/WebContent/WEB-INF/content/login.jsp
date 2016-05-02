<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ログインページ</title>
  <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
  <!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
  <sb:head includeScripts="false" includeScriptsValidation="false"/>
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="col-sm-offset-2 col-sm-8">
        多重ログイン防止機能の実装<br>
        Application領域にユーザIDとセッションIDのペアを格納して<br>
        セッションIDが同一かどうかで判断してます<br>
        後からログインするとApplication領域に格納されているセッションIDが上書きされて<br>
        先にログインしてるユーザを弾き出す形となってます<br>
        sqlファイルを実行して<br>
        user1@test.com、user2@test.com、user3@test.com<br>
        パスワードは全てpasswordです<br>
      </div>
    </div>
    <div class="row">
      <div class="col-sm-offset-4 col-sm-4">
        <s:actionerror />
      </div>
    </div>
    <div class="row">
      <s:form action="login_process" cssClass="form-horizontal">
        <div class="col-sm-offset-3">
          <s:textfield label="E-MAIL" name="email" placeholder="100文字以内" cssClass=""
                       elementCssClass="col-sm-4"/>
          <s:password label="PASSWORD" name="password" placeholder="8～32文字の半角英数字" cssClass="input-sm"
                      elementCssClass="col-sm-4"/>
          <div class="form-group">
            <div class="col-sm-offset-3 col-md-9">
              <s:submit value="ログイン" cssClass="btn btn-primary"/>
            </div>
          </div>
        </div>
      </s:form>
    </div>
  </div>
  <s:debug/>
</body>
</html>