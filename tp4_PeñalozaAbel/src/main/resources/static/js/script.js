
document.addEventListener('DOMContentLoaded', function(){
	let deleteModal = document.getElementById('deleteModal');
	deleteModal.addEventListener('show.bs.modal', function (event){
		let button = event.relatedTarget;
		let codCarrera = button.getAttribute('data-codCarrera');
		let form = deleteModal.querySelector('form');
		form.setAttribute('action', '/carrera/eliminar/' + codCarrera);	
	});
});