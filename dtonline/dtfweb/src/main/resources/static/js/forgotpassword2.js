 function passwordCheck() {
     var password = $("#password").val();
     var password2 = $("#password2").val();
     var passwordCheck = /^(\w){6,20}$/;
     if (password == "") {
         alert("密码不能为空")
         return false;
     }
     if (password2 == "") {
         alert("密码不能为空")
     }
     if (password != password2) {
         alert("密码不一致")
         return false;
     }
     if (!passwordCheck.test(password)) {
         alert("密码必须六位以上")
         return false;
     }
 }

 function gopassword() {
 }