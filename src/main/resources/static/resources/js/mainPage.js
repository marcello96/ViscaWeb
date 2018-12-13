$(function () {

    $('.visca-position-up').on('click', function () {
        var command = 'UP';
        var panSpeed = $('#pan-speed').eq(0).val();
        var tiltSpeed = $('#tilt-speed').eq(0).val();
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
        var data = {
            'command': command,
            'speed': speed
        };

        postAPI('/zoom-tele', data);
    });

    $('.visca-zoom-wide').on('click', function () {
        var command = 'ZOOM_WIDE';
        var speed = $('#zoom-wide-speed').eq(0).val();
        var data = {
            'command': command,
            'speed': speed
        };

        postAPI('/zoom-wide', data);
    });

    $('.visca-other-home').on('click', function () {
        var command = 'HOME';
        var data = {
           'command': command
        };

        postAPI('/home', data);
    });

     $('.visca-other-wait').on('click', function () {
        var command = 'WAIT';
        var time = $('#time-waiting').eq(0).val();
        var data = {
               'command': command,
               'time': time
        };

        postAPI('/wait', data);
     });

     $('.visca-change-address').on('click', function () {
        var command = 'CHANGE_ADDRESS';
        var newAddress = $('#change-address').eq(0).val();
        var data = {
            'command': command,
            'newAddress': newAddress
        };

        postAPI('/address', data);
     });

     $('.visca-macro-index').on('click', function () {
        window.location.href = "macroes";
    });
});

function postAPI(endpoint, data) {
    $('.response').val('');


    data['address'] = $('#change-destination').eq(0).val();

    $.ajax({
        type: 'POST',
        url: '/controller' + endpoint,
        data: data,
        success: function(msg){
            $('.response').val(msg);
        }
    });
}