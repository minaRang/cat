$("#question-fondup").click(function(){
 var token = $("meta[name='_csrf']").attr("content");
 var header = $("meta[name='_csrf_header']").attr("content");
    var objParams={
        "fond": 1,
        "question-boardIdx" : $("#question-boardIdx").val()
    };
    console.log(objParams);
    $.ajax({
        url:"/board/qusetion_fond",
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        type:"PUT",
        data : objParams,
        beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success:function(data){
            console.log(data);
            if(data.length==0){
                alert("로그인이 필요합니다.");
            }
            else{
                $("#question-vote-sum").text(data[2]);
                $("#question-fondup").children(".hc-icons-thumb-up").attr('class',data[0]);
                $("#question-fonddown").children(".hc-icons-thumb-down").attr('class',data[1]);
            }
        },
        error:function(jqXHR, textStatus, errorThrown){
                console.log("code:"+textStatus+":"+errorThrown);
        }
    })
});

$("#question-fonddown").click(function(){
 var token = $("meta[name='_csrf']").attr("content");
 var header = $("meta[name='_csrf_header']").attr("content");
    var objParams={
        "fond": -1,
        "question-boardIdx" : $("#question-boardIdx").val()
    };
    console.log(objParams);
    $.ajax({
        url:"/board/qusetion_fond",
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        type:"PUT",
        data : objParams,
        beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success:function(data){
            $("#question-vote-sum").text(data[2]);
            $("#question-fondup").children(".hc-icons-thumb-up").attr('class',data[0]);
            $("#question-fonddown").children(".hc-icons-thumb-down").attr('class',data[1]);
        },
        error:function(jqXHR, textStatus, errorThrown){
                console.log("code:"+textStatus+":"+errorThrown);
        }
    })
});