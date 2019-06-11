$(document).ready(function () {
    $(document).on('click', "#btn-editar-cliente", function () {
        var $row = $(this).closest("tr");
        var $id = $row.find("#id-cliente").text();
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8080/clientes/editar/' + $id,
            success: function (data) {
                $("#input-id").val(data.id).text();
                $("#input-nome").val(data.nome).text();
                $("#input-fone").val(data.fone).text();
                $("#input-email").val(data.email).text();
                $("#input-sexo").val(data.sexo).select();
                $('#btn-salvar-cliente').html("Editar");
                $('#clienteModalLabel').html("Editando cliente " + data.nome);
                $('#form-cliente').attr("action", "/clientes/editar");
            },
            error: function () {
                alert("Houve um erro na requisição!");
            }
        });
    });

    $(document).on('click', "#btn-novo-cliente", function () {
        $("#form-cliente").trigger("reset");
        $('#clienteModalLabel').html("Novo cliente");
        $('#btn-salvar-cliente').html("Adicionar");
    });

    $(document).on('click', "#btn-excluir-cliente", function () {
        var $row = $(this).closest("tr");
        var $id = $row.find("#id-cliente").text();
        $('#btn-confirma').attr("href", "/clientes/remover/" + $id);
    });

    $(document).on('click', "#btn-pesq-cliente", function () {
        var nome = $("#input-pesq-cliente").val();
        $('#btn-pesq-cliente').attr("href", "/clientes/pesquisar?nome=" + nome);
    });
});