var main={ //먼저 로딩된 js를 덮어쓰게 될 때 함수명이 중복될 위험을 방지함. 해당 객체 안에서만 function이 유효함
    init:function(){
        var _this = this;
        $('#btn-save').on('click',function(){
            _this.save();
        });
    },
    save:function(){
        var data = {
            title:$('#title').val(),
            author:$('#author').val(),
            content:$('#content').val()
        };
        $.ajax({
            type:'POST',
            url:'/api/v1/posts',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다.')
            window.location.href='/'; //글 등록이 성공하면 메인페이지로 이동
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};
main.init();