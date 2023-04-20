
const loginForm = document.getElementById("subscribe-form");


loginForm.addEventListener("submit", (event) => {
    console.log("loginForm");
    event.preventDefault();
    loginFromSubmitHandler(event.target);
});
function loginFromSubmitHandler(loginForm){
    const formData  = new FormData(loginForm);
    sendLoginData(formData);
};
function sendLoginData(formData){
    const email = formData.get("email");

    axios.post("http://localhost:8080/quantum/main", data: email,
        {headers: {'Content-Type': 'multipart/form-data'}}
    })
        .then(res => console.log(res))
        .catch(err => console.log(err));
};