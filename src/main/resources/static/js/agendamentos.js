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
        $('#btn-confirma-cancel').click(function () {
            acaoStatus($id, 'Cancelado');
        });
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
    $(document).on('click', "#reagendar", function () {
        var $row = $(this).closest("tr");
        var $id = $row.find("#id-agendamento").text();
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8080/agendamentos/reagendar/' + $id,
            success: function (data) {
                $("input[name*='id']").val(data.id).text();
                $("input[name*='dataAgendamento']").val(data.dataFormatada).text();
                $("input[name*='horaAgendamento']").val(data.horaFormatada).text();
                $('#input-cliente-agend').val(data.cliente.id).select();
                $('#btn-salvar-agendamento').html("Salvar");
                $('#agendamentoModalLabel').html("Reagendando cliente " + data.cliente.nome);
                $('#form-agendamento').attr("action", "/agendamentos/reagendar");
            },
            error: function () {
                alert("Houve um erro na requisição!");
            }
        });
    });

    $(document).on('click', "#btn-novo-agendamento", function () {
        $("#form-agendamento").trigger("reset");
        $('#agendamentoModalLabel').html("Novo agendamento");
        $('#input-cliente-agend').val('').text();
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

    //------------filtro data----------------
    $(document).on('click', "#btn-pesq-agendamento", function () {
        var data = $("#input-pesq-agendamento").val();
        $('#btn-pesq-agendamento').attr("href", "/agendamentos/filtrar?data=" + data);
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

    $('#input-pesq-agendamento').datepicker({
        uiLibrary: 'bootstrap4',
        format: 'dd/mm/yyyy',
        showOnFocus: true,
        showRightIcon: false
    });
});