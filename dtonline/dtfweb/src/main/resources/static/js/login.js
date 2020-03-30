     //登录验证
     function loginCheck() {
         var login_phone = $("#phone").val();
         var login_password = $("#login_password").val();

         //正则表达式
         var phoneCheck = /^[1][3,4,5,7,8][0-9]{9}$/;
         var passwordCheck = /^(\w){6,20}$/;
         if (login_phone == "") {
             document.getElementById("label_phone").innerHTML = "手机号不能为空";
             return false;
         } else {
             document.getElementById("label_phone").innerHTML = "";
         }
         if (login_password == "") {
             document.getElementById("label_password").innerHTML = "密码不能为空";
             return false;
         } else {
             document.getElementById("label_password").innerHTML = "";

         }
         if (!phoneCheck.test(login_phone)) {
             document.getElementById("label_phone").innerHTML = "格式不正确";
             return false;
         } else {
             document.getElementById("label_phone").innerHTML = " ";

         }
         if (!passwordCheck.test(login_password)) {
             document.getElementById("label_password").innerHTML = "6-20数字或字母";
             return false;
         } else {
             document.getElementById("label_password").innerHTML = " ";

         }



     }

     //登录操作
      function login() {
          loginCheck();


      }