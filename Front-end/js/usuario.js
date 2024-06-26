var url = "http://localhost:8080/api/v1/usuario/";

function listaUsuario() {
    var capturarFiltro = document.getElementById("SearchName").value;
    var urlLocal = url;
    if (capturarFiltro !== "") {
        urlLocal += "busquedafiltro/" + filtroUsuario;
    }

    $.ajax({
        url: urlLocal,
        type: "GET",
        success: function (result) {
            console.log(result); 

            var cuerpoTabla = document.getElementById("cuerpoTabla");
            cuerpoTabla.innerHTML = ""; 

            result.forEach(function (usuario) {
                var trRegistro = document.createElement("tr");

                // Crear celdas y añadir los datos
                var celdaId = document.createElement("td");
                var celdaNombre = document.createElement("td");
                var celdaDireccion = document.createElement("td");
                var celdaCorreo_electronico = document.createElement("td");
                var celdaTipo_usuario = document.createElement("td");
                var celdaAcciones = document.createElement("td");

                // Asignar valores a las celdas
                celdaId.innerText = usuario.id_usuario;
                celdaNombre.innerText = usuario.nombre;
                celdaDireccion.innerText = usuario.direccion;
                celdaCorreo_electronico.innerText = usuario.correo_electronico;
                celdaTipo_usuario.innerText = usuario.tipo_usuario;

                // Crear botones de acción
                var botonActualizarUsuario = document.createElement("button");
                botonActualizarUsuario.innerText = "Actualizar";
                botonActualizarUsuario.className = "btn btn-warning actualizar_usuario";
                botonActualizarUsuario.onclick = function () {
                    $('#modal-usuario').modal('show');
                    consultarUsuarioID(usuario.id_usuario);
                };

                var botonEliminar = document.createElement("button");
                botonEliminar.innerText = "Eliminar";
                botonEliminar.className = "btn btn-danger eliminar";
                botonEliminar.onclick = function () {
                    Swal.fire({
                        title: '¿Estás seguro?',
                        text: "¡No podrás revertir esto!",
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Sí, eliminarlo'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            eliminarUsuario(usuario.id_usuario);
                        }
                    });
                };

                var botonDetalles = document.createElement("button");
                botonDetalles.innerText = "Detalles";
                botonDetalles.className = "btn btn-primary detalles_usuario";
                botonDetalles.onclick = function () {
                    mostrarDetallesUsuario(usuario);
                };

                // Contenedor para los botones
                var divBotones = document.createElement("div");
                divBotones.className = "btn-group";
                divBotones.appendChild(botonActualizarUsuario);
                divBotones.appendChild(botonEliminar);
                divBotones.appendChild(botonDetalles);

                // Añadir el contenedor a la celda de acciones
                celdaAcciones.appendChild(divBotones);

                // Añadir celdas a la fila
                trRegistro.appendChild(celdaId);
                trRegistro.appendChild(celdaNombre);
                trRegistro.appendChild(celdaDireccion);
                trRegistro.appendChild(celdaCorreo_electronico);
                trRegistro.appendChild(celdaTipo_usuario);
                trRegistro.appendChild(celdaAcciones);

                // Añadir la fila a la tabla
                cuerpoTabla.appendChild(trRegistro);
            });
        }
    });
}


function registrarUsuario() {
    let nombre = document.getElementById("Nombre").value;
    let direccion = document.getElementById("Direccion").value;
    let correo_electronico = document.getElementById("Correo_electronico").value;
    let tipo_usuario = document.getElementById("Tipo_usuario").value;


     // Validación para comprobar si todos los campos están llenos
     if (nombre === "" || direccion === "" || correo_electronico === "" || tipo_usuario === "") {
        Swal.fire({
            title: "Error",
            text: "Por favor, rellene todos los campos.",
            icon: "error"
        });
        return;
    }
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(correo_electronico)) {
        Swal.fire({
            title: "Error",
            text: "Por favor, ingrese un correo electrónico válido.",
            icon: "error"
        });
        return;
    }
    // Datos del formulario
    let formData = {
        "nombre": nombre,
        "direccion": direccion,
        "correo_electronico": correo_electronico,
        "tipo_usuario": tipo_usuario
    };

    $.ajax({
        url: url,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function (result) {
            Swal.fire({
                title: "¡Excelente!",
                text: "Se guardó correctamente",
                icon: "success"
            });
            limpiarFormulario();
        },
        error: function (error) {
            Swal.fire({
                title: "Error",
                text: "Error al guardar el usuario",
                icon: "error"
            });
        }
    });
}



function eliminarUsuario(id_usuario) {
    $.ajax({
        url: url + id_usuario,
        type: "DELETE",
        success: function (result) {
            Swal.fire(
                '¡Eliminado!',
                'El usuario ha sido eliminado.',
                'success'
            );
            listaUsuario(); 
        }
    });
}

function consultarUsuarioID(id_usuario) {
    $.ajax({
        url: url + id_usuario,
        type: "GET",
        success: function (usuario) {
            document.getElementById('editNombre').value = usuario.nombre;
            document.getElementById('editDireccion').value = usuario.direccion;
            document.getElementById('editCorreo_electronico').value = usuario.correo_electronico;
            document.getElementById('editTipo_usuario').value = usuario.tipo_usuario;

            // Llamar a la función de actualización al enviar el formulario
            document.getElementById('formEditarUsuario').onsubmit = function (event) {
                event.preventDefault();
                actualizarUsuario(id_usuario);
            };
        }
    });
}
function actualizarUsuario(id_usuario) {
    var nombre = document.getElementById('editNombre').value;
    var direccion = document.getElementById('editDireccion').value;
    var correo_electronico = document.getElementById('editCorreo_electronico').value;
    var tipo_usuario = document.getElementById('editTipo_usuario').value;
  
    var datos = {
      id_usuario: id_usuario,
      nombre: nombre,
      direccion: direccion,
      correo_electronico: correo_electronico,
      tipo_usuario: tipo_usuario
    };
  
    $.ajax({
      url: url + id_usuario,
      type: "PUT", // Change the HTTP method to POST
      data: JSON.stringify(datos),
      contentType: "application/json",
      success: function (result) {
        $('#modal-usuario').modal('hide');
        Swal.fire(
          '¡Actualizado!',
          'Los datos del usuario han sido actualizados.',
          'success'
        );
        listaUsuario();
      },
      error: function (xhr, status, error) {
        Swal.fire(
          'Error',
          'No se pudo actualizar el usuario. ' + error,
          'error'
        );
      }
    });
  }




function limpiarFiltros() {
    document.getElementById("SearchName").value = "";
    document.getElementById("SearchIsbn").value = "";
    listaUsuario();
}



function mostrarDetallesUsuario(usuario) {
    Swal.fire({
        title: 'Detalles del Usuario',
        html: `
            <p><strong>ID Usuario:</strong> ${usuario.id_usuario}</p>
            <p><strong>Nombre:</strong> ${usuario.nombre}</p>
            <p><strong>Direccion:</strong> ${usuario.direccion}</p>
            <p><strong>Correo electronico:</strong> ${usuario.correo_electronico}</p>
            <p><strong>Tipo de usuario:</strong> ${usuario.tipo_usuario}</p>
        `,
        icon: 'info'
    });
}