$(document).ready(function () {

    //confirma agendamento
    $(document).on('click', "#confirmar-agend", function () {
        var $row = $(this).closest("tr");
        var $id = $row.find("#id-agendamento").text();
        acaoStatus($id, 'Confirmado');
    });

    //cancelar agendamento
    $(document).on('click', "#cancelar-agend", function () {
        var $row = $(this).closest("tr");
        var $id = $row.find("#id-agendamento").text();
        acaoStatus($id, 'Cancelado');
    });

    //finalizar agendamento
    $(document).on('click', "#finalizar-agend", function () {
        var $row = $(this).closest("tr");
        var $id = $row.find("#id-agendamento").text();
        acaoStatus($id, 'Finalizado');
    });

//--------------------alteração status-------------------------------
    var acaoStatus = function (id, status) {
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8080/agendamentos/status/' + id + '?paramStatus=' + status,
            success: function (data) {
                window.location.assign("http://localhost:8080/agendamentos/listar")
            },
            error: function () {
                alert("Houve um erro na requisição!");
            }
        });
    };

//--------------------edição-----------------------------------------
    $(document).on('click', "#btn-editar-agendamento", function () {
        var $row = $(this).closest("tr");
        var $id = $row.find("#id").text();
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8080/agendamentos/editar/' + $id,
            success: function (data) {
                $("input[name*='id']").val(data.id).text();
                $("input[name*='username']").val(data.username).text();
                $("input[name*='email']").val(data.email).text();
                $("#input-role").val(data.role).select();
                $("#cbx-desativato").prop("checked", data.desativado);
                $('#btn-salvar').html("Editar");
                $('#usuarioModalLabel').html("Editando usuário " + data.username);
                $('#form-user').attr("action", "/usuarios/editar");
            },
            error: function () {
                alert("Houve um erro na requisição!");
            }
        });
    });

    $(document).on('click', "#btn-novo-usuario", function () {
        $("#form-user").trigger("reset");
        $('#usuarioModalLabel').html("Novo usuário");
        $('#btn-salvar').html("Adicionar");
    });

    $('table tr').each(function () {
        var statusText = $(this).find('#span-status').text();
        statusColor = $(this).find(".badge");

        if (statusText === "Confirmado") {
            statusColor.addClass("badge-info");
        }
        if (statusText === "Atrasado") {
            statusColor.addClass("badge-warning");
        }
        if (statusText === "Cancelado") {
            statusColor.addClass("badge-danger");
        }
        if (statusText === "Finalizado") {
            statusColor.addClass("badge-success");
        }
    });

    //------------datetimepiker--------------


    var today;
    today = new Date(new Date().getFullYear(),
        new Date().getMonth(),
        new Date().getDate());

    $('#datepicker').datepicker({
        uiLibrary: 'bootstrap4',
        format: 'dd/mm/yyyy',
        minDate: today
    });

    $('#timepicker').timepicker({
        uiLibrary: 'bootstrap4'
    });
});