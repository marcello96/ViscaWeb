$(function () {
    $('textarea').each(function () {
        this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
    }).on('input', function () {
        this.style.height = 'auto';
        this.style.height = (this.scrollHeight) + 'px';
    });

    $('.visca-position-up').on('click', function () {
        var command = 'UP';
        var panSpeed = $('#pan-speed').eq(0).val();
        var tiltSpeed = $('#tilt-speed').eq(0).val();
        var data = {
            'command': command,
            'panSpeed': panSpeed,
            'tiltSpeed': tiltSpeed
        };

        $(".commandContent").append(command).append('_').append(tiltSpeed).append(' ');
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

        $(".commandContent").append(command).append("_").append(tiltSpeed).append(' ');
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

        $(".commandContent").append(command).append("_").append(panSpeed).append(' ');
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

        $(".commandContent").append(command).append("_").append(panSpeed).append(' ');
    });

    $('.visca-zoom-tele').on('click', function () {
        var command = 'ZOOM_TELE';
        var speed = $('#zoom-tele-speed').eq(0).val();
        var data = {
            'command': command,
            'speed': speed
        };

        $(".commandContent").append(command).append("_").append(speed).append(' ');
    });

    $('.visca-zoom-wide').on('click', function () {
        var command = 'ZOOM_WIDE';
        var speed = $('#zoom-wide-speed').eq(0).val();
        var data = {
            'command': command,
            'speed': speed
        };

        $(".commandContent").append(command).append("_").append(speed).append(' ');
    });

    $('.visca-other-home').on('click', function () {
        var command = 'HOME';
        var data = {
           'command': command
        };

        $(".commandContent").append(command).append(' ');
    });

    $('.visca-other-wait').on('click', function () {
        var command = 'WAIT';
        var time = $('#time-waiting').eq(0).val();
        var data = {
               'command': command,
               'time': time
        };

        $(".commandContent").append(command).append('_').append(time).append(' ');
    });

    $('.visca-change-address').on('click', function () {
        var command = 'CHANGE_ADDRESS';
        var newAddress = $('#change-address').eq(0).val();
        var data = {
            'command': command,
            'newAddress': newAddress
        };

        $(".commandContent").append(command).append('_').append(newAddress).append(' ');
    });

    $('.visca-submit-command').on('click', function () {
        if (!$.trim($('.commandName').text()) ) {
            alert('Name can not be null');
        }
        else if (!$.trim($('.commandContent').val())) {
            alert('Content can not be null');
        }
        else {
            var data = {
                'macroName' : $('.commandName').text(),
                'macroContent': $('.commandContent').val()
            };
            $.ajax({
                type: 'PUT',
                url: '/controller/macro',
                data: data,
                success: function(msg){
                    window.location.href = "/macroes";
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert("Can not parse content");
                }
            });
        }
    });
});
