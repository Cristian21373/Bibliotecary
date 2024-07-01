var url = "http://localhost:8080/api/v1/libro/";

function listaLibro() {
    var capturarFiltro = document.getElementById("SearchName").value;
    var urlLocal = url;
    if (capturarFiltro !== "") {
        urlLocal += "busquedafiltro/" + capturarFiltro;
    }

    $.ajax({
        url: urlLocal,
        type: "GET",
        success: function (result) {
            console.log(result); 

            var cuerpoTabla = document.getElementById("cuerpoTabla");
            cuerpoTabla.innerHTML = ""; 

            result.forEach(function (libro) {
                var trRegistro = document.createElement("tr");

                // Con esto se crean las celdas y se añaden datos
                var celdaId = document.createElement("td");
                var celdaTitulo = document.createElement("td");
                var celdaAutor = document.createElement("td");
                var celdaGenero = document.createElement("td");
                var celdaIsbn = document.createElement("td");
                var celdaCant_Dis = document.createElement("td");
                var celdaCant_Ocup = document.createElement("td");
                var celdaAcciones = document.createElement("td");

                // Asignar valores a las celdas
                celdaId.innerText = libro.id_libro;
                celdaTitulo.innerText = libro.titulo;
                celdaAutor.innerText = libro.autor;
                celdaGenero.innerText = libro.genero;
                celdaIsbn.innerText = libro.isbn;
                celdaCant_Dis.innerText = libro.cant_Dis;
                celdaCant_Ocup.innerText = libro.cant_Ocup;

                // Crear botones
                var botonActualizarLibro = document.createElement("button");
                botonActualizarLibro.innerText = "Actualizar";
                botonActualizarLibro.className = "btn btn-warning actualizar_Libro";
                botonActualizarLibro.onclick = function () {
                    $('#modalLibro').modal('show');
                    consultarLibroID(libro.id_libro);
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
                            eliminarLibro(libro.id_libro);
                        }
                    });
                };

                var botonDetalles = document.createElement("button");
                botonDetalles.innerText = "Detalles";
                botonDetalles.className = "btn btn-primary detalles_libro";
                botonDetalles.onclick = function () {
                    mostrarDetallesLibro(libro);
                };

                // Contenedor para los botones
                var divBotones = document.createElement("div");
                divBotones.className = "btn-group";
                divBotones.appendChild(botonActualizarLibro);
                divBotones.appendChild(botonEliminar);
                divBotones.appendChild(botonDetalles);

                // Añadir el contenedor de botones a la celda de acciones
                celdaAcciones.appendChild(divBotones);

                // Se añaden celdas al registro
                trRegistro.appendChild(celdaId);
                trRegistro.appendChild(celdaTitulo);
                trRegistro.appendChild(celdaAutor);
                trRegistro.appendChild(celdaGenero);
                trRegistro.appendChild(celdaIsbn);
                trRegistro.appendChild(celdaCant_Dis);
                trRegistro.appendChild(celdaCant_Ocup);
                trRegistro.appendChild(celdaAcciones);

                // Añadir el registro a la tabla
                cuerpoTabla.appendChild(trRegistro);
            });
        },
        error: function (error) {
            console.error("Error en la petición:", error);
            alert("Error en la petición: " + error.statusText);
        }
    });
}

function consultarLibroID(idLibro) {
    $.ajax({
        url: url + idLibro,
        type: "GET",
        success: function (libro) {
            document.getElementById("editTitulo").value = libro.titulo;
            document.getElementById("editAutor").value = libro.autor;
            document.getElementById("editGenero").value = libro.genero;
            document.getElementById("editIsbn").value = libro.isbn;
            document.getElementById("editCant_Dis").value = libro.cant_Dis;
            document.getElementById("editCant_Ocup").value = libro.cant_Ocup;

            document.getElementById("formEditarLibro").onsubmit = function (event) {
                event.preventDefault();
                actualizarLibro(idLibro);
            };
        },
        error: function (error) {
            console.error("Error al consultar el libro:", error);
            Swal.fire({
                title: "Error",
                text: "Ocurrió un error al consultar el libro. Por favor, inténtelo de nuevo.",
                icon: "error"
            });
        }
    });
}

function actualizarLibro(idLibro) {
    var titulo = document.getElementById("editTitulo").value;
    var autor = document.getElementById("editAutor").value;
    var genero = document.getElementById("editGenero").value;
    var isbn = document.getElementById("editIsbn").value;
    var cant_Dis = document.getElementById("editCant_Dis").value;
    var cant_Ocup = document.getElementById("editCant_Ocup").value;

    var formData = {
        "titulo": titulo,
        "autor": autor,
        "genero": genero, 
        "isbn": isbn,
        "cant_Dis": cant_Dis,
        "cant_Ocup": cant_Ocup
    };

    $.ajax({
        url: url + idLibro,
        type: "PUT", 
        data: JSON.stringify(formData),
        contentType: "application/json",
        success: function (result) {
            Swal.fire({
                title: "¡Actualizado!",
                text: "El libro ha sido actualizado correctamente.",
                icon: "success"
            });
            $('#modalLibro').modal('hide');
            listaLibro();
        },
        error: function (error) {
            console.error("Error al actualizar el libro:", error);
            Swal.fire({
                title: "Error",
                text: "Ocurrió un error al actualizar el libro. Por favor, inténtelo de nuevo.",
                icon: "error"
            });
        }
    });
}

// Función para eliminar el libro 
function eliminarLibro(idLibro) {
    $.ajax({
        url: url + idLibro,
        type: "DELETE",
        success: function (result) {
            listaLibro(); 
            Swal.fire({
                title: "¡Eliminado!",
                text: "El libro ha sido eliminado correctamente.",
                icon: "success"
            });
        },
        error: function (error) {
            console.error("Error en la petición de eliminación:", error);
            Swal.fire({
                title: "Error",
                text: "Ocurrió un error al eliminar el libro. Por favor, inténtelo de nuevo.",
                icon: "error"
            });
        }
    });
}


function registrarLibro() {
    let titulo = document.getElementById("Titulo").value;
    let autor = document.getElementById("Autor").value;
    let genero = document.getElementById("Genero").value;
    let isbn = document.getElementById("Isbn").value;
    let cant_Dis = document.getElementById("Cant_Dis").value;
    let cant_Ocup = document.getElementById("Cant_Ocup").value;

    // Validación para comprobar si todos los campos están vacíos
    if (titulo === "" && autor === "" && genero === "Seleccione el tipo de genero:" && isbn === "" && cant_Dis === "" && cant_Ocup === "") {
        Swal.fire({
            title: "Error",
            text: "Por favor, rellene todos los campos.",
            icon: "error"
        });
        return;
    }

    // Validaciones individuales de cada campo
    if (titulo === "") {
        Swal.fire({
            title: "Error",
            text: "Por favor, ingrese el título del libro.",
            icon: "error"
        });
        return;
    }
    if (autor === "") {
        Swal.fire({
            title: "Error",
            text: "Por favor, ingrese el autor del libro.",
            icon: "error"
        });
        return;
    }
    if (genero === "Seleccione el tipo de genero:") {
        Swal.fire({
            title: "Error",
            text: "Por favor, seleccione un género.",
            icon: "error"
        });
        return;
    }
    if (isbn === "" || isNaN(isbn) || parseInt(isbn) < 0) {
        Swal.fire({
            title: "Error",
            text: "Por favor, ingrese un número válido de ISBN que no sea negativo.",
            icon: "error"
        });
        return;
    }
    if (cant_Dis === "" || isNaN(cant_Dis) || parseInt(cant_Dis) < 0) {
        Swal.fire({
            title: "Error",
            text: "Por favor, ingrese un número válido de ejemplares disponibles que no sea negativo.",
            icon: "error"
        });
        return;
    }
    if (cant_Ocup === "" || isNaN(cant_Ocup) || parseInt(cant_Ocup) < 0) {
        Swal.fire({
            title: "Error",
            text: "Por favor, ingrese un número válido de ejemplares ocupados que no sea negativo.",
            icon: "error"
        });
        return;
    }


    // Datos del formulario
    let formData = {
        "titulo": titulo,
        "autor": autor,
        "genero": genero,
        "isbn": isbn,
        "cant_Dis": cant_Dis,
        "cant_Ocup": cant_Ocup
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
                text: "Error al guardar el libro",
                icon: "error"
            });
        }
    });
}







function limpiarFormulario() {
    document.getElementById("Titulo").value = "";
    document.getElementById("Autor").value = "";
    document.getElementById("Genero").value = "Seleccione el tipo de genero:";
    document.getElementById("Isbn").value = "";
    document.getElementById("Cant_Dis").value = "";
    document.getElementById("Cant_Ocup").value = "";
}


function mostrarDetallesLibro(libro) {
    Swal.fire({
        title: 'Detalles del Libro',
        html: `
            <p><strong>ID Libro:</strong> ${libro.id_libro}</p>
            <p><strong>Título:</strong> ${libro.titulo}</p>
            <p><strong>Autor:</strong> ${libro.autor}</p>
            <p><strong>Género:</strong> ${libro.genero}</p>
            <p><strong>ISBN:</strong> ${libro.isbn}</p>
            <p><strong>Cantidad Disponible:</strong> ${libro.cant_Dis}</p>
            <p><strong>Cantidad Ocupada:</strong> ${libro.cant_Ocup}</p>
        `,
        icon: 'info'
    });
}
