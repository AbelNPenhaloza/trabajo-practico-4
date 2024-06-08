
document.addEventListener('DOMContentLoaded', function(){
    let deleteModal = document.getElementById('deleteModal');
    deleteModal.addEventListener('show.bs.modal', function (event){
        let button = event.relatedTarget;
        
        // Check the type of the modal to determine the correct attribute
        if (button.hasAttribute('data-codCarrera')) {
            let codCarrera = button.getAttribute('data-codCarrera');
            let form = deleteModal.querySelector('form');
            form.setAttribute('action', '/carrera/eliminar/' + codCarrera);
        } else if (button.hasAttribute('data-codMateria')) {
            let codMateria = button.getAttribute('data-codMateria');
            let form = deleteModal.querySelector('form');
            form.setAttribute('action', '/materia/eliminar/' + codMateria);
        } else if (button.hasAttribute('data-codAlumno')) {
            let codAlumno = button.getAttribute('data-codAlumno');
            let form = deleteModal.querySelector('form');
            form.setAttribute('action', '/alumno/eliminar/' + codAlumno);
        } else if (button.hasAttribute('data-codDocente')) {
            let codDocente = button.getAttribute('data-codDocente');
            let form = deleteModal.querySelector('form');
            form.setAttribute('action', '/docente/eliminar/' + codDocente);
        }
    });
});
