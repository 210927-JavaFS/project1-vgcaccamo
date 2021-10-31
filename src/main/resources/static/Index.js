const URL = "http://localhost:8081/";

let loginButton = document.getElementById("loginButton");
let buttonRow = document.getElementById("buttonRow");
let showMyTicketsButton = document.createElement("button");
let showAllTicketsButton = document.createElement("button");
//let addReimbursementButton = document.getElementById("addReimbursementButton");

loginButton.onclick = loginUser;
showAllTicketsButton.onclick = showAllTickets;
//addReimbursementButton.onclick = addReimbursement;

showMyTicketsButton.innerText = "Show My Tickets";
showAllTicketsButton.innerText = "Show All Tickets";

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
    document.getElementsByClassName("formClass")[0].innerHTML = "";
    buttonRow.appendChild(showAllTicketsButton);
  } else {
    let para = document.createElement("p");
    para.setAttribute("style", "color:red");
    para.innerText = "LOGIN FAILED";
    document.getElementsByClassName("formClass")[0].appendChild(para);
  }
}

async function showAllTickets() {
  let response = await fetch(URL + "reimbursements", {
    credentials: "include",
  });

  if (response.status === 200) {
    let data = await response.json();
    console.log(data);
    populateReimbursementsTable(data);
  } else {
    console.log("Couldn't find any tickets, sorry!");
  }
}

function populateReimbursementsTable(data) {
  let tbody = document.getElementById("reimbursementBody");
  tbody.innerHTML = "";

  for (let reimbursement of data) {
    let row = document.createElement("tr");
    let rowInfo = {
      id: reimbursement.id,
      amount: reimbursement.amount,
      description: reimbursement.description,
      type: reimbursement.type.type,
      status: reimbursement.status.status,
    };
    let tdId = document.createElement("td");
    let tdAmount = document.createElement("td");
    let tdDescription = document.createElement("td");
    let tdType = document.createElement("td");
    let tdStatus = document.createElement("td");
    tdId.innerText = rowInfo.id;
    tdAmount.innerText = rowInfo.amount;
    tdDescription.innerText = rowInfo.description;
    tdType.innerText = rowInfo.type;
    tdStatus.innerText = rowInfo.status;
    row.appendChild(tdId);
    row.appendChild(tdAmount);
    row.appendChild(tdDescription);
    row.appendChild(tdType);
    row.appendChild(tdStatus);
    tbody.appendChild(row);
  }
}
