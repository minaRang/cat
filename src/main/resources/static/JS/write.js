
  $(function(){
    $('#btn-title-tips').popover({
        // container='.popover-title-tips',
        template:'<div class="popover-title-tips open"><h1 class="title">( ° ∀ ° )ﾉﾞ<br>좋은 제목 만들기</h1><div class="popover-body"><h6 class="title--sub"><ul><li></li><li></li></ul></h6><h6 class="title--sub"><ul><li></li><li></li><li></li></ul></h6><h6 class="title--sub"><ul><li></li><li></li><li></li></ul></h6></div></div>',
        placement:'bottom',
        animation: true,
        html : true,
        content: function() {
          var content = $(this).attr("data-popover-content");
          return $(content).children(".popover-body").html();
        }, 
        // title: function() {
        //   var title = $(this).attr("data-popover-content");
        //   return $(title).children(".title").html();
        // }
    });
});

$(function(){
  $('#btn-question-tips').popover({
      placement:'bottom',
      animation: true,
      html : true,
      template:'<div class="popover-question-tips open"><h1 class="title">작성 팁</h1><div class="popover-body"><h6 class="title--sub"></h6><ul><li></li><li></li></ul><h6 class="title--sub"></h6><ul><li></li></ul><h6 class="title--sub"></h6><ul><li></li><li></li><li></li></ul><h6></h6></div></div>',
        content: function() {
        var content = $(this).attr("data-popover-content");
        return $(content).children(".popover-body").html();
      },
      title: function() {
        var title = $(this).attr("data-popover-content");
        return $(title).children(".title").html();
      }
  });
});

$(function(){
  $('#btn-markdown-tips').popover({
      placement:'bottom',
      animation: true,
      html : true,
      // template: '<div class="popover-title-tips"><h1 class="title"></h1><div class="popover-content"></div></div>',
      template:'<div class="popover-markdown-tips open"><h1 class="title">Markdown 활용</h1><div class="popover-body"><h6 class="title--sub"></h6><div class="sample"></div><h6 class="title--sub"></h6><div class="sample"></div><h6 class="title--sub"></h6><div class="sample"></div></div></div>',
      
      content: function() {
        var content = $(this).attr("data-popover-content");
        return $(content).children(".popover-body").html();
      },
      title: function() {
        var title = $(this).attr("data-popover-content");
        return $(title).children(".title").html();
      }
  });
});
//const { Editor } = toastui;
//const { codeSyntaxHighlight } = Editor.plugin;
//const content = [].join('\n');
//function uploadImage(blob) {
//    let formData = new FormData();
//    formData.append('image', blob);
//    return axios('http://localhost:8080/questions', {
//        method: 'Post',
//        data: formData,
//        headers : {'Content-type' : 'multipart/form-data' }
//
//    }).then(function (response) {
//        if (response.data) {
//              if(this.state.thumbnailcheck === 0) {
//                this.setState({
//                  thumbnailchekc : 1,
//                  thumbnail : response.data
//                })
//              }
//                return response.data;
//            }
//            throw new Error('Server or network error');
//            });
//        };
//function onAddImageBlob(blob, callback) {
//    this.uploadImage(blob)
//        .then(function(response){
//            if (!response) {
//                throw new Error('Validation error');
//            }
//            else callback(response, "alt text");
//        }).catch(error => {
//            console.log(error);
//        });
//};
////async function uploadImage (blob) {
////	const formData = new FormData();
////    formData.append('image', blob);
////
////    // 서버로부터 이미지 주소 받아옴
////    const url = await fetch('http://localhost:8080/new', {
////    	method: 'POST',
////        body : formData
////    });
////
////    return url;
////};
// const editor = new Editor({
//    el: document.querySelector('#editor'),
//    previewStyle: 'vertical',
//    height: '500px',
//    initialEditType: 'markdown',
//        initialValue: content,
//    plugins: [[codeSyntaxHighlight, { highlighter: Prism }]],
//    hooks:{ addImageBlobHook: async function (blob, callback){
//            const img_url = await uploadImage(blob);
//            callback(img_url, 'alt_text');
//            return false
//            }}
//});
//
//
////const viewer = Editor.factory({
////    el: document.querySelector('#viewer'),
////    viewer: true,
////    height: '500px',
////    initialEditType: 'markdown',
////    initialValue: content,
////    plugins: [[codeSyntaxHighlight, { highlighter: Prism }]]
////});
//
////viewer.setMarkdown(editor.getMarkdown());
//
$(function(){
var list=[];
    $('#tags').autocomplete({
        appendTo:"#tag-form-group",
        source: function(request,response) {
            $.ajax({
                url:"/searchTag",
                type:"GET",
                dataType:"json",
                data:{tagName:request.term},
                success: function(data){
                    response(
                        $.map(data, function(item){
                            return{
                                label: item.tagName,
                                value:item.tagName
                            };
                        })
                    );
                }
            });
       },
        multiselect:true,
        minLength:1,
        close:function(evt){}
    });
});
$("#btn-submit-question").click(function(){
 var token = $("meta[name='_csrf']").attr("content");
 var header = $("meta[name='_csrf_header']").attr("content");
    var tagNameList =[];
    $('.ui-autocomplete-multiselect-item').each(function(i){
        tagNameList.push($(this).text());
    });
    if(tagNameList.length>5){
        alert("태그는 최대 5개만 선택 가능합니다.");
        return;
    }
    var objParams={
        "boardTitle" : $("#question_title").val(),
        "boardContent" : $("#question_content").val(),
        "tagNameList" : tagNameList
    };
    console.log(objParams);
    $.ajax({
        url:"/form",
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        type:"post",
        data : objParams,
        beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success:function(data){
            window.location = data;
        },
        error:function(jqXHR, textStatus, errorThrown){
                console.log("code:"+textStatus+":"+errorThrown);
        }
    });
});