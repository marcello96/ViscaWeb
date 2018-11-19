$(function () {

    $('.visca-position-up').on('click', function () {
        var command = 'UP';
        var panSpeed = $('#pan-speed').eq(0).val();
        var tiltSpeed = $('#tilt-speed').eq(0).val();
        $(".commandContent").append(command).append('_').append(tiltSpeed).append(' ');
        var data = {
            'command': command,
            'panSpeed': panSpeed,
            'tiltSpeed': tiltSpeed
        };
       postAPI('/position', data);
    });

    $('.visca-position-down').on('click', function () {
        var command = 'DOWN';
        var panSpeed = $('#pan-speed').eq(0).val();
        var tiltSpeed = $('#tilt-speed').eq(0).val();
        $(".commandContent").append(command).append("_").append(tiltSpeed).append(' ');
        var data = {
            'command': command,
            'panSpeed': panSpeed,
            'tiltSpeed': tiltSpeed
        };
        postAPI('/position', data);
    });

    $('.visca-position-left').on('click', function () {
        var command = 'LEFT';
        var panSpeed = $('#pan-speed').eq(0).val();
        var tiltSpeed = $('#tilt-speed').eq(0).val();
        $(".commandContent").append(command).append("_").append(panSpeed).append(' ');
        var data = {
            'command': command,
            'panSpeed': panSpeed,
            'tiltSpeed': tiltSpeed
        };
        postAPI('/position', data);
    });

    $('.visca-position-right').on('click', function () {
        var command = 'RIGHT';
        var panSpeed = $('#pan-speed').eq(0).val();
        var tiltSpeed = $('#tilt-speed').eq(0).val();
        $(".commandContent").append(command).append("_").append(panSpeed).append(' ');
        var data = {
            'command': command,
            'panSpeed': panSpeed,
            'tiltSpeed': tiltSpeed
        };
        postAPI('/position', data);
    });

    $('.visca-zoom-tele').on('click', function () {
        var command = 'ZOOM_TELE';
        var speed = $('#zoom-tele-speed').eq(0).val();
        $(".commandContent").append(command).append("_").append(speed).append(' ');
        var data = {
            'command': command,
            'speed': speed
        };
        postAPI('/zoom-tele', data);
    });

    $('.visca-zoom-wide').on('click', function () {
        var command = 'ZOOM_WIDE';
        var speed = $('#zoom-wide-speed').eq(0).val();
        $(".commandContent").append(command).append("_").append(speed).append(' ');
        var data = {
            'command': command,
            'speed': speed
        };
        postAPI('/zoom-wide', data);
    });

    $('.visca-other-home').on('click', function () {
        var command = 'HOME';
        $(".commandContent").append(command).append(' ');
        var data = {
           'command': command
        };
        postAPI('/home', data);
    });

     $('.visca-other-wait').on('click', function () {
        var command = 'WAIT';
        var time = $('#time-waiting').eq(0).val();
        $(".commandContent").append(command).append('_').append(time).append(' ');
        var data = {
               'command': command,
               'time': time
        };
        postAPI('/wait', data);
     });

     $('.visca-change-address').on('click', function () {
             var command = 'CHANGE_ADDRESS';
             var newAddress = $('#change-address').eq(0).val();
             $(".commandContent").append(command).append('_').append(newAddress).append(' ');
             var data = {
                    'command': command,
                    'newAddress': newAddress
             };
             postAPI('/address', data);
     });

    $('.visca-submit-command').on('click', function () {
        var data = {
            'macroName' : $('.commandName').eq(0).val(),
            'macroContent': $('.commandContent').eq(0).val()
        };
        postAPI('/macro/add', data);
    });

    $('[id^="button_"]').click(function(){
         var id = $(this).attr("id").replace('button_', '');
         var data = {
            'macroName' : id
         };
         postAPI('/macro/run', data)
    });

});

function postAPI(endpoint, data) {
    $('.response').val('');
    $.ajax({
        type: 'POST',
        url: '/controller' + endpoint,
        data: data,
        success: function(msg){
            $('.response').val(msg);
        }
    });
}