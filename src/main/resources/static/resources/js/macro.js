$(function () {
    $('#new-macro-button').on('click', function () {
        window.location.href = "macro/new";
    });

    $('#back').on('click', function () {
            window.location.href = "/";
    });

    $('[id^="button_run_"]').click(function(){
        var id = $(this).attr("id").replace('button_run_', '');
        $('#response').val("");
        $.ajax({
            type: 'POST',
            url: '/controller/macro/' + String(id),
            success: function(msg){
                $('#response').val(msg);
            }
        });
    });

    $('[id^="button_delete_"]').click(function(){
            var id = $(this).attr("id").replace('button_delete_', '');
            $('#response').val("");
            $.ajax({
                type: 'DELETE',
                url: '/controller/macro/' + String(id),
                success: function(msg){
                    $('#response').val(msg);
                    window.location.href = "/macroes";
                }
            });
    });

    $('[id^="button_edit_"]').click(function(){
        var id = $(this).attr("id").replace('button_edit_', '');
        window.location.href = "/macro/edit/" + String(id);
    });
});
