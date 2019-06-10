$(document).ready(function () {
    $(document).on('click', "#btn-editar", function () {
        var $row = $(this).closest("tr");
        var $id = $row.find("#id").text();
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8080/usuarios/editar/' + $id,
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
});