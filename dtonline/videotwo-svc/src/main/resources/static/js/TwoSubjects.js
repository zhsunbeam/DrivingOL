// axios.get("http://localhost:8080/video/queryTwoSubjectsVideo")
// .then(
//     function(dataInfo){
//         alert(JSON.stringify(dataInfo))
//         var vue = new Vue({
//             el:"#app",
//             data:{
//                 dataArr:dataInfo
//             }
//         });
//     },
//     function(err){
//         alert(err)
//     });
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/video/queryTwoSubjectsVideo",
        data:{
        },
        dataType:"JSON",
        success:function(dataInfo) {
            var vue = new Vue({
                el:"#app",
                data:{
                    dataArr:dataInfo
                }
            });
        },
        error: function(XMLHttpRequest, textStatus, errorThrow) {
            alert("error");
            debugger;
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });