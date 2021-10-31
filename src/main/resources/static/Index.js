const URL = "http://localhost:8081/";

let loginButton = document.getElementById("loginButton");
let buttonRow = document.getElementById("buttonRow");
let showMyTicketsButton = document.createElement("button");
let showAllTicketsButton = document.createElement("button");
//let addReimbursementButton = document.getElementById("addReimbursementButton");

loginButton.onclick = loginUser;
//addReimbursementButton.onclick = addReimbursement;

showMyTicketsButton.innerText = "Show My Tickets";

async function loginUser() {
  let user = {
    username: document.getElementById("username").value,
    password: document.getElementById("password").value,
  };

  let response = await fetch(URL + "login", {
    method: "POST",
    body: JSON.stringify(user),
    credentials: "include",
  });

  if (response.status === 200) {
    console.log("logged in!");
    document.getElementsByClassName("formClass")[0].innerHTML = "";
    buttonRow.appendChild(showMyTicketsButton);
  } else {
    let para = document.createElement("p");
    para.setAttribute("style", "color:red");
    para.innerText = "LOGIN FAILED";
    document.getElementsByClassName("formClass")[0].appendChild(para);
  }
}
