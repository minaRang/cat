
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
const Editor = toastui.Editor;
const editor = new Editor({
  el: document.querySelector('#editor'),
  height: '600px',
  initialEditType: 'markdown',
  previewStyle: 'vertical'
});

editor.getHtml();
