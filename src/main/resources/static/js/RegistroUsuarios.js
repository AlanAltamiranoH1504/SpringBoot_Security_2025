document.addEventListener('DOMContentLoaded', () => {
    // console.log("JS CARGADO")
    const fromPrueba = document.querySelector("#fromPrueba");
    fromPrueba.addEventListener("submit", envioPeticionPostUsuario);


    function envioPeticionPostUsuario(e) {
        e.preventDefault();
        const token = document.querySelector("#otkenprueba").value;
        const bodyRequest = {
            nombre: "Alan",
            correo: "alan15ASA@gmail.com",
            telefono: "5565350430",
            password: "150402"
        }
        fetch("/usuarios/saveUsuario", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "X-CSRF-TOKEN": token
            },
            body: JSON.stringify(bodyRequest)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            console.log(data)
        }).catch((error) =>{
            console.log(error)
        })
    }
})
