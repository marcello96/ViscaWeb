$(function () {

    $('.visca-position-up').on('click', function () {
        var data = {
            'command': 'PAN_TILT_UP',
            'panSpeed': $('#pan-speed').eq(0).val(),
            'tiltSpeed': $('#tilt-speed').eq(0).val()
        };
       postAPI('/position', data);
    });

    $('.visca-position-down').on('click', function () {
        var data = {
            'command': 'PAN_TILT_DOWN',
            'panSpeed': $('#pan-speed').eq(0).val(),
            'tiltSpeed': $('#tilt-speed').eq(0).val()
        };
        postAPI('/position', data);
    });
    $('.visca-position-left').on('click', function () {
        var data = {
            'command': 'PAN_TILT_LEFT',
            'panSpeed': $('#pan-speed').eq(0).val(),
            'tiltSpeed': $('#tilt-speed').eq(0).val()
        };
        postAPI('/position', data);
    });
    $('.visca-position-right').on('click', function () {
        var data = {
            'command': 'PAN_TILT_RIGHT',
            'panSpeed': $('#pan-speed').eq(0).val(),
            'tiltSpeed': $('#tilt-speed').eq(0).val()
        };
        postAPI('/position', data);
    });

    $('.visca-zoom-tele').on('click', function () {
        var data = {
            'command': 'ZOOM_TELE',
            'speed': $('#zoom-tele-speed').eq(0).val()
        };
        postAPI('/zoom-tele', data);
    });
    $('.visca-zoom-wide').on('click', function () {
        var data = {
            'command': 'ZOOM_WIDE',
            'speed': $('#zoom-wide-speed').eq(0).val()
        };
        postAPI('/zoom-wide', data);
    });

    $('.visca-other-home').on('click', function () {
           var data = {
               'command': 'HOME',
               'speed': $('input').eq(0).val()
           };
           postAPI('/other', data);
    });
});

function postAPI(endpoint, data) {
    $('.command').append(data['command']).append(' ');
    $.ajax({
        type: 'POST',
        url: '/controller' + endpoint,
        data: data,
        success: function(msg){
            $('.response').val(msg);
        }
    });
}

